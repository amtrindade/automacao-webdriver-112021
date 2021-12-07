package com.targettrust.core;

import static com.targettrust.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public abstract class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	
	@After()
	public void tearDown() throws IOException {
		File screen = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen , new File("target"+ File.separator + testName.getMethodName() +".png"));
		
		DriverFactory.killDriver();
	}
}
