# Locks and barriers

### Access to Critical Sections (CS)
- **Mutual exclution**: Only one process can have access at a time

### General Patterns for Critical Sections
- CSentry and CSexit
- Cannot be stuck in the CS
- Make sure to exit the CS

### Properties for a CS
- Safety property
    - Mutual exclusion
    - Absence of deadlock
    - Absence of unnecessary delay
- Liveness property
    - Eventual entry

#### Atomic Sections
- < **await** (B) S >
    - both B and S is atomically exectued

### Locks
< **await** (!lock) lock := true > <br>
CS <br>
lock := false <br>
non-CS

### Test & Set (TAS)
TAS(lock) { <br>
    < **bool** initial := lock; <br>
    lock := true; > <br>
    return initial <br>
}

**while** (TAS(lock)) {skip}; <br>
CS <br>
lock := false <br>

### Test, Test & Set (TTAS)

**while** (lock) {skip} <br>
**while** (TAS(lock)) {skip}; <br>
CS <br>
lock := false <br>

TASlock is slower than TTASLock


### Scheduling and atomic section
< S > = CSentry; S; CSexit

< **await** (B) S >

CSentry

#### Enabledness
#### Scheduling
- Pick a possible move out of a set of possible moves
#### Fairness (informally)
- Even though a move is not beneficial we run it either way

#### Unconditional Fairness
- each enabled unconditional atomic action, will eventually be chosen
- round robin

#### Weak fairness
- uncondinitinally fair, and
- every conditional atomic action will eventually be chosen, assuming that the condition becomes true and **remains true** until the action is executed
- might run infinitely

- **Tie-Breaker algorithm**
    - in1, in2
    - last
    - **while** (in2 and last != 2)
    - **while** (in1 and last != 1)
    - does not scale
- **Ticket algorithm**
    - queue implementation
    - ticket_number, highest possible number
    - next, next to be called
    - turn_array, n elements, default value 0
    - < turn[i] := number; number := number + 1 >
    - < **await** (turn[i] = next)
    - CS
    - < next := next +1 >
    - note: **await** could be a while loop
    - FA, fetch and add, returns the original value, but changes the value of it

#### Strong fairness
- uncondinitionally fair, and
- each conditional atomic action will eventuyally be chosen, if the condition is ture infinitely often
- there is always a opportunity for termination


### Barriers
- shared counter, uses atomic operations which is slow(?)
    - also slow due to many read and write to a "count" variable
- divide into multiple variables with a global coordinator process
    - waiting for a flag
    - the flag is a list containing the status of the workers
    - the workers wait for each of their flags (start of with 0 when the coordinator sets it to 1, then the workers wait for it to turn to 1)

