# IN2040 oblig 1a
## Oppgave 1
### a)
( * <span style="color:red">( + 4 2 )</span> 5 ) <br>
=> <span style="color:red">( * 6 5 )</span> <br>
\>\>\> 30

Man ganger sammen resultatet av å plusse sammen 4 og 2 med 5 som til slutt gir 30.

### b)
( * <span style="color:red">( +  4 2 )</span> (5) ) <br>
=> ( * 6<span style="color:red"> (5)</span> ) <br>
\>\>\> error

Ulikt den forrige oppgaven er 5 inne i paranteser som tilsier at den prøver å gjøre en opperasjon på samme måte som (+ 2 2), men siden 5 ikke er en operasjon som + så vil den ikke kompilere.

### c)
( * <span style="color:red">( 4 + 2 )</span> 5 ) <br>
\>\>\> feil

Likt som den forrige oppgaven så er ikke 4 en operasjon og dermed vil ikke programmet kompileres.

### d)
(define bar <span style="color:red">( / 44 2 )</span> ) <br>
<span style="color:red">bar</span> <br>
\>\>\> 22

Koden definerer bar til å være en funksjon som deler 44 på 2 så henter man verdien fra bar på siste linje.

### e)
( - <span style="color:red">bar</span> 11 ) <br>
=> <span style="color:red">( - 22 11 )</span> <br>
\>\>\> 11

Henter verdien fra forrige oppgave og gjør en minus operasjon på 22 med 11.

### f)
( / ( * <span style="color:red">bar</span> 3 4 1 ) bar ) <br>
=> ( / <span style="color:red">( * 22 3 4 1 )</span>  bar ) <br>
=> ( / 264 <span style="color:red">bar</span> ) <br>
=> <span style="color:red">( / 264 22 )</span> <br>
\>\>\> 12

Henter bar definisjonen fra oppgave 1d og ganger den sammen med 3 4 og 1. Deler resultatet på bar og får 12.

## Oppgave 2
### a)
( or <span style="color:red">( = 1 2 )</span><br>
&ensp;&ensp;"paff!"<br>
&ensp;&ensp;"piff!"<br>
&ensp;&ensp;( zero? ( 1 - 1 ) ) )<br>
=><br>
( or ( = 1 2 )<br>
&ensp;&ensp;<span style="color:red">"paff!"</span><br>
&ensp;&ensp;"piff!"<br>
&ensp;&ensp;( zero? ( 1 - 1 ) ) ) <br>
\>\>\> #t

I en or operasjon er det ikke nødvendig å sjekke alle de forskjellige utrykkene for å se om det slår ut sant. Hvis den første slår ut sant så er det ikke nødvendig å sjekke resten av listen med utrykk. Siden "paff!" gir en verdi #t ettersom det ikke er #f så slår ut hele or utrykket #t.

( and <span style="color:red">( = 1 2 )</span><br>
&ensp;&ensp;"paff!"<br>
&ensp;&ensp;"piff!"<br>
&ensp;&ensp;( zero? ( 1 - 1 ) ) )<br>
\>\>\> #f

Ulikt fra den forrige oppgaven så MÅ man sjekke alle verdiene om de er sanne for at hele utrykket skal slå ut sant. Derimot kan man sjekke en og en om de er falske for å gjøre hele utrykket falsk.

( if <span style="color:red">(positiv? 42)</span><br>
&ensp;&ensp;"poff!"<br>
&ensp;&ensp;(i-am-undentified) )
\>\>\> "poff!"

Her sjekker man om 42 er et positivt nummer som i dette tilfellet er sant så man "sender" ut det første som er "poff!".

### b)
```scheme
( define (sign x) 
    (cond
        ((null? x) 0)
        ((positive? x) 1)
        ((negative? x) -1)
    )
)
```

```scheme
( define (sign x) 
    (if (null? x)
        0
        (if (positive? x)
        1
        -1
        )))
```
