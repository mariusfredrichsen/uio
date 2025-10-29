# Actors, Futures and promises, Active objects, Asynchronous communication with await-statement

## Actors
- has an identity, unlike channels
- active monitors
- objects and encapsulation
- race-free (no rece-condition on shared state)
- can only communicate by sending messages, but to the actor and not just a channel
- sening a message is asynchronous
- create objects on the fly
- **the idea** is decouple communication and control
    - the actor is a processing unit
    - it can create other actors
    - has its own state
    - is an object that can only communicate asynchronously
- actors can only communicate with each other if they know their identities
    - send their identity to other actors via message passing
- the number of threads and vary since actors can create other actors

### Message server
- a function with subscribers
- can either subscribe or publish a message to an id with an value
- if not then ignore the message
- an actor can change message server over time
- can have a fixed size on a message server by accepting only if there is space, can start another server if the first one is full
- you can return a value from an actor by sending your id and make it send a message back to the id

### Active vs passive monitor
- active has its own thread of control


- passive has to be activated from the outside
    - no access to variables outside to inside or inside to outside the monitor


## Futures and promises
- callback hell
- handle return values between actors


### Future
- kind of a mailbox
- creates a Future object that is waiting for a value
- the program continues as normal, but waits when we actually need it
- can be read multiple times
- needs to send to an actor with an id?


### Promise
- is a future for which it is not clear who computes the value
- does not need to message an actor with an id, but needs to send its own id?
- can create a workflow pattern, make one process compute it, and another process continue on it
    - chaining



## Active objects
- is an actor with an implicit message server
- only communicates asynchronously
- allows internal mesage handelrs to use cooperative scheduling
- one process per object
- messages indified with methods

- **cooperative scheduling:**
    - suspending is not the same as blocking, suspending lets you do other tasks while waiting for a signal
        - await is a suspend function
    - get is a blocking function
    - w! (send message) is asynchronous


## Asynchronous communication with await-statement
- maybe this is the cooperative scheduling part?


