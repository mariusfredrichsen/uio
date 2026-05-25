# 2 — Testing Through the Software Life Cycle (ISTQB Ch. 2)

**TL;DR:** Every development activity has a corresponding test activity. The V-model pairs them; agile/iterative does the same continuously. Test **levels** (component → integration → system → acceptance) tell you *when* in the lifecycle you test; test **types** (functional, non-functional, structural, change-related) tell you *what aspect* you're testing.

---

## Software development models

### Waterfall
- Sequential, one phase after another. Testing comes late — risky and expensive.

### Iterative / Incremental
Includes Agile, RUP (Rational Unified Process), Scrum, Kanban, Spiral.
- CI/CD and DevOps fit naturally here.
- **Regression testing** is critical: every increment must re-verify earlier work.

### The V-model
The dev side and the test side mirror each other:

```
Requirements (User)         <----->     Acceptance testing
Requirements (System)       <----->     System testing
Design (Global)             <----->     Integration testing
Design (Detailed)           <----->     Component testing
                Implementation
```

- Test **design** for each level should start as soon as the corresponding development artefact exists.
- **Verification** (building the thing right) vs **Validation** (building the right thing — happens once user is involved).

### Key principles for any model
- Start testing as **early as possible**.
- Every dev activity has a test counterpart.
- Each test level has its own objectives.

---

## Test Levels

For each level you should know: **objectives, test basis, test objects, typical defects, approach.**

### Component (Unit) testing
- **Objective:** verify individual modules/classes/methods work in isolation.
- **Basis:** specification, design, data model, code itself.
- **Objects:** components/modules.
- **Tools:** **stubs** (called *by* the tested component) and **drivers** (call *into* the tested component) fill in for missing pieces.
- Done by/with the **programmer**. Bugs fixed informally.
- **TDD** lives here — write the test first.

### Integration testing
- **Objective:** test interfaces and interactions between components.
- **Basis:** design, architecture, data flow, workflows, use cases.
- **Objects:** builds, database, infrastructure, interfaces, system config.
- **Component integration testing:** tests the interaction *after* unit testing.
- **System integration testing:** tests interactions with other systems/external services.

### System testing
- Tests the system as a whole vs. specified requirements.
- Functional + non-functional.
- Performed in a near-production-like environment.

### Acceptance testing
- Final check that the system meets user/business needs (**validation**).
- **Alpha** (in-house, by intended users), **Beta** (in users' real environment).
- **UAT** (User Acceptance Testing), operational acceptance, contract/regulation acceptance.

---

## Test Types (the *what* you're testing)

### Functional testing
"What the system does." Black-box, requirements-based.

### Non-functional testing
"How well the system does it." Includes:
- Performance (load, stress, scalability).
- Usability, accessibility.
- Reliability, recoverability.
- Security.
- Portability, interoperability.
- Maintainability.

### Structural (white-box) testing
Tests the internal structure (statements, branches, paths). Often at component level.

### Change-related testing
- **Confirmation testing (re-testing):** rerun the failed test after a fix.
- **Regression testing:** rerun previously passing tests to confirm the change broke nothing else. Heavily automated.

---

## Maintenance testing

Triggered after the system is in production, when changes happen:

- **Modification:** bug fixes, enhancements.
- **Migration:** moving to new platform/data/format.
- **Retirement:** archiving data, decommissioning.

### Impact analysis
Before testing, assess:
- What parts of the system the change affects.
- Side effects.
- How much regression testing is needed.

Hard problems: documentation may be missing/outdated; original developers may be gone.

---

## Likely exam topics

- Draw the V-model and label each level with its corresponding test level.
- Difference between **verification** and **validation**.
- Difference between **stubs** and **drivers** (and which calls which).
- Compare **confirmation testing** vs **regression testing**.
- For each test level, give: objective, basis, typical defects.
- Example of a non-functional test (e.g., load test, security test).
- Why agile increases the importance of automated regression.
