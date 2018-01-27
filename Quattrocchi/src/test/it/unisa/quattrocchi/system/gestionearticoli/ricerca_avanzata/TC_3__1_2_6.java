package test.it.unisa.quattrocchi.system.gestionearticoli.ricerca_avanzata;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TC_3__1_2_6 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC3126() throws Exception {
		driver.get("http://localhost:8080/Quattrocchi/");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		driver.findElement(By.cssSelector("input.btn.btn-outline-secondary")).click();
		driver.findElement(By.name("marca")).click();
		new Select(driver.findElement(By.name("marca"))).selectByVisibleText("Lindberg");
		driver.findElement(By.cssSelector("option[value=\"Lindberg\"]")).click();
		driver.findElement(By.name("prezzoMin")).click();
		driver.findElement(By.name("prezzoMin")).clear();
		driver.findElement(By.name("prezzoMin")).sendKeys("50");
		driver.findElement(By.name("prezzoMax")).click();
		driver.findElement(By.name("prezzoMax")).clear();
		driver.findElement(By.name("prezzoMax")).sendKeys("60");
		driver.findElement(By.name("colore")).click();
		driver.findElement(By.name("colore")).clear();
		driver.findElement(By.name("colore")).sendKeys("nero");
		driver.findElement(By.id("advancedSearch")).click();
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