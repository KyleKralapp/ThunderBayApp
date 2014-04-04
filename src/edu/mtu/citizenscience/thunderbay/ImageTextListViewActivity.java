package edu.mtu.citizenscience.thunderbay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		
		Collections.sort(rowItems, new LexicographicComparator());

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
		// image
		intent.putExtra("edu.mtu.citizenscience.thunderbay.image", rowItem.getImageId());
		// ship info
		intent.putExtra("edu.mtu.citizenscience.thunderbay.vesselName", ships.get(rowItem.getTitle()).getName());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.vesselType", ships.get(rowItem.getTitle()).getType());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.cargo", ships.get(rowItem.getTitle()).getCargo());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.hull", ships.get(rowItem.getTitle()).getHull());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.beam", ships.get(rowItem.getTitle()).getBeam());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.length", ships.get(rowItem.getTitle()).getLength());
		// biuld info
		intent.putExtra("edu.mtu.citizenscience.thunderbay.builder", ships.get(rowItem.getTitle()).getBuilder());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.buildPlace", ships.get(rowItem.getTitle()).getBuildPlace());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.built", ships.get(rowItem.getTitle()).getBuilt());
		// lost info
		intent.putExtra("edu.mtu.citizenscience.thunderbay.lost", ships.get(rowItem.getTitle()).getLost());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.lossType", ships.get(rowItem.getTitle()).getLossType());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.livesLost", ships.get(rowItem.getTitle()).getLivesLost());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.county", ships.get(rowItem.getTitle()).getCounty());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.latitude", ships.get(rowItem.getTitle()).getLatitude());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.longitude", ships.get(rowItem.getTitle()).getLongitude());
		intent.putExtra("edu.mtu.citizenscience.thunderbay.depth", ships.get(rowItem.getTitle()).getDepth());
		// notes
		intent.putExtra("edu.mtu.citizenscience.thunderbay.notes", ships.get(rowItem.getTitle()).getNotes());
		

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
			descriptions.add("Vessel Type: " + ships.get(ship).getType() + "\nBuilt By: " + 
					ships.get(ship).getBuilder() + "\nBuilt In: " + ships.get(ship).getBuilt());
			
			System.out.println();
			
			if (ship.startsWith("Albany")) {
				images.add(R.drawable.albany);
			} else if (ship.startsWith("Buckingham")) {
				images.add(R.drawable.alvin_buckingham);
			} else if (ship.startsWith("American")) {
				images.add(R.drawable.americian_union);
			} else if (ship.startsWith("Barge No. 012")) {
				images.add(R.drawable.barge_one);
			} else if (ship.startsWith("Bay City")) {
				images.add(R.drawable.baycity);
			} else if (ship.startsWith("Blanchard")) {
				images.add(R.drawable.blanchard_b_w);
			} else if (ship.startsWith("City of Alpena")) {
				images.add(R.drawable.city_of_alpena);
			} else if (ship.startsWith("Windiate")) {
				images.add(R.drawable.cornelia_b_windiate);
			} else if (ship.startsWith("Corsican")) {
				images.add(R.drawable.corsican2);
			} else if (ship.startsWith("Wilson")) {
				images.add(R.drawable.d_m_wilson);
			} else if (ship.startsWith("Hanna")) {
				images.add(R.drawable.d_r_hanna);
			} else if (ship.startsWith("Davidson, James")) {
				images.add(R.drawable.davidson_james);
			} else if (ship.startsWith("Defiance")) {
				images.add(R.drawable.defiance);
			} else if (ship.startsWith("Detroit")) {
				images.add(R.drawable.detroit);
			} else if (ship.startsWith("Dump")) {
				images.add(R.drawable.dump_scow);
			} else if (ship.startsWith("Duncan")) {
				images.add(R.drawable.duncan_city);
			} else if (ship.startsWith("Allen")) {
				images.add(R.drawable.e_b_allen);
			} else if (ship.startsWith("Empire State")) {
				images.add(R.drawable.empire_state);
			} else if (ship.startsWith("Egyptian")) {
				images.add(R.drawable.eqypt);
			} else if (ship.equals("Barney, F.T.")) {
				images.add(R.drawable.f_t_barney);
			} else if (ship.startsWith("Florida")) {
				images.add(R.drawable.florida);
			} else if (ship.startsWith("Franklin")) {
				images.add(R.drawable.franklin_b);
			} else if (ship.startsWith("Franz")) {
				images.add(R.drawable.franz);
			} else if (ship.startsWith("Galena")) {
				images.add(R.drawable.galena);
			} else if (ship.startsWith("Grecian")) {
				images.add(R.drawable.grecian);
			} else if (ship.startsWith("Haltiner's")) {
				images.add(R.drawable.haltiner_barge);
			} else if (ship.startsWith("Bissell")) {
				images.add(R.drawable.harvey_bissell);
			} else if (ship.startsWith("Murray Company")) {
				images.add(R.drawable.heart_failure);
			} else if (ship.startsWith("Scott")) {
				images.add(R.drawable.i_m_scott);
			} else if (ship.startsWith("Ishpeming")) {
				images.add(R.drawable.ishpeming);
			} else if (ship.startsWith("Hall")) {
				images.add(R.drawable.james_h_hall);
			} else if (ship.startsWith("Audubon")) {
				images.add(R.drawable.john_j_audubon);
			} else if (ship.startsWith("Shaw")) {
				images.add(R.drawable.john_l_shaw);
			} else if (ship.startsWith("Johnson")) {
				images.add(R.drawable.johnson_t_j);
			} else if (ship.startsWith("Fay")) {
				images.add(R.drawable.joseph_s_fay);
			} else if (ship.startsWith("Spangler")) {
				images.add(R.drawable.kyle_spangler);
			} else if (ship.startsWith("Van Valkenburg")) {
				images.add(R.drawable.l_v_vanvalkenburg);
			} else if (ship.startsWith("Loretta")) {
				images.add(R.drawable.loretta);
			} else if (ship.startsWith("Marine City")) {
				images.add(R.drawable.marinecity);
			} else if (ship.startsWith("Messenger")) {
				images.add(R.drawable.messenger);
			} else if (ship.startsWith("Monohansett")) {
				images.add(R.drawable.monohansett);
			} else if (ship.startsWith("Monrovia")) {
				images.add(R.drawable.monrovia);
			} else if (ship.startsWith("Montana")) {
				images.add(R.drawable.montana);
			} else if (ship.startsWith("New Orleans")) {
				images.add(R.drawable.neworleans);
			} else if (ship.startsWith("Nordmeer")) {
				images.add(R.drawable.nordmeer);
			} else if (ship.startsWith("Norman")) {
				images.add(R.drawable.norman);
			} else if (ship.startsWith("Parks")) {
				images.add(R.drawable.o_e_parks);
			} else if (ship.startsWith("Ogarita")) {
				images.add(R.drawable.ogarita);
			} else if (ship.startsWith("Flint")) {
				images.add(R.drawable.oscar_t_flint);
			} else if (ship.startsWith("Pewabic")) {
				images.add(R.drawable.pewabic);
			} else if (ship.startsWith("Portland")) {
				images.add(R.drawable.portland);
			} else if (ship.startsWith("Portsmouth")) {
				images.add(R.drawable.portsmouth);
			} else if (ship.startsWith("Reindeer")) {
				images.add(R.drawable.reindeer);
			} else if (ship.startsWith("Rend")) {
				images.add(R.drawable.rend_w_p);
			} else if (ship.startsWith("Shamrock")) {
				images.add(R.drawable.shamrock);
			} else if (ship.startsWith("Spud")) {
				images.add(R.drawable.spud);
			} else if (ship.startsWith("Stevens")) {
				images.add(R.drawable.stevens_william_h);
			} else if (ship.startsWith("Typo")) {
				images.add(R.drawable.typo);
			} else if (ship.startsWith("Viator")) {
				images.add(R.drawable.viator);
			} else if (ship.startsWith("Mason")) {
				images.add(R.drawable.w_g_mason);
			} else if (ship.startsWith("Gilbert")) {
				images.add(R.drawable.w_h_gilbert);
			} else if (ship.startsWith("Thew")) {
				images.add(R.drawable.w_p_thew);
			} else if (ship.startsWith("Warner")) {
				images.add(R.drawable.warner_j_f);
			} else if (ship.startsWith("Maxwell")) {
				images.add(R.drawable.william_maxwell);
			} else {
				images.add(R.drawable.thuder_bay_logo);
			}
			
		}
	}
}

class LexicographicComparator implements Comparator<RowItem> {
    @Override
    public int compare(RowItem a, RowItem b) {
        return a.getTitle().compareToIgnoreCase(b.getTitle());
    }
}
