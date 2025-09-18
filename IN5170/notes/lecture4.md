# Monitors
- Definition: A monitor is a program module with more structure than semaphores: Is a abstract data type with built-in synchronization

- State: contains variables that descrive the "state"
    - variables can only be changes through the available procedures (in the monitor?)

- Synchronization of a monitor
    - Implicit mutual exclusion, at most one procedure may be active at a time for a monitor

- Cooperative scheduling: procedures coordinate their monitor access
    - Condition synchronization blocks a process until a particular condition is met
    - Condition synchronization is done with condition variables

- processes communicate by calling monitor procedures, they dont need to know all the implementation details
- All shared variables are inside the monitor

- has two queues
    - entry queue
    - condition variable queue

### Syntax & Semantics
- **monitor** name {
    variables               // are on the top
    ##**invariant**         // ??
    initialization code     // 
    procedures              // synchronized processes inside of the monitor
}

- **cond**, is the condition variable
    - each condition variable is associated with a queue of some sort
    - used to synchronized or delay processes

    - cond cv
    - empty(cv)         // check if the queue is empty
    - wait(cv)          // causes the process to wait in the cv queue
    - sigal(cv)         // remove one process from the waiting queue, or wakes it up
    - signal_all(cv)    // yess...


### Signal disciplines
- Signal and Wait (SW), the signaler waits, and the signaled process gets to execute immediately
- Signal and Continue (SC), the signaler continues, and the signaled process executes later


### Bounded buffer synchronization
- get and put in the queues wait if it is empty or full
- have to re-test everytime, has to wake up the put process if the process leaves


### Rendezvous
- Two process barrier, both parties must arrive before either can continue
- both the server and the client must wait for each other to continue
- 4 conditions and 3 conditional variables
