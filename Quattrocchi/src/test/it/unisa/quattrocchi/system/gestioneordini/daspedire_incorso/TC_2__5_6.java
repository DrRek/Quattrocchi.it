package test.it.unisa.quattrocchi.system.gestioneordini.daspedire_incorso;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_2__5_6 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC256() throws Exception {
		driver.get("http://localhost:8080/Quattrocchi/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("userid")).click();
		driver.findElement(By.name("passid")).clear();
		driver.findElement(By.name("passid")).sendKeys("Capra");
		driver.findElement(By.name("userid")).clear();
		driver.findElement(By.name("userid")).sendKeys("ViGal");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.name("inserireNAME")).click();
		driver.get("http://localhost:8080/Quattrocchi//inserisciDatiDiSpedizione?ordineId=999&corriere=Bartolini&tracking=TR867&statoOrdine=in%20corso&dataDiConsegna=ciao");
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
