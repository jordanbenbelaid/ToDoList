package com.example.toDoListTasks.seleniumTests.helper;



import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class UtilHelper {

	public static void screenShot(WebDriver webdriver, String path) throws Exception {

		//used to capture
		TakesScreenshot shot = ((TakesScreenshot) webdriver);

		// Call method to create image file
		File srcFile = shot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination
		File destFile = new File(path);

		// Copy file at destination
		Files.copy(srcFile, destFile);
	}
}
