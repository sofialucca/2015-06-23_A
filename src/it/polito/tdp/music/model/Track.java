package it.polito.tdp.music.model;

public class Track {
	
	private int id ;
	private String track ;
	
	public Track(int id, String track) {
		super();
		this.id = id;
		this.track = track;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return track;
	}
	public void setArtist(String artist) {
		this.track = artist;
	}
	
}
