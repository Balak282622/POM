package tests;

import java.io.IOException;
import java.util.ResourceBundle.Control;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import pages.PasswordPage;
import pages.amazonLoginPage;
import utils.TestUtil;

public class AmazonLoginTest extends TestBase {
	
	TestUtil tu;
	amazonLoginPage alp;
	PasswordPage pwdp;
//	TestBase tb = new TestBase();
	
	public AmazonLoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException, IOException{
		initailize();
		alp = new amazonLoginPage();
		tu = new TestUtil();
//		pwdp = new PasswordPage();
//		TestUtil.runTimeInfo("hi", "Hello");
	}
	@Test(priority = 1)
	public void a() {
		System.out.println("Hello");
	}
	
	@Test(priority = 2)
	public void verifySigninLabel() {
		Assert.assertTrue(alp.verifySigninLabel(), "Lable is missing");
	}
	
	@Test(priority = 3)
	public void checkTitle() {
		String title = alp.validateLoginPageTitle();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
	@Test(priority = 4)
	public void needHelp() {
		alp.needHelpExpand();
		Assert.assertTrue(alp.verifyForgetPass());
		Assert.assertTrue(alp.verifyOtherissues());
	}
	
	@DataProvider(name="WrongEmail")
	public Object[] WrongEmailData(){
		
		Object data[] = new Object[tu.xl.getRowCount("contacts")];
		for (int i = 0; i < data.length; i++) {
			System.out.println(data.length);
			data[i] = tu.xl.getCellData("contacts", 0, i+1);
		}
		return data;
	}
	
	@Test(priority = 6,dataProvider = "WrongEmail")
	public void enterWrongEmail(String email) {
		alp.enterWrongEmail(email);
		System.out.println("Enter email"+" : "+ email);
		Assert.assertEquals(alp.readError(), "We cannot find an account with that e-mail address");
	}
	
	@Test(priority = 7)
	public void enterEmail() {
		pwdp=alp.enterEmail(p.getProperty("email"));
		System.out.println("Enter email"+" : ");
		String title = alp.validateLoginPageTitle();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
	@Test(priority = 5)
	public void verifyFooter() throws InterruptedException {
		for (WebElement all : alp.footerList()) {
			System.out.println(all.getText());
			if(all.getText().equals("Privacy Notice")) {
				all.sendKeys(Keys.CONTROL,Keys.ENTER);
			};
		}
//		System.out.println(alp.footerList());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
