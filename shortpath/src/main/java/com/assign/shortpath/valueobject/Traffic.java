package com.assign.shortpath.valueobject;

public class Traffic {

	public String source;
	public String destination;
	public double delayDuration;

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the delayDuration
	 */
	public double getDelayDuration() {
		return delayDuration;
	}
	/**
	 * @param delayDuration the delayDuration to set
	 */
	public void setDelayDuration(double delayDuration) {
		this.delayDuration = delayDuration;
	}

}
