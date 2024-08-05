package com.sabir.tests.vendorportal;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.sabir.pages.vendorportal.DashboardPage;
import com.sabir.pages.vendorportal.LoginPage;
import com.sabir.tests.BaseTest;
import com.sabir.tests.vendorportal.model.VendorPortalTestData;
import com.sabir.util.Config;
import com.sabir.util.Constants;
import com.sabir.util.JsonUtil;

public class VendorPortalTest extends BaseTest{
	private LoginPage loginPage;
	private DashboardPage dashboard;
	private VendorPortalTestData testData;
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setPageObject(String testDataPath) throws StreamReadException, DatabindException, IOException {
		this.loginPage = new LoginPage(driver);
		this.dashboard = new DashboardPage(driver);
		this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
	}
	
	@Test(priority=1)
	public void loginTest() {
		loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
		Assert.assertTrue(loginPage.isAt());
		loginPage.login(testData.username(), testData.password());
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void dashboardTest() {
		Assert.assertTrue(dashboard.isAt());
		//finance metrics validation		
		Assert.assertEquals(dashboard.getMonthlyEarning(), testData.monthlyEarning());		
		Assert.assertEquals(dashboard.getAnnualEarning(), testData.annualEarning());		
		Assert.assertEquals(dashboard.getProfitMargin(), testData.profitMargin());		
		Assert.assertEquals(dashboard.getAvailableInventory(), testData.availableInventory());
		// other validation
		
		dashboard.searchOrderHistoryBy(testData.searchKeyword());
		Assert.assertEquals(dashboard.getSearchResultCount(), testData.searchResultsCount());		
	}
	
	@Test(dependsOnMethods = "dashboardTest")
	public void logoutTest() {
		dashboard.logout();
		Assert.assertTrue(loginPage.isAt());
	}

}
