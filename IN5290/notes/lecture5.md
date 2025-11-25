# Web hacking 1, Client side bypass, Tampering data, Brute-forcing

## HTTP Hyper Text Transfer Protocol
- Request containing:
    - protocal version
    - the request file
    - webmethod
    - host name
- response containing:
    - web answer
    - date
    - content type
- methods
    - GET, POST, PUT, HEAD, DELETE
- **telnet**
    - GET / HTTP/1.1
    - Host: www.uio.no

## Client side 
- the browser downloads the html that is being processed
- the html can contain additional files
    - pictures, stylesheets, javascript codes, flash objects

## Start compromising a website
- use it in a normal way to get info
- decide if its static or dynamic (server side scripts or a database)
- find not intended content
- force site to order
- robots.txt
    - made to make search engines not check the following sites
    - means also that these sites exists
- default scripts
    - cgi-bin/test-cgi
    - GET /cgi-bin/test-cgi?/*
- dirb for directory brute-force
- client side filtering
    - the client can decide what they are sending out even though the website is trying to restrict it
- brute-force with hydra
    - hydra -l username -P passwordfile url.to.bf http-post-form "/something/something/:ed=^USER^&pw=^PASS^:F=Invalid"