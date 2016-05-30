package it.polito.tdp.music.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.music.db.MusicDAO;

public class Model {
	
	private List<Month> months ;
	
	public List<Month> getMonths() {
		if( this.months != null ) {
			return this.months ;
		} else {
			MusicDAO dao = new MusicDAO() ;
			
			this.months = new ArrayList<>() ;
			for(Integer m : dao.getValidMonths()) {
				this.months.add( Month.of(m) ) ;
			}
			return this.months ;
		}
	}

}
