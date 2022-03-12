package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.TestUtil;
import utils.WebEventListner;

public class TestBase {
	public static WebDriver driver;
	public static Properties p;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListner eventListener;
	public static FileInputStream ip;
	
	public TestBase() {
		try {
			
			p = new Properties();
			
			FileInputStream ip = new FileInputStream("D:\\QA automation\\eclipse\\Practice_Workspace\\FrameworkPractice\\src\\main\\java\\Config\\porp.properties");
			p.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initailize() throws IOException {
		System.out.println("Base");
//		f = new FileInputStream("D:\\QA automation\\eclipse\\Practice_Workspace\\FrameworkPractice\\src\\main\\java\\Config\\porp.properties");
//		p.load(f);
		System.out.println(p.getProperty("browser"));
		String browser = p.getProperty("browser");
		
		if(browser.equals("Chrome")) {
//			System.setProperty(p.getProperty("chromeWebdriver"), p.getProperty("chromeWebdriverPath"));
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equals("Firefox")) {
//			System.setProperty(p.getProperty("geckoWebdriver"), p.getProperty("geckoWebdriverPath"));
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		try {
			eventListener = new WebEventListner();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(p.getProperty("url"));
	}
}
