package com.khoiphuc27.utils;

import com.khoiphuc27.model.Customer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelWriter {
	
	private static String[] columns = {"Name", "Date Of Birth", "Phone", "Email", "Gender", "Address Line"};
	
	public static boolean exportExcelCustomerList(String fileName, List<Customer> listCustomers) throws IOException, InvalidFormatException{
		
		// Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Customer List");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with Customer data
        int rowNum = 1;
        for(Customer customer: listCustomers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(customer.getName());
            row.createCell(1).setCellValue(customer.getDateOfBirth());
            row.createCell(2).setCellValue(customer.getPhone());    
            row.createCell(3).setCellValue(customer.getEmail());        
            row.createCell(4).setCellValue(customer.isGender());
            row.createCell(5).setCellValue(customer.getAddressLine());
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("D:/" + fileName + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        
        return true;
	
	}

}
