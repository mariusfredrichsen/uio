#lang racket

(define (count-leaves tree)
    (cond ((null? tree) 0)
        ((pair? tree) 
            (+ (count-leaves (car tree))
            (count-leaves (cdr tree))))
        (else 1)))

(count-leaves '((a b) (c d) ((k l (m n))e f) (g h (i j))))