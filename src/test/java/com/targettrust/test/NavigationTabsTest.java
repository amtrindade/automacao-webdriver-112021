package com.targettrust.test;

import static com.targettust.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.targettrust.inter.NegativeInterface;
import com.targettust.core.BaseTest;

public class NavigationTabsTest extends BaseTest {
	

	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao");
	}

	@Test
	@Category(NegativeInterface.class)
	public void testNavigationTabs() {
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkCpf = getDriver().findElement(By.linkText("Gerador de CPF"));
		linkCpf.click();
		
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		
		assertEquals("Gerador de CPF", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkDragAndDrop = getDriver().findElement(By.linkText("Drag and Drop JQuery"));
		linkDragAndDrop.click();
		
		tabs = new ArrayList<String>(getDriver().getWindowHandles());
		
		getDriver().switchTo().window(tabs.get(2));
		
		assertEquals("jQuery UI Droppable - Default functionality", getDriver().getTitle());
	}


}
