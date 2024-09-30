(define (last-pair items)
  (define (last-pair-iter reversed-list)
    (cond ((null? reversed-list) '())
          ((even? (car reversed-list)) (list (car reversed-list)))
          (else (last-pair-iter (cdr reversed-list))))
    )
  (last-pair-iter (reverse items))
)

(last-pair '(23 72 149 34))

