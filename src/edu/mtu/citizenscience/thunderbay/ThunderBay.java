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
public class ThunderBay
{
	private HashMap<String, ShipWreck> shipWrecks = new HashMap<String, ShipWreck>();
	
	
	
	public ShipWreck addShipWreck(ShipWreck theWreck){
		return shipWrecks.put(theWreck.getName(), theWreck);
	}

	public HashMap<String, ShipWreck> getShipWrecks() {
		return shipWrecks;
	}

	

	/**
	 * Loads data from tsv file and calls BuildWreck with array of data per ship
	 * @author jdploe
	 * @return 
	 * @return boolean -- true: successful load.... false: unsuccessful load
	 *
	 */
	

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
