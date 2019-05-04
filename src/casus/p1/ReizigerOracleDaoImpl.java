package casus.p1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl implements ReizigerDao  {
	
	ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
	
	// Get all reizigers
	public List<Reiziger> findAll(){ return reizigers; }
	
	// Get all reizigers with this gbdatum
	public List<Reiziger> findByGBdatum(String gbdatum){
		
		ArrayList<Reiziger> reizigersGBdatum = new ArrayList<Reiziger>();
		
		for (Reiziger single_reiziger : reizigers) {
			
			if (single_reiziger.getGBdatum().equals(Date.valueOf(gbdatum))) {
				reizigersGBdatum.add(single_reiziger);
			}
			
		}
		
		return reizigersGBdatum;

	}
	
	// Saves new reiziger to list
	public Reiziger save(Reiziger reiziger) {
		
		reizigers.add(reiziger);
		return reiziger;
		
	}
	
	// Updates oldReiziger to newReiziger
	public Reiziger update(Reiziger reiziger) {
		
		int index = reizigers.indexOf(reiziger);
		
		reizigers.set(index, reiziger);
		
		return reiziger;
		
	}
	
	// Deletes reiziger from reizigers
	public boolean delete(Reiziger reiziger) {
		
		for (int i = 0; i < reizigers.size(); i++) {
			
			if (reizigers.get(i).equals(reiziger)) {
				reizigers.remove(i);
			}
			
		}

		return false;
		
	}

}
