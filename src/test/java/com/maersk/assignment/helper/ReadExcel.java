package com.maersk.assignment.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	private static XSSFSheet excelSheet;
	private static XSSFWorkbook excellWBook;
	private static XSSFCell cell;

	public static Object[][] getData(String filePath, String sheetName) throws Exception {   

		String[][] data = null;		
		try {
			FileInputStream excelFile = new FileInputStream(filePath);
			excellWBook = new XSSFWorkbook(excelFile);
			excelSheet = excellWBook.getSheet(sheetName);
			int startRow = 1;
			int startCol = 0;
			int ci,cj;
			int totalRows = excelSheet.getLastRowNum();
			int totalCols = excelSheet.getRow(0).getLastCellNum();
			System.out.println("Columns: " + totalCols);
			data=new String[totalRows][totalCols];
			ci=0;
			for (int i=startRow;i<=totalRows;i++, ci++) {           	   
				cj=0;
				for (int j=startCol;j<totalCols;j++, cj++){
					data[ci][cj]=getCellData(i,j);
					System.out.println(data[ci][cj]);  
				}
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(data);
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		
		try{
			cell = excelSheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		}
		catch (Exception e){
			return "";
		}
	}
	
	public static void main(String[] args) throws Exception {
		getData(System.getProperty("user.dir") + "/src/test/resources/test-data/TestData.xlsx", "WeatherAPI");
	}
}
