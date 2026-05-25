# Back Propagation and Optimization

## Empetric Risk Minimization (ERM)
- average of output data, theta

## Gradient
- used to minimize the ERM
- gradient is the partial derivatives of the functions

## Back propagation for neural networks
- yeah... math...

## Stochastic gradient descent and mini batching
- select random n of all training points N
- Instead of O(Nd) we get O(d), but require more iterations to make up for the skipped data
- use mini-batches to avoid variance or noisy computing
- make sure to not check the same mini-batch all the time, "true" random

## SGD with Momentum
- we can land on flat points in the landscape
- we use momentum to move away from these "fake" minimums
- this can be done with a velocity term that is multiplied with what we did in the last iteration
- you can overshoot so be careful

## Adaptive variants of SGD
- RMSProp, use a hyperparameter to scale down big values and scale up small values
- ADAM = RMSProp + Momenetum
    - Added a bias correction for the first step since the first step often has very small values