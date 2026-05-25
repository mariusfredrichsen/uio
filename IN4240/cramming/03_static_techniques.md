# 3 — Static Techniques (ISTQB Ch. 3)

**TL;DR:** Static testing examines artefacts **without executing** them — through reviews (people) and static analysis (tools). Cheap, finds defects early, complements (not replaces) dynamic testing.

---

## Static vs dynamic testing

| | Static testing | Dynamic testing |
|---|---|---|
| Execution? | No | Yes |
| Finds | Deviations, missing/wrong requirements, design defects, non-maintainable code, inconsistent interfaces | Failures observed at runtime |
| Examples | Reviews, walkthroughs, inspections, static analysis | Unit tests, system tests, BVA |

**Reviews** are also informational/educational/communicational — useful for onboarding and learning roles.

---

## Review process — 6 phases

1. **Planning** — author asks moderator for review. Define scope, document size (limited by attention span), entry criteria. Moderator assigns reviewer roles:
   - Type 1: higher-level documents (design, requirements).
   - Type 2: standards, internal consistency, naming.
   - Type 3: related documents, interfaces.
   - Type 4: usability, testability, maintainability.
2. **Kick-off** (optional) — entry/exit criteria, role assignment, checking rate. Studies show kick-offs find ~70% more major defects.
3. **Preparation** — reviewers read individually, build checklists, log defects (typos noted but not raised in meeting).
4. **Review meeting** — logging phase:
   - Focus on logging, not discussing whether something is a defect.
   - Aim for 1–2 defects logged per minute.
   - **Severity:** critical (will damage other docs) > major (could damage) > minor (cosmetic).
   - A **scribe** is helpful in formal reviews.
5. **Rework** — author fixes the logged defects (or argues why not).
6. **Follow-up** — moderator confirms all defects were addressed; collects metrics.

---

## Roles

| Role | Responsibility |
|---|---|
| **Moderator** | Runs the meeting, distributes work, ensures quality of process |
| **Author** | Created the document under review |
| **Scribe** | Takes notes / logs defects |
| **Reviewers** ("checkers", "inspectors") | Find defects |
| **Manager** (sometimes) | Decides on reviews, allocates resources |

---

## Types of reviews

| Type | Formality | Key features |
|---|---|---|
| **Informal review** | None | Pair check, no documentation, fast. Common early in lifecycle. |
| **Walkthrough** | Low | Author *presents* to reviewers with mixed backgrounds. Educational. Scenarios used to validate. No pre-meeting prep needed. |
| **Technical review** | Medium | Discussion meeting. Defect detection + peer-level technical decisions. |
| **Inspection** | High | Most formal. Pre-meeting preparation, rules + checklists, trained roles, metrics, formal log. Goal: improve doc quality + improve the *process*. |

### Success factors
- Find a champion. Pick what really counts (high ROI). Plan and track. Train participants. Manage people issues. Follow rules but stay simple. Continuously improve. Report results. **Just do it.**

---

## Static analysis (by tools)

Tool-based examination of code/designs without execution:
- Catches defects similar to compilers but deeper.
- Cheaper and earlier than dynamic testing.
- Often noisy by default (many false positives) — needs tuning per codebase.

### What static analysis checks

**Coding standards** — programming rules, naming conventions, layout. Many tools enforce these.

**Code metrics** — used to find candidates for review:
- Comment frequency
- Depth of nesting
- **Cyclomatic complexity** (number of independent paths through code)
- Pareto: 80% of problems live in 20% of code → focus there.
- Decision trees can simplify complex logic.

**Code structure**
- **Control flow** — sequence of execution. Detects unreachable code, deep nesting.
- **Data flow** — which data items are accessed/initialised. Catches uninitialised variables.
- **Data structure** — organisation of data.

Defects easier to find statically than dynamically: dead code, uninitialised vars, off-spec interfaces, cyclomatic hot-spots.

---

## Likely exam topics

- Compare informal review, walkthrough, technical review, **inspection** — increasing formality.
- Phases of a formal review (especially **kick-off**, **preparation**, **rework**, **follow-up**).
- Roles: moderator, author, scribe, reviewer.
- Severity levels: critical / major / minor.
- What static analysis can find that dynamic testing struggles with (uninitialised vars, dead code, complexity hotspots).
- Define **cyclomatic complexity**.
