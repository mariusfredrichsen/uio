#lang racket

(define (make-queue)
    (cons '() '()))

(define (queue-empty? queue)
    (null? (car queue)))

(define (queue-insert! object queue)
    (let ((new (cons object '())))
        (if (queue-empty? queue)
            (set-car! queue new)
            (set-cdr! (cdr queue) new))
        (set-cdr! queue new)))


(queue-insert! 'd q)

(define (queue-delete! queue)
    (if (queue-empty? queue)
        "Error: empty queue."
        (let ((element (caar queue)))
            (set-car! queue (cdar queue))
            element)))

(queue-delete! q)