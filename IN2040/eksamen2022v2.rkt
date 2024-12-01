#lang racket


; Oppgave 1a
; Memoization handler om å lagre resultater fra tidligere kalkulasjoner.
; Istedenfor å skulle gjøre kalkulasjonene på nytt for hver gang
; det trenges, så skal det holde med å gjøre det en gang for å så
; hente resultatet lett seinere.



; Oppgave 2a
; (1 . 7)

; Oppgave 2b
; (1 2 3)

; Oppgave 2c
; (17 2 3)



; Oppgave 3a
(define (reverse-all items)
  (reverse (map (lambda (x)
                  (if (pair? x)
                      (reverse-all x)
                      x)) items)))

; Oppgave 3b
; først append
; så list



; Oppgave 4
; Klarte ikke



; Oppgave 5a
(define (height tree)
  (define (max a b)
    (if (> a b) a b))
  (cond ((leaf? tree) 1)
        ((null? tree) 0)
        ((+ 1 (max (height (left-branch tree))
                 (height (right-branch tree)))))))

; Oppgave 5b
(define (longest-path tree)
  (define (max a b)
    (if (> a b) a b))
  
  (let* ((left (left-branch tree))
         (right (right-branch tree))
         (h-left (height left))
         (h-right (height right)))
    
    (cond ((leaf? tree) (entry tree))
          ((null? tree) '())
          ((cons (entry tree)(if (> h-left h-right)
                                 (longest-path left)
                                 (longest-path right)))))))



; Oppgave 6a
(define (stream-diff-1 s)
  (cons-stream (- (stream-car (stream-cdr s))
                  (stream-car))
               (stream-diff-1 (stream-cdr s))))

; Oppgave 6b
