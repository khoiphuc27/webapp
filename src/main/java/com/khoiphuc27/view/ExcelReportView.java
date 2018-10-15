package com.khoiphuc27.view;
 
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.khoiphuc27.model.Customer;;
 
 
 
public class ExcelReportView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment;filename=\"Customers.xls\"");
		 List<Customer> listCustomers = (List<Customer>) model.get("listCustomers");
		 Sheet sheet = workbook.createSheet("Customers");
		 Row header = sheet.createRow(0);
		 header.createCell(0).setCellValue("Customer ID");
		 header.createCell(1).setCellValue("Customer Name");
		 header.createCell(2).setCellValue("Customer Phone");
		 header.createCell(3).setCellValue("Customer Email");
		  
		 int rowNum = 1;
		 for(Customer customer : listCustomers) {
			 Row row = sheet.createRow(rowNum++);
			 row.createCell(0).setCellValue(customer.getId());
			 row.createCell(1).setCellValue(customer.getName());
			 row.createCell(2).setCellValue(customer.getPhone());
			 row.createCell(3).setCellValue(customer.getEmail());
		 }
		 
		 //Fit column width to content
		 for (int i = 0; i < 4; i++)
			 sheet.autoSizeColumn(i);
	}
}