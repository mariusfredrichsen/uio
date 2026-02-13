# Lecture 1
1. atomic operations are operations that cant be split up into smaller operations. The operation must be executed sequentially

2. interleaving is the different possible execution orders of concurrent operations

3. its about which order two operations must be done it. this changes the interleavings

4. more concurrent available operations leads to more interleavings

5. at-most-once property is wheter a procedure has at most one variable referenced to in the procedure. it lets us know wheter the procedure is interference free or not

6. they dont diserupt eachother and they can run as concurrent as they want without any locks

7. im not sure, what are strong and weak invariants

8. im not sure, what are safety and liveness property

9. list of values for x: 1, 0, -1. for example for the 1 it can occur when one process is trying to decrease it while the other tries to increase it at the same exact time. if we read the value on the right side of the equals sign before assigning it in either processess then we work with the number 0 while the other is also working with the number zero, at this point its just a matter of being last to assign the values

10. it does not since it has two referenced values

11. (x,y) in {(1,2), (2,1), (1,1)}

12. its the <> symbols. they make the whole operation happen before anything else can. Im not sure what would happen if  the <> were removed

13. its the invariants that describes the every valid state the procedures can be in. if the program were to terminate then it would terminate successfully inside of the global invariant

14. counter >= 0, counter < 2. Im assuming counter can only be 0 and 1 and that we dont work with bigger values than 1. this guarantees that only one process can access the critical section at a time by making the check atomic so no other processess can access it at the same time

15. because it lets multiple values read and multiple values write

16. syncronized s1(); syncrhonized s2()



# Lecture 3
1. A critical section is a part of the program that is accessible by multiple processes when it shouldnt

2. mutual exclusion is when we make sure each concurrent process is excluded from accessing a critical section. this is to prevent accessing the critical section at the same time

3. deadlock is when multiple concurrent processes are waiting for each other and not able to proceed. unnecessary delay is when operations are done while holding a lock when holding the lock is not really necessary, the other process could of have started without any trouble

4. when a statement in theory can be executed next, the statement is then enabled.

5. answer in 4.

6. unconditional fairness is when every unconditional statement can be executed next. weak fairness is unconditionally fair and every conditional atomic action can be exectued when true and stays true. unconditionally strong is uncondtionally fair and every atomic action can be exectued and the condition can be infinitely true

7. im not sure, what is a spin lock?

8. the entry point is atomic so only one process can access the critical section. it does under weak fairness because when the condition is true then it is chosen and remains true until done with the critical section

9. TAS is test and set and it tests if a condition is true, ready to access the critical section, it uses <> internally. Its not good for multiprocessor systems since there is a lot of read and write operations being done

10. TTAS is about adding a test case before the TAS to reduce the big amounts of a read and write operations in TAS. it requires strong fairness because we need to test infinitely many times to have liveness, the first T in TTAS is a while(!lock) skip;

11. the processes has a last variable controlling when a process has entered the critical section. This is still not mutual exclusion since they might enter at the exact same time.it relies on weak fairness

12. im not sure

13. im not sure

14. because we might skip the number n when two processess increament at the same time

15. im not sure

16. im not sure



# Lecture 4
1. a semaphore is a counter that can both increase or decrease with atomic operations. it acts like a lock

2. passeren aquired the lock or decreases the value of the counter in the semaphore while the vrijgeven is the unlock and increases the counter in the lock

3. they are atomic because its meant to be access by multiple processess

4. im not sure

5. binary semaphores are either 0 or 1 meaning only one process can access the section after the sempahore at a time. counting semaphores are either 0 or bigger than 0.

6. im not sure

7. semaphore invariant is the different states of the semaphore it can reach

8. You can get only get "A" then "B" since "B" is always blocked by the semaphore and "A" must come before unlocking the semaphore

9. Only 2 processess may print "X" concurrently since the semaphore allows for 2 to pass the semaphore before they get released

10. initialise the semaphore with 0 and let the producer increase (V) when they have produced and let the consumer try and decrease (P). This lets the consumer wait for when there is actual material they can consume

11. P(m); CS; V(m)

12. im not sure

13. yes, they always have to wait for the other process. s and t = 0 or s = 1 or t = 1 and !(s = 1 and t = 1). There is no deadlock since they are always waiting for eachother and always increases the semaphore at the end

14. im not sure

15. it can be lead to non-mutual exclusion since another process can access the critical section before the first process is done

16. im not sure



# Lecture 5
1. a monitor is a object with methods where each method is synchronized with a common lock every method shares

2. a condition variable is a condition that can be either signaled or awaited

3. signal-and-wait waits for the singaler to complete its process while signal-and-continue is completing the awaited place first

4. a monitor invariant is the invariant for the monitor. it describes when the condition variables are being signaled and awaited

5. im not sure

6. im not sure

7. we need to have a while loop to recheck the condition and await again. if T2 signals before awaiting then we are waiting forever since the signal is just unlocking once and not continuesly

8. no it cant be reset because we continue the waited place after signaling. after the signaled process is done, then we first set x to 0. im not sure about the x == 0 part

9. im not sure how to give a monitor invariant. It is still correct to signal since we still need increase it somehow, the counter is not full when its negative. im not sure about weak fairness

10. scheduling by time just prioritizes the first processess, a queue. scheduling by rank is a own prioritized queue. shortest job is picking the processess with less computing time first

11. im not sure

12. because monitors are simplified by using a common shared lock instead of using smaller locks where we unlock and lock

13. it might disrupt the monitor invariant

14. to keep the system consistent



