# Lecture 5 — Deep Architecture Evolution

## Why architectures matter
- Deep nets learn hierarchical features but also suffer from **vanishing gradients**, **overfitting**, and **explosive parameter counts**.
- This lecture surveys the architectural ideas that made very deep training stable and accurate: regularization (dropout), normalization, multi-scale modules (Inception), shortcut connections (ResNet, DenseNet), transfer learning (fine-tuning).

## VGG (Simonyan & Zisserman, 2015)
- Stacks blocks of `Conv3 → ReLU → … → MaxPool`. Only 3×3 convs.
- Multiple stacked 3×3 ⇒ same effective receptive field as a larger filter, fewer parameters, more non-linearities.
- ~130M parameters — most of them in the **fully-connected head**.
- Uses **dropout** in the first two FC layers.
- Second on ImageNet 2014.

## Dropout (Srivastava et al., 2014)
- Training time: each neuron output is multiplied by a Bernoulli(p) mask `r ⊙ a^{[l-1]}`. Effectively temporarily removes neurons + their connections.
- Inference: no masking, but multiply weights by p (or use inverted dropout to skip rescaling).
- **Why it works**: forces the network to use a mixture of features rather than rely on one strong correlation; reduces co-adaptation; acts as ensemble of thinned subnetworks; regularization without extra parameters.

## GoogLeNet / Inception v1 (Szegedy et al., 2015)
Two ideas:
1. **Auxiliary classifiers** during training: extra softmax heads in the middle of the network, with weighted cross-entropy added to the main loss. Improves gradient flow to early layers and acts as regularization. Removed at inference.
2. **Inception module**: process the input at multiple scales **in parallel** (1×1, 3×3, 5×5 convs and 3×3 max pool) and concatenate along the channel axis.
   - **1×1 convolutions** are bottlenecks: reduce channels before expensive 3×3/5×5 convs ⇒ huge parameter savings.

### Inception v3
- **Asymmetric / factorized convolutions**: replace 3×3 with 1×3 followed by 3×1 — same receptive field, ~33% fewer ops.
- Replace 5×5 with two stacked 3×3.
- Don't apply factorization to early layers (loses too much expressiveness).
- Combined with **batch normalization**.

## ResNet — Residual Connections (He et al., 2016)
A residual block:
```
y = F(x) + x         (identity shortcut)
y = F(x) + W_s x     (projection shortcut when channel count changes)
```
- **Why it works**:
  - Forward: if F(x) isn't useful, the network can learn `F ≈ 0` and the block becomes the identity — never hurts performance.
  - Backward: gradient flows through the shortcut → mitigates **vanishing gradients**, enables training of nets 50+ layers deep.
- Structure:
  - **BatchNorm after every conv**.
  - As we go deeper: halve spatial size, double channel count.
  - Bottleneck variant for deep models (1×1 → 3×3 → 1×1).
- Plain 34-layer net trains worse than 18-layer; ResNet-34 trains better than ResNet-18 — depth finally helps.

## Normalization

### Batch Normalization (Ioffe & Szegedy, 2015)
For mini-batch B = {x_1, …, x_m}:
```
μ_B = (1/m) Σ x_i
σ²_B = (1/m) Σ (x_i − μ_B)²
x̂_i = (x_i − μ_B) / sqrt(σ²_B + ε)
y_i = γ x̂_i + β     (γ, β learnable)
```
- For conv layers: compute statistics over **(batch × spatial positions)** for each channel.
- **Inference** uses running averages of `μ` and `σ²` from training. Don't forget `model.eval()`!
- Needs decent batch size (≥ 8, ideally 16–32+).
- **Why it works**: stabilizes activation scale across layers, lets you use larger learning rates, mild regularization (each example sees noise from other batch members).

### Group Normalization (Wu & He, 2018)
- Compute statistics over a **group of channels** in one sample (independent of batch). Works at small batch sizes.

### Layer Normalization (Ba et al., 2016)
- Compute statistics over **all channels of one sample** in one layer.
- No batch-size dependence ⇒ used in transformers and RNNs.

## DenseNet (Huang et al., 2017)
- Inside a **dense block** (constant feature-map size), each layer receives the **concatenation** of all preceding feature maps.
- **Growth rate** k: number of new channels added per layer. Inputs at depth ℓ have `k_0 + (ℓ-1)·k` channels.
- **DenseNet-B** (bottleneck): 1×1 conv before each 3×3 to control input channels.
- **DenseNet-C**: transition layer halves channel count between blocks.
- Common variant: **DenseNet-BC** — strong accuracy with relatively few parameters.

## Fine-Tuning (Transfer Learning)
- **Don't train deep nets from scratch** unless your dataset has hundreds of thousands of examples. Initialize from weights trained on a similar large dataset (e.g., ImageNet via `torchvision.models`).
- Loading is done **bottom-up** — never load a layer if the layer below it is reset.
- **Why it works**: ERM is non-convex; high-dimensional, lots of bad local minima. A pretrained init lands in a good basin. Empirically, low-level filters (edges, textures) are reusable across very different tasks.

### Two regimes
- **Train only the top layer(s)** (freeze the backbone): best for very small datasets; cheap; bottom features can be precomputed (no augmentation, though).
- **Fine-tune all layers**: better when you have enough data; check on the validation set.

## State of the Art
- With lots of compute and data: **Vision Transformers (ViT)** — covered in lecture 10.

## Key Takeaways
- Regularization: **dropout** + augmentation prevents overfitting.
- Normalization: **BatchNorm / LayerNorm / GroupNorm** stabilize training, enable depth.
- Skip connections: **ResNet identity shortcuts** make very deep nets trainable.
- Multi-scale: **Inception** modules capture features at multiple receptive fields.
- Feature re-use: **DenseNet** concatenates rather than adds.
- Always **fine-tune** from pretrained weights when possible.
