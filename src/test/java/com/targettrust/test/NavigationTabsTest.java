package com.targettrust.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.targettrust.inter.NegativeInterface;

public class NavigationTabsTest {
	
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");				
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		driver.get("http://antoniotrindade.com.br/treinoautomacao");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	@Category(NegativeInterface.class)
	public void testNavigationTabs() {
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement linkCpf = driver.findElement(By.linkText("Gerador de CPF"));
		linkCpf.click();
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		assertEquals("Gerador de CPF", driver.getTitle());
		
		driver.switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement linkDragAndDrop = driver.findElement(By.linkText("Drag and Drop JQuery"));
		linkDragAndDrop.click();
		
		tabs = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(tabs.get(2));
		
		assertEquals("jQuery UI Droppable - Default functionality", driver.getTitle());
	}


}
