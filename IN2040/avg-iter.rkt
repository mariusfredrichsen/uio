#lang racket

(define (avg first . rest)
  (define (avg-iter nums sum counter)
    (if (null? nums)
        (/ sum counter)
        (avg-iter (cdr nums) (+ sum (car nums)) (+ counter 1))))
  (avg-iter (cons first rest) 0 0))

(avg 1 2)