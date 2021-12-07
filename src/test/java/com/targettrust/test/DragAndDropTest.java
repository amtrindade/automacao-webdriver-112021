package com.targettrust.test;

import static com.targettrust.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.targettrust.core.BaseTest;

public class DragAndDropTest extends BaseTest{
	
	@Before
	public void setUp() throws Exception {
		getDriver().get("https://jqueryui.com/resources/demos/droppable/default.html");
	}

	@Test
	public void testDragAndDrop() throws IOException {
		WebElement origin = getDriver().findElement(By.id("draggable"));
		WebElement target = getDriver().findElement(By.id("droppable"));
		
		assertEquals("Drag me to my target", origin.getText());
		assertEquals("Drop here", target.getText());
		
		File screen = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen , new File("target/before.png"));
		
		new Actions(getDriver()).dragAndDrop(origin, target).perform();
		
		screen = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen , new File("target/after.png"));
		
		assertEquals("Dropped!", target.getText());
	}


}
