package com.targettrust.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementsTest {
	
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");				
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidateTitle() {
		assertEquals("Selenium WebDriver Test Page", driver.getTitle());
	}
	
	@Test
	public void testValidateName() {
		WebElement textFieldName = driver.findElement(By.name("txtbox1"));
		
		textFieldName.sendKeys("Antônio");
		
		assertEquals("O nome deveria ser Antônio!", 
				"Antônio", textFieldName.getAttribute("value"));
	}
	
	@Test
	public void testShouldBeDisable() {
		WebElement textFieldName = driver.findElement(By.name("txtbox1"));
		
		assertTrue("Deveria estar habilitado!", textFieldName.isEnabled());
		
		
		WebElement textFieldDisable = driver.findElement(By.name("txtbox2"));

		assertFalse("Deveria estar desabilitado!",	textFieldDisable.isEnabled());
	}
	
	@Test
	public void testRadioGroup() throws InterruptedException {
		List<WebElement> radios = driver.findElements(By.name("radioGroup1"));
	
		//radios.get(2).click();
				
		for (WebElement e : radios) {
			if (e.getAttribute("value").equals("Radio Button 3 selecionado")) {
				e.click();
			}	
		}

		assertEquals(4, radios.size());
		
		assertFalse("Deveria estar selecionado na posição 1", radios.get(0).isSelected());
		assertFalse("Deveria estar selecionado na posição 2", radios.get(1).isSelected());
		assertTrue("Deveria estar selecionado na posição 3", radios.get(2).isSelected());		
		assertFalse("Deveria estar selecionado na posição 4", radios.get(3).isSelected());
		
	}

}
