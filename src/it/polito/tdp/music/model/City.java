package it.polito.tdp.music.model;

public class City {
	
	private int id ;
	private String country ;
	
	public City(int id, String country) {
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
		return "City[" + id + ", \"" + country + "\"]";
	}
	
}
