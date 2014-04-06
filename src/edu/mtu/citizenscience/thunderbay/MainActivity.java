package edu.mtu.citizenscience.thunderbay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	// Declare Tab Variable
	ActionBar.Tab helpTab, mapTab, searchTab;
	Fragment helpFragmentTab = new HelpFragmentTab();
	Fragment mapFragmentTab = new MapFragmentTab();
	Fragment searchFragmentTab = new SearchFragmentTab();

	private ThunderBayDataSource datasource;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		datasource = new ThunderBayDataSource(this);
	    try {
			datasource.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ActionBar actionBar = getActionBar();

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
	//TODO
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

				System.out.println("trying to add " + RowData[0] + " with size " + RowData.length);

				if(readCount>1){
					//myBay.addShipWreck(BuildWreck(RowData));
					BuildWreck(RowData);
				}

				System.out.println(RowData[0] + " was added.");
			}
		}
		catch (IOException ex) {
			// handle exception
			System.out.println(ex.getMessage());
			System.out.println(ex.getCause());
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

		String name = rowData[0];
		String type = rowData[1];
		String hull = rowData[2];

		//Get yearBuilt
		int yearBuilt;
		try {
			yearBuilt = Integer.valueOf(rowData[3]);
		}  catch(Exception E){ //Not an integer (unknown)
			//Exception stuff here
			System.out.println("THE YEAR BUILT IS UNKNOWN");
			yearBuilt = -1;
		} 

		//Get yearLost
		int yearLost;
		try{
			yearLost = Integer.valueOf(rowData[4]);
		}  catch(Exception E){ //Not an integer (unknown)
			//Exception stuff here
			System.out.println("THE YEAR LOST IS UNKNOWN");
			yearLost = -1;
		}

		//Get Builder
		String builder = rowData[5];
		//Get Build Place
		String buildPlace = rowData[6];
		//Get length
		double length;
		try {
			length = Double.valueOf(rowData[7]);
		} catch(Exception E){ //Not a Double (unknown)
			//Exception stuff here
			System.out.println("THE LENGTH IS UNKNOWN");
			length = -1;
		}

		//Get Beam
		double beam;
		try {
			beam = Double.valueOf(rowData[8]);
		} catch(Exception E){ //Not a Double (unknown)
			//Exception stuff here
			System.out.println("THE BEAM IS UNKNOWN");
			beam = -1;
		}

		//Set Loss Type
		String lossType = rowData[9];
		//Set Cargo
		String cargo = rowData[10];
		
		//Set Lives Lost
		int livesLost;
		try {
			livesLost = Integer.valueOf(rowData[11]);
		} catch(Exception E){ //Not an integer (unknown)
			//Exception stuff here
			System.out.println("THE LIVES LOST IS UNKNOWN");
			livesLost = -1;
		}

		//Set County
		String county = rowData[12];

		/**
		 * Need to parse longitutde and latitude
		 * prolly gonna need to use regex
		 * 
		 */
		float latitude = Float.MIN_VALUE; //FAKE DATA!!!!
		float longitude = Float.MAX_VALUE;//FAKE DATA!!!!
		
		//Set depth
		int depth;
		try {
			depth = Integer.valueOf(rowData[15]);
		} catch(Exception E){ //Not an integer (unknown)
			//Exception stuff here
			System.out.println("THE DEPTH IS UNKNOWN");

			depth = -1;
		}
		
		/**
		 * Check to see if the record contains notes
		 */
		String notes;
		if(rowData.length==17){
			notes = rowData[16];
		}
		else{
			notes = "";
		}

		return datasource.createWreck(name, type, hull, yearBuilt, yearLost, builder, buildPlace, length, beam, lossType, cargo, livesLost, county, latitude, longitude, depth, notes);
	}
}

