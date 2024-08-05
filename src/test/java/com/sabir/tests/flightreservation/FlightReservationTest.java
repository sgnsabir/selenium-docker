package com.sabir.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sabir.pages.flightreservation.FlightConfirmationPage;
import com.sabir.pages.flightreservation.FlightsSearchPage;
import com.sabir.pages.flightreservation.FlightsSelectionPage;
import com.sabir.pages.flightreservation.RegistrationConfirmationPage;
import com.sabir.pages.flightreservation.RegistrationPage;
import com.sabir.tests.BaseTest;
import com.sabir.tests.flightreservation.model.FlightReservationTestData;
import com.sabir.util.Config;
import com.sabir.util.Constants;
import com.sabir.util.JsonUtil;

public class FlightReservationTest extends BaseTest{
	RegistrationPage registrationPage;
	RegistrationConfirmationPage registrationConfirmPage;
	FlightsSearchPage flightsSearchPage;
	FlightsSelectionPage flightSelectionPage;
	FlightConfirmationPage flightConfirmPage;
	
	private FlightReservationTestData testData;
	

	@BeforeTest
	@Parameters("testDataPath")
	public void setPageObjects(String testDataPath) {
		this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
		//page objects
		registrationPage = new RegistrationPage(driver);
		registrationConfirmPage = new RegistrationConfirmationPage(driver);
		flightsSearchPage = new FlightsSearchPage(driver);
		flightSelectionPage = new FlightsSelectionPage(driver);
		flightConfirmPage = new FlightConfirmationPage(driver);
	}

	@Test(priority = 1)
	public void userRegistrationTest() {		
		registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(registrationPage.isAt());
		registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
		registrationPage.enterUsercredentials(testData.email(), testData.password());
		registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
		registrationPage.register();
	}

	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		Assert.assertTrue(registrationConfirmPage.isAt());
		Assert.assertEquals(registrationConfirmPage.getFirstName(), testData.firstName());
		registrationConfirmPage.goToFlightsSearch();
	}

	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest() {
		Assert.assertTrue(flightsSearchPage.isAt());
		flightsSearchPage.passengerSelectDropDown(testData.passengersCount());
		flightsSearchPage.searchFlights();
	}

	@Test(dependsOnMethods = "flightSearchTest")
	public void flightsSelectionTest() {
		Assert.assertTrue(flightSelectionPage.isAt());
		flightSelectionPage.selectFlights();
		flightSelectionPage.confirmFlights();
	}

	@Test(dependsOnMethods = "flightsSelectionTest")
	public void flightConfirmationTest() {
		Assert.assertTrue(flightConfirmPage.isAt());
		Assert.assertEquals(flightConfirmPage.getPrice(), testData.expectedPrice());
	}

	@AfterTest
	public void tearDown() {
		this.driver.quit();
	}
}
