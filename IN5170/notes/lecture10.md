# Linearity


### Channel types
- T <: T' with T not equal T'
- check if an expression is a channel and if e' has type T' in a enviroment and that T' is a subtype of T


- **typing receiving**
    - 

- **Channel modes**
    - ? - receive
    - ! - send
    - !? - both
    - T ::= ...| chan_M T where M ::= ! | ? | !?

    - subtyping
        - chan! T <: chan!? T
        - chan? T <: chan!? T
    - weakening
        - make a channel that can both receive and send to just receive or just send
        - make a type go to a subtype
    
### Linear types
- **linerity** 
    - a linear channel can be used for exactly one send/receive operation
    - a linear resource cannot be reused after being accessed and must be accessed

- type syntax
    - chan?0,!0 means that it can only receive/send nonce, but chan?1,!1 means once for both receive/send, chan?0,!1 means send once but not receiving
    - possible to combine two channels where each of their sends/receives are combined
        - this also menas we can split a channel into two channel where the channel had 1 receive and 1 send to two channels with 1,0 and 0,1

- unrestriction
    - if a enviroment has no send/receives in total for all channels, We write un(|´)
    - have to check if a channel is unrestricted in the enviroment
    - always check if a channel is unrestricted, has nothing left to do, else it is a deadlock

- when to split
    - if else
    - parallele
    - assign

### Usage Types
- T ::= ... . . . | chan_U T
- U ::= 0 | ?.U | !.U | U + U | U & U
- first eceive, then send, then no usage: ?.!.0
- Receive or send, no other usage, ?.0&!.0
- syncrhonization once, ?.0+!.0
- syncrhonize twice, ?.!.0 + !.?.0

- The dot ( . ) is to tell us which order we evaluate in
