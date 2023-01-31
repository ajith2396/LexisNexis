package dbData;

import java.beans.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead extends ExcelWrite {


	ExcelWrite excelWrite = new ExcelWrite();
	
Statement stmtStatement;
ResultSet rSet;
	public void readExcel() throws Exception
	{	 String filenameString= properties.getProperty("file");

		
		file = new File(filenameString);

		inputStream = new FileInputStream(file);
		
		workbook = new XSSFWorkbook(inputStream);
		sheet  = workbook.getSheet(" query Data ");
		String value = new BigDecimal(sheet.getRow(1).getCell(0).getNumericCellValue()).toString();
        String value1 = sheet.getRow(1).getCell(1).getStringCellValue();
			try {
		properties.setProperty("BundleRefId",value);
		properties.getProperty("BundleRefId");
		properties.setProperty("BundleId",value1);
		properties.getProperty("BundleId");
		FileOutputStream fos = new FileOutputStream(propertyFilePath);
		properties.store(fos, null);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
	
	public String getBundleId()
	{
		String bundleId = properties.getProperty("BundleId");
			return bundleId;	
	}
		public void FileNameGet() throws Exception
		{    {		
			String bundlerefId = properties.getProperty("BundleRefId");
			String secondquery = "use LA_Fabrication select FileName from [tblChildDocumentInfo] (nolock) where BundleRefID = "+bundlerefId+" and IsUploaded = 1 order by FileName desc";

				 rSet=DB_connect.setData(secondquery);

			file = new File("C:\\Users\\maji\\eclipse-workspace\\tracking\\src\\resources\\java\\excelReadWrite\\testing.xlsx");
			inputStream = new FileInputStream(file);;

			 workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet  = workbook.createSheet(" Export query ");
			XSSFRow row=sheet.createRow(0);
			row.createCell(0).setCellValue("FileName");
			int r=1;
		while(rSet.next())
			{
				String FileName=rSet.getString("FileName");
			row=sheet.createRow(r++);
			row.createCell(0).setCellValue(FileName);
			fos = new FileOutputStream(file);
			workbook.write(fos);
		    fos.close();
			}
		
		workbook.removeSheetAt(0);
				
	}
		
		}
}
	

	
