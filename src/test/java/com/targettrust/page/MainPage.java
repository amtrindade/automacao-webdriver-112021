package com.targettrust.page;

import static com.targettrust.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {
	
	public MainPage clickAvatar() {
		WebElement avatarMenu = getDriver().findElement(By.cssSelector("div.topbar-container.right"));
		avatarMenu.click();
		return this;
	}
	
	public String getTextAvatar() {
		WebElement menuAvatar = getDriver().findElement(By.xpath("//span[@class='text-login']/.."));
		return menuAvatar.getText();
	}

}
