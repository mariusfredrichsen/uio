# IN4310 — Glossary

A cross-lecture reference. Entries are ordered alphabetically, grouped by concept area where useful.

## Linear Algebra & Probability
- **Tensor** — an N-dimensional array; matrices are 2-D tensors.
- **Inner product** `x^T y` — scalar; measures alignment.
- **Outer product** `x y^T` — rank-1 matrix.
- **Norm** — generalised "length"; common: L1, L2, L∞, Frobenius.
- **Eigenvalue / eigenvector** — `Ax = λx`. `tr(A) = Σλ_i`, `det(A) = Πλ_i`.
- **Positive (semi)definite** — `x^T A x > 0` (≥ 0) for all nonzero x. PD Hessian → local minimum.
- **Gradient / Hessian** — first / second derivatives w.r.t. a vector.
- **Random variable, PMF/PDF, CDF** — fundamental probability objects.
- **Bayes' rule** — `p(A|B) = p(B|A) p(A) / p(B)`.
- **Expectation, variance, covariance** — first/second moments.
- **Bernoulli, Gaussian, multinomial** — common distributions.

## Models & Loss Functions
- **Linear model** — `y = Wx + b`.
- **Sigmoid / logistic** — `σ(z) = 1/(1+e^{-z})`; squashes to (0,1).
- **Softmax** — `softmax(z)_k = e^{z_k} / Σ_j e^{z_j}`; multi-class generalisation of sigmoid.
- **MSE / L2 loss** — for regression.
- **Cross-entropy loss** — `-Σ y log ŷ`; KL divergence to true labels (lecture 11).
- **Hinge loss** — used by SVMs.
- **Smooth L1** — robust to outliers; common for box regression.
- **Focal loss** — `-(1-p_t)^γ log p_t`; down-weights easy examples (lecture 12).
- **Dice loss** — `1 − 2|A∩B|/(|A|+|B|)`; popular in medical segmentation.
- **Regularisation** — L1 (sparsity), L2 (weight decay), dropout, early stopping.

## Neural Networks
- **Neuron / unit** — affine transform + nonlinearity.
- **MLP / fully-connected layer** — `y = σ(Wx + b)`.
- **Activation function** — sigmoid, tanh, **ReLU**, Leaky ReLU, ELU, GELU, Swish.
- **Universal approximation theorem** — a single hidden layer is enough in theory; depth is more efficient in practice.
- **Vanishing / exploding gradients** — gradients shrink or blow up through deep nets.
- **Backpropagation** — recursive chain-rule application, computed via the **computational graph**.
- **Optimization**: SGD, Momentum, Nesterov, AdaGrad, RMSProp, Adam, AdamW.
- **Learning rate schedule** — step decay, cosine annealing, warmup.

## Convolutional Neural Networks
- **Convolution** — sliding kernel, weight sharing, translation equivariance.
- **Stride, padding** — control output size.
- **Receptive field** — region of input each unit "sees".
- **Pooling** — max / average; reduces resolution.
- **Feature map** — output of a conv layer (channels × H × W).
- **1×1 convolution** — pointwise channel mixing.
- **Batch / Layer / Group / Instance Normalisation** — normalise activations.
- **Dropout** — randomly zero units to regularise.

## Architectures
- **LeNet, AlexNet, VGG** — classic CNNs.
- **GoogLeNet / Inception** — multi-branch modules.
- **ResNet** — residual connections (`y = F(x) + x`); enables very deep nets.
- **DenseNet** — concatenative skip connections.
- **MobileNet / EfficientNet** — depth-wise separable convolutions, compound scaling.
- **U-Net** — encoder-decoder with copy-and-crop skip connections (lecture 13).
- **FPN** — feature pyramid for multi-scale prediction.

## Recurrent Networks
- **RNN** — state update `h_t = f(W_h h_{t-1} + W_x x_t)`.
- **BPTT** — backpropagation through time.
- **LSTM** — input/forget/output gates + cell state; mitigates vanishing gradients.
- **GRU** — simplified LSTM (reset + update gates).
- **Bidirectional / stacked RNNs** — process forward + backward / stack layers.
- **seq2seq** — encoder-decoder for variable-length input/output.

## Attention & Transformers
- **Attention** — weighted sum of values via query-key similarity (Bahdanau 2014).
- **Self-attention** — Q, K, V from the same input.
- **Scaled dot-product attention** — `softmax(QK^T/√d_k) V`.
- **Multi-head attention** — H parallel heads; concatenate + project.
- **Positional encoding** — sinusoidal / learned; injects order.
- **Transformer encoder / decoder block** — MHSA + FFN + LayerNorm + residual.
- **Pre-norm / post-norm** — placement of LayerNorm.
- **Masked self-attention** — for autoregressive generation.
- **ViT (Vision Transformer)** — image patches as tokens.
- **[CLS] token** — special learned token for classification.
- **Swin Transformer** — hierarchical ViT with shifted windows.
- **DETR** — end-to-end transformer object detector.

## Training, Generalization, Evaluation
- **Train / validation / test split** — typically 70/15/15.
- **k-fold cross-validation** — rotate validation folds.
- **Bootstrapping** — sample with replacement.
- **Overfitting / underfitting** — high variance / high bias.
- **Generalisation gap** — train vs test performance.
- **External testing** — evaluate on a *new* dataset; tests representativity.
- **Calibration** — predicted probabilities reflect true likelihoods.
- **Class imbalance** — class frequencies skewed.
- **Confusion matrix** — TP/FP/TN/FN per class.
- **Precision / Recall / F1** — see classification metrics.
- **ROC curve, AUC** — TPR vs FPR.
- **Average Precision (AP)** — area under P-R curve.
- **mean Average Precision (mAP)** — average AP across classes.

