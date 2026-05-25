# Static techniques
- not a replacement for dynamic testing
- automated checks on the code

## 3.1 Reviews and the test process
- **static testing**, software products are examined manually or with tools, but not executed
    - defects found: deviations, missing requirements, design defects, non-maintainable code, inconsistent interface specifications
- **dynamic testing**, software is executed using a set of input values and its output is examined and compared
- **reviews**, informational, communicational and educational
    - learn about their own role
    - users can also join to give feedback

## 3.2 Review process
- **informal review**, applied early in the life cycle, review of code, not documented
- **formal review**, often bigger meetings, later in the process, legality, not documented
- phases of reviews:
    1. Planning
        - author requests a review to the moderator
        - **entry criteria**, formal reviews are often checked before the review to check if its worth other colleagues time
        - define document size to be reviewed, limited by human limits and experience and what the objective is
        - the moderator gives roles to each reviewer to foces on:
            - type 1: higher-level documents, design and requirements
            - type 2: standards, internal consistency, clarity, naming convections
            - type 3: related documents, interfaces between software functions
            - type 4: usage, testability or maintainability
    2. Kick-off
        - optional meeting
        - entry check and exit criteria is defined
        - effective, measured 70% more major defects in the book
        - role assignment, checking rate, pages to be checked can be defined in this kick-off meeting
    3. Preperation
        - creating checklists
        - spelling mistakes are only noted and not brought up in the meeting
        - logging of the meeting that is given to the author at the end
        - pages checked per hour varies depending on type of document, complexity, related documents and experience of the reviewer
    4. Review meeting
        - logging phase:
            - defects that have been identified during preperation
            - a scribe is useful expecially in more formal reviews
            - dont discuss if a defect is a defect, just move on
            - level of secerity:
                - **critical**, will damage other documents
                - **major**, could damage other documents
                - **minor**, likely not damge other documents, not following standards and templates
            - focus on logging as much as possible, should be 1 or 2 per minute
    5. Rework
        - author fixes up in the logged defects
        - can decide if its relevant or not
    6. Follow-up
        - moderator is responsible for ensuring that satisfactory actions have been taken on all logged defects
        - the moderator checks himself or distributes the work and collects feedback

### 3.2.2 Roles and responsibilities
- moderator, manages the whole meeting
- author, the creator of the document(s) being reviewed
- scribe, takes notes and logs defects found
- reviewers, "checkers" or "inspectors", checks the code

### 3.2.3 Types of reviews
## **Walkthrough**
- a presentation of the author to a set of people with different background
- can be a learning experience both for the author or the reviewers
- a seperate scribe is present
- scenarios can be used to validate
- doesnt require meetings before the review

## **Tehcnical review**
- discussion meeting
- defect detection
- peer review

## **Inspection**
- most formal
- reviewed before the meeting
- using rules and checklists
- help author to improve quality of the document
- effeciency vs effectiveness 
- trained individuals in their roles
- logging list
- metrics are used to analyse the process to make it more effecient or to define an end criteria

### Success factors for reviews
- **finding a champion**, an expert and an example
- **pick things that really cound**, select most important document, high ROI, find the correct review strategy based on ROI, right people involved
- **explicitly plan and track review activities**, make hours to be spent on testing visible
- **train participants**, important to train newbies
- **manage people issues**, avoid personal situations, open for critisism
- **follow the rules but keep it simple**, checklists and follow the script until you know you can change it
- **continously improve process and tools**, checklists, motivation is key, learning
- **report results**, early is good
- **just do it**, simple and not easy, get experience with experienced people



## 3.3 Static analysis by tools
- static analysis
    - requirements, design or code without running
    - informal reviews
    - find defects
- exists tools for static analaysis usually for software code

### 3.3.1 Coding standards
- define a coding standard
    - programming rules
    - naming conventions
    - layout speicifcations
- important to define a coding standard, can be decided by the tool or by preferences

### 3.3.2 Code metrics
- comment frequency
- depth of nesting
- 80% of problems comes from 20% of the code
- used to find areas of the code that is candidated for reviews
- cyclomatic complexity
- decision tree can be used to find a simpler way of doing logic

### 3.3.3 Code structure
- control flow structure, sequence of instructions executed, unreachable code, number of nested levels or cyclomatic complexity
- data flow structure, which data items are accessed or not or data items initated
- data structure, organization of data
- early detection
- not easily found in dynamic testing