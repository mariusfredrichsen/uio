#lang racket

(define (undo-cycle-fun items)
  (define (undo-iter rest)
    (if (eq? items (cdr rest))
        (list (car rest))
        (cons (car rest) (undo-iter (cdr rest)))))
  (undo-iter items))

(define my-list '(1 2 3))
(set-cdr! (cddr my-list) my-list)

my-list

(undo-cycle-fun my-list)

my-list