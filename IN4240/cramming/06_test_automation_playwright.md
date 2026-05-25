# 6 — Test Automation & Playwright (Itera guest lecture)

**TL;DR:** Test automation = software (separate from the SUT) that executes tests automatically. It pays back through speed, reuse and CI integration, but only when applied to the right things. Playwright is a modern Microsoft framework for end-to-end web/API testing.

---

## What is test automation?

> Use of specialised software, separate from the software being tested, to automatically execute tests.

### Levels (test pyramid, bottom = most automated)

```
        [GUI]        ← few, slow, high trust in whole system
      [Integration]
   [Unit / Component] ← many, fast, high trust in components
```

- Unit/integration tests are usually written by **developers**.
- GUI/end-to-end is usually owned by **test automation engineers**.

---

## Why automate

| Benefit | Why it matters |
|---|---|
| **Increased efficiency** | Tests run faster + more often → quicker feedback. |
| **Improved accuracy** | No human typos / fatigue. |
| **Reusability** | Reuse across versions, browsers, environments. |
| **Continuous testing** | Enables CI/CD. |
| **Documentation** | Automated tests *are* executable specs / reports. |

## Why not (always) automate

- **Initial investment** — setup costs time.
- **Additional costs** — licences, infrastructure.
- **Technical skill required.**
- **Maintenance burden** — tests rot when UI changes.
- **Distraction from real testing objectives** — chasing flaky tests, not bugs.
- **False alarms** — flakiness lowers trust.

xkcd "is it worth the time?" lesson: don't spend 10 hours automating a 10-minute task you do once a year.

### What you should *not* automate
- Tests that **can't** be automated (e.g., subjective UX).
- Tests that **shouldn't** (low risk, runs once, exploratory work).
- Systems that change drastically/often, are about to be retired, or are tiny.

### Combine automation with manual

| Risk | Mission/safety-critical | Non-mission/non-safety |
|---|---|---|
| High | Heavy automation + heavy black-box; some exploratory | Mainly exploratory, lighter automation |
| Medium | Mixed | Exploratory leads |
| Low | Light all-around | Skip automation; just exploratory |

Manual testing — especially **exploratory** — is not replaced by automation.

---

## Three main automation approaches

### 1. Capture & Playback
- Tool records user actions, replays them.
- Easy to set up; good for non-coders.
- **Lacks abstraction** — recording is pixel-/locator-specific, breaks on screen-size or DOM changes.
- High maintenance.
- Example: original Selenium IDE.

### 2. Structured scripting
- Start from manual procedures → translate into scripts using a **scripting library** (functions, classes, Page Objects).
- **Has abstraction**; easier to adapt and maintain.
- Requires programming skills + initial investment in the library.
- Examples: **Playwright**, **Cypress**.

### 3. Model-based scripting
- Auto-generate tests from a **model** of the system (state machine, UML class diagram, business model).
- Technology-independent; can reuse across implementations.
- Requires modelling expertise; tooling is not yet mainstream.
- Examples: **Tricentis Tosca**, **SpecFlow**.

---

## Test automation in agile SDLC

### Challenges
- Rapid requirement changes ("respond to change over following a plan").
- Maintaining speed under tight cycles → requires resource allocation + initial investment.
- Balancing customer value vs adequate coverage.
- Tests grow more complex as features grow.
- Cross-team collaboration.

### Test data
- **Synthetic test data** = artificially created data that mimics real-world data; created for non-prod environments.
- Sources: build them yourself, fetch from other teams, fetch from external systems (e.g., Norwegian *Tenor testdata*).

### Enabling agility
- Continuous process; releases stay fast and high-quality.
- Be mindful of **what** is automated — minimize initial investment, build in iterations.

### Who works on automation?
- Setup varies per project.
- Design / impl / maintenance is technical.
- Needs domain expertise + testing skills.
- **Cooperation** between QA and developers (e.g., dev provides a mock stub for QA's BankID flow).

---

## Choosing a framework

Considerations:
- Paid vs open-source.
- Continued support from vendor / community.
- Active community.
- Compatibility with chosen dev framework / language.
- Existing team experience.
- **No silver bullet.**

### Languages commonly used
Ruby, Python, JavaScript / TypeScript, Java, C#, PHP, Perl.

### Common frameworks
Cypress, Protractor, Serenity BDD, Cucumber, NightWatch, RobotFramework, WebDriverIO, RedWood, **Playwright**.

---

## Playwright in particular

- Created by **Microsoft, 2020**.
- **JavaScript / TypeScript** based, end-to-end testing framework.
- Front-end tool for **web + API** testing.
- **Free & open-source.**

### Features
- Cross-browser support (Chromium, Firefox, WebKit).
- Built-in screenshots and video recording.
- Multiple language bindings (TS/JS, Python, .NET, Java).
- Parallel execution.
- Network manipulation / stubbing.
- Easy CI integration.

### Code shape
```ts
import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
  await page.goto('https://demo.playwright.dev/todomvc');
});

test('should allow adding todo items', async ({ page }) => {
  const newTodo = page.getByPlaceholder('What needs to be done?');
  await newTodo.fill('buy some cheese');
  await newTodo.press('Enter');
  await expect(page.getByTestId('todo-title')).toHaveText(['buy some cheese']);
});
```

---

## Likely exam topics

- Compare **capture & playback**, **structured scripting**, **model-based scripting** — strengths and weaknesses.
- The test automation pyramid; why most automated tests should be unit-level.
- Benefits and risks of automation.
- When **not** to automate.
- Why "agile + automation" is harder than it looks.
- Synthetic test data — definition and example sources.
- Playwright — origin, language, what it tests.
