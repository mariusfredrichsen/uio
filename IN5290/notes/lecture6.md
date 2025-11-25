# Web hacking 2 - Session related attacks, Cross site scripting (XSS), Cross site Request Forgery (CSRF)

## Burpsuite
- Intruder
    - maniupulate paramters and brute-force

## Session related attacks
- session starts when a user launches the website, gets a unique session id
- attacks
    - prediction of a session token
    - session sniffing, uses a sniffer to find a token
    - client side attacks, redirects the user to its own site to steal the cookie (document.cookie)
    - man in the middle attack, intercept the communication between to computers
    - man in the browser attack
- protecting
    - using ssl/tls, packet is encrypted and decrypted
    - httponly flag, additional flag in the response header that protects the cookie 
    - geolocation
- session ids should expire after 30min but not after 6hours
- hijacking tools
    - firesheep http session hijacking (firefox extention)
    - cookie cadger
    - webcookiesniffer

## Cross site scripting (XSS)
- input from user that can change the behaviour of the website
- with XSS you can:
    - redirect, document.location="newip"
    - page rewrite, document.body.innerHTML="< div >...< /div >"
    - cookie stealing, without httponly you can alert the session token to show it, document.cookie
- filter evasion, can filter XSS attacks but again can be avoided by reformatting the attack
- **types**
    - DOM based, the dataflow never leaves the website
    - Stored, input is stored on a database or similar
    - Reflected, 
    - Client side, usually fires a javascript call
    - Server side, something sendt to the server without the server checking or validating
- tools
    - OWASP Xenotix XSS exploit framework
    - XSSer
    - XSS-Proxy

## Cross site request forgery (CSRF)
- forge a get request that make the user do something they dont want to, the session is valid since the user logged in and the forget get request is executed
- preventions
    - checking the referrer header 
    - adding a hash to everything
    - loging off before visiting a new site
    - clearing all cookies after each session