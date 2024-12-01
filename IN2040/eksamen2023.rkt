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

;; Oppgave 4
;; a)
(define reduce-left
  (lambda (proc init items)
    (if (null? items)
        init
        (proc (reduce-left proc init (cdr items)) (car items)))))

(reduce-left + 0 '(1 2 3 4 5))

;; fasit
(define (reduce-left op acc items)
  (if (null? items)
      acc
      (reduce-left op (op acc (car items)) (cdr items))))

(reduce-left + 0 '(1 2 3 4 5))

;; b)
;; reduce-left vil gi et pair<<pair<pair<pair,tall>,tall>,tall>, tall>

;; Oppgave 5
(define (orderedtree? tree)
  (define (fringe t)
    (cond ((null? t) '())
          ((pair? t) (apply (fringe (left-branch t))
                            (fringe (right-branch t))))
          ((leaf? t) (list entry t))))
  (define (orderedlist? items)
    (if (null? (cadr items))
        (> (car items) (cadr items))
        (and (> (car items) (cadr items))
             (orderedlist? (cdr items)))))
  (orderedlist? (fringe tree)))
            

;; fasit
(define (orederedtree? t)
  (define (help t)
    (cond ((leaf? t)
           (cons (entry t)
                 (entry t)))
          (else
           (let ((mm-l (help (left-branch t)))
                 (mm-r (help (right-branch t))))
             (if (and
                  mm-l
                  mm-r
                  (< (cdr mm-l) (entry t))
                  (< (entry t) (car mm-r)))
                 (cons (car mm-l) (cdr mm-r))

                 #f)))))
  (if (help t)
      #t
      #f))
;; henter ut alle alementene fra venstre siden og høyre siden og sjekker 
;; om noden er mindre eller større enn alle nodene i tilsvarende lister

;; Oppgave 6
;; a)
(define (stream-iterate proc init)
  (cons-stream init (stream-iterate proc (proc init))))

;; b)
;; 1. Motivasjonen for å bruke halerekusive versjoner av prosedyrer er at man sparer for minne
;; ettersom vanlig rekursive prosedyrer har økt bruk av minne ettersom man må lagre
;; tidligere kall for å så senere kalkulere svaret til slutt. Med halerekursjon blir
;; det en mer iterativ prosess som ikke lagrer på tidligere kall men heller sender over
;; alt ferdig kalkulert til neste iterasjon.

;; 2. Den fungerer ikke fordi det er ingen sted den returnerer selve streamen. Det
;; eneste den gjør er å kalle på hjelpe prosedyren som bare kjøres.

;; Oppgave 7
;; a)
(define make-charity
  (lambda ()
    (let ((total 0))
      (lambda ()
        (let ((person 0))
          (lambda (message)
            (cond ((eq? message 'status)
                   (cons person total))
                  ((eq? message 'donate)
                   (lambda (amount)
                     (set! person (+ person amount))
                     (set! total (+ total amount))
                     person))
                  (else "Not a valid command"))))))))

;; 