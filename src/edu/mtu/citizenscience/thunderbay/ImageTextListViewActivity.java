package edu.mtu.citizenscience.thunderbay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

public class ImageTextListViewActivity extends Activity implements OnItemClickListener {

	public ArrayList<String> titles;
	public ArrayList<String> descriptions;
	public ArrayList<Integer> images;

	ListView listView;
	List<RowItem> rowItems;
	
	Map<String, ShipWreck> ships;

	// TODO
	//ThunderBay myBay = new ThunderBay();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast toast = Toast.makeText(getApplicationContext(),
//						"Item " + (position + 1) + ": " + rowItems.get(position),
//						Toast.LENGTH_SHORT);
//				toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//				toast.show();

		onClick(view, rowItems.get(position));

	}

	public void onClick(View v, RowItem rowItem) {
		Intent intent = new Intent(this, WreckViewActivity.class);
		intent.putExtra("edu.mtu.citizenscience.thunderbay.vesselName", ships.get(rowItem.getTitle()).getName());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.vesselType", ships.get(rowItem.getTitle()).getType());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.builder", ships.get(rowItem.getTitle()).getBuilder());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.built", ships.get(rowItem.getTitle()).getBuilt());
		//TODO: send the rest of the data

		startActivity(intent);
	}

	private void loadDataShip() {

		titles = new ArrayList<String>();
		descriptions = new ArrayList<String>();
		images = new ArrayList<Integer>();

		// get extra passed in
		Intent intent = getIntent();
		String vesselType = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.vesselType");
		String hullType = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.hullType");
		String built = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.built");
		String lost = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.lost");
		String builder = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.builder");
		String buildPlace = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.buildPlace");
		String county = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.county");
		String depth = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.depth");

		System.out.println("received " + vesselType);
		System.out.println("received " + hullType);
		System.out.println("received " + built);
		System.out.println("received " + lost);
		System.out.println("received " + builder);
		System.out.println("received " + buildPlace);
		System.out.println("received " + county);
		System.out.println("received " + depth);

		// create sql
		ships = ThunderBay.getShipWrecks();
		Object[] toView = ships.keySet().toArray(); 
		// TODO: filter
		
		for (String ship : ships.keySet()) {
			titles.add(ship);
			descriptions.add(ships.get(ship).getType());
			images.add(R.drawable.thuder_bay_logo);
		}
	}
}
