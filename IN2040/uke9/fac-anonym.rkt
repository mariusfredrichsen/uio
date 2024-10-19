#lang racket

((lambda (proc n)
     (proc proc n))
  (lambda (fac n)
     (if (= n 1)
         1
         (∗ n (fac fac (− n 1)))))
  5 )