package com.targettrust.test;

import static com.targettrust.core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.targettrust.core.BaseTest;

public class CPNJRegularExpressionTest extends BaseTest{

	@Before
	public void setUp() throws Exception {
		getDriver().get("https://www.4devs.com.br/gerador_de_cnpj");
	}

	
	@Test
	public void testCPNJWithDot() throws InterruptedException {
		WebElement btnGerar = getDriver().findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		By locator = By.id("texto_cnpj");
		
		WebElement labelCNPJ = getDriver().findElement(locator);
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions
				.invisibilityOfElementWithText(locator, "Gerando..."));
		
		String cnpj = labelCNPJ.getText();
				
		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$"));
		
	}
	
	@Test
	public void testCPNJWithoutDot() throws InterruptedException {
		WebElement cbNo = getDriver().findElement(By.id("pontuacao_nao"));
		cbNo.click();
		
		WebElement btnGerar = getDriver().findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		By locator = By.id("texto_cnpj");
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions
				.invisibilityOfElementWithText(locator, "Gerando..."));
		
		WebElement labelCNPJ = getDriver().findElement(locator);
		String cnpj = labelCNPJ.getText();

		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{14}$"));
	}


}
