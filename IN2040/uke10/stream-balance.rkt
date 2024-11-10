#lang racket

(define-syntax cons-stream
  (syntax-rules ()
    ((cons-stream head tail)
     (cons head (delay tail)))))

(define (input-stream)
  (cons-stream (read) (input-stream)))

(define (stream-map proc stream1 stream2)
  (if (or (stream-null? stream1) (stream-null? stream2))
      the-empty-stream
      (cons-stream (proc (stream-car stream1) (stream-car stream2)) (stream-map proc (stream-cdr stream1) (stream-cdr stream2)))))

(define (add-streams stream1 stream2)
  (stream-map + stream1 stream2))

(define balances
  (cons-stream 100
               (add-streams balances (input-stream))))

(define (stream-null? stream)
  (null? stream))

(define (stream-car stream)
  (car stream))

(define the-empty-stream '())

(define (stream-cdr stream)
  (force (cdr stream)))

(define (show-stream stream . n)
  (define (ss-rec stream i)
    (cond ((= i 0) (display "...\n"))
          ((stream-null? stream)  (newline))
          (else (display (stream-car stream))
                (display " ")
                (ss-rec (stream-cdr stream) (- i 1)))))
  (ss-rec stream (if (null? n) 15 (car n))))

(define (integers-starting-from n)
  (cons-stream n (integers-starting-from (+ n 1))))

(show-stream balances 5)