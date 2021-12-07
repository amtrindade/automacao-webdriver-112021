package com.targettrust.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.targettrust.page.LoginPage;
import com.targettrust.page.MainPage;
import com.targettust.core.BaseTest;

public class LoginTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	
	@Test
	public void testValidLogin() {
		
		loginPage = new LoginPage();
		loginPage.open();
		loginPage.setEnvironment("trindade");
		loginPage.setUser("aluno01");
		loginPage.setPass("123456");
		mainPage = loginPage.submit();
		
		mainPage.clickAvatar();
		
		assertEquals("Aluno 01 (aluno01)", mainPage.getTextAvatar());
		
	}

}
