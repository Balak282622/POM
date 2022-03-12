package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.PasswordPage;
import pages.amazonLoginPage;
import utils.TestUtil;

public class PasswordPageTest extends TestBase {
	TestUtil tu;
	amazonLoginPage alp;
	PasswordPage pwdp;
	public PasswordPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException, IOException{
		initailize();
		alp = new amazonLoginPage();
		
		tu = new TestUtil();
		pwdp = alp.enterEmail(p.getProperty("email"));
		TestUtil.runTimeInfo("hi", "Hello");
	}
	
//	@Test(priority=1)
	public void checkTitle() {
		String title = alp.validateLoginPageTitle();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
//	@Test(priority = 2)
	public void verifyEmailId() {
		Assert.assertTrue(pwdp.verifyEmailId(), "Lable is missing");
	}
	
//	@Test(priority = 3)
	public void enterWrongpassword() {
		pwdp.enterWrongpassword(p.getProperty("email"));
		System.out.println("Enter password"+" : "+p.getProperty("email"));
		Assert.assertEquals(pwdp.readError(), "Your password is incorrect");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
