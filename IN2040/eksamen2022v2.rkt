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
(define (stream-diff n s)
  (if (zero? n)
      s
      (stream-diff (- n 1) (stream-diff-1 s))))

; Oppgave 6c
(define (stream-deriv n proc)
  (define (deriv-help counter)
    (cons-stream (proc counter) (deriv-help (+ counter 1))))
  (stream-diff n (deriv-help 0)))

; Oppgave 6d
(define (stream-of-list items)
  (define (stream-help rest)
    (if (null? rest)
        (stream-help items)
        (cons-stream (car rest) (stream-help (cdr rest)))))
  (if (null? items)
      the-empty-stream
      (stream-help items)))



; Oppgave 7
(define (make-monitor proc)
  (let ((count 0))
    (define (dispatch message)
      (cond ((eq? message 'how-often) count)
            ((eq? message 'reset!) (set! count 0))
            ((begin (set! count (+ count 1))
                    (proc message)))))
    dispatch))
; måtte teste



; Oppgave 8
(define (make-account balance password)
  (let ((bal balance)
        (pass password)
        (tries 0))

    (define (reset)
      (set! tries 0))

    (define (deposit amount)
      (set! bal (+ bal amount))
      (reset)
      bal)

    (define (withdraw amount)
      (if (> amount bal)
          "error: insufficient"
          (begin (set! bal (- bal amount)) (reset) bal)))

    (define (dispatch message amount p)
      (cond ((> tries 2) "error: account blocked")
            ((not (eq? pass p))
             (set! tries (+ tries 1))
             "error: wrong password")
            ((eq? message 'deposit) (deposit amount))
            ((eq? message 'withdraw) (withdraw amount))
            ("Invalid command")))
  dispatch))

(define (deposit account amount password)
  (account 'deposit amount password))

(define (withdraw account amount password)
  (account 'withdraw amount password))