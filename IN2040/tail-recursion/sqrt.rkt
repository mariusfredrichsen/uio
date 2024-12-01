#lang racket

(define (abs-p x)
  (* (if (< x 0) -1 1) x))

(define (sqrt-p n l)
  (define (sqrt-iter r x)
    (if (< (abs-p (- r x)) l)
        r
        (sqrt-iter (* 0.5 (+ r (/ n r))) r)))
  (sqrt-iter (* 0.5 (+ n (/ n n))) n))

(sqrt-p 300000000000 0.00000000000000000001)