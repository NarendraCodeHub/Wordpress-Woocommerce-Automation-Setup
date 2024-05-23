package TestScript;

import java.io.File;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Launch;

public class WordPress_Setup  extends Launch {
	@Test
	void WordPress_Woo_Setup() throws Exception{
		
		//Select Language
		WebElement dd = driver.findElement(By.xpath("//select"));
		Select lang = new Select(dd);
		lang.selectByVisibleText("English (United States)");
		driver.findElement(By.xpath("//input[@id='language-continue']")).click();
	    driver.findElement(By.xpath("//a[.='Let’s go!']")).click();
		String Database_Name = Launch.Database_Name;
		driver.findElement(By.xpath("//input[@id='dbname']")).sendKeys(Database_Name);
		String Database_UserName = Launch.Database_UserName;
		driver.findElement(By.xpath("//input[@id='uname']")).sendKeys(Database_UserName);
		String Database_Password = Launch.Database_Password;
    	driver.findElement(By.xpath("//input[@id='pwd']")).sendKeys(Database_Password);
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		driver.findElement(By.xpath("//a[.='Run the installation']")).click();
		String Site_Title = Launch.Site_Title;
    	driver.findElement(By.xpath("//input[@id='weblog_title']")).sendKeys(Site_Title);
		String Instance_UserName = Launch.Instance_UserName;
		String Instance_Password = Launch.Instance_Password;
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(Instance_UserName);
		WebElement pass_field = driver.findElement(By.xpath("//input[@name='admin_password']"));
		pass_field.clear();
		pass_field.sendKeys("a");
		driver.findElement(By.className("pw-checkbox")).click(); 
		pass_field.clear();
		pass_field.sendKeys(Instance_Password);
		driver.findElement(By.xpath("//input[@id='admin_email']")).sendKeys(Launch.Mail);
		driver.findElement(By.xpath("//input[@class='button button-large']")).click();
		driver.findElement(By.xpath("//a[.='Log In']")).click();
		
		//Admin Login
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(Launch.Instance_UserName);
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys(Launch.Instance_Password);
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
		
		//Uninstall Previous Plugin & Install New Plugin
		driver.findElement(By.xpath("//a[@href='plugins.php']")).click();
		driver.findElement(By.xpath("//input[@id='cb-select-all-1']")).click();
		WebElement bulk_option = driver.findElement(By.xpath("//select[@id='bulk-action-selector-top']"));
		Select plugin_bulk_opt = new Select(bulk_option);
		plugin_bulk_opt.selectByVisibleText("Delete");
		driver.findElement(By.xpath("//input[@id='doaction']")).click();
		try {
		    Alert popup = driver.switchTo().alert();
		    popup.accept();
		} catch (NoAlertPresentException e) {
		    // No alert present, continue with the next steps
		}
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//td[.='No plugins are currently available.']"), "No plugins are currently available."));
		driver.findElement(By.xpath("//a[.='Add New Plugin']")).click();
		
		//Install WP Mail Logging
		driver.findElement(By.xpath("//input[@id='search-plugins']")).sendKeys("WP Mail Logging");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-slug='wp-mail-logging']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Activate WP Mail Logging' and @data-slug='wp-mail-logging']"))).click();
		
