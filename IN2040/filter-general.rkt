#lang racket

(define (filter pred items)
  (cond ((null? items) '())
        ((pred (car items)) (cons (car items) (filter pred (cdr items))))
        (else (filter pred (cdr items)))))

(filter (lambda (x) (and (> x 2) (< x 8))) '(1 2 3 4 5 6 7 8 9 10))