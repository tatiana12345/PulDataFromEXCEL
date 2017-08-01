package ExceleUtilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FailedTC_Screenshot {

	public static class NewScreenshotMethod {

		public static String takeScreenshot(WebDriver wd, String fileName) throws IOException{
			fileName = fileName + ".png";
			String Directory = "/Users/tatianakesler/Desktop/test123/";
			File sourceFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File (Directory + fileName));
			String destination = Directory + fileName;
			return destination;
		}
}
}