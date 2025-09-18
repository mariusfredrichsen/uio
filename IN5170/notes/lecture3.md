# Semaphores
- a concept or a mechanism for mutual exclusion and conition synchronization
- signel and scheduling

### Two Semaphore Operations: P and V
- P (Passeren) wait for a signal - want to pass
    - wait unitl value is greater than zero, and decrease value by one
- V (Vrijgeven) Signal an event - release
    - increace the value by one

- names today, up/down, wait/signal, acquire/release
- different flavors, binary vs counting
- monst common, mutex as a sytnonym for binary sempahores




### Syntax and Semantics
- default inital value is zero for a semaphore
- sem s; // value is 0
- sem s[4] := ([4] 1) // an array of semaphores where everyone has a value of 1

#### P-operation P(s)
- < await (s > 0) s := s - 1 >

#### V-operation V(s)
- < s := s + 1 >

#### General semaphoers
- the possible values are all non-negative integers

#### Binary semaphore
- the possible values are just 0 and 1

#### Fairness
- Everything that is awaiting should eventually get access
- There is a queue, FIFO

#### Barriers with semaphores
- initalize the semaphores to 0
- wait for the others to arrive and increase it to 1 so everyone can pass

#### Split binary semaphores
- A set of sempahores whose sum is either 0 or 1


# Readers/Writers

### General synchronization problem
- Readers: must wait until no writers are active in DB
- Writers: must wait until no readers are active in the DB

#### Two approaches
- Mutex, easy but unfair
- Condition syncrhonization
    - using a split binary semaphore
    - easy to adapt to different cases

### Condition Synchronization: Converting to Split Binary Semaphores
- add two semaphores, one counter and one delay-sempahore
- add signaling, SIGNAL
    - SIGNAL is crafted in a smart way based on the counters and delays to be fair