#lang racket

(define (filter-even items)
  (cond ((null? items) '())
        ((even? (car items)) (cons (car items) (filter-even (cdr items))))
        (else (filter-even (cdr items)))))

(filter-even '(1 2 3 4 5 6 7 8 9 10))