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

public class CPFRegularExpressionTest {

	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");				
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		driver.get("https://www.geradordecpf.org/");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testCpfWithDot() throws InterruptedException {
		WebElement cbPontos = driver.findElement(By.id("cbPontos"));
		cbPontos.click();
		
		WebElement btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
		btnGerar.click();
		
		WebElement textFieldCPF = driver.findElement(By.id("numero"));
		
		String cpf = textFieldCPF.getAttribute("value");
		System.out.println(cpf);
		
		Thread.sleep(3000);
		
		assertTrue(cpf.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
	}
	
	@Test
	public void testCpfWhithoutDot() {
		WebElement btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
		btnGerar.click();
		
		WebElement textFieldCPF = driver.findElement(By.id("numero"));
		
		String cpf = textFieldCPF.getAttribute("value");
		System.out.println(cpf);
		
		assertTrue(cpf.matches("^[0-9]{11}$"));
	}
}
