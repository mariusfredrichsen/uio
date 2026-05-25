# Vision Transformers

## Attention Mechanism
- later states in the sequence of a RNN need to encode all previous states which takes long
- a naive way is to use all states as a input
    - more paramters
    - fixed length

- Neural Machine Translation (NMT)
    - use all the hidden states of the input sentence but focus more on the relevant ones
    - uses a encoder-decoder architecture

## Self-attention
- calculated sequentially with the values Q K and V.
- when multiplying Q_1 with K_1 and K_2 then make sure that Q_1 * K_2 is set to -infinity to avoid having the earlier arguments read into the future
- same with the softmax results with V

## Multi-head self-attention
- split the input parameters
- do the self-attention operation on them
- concatenate them back together as they were before they got split
- do linear projection

## Transformer encoder block
- N encoder blocks
- before the arguments reach the first block, we add positional encoding
- each block
    - multi-head self-attention
    - skip connection
    - layer normalization over each vector indubidually
    - position-wide FFN, 2-layer feed forward network over each vector individually but with shared weights
    - skip connection
    - layer normalization

## Transformer decoder block
- N blocks
- each block
    - positional encoding
    - masked multihead slef-attention (dont see the future)
    - layer normialization
    - multihead attention, k, v, q where q is from the normalization and k and v is from the encoded data
    - normalization
    - position wide FFN
    - normalization

- pre-norm transformer
    - do normalization before the multi-head attantion instead of after addition operation


## Vision transformer (ViT)
- Split the image into patches
- each patch is linear projected to D-dimensional vector
- add learned positional embedding
- transformer encoder (extra input, classification token)
- output vectors (extra output, predict class scores using a linear layer)
- Multi layred perceptron
- Classification results

## Swin transformer
- Input
- Patch partition
- stage 1
    - linear embedding
    - 2x swin transofmrer block
    - (split to 4x4 patches and project to C dimensions), C * H / 4 * W / 4
- stage 2
    - patch merging
        - concatinate groupd of 2x2 features
        - linear projection from 4C to 2C challen (1x1 conv)
    - 2x swin transformer block
    - (merge 2x2 neighbourhoods), 2C * H / 8 * W / 8
- stage 3
    - patch merging 2x2
    - 6x swin transformer block
- stage 4
    - patch merging
    - 2x swin transformer block
- too many parameters! use window attention, but loses global context
- shifting windows, first block is 2x2 patches, next block is 3x3 with different dimentions

## (Transformer Trends)