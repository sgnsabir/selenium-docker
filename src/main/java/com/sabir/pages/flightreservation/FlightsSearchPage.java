package com.sabir.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.sabir.pages.AbstractPage;

public class FlightsSearchPage extends AbstractPage {

	public FlightsSearchPage(WebDriver driver) {
		super(driver);
	}
	
	//will be modified later
	
	@FindBy(id="passengers")
	private WebElement passengerSelectDropDown;
	
	@FindBy(id="search-flights")
	private WebElement searchFlightsButton;

	@Override
	public boolean isAt()  {
		this.wait.until(ExpectedConditions.visibilityOf(searchFlightsButton));
		return this.searchFlightsButton.isDisplayed();
	}
	
	public void passengerSelectDropDown(String noOfPassengers) {
		Select passengers = new Select(this.passengerSelectDropDown);
		passengers.selectByValue(noOfPassengers);
	}
	
	public void searchFlights() {
		this.searchFlightsButton.click();
	}
	

}
