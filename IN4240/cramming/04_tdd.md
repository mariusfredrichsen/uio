# 4 — Test-Driven Development (TDD)

**TL;DR:** Write the test *before* the code. Cycle: **Red → Green → Refactor.** Forces small steps, simpler design, testable code, and immediate feedback.

---

## What is TDD?

- Write a failing test that describes a piece of desired behaviour.
- Write the **minimum** code to make it pass.
- Refactor without changing behaviour, keeping all tests green.
- Repeat.
- Mostly applied at the **unit/component** level (e.g., JUnit in Java).

### Advantages
- **Fast feedback** — you know within seconds if a change breaks things.
- **Simpler design** — only what's needed exists; emerges step by step.
- **Safer changes** — strong test suite catches regressions.
- **Testable code** — testability is forced from day one (no hidden globals, fewer hard dependencies).
- Acts as **executable documentation** of intended behaviour.

---

## The TDD cycle

```
   ┌──────── Refactor ◄────────┐
   ▼                           │
  Red ─────► Green ────────────┘
 (failing)  (passing)
```

| Phase | What you do |
|---|---|
| **Red** | Write a small test for behaviour that doesn't yet exist; run it; see it fail. |
| **Green** | Write the *minimum* production code to make the test pass. Don't over-engineer. |
| **Refactor** | Improve names, remove duplication, simplify — **without changing behaviour**. Tests still pass. |

---

## JUnit basics

Used in cyber-dojo exercises (Leap year, FizzBuzz).

- **Annotations:** `@Test` marks a method as a test case. (Also `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`.)
- **Assertions:**
  - `assertEquals(expected, actual)`
  - `assertTrue(condition)` / `assertFalse(condition)`
  - `assertAll(...)` — group multiple assertions; reports all failures, not just the first.
  - `assertThrows(Exception.class, () -> ...)` for expected exceptions.
- **Good test name** describes the **scenario** + the **expected result**.
  - Bad: `test1`
  - Good: `leapYear_DivisibleBy400_IsLeap`

---

## Cyber-dojo example rules

### Leap year
- Divisible by 4 → leap.
- Divisible by 100 → *not* leap.
- Divisible by 400 → leap (overrides the 100 rule).

Step-by-step TDD: start with smallest behaviour (e.g., `4 → leap`), grow rules one test at a time.

### FizzBuzz
- Multiples of 3 → "Fizz".
- Multiples of 5 → "Buzz".
- Multiples of both → "FizzBuzz".
- Otherwise → the number.

---

## Good practices

- Run tests **frequently**.
- Each test verifies **one thing**.
- Refactor regularly **while green** — don't refactor on red.
- Small, frequent commits.
- A failing test should fail for *one obvious reason*.

## Common pitfalls

- **Steps too big** — test covers too much, hard to make pass.
- **Writing production code first** "to save time" — defeats the purpose.
- **Too implementation-dependent tests** — they break on any refactor.
- **Insufficient refactoring** — tests pass but the code is messy and brittle.
- **"I trust that it works"** — rarely true long-term, especially for edge cases.

---

## TDD vs LLM-generated code (link to lecture 8)

- **Iterative TDD** with LLMs (write one test → make pass → next test) outperforms one-shot prompting on HumanEval benchmarks for many models — see `09_llms_and_testing.md`.

---

## Likely exam topics

- Describe the **Red-Green-Refactor** cycle and the goal of each step.
- Why "minimum code" matters in the green step.
- TDD advantages and pitfalls.
- The leap year rules as a TDD example — what tests would you write first?
- Difference between `assertEquals` and `assertAll`.
- Why a test should verify only one thing.
