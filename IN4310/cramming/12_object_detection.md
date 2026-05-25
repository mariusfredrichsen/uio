# Lecture 12 — Object Detection

## Tasks
- **Image classification**: one label per image.
- **Classification + localization**: at most one object → predict class probs + a bounding box.
- **Object detection**: unknown number of objects → many class labels and bounding boxes.

## Single-Instance Detection (Classification + Localization)
Network outputs a vector
```
y = [c_0, c_1, ..., c_K, b_r, b_c, b_h, b_w]
```
- `c_0` is the **background / "no object"** class.
- `c_1..c_K` are class probabilities.
- `b_r, b_c, b_h, b_w` are box centre-row, centre-column, height, width.

### Loss
Partition `y = [c, b]`. Let `c'`, `b'` be ground truths.
- **Box loss** (L2; L1 or smooth-L1 also common):
  `L_b(b, b') = Σ_i (b_i − b'_i)²`
- **Class loss** (cross-entropy):
  `L_c(c, c') = -Σ_i c'_i log c_i`
- **Total**:
  `L(y, y') = L_c + [c'_0 ≠ 0] · L_b`
  i.e., only penalise the box if there *is* an object.

### Pose estimation / landmark detection
Same idea — predict a list of `(r, c)` coordinates per landmark.

## Object Detection (Many Objects)
Output **multiple** boxes + class scores.

### Naive sliding window
Slide windows of multiple sizes; classify each. Cheap-classifier-friendly, but **prohibitive for CNN classifiers**.

### Two families
- **Two-stage detectors** — propose ROIs, then classify each.
  R-CNN, SPP-Net, Fast R-CNN, Faster R-CNN, R-FCN, FPN.
- **One-stage detectors** — single forward pass produces boxes + classes.
  YOLO, SSD, RetinaNet, CenterNet, EfficientDet.

## Two-Stage Detectors

### Pipeline
1. **Stage 1**: propose candidate ROIs (using "anchors" or selective search). Discard background-only proposals.
2. **Stage 2**: process each surviving ROI independently — assign a class and refine its box.

### Region proposal
- **Selective Search** — classical, no deep learning; merges similar superpixels into candidate boxes.
- **Region Proposal Network (RPN)** — small CNN that scores anchors as object/no-object.

### R-CNN family
- **R-CNN**: Selective Search → crop image → run CNN per region → SVM classifier + box regressor. Slow (CNN per region).
- **Fast R-CNN**: run CNN **once** on the whole image → reuse the feature map. Selective Search proposes regions; **ROI Pooling** crops fixed-size feature patches per region.
- **Faster R-CNN**: replaces Selective Search with a learned **Region Proposal Network** on the same feature map. End-to-end trainable.

## One-Stage Detectors

### Anchor-based
On the final feature map, slide a small kernel; at each spatial location predict a class + box offset for **each anchor** (a.k.a. *prior* or *default box*).
- Output shape: `H × W × A × (K+1+4)` for `A` anchors and `K` classes.
- Anchors of varying sizes/aspect ratios at the same location → multiple objects can share a centre.

### Anchor-free
Directly predict box corners or object centres without pre-defined anchors.

## Two Recurring Problems & Solutions

### Problem 1: Different object sizes
Anchors can vary, but each location's receptive field caps how big an object it can detect.

#### Solution A — **SSD** (Single-Shot Multibox Detector)
- Attach detection heads at **multiple feature-map scales**. Earlier (high-res) maps detect small objects; later (low-res) maps detect large objects.
- VGG-16 backbone + extra feature layers; ~8732 detections then **NMS**.

#### Solution B — **Feature Pyramid Network (FPN)**
- Deeper layers carry **semantically stronger** features but lower resolution.
- Combine top-down and bottom-up: take deep map → upsample 2× → add (via 1×1 conv) to higher-res shallow map → predict.
- All pyramid levels end up with the same channel dim (e.g. 256). Used as a backbone in many detectors (e.g. EfficientDet).

