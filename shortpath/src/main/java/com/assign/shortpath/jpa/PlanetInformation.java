package com.assign.shortpath.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Planet_Information")
public class PlanetInformation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String Planet_Name;
	private String Planet_Id;
	/**
	 * @return the planet_Name
	 */
	public String getPlanet_Name() {
		return Planet_Name;
	}
	/**
	 * @param planet_Name the planet_Name to set
	 */
	public void setPlanet_Name(String planet_Name) {
		Planet_Name = planet_Name;
	}
	/**
	 * @return the planet_Id
	 */
	public String getPlanet_Id() {
		return Planet_Id;
	}
	/**
	 * @param planet_Id the planet_Id to set
	 */
	public void setPlanet_Id(String planet_Id) {
		Planet_Id = planet_Id;
	}
	
}
