# Lecture 1 — Linear Algebra & Probability Review

## Linear Algebra

### Notation
- **Matrix** `A ∈ ℝ^{m×n}`: m rows, n columns. Lowercase bold = vector, uppercase = matrix.
- `x ∈ ℝ^n` is a column vector; `x^T` is a row vector (transpose).
- `a_j` (or `A_{:,j}`) is the j-th column of A; rows can be written as `b_i^T`.
- A **tensor** generalizes matrices to more than 2 axes.

### Vector and Matrix Products
- **Inner / dot product**: `x^T y = Σ_i x_i y_i` (scalar).
- **Outer product**: `x y^T` is an `n×n` matrix with entries `x_i y_j`.
- **Matrix-vector product** `y = A x` has two views:
  - Row view: `y_i = b_i^T x`
  - Column view: `y = Σ_j a_j x_j` (linear combination of columns).

### Transpose, Symmetry, Trace
- `(A^T)^T = A`, `(AB)^T = B^T A^T`, `(A+B)^T = A^T + B^T`.
- A is **symmetric** if `A = A^T`.
- **Trace** `tr(A) = Σ_i A_{ii}` (sum of diagonal). Cyclic property: `tr(ABC) = tr(BCA) = tr(CAB)`.

### Norms (informally: lengths)
- **L2 / Euclidean**: `||x||_2 = sqrt(Σ x_i^2)`. Note `||x||_2^2 = x^T x`.
- **L1**: `||x||_1 = Σ |x_i|`.
- **L∞**: `||x||_∞ = max_i |x_i|`.
- **Lp**: `||x||_p = (Σ |x_i|^p)^{1/p}`.
- **Frobenius** (matrix): `||A||_F = sqrt(Σ A_{ij}^2) = sqrt(tr(A^T A))`.

### Quadratic Forms & Definiteness
- For symmetric A: `x^T A x = Σ_{i,j} A_{ij} x_i x_j` is a **quadratic form**.
- A is **positive definite (PD)** if `x^T A x > 0` for all nonzero x.
- **Positive semidefinite (PSD)**: `≥ 0`. Likewise negative (semi)definite. **Indefinite** otherwise.
- Used heavily in optimization (PD Hessian ⇒ local minimum).

### Eigenvalues and Eigenvectors
- `A x = λ x` with `x ≠ 0`: λ is eigenvalue, x eigenvector.
- Found by solving `det(λI − A) = 0`.
- `tr(A) = Σ λ_i` and `det(A) = Π λ_i`.

### Matrix Calculus
- **Gradient** of `f: ℝ^n → ℝ` is the column vector `∇_x f(x) = [∂f/∂x_1, …, ∂f/∂x_n]^T`.
- For `g: ℝ^{m×n} → ℝ`, gradient is the matrix of partials `∂g/∂A_{ij}`.
- **Hessian** `∇²_x f` is the n×n matrix of second-order partials `∂²f/(∂x_i ∂x_j)`.
- Useful identities:
  - `∇_x (x^T A x) = 2 A x` (A symmetric)
  - `∇²_x (x^T A x) = 2 A`

## Probability

### Events and Axioms
- **Sample space** Ω: set of all possible outcomes.
- **Events** are subsets of Ω. Complement `A^c = Ω \ A`.
- **Axioms**: Pr(Ω)=1, Pr(A) ≥ 0, countable additivity for disjoint events.

### Properties of Pr
- `0 ≤ Pr(A) ≤ 1`, `Pr(A^c) = 1 − Pr(A)`.
- **Independent**: `Pr(A∩B) = Pr(A) Pr(B)`.
- **Mutually exclusive**: `Pr(A∩B) = 0`.
- Inclusion-exclusion: `Pr(A∪B) = Pr(A) + Pr(B) − Pr(A∩B)`.
- **Conditional**: `Pr(A|B) = Pr(A∩B)/Pr(B)` when `Pr(B) > 0`.

### Total Probability and Bayes
- If {B_i} partition Ω: `Pr(A) = Σ_i Pr(A|B_i) Pr(B_i)`.
- **Bayes' rule**: `Pr(B_i|A) = Pr(A|B_i) Pr(B_i) / Σ_j Pr(A|B_j) Pr(B_j)`.

### Random Variables
- A random variable (RV) is a function `X: Ω → ℝ` (with measurability conditions).
- **CDF**: `F_X(x) = Pr(X ≤ x)` — non-decreasing, right-continuous, F(−∞)=0, F(∞)=1.
- **Continuous RV**: F differentiable; **PDF** `f_X(x) = dF_X/dx` and `Pr(X ∈ [x, x+Δx]) ≈ f_X(x) Δx`.
- **Discrete RV**: F is piecewise-constant; **PMF** `f_X(x) = Pr(X=x)`.
- Examples: Gaussian, exponential (continuous); Bernoulli, Poisson (discrete).

### Expectation, Mean, Variance
- `E[g(X)] = ∫ g(x) f_X(x) dx` (continuous) or `Σ g(x_i) f_X(x_i)` (discrete).
- **Mean** = `E[X]`. **Variance** = `E[(X − E[X])^2]`.
- Exponential RV: `f_X(x) = λ e^{−λx}`, `E[X] = 1/λ`, `Var(X) = 1/λ²`.

### Joint Distributions and Conditional Densities
- **Joint CDF/PDF/PMF** generalize the 1-D case to vectors `X = (X_1, …, X_n)`.
- Conditional density: `f_{Y|X}(y|x) = f_{X,Y}(x,y) / f_X(x)`.
- **Bayes for densities**: `f_{X|Y}(x|y) = f_{Y|X}(y|x) f_X(x) / f_Y(y)`.

### Useful Inequalities (used later for generalization)
- **Markov's inequality**: For `X ≥ 0` and `ε > 0`, `Pr(X ≥ ε) ≤ E[X]/ε`.
- **Hoeffding's inequality**: For independent `X_i ∈ [a_i, b_i]` and `S_n = Σ X_i`,
  `Pr(S_n − E[S_n] ≥ ε) ≤ exp(−2ε² / Σ(b_i − a_i)²)` (and a symmetric lower-tail version).

## Why this matters for deep learning
- Forward and backward passes are sequences of matrix-vector products and gradients.
- Loss landscape analysis uses Hessians (PSD ↔ convexity).
- Norms underlie regularization (L1, L2, weight decay).
- Probability framework defines empirical risk, generalization, and stochastic gradient noise.
