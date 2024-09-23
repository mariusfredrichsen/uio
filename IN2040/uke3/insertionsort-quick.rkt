(define (insert x items)
  (cond ((null? items) (list x))
        ((<= x (car items)) (cons x items))
        (else (cons (car items) (insert x (cdr items))))))

(define (insertion-sort items)
  (define (sort-iter in out)
    (if (null? in)
        out
        (sort-iter (cdr in) (insert (car in) out))))
  (sort-iter items '()))

(insertion-sort '(5 2 1 3 4))
; setter inn direkte enn å splitte opp listen og legge det inn