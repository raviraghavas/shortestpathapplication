package com.assign.shortpath.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assign.shortpath.dataload.PopulateVertices;

@Component
public class DataLoaderFromFile {

	@Autowired
	PopulateVertices ppv;
	
	
	public void readFile(String FILE_NAME) {
		
		try {

			File fl = new File(FILE_NAME);
			System.out.println(fl.exists());
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			
			Workbook wkbook = new XSSFWorkbook(excelFile);

			System.out.println(wkbook.getNumberOfSheets());
			
			
			wkbook.forEach(M -> {
		
				Sheet datatypeSheet = M;
				
				if("Routes".equalsIgnoreCase(datatypeSheet.getSheetName()))
				{
					ppv.populateRouteFromSheet(datatypeSheet);
				}
				
				if("Traffic".equalsIgnoreCase(datatypeSheet.getSheetName()))
				{
					ppv.populateTrafficFromSheet(datatypeSheet);
				}
				
				if("Planet Names".equalsIgnoreCase(datatypeSheet.getSheetName()))
				{
					ppv.populatePlanetsFromSheet(datatypeSheet);
				}
				
			});

			ppv.mergeRouteAndTraffic();

			ppv.insertDataIntoDatabase();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}