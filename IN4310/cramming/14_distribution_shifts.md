# Lecture 14 — Distribution Shifts

## The Problem
Standard ML assumes train and test data are drawn iid from the same distribution.
- Training: `{(x_i, y_i)}_i ~ p^tr(x, y)`.
- We learn `f` minimizing the test risk `R(f) = E_{p^te(x,y)}[ℓ(f(x), y)]`.
- **Distribution shift**: `p^tr(x, y) ≠ p^te(x, y)` → model can fail at deployment.
- The **underlying target function** is usually unchanged; only the *data distribution* differs.

### Examples
- Dynamic environments (infection rates during a pandemic).
- Domain adaptation (MNIST → SVHN).
- Different cameras, lighting, scale, label distribution between train and test.

## Types of Distribution Shift
Use the factorisations `p(x, y) = p(x) p(y|x) = p(y) p(x|y)`.

| Shift | What changes | What stays |
|---|---|---|
| **Covariate shift** | `p(x)` differs | `p(y\|x)` unchanged |
| **Label / target shift** | `p(y)` differs | `p(x\|y)` unchanged |
| **Output noise / concept shift** | `p(y\|x)` differs | `p(x)` unchanged |
| **Class-conditional shift** | `p(x\|y)` differs | `p(y)` unchanged |
| **Full shift** | `p(x, y)` differs | nothing |

### Covariate shift
- Marginal `p(x)` differs (e.g. different demographics) but `p(y|x)` is the same.
- Training gives labelled `(x, y)`, test only gives unlabelled `x`.
- Examples: medical imaging across hospitals, agriculture across regions, robotics across environments.

### Label shift
- `p(y)` differs (e.g. infection rate goes up) but `p(x|y)` (symptoms given infected) is the same.
- Limitation: `p(x|y=infected)` may also change with new variants → only an approximation.

### Generalised (Relaxed) Label shift
- `p(y)` shifts and `p(x|y)` shifts only **slightly**.
- Bound `D(p^tr(x|y), p^te(x|y))` by some discrepancy measure (e.g. KL).
- A **divergence** is a "directional distance" — not symmetric, doesn't satisfy triangle inequality.

## Learning Under Distribution Shift

### Empirical Risk Minimization (ERM) recap
```
min_w  R_n(w) = (1/n) Σ_i ℓ(f_w(x_i), y_i)
```
ERM minimises risk over `p^tr` → no guarantee on `p^te`.

### Distributionally Robust Optimization (DRO)
```
min_w  sup_{Q ∈ 𝒬}  Ê_{(x,y) ~ Q}[ℓ(f_w(x), y)]
```
- Minimise the **worst-case** loss over an uncertainty set `𝒬` of plausible test distributions.
- Pros: robustness guarantees.
- Cons: can be overly pessimistic — the worst case may be unrealistic.

### Importance-Weighted ERM (IWERM)
Re-weight each training example by the **importance ratio**:
```
r(x, y) = p^te(x, y) / p^tr(x, y)
```
Then
```
min_w  Ê_{p^tr(x,y)} [ r(x,y) · ℓ(f_w(x), y) ]
```
- **Consistent**: as `n → ∞`, IWERM converges to the true test risk minimiser (by the law of large numbers — derivation uses change of measure).

### Importance ratios under specific shifts
- Under **covariate shift** (`p(y|x)` unchanged):
  `r(x) = p^te(x) / p^tr(x)`
- Under **label shift** (`p(x|y)` unchanged):
  `r(y) = p^te(y) / p^tr(y)` — a discrete vector indexed by class.

### Example (label shift)
| Class | p^tr(y) | p^te(y) | Ratio |
|---|---|---|---|
| A | 0.6 | 0.3 | 0.5 |
| B | 0.3 | 0.5 | 1.67 |
| C | 0.1 | 0.2 | 2.0 |

Class C is *under-represented* in training (ratio > 1) → up-weight; class A is over-represented → down-weight.

## Density-Ratio Estimation
You don't need the individual densities — only the ratio `r(x, y)`.
- Estimating the ratio directly is generally more robust than estimating numerator and denominator separately.
- Hard for high-dimensional `x`. Easier under **label shift** (`y` is low-dim discrete) → estimating `r(y)` is a **convex** optimisation problem.

### Setup for label-shift estimation
- Labelled training data: `{(x^tr_i, y^tr_i)} ~ p^tr(x, y)`.
- **Unlabelled** test data: `{x^te_j} ~ p^te(x)`.
- Estimate `r̂(y) ≈ p^te(y) / p^tr(y)` from these samples.

## Comparing Two Distributions
Many problems boil down to: given samples from `P` and `Q`, are they the same? Two big families of measures:

### Integral Probability Metrics (IPMs)
```
D_𝓗(P, Q) = sup_{g ∈ 𝓗} | E_{X~P}[g(X)] − E_{Y~Q}[g(Y)] |
```
Examples: **Wasserstein distance**, **Maximum Mean Discrepancy (MMD)**.

### φ-Divergences
```
D_φ(P, Q) = ∫ q(x) φ(p(x)/q(x)) dx
```
with `φ` convex, `φ(1) = 0`. Examples: **KL** (`φ(x) = x log x`), **Hellinger**, **Pearson χ²**.

### Wasserstein (Earth Mover's) distance
```
W(P, Q) = inf_{γ ∈ Π(P,Q)} E_{(x,y) ~ γ}[ ||x − y|| ]
```
- "Cost of optimally transporting mass from `P` to `Q`."
- **Kantorovich-Rubinstein duality**:
  `W(P, Q) = sup_{||f||_L ≤ 1} (E_P[f] − E_Q[f])`,
  i.e. supremum over 1-Lipschitz functions.

### KL divergence reminder
`D_KL(P, Q) = ∫ p(x) log(p(x)/q(x)) dx` — comes from `φ(x) = x log x`.

### Limitation of φ-divergences
- If supports are **disjoint** → KL is `∞`. Even partial non-containment → `∞`.
- Wasserstein is well-defined and **symmetric** even on non-overlapping supports — a key reason it's preferred for generative modelling.

## Key Takeaways
- Distribution shift = `p^tr ≠ p^te`; the categories (covariate, label, concept, …) tell you *which* component changed.
- Under **covariate shift** label info is unchanged given x; under **label shift** the class-conditional input distribution is unchanged.
- **IWERM** corrects ERM by reweighting each sample by `p^te / p^tr`. Consistent in the limit.
- Importance ratios specialise nicely: `p^te(x)/p^tr(x)` for covariate shift, `p^te(y)/p^tr(y)` for label shift.
- Estimate the **ratio**, not the densities, when possible — easier in high dim.
- DRO minimises worst-case loss; can be too pessimistic.
- Discrepancy measures split into IPMs (Wasserstein, MMD) and φ-divergences (KL, Hellinger, χ²). Wasserstein handles non-overlapping supports gracefully.
