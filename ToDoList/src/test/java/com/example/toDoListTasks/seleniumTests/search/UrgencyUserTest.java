package com.example.toDoListTasks.seleniumTests.search;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.example.toDoListTasks.seleniumTests.pages.UrgencyPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class UrgencyUserTest {

	private static String URL = "http:localhost:8080";
	private static WebDriver driver;
	private static ExtentReports report;
	private static ExtentTest test;
	
	@BeforeClass
	public static void init() {
		//new ExtentReport("location", override/replace existing)
		report = new ExtentReports("src/test/java/reports", true);
		//create new test 'name'
		test = report.startTest("Demo");
		
		//identifies which driver we are using
		System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");

		ChromeOptions fOptions = new ChromeOptions();
		//sets our driver window headless
		fOptions.setHeadless(false);
		
//		//configuration for cookie managements, where 2/true is on
//		fOptions.setCapability("profile.default_content_setting_values.cookies", 2);
//		fOptions.setCapability("network.cookie.cookieBehavior", 2);
//		fOptions.setCapability("profile.block_third_party_cookies", true);
		
		driver = new ChromeDriver(fOptions);
		driver.manage().window().setSize(new Dimension(1920, 1080));
	}
	
	@Before
	public void foundation() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(URL + "/urgency");
	}
	
//	@Test
//	public void CreateUrgencyTest() {	
//		
//		UrgencyPage nav = PageFactory.initElements(driver, UrgencyPage.class);
//		UrgencyPage list = PageFactory.initElements(driver, UrgencyPage.class);
//		
//		nav.createUrgencyButton();
//		
//		list.sendKeys("List");
//		
//		assertEquals(true, driver.getPageSource().contains("List"));
//	}
	
	@AfterClass
	public static void TearDown() {
		driver.quit();
		
		//lets report know test has been completed
		report.endTest(test);
		//sends to report 
		report.flush();
		//close down
		report.close();
	}
	
	@After
	public void CloseDown() {
		driver.close();
	}
}
