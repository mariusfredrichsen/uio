# Data Management for Data Analysis, Data Warehouses

### ML Techniques 
- Supervised, training data, classification, estimation and prediction
- Unsupervised, no training data, clustering, summarization, association
    - unknown classes

### ML Types
- Descriptive
    - charectarize the general data properties in the DB
    - finds a pattern in the data
    - the user determines which ones are important
    - used during exploration
    - functionalitites of descriptive data mining:
        - clustering
        - summarization
        - visualization
        - association

- Predective
    - create a function to determin the results and make a prediction based on some data

- Not mutually exclusive
    - used together
    - descriptive --> predictive

### Black-box model
- f(x) is super complex and is unknown
- only interested in results, accuracy

### Data analysis required
- DB schema must support complex and non-normalized data
- Data extraction and filtering:
    - need advanced tools
    - allow batch/scheduled data extraction
    - support different types of data sources
    - check for inconcistency

### Overview - Data management for ML
- get raw data and prepare it for training
- do the actual training    
    - feature selection, model selection
- model management, manage the machine learning with the user interface

### Challenges for managing the ML
- enough high quality training data, human expansive to aquire
- large amount of training data, need end to end data analysis
- need to train many models and hard to manage in a real application

### ML Pipeline
- get data, filter, prepare, standardize, features, train, split data
- data analysis, set a goal, data selection
- data preprocessing, 80% of the work, good data warehouse needed
- data reduction, find useful features, "compress"
- data mining, choose functions, choose algorithm, search for patterns of interest
- presentation, visualization, transofmration
- taking action, documenting, incorperate into the performance system

- data preperation (80% of the work including result reporting)
    - data warehouse preperations
        - data selection and extraction
        - data cleaning
    - additional preperation for data analysis
        - data enrichment
            - integrating additional data from external sources
            - can be a problem because the data might make the database more heterogenous
            - must also understand the external data
        - data coding
            - goal: streamline the data for effective and efficient processing by the target data analysis application
            - delete records with many missing values
                - fraud detection is a exception
            - delete extra attributes, customer names is useless for example
            - code detailed data values into ranges or categories
- data mining
- result reporting


### OLTP vs OLAP
- OLTP: On-Line Transation Processing
    - operational DBS, short time horizon
        - real-time data, short time frame
        - specific transactions that occur at a given time
        - the data is isolated, atomic transationcs
    - optimize throughput (TAs / time unit)
    - large number of short online TA (TransActions)
    - ACID transactions
    - Mostly updates
    - many small transactions
    
- OLAP: On-Line Analytical Processing
    - Data Warehourse, long time horizon
        - historic data, long time frame
        - different levels of aggregation
        - the data is related in many ways, larger picture
    - Optimize response time
    - read-only data/queries ( 1-n queries / minimal time )
    - complex queries
    - store aggrigated, historical data in multi-dimensional schemas
    - DW data latency: few hours, Data Marts: 1 day
- time span, granularity, dimensionality

### Where SQL stops short
- Need tools as support, use everything to be successfull in data analysis
- ML tools, creates new knowledge in form of predictions, classifications, assiociations and time-based patterns
- OLAP tools, analyzes a multi-dimensional view of existing knowledge
- DA-extended SQL Queries, extends the database schema to a limited, multi-dimensional view
- Complex SQL Queries, database schema describes the structure you already know

### Datawarehouse characteristics
- subject oriented, data are organized based on how the users refer to them
- data integrated, all inconsistencies are removed, cleaned up?
- non-volatile, read-only data unless there is a update or refresh
- time variant, time series (not real-time data)
- organized under a unified milti-dimensional schema
- OLAP
- summarized, operational data are mapped into a decision-usable format
- large volume
- not normalized, data can be and often are redundant
- metadata, data about stored data
- data sources, comes from internal and external un-integrated operational systems

### Datawarehouse architecture
- query-driven (lazy)
    - clients -->
    - mediator -->
    - sources --> wrappers -->

    - no need to copy data
    - no need to purchase data
    - more up to date
- Datawarehousing (eager)
    - clients --> query & analysis -->
    - warehouse --> metadata 
    - sources --> integrations -->

    - high query performance
    - queries not visible outside of datawarehouse
    - can operate when sources are not available
    - can query data not stored in a DBMS
    - added historical information

