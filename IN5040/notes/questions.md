# Lecture 1: Big Data
## 5 Related challenges
cant be bothered

# Lecture 2: Data Stream Processing (DSP)
## DSP Applications
1. a data stream is a continuouse stream of data that is being handled on the spot. Instead of storing all the data then writing queries, we do the queries straight away. 

2. IoT, sensors and mobile games

3. Because IoTs has a lot of data and it might be too much to handle at once but better to handle it when it actually happens. 

4. DSP is about taking one and one event at a time or a tuple at a time while batch processing takes a bulk of tuples and computes them in a bulk. DSP works with windows while batch processing is classic sql like.

5. It is sometime required to see real time updates in the data so we need low latency to achieve that.

## DBS vs DSP
6. im not sure, DBMS vs BSMS
    - DBMS stores bounded data and runs queries on demand
    - DSMS handles unbounded data streams and runs continous queries

7. because stream processing happens in the main memory and the amount of data its made to handle cant store it all on the main memory at once.

8. unbounded data is when the data can come forever, no end to the data retrieval

9. the data is sent i packets and may take different routes when arriving to the DSP node. Therefor we need to handle the disorder of the incoming data

10. im not sure, why is memory management more difficult in DSP?
    - the stream is unbounded
    - avoid storing all data
    - keep state small and bounded with windows

## DSMS vs CEP
11. im not sure, what is DSMS?
    - Data Stream Managemenet System
    - focuses on continour queries, windows and aggregation over streams

12. CEP is about computing events coming in a stream and finding matching pairs based on a query or a rule

13. im not sure, difference between DSMS and CEP
    - DSMS, continuous queries
    - CEP, detects patterns, sequences and complex event relationships

14. you want to find an event that occurs with specific rules like wheater has to be like this for 3 days or something like that

15. CEP focuses more on pattern detection

## Windows

16. a window is a defined space to let us know when we are going to do blocking operations

17. because we have blocking operators that needs to wait for all the data to arrive but we cant so we work in windows of the data

18. sliding, tumbling and session windows

19. tumbling windows is a fixed size in either length or time and doesnt have overlap but covers all the data

20. sliding windows has a slide parameter which tells it how much it should skip in the stream, it can contain overlapping elements in different positions of the window

## Time & Watermarks
21. event time is when the tuple was generated or sent

22. processing time is when the tuple arrived or was computed

23. late events might be sent in different routes when traversing the network of nodes

24. watermarks is a mechanism to tell the stream processor to ignore values filtered out by the watermark placed on the stream
    - ignore older events

25. im not sure, watermark "advances"?
    - watermark advances when the system believes that events older than a certain timestamp have mostly arrived

## Queries
26. a contunous query is a ready-only query which adapts to the continous stream

27. its harder to change a continous query and they are meant to last a while

28. if we stop them then we have a stop in the system and risk not having real time data

29. im not sure, stateless operator?
    - does not store any past information, filtering

30. im not sure, stateful operator?
    - keeps information to compute with later information, aggregations, joins, windows operations

## Load shedding
31. Load shedding is when we drop data when we have a overflow of data

32. we cant handle all the data and have a long queue of data we need to compute

33. we might drop important data

34. when we have too many patients waiting in queue to for a doctors appointment and the waiting room is too small. we then need to focus on the most important or the ones that actually can be attended to by the doctors

35. yes

## State management & reliability
36. im not sure, state in DSP?
    - keeps information over time

37. im not sure, state saves regularly?
    - avoid data loss if the system crashes

38. im not sure, checkpoint?
    - snapshot of the running state used for fault recovery

39. im not sure, savepoint?
    - manually created checkpoint that is kept permanently and can be used for updates or debugging

40. we need recovery mechanisms to make sure we dont lose data or lose progress

# Lecture 3: Distributed Database Systems
## Distributed Computing
1. distributed computing is deligating computational tasks to other nodes

2. to enable the possibility for parallelle computing which is faster

3. a node is a computer or a machine or a network connected with other computers

4. im not sure, what is a cluster?
    - a group of nodes that work together as a single system

5. it lets you store data in different places and allows for parallellisation
    - high availability too

6. its more complex and harder to manage than a centralized system

7. network latency is the time it takes for a computer to compute after receiving a task or data
    - the time data takes between sending data over the work and receiving it

## Distributed DBS
8. distributed database systems is when we spread the storage of the different datas on a network

9. it makes it faster by allowing parallell computing and easier to store data on smaller nodes than having a huge node. It is also more tolerant to failures by having copies stored on multiple databases

10. im not sure, what is fragmentation?
    - splitting table into pieces to distribute them across nodes

11. replication is a copy of data already existing in the system

12. it acts like a backup for when the main system fails
    - read scaling
    - geographic distribtuion
    - high availability

13. if replications are not updated we might fall back on old data 

## Distributed DBMS
14. distributed DBMS is to connect different databases and make them able to coperate with eachother without many errors

15. im not sure, location transparency?
    - user dont know where the data is stored physically

16. im not sure, replication transparency?
    - hides the fact that copies exist

17. im not sure, fragmentation transparency?
    - hides how tables are split across nodes

