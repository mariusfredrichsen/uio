#lang racket

; Oppgave 1a
(define (p-cons x y)
  (lambda (proc) (proc x y)))

(define (p-car proc)
  (proc (lambda (x y) x)))

(define (p-cdr proc)
  (proc (lambda (x y) y)))

(p-car (p-cons 1 2))
; > 1

(p-cdr (p-cons 1 2))
; > 2

(p-car (p-cdr (p-cons 3 (p-cons 2 1))))
; > 2



; Oppgave 1b
(define foo 42)

(let ((foo 5)
      (x foo))
  (if (= x foo)
      'same
      'different))
; > different

(let ((bar foo)
      (baz 'towel))
  (let ((bar (list bar baz))
        (foo baz))
    (list foo bar)))
; > (towel (42 towel))

((lambda (foo x)
   (if (= x foo)
       'same
       'different))
   5 foo)
; > different

((lambda (bar baz)
   ((lambda (bar foo)
      (list foo bar))
    (list bar baz) baz))
 foo 'towel)
; > (towel (42 towel))



; Oppgave 1c
(define (infix-eval items)
  ((car (cdr items)) (car items) (car (cdr (cdr items)))))

(define foo (list 21 + 21))
(define baz (list 21 list 21))
(define bar (list 84 / 2))

(infix-eval foo)
; > 42
(infix-eval baz)
; > (21 21)
(infix-eval bar)
; > 42



; Oppgave 1d
(define bah '(84 / 2))
(infix-eval bah)
; > error

; Grunnen til at det kommer en feil er fordi i bah så er '(84 / 2)
; forhånds evaluert på grunn av '. Dermed vil dette også
; evaluere det som ligger inne i listen og gjøre om / om til
; tekst versjonen av / som ikke kan ta inn to argumenter (84 og 2).



;;;;
;;;; Prekode til innlevering 2a i IN2040 (H24): Prosedyrer for å jobbe med
;;;; Huffman-trær, fra SICP, Seksjon 2.3.4.
;;;;

;;; Merk at koden under gjør bruk av diverse innebygde kortformer for
;;; kjeder av car og cdr. F.eks er (cadr x) det samme som (car (cdr x)), 
;;; og (caadr x) tilsvarer (car (car (cdr x))), osv. 



;;;
;;; Abstraksjonsbarriere for trær:
;;;

(define (make-leaf symbol weight)
  (list 'leaf symbol weight))

(define (leaf? object)
  (eq? (car object) 'leaf))

(define (symbol-leaf x) (cadr x))

(define (weight-leaf x) (caddr x))

(define (make-code-tree left right)
  (list left
        right
        (append (symbols left) (symbols right))
        (+ (weight left) (weight right))))

(define (left-branch tree) (car tree))

(define (right-branch tree) (cadr tree))

(define (symbols tree)
  (if (leaf? tree)
      (list (symbol-leaf tree))
      (caddr tree)))

(define (weight tree)
  (if (leaf? tree)
      (weight-leaf tree)
      (cadddr tree)))


;;;
;;; Dekoding:
;;;

(define (decode bits tree)
  (define (decode-1 bits current-branch)
    (if (null? bits)
        '()
        (let ((next-branch
               (choose-branch (car bits) current-branch)))  ;;
          (if (leaf? next-branch)
              (cons (symbol-leaf next-branch)
                    (decode-1 (cdr bits) tree))
              (decode-1 (cdr bits) next-branch)))))
  (decode-1 bits tree))

(define (choose-branch bit branch)
  (if (= bit 0) 
      (left-branch branch)
      (right-branch branch)))


;;;
;;; Sortering av node-lister:
;;;


(define (adjoin-set x set)
  (cond ((null? set) (list x))
        ((< (weight x) (weight (car set))) (cons x set))
        (else (cons (car set)
                    (adjoin-set x (cdr set))))))

(define (make-leaf-set pairs)
  (if (null? pairs)
      '()
      (let ((pair (car pairs)))
        (adjoin-set (make-leaf (car pair)
                               (cadr pair))
                    (make-leaf-set (cdr pairs))))))


;;; Eksempel på kodebok:
(define sample-tree
  (make-code-tree
    (make-code-tree
      (make-leaf 'fight 6)
      (make-leaf 'ninjas 5))
    (make-code-tree
      (make-leaf 'samurais 4)
      (make-code-tree
        (make-leaf 'night 2)
        (make-leaf 'by 1)))))

;;; Eksempelkode: 
(define sample-code '(1 0 0 0 0 1 1 1 1 1 1 0))