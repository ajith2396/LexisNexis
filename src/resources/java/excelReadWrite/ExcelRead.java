package excelReadWrite;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dbData.DB_connect;

public class ExcelRead {
	
	ExcelWrite excelWrite = new ExcelWrite();
	DB_connect aConnect = new DB_connect();

	public void readExcel() throws Exception
	{
		
		String path = System.getProperty("user.dir");
		File file = new File(path +"/tracking/src/resources/java/excelReadWrite/ExcelImport.xlsx");
		
		FileOutputStream fos = new FileOutputStream(file);
		XSSFWorkbook workbook = excelWrite.workbook;
		workbook.write(fos);
		XSSFSheet sheet=excelWrite.sheet;
	String value = new BigDecimal(sheet.getRow(1).getCell(0).getNumericCellValue()).toString();
		
		System.out.println(value);
		
	}

}
