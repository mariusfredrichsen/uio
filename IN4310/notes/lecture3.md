# Introduction to Neural Network
- Empiric Risk minimization (ERM)
    - assuming classification
    - make prediction as close to the ground truth as possible
    - minimize the loss by tweaking the weights

- Main components in deep learning pipeline
    - Massive datasets
    - Inductive bias from large-scale and complex architectures
    - Nonconvex ERM via Stochastic Gradient Descent (SGD) with multi-GPU systems

    - How to scale up?
        - Dividing work to different GPUs and combining
        - How to combine?

## Logistic regression
- sigmoid function, s(x)
    - exp(u) / (1 + exp(u)) => 1 / (exp(-u) + 1)
    - exponential, e? exp(u) = e^u
    - calibration, multiply value u with a constant c
    - s(h_w(x)) = 1 / (exp(-x^Tw) + 1)
    - l_n(w) = -log(^p_w(y_n|x_n))

## Structure of dense feedforward neural network
- go deeper rather than wider
- 


## Forward pass for dense feedforward neural networks



## Cross-entropy loss
- max likelihood, arg max
- find some w's that maximises this feature
- instead minimize
- 






