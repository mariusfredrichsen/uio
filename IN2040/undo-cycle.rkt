#lang racket

(define (undo-cycle-imp items)
  (define (undo-iter rest)
    (if (eq? items (cdr rest))
        (set-cdr! rest '())
        (undo-iter (cdr rest))))
  (undo-iter items)
  items)

(define my-list '(1 2 3))
(set-cdr! (cddr my-list) my-list)

my-list

(undo-cycle-imp my-list)