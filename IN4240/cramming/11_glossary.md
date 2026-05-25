# 11 — Key Terms Glossary

Quick reference to the terms used across the lectures. Organised by chapter for easy lookup.

---

## Chapter 1 — Fundamentals

| Term | Definition |
|---|---|
| **Bug / Defect / Fault** | A flaw in code/design that may cause a failure when executed. ("Bug" and "defect" are usually interchangeable; "fault" is the deepest cause.) |
| **Error / Mistake** | A human action that produced an incorrect result — the source of a defect. |
| **Failure** | The observable manifestation of a defect when the software is run. |
| **Quality** | Degree to which the system meets stated and implied needs. |
| **Risk** | Probability × impact of an undesirable outcome. |
| **Software** | Programs, data, and associated documentation. |
| **Testing** | Static + dynamic process to evaluate software, determine compliance, and detect defects. |
| **Exhaustive testing** | Testing every possible input/state combination — impossible in practice. |
| **Code** | Source instructions implementing the software. |
| **Debugging** | Process of finding and removing defects (developer-side, distinct from testing). |
| **Requirement** | A capability the system must provide. |
| **Review** | Manual examination of an artefact (no execution). |
| **Test basis** | The information from which tests are derived (specs, code, etc.). |
| **Test case** | Concrete inputs, preconditions, expected results, postconditions. |
| **Test objective** | A reason for designing/running a test (find defects, build confidence, etc.). |
| **Confirmation testing** | Re-running a previously failed test after a fix. |
| **Exit criteria** | Conditions that must hold for testing to stop. |
| **Incident** | An event during testing requiring investigation. |
| **Regression testing** | Re-running tests after changes to verify no new defects. |
| **Test condition** | Item or event that *could* be tested. |
| **Test coverage** | Percentage of identified items exercised by tests. |
| **Test data** | Inputs used during test execution. |
| **Test execution** | Running tests, comparing actual vs expected results. |
| **Test log** | Chronological record of test execution. |
| **Test plan** | Document defining scope, objectives, approach, schedule. |
| **Test strategy** | High-level description of test levels and techniques. |
| **Test summary report** | End-of-level document summarising results. |
| **Testware** | Artefacts produced during testing (cases, scripts, data, environments). |
| **Independence** | Degree of organisational separation between developers and testers. |

## Chapter 2 — Software life cycle

| Term | Definition |
|---|---|
| **V-model** | Sequential model where each dev phase has a mirrored test phase. |
| **Waterfall** | Strictly sequential dev model; testing happens late. |
| **Iterative-Incremental** | Builds the system in repeated cycles (Agile, Scrum, RUP, Spiral). |
| **Agile development** | Iterative approach with short cycles, embracing change. |
| **COTS (Commercial Off-the-Shelf)** | Pre-built software products bought rather than developed. |
| **Verification** | "Are we building the thing right?" — meets specification. |
| **Validation** | "Are we building the right thing?" — meets user needs. |
| **Component (unit) testing** | Testing isolated modules. |
| **Integration testing** | Testing interfaces between components or systems. |
| **System testing** | Testing the integrated system against requirements. |
| **Acceptance testing** | Final user/business sign-off level. |
| **Alpha testing** | Acceptance done in-house by stand-in users. |
| **Beta testing** | Acceptance done by real users in their real environment. |
| **Operational testing** | Acceptance testing for operational readiness (backups, recovery). |
| **UAT** | User Acceptance Testing. |
| **Stub** | A simulator called *by* the component under test. |
| **Driver** | A simulator that *calls* the component under test. |
| **Functional testing** | Tests *what* the system does. |
| **Non-functional testing** | Tests *how well* (performance, security, usability, etc.). |
| **Black-box testing** | Tests based on specification, ignoring internals. |
| **White-box (structural) testing** | Tests using knowledge of internal structure. |
| **Interoperability testing** | Tests how the system works with others. |
| **Load / Stress / Performance testing** | Non-functional tests of capacity, limits, responsiveness. |
| **Maintenance testing** | Testing after deployment when changes occur. |
| **Impact analysis** | Assesses what parts of the system a change affects. |
| **Modification** | Change to fix bugs or add features. |
| **Migration** | Move to new platform / format. |
| **Retirement** | Decommissioning a system. |

## Chapter 3 — Static techniques

