# Lecture 2 — Linear Models

## What is (Deep) Machine Learning?
- **Learning** = converting experience into expertise/knowledge.
- **ML** = automated learning of meaningful patterns in data; goal is to label **unseen** data (generalization).
- Three main components of an ML system: **dataset**, **model (hypothesis class)**, **learning algorithm**.

## Components of an ML problem
1. **I/O**: define the input space `X` and output space `Y`.
2. **Prediction model** (hypothesis class): a parameterized function `h_w: X → Y`.
3. **Loss function** `ℓ(h_w(x), y)`: measures discrepancy between prediction and ground truth.
4. **Optimizer**: algorithm to update parameters `w` from labeled data.

The classic discriminative goal: pick `h ∈ H` minimizing misclassification probability `Pr(h(x) ≠ y)` under the **unknown** data distribution.

## Empirical Risk Minimization (ERM)
With a training set of size n,
```
R_n(w) = (1/n) Σ_i ℓ(h_w(x_i), y_i)
w* = arg min_w R_n(w)
```
Common losses:
- **Quadratic / squared L2**: `||h_w(x) − y||²` — symmetric, simple derivative; used for regression.
- **0-1 loss**: `1[h_w(x) ≠ y]` — counts mistakes; non-differentiable, useless for gradient methods.
- **Hinge loss**: `max(0, 1 − y h_w(x))` — convex upper bound on 0-1; used for SVMs.
- **Logistic loss**: `log(1 + exp(−y h_w(x)))`.

## Linear Regression
- Predictor: `h_{w,b}(x) = x^T w + b` (a weighted sum of features).
- Loss: squared L2.
- **Closed-form solution** (no bias): `w* = (X^T X)^{-1} X^T y`, where X stacks examples row-wise.
- Bias trick: append a 1 to every x; `w_{d+1}` becomes the bias. Same closed form applies.
- Closed form requires `X^T X` to be invertible — fails when n < d or with collinear features.

### Ridge Regression (L2 regularization)
- Why: plain linear regression **overfits** — fits noise → low train, high test error.
- Add penalty: `min_w Σ (x_i^T w − y_i)² + λ ||w||²`.
- Closed form: `w* = (X^T X + λ I)^{-1} X^T y`.
- For any `λ > 0`, `(X^T X + λ I)` is **positive definite** ⇒ always invertible, always has a unique minimum.
- λ is a **hyperparameter**: pick on a validation set.

## Classification with a Linear Model
- Output `Y` is discrete and finite. Binary: `Y = {−1, +1}` (or `{0,1}`); C-class: `Y = {0, …, C−1}`.
- Linear classifier: `f_{w,b}(x) = sgn(x^T w + b)`.
- 0-1 loss is not gradient-friendly — replace with **hinge loss** as a convex surrogate:
  `ℓ(h_w(x), y) = max(0, 1 − y h_w(x))`.

### Support Vector Machine (SVM)
Hinge loss + L2 regularization:
```
arg min_w (1/n) Σ_i max(0, 1 − y_i h_w(x_i)) + λ ||w||²
```
- Geometric view: `1/||w||_2` is the **margin** to maximize.
- Robust linear classifier; large-margin principle generalizes well.

## First-order Optimization
Most deep learning losses have **no closed-form solution**, especially with non-convex landscapes (deep nets) — use iterative methods.

### Gradient Descent (GD)
```
Initialize w_0
for t = 0, 1, 2, …
    w_{t+1} = w_t − α_t ∇_w R_n(w_t)
```
- α: **step size / learning rate**. Too large → divergence; too small → slow.
- Per-update cost `O(n d)` — expensive for huge datasets.

### Stochastic Gradient Descent (SGD)
- Sample one (or a mini-batch of) example j uniformly: `w_{k+1} = w_k − α_k ∇_w ℓ_j(w_k)`.
- Cost per update `O(d)` — much cheaper, but the trajectory is noisy.
- Noise can help **escape saddle points** in non-convex problems (deep nets).

### GD vs SGD intuition
- GD: smooth descent toward optimum.
- SGD: zig-zags around the path but eventually converges (with appropriate step-size schedule).

## ERM landscape: convex vs non-convex
- Linear / ridge regression and SVM are **convex** — every local min is global.
- Deep networks are **non-convex** — many local minima, saddle points, plateaus. SGD plus tricks (momentum, scheduling, etc.) is the workhorse.

## Why this matters
- ERM is the unifying framework for the rest of the course; everything later just changes `h_w` (model), the loss, or the optimizer.
- The convex linear case is the only place we can reason cleanly about closed-form solutions, regularization, and margins; deep nets re-use the same vocabulary.
