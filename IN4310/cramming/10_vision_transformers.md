# Lecture 10 — Attention, Transformers, and Vision Transformers

## Why attention?
RNN encoder-decoder for translation has a **bottleneck**: the entire source sentence is squeezed into one final hidden state — the longer the input, the more is lost.
Naïvely connecting the decoder to *all* encoder hidden states adds parameters per timestep (k×) and breaks for variable-length inputs.

**Attention** (Bahdanau et al., 2014): use *all* encoder hidden states but with **input-dependent weights**, computed on the fly. Solves the bottleneck and (as a side benefit) makes the model interpretable.

## Attention Mechanism (NMT setup)
- Encoder produces hidden states `h_1, …, h_T`. These are both **keys** and **values**.
- Decoder produces a **query** `q` (its current hidden state).
- For each query, compute attention scores between `q` and every `k_i` via an **alignment function**.
- Softmax over scores → attention weights `α_i`.
- Output **context vector** `c = Σ α_i v_i` — weighted average of encoder hidden states.

### Alignment functions
- **Additive / concat** (Bahdanau): `a(q, k) = v^T tanh(W_a [q; k])`.
- **General** (Luong): `a(q, k) = q^T W_a k`.
- **Dot-product** (Luong): `a(q, k) = q^T k`.
- **Scaled dot-product** (Vaswani): `a(q, k) = q^T k / √d_k` — scaling stabilizes gradients for large d.

### Why attention works
- Solves the bottleneck — decoder can look directly at any source position.
- Improves gradient flow — direct path between decoder and any encoder timestep.
- Provides **interpretability** — attention heatmaps show what each output token focused on.

## Self-Attention
Same idea, but Q, K, V all come from the **same** input sequence X.

### Steps
1. Project each input `x_i` into three vectors:
   `Q = X W_Q`, `K = X W_K`, `V = X W_V`. (Three `nn.Linear` layers.)
2. Compute scores `S = Q K^T / √d_k`.
3. Softmax over the keys axis: `α = softmax(S)`.
4. Output `Z = α V`.

Compact form:
```
Attention(Q, K, V) = softmax(Q K^T / √d_k) V
```

### Permutation equivariance
Self-attention treats inputs as a **set** — re-order inputs ⇒ outputs re-ordered the same way. So we need **positional encoding** to inject order.

### Positional Encoding
Add a vector to each input embedding (same dim) describing its position. Variants:
- **Absolute / fixed** (sinusoidal, Vaswani et al.):
  `PE(pos, 2i) = sin(pos / 10000^{2i/d})`
  `PE(pos, 2i+1) = cos(pos / 10000^{2i/d})`
- **Relative** position encoding.
- **Learned** position embeddings (used in ViT).

### Masked Self-Attention
For autoregressive generation, prevent each position from attending to **future** positions. Set the corresponding scores to `-∞` before softmax → those weights become 0.
Used in the decoder (and in GPT-style language models).

## Multi-Head Self-Attention
- Run **H independent self-attention "heads"** in parallel; heads do **not** share parameters.
- Split each input embedding into H parts (or use H separate Q/K/V projections).
- Each head computes its own Z.
- Concatenate the H outputs and apply a final linear projection.
- Different heads learn different relations (some attend short-range, some long-range).

## Transformer Encoder Block
Stack N (e.g. 6) of these blocks. Each block:
```
x → +PositionalEncoding
   → MultiHeadSelfAttention → +residual → LayerNorm
   → Position-wise FFN (2-layer MLP, shared per position) → +residual → LayerNorm
```
- Self-attention is the **only** inter-token interaction. FFN and LayerNorm act per token.
- Highly parallelisable; high memory cost (O(N²) attention).

## Transformer Decoder Block
A decoder block has three sublayers:
1. **Masked** multi-head self-attention (over already-generated tokens).
2. **Cross-attention**: keys & values from the **encoder output**, queries from the decoder. Not self-attention.
3. Position-wise FFN.
With residual + LayerNorm around each.

