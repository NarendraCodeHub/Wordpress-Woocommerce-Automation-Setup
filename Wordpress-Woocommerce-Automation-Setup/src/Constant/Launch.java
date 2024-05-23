package Constant;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Launch implements Framework_Constants {
	public WebDriver driver;
	@BeforeMethod
	public void openBrowser()
	{
		System.setProperty(gk, gv);
	    driver = new FirefoxDriver();
//		driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get(url);
	}
	
	@AfterMethod
	public void closeBrowser(ITestResult res) throws Exception
	{
		if(ITestResult.FAILURE == res.getStatus())
		{
			Photo.capture(driver);
		}
		driver.quit();
	}
	
//	public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.until((ExpectedCondition<Boolean>) webDriver ->
//                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//	}
	
	 public void get(String url) {
	        driver.get(url);
	        waitForPageLoad();
	    }

	    public void waitForPageLoad() {
	        WebDriverWait wait = new WebDriverWait(driver, 200);
	        wait.until((ExpectedCondition<Boolean>) webDriver ->
	                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	    }

}