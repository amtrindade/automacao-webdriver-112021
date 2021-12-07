package com.targettrust.page;

import static com.targettrust.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskPage {
	
	public TaskPage cleanFilter() {
		WebElement btnClear = getDriver().findElement(By.id("scheduleList_doClear"));
		btnClear.click();
		return this;
	}
	
	public TaskPage searchTask(String value) {
		WebElement tfSearch = getDriver().findElement(By.id("genericFilter"));
		tfSearch.sendKeys(value);
		
		WebElement btnSearch = getDriver().findElement(By.id("scheduleList_doSearch"));
		btnSearch.click();
		
		return this;
	}
	
	public Boolean isDisplayed(String value) {
		WebElement line = getDriver().findElement(By.xpath("//a[contains(text(), '"+ value +"')]"));
		return line.isDisplayed();
	}

}
