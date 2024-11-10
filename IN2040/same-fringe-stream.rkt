(define-syntax cons-stream
  (syntax-rules ()
    ((cons-stream head tail)
     (cons head (delay tail)))))

(define (stream-null? stream)
  (null? stream))

(define (stream-car stream)
  (car stream))

(define the-empty-stream '())

(define (stream-cdr stream)
  (force (cdr stream)))

(define (stream-append s1 s2)
  (if (stream-null? s1)
      s2
      (cons-stream (stream-car s1)
                   (stream-append (stream-cdr s1) s2))))

(define (fringe-stream tree)
  (cond ((null? tree) the-empty-stream)
        ((pair? (car tree))
         (stream-append (fringe-stream (car tree))
                 (fringe-stream (cdr tree))))
        (else (cons-stream (car tree) (fringe-stream (cdr tree))))))

(define (same-fringe-stream? tree1 tree2 pred)
  (define (check-iter leafs1 leafs2)
    (cond ((and (stream-null? leafs1)
                (stream-null? leafs2))
           #t)
          ((or (stream-null? leafs1)
               (stream-null? leafs2))
           #f)
          ((pred (stream-car leafs1)
                 (stream-car leafs2))
           (check-iter (stream-cdr leafs1) (stream-cdr leafs2)))
          (else #f)))
  (check-iter (fringe-stream tree1) (fringe-stream tree2)))



(define (show-stream stream . n)
  (define (ss-rec stream i)
    (cond ((= i 0) (display "...\n"))
          ((stream-null? stream)  (newline))
          (else (display (stream-car stream))
                (display " ")
                (ss-rec (stream-cdr stream) (- i 1)))))
  (ss-rec stream (if (null? n) 15 (car n))))

(same-fringe-stream? '((1 2) 3 4)
                     '((1 (2)) 3 4)
                     =)

(same-fringe-stream? '((1 2) 3 4)
                     '((1 7) 3 4)
                     =)

(same-fringe-stream? '((1 2) 3 4 5)
                     '((1 2) 3 4)
                     =)