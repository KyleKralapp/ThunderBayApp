package edu.mtu.citizenscience.thunderbay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author jdploe
 *
 */
public class ThunderBay extends Activity
{
	private HashMap<String, ShipWreck> shipWrecks = new HashMap<String, ShipWreck>();
	
	
	


	public HashMap<String, ShipWreck> getShipWrecks() {
		return shipWrecks;
	}

	/**
	 * Loads data from tsv file and calls BuildWreck with array of data per ship
	 * @author jdploe
	 * @return boolean -- true: successful load.... false: unsuccessful load
	 *
	 */
	public boolean loadData(){
		InputStream tsvFile;
		try {
			tsvFile = getAssets().open("project5wrecks.tsv");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(tsvFile));
		try {
			String line;
			int readCount = 0;
			while ((line = reader.readLine()) != null) {
				String[] RowData = line.split("\\t");
				readCount++;
				//ignore first row of headings
				if(readCount>1){
					BuildWreck(RowData);
				}

			}
		}
		catch (IOException ex) {
			// handle exception
			return false;
		}
		finally {
			try {
				tsvFile.close();
			}
			catch (IOException e) {
				// handle exception
				return false;
			}
		}
		return true;

	}

	/**
	 * Loads data from tsv file and calls BuildWreck with array of data per ship
	 * @author jdploe
	 * @return 
	 * @return boolean -- true: successful load.... false: unsuccessful load
	 *
	 */
	private ShipWreck BuildWreck(String[] rowData) {
		ShipWreck myWreck = new ShipWreck();
		
		myWreck.setName(rowData[0]);
		myWreck.setType(rowData[1]);
		myWreck.setHull(rowData[2]);
		
		int yearBuilt = Integer.valueOf(rowData[3]);
		myWreck.setBuilt(yearBuilt);
		
		System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!" + yearBuilt);
		
		int yearLost = Integer.valueOf(rowData[4]);
		myWreck.setBuilt(yearLost);
		
		myWreck.setBuilder(rowData[5]);
		myWreck.setBuildPlace(rowData[6]);
		
		double length = Double.valueOf(rowData[7]);
		myWreck.setLength(length);
		
		double beam = Double.valueOf(rowData[8]);
		myWreck.setLength(beam);
		
		myWreck.setLossType(rowData[9]);
		myWreck.setCargo(rowData[10]);
		
		int livesLost = Integer.valueOf(rowData[11]);
		myWreck.setLivesLost(livesLost);
		
		myWreck.setCounty(rowData[12]);
		
		
		/**
		 * Need to parse longitutde and latitude
		 * prolly gonna need to use regex
		 * 
		 */
		
		myWreck.setLatitude(Float.MIN_VALUE); //FAKE DATA!!!!
		myWreck.setLongitude(Float.MAX_VALUE);//FAKE DATA!!!!
		
		int depth = Integer.valueOf(rowData[15]);
		myWreck.setDepth(depth);
		
		
		/**
		 * Check to see if the record contains 
		 * notes
		 */
		if(rowData.length==17){
			myWreck.setNotes(rowData[16]);
		}
		
		return shipWrecks.put(myWreck.getName(), myWreck);
	}

public void testSearch(int depth) {
		
		Iterator it = shipWrecks.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        ShipWreck myWreck = (ShipWreck) pairs.getValue();
	        
	        if(myWreck.getDepth() < depth){
	        	System.out.println(pairs.getKey().toString() +"--->" + myWreck.getDepth());
	        }
	        
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	}


}
