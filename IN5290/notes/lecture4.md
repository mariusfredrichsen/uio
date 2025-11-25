# Get in touch wtih services

## How to start comprimising a service?
- First use it in a normal way, get info
- force it to error and collect more info
- try factory defaults
    - cirt.net
    - phenoelit.org/dpl/dpl.html
    - defaultpassword.com
- brute-forcing
    - THC Hydra, ssh, ftp, http
    - Ncrack
    - Medusa
- search for known exploits, check version of the service
- unique ways

## Types of attack
### ftp service
- check if anonymouse is enabled
- can also brute-force credentials
    - hydra -t 2 -l admin -P passlist.txt -vV host ftp 
- if upload and the webroot is accessible then we can upload scripts to attack

### ssh service
- hydra --||-- ssh

### attacking SMTP
- SMTP (Simple Message Transfer Protocal) is a standard for emails 
- commands
    - HELO, sent by a client to identify itself
    - EHLO, multimedia HELO
    - MAIL FROM, identifies the sender
    - RCPT TO, message recepients
    - DATA, 
    - VRFY, verifies that a mailbox is available for message deliviry
- telnet < ip >
    - use the commands

### attacking DNS
- slave DNS servers can ask the master DNS server for a copy of its database
- bruteforce the subdomains in the database
- check if reverse lookup is enabled
- dnsrecon for subdomain lookup

## The order of steps of the attack
- manual analysis
- automatic analysis with tools
- manual analysis to verify the weaknessess
