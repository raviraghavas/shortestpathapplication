package com.assign.shortpath.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assign.shortpath.dataload.PopulateVertices;
import com.assign.shortpath.io.DataLoaderFromFile;
import com.assign.shortpath.util.ShortPathGraphPathUtil;
import com.assign.shortpath.valueobject.Vertex;

@RestController
public class ShortestPathController {

	@Autowired
	PopulateVertices popVert; 
	
	@Autowired
	ShortPathGraphPathUtil spgp;

	@Autowired
	DataLoaderFromFile df; 
	
	@RequestMapping("/test")
	public ResponseEntity<String> test()
	{
		//df.readFile("C:\\Users\\ravir\\Desktop\\JobApplication\\Sravan_SA\\Data.xlsx");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/getShortPath")
	public ResponseEntity<String> shortPath(@RequestParam("source") String source, @RequestParam("destination") String destination)
	{
		double time=0.0;
		if(popVert.routeMergeList.size()>0)
		{				
			popVert.populateRouteFromList();
		
			HashMap<String,Vertex> vertmap = popVert.vertMap;

			spgp.computePaths(vertmap.get(source)); // run Dijkstra
			
			List<Vertex> path = spgp.getShortestPathTo(vertmap.get(destination));
			time = spgp.getShortestTimeTo(vertmap.get(destination));
			
			System.out.println("Path: " + path);
		
			return new ResponseEntity<>("The shortest path is "+path+"\n And the number of light years to reach destination is "+time,HttpStatus.OK);
		}
		else {
			System.out.println("No Data Available");
			return new ResponseEntity<>("We do not have data to determine the paths",HttpStatus.OK);
		}

		
	}
}