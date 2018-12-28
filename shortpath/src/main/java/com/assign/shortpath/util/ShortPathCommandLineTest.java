package com.assign.shortpath.util;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assign.shortpath.dataload.PopulateVertices;
import com.assign.shortpath.io.DataLoaderFromFile;
import com.assign.shortpath.valueobject.Vertex;

//@RestController
public class ShortPathCommandLineTest {

	@Autowired
	PopulateVertices popVert; 
	
	@Autowired
	ShortPathGraphPathUtil spgp;

	@Autowired
	DataLoaderFromFile df; 
	
//	@RequestMapping("/test")
	public void test()
	{
		df.readFile("C:\\Users\\ravir\\Desktop\\JobApplication\\Sravan_SA\\Data.xlsx");
	
		double time= 0.0;
		if(popVert.routeMergeList.size()>0)
		{				
			popVert.populateRouteFromList();
		
			HashMap<String,Vertex> vertmap = popVert.vertMap;

			spgp.computePaths(vertmap.get("A")); // run Dijkstra
			
			List<Vertex> path = spgp.getShortestPathTo(vertmap.get("J'"));
			time = spgp.getShortestTimeTo(vertmap.get("J'"));
			
			
			System.out.println("Path: " + path);
		}
		else {
			System.out.println("No Data Available");
		}

	}
}
