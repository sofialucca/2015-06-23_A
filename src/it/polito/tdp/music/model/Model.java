package it.polito.tdp.music.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.music.db.MusicDAO;

public class Model {

	private List<Month> months;

	private List<ArtistFrequency> topArtists;
	private Month topArtistsMonth;

	private List<Country> topCountries;
	// le nazioni in cui sono stati ascoltati i topArtist

	private SimpleWeightedGraph<Country, DefaultWeightedEdge> graph;

	public List<Month> getMonths() {
		if (this.months != null) {
			return this.months;
		} else {
			MusicDAO dao = new MusicDAO();

			this.months = new ArrayList<>();
			for (Integer m : dao.getValidMonths()) {
				this.months.add(Month.of(m));
			}
			return this.months;
		}
	}

	public List<ArtistFrequency> getTopArtists(Month m) {
		if (this.topArtists != null && m.equals(this.topArtistsMonth)) {
			return this.topArtists;
		} else {
			MusicDAO dao = new MusicDAO();
			this.topArtists = dao.getTopArtists(m);
			this.topArtistsMonth = m;
			return this.topArtists;
		}
	}

	/**
	 * Calcola l'elenco di tutte le nazioni {@link Country} in cui sia stato
	 * ascoltato almeno una volta un artista presente nei {@code topArtist}, nel
	 * mese selezionato.
	 * 
	 * @param m
	 * @return
	 */
	public List<Country> getTopCountries(Month m) {

		List<Country> countries = new ArrayList<>();

		MusicDAO dao = new MusicDAO();

		for (ArtistFrequency af : getTopArtists(m)) {

			List<Country> partial = dao.getCountriesForArtist(af.getArtistId(), m);
			// NO countries.addAll(partial) ;
			//System.out.format("Artist %s -> found %d countries\n", af.getArtist(), partial.size());

			for (Country c : partial) {
				if (!countries.contains(c))
					countries.add(c);
			}
		}

		//System.out.format("TOTAL: %d countries\n", countries.size());
		///System.out.println(countries);

		this.topCountries = countries;
		return countries;

	}

	public void creaGrafo(Month m) {

		graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		Graphs.addAllVertices(graph, this.topCountries);

		MusicDAO dao = new MusicDAO();
		List<CountryPairFrequency> freq = dao.getArtistsForCountryPairs(m);

		for (Country c1 : graph.vertexSet()) {
			for (Country c2 : graph.vertexSet()) {
				if (c1.getId() < c2.getId()) {

					int peso = calcolaPeso(c1, c2, freq);

					if (peso != 0) {
						Graphs.addEdge(graph, c1, c2, peso);
					}
				}
			}
		}
		
		/*System.out.format("Grafo creato: %d vertici, %d archi\n",
				graph.vertexSet().size(), graph.edgeSet().size());*/

	}
	
	public int maxDistanzaCountry() {
		int max = 0 ;
		
		for( DefaultWeightedEdge e : graph.edgeSet() ) {
			if ( graph.getEdgeWeight(e) > max )
				max = (int)graph.getEdgeWeight(e) ;
		}
		return max ;
	}

	private int calcolaPeso(Country c1, Country c2, List<CountryPairFrequency> freq) {
				
		for( CountryPairFrequency cpf : freq ) {
			if( c1.getId()==cpf.getCountryId1() &&
					c2.getId()==cpf.getCountryId2() )
				return cpf.getFrequency() ;
		}
		
		return 0 ;
	}

}
