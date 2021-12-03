package com.targettrust.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CPNJRegularExpressionTest {

	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");				
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		driver.get("https://www.4devs.com.br/gerador_de_cnpj");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testCPNJWithDot() throws InterruptedException {
		WebElement btnGerar = driver.findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		By locator = By.id("texto_cnpj");
		
		WebElement labelCNPJ = driver.findElement(locator);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.invisibilityOfElementWithText(locator, "Gerando..."));
		
		String cnpj = labelCNPJ.getText();
				
		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$"));
		
	}
	
	@Test
	public void testCPNJWithoutDot() throws InterruptedException {
		WebElement cbNo = driver.findElement(By.id("pontuacao_nao"));
		cbNo.click();
		
		WebElement btnGerar = driver.findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		By locator = By.id("texto_cnpj");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.invisibilityOfElementWithText(locator, "Gerando..."));
		
		WebElement labelCNPJ = driver.findElement(locator);
		String cnpj = labelCNPJ.getText();

		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{14}$"));
	}


}