### Pre-Norm vs Post-Norm
- Original transformer: LayerNorm **after** addition (post-norm).
- Modern variants put LayerNorm **inside** the residual path (pre-norm) — more stable training, especially for deep models.

## Vision Transformer (ViT) — Dosovitskiy et al., ICLR 2021
**Idea**: treat an image as a sequence of patch tokens.

Steps:
1. Cut the image into N patches (typically 16×16). Each patch is `3 × 16 × 16`.
2. **Linear projection** of each patch (flatten + Linear) to a D-dim vector.
3. Add a **learned positional embedding** to each token.
4. Prepend a special learned **[CLS] / classification token** (`0*` in the figure).
5. Feed the full sequence into a stack of standard transformer **encoder** blocks.
6. The output corresponding to [CLS] is sent to a small MLP head → class scores.

Why patches and not pixels: a 224×224 image has 50k pixels; self-attention is O(N²) so this would need ~2.5e9 entries. With 16×16 patches → 196 tokens, manageable.

ViT learns from scratch only when pre-trained on huge datasets (JFT-300M). Otherwise CNN inductive biases (locality, translation invariance) are too useful to throw away.

## ViT vs ConvNet
- **ConvNets** are **hierarchical**: spatial size shrinks, channel count grows as you go deeper.
- **ViT (vanilla)** keeps all blocks at the **same resolution and channel count** — flat, not hierarchical. Limits its use as a backbone for dense prediction (segmentation, detection).

## Swin Transformer (Liu et al., CVPR 2021)
A hierarchical ViT.
- **Patch partition**: 4×4 patches → linear embedding → C-dim tokens.
- **Stages**: at each stage, apply Swin transformer blocks; between stages **patch merge** (concatenate 2×2 neighbours, project from 4C to 2C). Resolution halves, channels double.
- Stage shapes: `C × H/4 × W/4 → 2C × H/8 × W/8 → 4C × H/16 × W/16 → 8C × H/32 × W/32`.

### Window Attention
- Naive global attention on a 56×56 grid → `56^4 ≈ 9.8M` attention entries — too expensive.
- Solution: divide the patch grid into M×M **windows**; compute self-attention only **within each window**. Linear in image size.

### Shifted Window Attention
- Window-only attention has no cross-window communication.
- Alternate normal vs **shifted** windows in successive blocks. Tokens that share a window in one block won't share one in the next, propagating information across the whole image.

## Image Captioning (CNN + RNN combined)
Classic pre-transformer pipeline:
- Run CNN (e.g., VGG) on image → final FC-4096 feature `v`.
- Feed `v` into an RNN as an extra input: `h = tanh(W_xh x + W_hh h + W_ih v)`.
- RNN samples tokens autoregressively until `<END>`.
- Quality measured by **BLEU** score (n-gram precision against reference captions).
- Failure modes: hallucinations ("a woman holding a cat" when she isn't), conflating similar shapes/objects.

## Is self-attention necessary?
A growing list of competitive replacements:
- Conv-based MetaFormer / ConvMixer.
- MLP-Mixer (only MLPs).
- FNet (Fourier transforms).
- LSTM-based replacements.

But self-attention remains the standard.

## Trends
- **Foundation models**: GPT, StarCoder, Virchow2 — large transformers pretrained on huge corpora.
- **Multi-modal** models (text + image + audio).
- **DINO / DINOv2**: self-supervised vision transformers using contrastive pretraining and augmentations; representations transfer to many downstream tasks.

## Key Takeaways
- Attention computes a weighted average of values, with weights conditioned on a query and a set of keys.
- Self-attention does the same with Q, K, V drawn from the same input sequence.
- Transformers stack multi-head self-attention + FFN with residual + LayerNorm; need positional encoding.
- ViT applies standard transformer encoders to image patches; Swin makes ViT hierarchical with windowed attention + shifted windows.
- Decoder uses masked self-attention + cross-attention to encoder outputs.
