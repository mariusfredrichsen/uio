# 7 — Test Management (ISTQB Ch. 5)

**TL;DR:** Testing costs money — managing the team, plan, progress, configuration, risk, and defects is what keeps it useful. Risk-based testing balances coverage against time and cost; defect management gives feedback and supports decisions.

---

## 5.1 Test organization

### Independence in testing — a spectrum

From least to most independent:

1. Programmer tests own code.
2. Tester in same team as developers.
3. Dedicated test team within the organisation.
4. External test organisation.

- Lower independence = quick feedback, but blind spots.
- Higher independence = unbiased, but communication / "us vs them" risks.
- **Don't isolate the test team** — and don't let devs offload all testing onto them.

### Test leader / Test manager
- Plans, monitors, controls testing.
- Coordinates with stakeholders (devs, business, management).
- Owns: tools, environment, schedule, plan, automation needs.

### Tester
- Implements/executes tests, gathers metrics, supports the leader.
- Can build on developers' unit tests.

### Skills
The tester must understand **what is being tested** and the **end goal** of the project.

---

## 5.2 Test plans, estimates, strategies

### Purpose of a test plan
Ensure shared understanding of *what* is being tested, *how*, *by whom*, and *when*.

### IEEE 829 outline (test plan contents)
- Test plan identifier
- Introduction / scope
- Test objectives
- Test items
- Features to / not to be tested
- Approach / strategy
- Item pass/fail criteria
- Suspension / resumption criteria
- Test deliverables
- Testing tasks / schedule
- Environmental needs
- Responsibilities / staffing / training
- **Risks** and contingencies
- Approvals

### Entry criteria
What must be true *before* testing starts (e.g., build available, smoke test passes, env ready).

### Exit criteria
When testing can stop (e.g., coverage targets met, defect rate under threshold, time/budget exhausted).

### Estimating
- Use experience, expert judgement, metrics from similar projects.
- Reassess as new info arrives.

---

## 5.3 Test progress monitoring and control

### Monitoring
- Provides feedback / overview of testing.
- Data collected manually or automatically.
- **Metrics** measure progress vs plan and exit criteria (e.g., tests run/passed/failed, defects found per area, coverage %).

### Test logs (IEEE 829)
- Identifier.
- Description (items tested, environment).
- Activity / event entries.

### Test reporting
Periodic summaries (test summary report at end of a level) include:
- Test objectives.
- Approach taken.
- Effectiveness vs. objectives.
- Open defects, risks remaining.

### Control
Based on monitoring, you can:
- Re-prioritise areas of testing.
- Adjust schedule / scope.
- Add new test items.
- Tighten or loosen entry/exit criteria.

---

## 5.4 Configuration management

Tools/process that **identify and track every change** to test items and testware.
- Versioning of code, test scripts, test data, environments.
- Ensures a defect found in build X can be reproduced against the exact same X.
- Traceability for audits.

---

## 5.5 Risk and testing

### Risk = possibility of an undesirable outcome
Two sides:

#### Project risks
Threats to the project itself:
- **Organisational** — staff/skill shortages, low morale, lack of communication, poor attitude to testing.
- **Technical** — bad requirements, bad design, unstable platforms.
- **Supplier** — third-party failure, contractual disputes.

#### Product risks
The system might fail to meet user/customer/stakeholder expectations:
- Failures could cause harm.
- The product does the wrong thing / lacks key features.

### Four responses to risk
1. **Mitigate** — reduce probability/impact (e.g., extra testing).
2. **Contingency** — plan for what to do if it occurs.
3. **Transfer** — push the risk elsewhere (insurance, vendor).
4. **Ignore** — accept the risk if low cost/impact.

### Risk analysis
- Rate items 0–10 on **probability × impact**.
- Educated guesses, refined at project milestones.
- In V-model: requirements phase, end of design, during implementation, throughout testing.
- Risk-based testing **balances** coverage and effort — not "risk-free", but informed.

---

## 5.6 Defect management

### Definitions
- **Defect** — a discrepancy between actual and expected outcome.
- **Defect management** — the process of recognising, investigating, acting on, and closing incidents.

### Defect report (IEEE 829-style)
A defect report should answer: who is the audience, what is the purpose? Includes:
- Description of the defect.
- 1–2 screens of info from the tracking tool.
- Method used to identify it (steps to reproduce).
- Impact / severity.
- Classification: scope, severity, priority.
- Resources used / risked.
- **Root cause** (typically captured by the programmer who fixed it).
- Conclusions and recommendations.
- Lifecycle: opened → assigned → fixed → verified → closed (or deferred / rejected).
- Open to all roles for comment.
- Should follow a standard.

### Why bother
- Track recurring patterns, learn from incidents.
- Feed metrics back into planning and risk analysis.

---

## Likely exam topics

- Independence levels — pros & cons.
- Test plan contents (IEEE 829) — be ready to list ~6–8 of them.
- **Entry vs exit criteria** — examples.
- Difference between **project risk** and **product risk** with examples.
- Four responses to risk: mitigate / contingency / transfer / ignore.
- What goes into a **defect report** + the lifecycle.
- Why configuration management matters for reproducing defects.
