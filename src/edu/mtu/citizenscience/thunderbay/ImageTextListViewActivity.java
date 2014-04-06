package edu.mtu.citizenscience.thunderbay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.mtu.citizenscience.thunderbay.R;
import edu.mtu.citizenscience.thunderbay.CustomListViewAdapter;
import edu.mtu.citizenscience.thunderbay.RowItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ImageTextListViewActivity extends Activity implements OnItemClickListener {

	public ArrayList<String> titles;
	public ArrayList<String> descriptions;
	public ArrayList<Integer> images;

	ListView listView;
	List<RowItem> rowItems;

	// TODO
	//ThunderBay myBay = new ThunderBay();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);

		rowItems = new ArrayList<RowItem>();

		loadDataShip();
		
		for (int i = 0; i < titles.size(); i++) {
			System.out.println("creating new RowItem");
			RowItem item = new RowItem(images.get(i), titles.get(i), descriptions.get(i));
			rowItems.add(item);
		}

		listView = (ListView) findViewById(R.id.list);
		CustomListViewAdapter adapter = new CustomListViewAdapter(this,
				R.layout.list_item, rowItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast toast = Toast.makeText(getApplicationContext(),
				"Item " + (position + 1) + ": " + rowItems.get(position),
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
	}

	//TODO
//	public boolean loadData(){
//
//		InputStream tsvFile;
//		try {
//			tsvFile = getAssets().open("project5wrecks.tsv");
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//		BufferedReader reader = new BufferedReader(new InputStreamReader(tsvFile));
//		try {
//			String line;
//			int readCount = 0;
//			while ((line = reader.readLine()) != null) {
//				String[] RowData = line.split("\\t");
//				readCount++;
//				//ignore first row of headings
//				
//				System.out.println("trying to add " + RowData[0] + " with size " + RowData.length);
//				
//				if(readCount>1){
//					myBay.addShipWreck(BuildWreck(RowData));
//				}
//				
//				System.out.println(RowData[0] + " was added.");
//			}
//		}
//		catch (IOException ex) {
//			// handle exception
//			System.out.println(ex.getMessage());
//			System.out.println(ex.getCause());
//			return false;
//		}
//		finally {
//			try {
//				tsvFile.close();
//			}
//			catch (IOException e) {
//				// handle exception
//				return false;
//			}
//		}
//		return true;
//
//	}
//
//	private ShipWreck BuildWreck(String[] rowData) {
//		ShipWreck myWreck = new ShipWreck();
//
//		myWreck.setName(rowData[0]);
//		
//		System.out.println(rowData[0]);
//		
//		myWreck.setType(rowData[1]);
//		
//		System.out.println(rowData[1]);
//		
//		myWreck.setHull(rowData[2]);
//		
//		System.out.println(rowData[2]);
//
//		//Get yearBuilt
//		int yearBuilt = 0;
//		try{
//			yearBuilt = Integer.valueOf(rowData[3]);
//		}
//		//Not an integer (unknown)
//		catch(Exception E){
//			//Exception stuff here
//			System.out.println("THE YEAR BUILT IS UNKNOWN");
//			yearBuilt = -1;
//		}
//		finally{
//			myWreck.setBuilt(yearBuilt);
//			System.out.println(yearBuilt);
//		}
//
//		//Get yearLost
//		int yearLost = 0;
//		try{
//			yearLost = Integer.valueOf(rowData[4]);
//		}
//		//Not an integer (unknown)
//		catch(Exception E){
//			//Exception stuff here
//			System.out.println("THE YEAR LOST IS UNKNOWN");
//			yearLost = -1;
//		}
//		finally{
//			myWreck.setLost(yearLost);
//			
//			System.out.println(yearLost);
//		}
//
//		//Get Builder
//		myWreck.setBuilder(rowData[5]);
//		System.out.println(rowData[5]);
//
//		//Get Build Place
//		myWreck.setBuildPlace(rowData[6]);
//		System.out.println(rowData[6]);
//
//		//Get length
//		double length = 0;
//		try{
//			length = Double.valueOf(rowData[7]);
//		}
//		//Not a Double (unknown)
//		catch(Exception E){
//			//Exception stuff here
//			System.out.println("THE LENGTH IS UNKNOWN");
//			length = -1;
//		}
//		finally{
//			myWreck.setLength(length);
//			System.out.println(length);
//		}
//
//		//Get Beam
//		double beam = 0;
//		try{
//			beam = Double.valueOf(rowData[8]);
//		}
//		//Not a Double (unknown)
//		catch(Exception E){
//			//Exception stuff here
//			System.out.println("THE BEAM IS UNKNOWN");
//			beam = -1;
//		}
//		finally{
//			myWreck.setBeam(beam);
//			System.out.println(beam);
//		}
//
//		//Set Loss Type
//		myWreck.setLossType(rowData[9]);
//		System.out.println(rowData[9]);
//
//		//Set Cargo
//		myWreck.setCargo(rowData[10]);
//		System.out.println("cargo " + rowData[10]);
//
//		//Set Lives Lost
//		int livesLost = 0;
//		try{
//			livesLost = Integer.valueOf(rowData[11]);
//		}
//		//Not an integer (unknown)
//		catch(Exception E){
//			//Exception stuff here
//			System.out.println("THE LIVES LOST IS UNKNOWN");
//			livesLost = -1;
//		}
//		finally{
//			myWreck.setLivesLost(livesLost);
//			System.out.println("lives lost " + livesLost);
//		}
//
//
//		//Set County
//		myWreck.setCounty(rowData[12]);
//		System.out.println("County " + rowData[12]);
//
//		/**
//		 * Need to parse longitutde and latitude
//		 * prolly gonna need to use regex
//		 * 
//		 */
//
//		myWreck.setLatitude(Float.MIN_VALUE); //FAKE DATA!!!!
//		myWreck.setLongitude(Float.MAX_VALUE);//FAKE DATA!!!!
//		//Set depth
//		int depth = 0;
//		try{
//			depth = Integer.valueOf(rowData[15]);
//		}
//		//Not an integer (unknown)
//		catch(Exception E){
//			//Exception stuff here
//			System.out.println("THE DEPTH IS UNKNOWN");
//
//			depth = -1;
//		}
//		finally{
//			myWreck.setDepth(depth);
//			System.out.println("depth " + depth);
//		}
//		/**
//		 * Check to see if the record contains notes
//		 */
//		if(rowData.length==17){
//			myWreck.setNotes(rowData[16]);
//			System.out.println("Notes " + rowData[16]);
//		} else {
//			System.out.println("no notes");
//		}
//		
//		return myWreck;
//	}
	
	private void loadDataShip(){

		titles = new ArrayList<String>();
		descriptions = new ArrayList<String>();
		images = new ArrayList<Integer>();
		
		Map<String, ShipWreck> ships = ThunderBay.getShipWrecks();
		
		for (String ship : ships.keySet()) {

			titles.add(ship);
			descriptions.add(ships.get(ship).getType());
			images.add(R.drawable.thuder_bay_logo);
		}
		
//		for (String ship : myBay.getShipWrecks().keySet()) {
//
//			titles.add(ship);
//			descriptions.add(myBay.getShipWrecks().get(ship).getType());
//			images.add(R.drawable.thuder_bay_logo);
//		}
	}
}
