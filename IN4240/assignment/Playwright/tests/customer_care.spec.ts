import { test, expect } from "@playwright/test";

test("Customer Care skjema - innsending og bekreftelse", async ({ page }) => {
	await page.goto("https://parabank.parasoft.com/parabank/index.htm");

	await page.locator('#headerPanel a[href*="contact.htm"]').click();

	await expect(
		page.getByRole("heading", { name: "Customer Care" }),
	).toBeVisible();
	await expect(page.locator("#contactForm")).toBeVisible();

	await page.locator("#name").fill("Robert");
	await page.locator("#email").fill("Robert@robert.no");
	await page.locator("#phone").fill("32323232");
	await page.locator("#message").fill("hei hei");

	await page.locator('input[value="Send to Customer Care"]').click();

	await expect(page.getByText("Thank you")).toBeVisible();
	await expect(page.getByText("Robert")).toBeVisible();
});
