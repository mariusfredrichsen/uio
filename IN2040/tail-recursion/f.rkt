#lang racket

(define (f n)
  (if (< n 3)
      n
      (+ (f (- n 1)) (* 2 (f (- n 2))) (* 3 (f (- n 3))))))

(f 5)


(define (f-1 n)
  (define (f-iter x1 x2 x3 count)
    (cond ((< n 3) n)
          ((= count n) (+ x3 (* 2 x2) (* 3 x1)))
          (else (f-iter x2 x3 (+ x3 (* 2 x2) (* 3 x1)) (+ count 1)))))
  (f-iter 0 1 2 3))

(f-1 5)


