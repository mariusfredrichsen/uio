
- waterfall

### Iterative/incremental
- agile, rational unified process, scrum, kanban, spiral
- CI/CD and DevOps can be applied

### The V-model
- From the user to implementing then back to the user
- Requirements
    - User then System
- Design
    - Global then Detailed
- Implement
- Test execution
    - Component then Integration then System then Acceptance (User)
- Operational system

- Needs to start as early as possible
- Validation happens when the user is invloved in the testing
- **Regression testing**, testing in incremental development, tests all previuos versions of the systems and their components

### Testing within a life cycle model
- Every development activity has a corresponding testing activity
- Every test level has their objectives
- Designing of tests should start when the corresponding development happens



## Test Levels
- note for every test level:
    - generic objectives
    - test basis
    - test objects
    - typical defects and failures
    - specific approaches

### Component testing
#### Objectives
- Verifying the functioning of software items (models, methods, objects, classes, etc.) that are seperately testable
- testing of functionality and specific non-functional characteristics as resource behavior and robustness

#### Test basis
- All the component material:
    - Specification
    - Design
    - Data model
    - The code itself

- The code for testing the project code
- Can use stubs, drivers and simulators to fill inn for missing components
    - **Stubs**, to be called from the tested component
    - **Drivers**, to call the tested component

- Done with the programmer that wrote the code
- Bugs are fixed without formal recording of incidents
- TDD, prepare the tests before programming the functions

### Integration testing
- Tests interfaces between components
- Tests interactions between different parts
    - GUI, database, program logic, OS, file system, hardware

#### Test basis
- Design
- Architecture
- Data flow
- Workflows/use cases

#### Test objects
- builds incluide some or all components
- the database
- system infrastructure
- interfaces between components or objects
- system configuration
- configuration data

#### Component integration
- tests the interaction between software components after component testing
- 

### System testing

### Acceptance testing



## Test Types
### Testing of function

### Testing of non-functional software characteristics

### Testing of software structure

### Testing related to changes



## Maintenance testing
### 