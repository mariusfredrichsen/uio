# Large databases and performance

### Poor performance
- poor performance in databases is often the main reason there is poor system performance

### Why is there still a problem?
- hardware is still getting faster
- software is getting slower and slower
- the speed of hardware improvement hides the bad craftmanship in softwares
- we can tune the software, but that is a 40h introduction course and 5 years of experience
    - indices, effecient SQL and code & design
- divide and conquer and minimise the fetch of everything

### Tuning
- you can read pages in the database in bigger batches
    - reading 50 pages per batch is 30ms while 10 pages per match is 10ms per
    - there is an increase
- use indexing to speed up the search
- remove the unnessacary data before it is sent to the cloud or service

### Concurrency
- need to consider isolation level and clustering in a table
- "isolation levels are about SHARE LOCKS (read locks), how many locks to take, and how long to hold these locks"
- Repeatable Read
    - keeps all SHARE LOCKS till COMMIT (use only in very special cases)
    - doesnt let any inserts into a table that is being read
- Read Stability
    - locks the qualifying rows
    - keeps all SHARE LCOSK till COMMIT (use only in very special cases)
- Cursor Stability
    - frees SHARE LCOK as it works through the data, doenst wait untill COMMIT (is the one isolation level to use, except for very, very special cases)
- Uncommited Read
    - anarchy?
- Lock modes
    - S (Shared), select,  fetch, some open cursor
    - X (Exclusive), any sql that modifies data, insert, update, delete, merge
    - U (Update), fetch for updates, promoted to an X lock when the data is modified, helps prevent deadlocks

| Lock Modes | **S** | **U** | **X** |
| ---------- | ----- | ----- | ----- |
| **S** | OK | OK | NOK |
| **U** | OK | NOK | NOK |
| **X** | NOK | NOK | NOK |

- **Clustering**
    - its important to cluster the data in a smart way so you dont end up with deadlocks or waiting
    - if all processes is working on the same page in the database then the risk for deadlocks rises
    - should deadlock to seperate them so they dont lock the pages

### New directions: SQL accelerators - divide and conquer
- lots of CPUS with their own disc rack
- tables are distributed (striped) over a large number of disc racks