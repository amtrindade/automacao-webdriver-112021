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
		
		//TODO adicionar a espera
		Thread.sleep(3000);
		
		WebElement labelCNPJ = driver.findElement(By.id("texto_cnpj"));
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
		
		//TODO adicionar a espera
		Thread.sleep(5000);
		
		WebElement labelCNPJ = driver.findElement(By.id("texto_cnpj"));
		String cnpj = labelCNPJ.getText();

		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{14}$"));
	}


}
