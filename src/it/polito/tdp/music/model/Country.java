package it.polito.tdp.music.model;

public class Country {
	
	private int id ;
	private String country ;
	
	public Country(int id, String country) {
		super();
		this.id = id;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return country;
	}
	public void setArtist(String artist) {
		this.country = artist;
	}

	@Override
	public String toString() {
		return "Country[" + id + ", \"" + country + "\"]";
	}
	
	
	
}
