(define (filter pred items)
  (cond ((null? items) '())
        ((pred (car items))
         (cons (car items)
               (filter pred (cdr items))))
        (else (filter pred (cdr items)))))


(define (quicksort items)
  (if (null? items)
      items
      (append
       
       (quicksort
        (filter (lambda (y) (>= (car items) y))
                (cdr items)))
       
       (list (car items))
       
       (quicksort
        (filter (lambda (y) (< (car items) y))
                (cdr items)))
       )
      )
)

(quicksort '(5 3 2 4 1))