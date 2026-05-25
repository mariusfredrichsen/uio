# An Introduction to Deep Learning Theory

## Entropy
- suprise of some outcome x is -log(p(X=x))
- the expected surprise, given the distribution of X
    H(X) = -sum(p(X=x)log(p(X=x))) = E(-log(p(X)))
- low entropy is already predetermined, the weights are already leaned while high entropy is balanced weights so its not that determined
- joint entropy
- conditional entropy
- cross-entropy H(P;Q)

- KL Divergence
    - D_KL(P||Q) = sum( P(x) * log( (P(x)/Q(x)) ) )