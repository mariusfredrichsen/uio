# Web Data Management

### Web overview
- public indexable, there is a lot of websites, more than 25 billion static HTML pages and over 53 billion pages in dynamic web
- deep web contains over 500 billion documents
- search engines
- sites changes rapidly

### Properties of web data
- lack of schema, semi-structures
- volatility, changes frequently
- scale, how do you captures "everything"?
- querying is diffucult, user language, primitives, metasearch or search engiens?

### Web models
- web graph, pages are nodes, and links to pages are edges

### Search engine architecture
- couple of crawlers
- page repository, indexer module, collection analysis modeule
- huge index of pages
- query engine with ranking 

### Crawler
- visit the most important sites first
- ranking, static and dynamic
- how to pick the next page to visit
- incermental crawlers, revisit pages that change frequently
- focused crawler, search for perticular topic
- parallel crawlers, multiple crawler visting in parallelle, use a controller to manage them so they dont overlap

### Indexing
- structure index, link structure
- text index, suffix arrays, **inverted index**, signature files
- difficulties, the web is huge, hard to maintain due to rapid changes, storage vs performance 

### Web querying
- hard to find information with keywords
- question answering system, natural language
    - find answers too
- approaches
    - search engines and metasearches
    - semistructured data querying
        - use an edge-labeled graph model of data
        - simple and flexible, fits the natural link structure of the web
        - too simple, can't be used in a complex way 
        - complicated graph
    - special web query language
        - ordered edge-labeled tree
        - complexity issue, complicated graph
    - question-answering
        - IN4120

### XML Overview
- similar syntax as HTML
- was the web standard before, but HTML took over
- structured like a tree
- **Transport**
    - publishing, database -- JDBC (Java Database Connectivity) --> web service -- XML --> Client
    - storing, client -- XML --> application -- JDBC --> database

### XML-Enabled DBS
- for XML documents
- store by shredding and publish by composing
- stores just the data
- no XML "visible" in the DB
- integrated mapping software
- lossy modelling
    - discarded after shredding

- difference between XML eneabled and Native XML
    - native stores directly in a hierarchical tree structure
    - enabled stores in a normal database with extended features for XML
        - lossy moddelling, store the data but not everything, loses data

- use XML for semistructured data when all data formats cannot be predicted in advance + large volumes of data
- XML Document Archiving when retaining XML douments
- use Native XML DB when we need to be able to retain XML documents as-is
    - semistructured data


### RDF: Resource Descriptive Framework
- A data model on top of XML
- set of triples, subject, property, object
    - subject, the enitity that is described
    - property, a feature of the entity
    - object, value of the feature

- for the semantic web
- HTML describes structure but not meaning
- has a graph representation of the features of the document
-