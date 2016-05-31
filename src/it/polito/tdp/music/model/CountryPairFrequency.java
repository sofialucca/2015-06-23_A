package it.polito.tdp.music.model;

public class CountryPairFrequency {
	
	private int countryId1 ;
	private int countryId2 ;
	private int frequency ;
	
	public CountryPairFrequency(int countryId1, int countryId2, int frequency) {
		super();
		this.countryId1 = countryId1;
		this.countryId2 = countryId2;
		this.frequency = frequency;
	}

	public int getCountryId1() {
		return countryId1;
	}

	public void setCountryId1(int countryId1) {
		this.countryId1 = countryId1;
	}

	public int getCountryId2() {
		return countryId2;
	}

	public void setCountryId2(int countryId2) {
		this.countryId2 = countryId2;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	

}
