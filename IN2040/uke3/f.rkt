#lang racket

(define (f g)
    (g 2))

(define (square x)
  (* x x))

(f square)

; (f f)
; => (f 2)
; => (2 2) <--- Feil