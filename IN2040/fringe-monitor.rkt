#lang racket

(define (monitor procedure)
  (let ((count 0))
    (lambda arguments
      (let ((message (and (not (null? arguments))
                          (car arguments))))
        (cond ((eq? message 'zero) (set! count 0))
              ((eq? message 'count) count)
              ((eq? message 'reset) procedure)
              (else (set! count (+ count 1))
                    (apply procedure arguments)))))))

(define (fringe tree)
  (cond ((null? tree) '())
         ((pair? (car tree))
          (append (fringe (car tree))
                  (fringe (cdr tree))))
         (else (cons (car tree)
                     (fringe (cdr tree))))))

(define orginal fringe)

(set! fringe (monitor fringe))

(fringe '((1 2) 3))

(fringe 'count)

(fringe '((1 2) 3))

(fringe 'count)

(fringe 'zero)

(fringe '((1 2) 3))

(fringe 'count)

(set! fringe (fringe 'reset))

(eq? fringe orginal)