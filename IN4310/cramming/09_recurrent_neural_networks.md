# Lecture 9 — Recurrent Neural Networks (RNNs)

## Why RNNs?
Feedforward and CNN architectures expect **fixed-size inputs**. Many tasks have variable-length sequences:
- speech recognition
- music generation
- sentiment analysis
- machine translation, text generation

RNNs process a sequence `X = (x_1, …, x_T)` step-by-step, maintaining an internal **hidden state** that summarizes everything seen so far.

## Vanilla RNN
At each timestep:
```
h_t = tanh(W_hh h_{t-1} + W_ih x_t + b_h)
y_t = g(W_hy h_t + b_y)
```
- `h_t` is the hidden state vector; it's the network's "memory".
- The same weights `W_ih, W_hh, W_hy` are used at every timestep — **parameter sharing across time** (analogous to CNN parameter sharing across space).
- `h_0` is typically zeros or a learnable parameter.
- Equivalent compact form: concatenate `[h_{t-1}, x_t]` and apply a single weight matrix W.

## Input/Output Patterns
- **One-to-one**: standard feedforward (image classification).
- **One-to-many**: image → sentence (image captioning).
- **Many-to-one**: sequence → label (sentiment analysis).
- **Many-to-many (aligned)**: tag every input (POS tagging).
- **Many-to-many (encoder-decoder)**: sequence → sequence with possibly different lengths (machine translation).

## Training: Backpropagation Through Time (BPTT)
1. Unroll the RNN along the time axis → it looks like a (very deep) feedforward net with shared weights.
2. Apply ordinary backprop on the unrolled graph.
3. Sum gradient contributions from every timestep where a weight matrix appears.

### Truncated BPTT
- Storing every hidden state over a long sequence (e.g., 100M characters) is infeasible.
- **Truncate**: unroll for k steps, run BPTT, update; carry the final hidden state forward and start a new unroll.
- Saves memory, faster updates. Tradeoff: cannot capture dependencies longer than the truncation window.

## The Vanishing / Exploding Gradient Problem
Gradient flow through `W_hh` over n steps involves the product:
```
∏_{i=k+1..t} ∂h_i/∂h_{i-1} = ∏ diag(σ'(h_{i-1})) W_hh^T
```
Roughly behaves like `(σ' · W_hh)^n`. With eigendecomposition `W = U Σ U^{-1}`, `W^n = U Σ^n U^{-1}`.

- If the **spectral radius** (largest |eigenvalue|) of W > 1 ⇒ `Σ^n` blows up → **exploding gradients**.
- If the spectral radius < 1 ⇒ `Σ^n → 0` → **vanishing gradients**.
- For sigmoid/tanh, σ' is bounded ≤ 0.25 / 1 — they push toward vanishing.
- Even a single eigenvalue with magnitude > 1 can blow everything up.

### Mitigations
- **Gradient clipping** for explosion:
  - **By value**: if any component > τ, set to τ.
  - **By norm**: if `||g||_2 > τ`, rescale `g ← g · τ / ||g||_2`.
- **Orthogonal initialization**: eigenvalues all = 1 initially; gradients neither blow nor decay. (BPTT updates may break orthogonality over time.)
- **Gated architectures**: LSTM, GRU.

## LSTM (Long Short-Term Memory)
Solves vanishing gradients by adding a separate **cell state `C_t`** that flows through time with mostly-additive updates.

### Components (gates) at each timestep
All gates take `[h_{t-1}, x_t]` as input.
- **Forget gate** `f_t = σ(W_f [h_{t-1}, x_t] + b_f)` — what fraction of the old cell state to keep.
- **Input gate** `i_t = σ(W_i [h_{t-1}, x_t] + b_i)` — how much of the new candidate to add.
- **Candidate cell state** `Ĉ = tanh(W_c [h_{t-1}, x_t] + b_c)`.
- **Output gate** `o_t = σ(W_o [h_{t-1}, x_t] + b_o)` — how much of the cell state to expose.

### Updates
```
C_t = f_t ⊙ C_{t-1} + i_t ⊙ Ĉ
h_t = o_t ⊙ tanh(C_t)
```
- `⊙` = element-wise (Hadamard) product.
- The gradient `∂C_t/∂C_{t-1} ≈ f_t` — close to identity when the forget gate is open ⇒ gradients flow far back in time.

### Practical tips
- **Forget gate bias init > 1** (e.g., bias = 1 or 2): the gate starts open, gradients flow, the network can *learn* to close it for irrelevant signals.
- The forget gate is empirically the most important LSTM component.

## GRU (Gated Recurrent Unit)
Simpler / cheaper LSTM variant. Combines input + forget gates into a single **update gate** and merges cell + hidden state.
```
z_t = σ(W_z [h_{t-1}, x_t] + b_z)         (update gate)
r_t = σ(W_r [h_{t-1}, x_t] + b_r)         (reset gate)
ĥ_t = tanh(W_h [r_t ⊙ h_{t-1}, x_t] + b_h)
h_t = z_t ⊙ h_{t-1} + (1 − z_t) ⊙ ĥ_t
```
- Fewer parameters, similar performance to LSTM in many tasks.

## Multilayer & Bidirectional RNNs
- **Stacked / multilayer**: stack 2–3 RNN layers; higher layers receive lower layer's hidden states. Adds depth in the feature dimension.
- **Bidirectional**: run two RNNs — one forward, one backward — and concatenate hidden states. Exposes future context to each timestep. Useful for speech/handwriting/POS tagging.
```
h_t = [⃗h_t ; ⃐h_t]
```

## Key Takeaways
- RNNs share parameters across time and use a hidden state to handle variable-length sequences.
- Train via BPTT; truncate for long sequences.
- Vanilla RNNs suffer from vanishing/exploding gradients — stabilize with gradient clipping, orthogonal init, or **gated cells**.
- **LSTM** uses a cell state with gates; **GRU** is a leaner alternative.
- For modern sequence modeling, transformers (Lecture 10) have largely replaced RNNs, but LSTMs/GRUs are still useful when long sequences and small models are needed.
