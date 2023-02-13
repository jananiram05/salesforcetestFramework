package com.salesforcetest;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.ITestResult;



import com.base.BaseClass;
import com.utility.PropertiesUtility;
@Listeners(com.utility.TestEventListnersutility.class)
public class SalesforceTestAutomation extends BaseClass {

	static String viewName = null;

	static Random random = new Random();

	@Test
	@Parameters("browserName")
	public static void salesforceLoginErrorTc1() throws InterruptedException {
		
		logger.info("Inside saesforce error login");
		extentreport.logTestInfo("Inside saesforce error login");
		
		//extentreport.logTestInfo("Inside saesforce error login");
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.valid.userid");
		String password = propertiesUtility.getPropertyValue("login.empty.password");

		String expected = "Please enter your password.";

		WebElement username = driver.findElement(By.id("username"));
		enterText(userid, username);

		WebElement passWord = driver.findElement(By.id("password"));
		enterText(password, passWord);

		WebElement login = driver.findElement(By.id("Login"));
		// login.click();
		clickElement(login, "loginElement");
		WebElement error = driver.findElement(By.id("error"));
		String actual = error.getText();
		System.out.println("Error message is " + actual);
		Assert.assertEquals(actual, expected);
		extentreport.logTestInfo("Inside saesforce error login method ended");


	}

	@Test
	@Parameters("browserName")
	public static void salesforceLoginTc2() {
		validLoginMethod();

	}

	@Test
	@Parameters("browserName")
	public static void rememberMeTc3() {

		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.valid.userid");
		String pass = propertiesUtility.getPropertyValue("login.valid.password");

		WebElement username = driver.findElement(By.id("username"));
		enterText(userid, username);

		WebElement passWord = driver.findElement(By.id("password"));
		enterText(pass, passWord);

		WebElement remember = driver.findElement(By.id("rememberUn"));
		// remember.click();
		clickElement(remember, "rememberpassword");

		driver.findElement(By.id("Login")).click();
		WebElement newtab = driver.findElement(By.id("userNav-arrow"));
		// newtab.click();
		clickElement(newtab, "newtab");

		WebElement logout = driver.findElement(
				By.xpath("/html/body/div/div[1]/table/tbody/tr/td[3]/div/div[3]/div/div/div[2]/div[3]/a[5]"));
		//logout.click();

	}

	@Test
	@Parameters("browserName")
	public static void forgotPasswordTc4a() throws InterruptedException {
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.invalid.userid");
		String pass = propertiesUtility.getPropertyValue("login.invalid.password");

		WebElement username = driver.findElement(By.id("username"));
		enterText(userid, username);

		WebElement passWord = driver.findElement(By.id("password"));
		enterText(pass, passWord);

		WebElement login = driver.findElement(By.id("Login"));
		clickElement(login, "login");
		extentreport.logTestInfo("forgot password passed");
		Thread.sleep(4000);
	}

	@Test
	@Parameters("browserName")
	public static void forgotPasswordTc4() throws InterruptedException {
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.valid.userid");

		driver.findElement(By.id("forgot_password_link")).click();

		WebElement user = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[1]/input[1]"));

		user.sendKeys(userid);
		WebElement continueA = driver.findElement(By.id("continue"));
		//continueA.click();
		clickElement(continueA, "rememberpassword");
		extentreport.logTestInfo("forgot password testcase ended");

	}

	@Test
	@Parameters("browserName")
	public static void usermenuDropDownTc5() {
		validLoginMethod();

		WebElement ele = driver.findElement(By.id("userNav-arrow"));
		//ele.click();
		clickElement(ele, "");
		System.out.println("User drop down is displayed");
		extentreport.logTestInfo("User drop down is displayed");


	}

