#lang racket

(define (compose f g)
  (lambda (x) (f (apply g x))))

(define (reduce f items)
  (if (null? (cdr items))
      (car items)
      (f (car items) (reduce f (cdr items)))))

(define (comp f . procs)
  (reduce compose procs))

(define (square x)
    (* x x))

(define (inc x)
    (+ x 1))

(define (sum items)
  (if (null? (cdr items))
      (car items)
      (+ (car items) (sum (cdr items)))))

(define (filter pred items)
  (cond ((null? items) '())
        ((pred (car items)) (cons (car items) (filter pred (cdr items))))
        (else (filter pred (cdr items)))))

(define (range length)
  (define (range-iter num count)
    (if (zero? count)
        '()
        (cons num (range-iter (+ num 1) (- count 1)))))
  (range-iter 0 length))
