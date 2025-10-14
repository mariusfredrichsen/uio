# Mandatory assignment 1


## Task 1
```
find(d) {
  i := head; # variable i is local to the current method instance
  while (i != null and i.val != d) { i := i.next; }
  # return i
}
```

```
insert(new) {
  if (tail == null) { # empty list
    head := new;
  } else {
    tail.next := new;
  }
  tail := new;
}
```

```
delfront() {
  if (head != null) { # list not empty
    if (head == tail) { tail := null; } # only 1 elem. in list
    head := head.next;
  }
}
```

### (A)
- V(find(d)) = {head, i.val, i.next}
- W(find(d)) = Ø

### (B)
- V(insert(new)) = {tail, head, new, tail.next}
- W(insert(new)) = {head, tail.next, tail}

### (C)
- V(delfront()) = {head, tail, head.next}
- W(delfront()) = {head, tail}


## Task 2
### a
**find & find:**<br>
W_A disjoint V_A = Ø<br>
V_A disjoint W_A = Ø<br>
Ø union Ø = **Ø**<br>

**find & insert:**<br>
W_B disjoint V_A = {head}<br>
W_A disjoint V_B = Ø<br>
{head} union Ø = **{head}**<br>

**find & delfront:**<br>
W_C disjoint V_A = {head}<br>
W_A disjoint V_C = Ø<br>
{head} union Ø = **{head}**<br>

**insert & insert:**<br>
W_B disjoint V_B = {head, tail.next, tail}<br>
V_B disjoint W_B = {head, tail.next, tail}<br>
{head, tail.next, tail} union {head, tail.next, tail} = **{head, tail.next, tail}**<br>

**insert & delfront:**<br>
W_B disjoint V_C = {head, tail}<br>
W_C disjoint V_B = {head, tail}<br>
{head, tail} union {head, tail} = **{head, tail}**<br>

**delfront & delfront:**<br>
W_C disjoint V_C = {head, tail}<br>
V_C disjoint W_C = {head, tail}<br>
{head, tail} union {head, tail} = **{head, tail}**<br>

Find() combined with find() does not create an interference since it has the AMO property. It does not share any write or read variables so they can run concurrently.

However as for insert() and delfront(), there doesn't exist a combination where they do not share write or read variables. This means they cannot run concurrently with any other (including itself) processes.

find() in general does not create any interference since it upholds the AMO-property, it only reads from the global variable. While for insert() and delfront() they create interferences since they do not uphold the AMO-property, they write and read from the global variables.

### b
Based on the last task then every combination of processes should be done one at a time. 
**find & insert:**
- find; insert;
- find should return null if insert inserts d AFTER executing find
- Might return d if insert is faster than find can read head

**find & delfront:**
- find; delfront;
- find should return d if delfront deleted the head/d AFTER executing find
- Might return null if delfront is faster than find can read head

**delfront & insert:**
- delfront; insert;
- delfront should remove the head and insert should insert a new head AFTER delfront has deleted
- (case for 1 element) delfront updates head/tail to be null while insert has already read head to be not null and tries `tail.next := new` but ends up with a nullpointer exception because tail is null.

**find & find:**
- does not have any interfernece since it follows the AMO property for routines.

**insert & insert:**
- insert1 and insert2 reads head to be null
- insert1 writes to the head variable
- insert2 writes to the head variable, overwriting the previous

**delfront & delfront:**
- delfront; delfront;
- single node linked list.
- both delfront1 and delfront2 enters into the if-check (head != null) successfully
- delfront1 sets head to null before delfront2 does
- delfront2 executes `head := head.next` after head is set to null
- delfront2 crashes since null does not have a `.next` property
- as for the sequencial version, the node would be removed by delfront1 and delfront2 couldn't enter the first if-check

