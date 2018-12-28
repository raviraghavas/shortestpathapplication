package com.assign.shortpath.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Route_Information")
public class RouteInformation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String Planet_Origin;
	private String Planet_Destination;
	private double Duration;
	private double Traffic_Delay;
	
	/**
	 * @return the planet_Origin
	 */
	public String getPlanet_Origin() {
		return Planet_Origin;
	}
	/**
	 * @param planet_Origin the planet_Origin to set
	 */
	public void setPlanet_Origin(String planet_Origin) {
		Planet_Origin = planet_Origin;
	}
	/**
	 * @return the planet_Destination
	 */
	public String getPlanet_Destination() {
		return Planet_Destination;
	}
	/**
	 * @param planet_Destination the planet_Destination to set
	 */
	public void setPlanet_Destination(String planet_Destination) {
		Planet_Destination = planet_Destination;
	}
	/**
	 * @return the duration
	 */
	public double getDuration() {
		return Duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(double duration) {
		Duration = duration;
	}
	/**
	 * @return the traffic_Delay
	 */
	public double getTraffic_Delay() {
		return Traffic_Delay;
	}
	/**
	 * @param traffic_Delay the traffic_Delay to set
	 */
	public void setTraffic_Delay(double traffic_Delay) {
		Traffic_Delay = traffic_Delay;
	}
	
}
