(define (reverse2 items)
  (define (reverse-iter in out)
    (if (null? in)
        out
        (reverse-iter (cdr in)
                      (cons (car in) out))))
  (reverse-iter items '()))

(reverse2 '(1 2 3 4 5))