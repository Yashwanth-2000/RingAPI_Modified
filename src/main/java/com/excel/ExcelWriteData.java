package com.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.response.ValidatableResponse;


public class ExcelWriteData {

	public static void excelWrite(String xlsPath,String sSheetName ,String sData,int nRow,int nCell) throws IOException {

		try {

			// Create an object of FileInputStream class to read excel file
			FileInputStream fis = new FileInputStream(new File(xlsPath));

			// Create object of XSSFWorkbook class
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Read excel sheet by sheet name
			XSSFSheet sheet = workbook.getSheet(sSheetName);

			// Print data present at row 0 column 2
			//	            System.out.println(sheet.getRow(nRow).getCell(nCell).getCellValue());

			// Get the Cell at index 3 from the above row
			XSSFCell cell = sheet.getRow(nRow).getCell(nCell);

			cell.setCellType(CellType.STRING);
			cell.setCellValue(sData);

			// Write the output to the file
			FileOutputStream fileOut = new FileOutputStream(new File(xlsPath));
			workbook.write(fileOut);

			System.out.println("Write Excel is updated successfully");
			fileOut.close();

			// Closing the workbook
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void IntegerExcelWrite(String xlsPath,String sSheetName ,Integer sData,int nRow,int nCell) throws IOException {

		try {

			// Create an object of FileInputStream class to read excel file
			FileInputStream fis = new FileInputStream(new File(xlsPath));

			// Create object of XSSFWorkbook class
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Read excel sheet by sheet name
			XSSFSheet sheet = workbook.getSheet(sSheetName);

			// Print data present at row 0 column 2
			//		            System.out.println(sheet.getRow(nRow).getCell(nCell).getCellValue());

			// Get the Cell at index 3 from the above row
			XSSFCell cell = sheet.getRow(nRow).getCell(nCell);

			cell.setCellType(CellType.STRING);
			cell.setCellValue(sData);

			// Write the output to the file
			FileOutputStream fileOut = new FileOutputStream(new File(xlsPath));
			workbook.write(fileOut);

			System.out.println("Write Excel is updated successfully");
			fileOut.close();

			// Closing the workbook
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}		

	//	}	

	//	----------------------------------------------------------------------------------------


	public static void DemoExcel(String xlsPath,String sSheetName ,String sData,int nRow,int nCell) throws IOException {


		try {

			// Create an object of FileInputStream class to read excel file
			FileInputStream fis = new FileInputStream(new File(xlsPath));

			// Create object of XSSFWorkbook class
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Read excel sheet by sheet name
			XSSFSheet sheet = workbook.getSheet(sSheetName);

			Row row = sheet.getRow(nRow);
			Cell cel = row.createCell(nCell);

			// Get the Cell at index 3 from the above row
			//	             cell = sheet1.getRow(nRow).getCell(nCell);
			//	            cell.setCellValue(aaa);

			//	            cell.setCellType(CellType.);
			cel.setCellValue(sData);

			// Write the output to the file
			FileOutputStream fileOut = new FileOutputStream(new File(xlsPath));
			workbook.write(fileOut);

//			System.out.println("Write Excel is updated successfully");
//			fileOut.close();

			// Closing the workbook
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	
	public static void DemoExcelInteger(String xlsPath,String sSheetName ,Integer sData,int nRow,int nCell) throws IOException {


		try {

			// Create an object of FileInputStream class to read excel file
			FileInputStream fis = new FileInputStream(new File(xlsPath));

			// Create object of XSSFWorkbook class
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Read excel sheet by sheet name
			XSSFSheet sheet = workbook.getSheet(sSheetName);

			Row row = sheet.getRow(nRow);
			Cell cel = row.createCell(nCell);

			// Get the Cell at index 3 from the above row
			//	             cell = sheet1.getRow(nRow).getCell(nCell);
			//	            cell.setCellValue(aaa);

			//	            cell.setCellType(CellType.);
			cel.setCellValue(sData);

			// Write the output to the file
			FileOutputStream fileOut = new FileOutputStream(new File(xlsPath));
			workbook.write(fileOut);

//			System.out.println("Write Excel is updated successfully");
//			fileOut.close();

			// Closing the workbook
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
//	public static void getStringDataFromExcelSheet(String xlsPath,String sSheetName,String sData,int nRow,int nCell) throws EncryptedDocumentException, IOException {
//		FileInputStream fisForExcel=new FileInputStream(xlsPath);
//		Workbook workbook = WorkbookFactory.create(fisForExcel);
//		workbook.getSheet(sSheetName).getRow(nRow).createCell(nCell).setBlank(); 
//	}


}