	@Test
	@Parameters("browserName")
	public static void myprofile6() throws InterruptedException, AWTException {

		validLoginMethod();

		WebElement userMenu = driver.findElement(By.id("userNav-arrow"));
		userMenu.click();
		Thread.sleep(3000);
		WebElement myProfile = driver.findElement(By.xpath("//div[3]/div/div/div[2]/div[3]/a[1]"));
		myProfile.click();
		driver.findElement(By.xpath("//td/div/div[2]/div[2]/div[1]/h3/div/div/a/img")).click();
		System.out.println("Edit profile clicked");
		//Thread.sleep(5000);

		WebElement frame1 = driver.findElement(By.id("contactInfoContentId"));
		driver.switchTo().frame(frame1);
		Thread.sleep(3000);
		//WaitUntilElementIsVisible(frame1,"frame1 element");
		//waitFluentForVisibility(frame1,"frame1 element");

		WebElement about = driver.findElement(By.xpath("//li[@id=\"aboutTab\"]/a[1]"));
		//about.click();
		clickElement(about, "about");


		WebElement lastName = driver.findElement(By.id("lastName"));
		clearElement(lastName, "lastname");
		//lastName.clear();
		lastName.sendKeys("Ram");

		WebElement saveAll = driver.findElement(By.xpath("//div[@class=\"net-buttons zen-mtl\"]/input[1]"));
		//saveAll.click();
		clickElement(saveAll, "saveAll");

		//Thread.sleep(3000);
		// post
		WebElement postEle = driver.findElement(By.xpath("//span[text()='Post']"));
		postEle.click();
		WebElement postFrame = driver
				.findElement(By.xpath("//iframe[contains(@title,'Rich Text Editor, publisherRichTextEditor')]"));
		driver.switchTo().frame(postFrame);
		
		Thread.sleep(3000);
		WebElement frameBody = driver.findElement(By.xpath("//body[text()='Share an update, @mention someone...']"));
		frameBody.click();
		frameBody.sendKeys("Welcome to selenium automation");
		driver.switchTo().parentFrame();
		Thread.sleep(3000);
		WebElement postShare = driver.findElement(By.id("publishersharebutton"));
		postShare.click();
		System.out.println("post got shared");

		// file
		Thread.sleep(3000);
		WebElement file = driver.findElement(By.linkText("File"));
		file.click();
		WebElement upload = driver.findElement(By.id("chatterUploadFileAction"));
		
		//upload.click();
		clickElement(upload, "upload");
		WebElement browse = driver.findElement(By.id("chatterFileStageTwo"));

		// driver.findElement(By.xpath("//input[@id='chatterFile']")).click();

		JavascriptExecutor executor = (JavascriptExecutor) driver; //
		// executor.executeScript("arguments[0].click();", browse);
		executor.executeScript("document.getElementById('chatterFile').click();");
		Thread.sleep(5000);

		// StringSelection stringselec = new
		// StringSelection("C:\\Users\\agmarc80\\Desktop\\file.txt");
		StringSelection stringselec = new StringSelection(
				"C:\\Users\\agmarc80\\eclipse-workspace\\salesforcetestFramework\\src\\test\\resources\\file.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselec, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("done");
		Thread.sleep(3000);
		System.out.println("file uploaded");

		WebElement share = driver.findElement(By.id("publishersharebutton"));
		share.click();
		
		Thread.sleep(3000);

		deletePhoto();
		System.out.println("photo is deleted");
		Thread.sleep(3000);
//photo
		WebElement photoMod = driver.findElement(By.id("displayBadge"));
		Thread.sleep(3000);
		photoMod.click();
		Thread.sleep(5000);
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(3000);
		WebElement photo = driver.findElement(By.xpath("//input[@id=\"j_id0:uploadFileForm:uploadInputFile\"]"));
		// photo.sendKeys("C:\\Users\\agmarc80\\Downloads\\jan1.jpg");
		photo.sendKeys(
				"C:\\Users\\agmarc80\\eclipse-workspace\\salesforcetestFramework\\src\\test\\resources\\salesforce.jpg");

		System.out.println("upload profile page opened");
		WebElement save = driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
		//save.click();
		
		clickElement(save,"save clicked");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//html[1]/body[1]/span[1]/form[1]/div[2]/input[1]")).click();
		extentreport.logTestInfo("profile photo uploaded successfully");
		System.out.println("profile photo uploaded successfully");
		extentreport.logTestInfo("profile photo uploaded successfully");


	}

	@Test
	@Parameters("browserName")

	public static void mysettings7() throws InterruptedException {

		validLoginMethod();

		WebElement userMenu = driver.findElement(By.id("userNav-arrow"));
		//userMenu.click();
		clickElement(userMenu, "usermenu");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='My Settings']")).click();
		System.out.println("my settings link clicked");
		extentreport.logTestInfo(       "my settings link clicked"      );             
		driver.findElement(By.id("PersonalInfo_font")).click();
		System.out.println("personal tag clicked inside settings user menu");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@id='LoginHistory_font']")).click();
		System.out.println("login history clicked inside personal link");
		driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']")).click();
		System.out.println(" clicking Display and layout inside settings user menu ");
		driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']")).click();
		System.out.println(" clicking customizetab inside Display and layout menu ");
		driver.findElement(By.xpath("//select[@id='p4']")).click();
		System.out.println(" clicking custom tab  ");
		driver.findElement(By.xpath("//select/option[text()='Salesforce Chatter']")).click();
		System.out.println("clicking salesforce chatter in custom tab");
		driver.findElement(By.xpath("//option[text()='Reports']")).click();
		System.out.println("clicking Repors tab");
		driver.findElement(By.xpath("//tbody/tr[1]/td[2]/div[2]/a[1]/img[1]")).click();
		System.out.println("reports tab moved from available tabs toselected tabs");

