#lang racket

(define (sum . args)
  (if (null? args)
      0
      (+ (car args)
         (apply sum (cdr args)))))

; (define (sum . args)
;  (apply + args))

(sum 1 2 3)