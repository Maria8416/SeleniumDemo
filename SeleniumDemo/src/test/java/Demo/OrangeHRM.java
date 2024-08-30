
package Demo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import junit.framework.Assert;

public class OrangeHRM {
	public static void ScreenShot(WebDriver driver, String test) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File srcFile = screen.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("./ScreeshotOrangeHRM/" + test + ".png"));

	}

	public String BaseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;

	@BeforeTest
	public void setup() {
		System.out.println("Before Test Executed");
		driver = new ChromeDriver();

		// open url
		driver.get(BaseUrl);

		// time kept as 60
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@Test(priority=1)
	public void InvalidCredentials() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("afdhjm");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		ScreenShot(driver, "Invalid Login");
		Thread.sleep(5000);
		String ExpecTit = "Invalid credentials";
		WebElement ActuTitt = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']"));
		String ActuTit = ActuTitt.getText();
		/*
		 * Assert.assertEquals(ExpecTit, ActuTit); System.out.println("Invalid Cred");
		 */
		if (ActuTit.equals("ExpecTit")) {
			System.out.println("Credential matching");
		} else {
			System.out.println(" Failed" + ActuTit);
		}
	}

	@Test(priority=2)
	public void loginTestWithValidCredentials() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		ScreenShot(driver, "Login Successful");
		Thread.sleep(5000); // verify if login successful String pageTitle =
		driver.getTitle();
		String ExpecTit = "OrangeHRM";
		String pageTitle = driver.getTitle();
		/*
		 * if (pageTitle.equals("ExpecTit")) { System.out.println("Login Successful"); }
		 * else { System.out.println("Login Failed"); }
		 */

		Assert.assertEquals(pageTitle, ExpecTit);
		System.out.println("Login Successful");

	}

	@Test(priority=3)
	public void logOut() throws InterruptedException, IOException {
		// driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		System.out.println("Logged out");

		Thread.sleep(1000);
		ScreenShot(driver, "Logout Successful");
		Thread.sleep(10000);

	}

	@AfterTest
	public void tearDown() throws InterruptedException {

		Thread.sleep(5000);

		driver.quit();

	}

}