## Data Augmentation
- **Geometric**: flip, rotate, crop, scale, shear.
- **Photometric**: brightness, contrast, saturation, hue jitter.
- **Cutout / Random Erasing** — mask out random rectangles.
- **Mixup** — convex combinations of pairs of examples.
- **CutMix** — paste a patch from one image into another, mix labels accordingly.
- **AutoAugment / RandAugment** — learned/random augmentation policies.
- **Label smoothing** — soften one-hot labels for regularisation.

## Information Theory (Lecture 11)
- **Surprise** — `-log p(x)`.
- **Entropy** `H(X) = E[-log p(X)]` — expected uncertainty.
- **Joint / conditional entropy** — uncertainty in pairs / given knowledge.
- **Cross-entropy** `H(P; Q) = -Σ P log Q`.
- **KL divergence** `D_KL(P||Q) = Σ P log(P/Q)` — extra cost of using Q for P. Asymmetric.
- **Mutual information** `I(X;Y) = H(X) − H(X|Y)`. Zero iff independent.
- **Information Bottleneck** — view layer T as bottleneck: maximise `I(T;Y)`, minimise `I(X;T)`.
- **Bayes classifier** — argmax over `P(Y|X)`; theoretical optimum for 0-1 loss.

## Object Detection (Lecture 12)
- **Bounding box** — `(b_r, b_c, b_h, b_w)` (centre + size).
- **ROI (Region of Interest)** — candidate detection region.
- **Anchor / prior / default box** — pre-defined box at a location.
- **Anchor-free detector** — predicts box corners or centre directly.
- **Two-stage detector** — RCNN, Fast/Faster R-CNN. Propose + classify.
- **One-stage detector** — YOLO, SSD, RetinaNet. Single-pass.
- **Region Proposal Network (RPN)** — small CNN that proposes ROIs.
- **Selective Search** — classical (non-DL) region proposal.
- **ROI Pooling / RoIAlign** — fixed-size feature crop per ROI.
- **IoU (Intersection over Union)** — overlap metric for boxes/masks.
- **Non-Maximum Suppression (NMS)** — keep top-scoring box; drop highly overlapping ones.
- **Hard Negative Mining (OHEM)** — train on the hardest negatives.
- **Bipartite matching** — used in DETR to match predictions ↔ GT.

## Segmentation (Lecture 13)
- **Semantic segmentation** — pixel-level class labels.
- **Instance segmentation** — pixels grouped per object instance.
- **Panoptic segmentation** — semantic + instance combined.
- **Things vs stuff** — countable objects vs uncountable regions.
- **Encoder–decoder** — downsample then upsample.
- **Skip connection** — fuse encoder features into decoder.
- **Transposed convolution / "deconvolution"** — learnable upsampling.
- **Unpooling** — invert max-pooling using stored indices.
- **Dilated / atrous convolution** — kernel with gaps; larger receptive field, same params.
- **ASPP** — Atrous Spatial Pyramid Pooling (DeepLabv3+).
- **U-Net** — symmetric encoder-decoder + skip connections.
- **Mask R-CNN** — Faster R-CNN + per-ROI mask head.
- **mIoU** — mean Intersection over Union (semantic seg metric).
- **Dice coefficient** — `2|A∩B|/(|A|+|B|)`; F1 for binary segmentation.
- **Panoptic Quality (PQ)** — panoptic seg metric.
- **SAM (Segment Anything Model)** — promptable foundation model for segmentation.

## Distribution Shifts (Lecture 14)
- **Distribution shift** — `p^tr ≠ p^te`.
- **Covariate shift** — `p(x)` changes; `p(y|x)` fixed.
- **Label shift** — `p(y)` changes; `p(x|y)` fixed.
- **Concept shift** — `p(y|x)` changes.
- **Class-conditional shift** — `p(x|y)` changes.
- **Generalised label shift** — `p(y)` changes and `p(x|y)` changes "slightly".
- **Domain adaptation** — train on source, deploy on target with shifted distribution.
- **Empirical Risk Minimization (ERM)** — minimise mean training loss.
- **Importance-weighted ERM (IWERM)** — re-weight training samples by `p^te/p^tr`.
- **Importance / density ratio** `r = p^te / p^tr`.
- **DRO (Distributionally Robust Optimization)** — minimise worst-case loss over a family.
- **Discrepancy measure** — measure of how different two distributions are.
- **Integral Probability Metric (IPM)** — `sup_g |E_P g − E_Q g|`. Includes Wasserstein, MMD.
- **φ-divergence** — `∫ q φ(p/q) dx`. Includes KL, Hellinger, χ².
- **Wasserstein distance** — Earth Mover's; symmetric, defined on disjoint supports.
- **Kantorovich-Rubinstein duality** — Wasserstein over 1-Lipschitz functions.

## Miscellaneous
- **Inductive bias** — implicit assumptions baked into architecture (e.g. CNN locality / translation equivariance).
- **Foundation model** — large pretrained model usable across tasks (GPT, ViT, SAM, DINO).
- **Self-supervised learning** — pretext tasks without labels (DINO, SimCLR, MAE).
- **Zero-shot / few-shot learning** — perform new tasks with no / few examples.
- **Transfer learning** — fine-tune pretrained model on a new task.
- **Multi-modal model** — handles multiple input types (image + text + ...).