		driver.findElement(By.xpath("//span[@id='EmailSetup_font']")).click();
		driver.findElement(By.id("EmailSettings_font")).click();
		WebElement email = driver.findElement(By.id("sender_name"));
		//email.clear();
		clearElement(email, "email");
		email.sendKeys("janani");
		System.out.println("printing email name");
		WebElement emailAddress = driver.findElement(By.id("sender_email"));
		//emailAddress.clear();
		clearElement(emailAddress, "emailaddress");

		emailAddress.sendKeys("janani@gmail.com");
		driver.findElement(By.id("auto_bcc1")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).click();
		System.out.println("email settings done");
		driver.switchTo().alert().accept();

		Thread.sleep(3000);
		driver.findElement(By.id("CalendarAndReminders_font")).click();
		driver.findElement(By.id("Reminders_font")).click();
		driver.findElement(By.id("testbtn")).click();
		System.out.println("activity remainders set properly");

		driver.findElement(By.id("Reminders_font")).click();

	}

	@Test
	@Parameters("browserName")

	public static void developerConsoleTc8() throws InterruptedException {
		validLoginMethod();

		WebElement userMenu = driver.findElement(By.id("userNav-arrow"));
		//userMenu.click();
		clickElement(userMenu, "usermenu");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[contains(text(),'Developer Console')]")).click();

		String MainWindow = driver.getWindowHandle();

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);

				// Closing the Child Window.
				driver.close();
			}
		}
		System.out.println("console window is closed");
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
	}

	@Test

	@Parameters("browserName")
	public static void logout9() throws InterruptedException {
		validLoginMethod();

		WebElement userMenu = driver.findElement(By.id("userNav-arrow"));
		//userMenu.click();
		clickElement(userMenu, "usermenu");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[3]/div/div[3]/div/div/div[2]/div[3]/a[5]")).click();
		System.out.println("Logout tab clicked");
		extentreport.logTestInfo("Logout tab clicked");

	}

	@Test
	@Parameters("browserName")

	public static void createAccountTc10() throws InterruptedException, AWTException {
		validLoginMethod();

		driver.findElement(By.xpath("//div[1]/div/nav/ul/li[7]/a")).click();
		System.out.println("-----------------------");

		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/a")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).click();
		driver.findElement(By.xpath("//input[@id='acc2']")).sendKeys("JananiR");
		System.out.println("Accountname has added");
		Select typeDropdown = new Select(driver.findElement(By.id("acc6")));
		typeDropdown.selectByVisibleText("Technology Partner");
		System.out.println("Type has selected");

		Select customerPrior = new Select(driver.findElement(By.id("00NDn00000SjSK0")));
		customerPrior.selectByVisibleText("High");
		System.out.println("customer priority has selcted");
		driver.findElement(By.xpath("//input[@title='Save']")).click();

	}

	@Test
	@Parameters("browserName")

	public static void createNewViewinAccounTc11() throws InterruptedException {
		String viewName = "janani" + random.nextInt(200);

		validLoginMethod();

		driver.findElement(By.xpath("//div[1]/div/nav/ul/li[7]/a")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/a")).click();// prompt
		System.out.println("Account page has opened");

		driver.findElement(By.xpath("//form/div/span/span[2]/a[2]")).click();
		System.out.println("create new view selected");
		Thread.sleep(3000);
		driver.findElement(By.id("fname")).sendKeys(viewName);
		driver.findElement(By.xpath("//form/div[3]/table/tbody/tr/td[2]/input[1]")).click();
		System.out.println("newly added view saved");

	}

	@Test
	@Parameters("browserName")

	private static void editViewTc12() throws InterruptedException {
		validLoginMethod();

		driver.findElement(By.xpath("//div[1]/div/nav/ul/li[7]/a")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/a")).click();// prompt
		extentreport.logTestInfo("Account page has opened");
		System.out.println("Account page has opened");
		Thread.sleep(3000);

		WebElement ele = driver.findElement(By.xpath("//select[@id='fcf']"));

		Select typeDropdown = new Select(ele);

		// typeDropdown.selectByVisibleText(viewName);

		System.out.println("view name has selected");
		extentreport.logTestInfo("view name has selected");
		driver.findElement(By.linkText("Edit")).click();
		WebElement name = driver.findElement(By.id("fname"));
		name.clear();
		name.sendKeys("janani");
		driver.findElement(By.xpath("//form/div[3]/table/tbody/tr/td[2]/input[1]")).click();

		System.out.println("test case completed");
		extentreport.logTestInfo("test case completed");


	}

	@Test
	@Parameters("browserName")

	private static void mergeAccountsTc13() throws InterruptedException, AWTException {

		createAccountTc10();
		System.out.println("---------------------------------------");

		driver.findElement(By.xpath("//div[1]/div/nav/ul/li[7]/a")).click();
		Thread.sleep(3000);


		driver.findElement(By.xpath("//table/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a")).click();
		System.out.println("merge page opened");
		extentreport.logTestInfo("merge page opened");

		driver.findElement(By.id("srch")).sendKeys("janani");
		driver.findElement(By.xpath("//tbody/tr[1]/td[2]/form[1]/div[1]/div[2]/div[4]/input[2]")).click();
		driver.findElement(By.id("cid0")).click();
		driver.findElement(By.id("cid1")).click();
		driver.findElement(By.xpath("//form/div/div[2]/div[5]/div/input[1]")).click();
		System.out.println("next button clicked");

		extentreport.logTestInfo("next button clicked");
		driver.findElement(By.xpath("//form/div/div[2]/div[5]/div/input[2]")).click();
		System.out.println("merge button clicked");

		extentreport.logTestInfo("merge button clicked");
		// driver.findElement(By.name("ok")).submit();
		WebDriverWait wait = new WebDriverWait(driver, 30, 100);
		wait.until(ExpectedConditions.alertIsPresent());
		alertTes();

	}

	@Test
	@Parameters("browserName")

	private static void createAccountReportTc14() throws InterruptedException {
		validLoginMethod();
		String saveReportName = "jan" + random.nextInt(50);

		driver.findElement(By.xpath("//div[1]/div/nav/ul/li[7]/a")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/a")).click();// prompt
		System.out.println("Account page has opened");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]")).click();// prompt
		System.out.println("clicked accounts with last activity");
		Actions action = new Actions(driver);
		WebElement dropdown = driver.findElement(By.xpath("//img[@id='ext-gen148']"));
		do {
			action.sendKeys(Keys.ARROW_DOWN).perform();
		} while (!dropdown.isDisplayed());
		dropdown.click();
		System.out.println("dropdown is clicked");
		driver.findElement(By.xpath("//div[contains(text(),'Created Date')]")).click();
		System.out.println("created date is clicked in dropdown");
		driver.findElement(By.xpath("//img[@id='ext-gen152']")).click();
		driver.findElement(By.id("ext-gen152")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("ext-gen276")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("ext-gen154")).click();
		System.out.println("-----------------");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id='ext-gen296']")).click();
		Thread.sleep(3000);
		WebElement saveUnsaved = driver.findElement(By.xpath("//button[@id='ext-gen49']"));
		saveUnsaved.click();
		WebElement reportName = driver.findElement(By.xpath("//input[@id='saveReportDlg_reportNameField']"));
		reportName.sendKeys(saveReportName);
		WebElement reportUniName = driver.findElement(By.xpath("//input[@id='saveReportDlg_DeveloperName']"));
		reportUniName.click();
		Thread.sleep(3000);
		driver.findElement(By.id("dlgSaveAndRun")).click();

		System.out.println("clicked");
	}

	@Test
	@Parameters("browserName")

	public static void oppurtunityDropDownTc15() throws InterruptedException {
		validLoginMethod();

		WebElement opTab = driver.findElement(By.id("Opportunity_Tab"));
		opTab.click();
		Thread.sleep(3000);

		Select s = new Select(driver.findElement(By.xpath("//form/div/span/span[1]/select")));
		List<WebElement> op = s.getOptions();

		int size = op.size();
		for (int i = 0; i < size; i++) {
			String options = op.get(i).getText();
			System.out.println(options);
		}

	}

	@Test
	@Parameters("browserName")

	public static void createNewOptyTc16() throws InterruptedException {
		validLoginMethod();

		WebElement opTab = driver.findElement(By.id("Opportunity_Tab"));
		opTab.click();
		System.out.println("optab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		Thread.sleep(3000);

		WebElement newopTab = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]"));
		//Thread.sleep(5000);
		waitFluentForVisibility(newopTab,"newopTab element");

		newopTab.click();
		Thread.sleep(3000);

		WebElement opName = driver.findElement(By.id("opp3"));
		opName.sendKeys("janani");
		WebElement opAccName = driver.findElement(By.id("opp4"));
		opAccName.sendKeys("janani");
		WebElement closeDate = driver.findElement(By.id("opp9"));
		closeDate.click();

		System.out.println("date tab is clicked");
		Thread.sleep(3000);

		WebElement dateWidget = driver.findElement(By.xpath("//span[@class='dateFormat']//a"));
		dateWidget.click();

		System.out.println("date is clicked");

		WebElement opStageDropdown = driver.findElement(By.id("opp11"));
		Select opDrop = new Select(opStageDropdown);
		opDrop.selectByVisibleText("Qualification");
		System.out.println(opDrop + " dropdwon selected");
		WebElement prob = driver.findElement(By.id("opp12"));
		// prob.sendKeys("10%");
		System.out.println("prob selected");

		WebElement opLeadDropdown = driver.findElement(By.id("opp6"));
		Select opLead = new Select(opLeadDropdown);
		opLead.selectByVisibleText("Web");
		System.out.println("lead selected");
		WebElement prim = driver.findElement(By.id("opp17"));
		prim.click();
		prim.sendKeys("google");

	}

	@Test
	@Parameters("browserName")

	public static void optyyPipelineReportTc17() throws InterruptedException {
		validLoginMethod();

		WebElement opTab = driver.findElement(By.id("Opportunity_Tab"));
		opTab.click();
		System.out.println("optab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		driver.findElement(By.linkText("Opportunity Pipeline")).click();
		System.out.println("Report page with oppurtunity pipeline is displayed");

	}

	@Test
	@Parameters("browserName")
	public static void stuckOptsReportTc18() throws InterruptedException {
		validLoginMethod();

		WebElement opTab = driver.findElement(By.id("Opportunity_Tab"));
		opTab.click();
		System.out.println("optab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		driver.findElement(By.linkText("Stuck Opportunities")).click();
		System.out.println("Report page with oppurtunity that are stuck is displayed");

	}

	@Test
	@Parameters("browserName")

	public static void testQuarterlySummaryReportTc19() throws InterruptedException {
		validLoginMethod();

		WebElement opTab = driver.findElement(By.id("Opportunity_Tab"));
		opTab.click();
		System.out.println("optab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		WebElement quartInterval = driver.findElement(By.id("quarter_q"));
		Select interval = new Select(quartInterval);
		interval.selectByVisibleText("Current and Next FQ");
		String intervalOption = interval.getFirstSelectedOption().getText();
		Assert.assertEquals("Current and Next FQ", intervalOption);
		System.out.println(" Assert passed");

		Thread.sleep(3000);

		WebElement includeDrop = driver.findElement(By.id("open"));
		Select include = new Select(includeDrop);

		include.selectByVisibleText("Open Opportunities");
		String includeOption = include.getFirstSelectedOption().getText();
		Assert.assertEquals("Open Opportunities", includeOption); // assert
		System.out.println(" Assert passed");

		System.out.println("Open Opportunities selected");

		driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
		System.out.println("Report page with oppurtunities that satisfies search criteria displayed");

	}

	@Test
	@Parameters("browserName")

	public static void leadsTabTc20() throws InterruptedException {
		validLoginMethod();

		WebElement leadsTab = driver.findElement(By.id("Lead_Tab"));
		leadsTab.click();
		System.out.println("leadstab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("leads home page displayed");

	}

	@Test
	@Parameters("browserName")

	public static void leadsDropdown21() throws InterruptedException {
		validLoginMethod();

		WebElement leadsTab = driver.findElement(By.id("Lead_Tab"));
		leadsTab.click();
		System.out.println("leadstab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("leads home page displayed");

		Select LeadDrop = new Select(driver.findElement(By.id("fcf")));
		List<WebElement> op = LeadDrop.getOptions();// for dropown
		// selectFromList(op,"lead drop select");

		int size = op.size();
		for (int i = 0; i < size; i++) {
			String options = op.get(i).getText();
			System.out.println(options);
		}
		System.out.println("leads dropdown showed");

	}

	@Test
	@Parameters("browserName")
	public static void defaultViewinLeadsTc22() throws InterruptedException {
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.valid.userid");
		String pass = propertiesUtility.getPropertyValue("login.valid.password");

		WebElement username = driver.findElement(By.id("username"));
		enterText(userid, username);

		WebElement passWord = driver.findElement(By.id("password"));
		enterText(pass, passWord);
		driver.findElement(By.id("Login")).click();

		WebElement leadsTab = driver.findElement(By.id("Lead_Tab"));
		leadsTab.click();
		System.out.println("leadstab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("leads home page displayed");

		Select leadDrop = new Select(driver.findElement(By.id("fcf")));
		leadDrop.selectByVisibleText("Today's Leads");
		Thread.sleep(3000);
		WebElement goButton = driver.findElement(By.xpath("//input[@name='go']"));
		goButton.click();
		System.out.println("go clicked");

		WebElement logout = driver.findElement(By.id("userNav"));
		logout.click();
		System.out.println("-------");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Logout']")).click();
		System.out.println("logout clicked");
		Thread.sleep(3000);

		String userid1 = propertiesUtility.getPropertyValue("login.valid.userid");
		String pass1 = propertiesUtility.getPropertyValue("login.valid.password");

		WebElement username1 = driver.findElement(By.id("username"));
		enterText(userid1, username1);
		System.out.println("username entered");

		WebElement passWord1 = driver.findElement(By.id("password"));
		enterText(pass1, passWord1);

		driver.findElement(By.id("Login")).click();
		WebElement leadsTab1 = driver.findElement(By.id("Lead_Tab"));

		leadsTab1.click();
		System.out.println("leadstab clicked");
		Thread.sleep(5000);
		// driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("leads home page displayed");
		WebElement goButton1 = driver.findElement(By.xpath("//input[@name='go']"));
		goButton1.click();
		System.out.println("go clicked");

	}

	@Test
	@Parameters("browserName")
	public static void todaysLeadsTc23() throws InterruptedException {
		validLoginMethod();

		WebElement leadsTab = driver.findElement(By.id("Lead_Tab"));
		leadsTab.click();
		System.out.println("leadstab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("leads home page displayed");

		Select leadDrop = new Select(driver.findElement(By.id("fcf")));
		leadDrop.selectByVisibleText("Today's Leads");
		Thread.sleep(3000);
		System.out.println("Todays leads clicked");
		WebElement goButton = driver.findElement(By.xpath("//input[@name='go']"));
		goButton.click();
		System.out.println("go clicked");
	}

	@Test
	@Parameters("browserName")
	public static void newButtonOnLeadsTc24() throws InterruptedException {
		validLoginMethod();

		WebElement leadsTab = driver.findElement(By.id("Lead_Tab"));
		leadsTab.click();
		System.out.println("leadstab clicked");
		Thread.sleep(5000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("leads home page displayed");

		WebElement newLeadframe = driver.findElement(By.xpath("//input[@name='new']"));
		newLeadframe.click();
		System.out.println("new button clickedin lead");
		WebElement lastName = driver.findElement(By.id("name_lastlea2"));
		lastName.sendKeys("ABCD");
		WebElement company = driver.findElement(By.id("lea3"));
		company.sendKeys("ABCD");

		WebElement saveinLeads = driver.findElement(By.xpath("//input[@name='save']"));
		saveinLeads.click();
		// Thread.sleep(3000);
		System.out.println("new lead saved");
	}

	@Test
	@Parameters("browserName")
	public static void createNewcontactTc25() throws InterruptedException {
		validLoginMethod();

		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("contacts home page displayed");
		Thread.sleep(3000);
		WebElement newContactframe = driver.findElement(By.xpath("//input[@name='new']"));
		newContactframe.click();
		System.out.println("new button clicked in contact tab");

		WebElement lastName = driver.findElement(By.id("name_lastcon2"));
		lastName.sendKeys("Ram");
		WebElement company = driver.findElement(By.id("con4"));
		company.sendKeys("janani");

		WebElement saveinContact = driver.findElement(By.xpath("//input[@name='save']"));
		saveinContact.click();
		// Thread.sleep(3000);
		System.out.println("new contacts saved");

	}

	@Test
	@Parameters("browserName")
	public static void createNewViewIncontactTc26() throws InterruptedException {
		String str = "Janani" + Math.random();
		validLoginMethod();

		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		System.out.println("contacts home page displayed");
		Thread.sleep(3000);
		WebElement contactNewView = driver.findElement(By.linkText("Create New View"));
		contactNewView.click();
		System.out.println("contact New View clicked");

		WebElement contactFname = driver.findElement(By.id("fname"));
		contactFname.sendKeys(str);
		driver.findElement(By.id("devname")).click();
		System.out.println("Clicked fname and unique name");
		WebElement contactSave = driver.findElement(By.xpath("//input[@name='save']"));
		contactSave.click();
		Thread.sleep(3000);
		System.out.println("Clicked save button");

	}

	@Test
	@Parameters("browserName")
	public static void recentlyCreatedcontactTc27() throws InterruptedException, IOException {
		validLoginMethod();

		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		Select recent = new Select(driver.findElement(By.id("hotlist_mode")));
		recent.selectByVisibleText("Recently Created");
		String recentOption = recent.getFirstSelectedOption().getText();
		// System.out.println("option "+option);
		Assert.assertEquals("Recently Created", recentOption); // assert
		System.out.println(" Assert passed");
		// screenShotOfThePage();
		Thread.sleep(3000);

	}

	@Test
	@Parameters("browserName")
	public static void myContactsViewInTheCorrectPage28() throws InterruptedException, IOException {
		validLoginMethod();

		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		Select myContactViewdrop = new Select(driver.findElement(By.id("fcf")));
		myContactViewdrop.selectByVisibleText("All Contacts");
		Thread.sleep(3000);
		System.out.println("all contacts clicked in view drodown");

	}

	@Test
	@Parameters("browserName")
	public static void contactNameInRecentContacs29() throws InterruptedException, IOException {
		validLoginMethod();

		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");

		Thread.sleep(3000);

		WebElement rece = driver.findElement(By.xpath("//div/div/div[2]/table/tbody/tr[2]/th[1]/a"));
		rece.click();
		// Assert.assertEquals(true, Ram.isSelected()); //Verifies that the radio
		System.out.println("recent element is clicked");

	}

	@Test
	@Parameters("browserName")
	public static void checkerrorMessageinContacs30() throws InterruptedException, IOException {

		validLoginMethod();

		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		WebElement newViewContact = driver.findElement(By.linkText("Create New View"));
		newViewContact.click();
		System.out.println("Create new viw clicked");
		Thread.sleep(3000);

		WebElement uniqueNameinContact = driver.findElement(By.id("devname"));
		uniqueNameinContact.click();
		Thread.sleep(3000);

		WebElement savecontacts = driver.findElement(By.xpath("//input[@title='Save']"));
		savecontacts.click();
		System.out.println("Error message received");

	}

	@Test
	@Parameters("browserName")
	public static void cancelinCreateNewViewTc31() throws InterruptedException, IOException {
		validLoginMethod();
		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		WebElement newViewContact = driver.findElement(By.linkText("Create New View"));
		newViewContact.click();
		System.out.println("Create new viw clicked");
		Thread.sleep(3000);

		WebElement fnameinContact = driver.findElement(By.id("fname"));
		fnameinContact.sendKeys("ABCD");

		WebElement uniqueNameinContact = driver.findElement(By.id("devname"));
		uniqueNameinContact.sendKeys("EFGH");
		Thread.sleep(3000);

		WebElement cancel = driver.findElement(By.xpath("//input[@title='Cancel']"));
		cancel.click();
		System.out.println("given contacts not created in contacts home page");
	}

	@Test
	@Parameters("browserName")
	public static void saveAndNewInContactsTc32() throws InterruptedException, IOException {
		validLoginMethod();
		WebElement contactsTab = driver.findElement(By.id("Contact_Tab"));
		contactsTab.click();
		System.out.println("contacttab clicked");
		Thread.sleep(3000);
		driver.findElement(By.id("tryLexDialogX")).click();
		System.out.println("prompt is closed");
		Thread.sleep(3000);
		WebElement newContactframe = driver.findElement(By.xpath("//input[@name='new']"));
		newContactframe.click();
		System.out.println("new button clicked in contact tab");
		WebElement lastName = driver.findElement(By.id("name_lastcon2"));
		lastName.sendKeys("Ram");
		WebElement accttName = driver.findElement(By.id("con4"));
		accttName.sendKeys("Global Media");

		WebElement saveAndNew = driver.findElement(By.xpath("//input[@title='Save & New']"));
		saveAndNew.click();
		System.out.println("Save and new clicked");

	}

}
