#lang racket

(define (repeat2 proc count)
  (define (repeat2-help acc new-count)
    (if (zero? new-count)
        acc
        (repeat2-help (proc acc) (- new-count 1))))
  (lambda (x) (repeat2-help x count)))
  

(repeat2 (lambda (x) (* x 2)) 3)

((repeat2 (lambda (x) (* x x)) 3) 2)
