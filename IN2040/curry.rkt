#lang racket

(define (plus a b)
  (+ a b))


(define (curry proc)
  (lambda (a)
    (lambda (b)
      (proc a b))))

(define plus-curried (curry plus))

(plus 1 2)
((plus-curried 1) 2)