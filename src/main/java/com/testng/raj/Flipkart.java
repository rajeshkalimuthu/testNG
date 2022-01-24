package com.testng.raj;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
@DataProvider(name="mobileName")
public Object[][] mobileNames() {
	
	
	return new Object[][] {{"iPhone"}};
	
}




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
	public void login() {
		try {WebElement w=driver.findElement(By.xpath("//button[text()='✕']"));
			w.isDisplayed();
			w.click();
		}catch(Exception e) {
			System.out.println("not required");
			
		}
			

	}
	static String sss;
	@Test(priority=2,groups="smoke",dataProvider = "mobileName")
	public void mobileSearch(String s) {
		driver.findElement(By.name("q")).sendKeys(s,Keys.ENTER);
	WebElement search=driver.findElement(By.xpath("(//div[contains(text(),"+s+")])[2]"));
 sss=search.getText();
System.out.println("mobile name is:"+sss);
search.click();

	}
	@Test(priority=3)
	public void handling() throws InterruptedException {
		String parent=driver.getWindowHandle();
		
		Set<String> child=driver.getWindowHandles();
		
		for(String x:child) {
			if(!x.equals(parent)) {
				driver.switchTo().window(x);
				System.out.println("windows");
				Thread.sleep(3000);

			}
		}
	}
		
		@Test(priority=4,dataProvider =" mobileName")
		public void buyNow(String s) throws InterruptedException {
		WebElement buy=	driver.findElement(By.xpath("//button[text()='BUY NOW']"));
buy.isDisplayed();
WebElement search=driver.findElement(By.xpath("//span[contains(text(),'"+s+"')]"));
String ssss=search.getText();
System.out.println("mobile name is:"+ssss);

boolean xx=sss.equals(ssss);
System.out.println(xx);
Thread.sleep(3000);

		}
		@Test(priority=5)         
		public void screen() throws IOException {
			TakesScreenshot tk= (TakesScreenshot)driver;
File ff=tk.getScreenshotAs(OutputType.FILE);
File sf= new File(".//target//flip.jpg");
FileUtils.copyFile(ff, sf);


		}
		
		

		

		
		
		
		
}		

	
	
	
	

