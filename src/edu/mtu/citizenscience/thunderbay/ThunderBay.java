package edu.mtu.citizenscience.thunderbay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Application;

/**
 * @author jdploe
 *
 */
public class ThunderBay {

	private static HashMap<String, ShipWreck> shipWrecks = new HashMap<String, ShipWreck>();

	public static void addShipWreck(ShipWreck theWreck){
		shipWrecks.put(theWreck.getName(), theWreck);
	}

	public static HashMap<String, ShipWreck> getShipWrecks() {
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
