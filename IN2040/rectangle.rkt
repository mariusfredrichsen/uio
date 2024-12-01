#lang racket

(define (make-point x y)
  (cons x y))

(define (x-point point)
  (car point))

(define (y-point point)
  (cdr point))

(define (print-point p)
  (newline)
  (display "(")
  (display (x-point p))
  (display ",")
  (display (y-point p))
  (display ")"))

(define (midpoint-segment point1 point2)
  (let ((x (/ (+ (x-point point1) (x-point point2)) 2))
        (y (/ (+ (y-point point1) (y-point point2)) 2)))
    (cons x y)))

(define (make-segment start-segment end-segment)
  (print-point start-segment)
  (print-point (midpoint-segment start-segment end-segment))
  (print-point end-segment))

(make-segment (make-point 1 1) (make-point 5 4))

(define (square x)
  (* x x))

(define (length-p point1 point2)
  (let ((x (+ (x-point point1) (x-point point2)))
        (y (+ (y-point point1) (y-point point2))))
    (sqrt (+ (square x) (square y)))))

(define (rectangle point1 point2 point3)
  (let ((p12 (length-p point1 point2))
        (p23 (length-p point2 point3)))
    (/ (* p12 p23) 2)))

(rectangle (make-point 0 0) (make-point 2 0) (make-point 0 2))