#lang racket
(define (map-own proc items)
  (if (null? items)
      '()
      (cons (proc (car items)) (map-own proc (cdr items)))))

(map-own abs '(-7 -9 -3 2 8 -1))