# Data(base) integration Multi-Database Systems (MDBS)
- Distribution
    - 
- Autonomy
    - self-sufficient systems
- Heterogeneity
    - we have different types of databases and softwares
    - enable it so it can cooperate

- each of these factors depend on the data, application, goal, the resources we have
- we are always distributed

### Interoperability problem
- application level, non-database sources -> Data stream processing systems and data lakes for example
    - data lakes are for data analysis
- database level, bottom-up design process -> multi-database systems and data warehouses for example
    - data warehouses are also for data analisys
- data exchange -> web data management
    - unique, but important, how do we exchange the data between the systems?



### Integration Alternatives
- Physical integration (OLAP) ->
    - Data warehouses, data lakes
    - ETL - extract-transform-load
    - multi-dimensional DB
- Logical intergration (OLTP) -> 
    - Multi-DBS (MDBS)
    - Global conecptual schema is virtual and not meterialized
    - Data control and availability, multi-user, high throughput



### Heterogeneous / Federated / Multi-Database Systems (MDBS)
- you cant ensure full controll over the whole system, each system is in a way autonomouse and heterogeneouse
- the different systems must be integrated in a way


### Applications
- different departments with each of their own databases
- we are working with different type of data so therefore we need different types of systems
- global query over the different datas in the different database systems


### Requirements for Multi DBS
- Integration of heterogeneous DBSs
    - queries across the MDBS
    - hetereogeneouse informatiuon structures
    - avoid redundency
    - access (query) language transparency

- "Open" system
    - be open to adapt or integrate new systems

- constraints
    - retain autonomy of DBSs to be integrated
    - not allowed to change the underlying systems
    - define a viable global data model for global applications



### Database Integration - Multi-DBS Architecture
- Users ask queries
- MDBS Layer handles the query in a way that get the date from the different database system components
- the more autonomous the systems are the harder the task of cooperating is

#### Federation
- define export schemas that says something about where the data 
- also have a metadata database on a global level
- the unhandled data is never stored in the global integration layer
- schemas are handled in the global layer

#### Fully autonomous
- schmeas are handled in the local systems/layers
- the quelity of the results might not be good



### Integration Layer
- Global data model
    - local data models, any kind
    - global data model, must comprise modeling conecepts and mechanisms to express the feature of the local data models
- Global schema and metadata management
- Distributed query processing and optimization

- primarily read-only queries/transations at the global level
- local level can do everything, but unknown ot the integrations level


### Integrations process
- schema translation
- schema generation, 3 steps
    1. schema matching, syntax correction and semantics, cant be automized
    2. schema integration, add the local schemas into a global schema (conceptual?)
    3. schema mapping, ????
- query rewriting and processing, after schema generation we can create the relation between data
- query optimization


#### Bottom-up design
- problem: ???


### Query planning and optimization in a distributed Multi-DBMS
- global query ->
- localized multi DB query 1 -> 
- query fragmentation and global optimization -> 
- multiple SQ 1..n -> 
- query translator 1..n -> 
- TQ 1..n ->
- DB 1..2 

#### Issues in the query processing
- different computng capebilities
- different processing cost and optimization capabilities
- different data models and query languages
- different runtime performance and unpredictable behavior

#### Components in DBMS Autonomy
- Communcation
    - ability to terminate services at any time
    - how should the query be answered?
- Design
- Execution



#### 