		//Install WooCommerce and Setup
		driver.findElement(By.xpath("//a[.='Add New Plugin']")).click();
		driver.findElement(By.xpath("//input[@id='search-plugins']")).sendKeys("Woocommerce");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-slug='woocommerce']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Activate WooCommerce' and @data-slug='woocommerce']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Active']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='components-button woocommerce-profiler-navigation-skip-link is-link']"))).click();
		Actions act = new Actions(driver);
		WebElement select_country = driver.findElement(By.xpath("//div[@class='components-base-control__field']"));
		act.moveToElement(select_country).perform();
		select_country.click();
		driver.findElement(By.xpath("//input[@aria-label='Select country/region']")).sendKeys("India — Uttar Pradesh");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='India — Uttar Pradesh']")));
		WebElement country = driver.findElement(By.xpath("//button[.='India — Uttar Pradesh']"));
	    act.moveToElement(country).perform();
		country.click();
		driver.findElement(By.xpath("//button[.='Go to my store']")).click();
		
		//Configure Product 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Add your products']"))).click();
		driver.findElement(By.xpath("//a[.='Import your products from a CSV file']")).click();
		
		//Import Product
		File productImport = new File("./Excel/sample_products.csv");
		WebElement product = driver.findElement(By.xpath("//input[@type='file']"));
		product.sendKeys(productImport.getAbsolutePath());
		driver.findElement(By.xpath("//button[.='Continue']")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h2[.='Map CSV fields to products']"),"Map CSV fields to products" ));
		driver.findElement(By.xpath("//button[.='Run the importer']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='View products']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Products')]")));
		
		//Install StoreFront Theme
		driver.findElement(By.xpath("//div[.='Appearance']")).click();
		driver.findElement(By.xpath("//a[.='Add New Theme']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search themes...']")).sendKeys("Storefront");
		WebElement Storefront = driver.findElement(By.xpath("//h3[.='Storefront']"));
		act.moveToElement(Storefront).perform();
		driver.findElement(By.xpath("(//a[.='Install'])[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Activate']"))).click();
		
		//Configure WooCommerce Settings
		driver.findElement(By.xpath("//div[.='WooCommerce']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Settings']"))).click();
		
		//Configuration General
		driver.findElement(By.xpath("//input[@id='woocommerce_calc_taxes']")).click();
		driver.findElement(By.xpath("//button[.='Save changes']")).click();
		
		//Validate Successfully Message
		WebElement msg=driver.findElement(By.xpath("//div[@class='updated inline']"));
        String text=msg.getText();
        String expectedText = "Your settings have been saved.";
        Assert.assertEquals(text,expectedText);
        
        //configure Tax
        driver.findElement(By.xpath("//a[.='Tax']")).click();
        driver.findElement(By.xpath("//a[.='Standard rates']")).click();
        driver.findElement(By.xpath("//a[.='Import CSV']")).click();
        
        //Import Tax
        File taxImport = new File("./Excel/sample_tax_rates.csv");
		WebElement tax = driver.findElement(By.xpath("//input[@type='file']"));
		tax.sendKeys(taxImport.getAbsolutePath());
        driver.findElement(By.xpath("//button[.='Upload file and import']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='View tax rates']"))).click();
        
		//configure Payment 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Payments']"))).click();
		WebElement directBankTransfer = driver.findElement(By.xpath("(//span[@class='woocommerce-input-toggle woocommerce-input-toggle--disabled'])[1]"));
		act.moveToElement(directBankTransfer).perform();
		directBankTransfer.click();
		WebElement checkPayments = driver.findElement(By.xpath("(//span[@class='woocommerce-input-toggle woocommerce-input-toggle--disabled'])[2]"));
		act.moveToElement(checkPayments).perform();
		checkPayments.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='woocommerce-input-toggle woocommerce-input-toggle--enabled']")));
		driver.findElement(By.xpath("//button[.='Save changes']")).click();
		Assert.assertEquals(text,expectedText);
		
        //Configure Account & Privacy 
        driver.findElement(By.xpath("//a[.='Accounts & Privacy']")).click();
        driver.findElement(By.xpath("//input[@id='woocommerce_enable_myaccount_registration']")).click();
        driver.findElement(By.xpath("//button[.='Save changes']")).click();
        Assert.assertEquals(text,expectedText);
        
      //Create Users
        driver.findElement(By.xpath("//div[.='Users']")).click();
        driver.findElement(By.xpath("//a[.='Add New User']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='user_login']"))).sendKeys("billdoe");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("billdoe@gmail.com");
        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("Bill");
        driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("Doe");
        WebElement user_pass = driver.findElement(By.xpath("//input[@class='regular-text strong']"));
        user_pass.clear();
        user_pass.sendKeys("a");
        driver.findElement(By.xpath("//input[@class='pw-checkbox']")).click();
        user_pass.clear();
        user_pass.sendKeys("billdoe");
        WebElement user_dd = driver.findElement(By.xpath("//select"));
    	Select user_role = new Select(user_dd);
    	user_role.selectByVisibleText("Customer");
    	driver.findElement(By.xpath("//input[@id='createusersub']")).click();
    	
    	//Update Permalinks
    	driver.findElement(By.xpath("//div[.='Settings']")).click();
    	driver.findElement(By.xpath("//a[.='Permalinks']")).click();
    	driver.findElement(By.xpath("//input[@id='permalink-input-post-name']")).click();
    	driver.findElement(By.xpath("//input[@id='submit']")).click();
    	
    	//Logout user
    	Actions user_logout = new Actions(driver);
    	WebElement user = driver.findElement(By.xpath("//a[contains(.,'Howdy')]"));
    	user_logout.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(user))).perform();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Log Out']"))).click();	
		
		//Login as User
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("billdoe");
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("billdoe");
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
		
		//Place a order
		driver.findElement(By.xpath("//a[.='Shop']")).click();
		driver.findElement(By.xpath("//h2[.='Beanie']")).click();
		driver.findElement(By.xpath("//button[.='Add to cart']")).click();
		driver.findElement(By.xpath("//a[.='View cart']")).click();
		driver.findElement(By.xpath("//span[.='Proceed to Checkout']")).click();
		driver.findElement(By.xpath("//input[@id='billing-first_name']")).sendKeys("Bill");
		driver.findElement(By.xpath("//input[@id='billing-last_name']")).sendKeys("Doe");
		driver.findElement(By.xpath("//input[@id='billing-address_1']")).sendKeys("Noida");
		driver.findElement(By.xpath("//input[@id='billing-city']")).sendKeys("Noida");
		driver.findElement(By.xpath("//input[@id='billing-postcode']")).sendKeys("201301");
		driver.findElement(By.xpath("//span[.='Place Order']")).click();
		driver.findElement(By.xpath("//a[.='My account']")).click();
		driver.findElement(By.xpath("//a[.='Log out']")).click();
	}
}
