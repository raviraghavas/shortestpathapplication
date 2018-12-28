package com.assign.shortpath.dataload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assign.shortpath.jpa.PlanetInformation;
import com.assign.shortpath.jpa.PlanetInformationRepository;
import com.assign.shortpath.jpa.RouteInformation;
import com.assign.shortpath.jpa.RouteInformationRepository;
import com.assign.shortpath.valueobject.Edge;
import com.assign.shortpath.valueobject.PlanetName;
import com.assign.shortpath.valueobject.Route;
import com.assign.shortpath.valueobject.Traffic;
import com.assign.shortpath.valueobject.Vertex;

@Component
public class PopulateVertices {

	@Autowired
	RouteInformationRepository rir;

	@Autowired
	PlanetInformationRepository pir;

	public HashMap<String,Vertex> vertMap = new HashMap<String,Vertex>();
	public List<Route> routeList = new ArrayList<Route>();
	public List<Traffic> traffList = new ArrayList<Traffic>();
	public List<PlanetName> plaNameList = new ArrayList<PlanetName>();
	public List<Route> routeMergeList = new ArrayList<Route>();

	public void populateRouteFromSheet(Sheet datatypeSheet)
	{
		routeList = new ArrayList<Route>();

		Iterator<Row> iterator = datatypeSheet.iterator();

		iterator.next();//To skip the header row.

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			Route traff = new Route();

			while (cellIterator.hasNext()) {

				Cell currentCell = cellIterator.next();

				switch(currentCell.getColumnIndex()) {

				case 0:
					break;

				case 1:
					traff.setSource(currentCell.getStringCellValue());
					break;

				case 2:
					traff.setDestination(currentCell.getStringCellValue());
					break;

				case 3:
					traff.setDistance(currentCell.getNumericCellValue());
					break;

				default:
					break;
				}

			}
			routeList.add(traff);
		}		

	}

	public void populateTrafficFromSheet(Sheet datatypeSheet)
	{
		traffList = new ArrayList<Traffic>();

		Iterator<Row> iterator = datatypeSheet.iterator();

		iterator.next();//To skip the header row.

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			Traffic traff = new Traffic();

			while (cellIterator.hasNext()) {

				Cell currentCell = cellIterator.next();

				switch(currentCell.getColumnIndex()) {

				case 0:
					break;

				case 1:
					traff.setSource(currentCell.getStringCellValue());
					break;

				case 2:
					traff.setDestination(currentCell.getStringCellValue());
					break;

				case 3:
					traff.setDelayDuration(currentCell.getNumericCellValue());
					break;

				default:
					break;
				}

			}
			traffList.add(traff);
		}		

	}

	public void populatePlanetsFromSheet(Sheet datatypeSheet)
	{
		plaNameList = new ArrayList<PlanetName>();

		Iterator<Row> iterator = datatypeSheet.iterator();

		iterator.next();//To skip the header row.

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			PlanetName planName = new PlanetName();

			while (cellIterator.hasNext()) {

				Cell currentCell = cellIterator.next();

				switch(currentCell.getColumnIndex()) {

				case 0:
					planName.setPlanetId(currentCell.getStringCellValue());
					break;

				case 1:
					planName.setName(currentCell.getStringCellValue());
					break;

				default:
					break;
				}

			}
			plaNameList.add(planName);
		}		

	}

	public void populateRouteFromList() {

		vertMap = new HashMap<String,Vertex>();

		for(Route traff:routeList)
		{
			if(!vertMap.containsKey(traff.source))
			{
				vertMap.put(traff.source, new Vertex(traff.source));
			}
			if(!vertMap.containsKey(traff.destination))
			{
				vertMap.put(traff.destination, new Vertex(traff.destination));
			}
		}

		routeList.forEach(traff -> {
			vertMap.get(traff.source).adjacencies.add(new Edge(vertMap.get(traff.destination), traff.distance,traff.delay));
		});		

	}


	public List<Route> mergeRouteAndTraffic()
	{
		routeMergeList = new ArrayList<Route>();

		routeMergeList.addAll(routeList);

		routeMergeList.forEach(M ->
		{	
			traffList.forEach(N -> {
				Traffic traff = N;
				if(traff.source.equalsIgnoreCase(M.source)
						&& traff.destination.equalsIgnoreCase(M.destination))
				{
					M.setDelay(N.delayDuration);
				}
			});
		});

		return routeMergeList;		
	}

	public void insertDataIntoDatabase()
	{	
		List<RouteInformation> ril = new ArrayList<RouteInformation>();
		routeList.forEach(M -> {
			RouteInformation ri = new RouteInformation();

			ri.setDuration(M.getDistance());
			ri.setPlanet_Destination(M.getDestination());
			ri.setPlanet_Origin(M.getSource());
			ri.setTraffic_Delay(M.getDelay());

			ril.add(ri);
		});

		rir.saveAll(ril);
		rir.flush();

		List<PlanetInformation> pil = new ArrayList<PlanetInformation>();
		plaNameList.forEach(M -> {
			PlanetInformation pi = new PlanetInformation();
			
			pi.setPlanet_Id(M.getPlanetId());
			pi.setPlanet_Name(M.getName());
			
			pil.add(pi);
		});
		
		pir.saveAll(pil);
		pir.flush();
	}
}
