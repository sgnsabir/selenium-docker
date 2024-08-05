package com.sabir.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sabir.pages.AbstractPage;

public class LoginPage extends AbstractPage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="username")
	private WebElement usernameInput;
	
	@FindBy(id="password")
	private WebElement passwordInput;
	
	@FindBy(id="login")
	private WebElement loginButton;

	@Override
	public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginButton));
        return this.loginButton.isDisplayed();
	}
	
	public void login(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
	}

}
