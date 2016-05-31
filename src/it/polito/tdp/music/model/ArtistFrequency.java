package it.polito.tdp.music.model;

public class ArtistFrequency {
	
	private int artistId ;
	private String artist ; // name
	private int freqency ;
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getFreqency() {
		return freqency;
	}
	public void setFreqency(int freqency) {
		this.freqency = freqency;
	}
	
	public ArtistFrequency(int artistId, String artist, int freqency) {
		super();
		this.artistId = artistId;
		this.artist = artist;
		this.freqency = freqency;
	}
	
	

}
