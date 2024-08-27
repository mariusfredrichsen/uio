#lang racket

(define (factorial x)
  (if (equal? x 1)
      x
      (* x (factorial (- x 1)))))
(write (factorial 5))