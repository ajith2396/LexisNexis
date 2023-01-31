package com.qa.factory;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Baseclass {
	public static Properties properties;
	private final String propertyFilePath= "C:\\Users\\maji\\eclipse-workspace\\tracking\\src\\resources\\java\\config\\config.properties";


	public static WebDriver driver;
	

	 public Baseclass() {
		
		BufferedReader reader;
		try {			
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Config.properties not found at " + propertyFilePath);
		}		
	
	 }
	 
	 
	
	public void getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null)  driver.get(url);
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	public  void  browserLaunch() {
		
		String browser = properties.getProperty("browser");
		String driverPath = properties.getProperty("driverPath");
if (driver==null)
{
		try {
		
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver=new ChromeDriver();
			}
		
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\maji\\Downloads\\geckodriver-v0.32.0-win32\\geckodriver.exe");
				driver = new FirefoxDriver();
				
		
			} else if (browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", "C:\\Users\\maji\\Downloads\\geckodriver-v0.32.0-win32\\edgeodriver.exe");
				driver = new EdgeDriver();
			} 
			
		} catch (Exception e) {
	e.printStackTrace();
			System.out.println("Browser Failed");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
}

	   
	}
	}
	

	
		
	


	
