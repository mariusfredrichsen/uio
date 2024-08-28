#lang racket

; Oppgave 1

; a)
; ( * ( + 4 2 ) 5 )
; Her sjekker den * først så ( + 4 2 ) så ganger resultatet med 5

; b)
; ( * ( + 4 2 ) ( 5 ) )
; Her vil den plusse sammen 4 og 2 først, men stopper på evalueringen av ( 5 )
; ettersom 5 er ikke definert som en operasjon

; c)
; ( * ( 4 + 2 ) 5 )
; På samme måte som i forrige oppgave så er ikke 4 definert som en operasjon

; d)
; ( define bar ( / 44 2 ) )
; bar
; Her defineres bar med at man deler 44 på 2 så evaluerer man bar
; som er da denne dele operasjonen på første linje

; e)
; ( - bar 11 )
; Antar at bar bruker samme definisjon fra forrige oppgave
; den vil først evaluere bar og finner 22, så utføre minus operasjonen

; f)
; ( / ( * bar 3 4 1 ) bar )
; Den går inn i gange operasjonen og evaluerer bar først
; etter den har ganget evaluerer den bar variabelen som ligger ytterst
; så dele 264 på bar

; Oppgave 2
; a)
; ( or ( = 1 2 ) 
;    "paff!"
;    "piff!"
;    (zero? ( 1 - 1 )))

; evaluerer = først, så "paff!" og slår ut true
; or er "lazy" og tar en og en ting ad gangen og sjekker om den er true
; trenger ikke å sjekke for de andre hvis utrykket er allerede true

; ( or ( = 1 2 ) 
;    "paff!"
;    "piff!"
;    (zero? ( 1 - 1 )))

; evaluerer = først og slår av #f siden det ikke er sant
; siden resten av utrykket er irrelevant siden det blir false
; uansett så evalueres ikke flere utrykk

; (if (positiv? 42) 
;    "poff!"
;    (i-am-undentified))
; Her vil den slå ut true og evaluerer "poff!" og "sender" den ut 

; b)
( define (sign x) 
    (if (positive? x)
        1
        (if (negative? x)
            -1
            0 )))

( define (sign x) 
    ( cond
        ( ( positive? x ) 1 )
        ( ( negative? x ) -1 )
        ( else 0 )))

; c)
( define (sign x)
  ( or ( or ( and ( > x 0 )
                1 )
           ( and ( < x 0 )
                -1 ) )
       0 ))
; misbruker det at and returnerer det siste som slår true i utrykket
; og or for å ha en form for else for noe er null

; Oppgave 3
; a)
( define ( add1 x )
   ( + x 1 ) )

( define ( sub1 x )
   ( - x 1 ) )

; b)
( define ( plus x y)
   (if (zero? y)
       x
       (plus (add1 x) (sub1 y))))
