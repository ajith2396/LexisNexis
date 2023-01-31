package testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features="C:/Users/maji/eclipse-workspace/tracking/src/resources/java/com/features/file.feature",
glue = {"stepDefinitions","Hooks"})//plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
	
	}
	


