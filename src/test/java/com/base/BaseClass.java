package com.base;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.utility.Constants;
import com.utility.ExtentReportsUtility;
import com.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	protected static WebDriver driver = null;
	protected static Logger logger=null;
	protected static WebDriverWait wait=null;
	protected static ExtentReportsUtility extentreport=ExtentReportsUtility.getInstance();
	
	
	@BeforeTest
	public void setUpBeforeTest() {
		//extentReport.logTestInfo("beforeTest method has started");
		System.out.println("inside @BeforeTest method");
		logger=LogManager.getLogger(BaseClass.class.getName());
		//extentReport=new ExtentReportsUtility();
		//extentReport.startExtentReport();
	}
	
	@BeforeMethod
	@Parameters("browserName")
	public void setupBeforeTestMethod(@Optional("chrome") String browserName) {
		extentreport.startSingleTestReport("testcase");
		logger.info("Started test script");
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String url = propertiesUtility.getPropertyValue("url");
		getDriverInstance(browserName);
		goToUrl(url);
	}
	public void tearDownAfterTest() {
		extentreport.endReport();
	}
	
	@AfterMethod
	public void AfterTearDownMethod() {
		closeBrowser();
	}
	public static void validLoginMethod() {
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.valid.userid");
		String pass = propertiesUtility.getPropertyValue("login.valid.password");

		WebElement username = driver.findElement(By.id("username"));
		enterText(userid, username);

		WebElement passWord = driver.findElement(By.id("password"));
		enterText(pass, passWord);
		WebElement login=driver.findElement(By.id("Login"));
		//login.click();
		clickElement(login, "login clicked");


	}
	

	public static void enterText(String text,WebElement element ) {
		if(element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
		}else {
			logger.info(text + "element is not displayed");
			
		}
		
	}

	public static void getDriverInstance(String browserName) {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("not entered proper browsername");

		}

	}
	public WebDriver returnDriverInstance() {
		return driver;
	}
	public static void clearElement(WebElement element, String ele) {
		if (element.isDisplayed()) {
			element.clear();
			logger.info("pass:" + ele + "  element cleared");
			extentreport.logTestInfo("pass:" + ele + "  element cleared");

		} else {
			logger.info("fail:" + ele + " element not displayed");
			extentreport.logTestInfo("fail:" + ele + " element not displayed");

		}
	}
	public static void clickElement(WebElement element, String ele) {
		if (element.isDisplayed()) {
			element.click();
			logger.info("pass:" + ele + "  element clicked");
			extentreport.logTestInfo("pass:" + ele + "  element clicked");


		} else {
			logger.info("fail:" + ele + " element not displayed");
			extentreport.logTestInfo("fail:" + ele + " element not displayed");

		}
	}
	public  WebElement selectFromList(List<WebElement> li,String text) {
		WebElement ele=null;
		for(WebElement i:li) {
			if(i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected" + i.getText());
				ele=i;
			}
		}
		return ele;
		
	}
	
	
	public static void alertTest(String expected ) {
		Alert alert = driver.switchTo().alert();
		 String actual = alert.getText();
		alert.accept();
		if (expected.equals(actual)) {
			logger.info("Test case passed");
		} else
			logger.info("testcase failed");
	}
	public static void alertTes() {

		Alert alert = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = driver.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage);
		//Thread.sleep(5000);

		// Accepting alert
		alert.accept();

	}
	public static void deletePhoto() throws InterruptedException {
		WebElement photoMod = driver.findElement(By.id("displayBadge"));
		Thread.sleep(3000);
		photoMod.click();
		Thread.sleep(5000);
	
		Thread.sleep(3000);
		WebElement deletePhoto = driver.findElement(By.id("deletePhoto"));
		deletePhoto.click();
		WebElement sureDelete=driver.findElement(By.id("simpleDialog0button0"));
		sureDelete.click();
		
	}
	public static void goToUrl(String url) {
		logger.info("going to url"+ url);
		driver.get(url);
	}
	public static  String getPageTitle() {
		logger.info("getting page title");
		return driver.getTitle();
		
	}
	public static void refershPage() {
		logger.info("Refreshing the page");
		driver.navigate().refresh();
	}
	public static void closeBrowser() {
		logger.info("closing the browser");

		driver.close();
	}
	public static void waitUntiPageloads() {
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	public static void mouseoverAction(WebElement ele,String obj) {
		Actions actions = new Actions(driver);
       actions.moveToElement(ele);
	    actions.click().build().perform();
		System.out.println(" cursor moved to web element and clicked"+obj);

	}

	public static void moveToElementAction(WebElement ele, String obj) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element "+obj);
	}
	public static void ContextClickAction(WebElement ele, String obj) {
		Actions action=new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click persormed on web element "+obj);
	}
	
	public static void WaitUntilElementIsVisible(WebElement ele, String obj) {
		logger.info("waiting for an web element "+obj+" for its visibility");
		wait=new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOf(ele));
		 
		 
	}
	
	public static void waitUntilAlertIsPresent() {
		System.out.println("waiting for alert to be present");
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator, String objName) {
		System.out.println("waiting for an web element "+objName+" to be clickable");
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static void waitFluentForVisibility(WebElement ele, String objName) {
		 Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				 					.withTimeout(Duration.ofSeconds(30))
				 					.ignoring(NoSuchElementException.class);				
				wait.until(ExpectedConditions.visibilityOf(ele));
	}

	/*
	 * public static void screenShotOfThePage() throws IOException { String date=new
	 * SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date()); String
	 * curDir=System.getProperty("user.dir"); TakesScreenshot
	 * screenShot=(TakesScreenshot)driver; File
	 * imgFile=screenShot.getScreenshotAs(OutputType.FILE); File destFile=new
	 * File(curDir + "/screenShots/"+ date + ".png"); try {
	 * FileHandler.copy(imgFile, destFile);} catch(IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	public  String getScreenshotOfThePage(WebDriver driver) {
		/*
		 * if(driver==null) { driver = returnDriverInstance(); }
		 */
		String date=new SimpleDateFormat("yyyy-MM-dd_HH_mm").format(new Date());
		String curDir=System.getProperty("user.dir");
		TakesScreenshot screenShot=(TakesScreenshot)driver;
		File imgFile=screenShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(Constants.SCREENSHOTS_DIRECTORY_PATH+"screenShots"+date+".png");
		System.out.println(destFile);
		try {
		FileHandler.copy(imgFile, destFile);}
		catch(IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
		
	}
	
}