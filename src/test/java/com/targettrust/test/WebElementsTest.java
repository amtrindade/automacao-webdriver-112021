package com.targettrust.test;

import static com.targettust.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.targettrust.inter.NegativeInterface;
import com.targettrust.inter.PositiveInterface;
import com.targettust.core.BaseTest;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebElementsTest extends BaseTest {
	
	public WebDriverWait wait;

	@Before
	public void setUp() throws Exception {
		
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");		
		wait = new WebDriverWait(getDriver(), 10);
		
	}
		
	@Test
	@Category(PositiveInterface.class)
	public void testA_ValidateTitle() {
		assertEquals("Selenium WebDriver Test Page", getDriver().getTitle());
	}
	
	@Ignore("Bug registrado no Jira cod-323")
	@Test
	@Category(PositiveInterface.class)
	public void test1_ValidateName() {
		WebElement textFieldName = getDriver().findElement(By.name("txtbox1"));
		
		textFieldName.sendKeys("Antônio");
		
		assertEquals("O nome deveria ser Antônio!", 
				"Antônio", textFieldName.getAttribute("value"));
	}
	
	@Test
	@Category(PositiveInterface.class)
	public void should1_BeDisable() {
		WebElement textFieldName = getDriver().findElement(By.name("txtbox1"));
		
		assertTrue("Deveria estar habilitado!", textFieldName.isEnabled());
		
		
		WebElement textFieldDisable = getDriver().findElement(By.name("txtbox2"));

		assertFalse("Deveria estar desabilitado!",	textFieldDisable.isEnabled());
	}
	
	@Test
	@Category(PositiveInterface.class)
	public void testRadioGroup() throws InterruptedException {
		List<WebElement> radios = getDriver().findElements(By.name("radioGroup1"));
	
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
	
	@Test
	public void testValidateDropDownSingle() throws InterruptedException {
		WebElement dropSingle = getDriver().findElement(By.name("dropdownlist"));
		Select selectSingle = new Select(dropSingle);
		
		selectSingle.selectByVisibleText("Item 1");
		selectSingle.selectByVisibleText("Item 9");
		selectSingle.selectByVisibleText("Item 7");
		
		assertEquals("Item 7", selectSingle.getFirstSelectedOption().getText());
	}
	
	@Test
	@Category(NegativeInterface.class)
	public void testValidateDropDownMultiple() {
		WebElement dropMulti = getDriver().findElement(By.name("multiselectdropdown"));
		Select selectMulti = new Select(dropMulti);
		
		selectMulti.selectByIndex(4);
		selectMulti.selectByIndex(7);
		selectMulti.selectByVisibleText("Item 9");
		
		List<WebElement> allSelected = selectMulti.getAllSelectedOptions();
		
		assertEquals(3, allSelected.size());
		
		assertEquals("Item 5", allSelected.get(0).getText());
		assertEquals("Item 8", allSelected.get(1).getText());
		assertEquals("Item 9", allSelected.get(2).getText());
		
		selectMulti.deselectByVisibleText("Item 5");
		
		allSelected = selectMulti.getAllSelectedOptions();
		
		assertEquals(2, allSelected.size());
			
		assertEquals("Item 8", allSelected.get(0).getText());
		assertEquals("Item 9", allSelected.get(1).getText());
		
	}
	
	@Test
	@Category(NegativeInterface.class)
	public void testIframe() {
		getDriver().switchTo().frame("iframe_d");		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav/button")));
		
		WebElement menu = getDriver().findElement(By.xpath("//nav/button"));
		menu.click();
		
		WebElement textFieldSearch = getDriver().findElement(By.cssSelector("span > input"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span > input")));
		
		textFieldSearch.sendKeys("Antônio");
		
		assertEquals("Antônio", textFieldSearch.getAttribute("value"));
				
		getDriver().switchTo().defaultContent();
		
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		assertTrue(btnAlert.isDisplayed());
	}
	
	@Test
	@Category(NegativeInterface.class)
	public void testB_Alert() {
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alert = getDriver().switchTo().alert();
		
		assertEquals("Eu sou um alerta!", alert.getText());
		
		alert.accept();		
	}
	
	@Test 
	@Category(NegativeInterface.class)
	public void testConfirm() {
		WebElement btnConfirm = getDriver().findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirm = getDriver().switchTo().alert();
		
		assertEquals("Pressione um botão!", confirm.getText());
		
		confirm.dismiss();
		
		btnConfirm.click();
		assertEquals("Pressione um botão!", confirm.getText());
		confirm.accept();
	}
	
	@Test
	public void testPrompt() throws InterruptedException {
		WebElement btnPrompt = getDriver().findElement(By.name("promptbtn"));
		btnPrompt.click();
		
		Alert prompt = getDriver().switchTo().alert();
		
		assertEquals("Por favor, insira seu nome:", prompt.getText());
		
		prompt.sendKeys("Antonio");		
		
		Thread.sleep(3000);
		
		prompt.accept();
		
		
		
	}
	
	

}
