#lang racket

; Oppgave 1
; f)
(define oppgave1f '(0 42 #t bar))
(car (cdr oppgave1f))

; g)
(define oppgave1g '((0 42) #t bar))
(car (cdr (car oppgave1g)))

; h)
(define oppgave1h '((0) (42 #t) (bar)))
(car (car (cdr oppgave1h)))

; i)
; cons:
(cons (cons 0 (cons 42 '())) (cons (cons #t (cons bar '())) '()))

; list:
(list (list 0 42) (list #t 2))




; Oppgave 2
; a)
(define (take n items)
    (if (or (zero? n) (null? items))
        '()
        (cons (car items) (take (- n 1) (cdr items)))))

; b)
(define (take n items)
    (define (take-iter n in out)
        (if (or (zero? n) (null? in))
            out
            (take-iter (- n 1) (cdr in) (cons (car in) out))
        )
    )
    ; vet ikke om det var nødvendig å ha det i rekkefølge så jeg bare kalte på
    ; funksjonen en ekstra gang
    (take-iter n (take-iter n items '()) '())
)

; c)
(define (take-while pred items)
    (cond ((null? items) '())
        ((pred (car items)) 
            (cons (car items) 
            (take-while pred (cdr items))))
        (else '()) ; dersom elementet er hverken så stopper vi
    )
)

; d + e)
; vet ikke helt hva de mente i oppgave d
(define (map2 proc items1 items2)
    (if (or (null? items1) (null? items2))
        '()
        (cons (proc (car items1) (car items2))
            (map2 proc (cdr items1) (cdr items2))    
        )
    )
)
(map2 + '(1 2 3 4) '(3 4 5))
(map2 (lambda (x y)  (/ (+ x y) 2)) '(1 2 3 4) '(3 4 5))