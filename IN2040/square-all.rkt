#lang racket

(define (sqr-all items)
  (if (null? items)
      '()
      (cons (expt (car items) 2) (sqr-all (cdr items)))))

(define foo '(1 2 3 4))
(sqr-all foo)