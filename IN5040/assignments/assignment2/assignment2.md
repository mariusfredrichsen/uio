# Assignment 2 
## Question 1. Text – DSPS (~1 page)
**a)** Explain the motivation for Data Stream Processing Systems (DSPS).

**b)** Elaborate on the differences between a traditional DBS and DSPS.

**c)** Which applications make use of DSPS?

---

## Question 2. Text – DSPS (~0.5 page)
**a)** What is a window in DSPS and what are windows used for?

**b)** What is the difference between tumbling and sliding windows and what are their advantages and disadvantages?

---

## Question 3. Text (~0.5 page) Complex Event Processing
**a)** What do the `every` and `followed-by (->)` patterns do?

**b)** Explain the difference between the following:
- `every A -> B`
- `A -> every B`

---

## Question 4. Text – Distributed DBS (~1 page)
**a)** A centralized DBS stores all data and processing on a single system, while a distributed DBS spreads data and processing across multiple interconnected sites or nodes.

**b)** Distributed DBS are needed to improve reliability, availability, scalability, and to support geographically distributed users and data sources.

**c)** Advantages: improved fault tolerance, scalability, and local autonomy. Disadvantages: increased complexity, potential consistency issues, and higher communication overhead.

**d)** Distributed DBS solutions can be classified by: 
1. Distribution transparency (how much the distribution is hidden from users)
2. Autonomy (degree of independence of each site)
3. Heterogeneity (differences in hardware, software, or data models among sites)

**e)** Approaches include:
- Homogeneous distributed DBS (same DBMS at all sites)
- Heterogeneous distributed DBS (different DBMSs)
- Federated DBS (loosely coupled, each site retains control)
- Client-server and peer-to-peer architectures

---

## Question 5. Text – Database Integration (~1 page)
**a)** Challenges include schema heterogeneity, data model differences, data quality issues, semantic conflicts, and maintaining consistency across sources.

**b)** A heterogeneous MDBS integrates multiple, diverse databases. It provides a unified interface, translating queries and results between the global schema and local schemas.

**c)** The integration layer maps and translates between global and local schemas. Issues include schema mapping, query translation, data consistency, and handling updates across sources.

---

## Question 6. Text – Web Data Management (~1 page)
**a)** Problems: data heterogeneity, scale, and unstructured data. Approaches: crawling, indexing, ranking algorithms, and query processing techniques.

**b)** Approaches for Web Data Exchange include using XML/JSON, schema mapping, data transformation, and mediation systems.

**c)** Challenges: data variety, quality, and dynamic sources. Approaches: data integration frameworks, entity resolution, and semantic web technologies.

---

## Question 7. Text – ML Data Management & Data Warehousing (~1 page)
**a)** Challenges: data collection, cleaning, labeling, versioning, and reproducibility. Tasks: data preprocessing, feature engineering, and pipeline management.

**b)** Knowledge Discovery in DBs stages: data selection, preprocessing, transformation, data mining, and interpretation/evaluation.

**c)** DW Life Cycle: requirements analysis, design, implementation, deployment, maintenance, and evolution.

**d)** DW design schemas:
- Star schema: simple, fast queries, but can have data redundancy.
- Snowflake schema: normalized, reduces redundancy, but more complex queries.
- Galaxy (fact constellation): supports multiple fact tables, but increases complexity.

---

## Question 8. Text – Scalable Data Management (~1 page)
**a)** Design for scalability by partitioning data, using distributed storage/computation, and leveraging parallel processing frameworks.

**b)** Challenges: resource management, data consistency, and fault tolerance. Approaches: virtualization, elastic scaling, and managed services.

**c)** CAP Theorem: a distributed system can only guarantee two of Consistency, Availability, and Partition tolerance. NoSQL solutions:
- CP (e.g., HBase): consistency and partition tolerance
- AP (e.g., Cassandra): availability and partition tolerance
- CA (rare in distributed systems)