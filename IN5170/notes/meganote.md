# Models of Concurrency
## Lecture 1: Introduction
### Atomic operations
### Interleaving
### Possible execution sequence
### Program order
### Factorial explosion
### At-Most-Once (AMO)
### Read and Write variables
#### Interference Freedom
### Critical section and inveriants
#### Property
##### Safety
##### Liveness
##### Invariant
###### Strong
###### Weak
###### Loop
###### Global
###### Local

## Lecture 3: Locks and Barriers
### Mutual Exclusion
#### Critical Section
##### Pattern
##### Desired Properties for Access
##### TAS & TTAS
### Scheduling & Fairness
#### Enabledness
#### Fairness
##### Unconditional
##### Weak
##### Strong
### Locks and Barriers

## Lecture 4: Semaphores
### Sempahore
#### P (Passern)
##### ⟨await (s > 0)s := s − 1⟩
#### V (Vrijgeven)
##### ⟨s := s + 1⟩
### Invariants & Condition Synchronization
#### Invariant

## Lecture 5: Monitors
### Monitors
#### State
#### Synchonrization
#### Condition
##### Signaling
###### Signal & Wait
###### Signal & Continue
#### Invariant
### Time server
#### Prioritization
##### Time
##### Rank
##### Shortest Job

## Lecture 6: Message Passing
### Distributed Systems
### Message Passing
#### Synchronous
#### Asynchronous
### Channel
#### Call-back
#### Monitors
##### Passive
##### Active

## Lecture 7: Concurrency in Go
### Go
#### Struct
#### Duck Typing
### Go Concurrency
#### Goroutines
##### f(x)
##### go
##### defer
#### Channels in Go
##### make
##### <-
##### ->
##### Select
#### WaitGroup

## Lecture 8: Actors, Active Objects & Asynchronous Communication
### Actors
#### Characteristics
### Futures
### Promises
### Active Objects
#### Implicit message server
#### Asynchronous communication
#### Cooperative scheduling
##### Send value
##### Retrieve value
##### Check value
##### Task
###### Suspending
###### Blocking
### Async
#### Asynchirnous calls
#### Returns Task
#### Can perform await

## Lecture 9: Type Systems and Concurrency
### Foundations
#### Types for Expressions
##### Data
##### Behaviour
#### Well-typedness
##### Dyanmic vs static
##### Decidable vs undecidable
##### Strong vs weak
#### Type Soundness
##### Reachability
##### Reduction
### Typing rules
#### Premise
#### Rule name
#### Conclusion
#### Typing Enviroment
##### Γ(x) = ⊥
#### Progress & PReservation
#### Subtyping
### Channel types
#### ?
#### !
#### !?
#### Weakning rule
#### Un
#### 