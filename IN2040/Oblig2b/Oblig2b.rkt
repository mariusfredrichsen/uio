#lang racket

; Oppgave 1a

(define make-counter
    (lambda (n)
        (let ((counter 0))
            (lambda ()
                (set! counter (+ counter n)) counter))))

(define count 42)
(define c1 (make-counter))
(define c2 (make-counter))

(c1 34) ; > 1
(c1) ; > 2
(c1) ; > 3
count  ; > 42
(c2) ; > 1

; Oppgave 1b
; tegning

; Oppgave 2a

(define make-stack
  (lambda (list)
    (let ((list (cons list '())))
      (lambda (message . args)
        (cond ((eq? message 'stack) (car list))
            ((eq? message 'push!) (stack-insert! args list))
            ((eq? message 'pop!) (pop list))
            (else "Not a command"))))))

(define (stack-empty? stack)
  (null? (car stack)))

(define (pop stack)
  (if (stack-empty? stack)
      "Error: empty stack."
      (let ((element (caar stack)))
        (set-car! stack (cdar stack))
        element)))

(define (stack-insert! object queue)
  (if (not (null? object))
      (let ((new (cons (car object) '())))
        (set-cdr! new (car queue))
        (if (stack-empty? queue)
            (set-car! queue new)
            (set-car! queue new))
        (stack-insert! (cdr object) queue))))


(define s1 (make-stack (list 'foo 'bar)))
(define s2 (make-stack '()))
(s1 'pop!)
(s1 'stack)
(s2 'pop!) ;; popper en tom stack
(s2 'push! 1 2 3 4)
(s2 'stack)
(s1 'push! 'bah)
(s1 'push! 'zap 'zip 'baz)
(s1 'stack)



; Oppgave 2b

(define (pop! s)
  (s 'pop!))

(define (stack s)
  (s 'stack))

(define (push! s . args)
  (apply s 'push! args))

(pop! s1)
(stack s1)
(push! s1 'foo 'faa)
(stack s1)



; Oppgave 3 a

; tegning



; Oppgave 3 b

; tegning



; Oppgave 3 c

(define bar (list 'a 'b 'c 'd 'e))

(set-cdr! (cdddr bar) (cdr bar))

(list-ref bar 0)
(list-ref bar 1)
(list-ref bar 2)
(list-ref bar 3)
(list-ref bar 4)
(list-ref bar 5)
bar

(define bah (list 'bring ' a 'towel))
(set-car! bah (cdr bah))
(set-car! (car bah) 42)
bah

(define (cycle? list)
  (define (cycle-iter slow fast)
    (cond ((null? fast) #f)
          ((null? (cdr fast)) #f)
          ((eq? slow fast) #t)
          (else (cycle-iter (cdr slow) (cddr fast)))))
  (and (not (null? list)) (cycle-iter list (cdr list))))

(cycle? '(hey ho))
(cycle? '(la la la))
(cycle? bah)
(cycle? bar)