| Term | Definition |
|---|---|
| **Static testing** | Examining artefacts without executing them. |
| **Dynamic testing** | Tests that execute the software. |
| **Reviews** | Manual examination by people. |
| **Entry criteria** | What must be true *before* a review starts. |
| **Exit criteria** | What must be true *after* the review to consider it done. |
| **Formal review** | Documented, role-based, follows a defined process. |
| **Informal review** | Lightweight, undocumented review. |
| **Inspection** | Most formal review type, with rules, checklists, metrics. |
| **Walkthrough** | Author-led presentation; lightweight, often educational. |
| **Technical review** | Peer discussion meeting focused on technical correctness. |
| **Moderator** | Runs the review process. |
| **Reviewer** | Examines the artefact for defects. |
| **Scribe** | Takes notes / logs defects during the meeting. |
| **Compiler** | Tool that translates source → executable; can perform static checks. |
| **Cyclomatic complexity** | Metric counting independent paths through code. |
| **Control flow** | Sequence of execution through code. |
| **Data flow** | Pattern of data being read/written across code. |
| **Static analysis** | Tool-based examination of artefacts without execution. |

## Chapter 4 — Test design techniques

| Term | Definition |
|---|---|
| **Test condition** | Anything we *could* test. |
| **Test case** | Concrete inputs + expected results. |
| **Test procedure / script** | Ordered sequence of test cases ready to execute. |
| **Test suite** | A grouping of related tests. |
| **Traceability** | Link from test back to its source (requirement / spec). |
| **Black-box / specification-based** | Tests based on specs, ignoring internals. |
| **White-box / structure-based** | Tests using internal structure to drive coverage. |
| **Experience-based** | Tests driven by tester intuition + domain knowledge. |
| **Equivalence partitioning** | Group inputs into classes where the system behaves the same. |
| **Boundary value analysis (BVA)** | Test on and around the edges of equivalence partitions. |
| **Decision table testing** | Use tables of conditions/actions to cover combinations. |
| **State transition testing** | Test based on a state model (states, events, transitions). |
| **Use case testing** | Test scenarios driven by actor–system interactions. |
| **Statement testing / coverage** | Cover every executable statement at least once. |
| **Decision testing / coverage** | Cover both outcomes (T/F) of each decision. |
| **Control flow testing** | Tests guided by paths/branches in the code. |
| **Error guessing** | Tester predicts likely defect locations from experience. |
| **Exploratory testing** | Simultaneous learning, design, execution — guided by tester. |
| **Test strategy** | High-level approach for deciding test techniques. |
| **Regulatory standards** | External rules (e.g., FDA, aviation) shaping test depth. |
| **Complexity of system** | Driver for technique choice (more complex → more rigorous). |

## Chapter 5 — Test management

| Term | Definition |
|---|---|
| **Test organization** | How testing roles are arranged in the company. |
| **Test plan** | Document defining what/how/when of testing. |
| **Test estimate** | Predicted effort/time/cost for testing. |
| **Configuration management** | Process of identifying and tracking changes to artefacts. |
| **Risk** | Probability × impact of an undesirable event. |
| **Project risk** | Threat to the project's ability to deliver. |
| **Product risk** | Threat that the product will fail to satisfy users. |
| **Risk analysis** | Rating items on likelihood × impact (e.g., 0–10). |
| **Mitigate / Contingency / Transfer / Ignore** | Four options for handling risk. |
| **Defect** | Discrepancy between actual and expected outcome. |
| **Defect management** | Identifying, investigating, acting on, closing incidents. |
| **Defect report** | Document describing an incident — steps, severity, root cause, etc. |
| **Severity** | Technical impact of a defect. |
| **Priority** | Business urgency of fixing the defect. |
| **Test progress monitoring** | Collecting data on how testing is going. |
| **Test control** | Adjusting plans / scope based on monitoring data. |

## Chapter 6 — Tool support

| Term | Definition |
|---|---|
| **Probe effect** | The act of measuring (e.g., performance probe) changes the result. |
| **Intrusive tool** | A tool whose presence affects the behaviour being measured. |
| **(D)** | ISTQB notation for tools mainly aimed at developers. |
| **SAST** | Static Application Security Testing. |
| **DAST** | Dynamic Application Security Testing. |

---

## Quick mnemonics

- **7 principles:** "Defects shown · Exhaustive impossible · Early · Cluster · Pesticide · Context · Absence-of-errors fallacy."
- **Risk responses:** Mitigate, Contingency, Transfer, Ignore (MCTI).
- **Exploratory heuristics:** CRUD · COUNT · Goldilocks · RCRCRC · Trigger emotions.
- **TDD cycle:** Red → Green → Refactor.
- **Automation approaches:** Capture & playback · Structured scripting · Model-based.
- **V-model levels:** Component → Integration → System → Acceptance.
