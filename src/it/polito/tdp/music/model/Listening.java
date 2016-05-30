package it.polito.tdp.music.model;

public class Listening {
	
	private long id ;
	private long userId ;
	private int month ;
	private int weekday ;
	private double longitude ;
	private double latitude ;
	private int countryId ;
	private int cityId ;
	private int artistId ;
	private	int trackId ;
	
	
	public Listening(long id, long userId, int month, int weekday,
			double longitude, double latitude, int countryId, int cityId,
			int artistId, int trackId) {
		super();
		this.id = id;
		this.userId = userId;
		this.month = month;
		this.weekday = weekday;
		this.longitude = longitude;
		this.latitude = latitude;
		this.countryId = countryId;
		this.cityId = cityId;
		this.artistId = artistId;
		this.trackId = trackId;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getWeekday() {
		return weekday;
	}


	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public int getCountryId() {
		return countryId;
	}


	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}


	public int getCityId() {
		return cityId;
	}


	public void setCityId(int cityId) {
		this.cityId = cityId;
	}


	public int getArtistId() {
		return artistId;
	}


	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}


	public int getTrackId() {
		return trackId;
	}


	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	
	

}
