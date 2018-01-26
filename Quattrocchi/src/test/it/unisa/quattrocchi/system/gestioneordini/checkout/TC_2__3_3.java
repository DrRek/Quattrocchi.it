package test.it.unisa.quattrocchi.system.gestioneordini.checkout;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_2__3_3 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC233() throws Exception {
		driver.get("http://localhost:8080/Quattrocchi/");
		driver.findElement(By.linkText("Catalogo")).click();
		driver.findElement(By.cssSelector("img[alt=\"pic\"]")).click();
		driver.findElement(By.id("addCart")).click();
		driver.findElement(By.id("addCart")).click();
		driver.findElement(By.linkText("2")).click();
		driver.findElement(By.xpath("//input[@value='Accedi per il checkout']")).click();
		driver.findElement(By.name("userid")).click();
		driver.findElement(By.name("userid")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [doubleClick | name=userid | ]]
		driver.findElement(By.name("userid")).click();
		driver.findElement(By.name("passid")).clear();
		driver.findElement(By.name("passid")).sendKeys("Forzajuve");
		driver.findElement(By.name("userid")).clear();
		driver.findElement(By.name("userid")).sendKeys("AntosxA");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.linkText("2")).click();
		driver.findElement(By.xpath("//input[@value='Checkout']")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}