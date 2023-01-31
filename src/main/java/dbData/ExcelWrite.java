package dbData;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;


public class ExcelWrite {
	
	DB_connect aConnect = new DB_connect();
	public  XSSFSheet sheet;
	public  XSSFWorkbook workbook;
	public  FileInputStream inputStream;
	public  FileOutputStream fos;
	public  File file;
	public  Row row;
	ResultSet rs1;
	public int BunRefId;
	public String bunId;
	public String modDate;
	public int coun;
Statement stmt;
ResultSet rs;
protected static Properties properties;
protected final String propertyFilePath= "C:\\Users\\maji\\eclipse-workspace\\tracking\\src\\resources\\java\\config\\excelconfig.properties";
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

public void Queryrun() throws Exception
{		
	String dpsicode =properties.getProperty("Dpsicode");
	String query = "use LA_Fabrication select bd.BundleRefID, bd.BundleID, b.ModifiedDate, count(tab.BundleRefID) 'Count' from [dbo].[tblFTKBundleDetails] bd (nolock) join tblFTKBundle b (nolock) on bd.BundleRefID = b.BundleRefID join [dbo].[tblChildDocumentInfo] tab (nolock) on b.BundleRefID = tab.BundleRefID join tblFTKSource s (nolock) on tab.DPSIID = s.DPSIID where b.ModifiedDate > (GETDATE()-1 )  and s.DPSICode = '"+dpsicode+"' group by tab.BundleRefID,bd.BundleRefID, bd.BundleID, b.ModifiedDate order by b.ModifiedDate desc";

	rs=DB_connect.setData(query);

	}
	public void excelheading() throws Exception
	{
		try {
			
	String filenameString= properties.getProperty("file");
			file = new File(filenameString);
	inputStream = new FileInputStream(file);

	 workbook = new XSSFWorkbook(inputStream);
sheet  = workbook.createSheet(" query Data ");

row=sheet.createRow(0);
row.createCell(0).setCellValue("BundleRefId");
row.createCell(1).setCellValue("BundleId");
row.createCell(2).setCellValue("ModifiedDate");
row.createCell(3).setCellValue("Count");
		}
		catch (Exception e) {
			System.out.println("failed to SQL server excel headings");
		}
	}
		public void excelwrite() throws Exception 
		{

int r=1;
while(rs.next())
{
 BunRefId=rs.getInt("BundleRefId");
 bunId=rs.getString("BundleId");
 modDate=rs.getString("ModifiedDate");
coun=rs.getInt("Count");
row=sheet.createRow(r++);
row.createCell(0).setCellValue(BunRefId);
row.createCell(1).setCellValue(bunId);
row.createCell(2).setCellValue(modDate);
row.createCell(3).setCellValue(coun);
}
fos = new FileOutputStream(file);
workbook.write(fos);
fos.close(); 



	}
	
	
			
					
	}

