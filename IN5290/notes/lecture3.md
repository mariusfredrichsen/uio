# Network reconnaissance, port scanning

## Circuit switched vs packet switched networks
- **Curcuit switched**
    - a communication line between A and B is busy until the communication is over
- **Packet swictehd**
    - send a packet to a node and the node sends it to the next appropriate node 
    - no planned route
    - need a ttl (time to live) value to make sure that the packet doesnt go in a infinite loop

### OSI model
- Data - Application, Presentation, Session
- Segments - Transport
- Packets - Network
- Frames - Data Link
- Bita - Physical

### Network mapping - answer options
- positive, response saying its reachable
- negative, response saying its unreachable 
- no answer, no response

## Nmap 
- ping, nmap **-sP** www.uio.no
- can scan with ip, CIDR, range, list
- version detection
- -sL, list scan
- -sT, tcp scan
- -sA, ack scan
- check database for version detection or os version
- can script using lua language
- 

## Transport layer
- TCP, slow but reliable
    - typical ports, 80, 443, 20, 21, 22, 25, 137, 139, 445, 3306, 3389, 5900
    - **handshake**
        - sender sends SYN
        - receiver sends SYN + ACK
        - sender sends ACK 
        - ^ port is open, v posrt i closed
        - sender sends SYN
        - receiver sends RST
        - if sender sends SYN and sender receives nothing back the port is then filtered
        - can do a SYN scan to avoid the completion of the handshake
            - done with a RST respons (Reset)
            - useful since firewalls usualy logs only the established connections
    - ACK scan to check if the firewall is stateless or stateful
- UDP, fast but unreliable
    - typical ports, 53, 111, 123 



