# Test-Driven Development (TDD)
- Cyber-dojo, Java and JUnit

## What is TDD?
- first write the test then write the code
- write enough code until the tests are satisfied and reiterate
- uses unit testing mostly

### Advantages of TDD
    - fast feedback
    - simpler design, step by step apporach
    - safer changes, always tested
    - testable code

### The TDD cycle
- Red then green then refactor
- Red is writing the tests describing the desired behaviour
- Green is writing the MINIMUM code
- Refactor is when improving the code without changing functionality and then rerun the tests


## Cyber-dojo
### Leap year
- divisable by 4
- not divisable by 100 unless its divisable by 400

### FizzBuzz


## JUnit
- Metadata
- Annotations, @Test
- assertEqual
- assertTrue, assertFalse
- assertAll ??

- a good test name is describing the scenario and the expected results


## Summary, good practices and common pitfalls
- pitsfalls:
    - too big steps
    - writing production code first to save time
    - too implementation-dependent
    - too little refactoring, green but messy and hard to read
    - "I trust that it works" => rarely true in the long run

- good habit:
    - run tests frequently
    - each test should verify ONE THING
    - refactor regularly when tests are green