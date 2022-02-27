package pages;

import java.awt.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import base.TestBase;

public class amazonLoginPage extends TestBase {

	@FindBy(xpath = "//h1[@class='a-spacing-small']")
	WebElement signinLabel;
	
	@FindBy(id="ap_email")
	WebElement emailBox;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	@FindBy(xpath = "//span[@class='a-list-item']")
	WebElement globalError;
	
	@FindBy(className = "a-expander-prompt")
	WebElement needHelpExpander;
	
	@FindBy(id = "auth-fpp-link-bottom")
	WebElement forgotPassLink;
	
	@FindBy(id = "ap-other-signin-issues-link")
	WebElement otherSigninIssueLink;
	
	@FindBy(xpath = "//div[contains(@class,'a-size-mini')]//a[contains(@class,'a-link-normal')]")
	java.util.List<WebElement> a;
	
	public amazonLoginPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifySigninLabel() {
		return signinLabel.isDisplayed();
	}
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public String readError() {
		String actualErr = globalError.getText();
		System.out.println(actualErr);
		return actualErr;
	}
	
	public void enterWrongEmail(String a) {
		emailBox.sendKeys(a);
		continueButton.click();
	}
	public PasswordPage enterEmail(String email) {
		emailBox.sendKeys(email);
		continueButton.click();
		return new PasswordPage();
	}
	
	public void needHelpExpand() {
		needHelpExpander.click();
	}
	
	public boolean verifyForgetPass() {
		return forgotPassLink.isDisplayed();
	}
	
	public boolean verifyOtherissues() {
		return otherSigninIssueLink.isDisplayed();
	}
	
	public java.util.List<WebElement> footerList() {
//		String t = null;
		return a;
	}
	
	
	
}
