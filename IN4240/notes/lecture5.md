# Test design techniques - Part 1
- **dynamic testing**, executing tests on running code

## 4.1 Identifying test conditions and designing test cases
### 4.1.1 Introduction
- what are we testing?
- looking for: test condition, test cases, test procedures
- document test cases

### 4.1.2 Formailty of test documentation
- different formalities, most formal test documentation is well documented while informal can have no documentation at all
- formality of documentation depends on context
    - critical application or one-time use
    - time constrains
    - work culture, maturity of developers

### 4.1.3 Test analysis: identifying test conditions
- defining the test basis. It can be system requimrent, technical specification or the code itself
- **test condication**: something that we could test
- **test possibilities**: identify as many test conditions as possible, not yet tested but can be tested
- cant test everything, needs to find a **test technique**
- **test conditions** should be tracable to their source
    - can be traced through documents in the same testing level, horizontal
    - can be traced through documents in different layers of testing, vertical
- **test data**: most important types of data, most important data conditions

### 4.1.4 Test design: specifying test cases
- making test cases too detailed might be waste, but a new developer that doesnt know the system might not know whats happening. find a middle ground
- should also write down why the test exists for traceability

### 4.1.5 Test implementation: specifying test procedures or scripts
- group tests and execute them in the correct sequence
- the documentation for the sequence of which tests should run is **test procedure** or a **test script**
- find the "scariest" bugs first

## 4.2 Categories of test design techniques
### 4.2.1 Introduction
- different test techniques might find different things, pick the most suitable one
- static testing and dynamic testing
    - dynamic testing is divided into:
        - **specification-based**, black-box, behavioural techniques
        - **structured-based**, white-box, structural techniques
        - **experience-based**

### 4.2.2 Static testing techniques
- non-execution techniques

### 4.2.3 Specification-based (black-box) testing techniques
- black-box or input/output testing techniques
- focus on what and not how
- functional focus on what the system does and non-functionl focus on how well the system does

### 4.2.4 Structure-based (white-box) testing techniques
- test tehcniques to what the system does with insight to how the system does it, glass-box, seethrough

### 4.2.5 Experience-based testing techniques
- just based on the peoples experience maybe from different fields 

### 4.2.6 Where to apply the different categories of techniques
- speicifcation-based testing can be used throughout all test levels
- structure-based testing can also be used at all levels
- experience-based testing is used to support the other categories

## 4.3 Specification-based or black-box techniques
### 4.3.1 Equivalence partitioning (EP) and boundary value analysis
- good all-around, can be used at all levels, good to start with first
- common sense approach
- group together test-condition and only test one of the test-conditions in that group and assume the rest of the equivalent test-conditions have the same result
- good to state assumption so others can se them
- when errors occur they should be stated with what the real value should be

#### Boundary value analysis
- test the boundaries by defining values and testing
- open boundary is test for the minimum and maximum
    - use experience-based apporach to find a upper and lower boundary

##### Extending equivalence partitioning and boundary values analysis
- testing different inputs, fuzzing?, to find more partitions

##### Designing test cases
- the more a test case can cover test conditions, the less test cases we need to cover "everything"
- need to balance how many, if you have many then its harder to trace back to the source

### 4.3.2 Decision table testing
- used to handle combinations of inputs
- more focus on buseniss logic or rules
- should have a systematic way of chosing combinations

#### Using decision tables for test design
- split the combinations into smaller subsets and deal with them one at the time

### 4.3.3 State transition testing
- finite state machine, shown with a state diagram
- can give info even when incomplete
- flexible in level of detail

#### Testing for invalid transitions
- **state table**, lists all the states down one side of the table and all the events that cause transitions along the top
    - cells represents which state it moves to, S2 with action 2 moves to S6

### 4.3.4 Use case testing
- have an actor interact with the system
    - actor is usually a person but can be another component in the system or something similar
- finds more defects in real-world use
- must have a precondition and a postcondition
- used for system and acceptance testing levels

## 4.4 Structure-based or white-box techniques
### 4.4.1 Using structure-based techniques to measure coverage and design tests
- help to ensure more breadth of testing
- **test coverage** = number of coverage items exercised / total number of coverage items * 100%
- 100% doenst mena 100% tested, there is always more to be tested

#### Types of coverage
- can be measured at different test levels
- EP: percentage of equivalence partitions exercised
- BVA: percentage of vboundaries exercised
- Decision tables: percentage of busniess rules or decision table columns tested
- State transition testing: multiple, percentage of states visited, percentage of valid transitions exercised, pairs of valid transitions exercised, of invalid transitions exercised
- **code coverage**

#### How to measure coverage
- usually needs tool support
- implement instruments to help the tool
- focus on critical parts of the code

### 4.4.2 Statement coverage and statement testing
- **Statement coverage** = number of statements exercised / total number of statements * 100%
- statements can be anything from each line to something more, depends on the tool, it counts differntly depending on the tool

### 4.4.3 Decision coverage and decision testing
- Try out for True AND False or multiple combinations if there are any

### 4.4.4 Other structure-based techniques
- there are differnt ways of covering code
- branching or line by line or decisions, etc. 

## 4.5 Experience-based techniques
- non-systamatic

### 4.5.1 Error guessing
- dependant on skill of the tester
- can have a list of possible errors and have someone else produce tests for these defects

### 4.5.2 Exploratory testing
- time-box test effort, little planning and more hands on
- test logging
- usefull when there is poor documentation or limited time
- 

## 4.6 Choosing a test techniques
- use common knowledge
- there are A LOT of factors