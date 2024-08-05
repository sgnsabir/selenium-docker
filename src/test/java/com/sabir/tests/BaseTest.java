package com.sabir.tests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.sabir.listener.TestListener;
import com.sabir.util.Config;
import com.sabir.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({TestListener.class})
public abstract class BaseTest {
	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
	protected WebDriver driver;
	
	@BeforeSuite
	public void setupConfig() {
		Config.initialize();
	}
	
	@BeforeTest
	public void setDrive(ITestContext ctx) throws MalformedURLException, URISyntaxException {
		this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
		ctx.setAttribute(Constants.DRIVER, this.driver);
	}
	
	private WebDriver getRemoteDriver() throws MalformedURLException, URISyntaxException {
		Capabilities capabilities = new ChromeOptions();
		if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			capabilities = new FirefoxOptions();
		}
		if(Constants.EDGE.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			capabilities = new EdgeOptions();
		}
		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = Config.get(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);
		log.info("grid url: {}", url);
		return new RemoteWebDriver(new URI(url).toURL(), capabilities);
	}
	
	private WebDriver getLocalDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}
	
	@AfterTest
	public void tearDown() {
		this.driver.quit();
	}
	
}