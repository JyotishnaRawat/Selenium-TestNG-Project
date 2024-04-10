package Sauce_demo_Project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Selenium_Project {

	WebDriver driver;
	WebDriverWait wait;
	ExtentReports er;
	ExtentTest test;
	
    
	@BeforeTest
	//open a browser and get url
	public void setup() {
		er = new ExtentReports(System.getProperty("user.dir")+"//SauceTestng.html");//define the path for my folder by using directory
	    test = er.startTest("saucedemo_report");
	    driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
	}
	
	
	
	@Test(groups = "smoke" , priority =  0)
	//page contains title 
	public void Title() throws InterruptedException {
		WebElement Swag = driver.findElement(By.xpath("//div[.='Swag Labs']"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains("Swag Labs"));
		Thread.sleep(2000);
		System.out.println("This page contains title"); 
		test.log(LogStatus.PASS, "It is sucessful");
		
	}
	
	
	@Test(groups = "smoke" , priority = 1)
	//Login the application
	public void Login() throws InterruptedException {
		WebElement sign_in = driver.findElement(By.id("user-name"));
		wait.until(ExpectedConditions.elementToBeClickable(sign_in));
		sign_in.sendKeys("standard_user");
		test.log(LogStatus.PASS, "Username is clickable");
		Thread.sleep(2000);
		
		WebElement secret = driver.findElement(By.name("password"));
		wait.until(ExpectedConditions.elementToBeClickable(secret));
		secret.sendKeys("secret_sauce");
		//wait.until(ExpectedConditions.elementToBeClickable("secret")
		test.log(LogStatus.PASS, "Password is clickable");
		Thread.sleep(2000);
		
		WebElement clicks = driver.findElement(By.xpath("//input[@type='submit']"));
		wait.until(ExpectedConditions.elementToBeClickable(clicks));
		clicks.click();
		System.out.println("Login sucessfully");
		test.log(LogStatus.PASS, "Login is sucessful");
		Thread.sleep(2000);
	}
	
	
	
	
	
	
	@Test(groups = "smoke" , priority = 3)
	//Add to cart the application
	public void Add_to_cart() throws InterruptedException {
		 
		WebElement add =  driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
		add.click();
		test.log(LogStatus.PASS, "Backpack is added to cart");
		Thread.sleep(2000);
	}
	
	
	
	
	@Test(groups ="smoke" , priority = 4)
	//checking the itme added to cart 
	public void cart_item() throws InterruptedException {
		WebElement shopping_cart =driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
		wait.until(ExpectedConditions.elementToBeClickable(shopping_cart));
		shopping_cart.click();
		test.log(LogStatus.PASS, "Backpack is available in cart");
		Thread.sleep(2000);
	}
	

	@Test(groups = "smoke" , priority = 5)
	//checkout the item
	public void checkout() throws InterruptedException {
		WebElement proceed = driver.findElement(By.id("checkout"));
		wait.until(ExpectedConditions.elementToBeClickable(proceed));
		proceed.click();
		test.log(LogStatus.PASS, "Backpack has been checkout");
		Thread.sleep(2000);
	}
	
	
	@Test(groups = "smoke" , priority = 6)
	//adding additional information
		public void details() throws InterruptedException {
		WebElement attribute = driver.findElement(By.id("first-name"));
		WebElement attribute1 =	driver.findElement(By.name("lastName"));
		WebElement attribute2 =	driver.findElement(By.name("postalCode"));
		WebElement attribute3 = driver.findElement(By.id("continue"));
		attribute.sendKeys("jyotsna");
		Thread.sleep(1000);
		attribute1.sendKeys("rawat");
		Thread.sleep(1000);
		attribute2.sendKeys("248007");
		Thread.sleep(1000);
        attribute3.click();
		Thread.sleep(1000);
		test.log(LogStatus.PASS, "For procedding adding details is sucessful");
		}
	
	
	
	
	@Test(groups = "smoke" , priority =7  )
	//payment mode
	public void payment() throws InterruptedException {
		WebElement  finish = driver.findElement(By.name("finish"));
		wait.until(ExpectedConditions.elementToBeClickable(finish));
		finish.click();
		test.log(LogStatus.PASS, "Payment is sucessful");
		Thread.sleep(2000);
	}
	
	
	@Test(groups = "smoke" , priority = 8)
	// order successful
	//take a screenshot
	public void order() throws InterruptedException, IOException {
		System.out.println("Thank you for your order");
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("B:\\Selenium\\screenshot\\screenshot.png");
		
		FileUtils.copyFile(src, dest);
	
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Your order is sucessful");
	}

	//close the browser
	@AfterTest
	public void closeup(){
		driver.close();
		test.log(LogStatus.PASS, "closed browsers");
		
	
		er.endTest(test);
		er.flush();
	}
	}

