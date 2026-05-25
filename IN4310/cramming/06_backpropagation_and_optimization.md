# Lecture 6 — Backpropagation and Optimization

## ERM as Optimization
Train by solving
```
min_θ R_N(θ) = (1/N) Σ_{n=1..N} ℓ(h_θ(x_n), y_n)
```
Gradient is linear in the loss → `∇_θ R_N = (1/N) Σ_n ∇_θ ℓ_n` — we compute per-sample gradients and average.

## Directional Derivatives & the Gradient
- **Directional derivative** of f at x along v: `δ_v f(x) = lim_{ε→0} (f(x+εv) − f(x))/ε`.
- For differentiable f: `δ_v f(x) = v^T ∇f(x)`.
- **Gradient** `∇f(x)` points in the direction of **steepest ascent**; `−∇f(x)` is the steepest descent.

## Gradient Descent Framework
```
Initialize θ_0 and learning rate schedule (α_t).
for t = 0, 1, …:
    g_t = ∇_θ R_N(θ_t)
    θ_{t+1} = θ_t − α_t g_t
```
Stop when iteration limit, loss change, or `||g||` is small enough.

## Why we need backpropagation
- Naïve numerical gradient (finite differences): perturb each weight, evaluate loss → `O(d)` per partial, `O(d²)` per iteration. Too slow for d ≈ millions.
- **Backprop**: compute **all partial derivatives in `O(d)`** by re-using forward-pass values and applying the chain rule layer-by-layer.

## Forward Pass (recap)
```
a^{[0]} = x
z^{[l]} = W^{[l]} a^{[l-1]} + b^{[l]}
a^{[l]} = g(z^{[l]})           for l < L
a^{[L]} = softmax(z^{[L]})     (or sigmoid / identity)
```

## Backpropagation
Define the **error signal** `δ^{[l]}_j = ∂ℓ/∂z^{[l]}_j`.

### Output layer
- **Regression** (squared loss + identity): `δ^{[L]}_j = 2(a^{[L]}_j − y_j)`.
- **Classification** (softmax + cross-entropy): the messy derivatives collapse to
  `δ^{[L]}_j = a^{[L]}_j − y_j` — predicted prob minus one-hot label.

### Hidden layers (recursion)
By chain rule:
```
δ^{[l]} = (W^{[l+1]})^T δ^{[l+1]}  ⊙  g'(z^{[l]})
```
where `⊙` is element-wise product. The error is **back-propagated** through transposed weights and the activation derivative.

### Parameter gradients
```
∂ℓ/∂b^{[l]} = δ^{[l]}
∂ℓ/∂W^{[l]} = δ^{[l]} (a^{[l-1]})^T
```

### Forward & backward "messages"
- Forward message to neuron j in layer l: `a_i^{[l-1]}` (activation of upstream neurons).
- Backward message from neuron j in layer l: `δ_j^{[l]}` (error signal propagated to upstream neurons).
- Each weight gradient = forward message × backward message.

## Stochastic and Mini-batch SGD

### Plain SGD
- Pick one sample uniformly: `θ_{t+1} = θ_t − α_t ∇ℓ_n(θ_t)`.
- `O(d)` per update vs `O(Nd)` for full-batch GD.
- High **variance** in updates ⇒ noisy trajectory.

### Mini-batch SGD
- Use a batch of size B: `∇R_S(θ) = (1/B) Σ_{i ∈ S} ∇ℓ_i(θ)`.
- **Epoch** = one full pass through training data (typical loop: shuffle then iterate over batches of size B).
- Trades variance for compute; plays well with GPUs (parallel matmul).

### Convex vs non-convex
- Convex (linear/logistic regression): `||∇R|| ≈ 0` ⇒ global minimum.
- Non-convex (deep nets): risks include **local minima**, **saddle points**, **flat plateaus**. In practice many local minima are "good enough" — generalization matters more than the absolute global minimum.

## SGD with Momentum (Polyak)
Maintain a velocity term:
```
v_t = μ v_{t-1} − α g_t
θ_{t+1} = θ_t + v_t
```
- μ ∈ {0.5, 0.9, 0.99} typically.
- Accumulates exponentially-weighted past gradients.
- **Why it helps**:
  - **Skips saddle points** — even when `g_t ≈ 0`, leftover velocity carries you through.
  - Smooths zig-zags in skewed-curvature loss surfaces.
  - Polyak proved faster convergence than vanilla GD on convex problems.
- Risk: too much momentum → **overshooting**.

## Adaptive Variants

### AdaGrad (Duchi et al., 2011)
Track running sum of squared gradients per coordinate:
```
c_t = c_{t-1} + g_t ⊙ g_t
θ_{t+1} = θ_t − α · g_t / (√c_t + ε)
```
- Smaller learning rate where the gradient has been large (steep direction).
- Problem: c_t grows unboundedly → effective LR shrinks to 0.

### RMSProp
Replace cumulative sum with **exponential moving average**:
```
c_t = β c_{t-1} + (1 − β) g_t ⊙ g_t
θ_{t+1} = θ_t − α · g_t / (√c_t + ε)
```
- Solves AdaGrad's vanishing-LR problem.

### Adam (Kingma & Ba, 2015) — RMSProp + Momentum
```
m_t = β_1 m_{t-1} + (1 − β_1) g_t
v_t = β_2 v_{t-1} + (1 − β_2) g_t ⊙ g_t
m̂_t = m_t / (1 − β_1^t)            (bias correction)
v̂_t = v_t / (1 − β_2^t)
θ_{t+1} = θ_t − α · m̂_t / (√v̂_t + ε)
```
- Defaults: `β_1 = 0.9, β_2 = 0.999, α = 1e-3`.
- Bias correction matters in early iterations (when m_t, v_t are small).
- The default optimizer in most modern code.

## Key Takeaways
- Backpropagation = chain rule applied layer-by-layer using `δ^{[l]} = (W^{[l+1]})^T δ^{[l+1]} ⊙ g'(z^{[l]})`.
- For softmax + cross-entropy, the output δ simplifies to `a^{[L]} − y` — implement this directly.
- SGD + momentum (or Adam) are the standard choices; full-batch GD is rarely used.
- Non-convex loss landscapes are dominated by saddle points; momentum and adaptive methods help escape them.
