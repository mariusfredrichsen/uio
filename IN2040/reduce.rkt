#lang racket

(define (reduce init proc items)
  (if (null? items)
      init
      (proc (car items) (reduce init proc (cdr items)))))

(reduce 6 max '(1 9 3 4 5))