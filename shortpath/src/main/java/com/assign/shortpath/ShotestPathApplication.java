package com.assign.shortpath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.assign.shortpath.io.DataLoaderFromFile;

@SpringBootApplication
public class ShotestPathApplication {

	@Value("${spring.dataload.excelfile.name}")
	private String fileName;
	
	@Autowired
	DataLoaderFromFile df; 
	
	public static void main(String[] args) {
		
		SpringApplication.run(ShotestPathApplication.class);
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	private void loadFile()
	{
		df.readFile(fileName);
	}
	
}
