package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.qa.factory.Baseclass;

import dbData.ExcelRead;

public class Page extends Baseclass {
	 By deliveryBundle = By.id("selectedSearchCriteria3");
	 By searchBox = By.id("bundleIDField");
	 By searchKey = By.xpath("//input[@value='Search']");
	 By matchingRecord = By.xpath("//table[@id='message']/tbody/tr/td[1]");
	 By status = By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[9]");
		public ExcelRead excelRead = new ExcelRead();

public Page()
{	
	}
	
	public void DeliveryBundle()
	{		
   driver.findElement(deliveryBundle).click();
}
	public void SearchBox()
	{

		driver.findElement(searchBox).sendKeys(excelRead.getBundleId());	
	}
	
	public void SearchKey()
	{
		
		 driver.findElement(searchKey).click();
	}
	
	public void MatchingRecord()
	{
		
	 driver.findElement(matchingRecord);
	}
	
	public boolean Status()
	{
		boolean stat=false;
		
		String bElement= driver.findElement(status).getText();
		 try 
		 {
				 Assert.assertEquals("Successful", bElement);
		 
			
		 }
		 catch (Exception e) {
			 System.out.println("Not Successful");
			 e.printStackTrace();
		}
return stat;
	}
}

	

