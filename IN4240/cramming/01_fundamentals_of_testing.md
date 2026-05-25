# 1 — Fundamentals of Testing (ISTQB Ch. 1)

**TL;DR:** Testing is the activity of evaluating software (statically and dynamically) to find defects, build confidence in quality, and inform decisions. You can never test everything — choose where to focus using risk and the 7 principles.

---

## Why testing is necessary

- **Software disasters** show what happens when testing is skipped: financial loss, reputation damage, injury or death.
- **Causes of defects:** human error, non-controllable events, weak links anywhere in the chain (analyst → requirements → architect → design → programmer).
- **Cost of defects** rises sharply the later they are found — fixing in production is far more expensive than fixing in design.
- **Role of testing:** active in planning, development, maintenance, operations. Reduces risk, verifies legal/industry requirements, teaches us about the system.

## Testing and quality

- Quality is measured (in part) by defects found — both **functional** and **non-functional**.
- Good testing creates **confidence** when few defects remain.
- Lessons learned feed back into future projects.

## How much testing is enough?

Depends on:
- Level of **risk** (technical and business).
- Project **constraints** (time, budget).

Exhaustive testing is impossible. Testing should produce **enough information to make good decisions**.

---

## What is testing? (definition)

> The process of all SW life-cycle activities, both **static** and **dynamic**, concerned with planning, preparation, and evaluation of software products and related work products to determine that they satisfy specified requirements, demonstrate fitness for purpose, and detect defects.

### Test activities
Plan → control → analysis → design → implementation → execution → checking results → evaluating exit criteria → reporting → closure.

### Possible focuses
- Confirm requirements are met (verification/validation).
- Cause as many failures as possible (fault detection).
- Check no new defects were introduced (regression).
- Assess overall quality (no specific intent to find bugs).

---

## The 7 Test Principles (memorise!)

1. **Testing shows the presence of defects** — never their absence.
2. **Exhaustive testing is impossible** — use risk + priorities.
3. **Early testing** saves time and money.
4. **Defect clustering** — defects pile up; 80% of issues come from 20% of code (Pareto).
5. **Pesticide paradox** — same tests stop finding bugs; tests must evolve.
6. **Testing is context-dependent** — safety-critical ≠ a website.
7. **Absence-of-errors fallacy** — bug-free software is useless if it doesn't meet user needs.

---

## Fundamental test process

### 1. Plan and control
*Who, what, why, when, where?*
- Plan covers: scope, objectives, risk, levels, types, documentation, resources, schedule.
- Control = adjust the plan as new information arrives.

### 2. Analysis and design
- **Test basis:** requirements, user stories, architecture, design, interfaces, risk reports.
- **Analysis:** turn objectives into **test conditions**.
- **Design:** test cases, test environments, test data, traceability.

### 3. Implementation and execution
- Group tests into scripts, prioritise, prepare oracles, automate.
- Execute, compare with oracle, report incidents, repeat, log.

### 4. Test completion (closure)
- Evaluate vs. objectives — more tests needed? Adjust exit criteria?
- Write test summary report.
- Archive testware for future use.

---

## Psychology of testing

A good tester needs:
- **Curiosity** + **professional pessimism**.
- Strong **communication skills**.
- Skill at **error guessing**.
- Constructive way of reporting defects (fact-focused, peer-reviewed).

### Independence in testing (spectrum)

From least to most independent:

1. Programmer tests own code.
2. Tester in the same team as the developers.
3. Separate test team within the organisation.
4. External test organisation.

Pros of more independence: less bias, fresh eyes. Cons: communication gaps, "us vs them" attitude, slower feedback loops.

### Reporting tips
- Be clear and objective.
- Confirm you understood the requirement.
- Confirm the fixer understood the problem.

---

## Likely exam topics

- Recite and explain the 7 test principles with examples.
- Describe the four phases of the fundamental test process.
- Definition of testing (static + dynamic, planning + evaluation).
- Why early testing saves money — cost-of-defect curve.
- Independence levels and their tradeoffs.
