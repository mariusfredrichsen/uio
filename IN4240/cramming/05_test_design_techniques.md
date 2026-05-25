# 5 — Test Design Techniques (ISTQB Ch. 4)

**TL;DR:** Three families of dynamic test techniques: **black-box** (specification-based), **white-box** (structure-based), **experience-based**. Pick the technique that fits the level, the artefact, and the risk. Mix them.

---

## 4.1 Identifying test conditions and designing test cases

### Key terms (from informal → formal)
- **Test condition** — anything we *could* test (a behaviour, a rule, a value).
- **Test case** — a concrete input + expected result.
- **Test procedure / script** — ordered sequence of test cases, ready to execute.
- **Test suite** — a group of related test cases.
- **Traceability** — link from a test back to the requirement / test basis it covers (horizontal across docs at one level; vertical across levels).

### Documentation formality
Depends on context: critical apps and regulatory environments demand rigorous docs; throwaway scripts may need none. Drivers: criticality, time, team maturity, audit needs.

### Test analysis
- Examine the **test basis** (requirements, specs, code, models) and identify **test conditions**.
- Capture **test possibilities** = things you could test but haven't yet.
- Test conditions should be **traceable** to their source.

### Test design
- Specify each test case: input, expected result, preconditions, postconditions.
- Decide level of detail: experienced testers can work from coarse cases; new testers need detail.
- Record **why** each test exists.

### Test implementation
- Group tests, prioritise (find the **scariest bugs first**), produce executable scripts.

---

## 4.2 Categories of techniques

| Category | Other names | Focus |
|---|---|---|
| **Static** | review, analysis | Don't execute; covered in `03_static_techniques.md`. |
| **Specification-based** | black-box, behavioural | What the system should do. No knowledge of internals. |
| **Structure-based** | white-box, structural, glass-box | How the system does it. Coverage of code structures. |
| **Experience-based** | error guessing, exploratory | Tester's intuition + skill. |

- **Specification-based** works at every level.
- **Structure-based** works at every level (statements, branches, paths, etc.).
- **Experience-based** complements the above.

---

## 4.3 Black-box (specification-based) techniques

### Equivalence Partitioning (EP)

Group inputs that should be treated the same. Pick **one** value per partition.

Example — age field accepting 18–65:
- Partition A: `< 18` (invalid, too young)
- Partition B: `18–65` (valid)
- Partition C: `> 65` (invalid, too old)

Test one value per partition (e.g., 10, 30, 80).

### Boundary Value Analysis (BVA)

Defects cluster at boundaries. For each boundary, test:
- The boundary value itself.
- One value on each side.

For 18–65: test **17, 18, 19** and **64, 65, 66**.

**Open boundaries** (e.g., "any positive integer") use experience to pick a sensible upper/lower edge (max int, 0, 1).

### Decision Table Testing

Used when **combinations of inputs** drive different outcomes (business rules).

| Rule | Cond1 | Cond2 | Cond3 | → Action |
|---|---|---|---|---|
| 1 | T | T | F | Approve |
| 2 | T | F | F | Hold |
| 3 | F | * | * | Reject |

Strengths: complete coverage of rules; flushes out missing/contradictory rules.
Tactic: split combinations into manageable subsets.

### State Transition Testing

Model the system as a **finite state machine**:
- States, events (transitions), guard conditions, actions.

**State table** lists all states (rows) × events (columns); cells = next state. Useful for finding **invalid transitions** (cells that should be empty).

Coverage options: every state, every valid transition, every pair of valid transitions, every invalid transition.

### Use Case Testing

Centred on actor–system interactions:
- **Actor** = user, external system, or component.
- Each use case has **preconditions**, main flow, alternative flows, **postconditions**.
- Great at finding integration defects and real-world workflow problems.
- Mostly applied at **system** and **acceptance** levels.

---

## 4.4 White-box (structure-based) techniques

### Coverage = a measure of "how much"

> coverage = (items exercised / total items) × 100%

100% coverage **does not** mean fully tested. There's always more to test. Coverage tells you *what your tests reach*, not what they *prove*.

### Common coverage types

| Technique | What's the "item"? |
|---|---|
| EP | Equivalence partitions |
| BVA | Boundaries |
| Decision tables | Business rules / columns |
| State transition | States / valid transitions / pairs / invalid transitions |
| **Statement coverage** | Each executable statement |
| **Decision (branch) coverage** | Each True/False outcome of each decision |
| Path coverage | Every independent path (often impractical) |

### Statement coverage
- "Has every statement been executed at least once?"
- Easy to achieve high numbers, but doesn't guarantee branch logic is tested.

### Decision coverage
- For every `if`, `while`, `for`, `case` — has each outcome been tested?
- Stronger than statement coverage. 100% decision coverage implies 100% statement coverage.

### Tooling
Almost always needs instrumentation + a coverage tool. Focus on critical sections of code.

---

## 4.5 Experience-based techniques

### Error guessing
- Tester uses experience to predict where defects hide (off-by-one, null, empty list, encoding, time zones…).
- Effectiveness depends entirely on tester skill.
- A good practice: maintain a **defect / error list** as input for tests.

### Exploratory testing
- Time-boxed; minimal up-front planning.
- Simultaneous **learning, design, execution**.
- Useful when documentation is poor or time is short.
- See `10_exploratory_testing.md` for the full treatment.

---

## 4.6 Choosing a technique

No single recipe — depends on:
- Test basis available.
- Risk profile and regulatory context.
- Tester skill.
- Time / cost.
- Models already in place (state machines? decision tables?).
- System complexity.

Often **combine** several: BVA + decision tables + exploratory is a standard mix.

---

## Likely exam topics

- Apply EP + BVA to a given specification (e.g., age field, password length).
- Build a **decision table** from a set of business rules.
- Draw / interpret a **state transition diagram** + state table; find invalid transitions.
- Compare statement coverage and decision coverage; show that 100% statement ≠ 100% decision.
- Define **traceability** and explain horizontal vs vertical.
- When would you choose experience-based testing over specification-based?
