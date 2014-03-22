package edu.mtu.citizenscience.thunderbay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

public class MainActivity extends Activity {
	// Declare Tab Variable
	ActionBar.Tab helpTab, mapTab, searchTab;
	Fragment helpFragmentTab = new HelpFragmentTab();
	Fragment mapFragmentTab = new MapFragmentTab();
	Fragment searchFragmentTab = new SearchFragmentTab();

	ThunderBay myBay = new ThunderBay();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();

		ThunderBay myBay = new ThunderBay();

		loadData();

		//Generic test to check if data is entered in correctly.
		//myBay.testSearch(10);

		// Hide Actionbar Icon
		//actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		//actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		helpTab = actionBar.newTab().setText("Help");
		mapTab = actionBar.newTab().setText("Map");
		searchTab = actionBar.newTab().setText("Search");

		// Set Tab Listeners
		helpTab.setTabListener(new TabListener(helpFragmentTab));
		mapTab.setTabListener(new TabListener(mapFragmentTab));
		searchTab.setTabListener(new TabListener(searchFragmentTab));

		// Add tabs to actionbar
		actionBar.addTab(helpTab, false);
		actionBar.addTab(mapTab, true);
		actionBar.addTab(searchTab, false);

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
	
	private ShipWreck BuildWreck(String[] rowData) {
		ShipWreck myWreck = new ShipWreck();


		myWreck.setName(rowData[0]);
		myWreck.setType(rowData[1]);
		myWreck.setHull(rowData[2]);


		//Get yearBuilt
		int yearBuilt = 0;
		try{
			yearBuilt = Integer.valueOf(rowData[3]);
		}
		//Not an integer (unknown)
		catch(Exception E){
			//Exception stuff here
			System.out.println("THE YEAR BUILT IS UNKNOWN");
			yearBuilt = -1;
		}
		finally{
			myWreck.setBuilt(yearBuilt);
		}


		//Get yearLost
		int yearLost = 0;
		try{
			yearLost = Integer.valueOf(rowData[4]);
		}
		//Not an integer (unknown)
		catch(Exception E){
			//Exception stuff here
			System.out.println("THE YEAR LOST IS UNKNOWN");
			yearLost = -1;
		}
		finally{
			myWreck.setLost(yearLost);
		}




		//Get Builder
		myWreck.setBuilder(rowData[5]);

		//Get Build Place
		myWreck.setBuildPlace(rowData[6]);



		//Get length
		double length = 0;
		try{
			length = Double.valueOf(rowData[7]);
		}
		//Not a Double (unknown)
		catch(Exception E){
			//Exception stuff here
			System.out.println("THE LENGTH IS UNKNOWN");
			length = -1;
		}
		finally{
			myWreck.setLength(length);
		}


		//Get Beam
		double beam = 0;
		try{
			beam = Double.valueOf(rowData[8]);
		}
		//Not a Double (unknown)
		catch(Exception E){
			//Exception stuff here
			System.out.println("THE BEAM IS UNKNOWN");
			beam = -1;
		}
		finally{
			myWreck.setBeam(beam);
		}

		
		
		
		//Set Loss Type
		myWreck.setLossType(rowData[9]);

		//Set Cargo
		myWreck.setCargo(rowData[10]);


		//Set Lives Lost
		int livesLost = 0;
		try{
			livesLost = Integer.valueOf(rowData[11]);
		}
		//Not an integer (unknown)
		catch(Exception E){
			//Exception stuff here
			System.out.println("THE LIVES LOST IS UNKNOWN");
			livesLost = -1;
		}
		finally{
			myWreck.setLivesLost(livesLost);
		}


		//Set County
		myWreck.setCounty(rowData[12]);


		/**
		 * Need to parse longitutde and latitude
		 * prolly gonna need to use regex
		 * 
		 */

		myWreck.setLatitude(Float.MIN_VALUE); //FAKE DATA!!!!
		myWreck.setLongitude(Float.MAX_VALUE);//FAKE DATA!!!!




		//Set depth
		int depth = 0;
		try{
			depth = Integer.valueOf(rowData[15]);
		}
		//Not an integer (unknown)
		catch(Exception E){
			//Exception stuff here
			System.out.println("THE DEPTH IS UNKNOWN");
			
			depth = -1;
		}
		finally{
			myWreck.setDepth(depth);
		}

		


		/**
		 * Check to see if the record contains 
		 * notes
		 */
				if(rowData.length==17){
					myWreck.setNotes(rowData[16]);
				}


		return myBay.addShipWreck(myWreck);
	}
}

