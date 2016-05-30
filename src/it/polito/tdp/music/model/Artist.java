package it.polito.tdp.music.model;

public class Artist {
	
	private int id ;
	private String artist ;
	
	public Artist(int id, String artist) {
		super();
		this.id = id;
		this.artist = artist;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
