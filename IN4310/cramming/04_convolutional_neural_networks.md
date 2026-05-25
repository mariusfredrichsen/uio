# Lecture 4 — Convolutional Neural Networks (CNNs)

## The Problem with Fully-Connected Layers on Images
- Dense layers connect every neuron to every neuron in the previous layer.
- For a 224×224×3 image, layer 1 already has ~150k inputs ⇒ huge weight matrix, lots of parameters.
- Most useful image patterns are **local** (an edge, a corner) and **translation-invariant** (a cat is a cat anywhere in the frame). FC layers ignore this structure.

## The Convolution Operator
- A **kernel / filter** is a small tensor of learned weights (e.g. 3×3).
- Slide it across the input; at each position compute an inner product between kernel and the underlying patch (plus bias). That scalar fills one position in the output.
- 2-D convolution:
```
z[i,j] = Σ_m Σ_n K[m,n] · x[i+m, j+n]   (cross-correlation, what most libraries do)
```
- Frameworks call it "convolution" even though strictly it's cross-correlation; the kernel is learned, so it doesn't matter.

### Multi-Channel Convolution
- Input has C_i channels (e.g. RGB → 3); each filter is a 3-D kernel `(C_i × k × k)`.
- A layer applies C_o filters → output has C_o channels.
- Per-output-pixel:
```
z[r, i, j] = Σ_m Σ_n Σ_{c=1..C_i} K[c, m, n, r] · x[c, i+m, j+n]
```
- Output shape is set by: input channel count, kernel size, stride, padding, and **# output channels**.

## Why Convolutions Work
1. **Parameter sharing**: same kernel slides everywhere ⇒ k² parameters per filter, regardless of input size. A 1-D filter of size 5 has 5 weights, not "5 × #outputs".
2. **Local pattern detection**: the inner product is a similarity measure — large positive output means input patch resembles the kernel.
3. **Translation invariance**: applying the same filter at every spatial location detects the pattern wherever it appears.
4. **Hierarchical features**: stacking conv layers, early layers detect edges → mid layers detect parts → deep layers detect object-level concepts.
5. **Stack small filters > use one big filter**: two 3×3 (18 params) ≈ one 5×5 receptive field (25 params). Three 3×3 < one 7×7. More non-linearities + fewer params.

## Stride and Padding

### Stride `s`
- Number of input cells the kernel jumps each step.
- `s > 1` downsamples spatially by ~factor of s.

### Padding `p`
- Adds a border to the input so the kernel can fit at edges.
- **Zero padding** is the default. Other variants: replicate, reflect, fill with mean pixel.
- Three modes:
  - **Valid** (no padding): output shrinks to `(M − k + 1) × (N − k + 1)`.
  - **Same**: output keeps same size — set `p = (k − 1)/2` with `s = 1`.
  - **Full**: output grows to `(M + k − 1) × (N + k − 1)`.

### Output Spatial Size Formula
```
N^{[l+1]} = floor( (N^{[l]} + 2p − k) / s + 1 )
```

## Receptive Field
- The region of the **input image** that influences a particular output neuron.
- Recursion (one filter per layer):
```
R^{[l]} = R^{[l-1]} + (k^{[l]} − 1) · Π_{i=1..l-1} s^{[i]}
```
- Unrolled:
```
R^{[L]} = Σ_{j=1..L} (k^{[j]} − 1) · Π_{i<j} s^{[i]}
```
- Stride and depth grow the receptive field; deeper net + smaller filters = larger receptive field per parameter.

## Pooling
- Down-sample without learning parameters; force local invariance to small shifts.
- Operates **per channel**.
- **Max pooling**: take the max over a window — keeps the strongest detector response.
- **Average pooling**: take the mean.
- Typical: 2×2 stride 2 ⇒ halves H and W.
- Replaces the role of large strides in some architectures; often combined with increasing #channels as you go deeper.

## Classical CNN Architecture
```
[Conv → ReLU → (Pool)] × n  →  Flatten / global avg pool  →  Dense → Softmax
```
- Spatial dims shrink, channel count grows as the network deepens.
- Two ways to produce class scores:
  - **Fully convolutional**: last conv layer has C filters, then global average pool + softmax.
  - **Dense head**: flatten the final feature map and put one or two FC layers on top.

## Algebraic Properties (Discrete Convolution)
- Commutative: `f * g = g * f`.
- Associative: `(f * g) * h = f * (g * h)` — justifies stacking filters mathematically.
- Distributive over addition; associative with scalar multiplication.

## Edge-Detection Examples
- Horizontal edge filter `[[1,2,1],[0,0,0],[-1,-2,-1]]` highlights vertical-direction intensity changes (and vice-versa for vertical edges).
- Convolution + ReLU: produces a "feature map" where high values indicate the pattern is present at that location.

## Why this matters
- CNNs are the standard inductive bias for image data: locality + translation equivariance + parameter efficiency.
- Almost every architectural innovation (ResNet, U-Net, ViT) is reasoned about in terms of receptive field, channel count, and parameter count — get fluent in those numbers.
