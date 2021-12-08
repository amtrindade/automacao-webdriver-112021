package com.targettrust.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.targettrust.core.BaseTest;
import com.targettrust.core.GlobalProperty;
import com.targettrust.page.LoginPage;
import com.targettrust.page.MainPage;

public class LoginTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	
	@Test
	public void testValidLogin() {
		
		loginPage = new LoginPage();
		
		loginPage.open();
		loginPage.setEnvironment("trindade")
			.setUser(GlobalProperty.getProperty("umovme.user"))
			.setPass("123456");
		
		mainPage = loginPage.submit();
		
		mainPage.clickAvatar();
		
		assertEquals("Aluno 01 (aluno01)", mainPage.getTextAvatar());
		
	}
	
	@Test
	public void testInvalidLogin() {
		loginPage = new LoginPage();
		
		loginPage.open();
		loginPage.setEnvironment("trindade")
			.setUser("alunoinvalido")
			.setPass("123456");
		
		loginPage.submitInvalid();
		assertEquals("ERRO\nLOGIN INVÁLIDO.", loginPage.getMessageError());
		
	}

}
