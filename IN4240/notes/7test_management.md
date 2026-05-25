# Test management 
- testing can be costly so need to manage it

## 5.1 Test organization
- test team
- test independence

### 5.1.1 Independent and integrated testing
- the opposite of independence in testing we have the programmer itself writing the tests
- the other end (having independence) we have the testers in its own team
- in between we have testers working in the same team as the programmer
- dont let the test team get too isolated
- programmers off-load too much work on the test team

### 5.1.2 Working as a test leader
- involved in planning, monitoring and control of testing activities
- collaborates with the stakeholders
- plan the effort, tools and talks with the programmers if automatic testing is required
- basically has a lot of responsibility that everything is set up correctly (the enviroment, correct tools, tasks, etc.)

### 5.1.3 Working as a tester
- helps the leader and helps collecting metrics
- may use programmers unit tests

### 5.1.4 Defining the skills test staff need
- the testers must understand what is supposed to be tested and what the end goal is

## 5.2 Test plans, estimates and strategies

### 5.2.1 The purpose and substance of test plans
- we write test plans to ensure we understand what we are testing
- IEEE 829 test plan
- how to make the test plan
    - scope
    - test objectives
    - what is the risks
    - what kind of constraints are there
    - whats the most critical
    - whats the most and least testable in the project
    - scheduling
- entry criteria
- exit criteria
    - resources
    - items
    - 

## 5.3 Test progress monitoring and control
### 5.3.1 Test progress monitoring
- Used to give feedback or and overview about test activities
- information can be collected manually or automatically
- metrics can be used to measure the exit criteria or if the testing progress is in line with the schedule
- test log template
    - IEEE 829 standard
    - identifier
    - description, items being tested and in which enviroment
    - activity and event entries

### 5.3.2 Test reporting
- report stuff to then later make better choices based on the logged stuff
- metrics are collected at the end of a test level to:
    - test objectives
    - test approaches
    - effectiveness of approaches with respect to its objectives

### 5.3.3 Test control
- based on earlier decisions you can
    - re-prioritize
    - make new decisions
    - add new items
    - set an entry-criteria


## 5.4 Configuration management
- tools used to identify and track every change done

## 5.5 Risk and testing
- possibility of a negative or undesirable outcome
- product or project
- helps plotting for solid testing
- four possibilities
    - mitigate
    - contingency
    - transfer
    - ignore

### 5.5.1 Project risks
- the risk that surrounds the project's capability to deliver its objectives
    - organizational factors, staff/skill shortage, personal issues, lack of communication, improper attitude against testing
    - tehcnical issues, wrong requirements, quality of design or code
    - supplier issue, third party failure, contractual issues

### 5.5.2 Product risks
- risk directly related to the test object, is the possibility that the system or software might fail to satisfy som reasonable customer, user or stakeholders expectation
- failures in the product can hamr the user
- the product does the wrong thing

### 5.5.3 Risk analysis
- rate risk items on a scale from 0-10 based on impact and probability
- risk analysis are educated guesses, done at project milestones
    - v-model, the requirements phase, end of design phase, implmentation phase, during all testing phases
- analyze risk early
- risk based testing does not aim to make risk free application, but balance the factors so that we have the good of everything

## 5.6 Defect management
- **defect**, discrepancies between actual and expected test outcomes.
- **defect management**, the process of recognizing, investigating, taken action and disposing of incidents.
    - keep track of everything, identify, previes changes, etc.
- **defect report**, a report document reporting on any event that occurred, e.g. during testing, which requires investigation.
    - what is the objective? what is the purpose? who is the reader? what does into it?
    - description
    - one or two screens of information from the defect-tracking tools
    - method used to solve
    - impact of the problem
    - classification information, scope, sevirity, priority of the defect
    - a level of priority
    - resources used to fix vs resources risking to be lost
    - root cause, captured by the programmer
    - conclusions and recommendations made by managers, programmer or other
    - let everyone write in it
    - can be reviewed, opened, closed, reported, assigned, deferred, etc. at any time
    - follows a standard