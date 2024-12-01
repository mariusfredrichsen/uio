#lang racket

;; Oppgave 1
;; a)
;; Memoization handler om å lagre resultater fra tidligere kalkulasjoner
;; slik at man slipper å gjøre dem på nytt.
;; Det gjør senere kalkulasjoner raskere ved å bare hente dem ut direkte fra en tabell
;; som lagrer på de tidligere resultatene.
;; Det kan være et problem dersom resultatet hadde vært annerledes på grunn av
;; noen sideeffekter som endrer noen variabler.



;; Oppgave 2
;; a)
(define one (list 1))
(set-cdr! one 7)
one ;; -> (1 . 7)

;; b)
(define foo '(1 2 3))
(let ((bar foo))
    (set! bar (cons 17 (cdr foo))))
foo ;; -> (1 2 3)

;; c)
(define foo '(1 2 3))
(let ((baz foo))
    (set-cdr! baz (cons 17 (cdr foo))))
foo ;; -> (1 2 3) FEIL

;; fasit
foo ;; -> (1 17 2 3)

;; Notat:
;; Virker som at set-cdr! fungerer litt annerledes på den måten at den endrer
;; det faktisk objektet som baz er knyttet til.
;; Samtidig så bruker den foo i sin tidligere tilstand før den blir endret, men
;; dette gir mening siden man kan ikke basere en endring på hva endringen er sjamener



;; Oppgave 3
;; a)
(define (reverse-all items)
  (map (lambda (item)
         (if (list? item)
             (reverse-all item)
             item)) (reverse items)))

(reverse-all (list 1 (list 2 3) (list 4 5)))
(reverse-all (list 1 (list 2 (list 3 4) 5 6) 7))

;; b)
;; cons for list varianten FEIL
;; list for car varianten FEIL

;; append så list



;; Oppgave 4
;; a)
(define (magic n)
  (define (magic-rec count acc)
      (if (zero? count)
          acc
          (lambda (x) (magic-rec (- count 1) (+ acc x)))))
  (magic-rec n 0))


(((((magic 4) 3) 1) 10) 2)

;; Klarte ikke



;; Oppgave 5
;; a)
(define (height tree)
  (define (max a b)
    (if (> a b)
        a
        b))
  (cond ((leaf? tree) 1)
        ((pair? (+ 1
                   (max (height (left-branch tree))
                        (height (right-branch tree))))))
        (else 0)))

;; Kommentar:
;; - (max x y) er innebygd og kan brukes direkte
;; - trengte bare å ha en if med leaf?, trengte ikke cond med en else 0

;; b)
(define (height tree)
  (if (leaf? tree)
      1
      (+ 1
         (max (height (left-branch tree))
              (height (right-branch tree))))))
        
(define (max-branch tree)
  (define (c-max t1 t2)
    (if (> (height t1)
           (height t2))
           t1
           t2))
  (if (leaf? tree)
      (list (entry tree))
      (cons (entry tree)
            (max-branch (c-max (left-branch tree)
                               (right-branch tree))))))

;; Kommentar:
;; Kunne ha sendt ned høyden i samme slengen (høyde (liste av noder som følger med høyden))






