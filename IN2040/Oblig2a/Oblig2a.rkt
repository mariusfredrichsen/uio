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

; p-car og p-cdr er inneholder en lambdafunksjon som tar inn verdiene
; fra p-cons (x og y) slik at i lambda funksjonen kan hente ut en av
; verdiene.


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
; forhåndsevaluert på grunn av ' tegnet. Dermed vil dette også
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
(define sample-code '(1 0 0 0 0 1 1 1 1 1 1 0))

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

; Oppgave 2a
(define (decode-iter bits tree)
  (define (decode-1-iter bits current-branch out)
    (if (null? bits)
      out
      (let ((next-branch
                (choose-branch (car bits) current-branch)))
        (cond 
          ((leaf? next-branch) 
            (decode-1-iter (cdr bits) 
            tree 
            (cons (symbol-leaf next-branch) out)))

          ((null? bits) out)
          (else (decode-1-iter (cdr bits) next-branch out))))))

  (decode-1-iter bits tree '()))

; Oppgave 2b
; > (samurais fight ninjas by night)


; Oppgave 2c

;; Hjelpefunksjon som sjekker om ordet vi skal kode
;; ligger i grenen sin liste over ord den inneholder
(define (check-if-word-in word symbollist)
  (if (null? symbollist)
      #f
      (if (eq? word (car symbollist))
          #t
          (check-if-word-in word (cdr symbollist)))))

;; Filter for aa sjekke at alle ordene vi vil kode er i treet
(define (filter pred items)
  (cond ((null? items) '())
        ((pred (car items)) (cons (car items) (filter pred (cdr items))))
        (else (filter pred (cdr items)))))


(define (encode seq tree)
  (define (encode-1 seq branch bits)    
    (cond ((null? seq) (reverse bits))
          ((check-if-word-in (car seq) (symbols (left-branch branch)))
            (if (leaf? (left-branch branch))
              (encode-1 (cdr seq) tree (cons '0 bits))
              (encode-1 seq (left-branch branch) (cons '0 bits))))
          (else
            (if (leaf? (right-branch branch))
              (encode-1 (cdr seq) tree (cons '1 bits))
              (encode-1 seq (right-branch branch) (cons '1 bits)))))
    )
  (if (equal? (filter (lambda (x) (check-if-word-in x (symbols tree))) seq) seq)
      (encode-1 seq tree '())
      '()))


(define (make-leaf-set pairs)
  (if (null? pairs)
      '()
      (let ((pair (car pairs)))
        (adjoin-set (make-leaf (car pair)
                               (cadr pair))
                    (make-leaf-set (cdr pairs))))))

(define (grow-huffman-tree freqs)
  ; sortert liste her
  
  (define (grow-huffman-tree-iter nodes)
    (write nodes)
    (grow-huffman-tree-iter (adjoine-set (make-code-tree (car nodes) (cadr nodes)) (cddr nodes))))


  (grow-huffman-tree-iter (make-leaf-set freqs))
)
(define freqs '((a 2) (b 5) (c 1) (d 3) (e 1) (f 3)))
(grow-huffman-tree freqs)



(define (huffman-leaves branch)
  (if (leaf? branch)
    (cons (list (symbol branch) (weight branch)))
    (huffman-leaves (left-branch branch))
    (huffman-leaves (right-branch branch))
  )
)

(huffman-leaves sample-tree)