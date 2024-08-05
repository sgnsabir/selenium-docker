package com.sabir.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sabir.pages.AbstractPage;

public class DashboardPage extends AbstractPage {
	private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "monthly-earning")
	private WebElement monthlyEarningElement;

	@FindBy(id = "annual-earning")
	private WebElement annualEarningElement;

	@FindBy(id = "profit-margin")
	private WebElement profitMarginElement;

	@FindBy(id = "available-inventory")
	private WebElement availableInventoryElement;

	@FindBy(css = "[type='search']")
	private WebElement searchInputElement;

	@FindBy(id = "dataTable_info")
	private WebElement searchResultCountElement;

	@FindBy(css = "img.img-profile")
	private WebElement userProfilePictureElement;

	@FindBy(css = "[data-target='#logoutModal']")
	private WebElement logoutLinkElement;

	@FindBy(css = "a.btn-primary")
	private WebElement modalLogoutButton;

	@FindBy(css = "h1.h4")
	private WebElement loginWelecomeText;

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.availableInventoryElement));
		return this.availableInventoryElement.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		return this.monthlyEarningElement.getText();
	}
	
	public String getAnnualEarning() {
		return this.annualEarningElement.getText();
	}
	
	public String getProfitMargin() {
		return this.profitMarginElement.getText();
	}
	
	public String getAvailableInventory() {
		return this.availableInventoryElement.getText();
	}
	
	public void searchOrderHistoryBy(String keyword) {
		this.searchInputElement.sendKeys(keyword);
		this.wait.until(ExpectedConditions.visibilityOf(searchResultCountElement));
	}
	
	public int getSearchResultCount() {
		int resultCount=0;
		String resultCountText = this.searchResultCountElement.getText();
		String count = resultCountText.split(" ")[5];
		resultCount = Integer.parseInt(count);
		log.info("Results count: {}", count);
		return resultCount;
	}
	
	public void logout() {
		this.userProfilePictureElement.click();
		this.logoutLinkElement.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
		this.modalLogoutButton.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.loginWelecomeText));
		
	}

}
