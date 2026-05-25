# Lecture 8 — Data Augmentation, Initialization & Pre-training Tricks

## Why we need this
Inference data almost always differs from training data: lighting, resolution, camera, demographics. The "deep learning answer" — collect more data — runs out of steam. We close the gap with **data augmentation**, careful **preprocessing**, **weight initialization**, and **pre-training** strategies (transfer learning, distillation, contrastive).

## Terminology
- **Generalization** (model property): `f` works on `D_train ≈ D_target`.
- **Domain adaptation** (model property): `f` works even when `D_train ≠ D_target`.
- **Out-of-distribution (OOD)** (data): samples significantly different from training.
- **Outliers** (data): tails of the distribution.
- **Distribution shift** (data): different statistics overall.

## Rule #1: Preprocess your inputs
Standardize before feeding to the network:
- **MinMax**: `z = (x − min)/(max − min) ∈ [0, 1]`.
- **MaxAbs**: `z = x / max(|x|) ∈ [−1, 1]`.
- **Standardization**: `z = (x − μ)/σ ⇒ E[z]=0, Var[z]=1`.

Why: control variance, improve convergence, reduce numerical instability.

## Data Augmentation
Cheaper than collecting more data. Generate samples that look natural but expose the model to expected variation.

### Geometric transformations
Change spatial layout:
- **Flip** (horizontal/vertical): `img.flip(dim)`.
- **Rotation**: rotation matrix `[[cosθ, sinθ], [−sinθ, cosθ]]`.
- **Affine** (generalizes flip + rotation + shear + scale + translate): 2×3 matrix in homogeneous coordinates. More expressive, more compute.

### Photometric transformations
Change pixel values:
- **Color jitter**: random perturbations in HSV/HSL space (note hue is angular).
- **Solarization**: invert pixels above a threshold.
- **Blur**: low-pass filter (e.g., Gaussian).

### Mixed / advanced
- **RandAugment**: random combination of ~14 photometric/geometric transforms with a single intensity hyperparameter.
- **Mixup**: blend two images with a random convex weight, blend their labels accordingly.
- **CutMix**: copy a random box from one image into another, blend labels by area.

### Label Smoothing
- Originally Szegedy et al. (2015).
- Treat the label as correct with probability `1 − ε`; spread `ε` over other classes.
- Acts as a regularizer; helps with mislabeled / ambiguous samples.

### Choosing augmentations — "no free lunch"
Augmentations are **task-specific**:
- Grayscale destroys flag colors → bad for "Sweden vs Denmark".
- Flipping flips Côte d'Ivoire ↔ Ireland.
- Rotation works for natural photos but not for OCR.
- For pathology, **color jitter** mimics inter-lab stain variation (good).
- Augmenting the **validation set** to look like test is a legit technique — but pre-specify it; otherwise it's leakage.

## Handling Variable Input Sizes
- Naïve: rescale every image to one canonical size.
- Better: convolutions are size-agnostic; use a **global pooling** layer before the FC head so the network handles any input larger than its minimum.
- Conv output dim: `d' = floor((d − k − 2p)/s + 1)`.
- **Global pooling** (`nn.AdaptiveAvgPool2d((1,1))`) collapses spatial dims to 1×1 per channel.

## Augmentation in Other Domains
- **Audio**: pitch shift, resampling, modulation, equalisation — applied on the fly.
- **NLP** (categorical, ordering matters): masking (BERT-style), random insert/swap/delete preserving N, synonym substitution via embeddings, **back-translation** (translate to another language and back).
- General-purpose tricks: additive noise, sample from generative models — both have downsides (drift from real distribution, GAN artifacts).

## Weight Initialization
Bad init → vanishing/exploding gradients, slow convergence, dead neurons.

### Strategies
- **Identity**: `W_θ x = x`. Simple, no bias, unit gradients. Cons: off-diagonals stay 0 unless you add tiny noise; only square matrices.
- **Orthogonal**: `A^T A = I`. Invariant to rotation/scaling, optimal gradient propagation. Cons: must be square; tricky for convs.
- **Random** (most common): Gaussian or Uniform with zero mean — non-informative, computationally cheap.

### Random init schemes (target: `Var(σ(W x + b)) ≈ Var(x)`)
- **Xavier / Glorot** (sigmoid-like activations):
  `W_{i,j} ~ N(0, 2/(n_in + n_out))`.
- **Kaiming / He** (ReLU-like activations):
  `W_{i,j} ~ N(0, 2/n_in)`.

### Asymptotic orthogonality
Large random zero-mean matrices with proper variance are *approximately* row-orthogonal (LLN: average inner product → 0). So Xavier/Kaiming inherit many benefits of orthogonal init for free.

### Pre-trained weights (transfer learning)
- Take a model trained on ImageNet (or similar large source); copy weights bottom-up; replace and fine-tune the head.
- Beats random init when your target dataset is small.

## Reparametrization Tricks
- **Weight normalization**: `W = W̃ / ||W̃||_2` — keep weights on the unit sphere.
- **Weight standardization**: zero-mean, unit-variance per output channel — designed to play with **GroupNorm / LayerNorm** rather than BatchNorm.

## Beyond Supervised Training
When labels are scarce:

### Knowledge Distillation (Student-Teacher)
- Train a smaller **student** to match the **teacher's** softmax outputs (soft labels) using cross-entropy.
- Useful when the big teacher is too slow/expensive at inference.
- Soft labels carry more info than one-hot (encodes class similarity).

### Contrastive Learning (e.g., SimCLR)
- Pre-train without labels.
- For each image, create two augmented views (positive pair); other images in the batch are negatives.
- Loss pulls positive pairs together and pushes negatives apart in representation space:
```
ℓ_{i,j} = − log(exp(sim(z_i, z_j)/τ) / Σ_{k≠i} exp(sim(z_i, z_k)/τ))
sim(u, v) = u^T v / (||u||·||v||)        (cosine similarity)
```
- τ = temperature.
- Drawback: needs **very large batch sizes** for enough informative negatives.
- Issue when negatives are nearly indistinguishable from positives → loss becomes noisy.

## Geometric Picture
Without augmentation: Voronoi cells from training points form decision regions — many regions are tiny / fragile.
With augmentation: each training point grows a "ball" of close-by samples, decision regions become smoother and more robust to noise.

## Key Takeaways
- Always preprocess; augment as much as the task allows but **respect class invariances**.
- Randomization should be applied per-iteration, with sensible probabilities.
- Initialization matters: use **Kaiming for ReLU, Xavier for sigmoid**, or pre-trained weights.
- For label-scarce settings, contrastive pre-training and distillation are powerful.
