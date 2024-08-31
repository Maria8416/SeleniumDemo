
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

		// time kept as 15
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void InvalidCredentials() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("afdhjm");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		ScreenShot(driver, "Invalid Login");
		Thread.sleep(5000);
		String ExpecTit = "Invalid credentials";
		// WebElement ActuTitt = driver.findElement(By.xpath("//p[@class='oxd-text
		// oxd-text--p oxd-alert-content-text']"));
		String ActuTit = driver.getTitle();
		/*
		 */
		if (ActuTit.equals("ExpecTit")) {
			System.out.println("Credential matching");
		} else {
			System.out.println(" Failed" + ActuTit);
		}
	}

		
	@Test(priority = 2,enabled=false)
	public void loginTestWithValidCredentials() throws IOException, InterruptedException {
		System.out.println("@Test loginTestWithValidCredentials");
		
		login();
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
		logOut();

	}

	@Test(priority = 3)
	public void addEmployee() throws InterruptedException, IOException {
		System.out.println("@Test addEmployee");
		
		login();
		Thread.sleep(1000);
		driver.findElement(By.linkText("PIM")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
		// driver.findElement(By.xpath("//a[class='oxd-topbar-body-nav-tab-item']")).click();
		driver.findElement(By.name("firstName")).sendKeys("Mann");
		driver.findElement(By.name("lastName")).sendKeys("Jo");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(5000);
		ScreenShot(driver, "Employee added");
		Thread.sleep(5000);
		String Exptit = "Personal Details";
		String Actit = driver.getTitle();
		if (Actit.equalsIgnoreCase(Exptit)) {
			System.out.println("Title Matching" + Actit);

		} else {
			System.out.println("Not Matching" + Actit);

		}

		driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active']")).sendKeys("794211116");

		driver.findElement(By.xpath("//label[normalize-space()='Female']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		System.out.println("Employee added");

		Thread.sleep(5000);
		ScreenShot(driver, "Employee Personal Added");
		Thread.sleep(5000);
		logOut();
	}

	@Test(priority = 4)

	public void searchEmployeeByName() throws InterruptedException, IOException {
		System.out.println("@Test searchEmployeeByName");
		login();
		Thread.sleep(1000);
		driver.findElement(By.linkText("PIM")).click();
		Thread.sleep(500);
		
		driver.findElement(By.linkText("Employee List")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("Mann");
		//driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		System.out.println("Found Employee");
		Thread.sleep(5000);
		
		ScreenShot(driver, "Found Employee");
		Thread.sleep(5000);

		// verify if employee is added
		WebElement AcText = driver.findElement(By.xpath("//div[@role='rowgroup']//div[1]//div[1]//div[3]//div[1]"));
		String ExText = "Mann";
		if (AcText.equals(ExText)) {
			System.out.println("Found Employee");
		} else {
			System.out.println("Not Found Employee");

		}
					
		//Assert.assertEquals("Personal Details", conmessage);
		logOut();
	}
	
	public void login() throws InterruptedException {

		// open url
		driver.get(BaseUrl);
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Logged in");
		Thread.sleep(5000);
	}

	
	public void logOut() throws InterruptedException, IOException {

		// Dropdown
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
		// click on Logout;
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged out");

		Thread.sleep(1000);
		ScreenShot(driver, "Logout Successful");
		Thread.sleep(10000);

	}

	@AfterTest
	public void tearDown() throws InterruptedException, IOException {

		logOut();
		Thread.sleep(5000);

		driver.quit();

	}

}
