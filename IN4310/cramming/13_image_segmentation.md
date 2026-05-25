# Lecture 13 — Image Segmentation

## Three flavours
- **Semantic segmentation** — classify *every pixel*, distinguishing classes but **not** instances. Includes background classes (sky, road).
- **Instance segmentation** — pixels of *object* classes only, each instance gets its own label. Background ignored.
- **Panoptic segmentation** — semantic + instance combined: every pixel is labelled, and each instance of an object class gets a unique label.

## Semantic Segmentation

### Naive approaches
1. **Sliding window**: extract a patch, classify its centre pixel with a CNN. Very expensive; lacks global context.
2. **CNN without downsampling**: keep `H × W` everywhere. Small receptive field, expensive on large images, can't use pre-trained backbones.

### Basic recipe
- Use **downsampling** to grow the receptive field (capture global context).
- Then **upsample** back to image-level resolution.
- **Combine** information from earlier (high-res) layers to recover fine boundaries.

## Problem 1: Capturing Global Context

### Solution A — Downsampling (you already know this)
Max/avg pool or strided convolution. Pixels deeper in the network see large image regions.

### Solution B — Dilated / Atrous Convolution
```
y[i] = Σ_k x[i + r·k] w[k]
```
- `r` is the **dilation rate**. `r=1` is standard convolution.
- Larger `r` enlarges the receptive field **without** reducing resolution and **without** adding parameters.
- Replace downsampling layers by doubling the dilation rate instead.
- Used in DeepLabv3+ which applies several rates in parallel (Atrous Spatial Pyramid Pooling).

## Problem 2: Upsampling Back to Image Resolution

### Encoder-Decoder architecture
Encoder downsamples (Convs + pooling) → bottleneck → Decoder upsamples (unpooling + conv) → pixelwise prediction.

### Upsampling techniques
- **Nearest neighbour / bilinear / bicubic** — non-parametric, fast, no learnable weights.
- **Unpooling** — store the **argmax indices** during max-pool; place values back at those indices when unpooling, fill rest with zeros. Used in **SegNet**.
- **Transposed convolution** (a.k.a. *deconvolution* — incorrectly):
  - Learns kernel weights to enlarge the input.
  - For each input value `x_ij`, place `x_ij · K` into the output and accumulate.
  - Stride applied to the *output* spacing (stride 2 → outputs 2× apart).
  - PyTorch: `nn.ConvTranspose2d`.

## Problem 3: Precise Boundaries
Deeper layers know **what** (semantics) but not **where** (location). Earlier layers have the **where** at high resolution.

**Solution**: skip connections — decoder fuses information from corresponding encoder layers (FPN-style). Two famous instances:

### U-Net (Ronneberger 2015)
- Originally for biomedical images. Now a *de facto* baseline.
- Symmetric encoder-decoder; **copy-and-crop** skip connections at each scale.
- Up-conv 2×2 in the decoder; final 1×1 conv produces class scores.
- Many extensions: U-Net++, Attention U-Net.

### DeepLabv3+
- Backbone (DCNN with atrous convs) → **ASPP** (atrous convs at rates 6, 12, 18 + image pooling, in parallel) → 1×1 conv.
- Decoder fuses with low-level features, then upsamples by 4 → final 4× upsample → prediction.

## Instance Segmentation
Strategy: detect, then segment each instance.

### Mask R-CNN
Extends Faster R-CNN with a parallel **mask head**.
- For each ROI, predict (in parallel): class, refined box, **per-class mask** (small conv head, e.g. 28×28).
- Uses **RoIAlign** (no quantization) instead of RoIPool to keep masks pixel-accurate.
- Extensions: Cascade Mask R-CNN, etc.

## Panoptic Segmentation
- Every pixel gets a class label, and **things** (object instances) get unique IDs; **stuff** (sky, grass) does not.
- Approaches: shared backbone → separate semantic & instance heads, or one-shot models, or cascade models.
- Metric: **Panoptic Quality (PQ)**.

## Performance Metrics

### Mean IoU (mIoU) — semantic seg standard
Average per-class IoU (predicted mask vs GT mask).

### Mask mAP — instance seg standard
mAP computed with IoU on **masks** instead of boxes.

### Other
- **Pixel accuracy** — fraction of pixels correctly classified. Misleading on imbalanced classes.
- **Mean pixel accuracy** — averaged over classes.
- **Dice coefficient**:
  `Dice = 2|A ∩ B| / (|A| + |B|)`
  Same as F1 score for binary segmentation. Popular in medical imaging; UNet++ uses it as loss.

## Loss Functions
- **Semantic segmentation**: per-pixel cross-entropy, summed (optionally class-balanced).
- **Instance segmentation**: classification loss + box regression loss + mask loss (per-pixel CE on the predicted mask).
- Many domain-specific variants (Dice, focal, boundary, Tversky, ...).

## Segment Anything Model (SAM, Kirillov 2023)
A **foundation model** for segmentation that produces a mask for any prompt.

### Architecture
1. **Image encoder** — heavy ViT, run **once** per image; produces an image embedding.
2. **Prompt encoder** — encodes:
   - **Sparse**: points (positional + learned fg/bg embedding), boxes (top-left/bottom-right learned tokens), text (CLIP).
   - **Dense**: masks (downscaled by stride-2 convs).
3. **Mask decoder** — light ViT-style decoder + mask prediction head; predicts **3 masks** + IoU score for each.

### Training
- Predicts 3 candidate masks; backprop only the **minimum** loss over them — handles ambiguity.
- Loss: **Dice + Focal**.
- Trained iteratively, feeding back the best predicted mask.

### Properties
- **Efficient**: image encoder runs once; prompt encoder + decoder are light.
- **Zero-shot** segmentation on new domains.

### Failures
- "Includes a piece of another object": e.g. mask covers parts of two adjacent elephants.
- "Misses a part of an object": disconnected pieces left unmasked.

## Key Takeaways
- Encoder-decoder + skip connections (U-Net / FPN-style) is the dominant semantic segmentation pattern.
- Atrous/dilated convolutions enlarge receptive fields without losing resolution → DeepLabv3+ ASPP.
- Upsample with nearest-neighbour / unpooling / transposed convolutions.
- Instance segmentation usually = detection + per-ROI mask head (Mask R-CNN).
- Panoptic = semantic + instance over every pixel.
- mIoU for semantic, mask mAP for instance.
- SAM is a promptable foundation model for segmentation, trained with Dice+Focal and iterative refinement.
