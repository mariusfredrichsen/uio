# Technical Information Gathering

## Domain names
- domain names are formed by the rules and procedurrs of the Domain Name System (DNS)
- anything named in DNS is a domain name
- top level domains: com, net, info, edu, org, and country codes
- second and third level domains can be any string
- full length of the domain cannot be longer than 255 characters
- the structure of the domain reads from right to left
- host (www), third (cs), second (uio), top (no), www.cs.uio.no
    - TLD (Top-Level Domain)
    - SLD (Second-Level Domain)

- **whois** database
    - contains: 
        - Administative contact
        - Technical contact
        - Billing contact
        - Name servers
            - computers that provide subdomain infromation for the particular domain using the dns protocal

- OS version detection for domains can be done with netcraft
- DNS dumpster
- crt.sh, Certificate transparency logs

## IP addresses
- IPv4: 32bit, 2³² combinations
- IPv6: 128bit, 2¹²⁸ combinations
- identification of computers during communication
- 8bits are used for ipv4, 129.240.171.52
- ipv6 are represented as hexadecimals digits, 2001:0db8:0000:0042:0000:8a2e:0370:7334

### Ip ranges
- **classful networking**
    - 129.240.171.56 - 129.240.171.63 (8 adresses)
    - classes A, B and C
        A. 0.0.0.0 - 127.255.255.255, 128 ranges 125³ in 1 range
        B. 128.0.0.0 - 191.255.255.255, 16384 ranges 256² in 1 range
        C. 192.0.0.0 - 223.255.255.255, 2097152 ranges 256 in 1 range
    - divide it into network and subnet part
        - 129.240, network
        - 171.58, identifies the host within the network
- **classless InterDomain Routing (CIDR)**
    - network adress length is arbitrary (not only 8,16,24)
    - 129.240.171.56/29, the first 29 bits are fixed while the 3 last can be anything

### Domain to ip conversion
    - Root DNS --> .com DNS, .org DNS, .edu DNS, .au DNS ... --> .amazon.com, -pbs.org, .poly.edu, .mit.edu ...
    - 13 root servers, stores reduntently, master and slave
    - Address mapping (A)
    - IP Version 6 Adress (AAAA)
    - Canonical Name (CNAME)
    - Host information (HINFO)
    - Mail exchanger (MX)
    - Name Server (NS)
    - Reverse-lookup Pointer (PTR)

### IP range owners
- whois protocal
- stored in different databases
- European database is RUPE NCC for example

### Hosted websites - cloud services
- website is hosted, stored on a webserver
- hard to attack the website itself

### Finding network ranges
- search for all domains including second and third level
- look for the corresponding ips
- check which database contains the ip owner (whois)
- check the ip ranges (ripe, arin, etc...)
- check by AS number (Autonomous system number)
    - ASN lookup

### Robtex
- used for research of IP numbers, domain names, etc

### Shodan - IOT device finder

### Types of computers in the network
- Server
- Network device (router or switch)
- Firewall (stateless, statefull, Ids, Ips)
- Printers
- User desktops
- User laptops
- Mobile devices
- IOTs

- Good to map out the network architecture

### FOCA
- Automatically identifies subdomains, servers, ips

### Maltego - Information gathering tool
- finds subdomains to a main domain?

