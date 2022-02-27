 package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class PasswordPage extends TestBase {

	@FindBy(xpath = "//div[@class='a-row a-spacing-base']//span")
	@CacheLookup
	WebElement emailId;
	
	@FindBy(id = "ap_password")
	WebElement passwordIP;
	
	@FindBy(id = "signInSubmit1")
	WebElement submit; 
	
	@FindBy(xpath = "//div[@class='a-alert-content'][1]//li")
	WebElement passwordErr;
	
	public PasswordPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validatePasswordPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyEmailId() {
		return emailId.isDisplayed();
	}
	
	public void enterWrongpassword(String property) {
		passwordIP.sendKeys(property);
		submit.click();
		
	}
	public String readError() {
		String actualErr = passwordErr.getText();
		System.out.println(actualErr);
		return actualErr;
	}
}
