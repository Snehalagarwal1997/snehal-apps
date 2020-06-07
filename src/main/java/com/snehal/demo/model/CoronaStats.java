package com.snehal.demo.model;

public class CoronaStats {
	
	private String country;
	private String state;
	private int latestTotalCases;
	private int diffFromPrevDay;
	
	public CoronaStats() {
		
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	

	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}

	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}

	public CoronaStats(String country, String state, int latestTotalCases,int diffFromPrevDay) {
		this.country = country;
		this.state = state;
		this.latestTotalCases = latestTotalCases;
		this.diffFromPrevDay=diffFromPrevDay;
	}

	@Override
	public String toString() {
		return "CoronaStats [country=" + country + ", state=" + state + ", latestTotalCases=" + latestTotalCases
				+ ", diffFromPrevDay=" + diffFromPrevDay + "]";
	}

	
	
	

}
