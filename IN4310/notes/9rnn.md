# Recurrent Neural Networks (RNN)
- speech recognition
- music generation
- text analysis and translation

## Vanilla RNN
- for each timeseries datapoint X_t compute hidden state H_t and output Y_t
- h_t = tanh(W_ih * x_t + W_hh *h_t−1 + b_h)
- y_t = g(Whyht + by), where g is an activation function

- Types of network connections
    - One-to-one
    - One-to-many
    - Many-to-one
    - Many-to-many

## Training RNNs: Backpropgation through time (BPTT)
- unroll the time dimensions in the RNN and do normal backpropegation on the unrolled RNN
- calculate loss at each timestamp
- takes to much memory
    - Can split up but loses the conext from the earlier batches

## Challenges in training: Exploding or vanishing gradients
- weights < 1 multiplied with itself gets infinitely smaller and > 1 bigger
- gradient clipping
    - by value, g > threshold gives g = threshold
    - by norm, l²norm(gg) > threshold for each g in vector gg apply: 
        g = (g*threshold) / l²norm(gg)
- orthogonal initialisation, eigenvalues start at 1 instead of 0

## Long Short Term Memory (LSTM) Networks
- maintin a cell state C which gives and output and throughout the layers edit this cell state
- input gates, generates a new cell candidate and calculates how much of this candidate is being added to the old cell state
    - Ĉ = tanh (W_c [h_t−1 * x_t] + b_c), where Ĉ is the new candidate
    - i_t = sigmoid(W_i [h_t−1 * x_t] + b_i), where i_t is the fraction of the new state that is going to be added
- output gates, calculate a candidate output then decide how much of this is going to be outputted
    - ĥ_t = tanh(C_t), candidate output
    - o_t = sigmoid(W_o [h_t−1 * x_t] + b_o), fraction of the candidate output
    - h_t = o_t ⊙ ĥ_t = o_t ⊙ tanh(C_t), output
- forget gates, tries to remove useless information
    - f_t = sigmoid(W_f [h_t−1 * x_t] + b_f )
    - add a bias grater than 1 to not disturb the gradient flow
- new cell state is caluclated like this:
    C_t = f_t ⊙ C_t−1 + i_t ⊙ Ĉ

## GRU: Gated Recurrent Unit
- a simpler version of LSTM
- combines input and forget gates, and state C and hidden state h
- update gate, z_t = sigmoid(W_z [h_t−1 * x_t] + b_z)
- reset gate, r_t = sigmoid(W_r [h_t−1 * x_t] + b_r)
- proposed activation, ĥ_t = tanh(W_h [(r_t ⊙ h_t−1) * x_t] + b_h)
- final output activation, h_t = z_t ⊙ h_t−1 + (1 − z_t) ⊙ ĥ_t



### Multi-layer Recurrent Neural Networks
- normally 2-3 layers deep is enough but more complex relations is learned in the time dimension

### Bidirectional RNNs
- traverses the input in both the past and the future