package com.sabir.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sabir.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {

	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="p.mt-3")
	private WebElement congratsText;
	
	@FindBy(id="go-to-flights-search")
	private WebElement goToFlightsSearchButton;
	
	public void goToFlightsSearch() {
		this.goToFlightsSearchButton.click();
	}

	@Override
	public boolean isAt()  {
		this.wait.until(ExpectedConditions.visibilityOf(goToFlightsSearchButton));
		return this.goToFlightsSearchButton.isDisplayed();
	}
	
	public String getFirstName() {
		String firstName = congratsText.getText().split("!")[0].split(" ")[1];		
		return firstName;
	}
	

}
