package genericLibrary;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRW {
	XSSFWorkbook wb;
	FileInputStream fis;
	public ExcelRW(String path) throws Exception{
		fis = new FileInputStream(path);
		wb= new XSSFWorkbook(fis);		
	}
	 public int getRowcount(String Sheetname){
		 XSSFSheet sheet = wb.getSheet(Sheetname);
		 int rowcount = sheet.getLastRowNum();
		 return rowcount;
		 
		 
	 }
	 
	 public int getcolcount(String Sheetname){
		 XSSFSheet sheet = wb.getSheet(Sheetname);
		 int colcount = sheet.getRow(0).getLastCellNum();
		 return colcount;
	 }
	 
	 
	public String readcell( String Sheetname,int row,int column){
		
		 XSSFSheet sheet = wb.getSheet(Sheetname);
		 
		 XSSFCell cell = sheet.getRow(row).getCell(column);
		 String celltext="";
		 if(cell.getCellType()==Cell.CELL_TYPE_STRING){
			celltext = cell.getStringCellValue();
		 }if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			 celltext= String.valueOf(cell.getNumericCellValue());
		 }if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
			 celltext="";
		 }if(cell.getCellType()==Cell.CELL_TYPE_FORMULA){
			 celltext=cell.getRawValue();		 
		 }
			
		return celltext;
			
	}
	
	public void writecell(String Sheetname,int row,int column,String val){
		XSSFSheet sheet = wb.getSheet(Sheetname);
		sheet.getRow(row).getCell(column).setCellValue(val);	
	}
	
	public void saveandcloseexcel(String path) throws Exception{
		 fis.close();
		 FileOutputStream fos = new FileOutputStream(path);
		 wb.write(fos);
		 fos.close();
		 
		
	}
	
}
