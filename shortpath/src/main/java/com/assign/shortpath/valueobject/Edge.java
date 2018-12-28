package com.assign.shortpath.valueobject;

public class Edge
{
	public final Vertex target;
	public final double weight;
	public final double delay;
	
	public Edge(Vertex argTarget, double argWeight, double delayDuration)
	{ target = argTarget; weight = argWeight; delay = delayDuration;}
	
}
