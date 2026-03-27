import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {

  await page.goto('https://parabank.parasoft.com/parabank/index.htm');
  await expect(page).toHaveTitle(/ParaBank/);

  await page.fill('input[name="username"]', 'RobertD');
  await page.fill('input[name="password"]', 'Robert');

  await page.click('input[value="Log In"]');

  await expect(page.locator('text=Account Services')).toBeVisible();
});

test.afterEach(async ({ page }) => {
  await page.click('a[href*="logout"]');

  await expect(page.locator('input[name="username"]')).toBeVisible();
});

test.describe('Customer Services', () => {

  test('Open New Account - should open a savings account', async ({ page }) => {
    await page.click('a[href*="openaccount"]');

    await expect(page.locator('h1, h2').filter({ hasText: /open new account/i })).toBeVisible();

    await page.selectOption('#type', { label: 'SAVINGS' });

    await page.waitForFunction(() => {
      const select = document.querySelector('#fromAccountId') as HTMLSelectElement;
      return select && select.options.length > 0;
    });

    await page.selectOption('#fromAccountId', { index: 0 });

    await page.click('input[value="Open New Account"]');

    await expect(page.locator('h1.title', { hasText: 'Account Opened!' })).toBeVisible({ timeout: 10000 });
  });

  test('Request Loan - should submit a loan application', async ({ page }) => {
    await page.click('a[href*="requestloan"]');

    await expect(page.locator('h1.title', { hasText: 'Apply for a Loan' })).toBeVisible();

    await page.fill('#amount', '100');
    await page.fill('#downPayment', '1');

    await page.waitForFunction(() => {
      const select = document.querySelector('#fromAccountId') as HTMLSelectElement;
      return select && select.options.length > 0;
    });

    await page.selectOption('#fromAccountId', { index: 0 });

    await page.click('input[value="Apply Now"]');

    await expect(page.locator('h1.title', { hasText: 'Loan Request Processed' })).toBeVisible({ timeout: 10000 });
  });

  test('Transfer Funds - should transfer funds between accounts', async ({ page }) => {
    await page.click('a[href*="transfer"]');

    await expect(page.locator('h1.title', { hasText: 'Transfer Funds' })).toBeVisible();

    await page.fill('#amount', '500');

    await page.waitForFunction(() => {
      const select = document.querySelector('#fromAccountId') as HTMLSelectElement;
      return select && select.options.length > 0;
    });
    await page.selectOption('#fromAccountId', { index: 0 });

    await page.waitForFunction(() => {
      const select = document.querySelector('#toAccountId') as HTMLSelectElement;
      return select && select.options.length > 0;
    });
    await page.selectOption('#toAccountId', { index: 0 });

    await page.click('input[value="Transfer"]');

    await expect(page.locator('h1.title', { hasText: 'Transfer Complete!' })).toBeVisible({ timeout: 10000 });
  });

});