18. transparency is to make the user focus more on the task rather then the database itself to make it seamless to work with

## Classification of Distributed DBMS Alternatives
### Distribution
19. horizontal fragmentation is when adding more and more nodes to a system, more storage by increasing the number of nodes. storing the same data but on multiple nodes
    - splits rows across sites

20. vertical fragmentation is when the main strategy is to increase the storage capacity of a single node to be able to store more of the same data on the same system
    - splits columns across sites

21. when you combine vertical and horizontal fragmentation where you increase the storage of the same data on the same node or spread it out more on multiple nodes

### Autonomy
22. autonomy means how much the database in the system shows its structure, how exposed it is to the bigger system

23. local autonomy is when its not exposed to anything except the local network it is locaed on and not the global one

24. federated databases is meant to be isolated from eachother but we still want to manage them from a higher level but not on the same level

25. full autonomy leads to unpredictable behaviour and is harder to calculate or optimize queries

### Heterogenity
26. heterogenity is about how different the data is, how the data is stored, how the data is precomputed

27. schema might contain a column about temperature but it might be stored as either celcius or fahrenheit

28. im not sure, what is a data model?
    - relational, object, graph, document

29. heterogenity is challenging because we have to do manual work to double check if the databases can work as they are or need to have a form for wrapper to do ETL work

### Modern alternatives
30. shared-nothing architecture is when a dabase management system has nodes that do not share anything with eachother and is easy to scale up since they dont share anything

31. im not sure, what is a shared-disk architecture
    - share the same physical disk but have their own cpu and memory

32. shared-nothing scales better

## Client/Server architecture
33. a client is the user of the database system, the computer that sends the query to the server computer

34. a server is the one storing the data and doing the queries on the stored data

35. the server stores data and queries on the data

36. thin client is a client that does very little computing of the whole task

37. thick client is a client that does half of the computing of the whole task

### Relational
38. a table is rows and columns where rows are datapoints while columns are data types

39. a relation is about foreign keys and primary keys to show the relations between the datapoints

40. rows are either stored partially on different nodes or stored fully on different nodes

### Object-Oriented
41. im not sure, what is a object in a object oriented database?

42. im not sure, how is an object different from a relational row?

## Multi-DBS Components & Execution
43. im not sure, what is Multi-DBS?

44. im not sure, why do organizations use multi-DBS?

45. global schema is a representation of how the schema should look on a global level for the managing layer of the multi-dbs

46. local schema is the schema of the smaller dbs in the multi-dbs system

47. wrappers transform the data to a format of the global schema

## Mediator/Wrapper architecture
48. im not sure, what is a mediator?

49. a wrapper is a component that takes the data from a dbs and transforms it

50. we need wrappers to create consistency in the dbs

51. the wrapper

52. the mediator

## Distributed DBMS Functionaility
### Design
53. im not sure, what is schema fragmentation

54. im not sure, what is scheam allocation?

55. im not sure, why is placement important?

### Directory/Catalog Management
56. im not sure, what does a catalog store?

57. metadata stores important information about the location of the data

### Query Processing & Optimization
58. Query optimization is about optimizing the query in a way that lets us reduce the latency by doing parallell computing and maybe distributing different tasks to different systems based on their statistics

59. the sub systems can be autonomouse and hard to make good analysis

60. im not sure, what is a query execution plan?

61. network cost is important because it takes time to transfer the data back and forth so we need to be careful not distributing big tasks to slow systems for example

### Transaction management
62. a transaction is a dbs query that guarentees the ACID mostly

63. ACID stands for Atomicity, Consistency, Isolation and Durability

64. ACID is important to keep the database up and running as it should

65. locking is when not letting other threads access data when written to or read and not letting written to

66. we need locking to avoid dirty reads or overwriting data

67. im not sure, what is two-phase locking?

68. im not sure, what is 2-phase commit protocol

69. im not sure, what is the coordinator?

70. im not sure, why can 2pc block?

71. replica control is making sure that we have mutual consistency across the system 

72. we need it to make sure we dont store old data on replicas

73. eventual consistency is when the replicated data is used then it should be consistent, not necesseraly all the time

74. strong consistency is when we at all time nodes points to the same data



# Lecture 4: Database Integration & Multi-Database Systems (MDBS)
## Interoptebility problem
1. It means the level of cooperating and working together seamlessly in a system

2. heterogeneous systems has a lot of different datas stored, different schemas, different hardware and softwares which makes it harder to make it work together

3. schema mismatch is when two schemas dont match together by either having different names but represent the same, or have the same name but represented in different units

4. if the datatype mismatch then we need to translate it some way which takes more time and careful handling so it isnt done wrong

5. because each database has different purposes and different goals so they may have different functions

## Integration Alternatives
6. thight coupling is when systems are so coupled that data can passe between the systems

7. loose coupling is when data is not transfered between systems

8. loosely coupled is easier since we dont need to match the schemas

9. loosely coupled gives more autonomy since they dont need to be able to receive or send data to other systems

## Multi-DBS Architecture
### Fedration, Partially Autonomous
10. federated database systems is database systems that are in silo format, the database systems inside of the multi database system is working more independently and dont share anything with the other systems

11. the integration layer has 