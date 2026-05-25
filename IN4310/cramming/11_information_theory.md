# Lecture 11 — Information Theory & Deep Learning Theory

## Motivating Questions
- What are we actually learning in a neural network?
- Why is cross-entropy loss defined the way it is?
- Is there a fundamental bound on how good our network can be?
- What happens to information as it flows through the layers?

## Entropy
Measures **uncertainty** in a (discrete) random variable `X`.
- **Surprise** of an outcome `x` is `-log p(X=x)` — rare outcomes are surprising.
- **Entropy** is the *expected* surprise:
  ```
  H(X) = -Σ_x p(X=x) log p(X=x) = E[-log p(X)]
  ```
- High entropy → very uncertain (uniform-ish). Low entropy → mostly predetermined.

### Examples
- Uniform die over n outcomes: `H(X) = log(n)`.
- Coin lands H/T/Edge with `1/4, 1/4, 1/2`: `H = 3/2 < log(3)`. Skewing reduces entropy.

### Joint and Conditional Entropy
- **Joint** `H(X, Y) = -Σ_{x,y} p(x,y) log p(x,y) = E[-log p(X, Y)]` — total uncertainty in both.
- **Conditional** `H(Y|X) = Σ_x p(x) H(Y|X=x) = E[-log p(Y|X)]` — uncertainty left in Y after seeing X.

### Properties
- `H(0) = 0` (a point mass has zero entropy).
- If `X ⊥ Y`, then `H(X, Y) = H(X) + H(Y)` — entropy is additive for independent variables.

## Cross-Entropy & KL Divergence
Let `P` be the true distribution, `Q` our model.

- **Cross-entropy** `H(P; Q) = -Σ_x P(x) log Q(x)` — expected number of bits when encoding data from `P` using a code optimized for `Q`.
- **KL divergence** is the *additional* uncertainty paid for using `Q` instead of `P`:
  ```
  D_KL(P || Q) = H(P; Q) − H(P) = Σ_x P(x) log(P(x) / Q(x))
  ```
- Properties:
  - `D_KL(P || Q) ≥ 0`, with `=0` iff `P = Q`.
  - **Asymmetric**: `D_KL(P || Q) ≠ D_KL(Q || P)` in general → not a metric.

## Mutual Information
How much does knowing one variable tell us about another?
- `I(X; Y) = H(X) − H(X|Y)` (reduction of uncertainty).
- Equivalently, `I(X; Y) = D_KL(p(X,Y) || p(X) p(Y))`.
- `I(X; Y) = 0` iff `X ⊥ Y`.

### Venn diagram intuition
The two circles `H(X)` and `H(Y)` overlap in `I(X; Y)`. The non-overlap parts are `H(X|Y)` and `H(Y|X)`. Their union is `H(X, Y)`.

## Statistical Learning view
Standard supervised setup: `(X, Y) ~ p(X, Y)`. We want
```
f* = argmin_f E[ℓ(Y, f(X))]
```
With 0-1 loss this becomes `f*(x) = argmax_k P(Y=k | X=x)` — the **Bayes classifier**, the best possible classifier given `P(Y|X)`.

## Why Cross-Entropy Loss?
Treat the softmax output as a distribution `q(Y|X)` with `q(Y=k|x) = f(x)_k`.

Compute KL divergence to the true conditional `P(Y|X)`:
```
D_KL(P(Y|X) || q(Y|X)) = H(P(Y|X), q(Y|X)) − H(P(Y|X))
```
The second term doesn't depend on the model. Minimizing the first is therefore the same as minimizing `D_KL`. With a Monte-Carlo estimate over the dataset:
```
L_CE = -1/N Σ_i log q(y_i | x_i)
```
**Cross-entropy loss is exactly minimizing the KL divergence between the empirical conditional and the model's predicted conditional.**

## Information Bottleneck
Layers form a Markov chain `X → X_1 → X_2 → ... → X_n = Ŷ`. Pick any layer `T`.
- **Want** high `I(T; Y)` (representation is informative about the label).
- **Want** low `I(X; T)` (representation has discarded irrelevant input information — *compression*).
- These two goals trade off → the **Information Bottleneck** (Tishby, 2015).

### Why high `I(T; Y)` is sensible
Since the prediction depends on `X` only through `T`, we can show
```
E[-log q(Y|X)] = E[-log q(Y|T)] ≥ H(Y) − I(Y; T)
```
So `I(Y; T)` lower-bounds how good the loss can possibly get.

### Compression `I(X; T)`
- Formally `I(X; T) = ∞` for continuous distributions when `T` is deterministic in `X`.
- In practice: bin or add noise to estimate it. Hard to interpret.

### Shwartz-Ziv & Tishby (2017) observation
Training proceeds in two phases:
1. **Fitting**: both `I(T; Y)` and `I(X; T)` increase.
2. **Compression**: `I(X; T)` shrinks while `I(T; Y)` stays high.

**Debate**: later work argues this is an artifact of `tanh` activations and binning, not a universal property.

### XOR example
Classify `{(1,0), (-1,0)}` as 0 and `{(0,1), (0,-1)}` as 1. Two networks both achieve `I(T; Y) = I(X; Y) = 1` bit, but different `I(X; T)` — same predictive power, different compression.

## Key Takeaways
- Entropy = expected surprise; cross-entropy = surprise of `Q` on samples from `P`.
- KL divergence = extra cost of using `Q` instead of `P`. Asymmetric, not a metric.
- Mutual information measures dependence; zero iff independent.
- **Cross-entropy loss is KL divergence to the true conditional**, up to a model-independent constant.
- The Information Bottleneck framework views training as balancing prediction (`I(T; Y)`) and compression (`I(X; T)`).
- `I(T; Y)` lower-bounds the achievable loss — a fundamental limit on the network's quality.
