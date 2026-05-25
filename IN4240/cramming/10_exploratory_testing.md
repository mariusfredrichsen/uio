# 10 — Exploratory Testing

**TL;DR:** Exploratory testing is **simultaneous learning, test design and test execution**, guided by the tester (not a script). It complements scripted testing, finds defects scripted tests miss, and depends on the tester's domain knowledge and curiosity.

---

## Why humans test

Three intertwined goals:
- **Make sure it works.**
- **Make sure it works as expected.**
- **Make sure it is good** — but good *for whom* and *in what context*?

### Weird vs Wrong
- **Wrong** = violates a specified rule / contract.
- **Weird** = violates an unstated expectation, taste, rhythm.

Both matter. A scripted test catches "wrong"; only a human is likely to catch "weird".

### Where AI / scripted testing falls short
- **Tone** — error messages that are correct but condescending, alarming, or cold.
- **Convention** — a button in an unexpected place; a colour that means the wrong thing here.
- **Friction** — the app works, but using it is tiring; models don't get tired.
- **Absence** — models test what's there; people notice what's missing.

---

## Principles

### ISTQB classics (scripted-friendly)
1. Testing shows the presence of defects.
2. Exhaustive testing is impossible.
3. Early testing saves time and money.
4. Defects cluster.
5. Pesticide paradox.
6. Testing is context-dependent.
7. Absence-of-errors fallacy.

### Human-centred (exploratory framing)
- An **act of looking**, not of proving.
- **You choose what to look at** — that choice *is* the work.
- Understand what is **really** difficult.
- **Context is lived** (not declared in a doc).
- A working product can still be the **wrong product**.

### Why context is lived
Same 200ms delay isn't the same delay for a nurse on night shift, a teenager on a bus, or a pensioner at a kitchen table. Same behaviour, three users, three experiences.

### Test effort depends on
- **Type of technology** (old, new, what stack).
- **Type of project** (small, large, agile, waterfall).
- **Type of product** (experimental, life-critical).

---

## What exploratory testing is

> Simultaneous **learning**, **test design**, and **test execution** — guided by the tester, not by a script.

| Scripted testing asks | Exploratory testing asks |
|---|---|
| Does it do what we said? | What is this thing, really? |

- Focuses on **discovery** and relies on the tester's guidance.
- Uncovers defects not easily found by other approaches.
- ISTQB classifies it as both **dynamic** (executes the system) and **experience-based** (relies on tester intuition / domain knowledge).

---

## Mnemonics & heuristics

- **Mnemonic** = a memory aid (e.g., "I before E except after C").
- **Heuristic** = a problem-solving rule of thumb that often works but isn't guaranteed.

Heuristics give you angles to attack a feature without writing exhaustive scripts.

### COUNT — zero, one, many, too many, too few

| Trigger | Test idea |
|---|---|
| 0 | Search for non-existent item |
| 1 | Search for a specific item |
| n | Search returning many items |
| Too many / too few | Both ends of the spectrum |

### Goldilocks — too big, too small, just right

| Trigger | Test idea |
|---|---|
| Too big | Overload a text field |
| Too small | Leave a text field empty |
| Just right | The happy-case input |

### CRUD — Create, Read, Update, Delete
For each operation, test happy + non-existent / duplicate variants:

| Op | Happy | Edge |
|---|---|---|
| Create | New user | User with duplicated attributes |
| Read | Existing user | Non-existent user |
| Update | Existing user | Non-existent user |
| Delete | Existing user | Non-existent user |

### RCRCRC — for regression scoping
Recent / Core / Risky / Configuration / Repaired / Chronic.

| Letter | Focus |
|---|---|
| **R**ecent | New features / freshly added code |
| **C**ore | Key functionality that simply must work |
| **R**isky | Areas relying on other services / components |
| **C**onfig | Areas affected by config / environment settings |
| **R**epaired | Code changed during a recent bug fix |
| **C**hronic | Areas that frequently break |

### Trigger heuristics — emotions are data
Watch how *you* feel as you use the product:

| Emotion | Investigate |
|---|---|
| Impatience | System too slow → delays? |
| Confusion | Counterintuitive interface? |
| Surprise | An assumption has been violated |
| Discomfort | Something is off — investigate it |

---

## How to do exploratory testing — 5 steps

### I. Develop a bug classification
- Classify bugs found in previous projects.
- Analyse root causes.
- Define risks in light of typical bug taxonomy.

#### Bug taxonomy
| Category | Examples |
|---|---|
| Input validation | Bad input accepted; good input rejected. |
| Boundary | Off-by-one, edges, min/max values. |
| Calculation | Wrong math, rounding, totals that don't add up. |
| Usability | Confusing flow, hidden affordance, dead ends. |
| Security | Auth gaps, leaks, injection, escalation. |
| Logic | Valid input → wrong outcome; missed branches. |
| Compatibility | Browser, device, locale, version drift. |
| Syntax | Typos, format errors, malformed payloads. |
| Performance | Slow responses, leaks, timeouts, jank. |

### II. Understand the system under test
- What kind of system?
- What kind of functionality?
- Who are the users?

### III. Choose a heuristic
Pick one or more from above (CRUD, COUNT, Goldilocks, RCRCRC, trigger heuristics, etc.).

### IV. Create a test charter / plan
A **charter** is a short mission statement for an exploratory session:
- Target (what's being explored)
- Resources (what tools / data / time)
- Information goals (what you hope to learn)

Time-box (e.g., 90-minute sessions).

### V. Continuously assess findings
- Take notes during the session.
- Adapt the next session based on what you learned.
- Feed bugs into the defect-tracking system.
- Update charter for follow-up exploration.

---

## When exploratory testing shines

- Poor or missing documentation.
- Limited time.
- Unfamiliar / new product.
- Areas where scripted tests pass but users complain.
- High-risk usability and UX flows.
- After an automated regression run — to find what scripted tests didn't.

---

## Likely exam topics

- Define exploratory testing — emphasise simultaneous learning + design + execution.
- Difference between **mnemonic** and **heuristic**.
- Explain at least 2 heuristics (CRUD, RCRCRC, COUNT, Goldilocks).
- Why is exploratory testing classified as both dynamic and experience-based?
- The 5 "how to" steps.
- Why "weird" matters even when nothing is "wrong".
- Why context is *lived* — give an example (nurse vs teenager vs pensioner).
