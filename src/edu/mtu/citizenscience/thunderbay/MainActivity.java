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
					myBay.BuildWreck(RowData);
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
	
}
