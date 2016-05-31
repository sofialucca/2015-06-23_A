package it.polito.tdp.music.db;

import it.polito.tdp.music.model.Artist;
import it.polito.tdp.music.model.ArtistFrequency;
import it.polito.tdp.music.model.City;
import it.polito.tdp.music.model.Country;
import it.polito.tdp.music.model.CountryPairFrequency;
import it.polito.tdp.music.model.Listening;
import it.polito.tdp.music.model.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MusicDAO {

	public List<Country> getAllCountries() {
		final String sql = "SELECT id, country FROM country";

		List<Country> countries = new LinkedList<Country>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				countries.add(new Country(res.getInt("id"), res.getString("country")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return countries;

	}

	public List<City> getAllCities() {
		final String sql = "SELECT id, city FROM city";

		List<City> cities = new LinkedList<City>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				cities.add(new City(res.getInt("id"), res.getString("city")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return cities;

	}

	public List<Artist> getAllArtists() {
		final String sql = "SELECT id, artist FROM artist";

		List<Artist> artists = new LinkedList<Artist>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				artists.add(new Artist(res.getInt("id"), res.getString("artist")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return artists;

	}

	public List<Track> getAllTracks() {
		final String sql = "SELECT id, track FROM track";

		List<Track> tracks = new LinkedList<Track>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				tracks.add(new Track(res.getInt("id"), res.getString("track")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return tracks;

	}

	public List<Listening> getAllListenings() {
		final String sql = "SELECT id, userid, month, weekday, longitude, latitude, countryid, cityid, artistid, trackid FROM listening";

		List<Listening> listenings = new LinkedList<Listening>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				listenings.add(new Listening(res.getLong("id"), res.getLong("userid"), res.getInt("month"),
						res.getInt("weekday"), res.getDouble("longitude"), res.getDouble("latitude"),
						res.getInt("countryid"), res.getInt("cityid"), res.getInt("artistid"), res.getInt("trackid")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return listenings;

	}

	public List<Integer> getValidMonths() {

		List<Integer> months = new ArrayList<>();

		String sql = "select distinct month from listening order by month";

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				months.add(rs.getInt("month"));
			}

			st.close();
			conn.close();

			return months;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<ArtistFrequency> getTopArtists(Month m) {

		// TODO complete method
		String sql = "select count(listening.id) as cnt , artistid, artist.artist " + "from listening, artist "
				+ "where month=? " + "and listening.artistid = artist.id " + "group by artistid " + "order by cnt desc "
				+ "limit 0, 20";

		List<ArtistFrequency> list = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, m.getValue());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				list.add(new ArtistFrequency(rs.getInt("artistid"), rs.getString("artist.artist"), rs.getInt("cnt")));
			}

			conn.close();

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		MusicDAO dao = new MusicDAO();

		List<Country> countries = dao.getAllCountries();
		// System.out.println(countries) ;

		List<City> cities = dao.getAllCities();
		// System.out.println(cities) ;

		List<Artist> artists = dao.getAllArtists();

		List<Track> tracks = dao.getAllTracks();

		List<Listening> listenings = dao.getAllListenings();

		System.out.format("Loaded %d countries, %d cities, %d artists, %d tracks, %d listenings\n", countries.size(),
				cities.size(), artists.size(), tracks.size(), listenings.size());
	}

	public List<Country> getCountriesForArtist(int artistId, Month m) {

		String sql = "select distinct country.id, country.country " + "from listening, country " + "where artistid = ? "
				+ "and listening.month = ? " + "and listening.countryid = country.id";

		List<Country> countries = new LinkedList<Country>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, artistId);
			st.setInt(2, m.getValue());

			ResultSet res = st.executeQuery();

			while (res.next()) {
				countries.add(new Country(res.getInt("id"), res.getString("country")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return countries;
	}

	public List<CountryPairFrequency> getArtistsForCountryPairs(Month m) {

		String sql = "select count(distinct l1.artistid) as cnt, c1.id as country1, c2.id as country2 "
				+ "from country c1, listening l1, listening l2, country c2 " + "where l1.countryid=c1.id "
				+ "and l2.countryid=c2.id " + "and l1.artistid=l2.artistid " + "and l1.month=? " + "and l2.month=? "
				+ "group by c1.id, c2.id";

		List<CountryPairFrequency> list = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, m.getValue());
			st.setInt(2, m.getValue());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				list.add(new CountryPairFrequency(
						rs.getInt("country1"), 
						rs.getInt("country2"), 
						rs.getInt("cnt")));
			}

			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
