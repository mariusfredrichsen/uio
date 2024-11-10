#lang racket

;; Oppgave 1
;; a)

; Et prosedyre objekt i SICP er en variabel som holder på instruksjoner som skal utføres når det blir kalt på


;; b)

; undefined (procedure)


;; c)

; (1 2 3 4)



;; Oppgave 2
;; a)

(define (p-list? seq)
  (cond ((null? seq) #t)
        ((and (pair? seq) (pair? (cdr seq))) (p-list? (cdr seq)))
        (else #f)))


;; b)
(define (count-cons-cells seq)
  (if (not (pair? seq))
      0
      (+ 1 (count-cons-cells (cdr seq)) (count-cons-cells (car seq)))))


;; c)
(define ls '(((1)) (2 ((1)))))

;; d)
#t

;; e)
; jeg kan bruke 2 pekere som peker på første og andre
; element. Deretter endrer jeg første pekeren til å
; hoppe en fram mens den andre hopper to fram.
; Dersom den første og andre kolliderer og peker
; på samme element så er det en sykel.
; Ellers avslutter den når en av pekerne når en
; tom liste.

;; Oppgave 3
;; a)
(define (splitlist pred seq)
  (define (iter m l1 l2)
    (cond ((null? m)
           (cons (reverse l1) (reverse l2)))
          ((pred (car m))
           (iter (cdr m) (cons (car m) l1) l2))
          (else (iter (cdr m) l1 (cons (car m) l2)))))
  (iter seq '() '()))

(splitlist odd? '(1 2 3 4 5 6 7 8))