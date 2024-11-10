#lang racket

(define bar '(1 2 3 4 5 6))

(define (transform-if test trans seq)
  (cond ((null? seq) '())
        ((test (car seq))
         (cons (trans (car seq))
               (transform-if test trans (cdr seq))))
        (else (cons (car seq) (transform-if test trans (cdr seq))))))

(define (transform-if! test trans seq)
  (cond ((null? seq) '())
        ((test (car seq))
         (set-car! seq (trans (car seq)))
         (set-cdr! seq (transform-if! test trans (cdr seq))))
        (else (set-cdr! seq (transform-if! test trans (cdr seq)))))
  seq)



(define foo '(1 2 3 4 5 6))

(transform-if! odd? (lambda (x) (+ x 1)) foo)


(transform-if odd? (lambda (x) (+ x 1)) bar)

;; En mer effektiv en (destruktiv)

(define (transform-if! test trans seq)
    (define (t-iter sub)
        (cond ((null? sub) seq)
              ((test (car sub))
               (set-car! sub (trans (car sub)))
               (t-iter (cdr sub)))
              (else (t-iter (cdr sub)))))
    (t-iter seq))