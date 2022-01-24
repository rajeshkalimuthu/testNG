package com.testng.raj;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TVpurchase {
	static WebDriver driver;
	@BeforeClass(groups="default")
	public static void launch() {
		WebDriverManager.chromedriver().setup();
		 driver= new ChromeDriver();
 driver.get("https://www.flipkart.com/");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	@AfterClass(groups="default")
	public static void close() {
	driver.quit();
	}
	static long st;
@BeforeMethod(groups="default")
	public void startTime() {
		st=System.currentTimeMillis();

	}
	@AfterMethod(groups="default")
	public void endTime() {
		long g=System.currentTimeMillis();
System.out.println("Method time taken by:"+(g-st));
	}
	
	
	@Test(priority=1,groups="smoke")
	public void loginHandling() {
		try {
			WebElement w=driver.findElement(By.xpath("//button[text()='✕']"));
			w.isDisplayed();
			w.click();
		}catch(Exception e) {
			System.out.println("not required");
			
		}
			

	}
	static String ss;
	@Test(priority=2,groups="smoke")
	public void tvSearch() {
		driver.findElement(By.name("q")).sendKeys("samsung tv",Keys.ENTER);
	WebElement search=driver.findElement(By.xpath("(//div[contains(text(),'SAMSUNG')])[2]"));
 ss=search.getText();
System.out.println("tv name is:"+ss);
search.click();
	}
	@Test(priority=3)
	public void handlingWindows() {
		String parent=driver.getWindowHandle();
		
		Set<String> child=driver.getWindowHandles();
		
		for(String x:child) {
			if(!x.equals(parent)) {
				driver.switchTo().window(x);
				System.out.println("windows");
			}
		}
	}
		


}
