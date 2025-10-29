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
- 