### Data marts
- smaller data warehouses
- has a specific usecase
- doesnt have a enterprise-wide consesus, but might bring long term integration problems?
- stores a second copy of whats inside the data warehouse
- gives better performance by querying through less data
- costs more since its more hardware and software and need to define a subset of global data model
- supports a better user interface
- more reliable access control

### DW models & operators
- data models
    - relations
    - star schema
        - Fact --> Dim1, Dim2, Dim3, denormalized
    - snowflake schema 
        - Fact --> Dim1(Dim1.1), Dim2(Dim2.1), normalized (because its splitted into smaller dimensions)
    - starflake schema
        - combination, dimensions can be normalized or denormalized
    - constellation schema
        - fact table --> dim1 --> fact table
        - stores the relations between two fact tables
    - data cubes (summary tables)
        - stores precomputed queries to make usual queries go much faster, changes with the usual queries
            - typical "measures" are sum, percentage, average, std deveiation, count, min-value, max-value, percentile
        - stores denomarlized data
        - aggregate data from one or more fact table / one or more dimension table
         
- operators
    - slice & dice
    - roll-up, drill down
    - pivoting
    - other

- Global schema design - base tables
    - facts table 
        - stores facts from the source database
        - data about past events
        - time period included most likely
        - very large table (up to 1 TB)
    - dimension tables
        - contains attributes of one dimension of the facts table
        - can describe another dimension table
        - the data values can change while the fact table doesn't change that often
    - fact table primary keys point to dimension tables foreign/primary keys
    - summary tables (datacubes)

- Design mehtlogoies
    - top-down
        - start with datawarehouse and build data marts as they are required
    - bottom-up
        - start with the data-marts and create a datawarehouse based on the data-marts 

- when refreshing/updating the datewarehouse we recalculate the different measures with the new data included
- when updating or refreshing it basically shutsdown the query processing for the datawarehouses
- approaches for monetoring data sources
    - value-deltas, capture before and after of all tuples being changes by normal DB operations and store them in differential relations 
        - need to go offline
    - operations-delta
        - capture all sql updates from all transactions logs of each source and build a new log of all logs that effect the data in the datawarehouse
            - dont need to go offline since we can use concurrency
        - smaller than value-deltas
        - not always possible to transofrm the operation on the source to an update to the datawarehouse
    - hybrid, use both 
    - creating a differential relation
        - execute and update 3 times
            - select and record the values
            - do the update
            - select again and get the after values
            - costs a lot in time and space
        - define and use DB triggers
            - triggers on insert, delete and update, and log each before adn after values
            - not all data sources support triggers so reduces the possiblity for autonomy
        - delta tables
            - two processes
                - propagation, pre-compute all new tuples and replcement tuples and store them in the delta table, dw is online
                - refresh, scan the dw tuples, replace existing tuples with the pre-computed ones, and insert new tuples, must go offline 

### Data lakes
- collection of raw data
- based on hadoop software, managing many different data sets
- advantages
    - schema on read
    - multi-workload data processing on the same data
    - cost-effective data architecture, opensource and high return of investment with SN cluster
- principles
    - collect all useful data
    - dive from everywhere
    - flexible access, different access paths to the same infrastructure
        - batch, interatctive (OLAP and BI), real-time, search, ...
- store and process large amounts of data
- data access, interactive, batch, real time, streaming



### Data lake vs data warehouse
| Feature | Data Lake | Data Warehouse |
|---|---|---|
| Data sources | Heterogeneous — various raw data sources | Physical DB integration — consolidated operational sources |
| Typical workloads | Multi-workload: batch, streaming, interactive, ML experiments | OLAP / analytics / ML (structured OLAP workloads) |
| Development time | Shorter development process | Long development process |
| Storage model | Stores raw objects (id, metadata, original data) | Structured relational storage with predefined schema |
| Transformation approach | No forced cleaning/transformation — schema-on-read | Complex ETL (extract, transform, load) — schema-on-write |
| Query processing | Query processing & optimization more difficult | Query processing efficient and optimized |
| Consistency & quality | Weak by default — challenges: metadata, data quality, consistency, integration, governance, security | Stronger DB consistency, governance and security controls |
| Adaptability | Cost-effective, easy to adapt & extend to changes | Difficult and costly to adapt; schema/data updates are hard |
| Pros | Flexible, low-cost entry, supports many workloads | Predictable performance, consistent schema, optimized queries |
| Cons | Harder metadata management, quality, governance, and QP | Complex ETL, long lead times, costly to change |