# Lecture 6
1. message passing is the sharing of information without shared memory. different systems or channels pass messages to eachother either synchrounously or asynchronously

2. shared-memory system har a physical memory that they can share while distributed systems dont share anything physical and therefor have to rely on values being passed instead of 

3. synchrounous communication is when we need block or wait for the message to be received, asynchronouse is when we dont need to block

4. channel is the end points of message passing

5. buffered has a set amounts of receives and unbuffered is when we can recieve as many messages as wanted

6. im not sure

7. when receiving a message we wawnt to pass the results back to the channel that we received the message from with the results for example

8. im not sure

9. x can be either A or B and y can either be A or B (opposite of x) because the messages might take different routes when traversing packets in the network

10. im not fully sure but non-determenistic is when we cant know for sure when messages are getting passed through and therefor need fail safes for them

11. monitors are more reliable since channels isnt always reliable

12. message passing can be used acrros distributed systems

13. because distributed systems doesnt share anything physical and therefor need to use a message passing system to share information and cooperate

14. im not sure

15. im not sure

16. im not sure

17. a deadlock might be possible if the sends are not working properly but other than that there is no deadlocking. yes there might be deadlocks since we are activly waiting for messages and might be waiting for eachother

18. im not sure

19. im not sure

20. im not sure



# Lecture 7
1. goroutine is the different ways of calling function or operations in go, it creates concurrency in the code

2. go runs a new thread 

3. defer runs a process at the end of the process

4. a go channel is a channel that lets you send and receive messages with a set of parameters

5. x is assigned to what we are receiving from ch and ch <- x is sending x to ch 

6. it returns a channel that cant receive anything and only has a int as a parameter

7. waitgroup is a barrier

8. this is asynchronous communication because the send is doen via the go keyword. no because the receive part is not asynchronouse. because of the go keyword and because there is no go keyword on the receive

9. we send the value 5 to a channel ch

10. "C" blocks since we only can send 2 times to the channel

11. im not sure but select picks a case which is true and executes some code if the case is true

12. no because the messages may arrive at different speeds

13. im not sure, deadlock and go reacts on it?

14. because we might have multiple threads executing different things and we need form of synchronization to prevent different processess to finish after the main program is done for example

15. y:=<-ch1; x:=<-ch2; ch1<-1; ch2<-2;

16. close is necessary to tell processes that a channel is no longer in use and that they dont need to wait for the channel to receive

17. duck typing is about how typing in go works. if it walks like a duck, talks like a duck, work like a duck then it probably is a duck. if it has the same behaviour as a type then it probably is that type. Its supported with struct

18. because person doesnt have a function called work

19. im not sure

20. yes, the messages needs to be recieved before the channel is closed. im not sure. im not sure




# Lecture 8
1. im not sure

2. a future is a wished for value that is computed somewhere else either be a thread or a another system. it can be read as many times

3. a promise is a wished for value that is computed on another system

4. active object is its own thread of control which receives messages or sends messages. passive objects waits for its methods to be called from external objects

5. im not sure

6. im not sure

7. task is a process being done at the time. suspension is when we stop doing a task and do something else on the same thread. the thread is not stopping but the suspended task is. blocking is when the thread is stopping

8. no because actors operate on their own states only or send messages

9. im not sure

10. im not sure

11. im not sure

12. its when we are awaiting

13. im not sure

14. we suspend the task and do something else

15. im not sure, its not necessary to block the whole process when we can just suspend the task

16. yes because futures might not be completed and there might occur errors that prevents the future from being computed

17. they dont know anyone else if they are not told or have created another actor

18. im not sure

19. im not sure

20. because of package routes or them dying out when traversing the network

21. im not sure

22. no because we just suspend the current line and not block the whole process. return does not happen first at await 1 or 2. if its expensive then we pick the shortest job first

23. im not sure

24. im not sure

25. futures lets us do more tasks at the same time and wait for computations that might be done by other actors



# Lecture 9
1. a type system ensures that the typing of each variable or objects are correct and stay consistent so we dont run programs that might have something wrong

2. data types is the primary types or a tag that says a variable is that type while behaviour types is the available methods of a object

3. static typing is when type checking happens during compile time. we avoid running programs with fault in their typing. dynamic type checking is when we check the typing during run time. it lets us check for faults during runtime in case of type checking is not robust in compile time

4. im not sure

5. im not sure

6. im not sure 

7. im not sure

8. a premise is an assumption about a truth. conclusion is the result of that assumption. rule name just says what the step we are taking to get to a conclusion is. typing enviroment is to keep track of variables and channels

9. x does not exist in this styping enviroment

10. im not sure

11. progress theorem is that if there is code that hasnt been executed then we can run it

12. im not sure

13. im not sure

14. subtyping is reducing a type to a sub variant, integer is a sub type of double for example

15. im not sure

16. because we have many different threads and need to keep track of every thread

17. ! is a channel with send, ? is a channel with a receive, !? is a channel with both receive and send. Un is wheter a channel has a receive or send

18. yes because !? contains all the methods a ! channel has

19. no because the channel with only ? can ony recieve and not send

20. linear usage is when we use all the sends and receives in every channel by the end of the type checking. we dont want to have a deadlock. deadlocks occurr when there is sends or receives left

21. A and C since the other options contains receive

22. A: yes, B: no, C: i think so

23. They need to have used every receive and send if they have the type Un. This lets us know if there is a deadlock or not

24. im not sure

25. im not sure

26. because we cant decide whetere a program is decideable or not since that would solve NP = P

