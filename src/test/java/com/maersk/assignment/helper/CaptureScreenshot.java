package com.maersk.assignment.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	WebDriver driver;
	public CaptureScreenshot(WebDriver driver) {
		this.driver=driver;
	}
	public void takeScreenShot(String fileName) throws IOException {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir")+ "/src/test/resources/screenshots/" + fileName + ".png");
		FileUtils.copyFile(srcFile, destFile); 
	}

}
