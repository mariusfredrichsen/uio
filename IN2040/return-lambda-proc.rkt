#lang racket

(define (replace-proc x y)
  (lambda (z)
    (if (= z x)
        y
        z)))

(map (replace-proc 42 'asd) '(1 42 2 42 3 42 4 42 5))