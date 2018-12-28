/**
 * This is from the djikstra simple implementation
 */
package com.assign.shortpath.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.assign.shortpath.valueobject.Edge;
import com.assign.shortpath.valueobject.Vertex;

@Component
public class ShortPathGraphPathUtil
{
    public void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }        
        
    }

    public List<Vertex> getShortestPathTo(Vertex target)
    {
    	List<Vertex> path = new ArrayList<Vertex>();
        
        double size = 0;
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
        {
        	size += vertex.minDistance;
            path.add(vertex);
        }
        Collections.reverse(path);
        System.out.println("Total distance is "+size);
        
        return path;
    }

    public double getShortestTimeTo(Vertex target)
    {    
        double size = 0;
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
        {
        	size += vertex.minDistance;
            
        }
        System.out.println("Total distance is "+size);
        
        return size;
    }

    
}