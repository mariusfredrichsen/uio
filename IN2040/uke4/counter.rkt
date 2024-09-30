#lang racket

(define counter
  (let ((x 0))
    (lambda ()
      (set! x (+ x 1))
      x)))

(counter)
(counter)
(counter)
(counter)
(counter)
