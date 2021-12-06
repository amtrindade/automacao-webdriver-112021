package com.targettrust.test;

import static com.targettust.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.targettrust.inter.NegativeInterface;
import com.targettrust.inter.PositiveInterface;
import com.targettust.core.BaseTest;

public class CalcTest extends BaseTest {

	public WebElement textNumber1;
	public WebElement textNumber2;
	public WebElement textTotal;

	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");		
	}
	
	private void mapeiaElementos() {
		textNumber1 = getDriver().findElement(By.id("number1"));
		textNumber2 = getDriver().findElement(By.id("number2"));
		textTotal = getDriver().findElement(By.id("total"));
	}

	@Test
	@Category(NegativeInterface.class)
	public void testSum() throws InterruptedException {
		mapeiaElementos();
		WebElement btnSum = getDriver().findElement(By.id("somar"));
		
		Float value1 = 5.45f;
		Float value2 = 4.01f;
		
		Float total = value1 + value2;
		
		textNumber1.sendKeys(String.valueOf(value1));
		textNumber2.sendKeys(Float.toString(value2));
		
		btnSum.click();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions
				.textToBePresentInElementValue(textTotal, Float.toString(total)));
		
		assertEquals(Float.toString(total), textTotal.getAttribute("value"));
	}
	
	@Test
	@Category(PositiveInterface.class)
	public void testSubtract() {
		mapeiaElementos();
		WebElement btnSubtract = getDriver().findElement(By.id("subtrair"));
		
		Float value1 = 5.5f;
		Float value2 = 4.0f;
		
		Float total = value1 - value2;
		
		textNumber1.sendKeys(String.valueOf(value1));
		textNumber2.sendKeys(Float.toString(value2));
		
		btnSubtract.click();
		
		assertEquals(Float.toString(total), textTotal.getAttribute("value"));
	}
	
}
