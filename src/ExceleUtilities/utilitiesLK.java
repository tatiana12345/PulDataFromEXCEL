package ExceleUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class utilitiesLK {
	WebDriver wd = null;
	ExtentTest test;
	
	public utilitiesLK (WebDriver wd, ExtentTest test){
		this.wd = wd;
		this.test = test;
	}

	public static WebElement element = null;
	
	public void clickLearnNow(){
		WebElement LearnNow = wd.findElement(By.xpath("//span[text()='Learn Now']"));
		LearnNow.click();
		test.log(LogStatus.INFO, "'Learn now' button clicked");
	}
	
	public void Click_Login(){
		WebElement LoginButton = wd.findElement(By.xpath("//div[@id='navbar']//a[contains(text(),'Login')]"));
		LoginButton.click();
		test.log(LogStatus.INFO, "'Login button' clicked");
	}
	public void typeEmail (String username){
		WebElement emailField = wd.findElement(By.id("user_email"));
		emailField.sendKeys(username);
		test.log(LogStatus.INFO, "Email address typed: " + username);
		
	}
	public void typePassword (String password){
		WebElement passField = wd.findElement(By.id("user_password"));
		passField.sendKeys(password);
		test.log(LogStatus.INFO, "Password typed: " + password);
	}
	public void clickSubmit(){
		WebElement submit = wd.findElement(By.name("commit"));
		submit.click();
		test.log(LogStatus.INFO, "Submit button clicked");
	}
	
	public boolean LoginButton_isPresent(){
		WebElement loginButton = null;
		try {
			loginButton = wd.findElement(By.xpath("//form[@id='new_user']//div[3]"));
			if (loginButton != null){
				return true;
			}
		}	
			catch (NoSuchElementException e){
				System.out.println(e.getMessage());
			}
		return false;
	}
}

