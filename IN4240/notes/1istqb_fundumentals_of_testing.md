# Introduction
## Why is testing necessary
- Disasters

### SoftWare system context
- If SW system is not working it can lead to
    - Loss of money
    - Loss of business repuation
    - Injury or death
- Diversity of users, situations and devices
    - Different social situations and preferences
    - Different conditions both physically and mentally

### Causes of software defects
- Human error
- Non-controllable events
- All parts of the chain of developing the software system needs to be working
    - Business analyst
    - Requirements
    - System architect
    - Design
    - Programmer
- Cost to repair is much bigger the longer one waits to fix the issue

### Role of testing in software systems
- Important in alle stages
    - Planning
    - Development
    - Maintenance
    - Operations
- Reduce the risk of problems occuring
- Check if the software system meets legal requirements and industry standards
- Learn more about the software system

### Testing and quality
- measuring the quality is based on defects found
    - non-functional and functional aspects
- creates confidence in the quality if its properly tested and a minimum defects are found
- teaches us lessons to apply in future projects

### How much testing is enough?
- Depends on:
    - Level of risk, technical and business
    - Projects constraints, time and budget
- Impossible to test everything
- Testing should provide enough information to make good decisions



## What is testing?
- Definition:
    - The process of testing all SW life-cycle activies, both static and dynamic, concerned with, planning, preperation and evaluation of: software products and related work products to: determine that they satisfy spcified requirements, demonstrate that they are fit for purpose and to detect defects.
- Test activities:
    - test planning
    - test control
    - test analysis
    - test design
    - test implementation
    - test exectution
    - checking results
    - evaluation of exit criteria
    - test result reporting
    - test closure
- Depending on the objectives, we focus on:
    - confirming that the SW systems meet the requirements
    - causing as many failures as possible
    - checking that no defects have been introduced during changes
    - assessing the quality of the SW (with no intetion of finding bugs)



## The seven test principles
1. Testing shows presence of defects, can only show if there are defects, not if there are none
2. Exhaustive testing is impossible, impossible to test everything, use risk and priorities to chose where to focus
3. Early testing, should start as soon as possible
4. Defect clustering, defects are piled together
5. Pesticide paradox, tests need to be further developed to find new defects
6. Testing is context dependent, tests are done differntly in different contexts
7. Absence-of-errors fallacy, no point in fixing defects if the SW system does not filfill users needs



## Fundamental test processes
### Plan and control
- who, what, why, when and where?
- A plan includes:
    - scope, objectives and risk analysis
    - test levels and types
    - documentation
    - resources assigned for the different test activities
    - schedule of test implementation, execution and evaluation
- Control and adjust the planning to reflect new information, goals and challenges

### Analysis and design
- Review test basis:
    - Requirements
    - User stories
    - Product architecture
    - Product design
    - Interfaces
    - Risk Analysis report
- Analysis:
    - general test objectives are transformed into "test conditions"
- Design:
    - Test cases
    - Test enviroments
    - Test data
    - Create tracebility

### Implementation and execution
- Implement
    - Group tests into scripts
    - Prioritize the scripts
    - Prepare test oracles
    - Write automated test scenarios
- Execute
    - run tests and compare results with oracles
    - report incidents
    - repeat test activities for each correct discrepancy
    - log the outcome of the test execution

### Test completion
- Evaluate
    - Assess test execution against the defined objectives
    - check if:
        - more tests are needed?
        - exit criteria should be changed
- Report
    - write or extract a test summary report
- Test closure activities
    - make test assets available for later use



## Psychology of testing
- A good tester needs:
    - Curiosity
    - Professional pessimism
    - Good communication skills
    - Experience at error guessing
    - To communicate defects and failures in a constructive way
        - fact focused reports
        - review of findings

### Independence in testing
- ??

### Tips and tricks
- Be clear and objective
- Confirm that:
    - You have understood the requirements
    - The person who is going to fix the bug has understood the problem
