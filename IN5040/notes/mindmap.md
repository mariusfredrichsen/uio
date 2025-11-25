```mermaid
mindmap
    root((IN5040 Big Data))
        Data Stream Processing
            Core Concepts
                Data Streams
                    Unbounded sequences
                    Continuous generation
                Operators
                    Non-blocking: immediate processing
                    Blocking: needs all data
                Operator Graph
                    DAG structure
                    Data flows
                Performance
                    Latency: ms processing time
                    Throughput: events per time
            Windows
                Tumbling
                    Fixed intervals
                    Non-overlapping
                Sliding
                    Overlapping windows
                    Configurable slide
                Session
                    Activity-based
                    Gap timeout
            Time Management
                Event Time
                    When event occurred
                    Real timestamp
                Processing Time
                    Machine clock time
                    Operator execution
                Watermarks
                    Logical clock
                    Triggers windows
                    Handles late events
            State Management
                Operator State
                    List state
                    Broadcast state
                Keyed State
                    Key-value pairs
                    Partitioned
                State Backends
                    In-memory: JVM heap
                    Disk: RocksDB
            Guarantees
                At-most-once
                    May lose events
                At-least-once
                    May duplicate
                Exactly-once
                    Perfect processing
                    Needs checkpoints
            Fault Tolerance
                Checkpoints
                    Consistent snapshots
                    Automatic recovery
                Savepoints
                    User-triggered
                    Migration support
                Chandy-Lamport
                    Distributed algorithm
                    No pause needed
            Load Shedding
                When: overload detection
                Where: early dropping
                How: semantic or random
        Distributed Databases
            Architecture
                Client/Server
                    Centralized server
                    Multiple clients
                Peer-to-Peer
                    Equal nodes
                    Distributed control
                Multi-DBMS
                    Heterogeneous integration
                    Federated systems
            Data Distribution
                Horizontal Fragmentation
                    Distribute rows
                    Selection-based
                Vertical Fragmentation
                    Distribute columns
                    Projection-based
                Replication
                    Multiple copies
                    High availability
                    Needs consistency
            Transaction Management
                Concurrency Control
                    2-Phase Locking
                    Timestamp Ordering
                    Prevents conflicts
                Commit Protocols
                    2-Phase Commit
                    Ensures atomicity
                    Coordinator + participants
                Deadlock Management
                    Detection methods
                    Prevention protocols
                    Wait-Die, Wound-Wait
                Recovery
                    WAL logging
                    Redo/Undo
                    Checkpoint-based
            CAP Theorem
                Consistency
                    All nodes see same data
                    Strong vs eventual
                Availability
                    All requests answered
                    System responsiveness
                Partition Tolerance
                    Works during network splits
                    Fault resilience
                Trade-offs
                    Choose 2 of 3
                    ACID vs BASE
            Query Processing
                Query Decomposition
                    Break into subqueries
                    Local execution
                Query Localization
                    Map to local DBs
                    Schema translation
                Optimization
                    Cost-based models
                    Minimize data transfer
                    Adaptive execution
        Multi-Database Systems
            Schema Integration
                Schema Matching
                    Find correspondences
                    Resolve conflicts
                Schema Integration
                    Create global schema
                    Merge local schemas
                Schema Mapping
                    Map local to global
                    Data transformation
            Conflicts
                Name Conflicts
                    Synonyms
                    Homonyms
                Structure Conflicts
                    Missing attributes
                    Different relationships
                Data Conflicts
                    Different units
                    Different precision
            Architecture
                Mediator
                    Coordinates queries
                    Global schema view
                Wrapper
                    Translates protocols
                    Local DB interface
                Export Schema
                    Local exposed view
                    Shared subset
        Data Warehousing
            Characteristics
                Subject-oriented
                    Business perspective
                    User-centric
                Integrated
                    Consistent formats
                    Unified view
                Non-volatile
                    Read-only
                    Historical data
                Time-variant
                    Time-series
                    Temporal analysis
            Schema Designs
                Star Schema
                    Fact table center
                    Dimension tables around
                Snowflake Schema
                    Normalized dimensions
                    Less redundancy
                Constellation Schema
                    Multiple fact tables
                    Shared dimensions
                Fact Table
                    Measures/metrics
                    Foreign keys
                    Very large
                Dimension Table
                    Context attributes
                    Descriptive data
            ETL Process
                Extract
                    Get from sources
                    Multiple systems
                Transform
                    Data cleaning
                    Format conversion
                    Integration
                Load
                    Store in warehouse
                    Create indexes
            OLAP Operations
                Roll-up
                    Aggregate higher
                    Summarize
                Drill-down
                    Detail lower
                    Decompose
                Slice
                    One dimension value
                    Sub-cube
                Dice
                    Multiple values
                    Cross-dimensions
                Pivot
                    Rotate view
                    Reorient
            vs OLTP
                OLTP: transactions
                OLTP: current data
                OLAP: analytics
                OLAP: historical
            Data Marts
                Department-specific
                Subset of warehouse
                Specialized views
        Web Data Management
            Characteristics
                Semi-structured
                    No fixed schema
                    Flexible format
                Volatile
                    Changes frequently
                    23-40% daily
                Massive Scale
                    Billions of pages
                    Petabytes of data
                Deep Web
                    Hidden behind forms
                    Not indexed
            Web Search
                Crawling
                    Incremental crawlers
                    Focused crawlers
                    Parallel crawlers
                Indexing
                    Inverted index
                    Term to document
                    Fast lookup
                Ranking
                    PageRank algorithm
                    Link analysis
                    Relevance scoring
            XML Technologies
                XML Documents
                    Tagged elements
                    Hierarchical
                    Self-describing
                XML Schema
                    Structure definition
                    DTD or XSD
                XPath
                    Navigate XML
                    Select nodes
                XQuery
                    Query language
                    Complex queries
            Semantic Web
                RDF Triples
                    Subject-Predicate-Object
                    Graph structure
                    URIs as identifiers
                SPARQL
                    RDF query language
                    Pattern matching
                Ontologies
                    Semantic metadata
                    Concept relationships
                Linked Open Data
                    Interconnected data
                    LOD cloud
            Query Approaches
                Keyword-based
                    Search engines
                    Simple interface
                Semi-structured
                    Graph queries
                    Pattern matching
                Question-Answering
                    Natural language
                    NLP techniques
        Cloud & Scalable Systems
            Cloud Services
                IaaS
                    Infrastructure provision
                    VMs, storage, network
                PaaS
                    Development platform
                    APIs and tools
                SaaS
                    Application software
                    Hosted services
                DaaS
                    Database services
                    Managed DB
            Scaling
                Scale-out
                    Add more nodes
                    Horizontal growth
                    Shared-nothing
                Scale-up
                    Increase capacity
                    Vertical growth
                    Has limits
                Elasticity
                    Dynamic scaling
                    Auto-adjust resources
            NoSQL
                Key-Value
                    Simple pairs
                    DynamoDB, Cassandra
                    Fast lookups
                Document
                    JSON/XML docs
                    MongoDB, CouchDB
                    Flexible schema
                Tabular
                    Multi-valued attributes
                    BigTable, HBase
                    Timestamped
                Graph
                    Nodes and edges
                    Neo4J, OrientDB
                    Relationship-focused
                Trade-offs
                    Sacrifice ACID
                    Gain scalability
                    Eventual consistency
            NewSQL
                SQL + Scalability
                    ACID transactions
                    Horizontal scaling
                Examples
                    Google F1/Spanner
                    CockroachDB
                    VoltDB
            Polystores
                Loosely-coupled
                    Mediator-wrapper
                    Autonomous stores
                Tightly-coupled
                    Unified query language
                    Data movement
                Integration
                    RDBMS + NoSQL
                    HDFS access
            Big Data Processing
                MapReduce
                    Batch processing
                    Map and Reduce phases
                    Parallelizable
                Spark
                    In-memory processing
                    Faster than MapReduce
                    Supports streaming
                Hadoop
                    HDFS storage
                    YARN resource manager
                    Hive, Pig tools
            Data Lakes
                Schema-on-read
                    Flexible interpretation
                    Query-time schema
                Raw Data
                    No transformation
                    Original format
                Multi-workload
                    Batch, interactive, real-time
                    Cost-effective
        Machine Learning Integration
            ML Pipeline
                Pre-ML
                    Data discovery
                    Data cleaning
                    Data labeling
                In-ML
                    Feature extraction
                    Model selection
                    Training acceleration
                Post-ML
                    Model storage
                    Versioning
                    Deployment
            Data Preparation
                Discovery
                    Find sufficient data
                    Attribute/tuple level
                Cleaning
                    Missing values
                    Outliers
                    Duplicates
                Labeling
                    Crowdsourcing
                    Active learning
                    Weak supervision
            Training
                Feature Extraction
                    Batching
                    Materialization
                    Pruning
                Model Selection
                    Choose parameters
                    Optimize performance
                Acceleration
                    Parallelism
                    GPU/TPU
                    Distributed training
            Model Management
                Storage
                    Column-store
                    Compression
                    Indexing
                Versioning
                    Track variants
                    Metadata logging
                Deployment
                    Low latency
                    High throughput
                    Model serving
                Debugging
                    Data debugging
                    Model diagnosis
            ML Types
                Supervised
                    Classification
                    Prediction
                    Labeled data
                Unsupervised
                    Clustering
                    Association
                    No labels
                Descriptive
                    Pattern discovery
                    Data exploration
                Predictive
                    Future outcomes
                    Inference
        Cross-Cutting Concerns
            Performance
                Latency
                    Response time
                    Milliseconds
                Throughput
                    Events per time
                    System capacity
                Parallelism
                    Pipeline parallelism
                    Data parallelism
                    Task parallelism
            Fault Tolerance
                Checkpointing
                    State snapshots
                    Recovery points
                Replication
                    Multiple copies
                    High availability
                Recovery Protocols
                    Restore consistency
                    Redo/Undo logs
            Security & Privacy
                Encryption
                    Data protection
                    At rest and transit
                Access Control
                    Authentication
                    Authorization
                    RBAC, ABAC
                Privacy
                    GDPR compliance
                    Differential privacy
                    Anonymization
            Data Quality
                Veracity
                    Trustworthiness
                    Accuracy
                Profiling
                    Understand structure
                    Content analysis
                Validation
                    Check constraints
                    Data integrity
                Enrichment
                    Add external data
                    Enhance value
            Query Optimization
                Cost-based
                    Estimate costs
                    Statistics
                    Cardinality
                Heuristic
                    Rule-based
                    Best practices
                Adaptive
                    Runtime adjustment
                    Dynamic plans
            Indexing
                B-tree
                    Range queries
                    Sorted data
                Hash
                    Equality lookups
                    Fast access
                Bitmap
                    Low cardinality
                    Efficient storage
            Storage
                Column Store
                    Analytics workloads
                    Parquet, ORC
                    Compression-friendly
                Row Store
                    Transactional
                    Traditional RDBMS
                    Row-level operations
        Big Data Characteristics
            Five Vs
                Volume
                    Petabytes to zettabytes
                    Requires distribution
                Variety
                    Multimodal data
                    Structured/unstructured
                    Handled by NoSQL
                Velocity
                    High-speed streams
                    Real-time processing
                    DSP systems
                Veracity
                    Data quality
                    Trustworthiness
                    Needs cleaning
                Value
                    Extract insights
                    ML and analytics
                    Business value
```