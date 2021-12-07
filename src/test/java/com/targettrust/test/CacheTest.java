package com.targettrust.test;

import static com.targettust.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.targettrust.inter.NegativeInterface;
import com.targettrust.inter.PositiveInterface;
import com.targettust.core.BaseTest;

public class CacheTest extends BaseTest{

	@Before
	public void setUp() throws Exception {	
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao");		
	}
	
	@Test
	@Category({PositiveInterface.class, NegativeInterface.class})
	public void testNavigationWithCache() {
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement menuAlert = getDriver().findElement(By.linkText("Alerts and Confirmations"));
		menuAlert.click();
		
		assertEquals("Alerts and Confirmations", getDriver().getTitle());
		
		WebElement menuCalculator = getDriver().findElement(By.linkText("Calculadora"));
		menuCalculator.click();
		
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		WebElement menuTable = getDriver().findElement(By.linkText("Localizar Table"));
		menuTable.click();
		
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
		getDriver().navigate().back();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().back();
		assertEquals("Alerts and Confirmations", getDriver().getTitle());
		
		getDriver().navigate().back();
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Alerts and Confirmations", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
	}
	
}
