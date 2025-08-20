# 


## Shared variable
- Atomic operations
- Interference
- Deadlock, Livelock
- etc.

**Parallel Operator**: 
- **co** S1 || S2 || ... || Sn **oc**
- Terminates when all of the programs (S1 - Sn) are terminated


### Interaction Between Parallel Processes
Interaction between processes can happen in two ways:
- ___Cooperation___ to obtain a result
- ___Competation____ for common resources

**Critical sections**: areas of the code that cannot be executed concurrently
**Ciritcal expression**: an expression e, a variable that is changed by another process
**Atomic operation**: The operation cannot be divided into smaller operations
- Able to ignore concurrency inside of atomic operations since there is no space to do multiple operations at the same time
- Examples of atomic operations:
    - Reading a value
    - Writing a value
- Factorial growth in number of executions when increasing the number of atomic operations
    - number of executions = (n*m)! / m!^n 

**Interleaving**: A possible execution of the program where order is respected.
- You cannot write a value before reading it. This is a order that needs to be respected

**Interleaving semantics**:  

**amo**: At most one reference?

**Disjoint processes**: Doing a disjunction between two processes looking at the only variables
- **Global variables** is shown if the disjunction doesn't give a empty set



### Properties
**Safety**: program will never reach an undesirable state
**Liveness**: program will eventually reach a desirable state

