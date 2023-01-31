package excelReadWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dbData.DB_connect;

public class ExcelWrite {
	
	DB_connect aConnect = new DB_connect();
	public XSSFSheet sheet;
	public XSSFWorkbook workbook;
	public void excelwrite() throws Exception
	{
	String path = System.getProperty("user.dir");
	File file = new File(path +"/tracking/src/resources/java/excelReadWrite/ExcelImport.xlsx");
    FileInputStream inputStream = new FileInputStream(file);

	 workbook = new XSSFWorkbook(inputStream);
sheet  = workbook.createSheet(" query Data ");

Row row=sheet.createRow(0);
row.createCell(0).setCellValue("BundleRefId");
row.createCell(1).setCellValue("BundleId");
row.createCell(2).setCellValue("ModifiedDate");
row.createCell(3).setCellValue("Count");
int r=1;
ResultSet rs = aConnect.rs;
while(rs.next())
{
int BunRefId=rs.getInt("BundleRefId");
String bunId=rs.getString("BundleId");
String modDate=rs.getString("ModifiedDate");
int coun=rs.getInt("Count");
row=sheet.createRow(r++);
row.createCell(0).setCellValue(BunRefId);
row.createCell(1).setCellValue(bunId);
row.createCell(2).setCellValue(modDate);
row.createCell(3).setCellValue(coun);

}

FileOutputStream fos = new FileOutputStream(file);
workbook.write(fos);
fos.close(); 
Connection connect=aConnect.connect;
connect.close();

	}

}
