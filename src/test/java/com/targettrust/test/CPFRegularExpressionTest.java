package com.targettrust.test;

import static com.targettrust.core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.targettrust.core.BaseTest;

public class CPFRegularExpressionTest extends BaseTest{

	@Before
	public void setUp() throws Exception {
		getDriver().get("https://www.geradordecpf.org/");
	}

	@Test
	public void testCpfWithDot() throws InterruptedException {
		WebElement cbPontos = getDriver().findElement(By.id("cbPontos"));
		cbPontos.click();
		
		WebElement btnGerar = getDriver().findElement(By.id("btn-gerar-cpf"));
		btnGerar.click();
		
		WebElement textFieldCPF = getDriver().findElement(By.id("numero"));
		
		String cpf = textFieldCPF.getAttribute("value");
		System.out.println(cpf);
		
		Thread.sleep(3000);
		
		assertTrue(cpf.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
	}
	
	@Test
	public void testCpfWhithoutDot() {
		WebElement btnGerar = getDriver().findElement(By.id("btn-gerar-cpf"));
		btnGerar.click();
		
		WebElement textFieldCPF = getDriver().findElement(By.id("numero"));
		
		String cpf = textFieldCPF.getAttribute("value");
		System.out.println(cpf);
		
		assertTrue(cpf.matches("^[0-9]{11}$"));
	}
}
