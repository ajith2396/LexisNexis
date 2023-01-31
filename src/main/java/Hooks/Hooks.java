package Hooks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.factory.Baseclass;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks extends Baseclass {
	
	public Hooks()
	{
		
	}
	@AfterStep
	public void afterStep(Scenario scenario) throws IOException {
		
	try {
		if(driver!=null)
		{
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
	scenario.attach(fileContent, "image/png", "screenshot");
	}
		}
	catch (Exception e) {
		e.printStackTrace();
		}
	}

@After
public void afterHooks(Scenario s) {
	if(driver!=null) {
System.err.println("Scenario-Status: "+s.getName()+" <<<"+s.getStatus()+">>> ");
driver.quit();
	}
}


}
