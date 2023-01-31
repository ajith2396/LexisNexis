package dbData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;




public class DB_connect {
	private static Properties properties;
	private final String propertyFilePath= "C:\\Users\\maji\\eclipse-workspace\\tracking\\src\\resources\\java\\config\\excelconfig.properties";
    public static WebDriver driver;
	
	public void ExcelConfigFileReader(){
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
			throw new RuntimeException("ExcelConfig.properties not found at " + propertyFilePath);
		}		
	}
	private static Connection connect;
public static Connection getConnection() 
	{
	try {Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	String url = properties.getProperty("url");
	String username = properties.getProperty("username");
	String password = properties.getProperty("password");

	 connect = DriverManager.getConnection(url,username,password );
	if (connect!=null) {
		//System.out.println("Connected to SQL server successfully");
	    Assert.assertTrue(true);
	}
		
	} catch (Exception e) {
		//System.out.println("Not Connected to SQL server successfully");
		e.printStackTrace();
	}

	

return connect;
	}
	

public static ResultSet setData(String s) throws Exception
{
	return DB_connect.getConnection().createStatement().executeQuery(s);
	}



}
