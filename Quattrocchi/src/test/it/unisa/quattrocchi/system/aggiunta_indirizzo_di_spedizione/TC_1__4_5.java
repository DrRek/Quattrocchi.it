package test.it.unisa.quattrocchi.system.aggiunta_indirizzo_di_spedizione;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_1__4_5 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC145() throws Exception {
		driver.get("http://localhost:8080/Quattrocchi/logout");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("userid")).click();
		driver.findElement(By.name("passid")).clear();
		driver.findElement(By.name("passid")).sendKeys("Forzajuve");
		driver.findElement(By.name("userid")).clear();
		driver.findElement(By.name("userid")).sendKeys("AntosxA");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.linkText("Benvenuto, AntosxA")).click();
		driver.findElement(By.name("indirizzo")).click();
		driver.findElement(By.name("indirizzo")).clear();
		driver.findElement(By.name("indirizzo")).sendKeys("CIAO");
		driver.findElement(By.name("numeroCivico")).clear();
		driver.findElement(By.name("numeroCivico")).sendKeys("45");
		driver.findElement(By.name("cap")).clear();
		driver.findElement(By.name("cap")).sendKeys("80065");
		driver.findElement(By.name("provincia")).clear();
		driver.findElement(By.name("provincia")).sendKeys("NA");
		driver.findElement(By.name("stato")).clear();
		driver.findElement(By.name("stato")).sendKeys("Italia");
		driver.findElement(By.name("addAddress")).click();
		driver.findElement(By.cssSelector("input.btn.btn-outline-secondary")).click();
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