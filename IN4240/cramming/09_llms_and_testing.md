# 9 — LLMs and Software Testing

**TL;DR:** LLMs are powerful **tools** for testers — generating tests, drafting scripts, predicting risky changes — but the **tester remains accountable** for quality. LLM-written code needs *more* systematic testing because it's non-deterministic, may have subtle logic errors, security blind spots, and outdated patterns.

---

## How testers use LLMs today

ISTQB **CT-GenAI** (Certified Tester — Generative AI Testing) recognises four core uses:

1. **Test case generation** — from requirements, user stories or API specs via structured prompts.
2. **Test data synthesis** — realistic edge cases, boundary values, equivalence partitions.
3. **Test script drafting** — Cypress, Playwright, pytest scaffolding from natural language.
4. **Defect prediction** — analysing code changes to prioritise where to test.

### Prompt engineering pattern (example)
```
Role:        You are a senior QA engineer.
Context:     REST API endpoint /users.
Instruction: Generate boundary value test cases for the age field
             (integer, 0–150).
Constraints: Include valid, invalid, and edge cases.
Output:      Table with input, expected result, and test type.
```

### Risk: hallucination
LLMs can produce **plausible but incorrect** test cases. Always verify against the specification.

> Key ISTQB concept: the **tester remains accountable** for evaluating and validating all LLM-generated output.

---

## Why LLM-generated *code* needs extra scrutiny

| Reason | Explanation |
|---|---|
| **Non-determinism** | The same prompt may give different code each time. |
| **Subtle logic errors** | Code compiles and "looks right" but fails on edge cases. |
| **Security blind spots** | May produce injection-prone code, hardcoded secrets, weak crypto. |
| **Outdated patterns** | Training data may contain deprecated APIs, libs with **CVEs**. |

### Recommended test strategy for LLM code

| Technique | Focus |
|---|---|
| Code review | Logic, security, style |
| Unit tests | Boundary values |
| Static analysis | SAST tools (e.g., SonarQube) |
| Mutation testing | Test-suite adequacy |
| Fuzzing | Unexpected inputs |
| Dependency scan | Known CVEs |

> **Key insight:** The faster code is written (by humans **or** AI), the more important systematic testing becomes. Velocity without verification is **technical debt**.

---

## From software testing to vulnerability discovery

Same techniques, escalating questions:

```
Functional testing → Security testing → Vulnerability research
"Does it work?"     "Can it be exploited?"     "Find unknown flaws"
EP, BVA, decision   Pen-testing, fuzzing,      Zero-day discovery,
tables              SAST, DAST                 exploit dev, AI-assisted
```

- **SAST** = Static Application Security Testing
- **DAST** = Dynamic Application Security Testing
- **Zero-day** = a vulnerability where developers have had "zero days" to patch.

### Shared foundations
Boundary value analysis finds buffer overflows. Equivalence partitioning finds input-validation gaps. Control-flow testing reveals authentication bypasses. Same fundamentals, different stakes.

### The AI amplifier
LLMs can apply these techniques **at scale** — reading millions of LoC, reasoning about complex interactions, finding flaws decades of human review missed.

---

## Timeline (state of the art)

- **2014** — Google Project Zero founded; elite humans hunt zero-days.
- **2023–24** — LLMs begin assisting with code review and simple vuln detection.
- **2025** — Google "Big Sleep" finds first real-world zero-day with an LLM agent.
- **April 2026** — Anthropic's **Claude Mythos Preview** autonomously discovers *thousands* of zero-days across major OSes and browsers.

### Reported capability jump
- Project Zero (humans): ~20–30 zero-days/year.
- Claude Opus 4.6: 2 successful Firefox JS-engine exploit developments (v147).
- Claude Mythos Preview: **181 working exploits** on the same benchmark.
- Notable Mythos findings: 27-year-old RCE in OpenBSD; 16-year-old flaw in FFmpeg; memory corruption in a *memory-safe* VM monitor.

> Not apples-to-apples — but a striking illustration of scale.

### Project Glasswing (April 2026)
- Anthropic's restricted program for using Mythos Preview in **defensive cybersecurity**.
- 40+ critical-software organisations participate; up to **$100M** in Mythos credits + **$4M** in donations to OSS security orgs.
- Important limits: does **not** include CERT/CC (the long-standing coordination tradition for responsible disclosure) or Project Zero (elite hunting + strong disclosure pressure).
- For testers: more AI-assisted vulnerability discovery, but humans still own **reproduction, prioritisation, reporting, coordination**. Tester role expands toward managing **bug-discovery pipelines**.

---

## TDD vs prompt generation (HumanEval study)

Three pipelines tested on 164 HumanEval Python tasks:

| Pipeline | What happens |
|---|---|
| **Iterative (enforced TDD)** | LLM writes one test → run → write code that passes it → next test → repeat. |
| **Batch (all tests first)** | LLM writes the entire test suite, then implementation, then revises if tests fail. |
| **NoTDD (direct prompt)** | LLM is asked directly for a solution; no tests; baseline. |

### Results (pass@1, all out of 164)

| Model | Iterative | Batch | NoTDD |
|---|---|---|---|
| Qwen3:8b | **0.884** | 0.872 | 0.842 |
| gpt-oss:20b | 0.945 | **0.951** | 0.933 |
| Gemma3:12b | **0.842** | 0.811 | 0.835 |
| Gemma3:27b | **0.878** | 0.848 | 0.860 |

**Takeaway:** Iterative TDD usually beats NoTDD. Batch sometimes wins. Test-first feedback loops are useful even for LLMs.

---

## Key takeaways

- LLMs are **tools** for testers — but the tester owns the quality judgement.
- LLM-generated code needs **adapted test strategies** — extra security/correctness focus.
- Software testing and vuln research share the **same foundational techniques**.
- AI has crossed a threshold where it can find vulnerabilities **faster and at greater scale** than human experts.
- Testers must understand AI capabilities and limitations — it's now a **core competence**.

### Two ISTQB AI certifications

| Cert | Focus |
|---|---|
| **CT-AI** (2021) | Testing **of** AI systems — data quality, bias, non-determinism, ML metrics. |
| **CT-GenAI** (2025) | Testing **with** generative AI — prompt engineering, LLM evaluation, RAG, integration. |

Both build on the **Foundation Level**.

---

## Discussion / likely exam topics

- Should AI-generated code be labelled?
- Who is liable when an AI-written test misses a critical bug?
- Is the tester's role expanding or shrinking with AI?
- Why LLM-generated code requires more testing, not less.
- The four uses of LLMs in testing (case gen, data synthesis, script drafting, defect prediction).
- Difference between **CT-AI** and **CT-GenAI**.
- TDD vs NoTDD prompting — what the HumanEval data showed.
