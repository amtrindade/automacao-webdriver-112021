package com.targettrust.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.targettrust.core.BaseTest;
import com.targettrust.page.LoginPage;
import com.targettrust.page.MainPage;
import com.targettrust.page.TaskPage;

public class TaskTest extends BaseTest {
	
	private LoginPage loginPage;
	private MainPage mainPage;
	private TaskPage taskPage;
	
	@Before
	public void setUp() {
		loginPage = new LoginPage();
		loginPage.open()
			.setEnvironment("trindade")
			.setUser("aluno01")
			.setPass("123456");
		mainPage = loginPage.submit();
	}
	
	@Test
	public void testSearchTask() {
		String agent = "Aluno 01";
		
		taskPage = mainPage.menuTask();
		taskPage.cleanFilter();
		taskPage.searchTask(agent);
		assertTrue(taskPage.isDisplayed(agent));
	}

}
