


(define (slice i j items)
  (define (slice-help c rest)
    (cond ((null? rest) '())
          ((> c j) '())
          ((and (> c (- i 1)) (< c j))
           (cons (car rest) (slice-help (+ c 1) (cdr rest))))
          (else (slice-help (+ c 1) (cdr rest)))))
  (slice-help 0 items))




(define (insert i elem items)
  (define (insert-help c rest)
    (cond ((eq? c i) (cons elem rest))
          ((null? rest) (list elem))
          (else (cons (car rest)
                      (insert-help (+ c 1)
                                   (cdr rest))))))
  (insert-help 0 items))

(define (insert! i elem items)
  (define (insert!-help c rest)
    (cond ((eq? (+ c 1) i)
           (set-cdr! rest (cons elem (cdr rest))))
          ((null? (cdr rest))
           (set-cdr! rest (list elem)))
          ((insert!-help (+ c 1) (cdr rest)))))
  (if (< i 1)
      (set! items (cons elem items))
      (insert!-help 0 items))
  items)


; Oppgave 2.1

; For #1 så vil prosedyren gjøre rekusive kall der hvor hvert kall venter på at neste kall skal
; bli ferdig evaluert. Dette krever mye minne bruk ettersom kallene må bli lagret et sted.
;
; kall-eksempel: (cons (x 1) (cons (x 2)) (cons (to-all x '(3))))

; For #2 så vil prosedyren gjøre rekursive kall på en hale-rekursiv måte. Det vil si at for hvert kall
; så er alt ferdig evaluert og man slipper å vente på resultatet fra de neste kallene slik at resultatene
; kan bli sendt bakover i call-stacken.

; kall-eksempel (aux (cons (x 1) '() '(2 3))))
;               (aux (cons (x 2) '(1x) '(3))))
;               (aux (cons (x 3) '(2x 1x) '())))
;               (reversed '(3x 2x 1x))

; For #3 gjør man

; kall-eksempel (aux (cons (x 1) '() '(2 3))))
;               (aux (cons (x 2) '(1x) '(3))))
;               (aux (cons (x 3) '(2x 1x) '())))
;               (reversed '(3x 2x 1x))


; Oppgave 3.1
; Eager evaluation handler om at man skal evaluere alle utrykkene først før man bruker resultatene fra
; utrykkene videre i videre prosedyre kall.
;
; if-utrykket bruker ikke dette ettersom den ikke evaluerer begge utrykkene i if-utrykket er #f
; eller #t. Den kun evaluerer det når det først slår av enten #t eller #f for hver situasjon.
; For eksempel hvis et if-utrykk slår av #t så vil den kun evaluere første utrykk etter if-utrykket
; og den vil ikke evaluere det andre utrykket i if-utrykket

; Oppgave 3.2
; Motivasjonen bak memoisering er er at man skal spare tid på å huske resultater av tidligere kalkulerte
; utrykk.

; Oppgave 3.3




; Oppgave 4.1 og 4.2

(define make-keeper
  (let ((shared '()))
    (define (dispatch)
      (let ((private '()))
        (lambda (message elem)
          (cond ((eq? message 'shared)
                 (set! shared (cons elem shared))
                 shared)
                ((eq? message 'private)
                 (set! private (cons elem private))
                 private)))))
    dispatch))

(define foo (make-keeper))
(define bar (make-keeper))

(foo 'shared 1)
(foo 'shared 2)
(bar 'shared 3)
(bar 'private 'a)
(bar 'private 'b)
(foo 'private 42)
(foo 'private 43)
(foo 'shared 3)

(define baz (make-keeper))

(baz 'shared 4)
(baz 'private "z")

(define (keep-shared elem keeper)
  (keeper 'shared elem))

(define (keep-private elem keeper)
  (keeper 'private elem))

(keep-shared 100 foo)
(keep-private 200 bar)



; Oppgave 6.1 og 6.2

(define (iterate f x)
  (cons-stream x (iterate f (f x))))

(define (cycle items)
  (define (cycle-help rest)
    (if (null? rest)
        (cycle-help items)
        (cons-stream (car rest)
                     (cycle-help (cdr rest)))))
  (cycle-help items))


; Oppgave 7.1
