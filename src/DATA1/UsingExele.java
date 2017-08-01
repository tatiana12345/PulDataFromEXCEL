package DATA1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExceleUtilities.Constants;
import ExceleUtilities.ExcelUtility;
import ExceleUtilities.ReportNamePath;
import ExceleUtilities.utilitiesLK;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import ExceleUtilities.FailedTC_Screenshot;

public class UsingExele {
	private WebDriver wd;
	utilitiesLK UT;
	ExtentReports report;
	ExtentTest test;
  
	  @BeforeClass
	  public void beforeClass() throws Exception {
		  
		  report = ReportNamePath.getInstance();
		  test = report.startTest("Verify login impossible with invalid credentials");
		  
		  System.setProperty("webdriver.gecko.driver", "/Users/tatianakesler/Desktop/Selenium/installation/geckodriver");
		  wd = new FirefoxDriver();
		  UT = new utilitiesLK(wd, test);
		 
		// Maximize the browser's window
		  wd.manage().window().maximize();
		  wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  wd.get(Constants.URL);
		  
		  test.log(LogStatus.INFO, "WebBrowser started");
		  test.log(LogStatus.INFO, "Browser maximized");
		  
		  UT.clickLearnNow();
		  
		 //Locate excel file + SHEET NAME
		  ExcelUtility.setExcelFile(Constants.File_Path + Constants.File_Name, "LoginTests");
	  }
	  
	@DataProvider(name="loginData")
  public Object[][] DataProvider() {
		Object[][] testData = ExcelUtility.getTestData("Invalid_Login");
	return testData;
  }

	@Test(dataProvider="loginData")
	public void testUsingExcel (String username, String password) throws Exception {
		
		//click login button
		UT.Click_Login();
		
		Thread.sleep(2000);
		
		//Enter User Name
		UT.typeEmail(username);
		
		//enter password
		UT.typePassword(password);
		
		//click login button
		UT.clickSubmit();
		
		Thread.sleep(2000);
		
		//Verify if there Error message provided
		//wd.findElementSSSSSS, not elemenT
//		boolean result = wd.findElements(By.xpath("//form[@id='new_user']//div[3]")).size() != 0;
		boolean result = UT.LoginButton_isPresent();
		Assert.assertTrue(result);
		test.log(LogStatus.PASS, "Test passed, user's unable to login with invalid credentials");
	}
	

	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException{
		if (testResult.getStatus()==ITestResult.FAILURE){
			String path = ExceleUtilities.FailedTC_Screenshot.NewScreenshotMethod
					.takeScreenshot(wd, testResult.getName());
			String imgPath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Verification of login with invalid credentials failed ", imgPath);
		}
		
	}
	
  @AfterClass
  public void afterClass() throws Exception {
	  Thread.sleep(2000);
	  wd.quit();
	  report.endTest(test);
	  report.flush();
  }

}
