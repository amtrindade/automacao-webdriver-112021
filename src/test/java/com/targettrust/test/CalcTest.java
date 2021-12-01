package com.targettrust.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalcTest {

	public WebDriver driver;
	public WebElement textNumber1;
	public WebElement textNumber2;
	public WebElement textTotal;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");				
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		driver.get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");		
	}
	
	private void mapeiaElementos() {
		textNumber1 = driver.findElement(By.id("number1"));
		textNumber2 = driver.findElement(By.id("number2"));
		textTotal = driver.findElement(By.id("total"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testSum() throws InterruptedException {
		mapeiaElementos();
		WebElement btnSum = driver.findElement(By.id("somar"));
		
		Float value1 = 5.45f;
		Float value2 = 4.01f;
		
		Float total = value1 + value2;
		
		textNumber1.sendKeys(String.valueOf(value1));
		textNumber2.sendKeys(Float.toString(value2));
		
		btnSum.click();
		
		//TODO voltar aqui para arrumar
		Thread.sleep(3000);
		
		assertEquals(Float.toString(total), textTotal.getAttribute("value"));
	}
	
	@Test
	public void testSubtract() {
		mapeiaElementos();
		WebElement btnSubtract = driver.findElement(By.id("subtrair"));
		
		Float value1 = 5.5f;
		Float value2 = 4.0f;
		
		Float total = value1 - value2;
		
		textNumber1.sendKeys(String.valueOf(value1));
		textNumber2.sendKeys(Float.toString(value2));
		
		btnSubtract.click();
		
		assertEquals(Float.toString(total), textTotal.getAttribute("value"));
	}
	
}
