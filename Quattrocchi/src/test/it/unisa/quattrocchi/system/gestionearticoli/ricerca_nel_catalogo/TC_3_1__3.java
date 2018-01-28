package test.it.unisa.quattrocchi.system.gestionearticoli.ricerca_nel_catalogo;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_3_1__3 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC313() throws Exception {
		driver.get("http://localhost:8080/Quattrocchi/");
		driver.findElement(By.name("toSearch")).click();
		driver.findElement(By.name("toSearch")).clear();
		driver.findElement(By.name("toSearch")).sendKeys("RAY");
		driver.findElement(By.name("toSearch")).sendKeys(Keys.ENTER);
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
