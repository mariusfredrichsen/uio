# Lecture 3 — Introduction to Neural Networks

## Big Picture
- ML pipeline: dataset → model → loss → optimizer (ERM).
- Deep learning extras: massive datasets, complex architectures (inductive bias), non-convex ERM trained with **SGD on multi-GPU systems**.
- Key challenges that follow: scaling (data parallelism, model parallelism), gradient propagation, generalization.

## Classification by Logistic Regression
- For binary classification with `y ∈ {-1,+1}` (or `{0,1}`), a raw linear score `h_w(x) = x^T w` is unbounded — `sgn(h_w(x))` throws away **confidence**.
- Squash the score into `[0,1]` with the **logistic sigmoid**:
```
s(u) = 1 / (1 + exp(-u))
```
- Properties: `s(0) = 0.5`, `lim_{u→+∞} s(u) = 1`, `lim_{u→-∞} s(u) = 0`. Steeper for larger scaling factor `c` in `s(cu)`.
- **Logistic regression**: `Pr(y=1|x) ≈ s(x^T w)`, `Pr(y=-1|x) = 1 − s(x^T w)`.

### Cross-entropy via Maximum Likelihood
For i.i.d. samples `(x_i, y_i)` with `y ∈ {0,1}`, the model probability of label `y_i` is
```
p̂_w(y_i|x_i) = s(h_w(x_i))^{y_i} (1 − s(h_w(x_i)))^{1 − y_i}
```
Maximizing the joint likelihood is equivalent (after −log) to minimizing
```
ℓ_i(w) = − y_i log(s(h_w(x_i))) − (1 − y_i) log(1 − s(h_w(x_i)))
```
This is the **(binary) cross-entropy loss** — a.k.a. log-loss.

## Artificial Neuron
- A neuron computes a weighted sum `z_j = Σ_i x_i w_{ij} + b_j` and applies a **non-linear activation** `g`:
  - Activation `a_j = g(z_j)`.
- Without non-linearity, stacking layers collapses to one linear map.

### Activation Functions
- Should introduce non-linearity, be (mostly) differentiable, and suppress weak signals.
- **Sigmoid** `s(x) = 1/(1+e^{-x})` — historically popular but **saturates**: gradients vanish for large |x|, so deep nets can't learn. Now rare.
- **ReLU** `g(x) = max(0, x)` — gradient is 0 or 1, no saturation on the positive side.
  - Non-differentiable at 0; use sub-gradient = 1.
  - **Dead ReLU**: a neuron stuck at 0 never recovers.
  - Variants: **Leaky ReLU** `max(0.01x, x)`, **GELU**, **ELU**.
- ReLU + variants dominate modern CNNs.

## Neural Network = Directed Graph of Neurons
- Two main types: **feedforward** (image classification etc.) and **recurrent** (sequence data).
- A **dense / fully-connected feedforward** network: every neuron in layer `l` receives inputs from every neuron in layer `l − 1`.

### Notation
- `L` layers (input layer often not counted). `n^{[l]}` = neurons in layer `l`.
- `a^{[0]} = x` (input), `a^{[L]} = ŷ` (prediction).
- Weights `W^{[l]}`, biases `b^{[l]}`.

### Forward Pass
For each layer `l = 1, …, L`:
```
z^{[l]} = W^{[l]} a^{[l-1]} + b^{[l]}
a^{[l]} = g(z^{[l]})         (element-wise)
```
- The matrix-vector multiplication `W^{[l]} a^{[l-1]}` is the workhorse op (BLAS / cuBLAS / TensorCores).

## Output Layer & Multi-class Classification
- A generic activation does not yield probabilities.
- **Binary** output: sigmoid.
- **Multi-class** (C classes): **softmax** over logits `z^{[L]}`:
```
ŷ_k = softmax(z^{[L]})_k = exp(z^{[L]}_k) / Σ_j exp(z^{[L]}_j)
```
- Outputs sum to 1 and lie in `[0,1]`.

### Cross-entropy for Multi-class
For one-hot label `y_i = e_k`:
```
ℓ(ŷ_i, y_i) = − Σ_k (y_i)_k log(ŷ_i)_k = − log(ŷ_i)_k
```
- Penalizes the negative log probability of the **ground-truth class** — pushes the right class probability toward 1.

## ERM with Cross-entropy
```
θ* = arg min_θ (1/N) Σ_i ℓ(ŷ_i, y_i | θ)
```
where `θ = {W^{[l]}, b^{[l]} : l = 1,…,L}` — many more parameters than logistic regression.
Optimization is non-convex; later lectures cover **backpropagation** for computing gradients efficiently.

## Key Takeaways
- Logistic regression = a single-output neural net with **0 hidden layers** + sigmoid.
- Deep networks stack many layers of (linear → non-linear) blocks.
- The forward pass is just repeated `g(W a + b)` operations.
- Cross-entropy is the standard loss for classification because it falls out of MLE on a logistic / softmax model.
