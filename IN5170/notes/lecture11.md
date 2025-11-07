# Concurrecny in Rust
- rust has a lot of guards to make sure you dont do anything wrong
- memory safety and concurrency

- linear types (deallocation after use)


### Ownership in rust
- each value is owned by a single variable called "owner"
- can only be one owner at a time, the ownership can be passed
- when the owner goes out of scope the value will be dropped
- a form of garbage collection, managing memory
- helps prevent data races at compile time
- cannot pass the ownership from s1 --> s2 (s2 = s1) and then later use s1 since s1 is not in the memory since its ownship is gone
- **borrowing**, passing down the reference and not the whole object and ownership
    - syntax is &VariableType in the parameter. String reference and not a String object
    - also need to send in the reference by &Variable, s2 = &s1
- can clone the object to have two ownerships (uses more memory)
- integer values are automatically copied, but not strings (s1 and s2)
- reference passing is immutable by default
    - need to pass &mut reference into a &mut parameter from a mut variable
    - let mut s, send &mut s, to &mut Type
- a value can have one mutable reference or many immutable references but not both

### Lifetime
- dies out in scopes
- cannot have a lifetime longer than the value it refers to
- liftetime ends when ownership is passed

### Threads in Rust
- std::thread::spawn function
- use a handler to wait for the thread to finish
    - let handle = thread::spawn(|| {})
    - handle.join().unwrap()
- unsafe {} syntax
- threads take the ownership of a value if it passed into a thread (channels?)

### Message passing in rust
- share memory by communicating
- std::sync::mpsc
- transmitter(tx) and receiver(rx)
- let (tx, rx) = mpsc::channel, returns a tuple with two elements
- clone the tx but cant clone the rx, only one receiver

### Mutex
- mutual exlusion primitive used for protecting shared data
- Mutex<T>
- two methods, lock and unlock
- lets you mutate the data in the Mutex
- 