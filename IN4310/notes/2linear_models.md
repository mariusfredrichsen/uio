# Linear Models
## Basic components of machine learning problems
- the enviroment changes and therefor the data changes
- data is king
- learning of meaningful patterns in data (generilization)
- need a performance meassure to teach the model what is good and what is bad
- to make more green AI we need to have a smaller model which does less computing keeps the effeciency

- foundation models, trained at larger scale data and focuses more on reasoning
- SIMA, DeepMind, gaming industry AI

- ALPHA GOAL game Reinforcment learning

- I/O
- Prediction model
- Loss
- Optimizer

## Loss functions
- measure discrepency of model prediction vs ground truth
- use training data and a loss function to optimize weights **w**
- compare the prediction and the ground truth

### Quadratic loss (Euclidean)
- (prediction - ground truth)²

### 0-1 loss
- sigma, binary classification loss function
- counts mispredrictions
- not differentiable

### Hinge loss
- upper bound on 0-1 loss function
- max(0, 1 - (ground truth * prediction))

## Empirical risk minimization
- quadratic reularization term
- added on weights, + lambda||weight||²_2
- 1 / ||**w**||² is the margin to be maximized

## First-order optimization method
- defining an algorithm for updating model parameters using labeled data
- SAM - Sharp Average ??
- Muon - ??
- Gradient descent vs Stochastic Gradient Descent
    - Prefect vs noisy
    - more computing vs little computing


