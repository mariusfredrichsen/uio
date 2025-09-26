# Message passing and channels (Shared Memory and Await)

### Shared state vs messages
- shared variables: one process writes into a variable that is read by another
- message passing: one process sends a message that is received by another
    - distributed programming

### Distributed memory
- each processor has their own private memory
- creates a network where messages are passed to each other
- memory is dsitrubuted which means that they dont share any variables
- communication happens via sending/receiving messages in shared channels
    - or via RPC and rendezvous

### System Architectures
- SMP (symmetrics Multiprocessing)
- Multi-core architecture
- NUMA


### Message passing
- sending a message to a process
- can use the message to invoke a process

- (synchranous) a process sends a message to another process and the sender blocks until they receive a reply from the receiver, then is unblocked when they have received a message back
    - memory buffer is required
    - more proned to deadlocks
- (asynchranous) you can choose to continue its process after sending a message instead of just waiting for waiting for a response
    - memory buffer is required
    - less proned to deadlocks

### Channels (async)
- physical comminication network, one-way communication between entities
- is a FIFO queue of waiting messages
- it preserves message order
- atomic access to the channel
- error-free so we dont have package loss
- they are typed so we know how they look and can base it off that
- there are many different types of channels that might make the points above different

### Syntax
- chan c(type1 id1, ..., typeN idN)
- methods
    - send c(expr1, ..., exprN)
        - non-blocking, continues its process
    - receive c(v1, ..., vN)
        - blocking: waits for the message to be passed to this channel
        - message stored in variables v1, ..., vN
        - local variables are defined and is later given a value with receive channel(local variable 1, local variable 2...)
    empty(c)
        - true if channel is empty
    - **EOS** is a end of stream marker to say that there arent any more values being sendt

### Filters
- the messages are passing through a filter
- the filter takes a input and send out a output as a function if the input
- sort example
    - the n inputs and sort the n inputs and sort them then output it

    - sorting network, a lot of mergers (filters)

### Monitor and message passing
- monitors (passive)
    - controlled access to shared resources
    - global variables safeguard the resource state
    - access to a resource via procedures
    - procedures are exectured under mutual exclusion
    - condition variables for synchronization
- active monitors

### channels (sync)
- all sender has at most 1 unsent message
- all receivers can at most receive 1 message at a time
