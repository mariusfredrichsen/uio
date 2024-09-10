#lang racket

(define (copy-list items)
  (define (copy-list-iter in out)
    (if (null? in)
        out
        (copy-list-iter (cdr in) (cons (car in) out))))
  (copy-list-iter items '()))

(copy-list '(1 2 3 4))