### Problem 2: Multiple overlapping predictions
Many boxes cover the same object → **Non-Maximum Suppression (NMS)**.

#### Intersection over Union (IoU)
```
IoU = Area(A ∩ B) / Area(A ∪ B)
```
0 (disjoint) to 1 (identical).

#### NMS algorithm
For each class:
1. Drop boxes with `c_k < threshold` (e.g. 0.5).
2. While unseen boxes remain:
   - Pick `R_max` = box with highest score.
   - Remove all boxes with `IoU(R, R_max) > 0.5`.
   - Move `R_max` to "keep".

### Problem 3: Imbalanced training (too many backgrounds)
Most anchors are background → loss dominated by easy negatives.
- **Hard Negative Mining (OHEM)**: when computing loss, keep only the *hardest* (lowest background-score) negatives.
- **Focal Loss** (RetinaNet) — re-weights cross-entropy:
  ```
  FL(p_t) = -(1 − p_t)^γ log(p_t)
  ```
  - `γ = 0` → ordinary CE.
  - `γ > 0` down-weights well-classified examples (large `p_t`).
  - An optional `α` factor up-weights non-background classes.
  - Used mostly in one-stage detectors.

## Training: Matching Anchors to Ground Truth
SSD-style strategy:
```
for every ground-truth box:
    match it to the anchor with biggest IoU
for every anchor:
    if max IoU with any GT box > threshold:
        match it to that GT box
```
Plus heavy **data augmentation** (cropping, resizing, mirroring, photometric distortions).

## DETR — Object DEtection with TRansformers (Carion 2020)
End-to-end transformer detector.
1. ResNet50 backbone → feature map.
2. 1×1 conv reduces channels → flatten to `HW × C` tokens + fixed positional encodings.
3. Transformer **encoder** processes the image tokens.
4. Transformer **decoder** takes `N` learnable **object queries** (random init) + encoder output → produces `N` outputs.
5. Each output → FFN → `(class, box)` or "no object".

Key properties:
- **Anchor-free** and **NMS-free** — both learned implicitly.
- Uses **bipartite (Hungarian) matching** between predictions and ground-truth boxes for the loss.

## Performance Metrics

### Single-class case (per IoU threshold, e.g. 0.5)
- **TP**: predicted box matches a GT box (IoU ≥ threshold).
- **FP**: predicted box not matching any GT.
- **FN**: GT box with no matching prediction.

### Precision / Recall (per class)
```
Precision = TP / (TP + FP)        (How many predictions are correct)
Recall    = TP / (TP + FN)        (How many ground truths are found)
```

### Precision–Recall curve
Each detection has a class score. Sweeping the score threshold traces a P-R curve. Lower threshold → more detections → higher recall, lower precision.

### Average Precision (AP)
Area under the P-R curve for class `k` → `AP_k`.

### Mean Average Precision (mAP)
```
mAP = (1/K) Σ AP_k
```
Often averaged over multiple IoU thresholds (COCO: 0.5:0.05:0.95).

## In Practice
- PyTorch / torchvision, pytorch.org/hub
- **MMDetection**, **Detectron2** — actively maintained toolboxes with many backbones.
- TensorFlow object detection API.

## "Bag of Freebies"
Tricks that increase **training** time but not **inference** time (data augmentation, label smoothing, mosaic, etc.).

## Key Takeaways
- Detection = classification + bounding box(es) for an unknown number of objects.
- Two-stage (Faster R-CNN) is generally more accurate; one-stage (YOLO/SSD/RetinaNet) is faster.
- Anchors give a fixed grid of priors; anchor-free / DETR predict boxes directly.
- IoU + NMS clean up overlapping predictions.
- FPN + multi-scale heads handle varying object sizes.
- Focal loss / hard-negative mining handle background imbalance in one-stage detectors.
- mAP (over classes, often over IoU thresholds) is the standard metric.
