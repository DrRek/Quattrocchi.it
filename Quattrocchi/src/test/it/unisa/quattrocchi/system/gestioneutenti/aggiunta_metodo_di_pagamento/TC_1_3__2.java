package test.it.unisa.quattrocchi.system.gestioneutenti.aggiunta_metodo_di_pagamento;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_1_3__2 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC132() throws Exception {
		driver.get("http://localhost:8080/Quattrocchi/inserisci_carta");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("userid")).click();
		driver.findElement(By.name("passid")).clear();
		driver.findElement(By.name("passid")).sendKeys("Forzajuve");
		driver.findElement(By.name("userid")).clear();
		driver.findElement(By.name("userid")).sendKeys("AntosxA");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.linkText("Benvenuto, AntosxA")).click();
		driver.findElement(By.name("numcc")).click();
		driver.findElement(By.name("numcc")).clear();
		driver.findElement(By.name("numcc")).sendKeys("4256321456325645");
		driver.findElement(By.name("intestatario")).clear();
		driver.findElement(By.name("intestatario")).sendKeys("Lu");
		driver.findElement(By.name("circuito")).clear();
		driver.findElement(By.name("circuito")).sendKeys("VISA");
		driver.findElement(By.name("scadenza")).clear();
		driver.findElement(By.name("scadenza")).sendKeys("07/2023");
		driver.findElement(By.name("cvv")).clear();
		driver.findElement(By.name("cvv")).sendKeys("456");
		driver.findElement(By.name("addCard")).click();
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
