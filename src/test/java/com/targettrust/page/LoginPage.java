package com.targettrust.page;

import static com.targettrust.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.targettrust.core.GlobalProperty;

public class LoginPage {

	public LoginPage open() {		
		getDriver().get(GlobalProperty.getProperty("umovme.endpoint"));
		return this;
	}
	
	public LoginPage setEnvironment(String environment) {
		WebElement tfEnvironment = getDriver().findElement(By.id("workspace"));
		tfEnvironment.sendKeys(environment);
		return this;
	}
	
	public LoginPage setUser(String user) {
		WebElement tfUser = getDriver().findElement(By.id("username"));
		tfUser.sendKeys(user);		
		return this;
	}
	
	public LoginPage setPass(String password) {
		WebElement tfPass = getDriver().findElement(By.id("password"));
		tfPass.sendKeys(password);
		return this;
	}
	
	public MainPage submit() {
		WebElement btnAcess = getDriver().findElement(By.id("submit_button"));
		btnAcess.click();
		return new MainPage();
	}
	
	public LoginPage submitInvalid() {
		WebElement btnAcess = getDriver().findElement(By.id("submit_button"));
		btnAcess.click();
		return this;
	}
	
	public String getMessageError() {
		WebElement dialogError = getDriver().findElement(By.xpath("//*[@class='nm-li nm-message-error']"));
		return dialogError.getText();
	}

}
