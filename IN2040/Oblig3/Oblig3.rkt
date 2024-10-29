#lang racket









;;;; Diverse hjelpekode for innlevering 3 i IN2040, 2024,
;;;; tidlige var det for oblig3a (2020, 2022, 2023)
;;;; erikve [at] ifi.uio.no,...  

;; Tabell-abstraksjon fra seksjon 3.3.3 i SICP:

(define (make-table)
  (list '*table*))

(define (lookup key table)
  (let ((record (assoc key (cdr table))))
    (and record (cdr record))))

(define (insert! key value table)
  (let ((record (assoc key (cdr table))))
    (if record
        (set-cdr! record value)
        (set-cdr! table
                  (cons (cons key value) (cdr table))))))


;; mem-testprosedyre 1; fibonacci-tallene
(define (fib n)
  (display "computing fib of ")
  (display n) (newline)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        (else (+ (fib (- n 1))
                 (fib (- n 2))))))



;; mem-testprosedyre 2; tar virkårlig mange argumenter (null eller flere).
;; (Returnerer summen av argumentenes kvadrerte differanse fra 42.)
(define (test-proc . args)
  (display "computing test-proc of ")
  (display args) (newline)
  (if (null? args)
      0
      (+ (expt (- 42 (car args)) 2)
         (apply test-proc (cdr args)))))

(define global-counter 0)

(define (mem mode proc)
  (let*  ((first-proc #f)
         (old-proc proc))
    (lambda (n)
      (let ((table (make-table)))
        (if (zero? global-counter)
            (set! first-proc proc))
        (set! global-counter (+ global-counter 1))
        (cond ((equal? mode 'unmemoize)
               (first-proc n))
              ((eq? mode 'memoize)
               (or (lookup n table)
                   (let ((result (proc n)))
                     (insert! n result table)
                     result))))))))
  
        


(set! fib (mem 'unmemoize fib))
(fib 3)
(set! fib (mem 'unmemoize fib))
(fib 3)
(set! fib (mem 'memoize fib))
(fib 3)
(fib 5)
(fib 8)
(set! fib (mem 'unmemoize fib))
(fib 4)


; forsøk 2

(define (memoize proc)
  (let ((table (make-table)))
    (lambda (n)
      (or (lookup n table)
          (let ((result (proc n)))
            (insert! n result table)
            result)))))

(define (mem mode proc)
  (let ((table (make-table)))
    (define (memoized proc)
      (let ((result (memoize proc)))
             (insert! result proc table)
             result))
    (define (unmemoized proc)
      (let ((first-proc (lookup proc table)))
             (or first-proc
                 proc)))
    (define (dispatch mode)
      (cond ((eq? mode 'memoize)
           (memoized proc))
          ((eq? mode 'unmemoize)
           (unmemoized proc))))
    (dispatch mode)))
  



(set! fib (mem 'unmemoize fib))
(fib 3)
(set! fib (mem 'unmemoize fib))
(fib 3)
(set! fib (mem 'memoize fib))
(fib 3)
(fib 5)
(fib 8)
(set! fib (mem 'unmemoize fib))
(fib 4)



;; Under definerer vi et grensesnitt som lar oss jobbe med strømmer på
;; samme måte som i seksjon 3.4 i SICP.

(define-syntax
  cons-stream
  (syntax-rules ()
    ((cons-stream head tail) (cons head (delay tail)))))

(define (stream-car stream)
  (car stream))

(define (stream-cdr stream)
  (force (cdr stream)))

(define the-empty-stream '())

(define (stream-null? stream)
  (null? stream))

;; Merk at `force' (prosedyre) og `delay' (special form) er innebygd i
;; R5RS. Konseptuelt kan vi tenke oss at de er definert som følger;
;;
;; (define (memo proc)
;;   (let ((forced? #f)
;;         (result #f))
;;     (lambda ()
;;       (if (not forced?)
;; 	  (begin (set! result (proc))
;; 		 (set! forced? #t)))
;;       result)))
;;
;; (define-syntax
;;   delay
;;   (syntax-rules ()
;;     ((delay exp) (memo (lambda () exp)))))
;;
;; (define (force promise)
;;   (promise))


;; Under følger en del hjelpeprosedyrer til de ulike deloppgave, og
;; diverse listeoperasjoner tilpasset strømmer;


;; En prosedyre som lar oss titte på de n første elementene i en strøm:
(define (show-stream stream . n)
  (define (ss-rec stream i)
    (cond ((= i 0) (display "...\n"))
          ((stream-null? stream)  (newline))
          (else (display (stream-car stream))
                (display " ")
                (ss-rec (stream-cdr stream) (- i 1)))))
  (ss-rec stream (if (null? n) 15 (car n))))


;; brukes bl.a. i oppgave 2d:

(define (stream-filter pred stream)
  (cond ((stream-null? stream) the-empty-stream)
        ((pred (stream-car stream))
         (cons-stream (stream-car stream)
                      (stream-filter pred
                                     (stream-cdr stream))))
        (else (stream-filter pred (stream-cdr stream)))))



;; brukes i oppgave 2a:

(define (integers-starting-from n)
  (cons-stream n (integers-starting-from (+ n 1))))

;; naturlige tall / natural numbers:
(define nats (integers-starting-from 1))

(define (stream-ref s n)
  (if (= n 0)
      (stream-car s)
      (stream-ref (stream-cdr s) (- n 1))))

(define (stream-interval low high)
  (if (> low high)
      the-empty-stream
      (cons-stream
        low
        (stream-interval (+ low 1) high))))


;; nyttig prosedyre for å leke seg med eksempler fra foilene:

(define (stream-map proc . argstreams)
  (if (any? stream-null? argstreams)
      the-empty-stream
      (cons-stream
        (apply proc (map stream-car argstreams))
        (apply stream-map
               (cons proc (map stream-cdr argstreams))))))


;; hjelpeprosedyre for basistilfellet i stream-map:
(define (any? pred list)
  (cond ((null? list) #f)
        ((pred (car list)) #t)    ;; stopp så fort vi får #t
        (else (any? pred (cdr list)))))



; Oppgave 2

; a)
(define (list-to-stream list)
  (if (null? list)
      the-empty-stream
      (cons-stream (car list) (list-to-stream (cdr list)))))

(define (stream-to-list stream . n)
  (define (rec stream i)
    (cond ((= i 0) '())
          ((stream-null? stream) '())
          (else (cons (stream-car stream)
                      (rec (stream-cdr stream) (- i 1))))))
  (rec stream (if (null? n) 15 (car n))))


; b)
(define (stream-take n stream)
  (if (or (zero? n) (stream-null? stream))
      the-empty-stream
      (cons-stream (stream-car stream)
                   (stream-take (- n 1)
                                (stream-cdr stream)))))

; c)
; diskuter

; d)
(define (remove-duplicates stream)
  (if (stream-null? stream)
      the-empty-stream
      (cons-stream (stream-car stream)
                   (remove-duplicates
                    (stream-filter
                     (lambda (element)
                       (not (eq? (stream-car stream) element)))
                    (stream-cdr stream))))))
