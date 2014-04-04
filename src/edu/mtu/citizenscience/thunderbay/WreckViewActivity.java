package edu.mtu.citizenscience.thunderbay;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class WreckViewActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wreck_view);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		// image
		int imageID = intent.getIntExtra("edu.mtu.citizenscience.thunderbay.image", 0x7f020037);
		// ship info
		String vesselName = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.vesselName");
		String vesselType = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.vesselType");
		String hull = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.hull");
		double beam = intent.getDoubleExtra("edu.mtu.citizenscience.thunderbay.beam", 0);
		double length = intent.getDoubleExtra("edu.mtu.citizenscience.thunderbay.length", 0);
		String cargo = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.cargo");
		// biuld info
		String builder = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.builder");
		String buildPlace = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.buildPlace");
		int built = intent.getIntExtra("edu.mtu.citizenscience.thunderbay.built", 0);
		// lost info
		int lost = intent.getIntExtra("edu.mtu.citizenscience.thunderbay.lost", 0);
		String lossType = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.lossType");
		int livesLost = intent.getIntExtra("edu.mtu.citizenscience.thunderbay.livesLost", 0);
		String county = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.county");
		float latitude = intent.getFloatExtra("edu.mtu.citizenscience.thunderbay.latitude", 0);
		float longitude = intent.getFloatExtra("edu.mtu.citizenscience.thunderbay.longitude", 0);
		double depth = intent.getDoubleExtra("edu.mtu.citizenscience.thunderbay.depth", 0);
		// notes
		String notes = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.notes");
		
		//TODO: find image of the ship by name
		ImageView imageView = (ImageView) findViewById(R.id.vesselimage_imageView);
		imageView.setImageResource(imageID);
		
		// ship info
		TextView textView = (TextView) findViewById(R.id.vesselname_textView);
		textView.setText("Vessel Name: " + vesselName);
		
		textView = (TextView) findViewById(R.id.vesseltype_textView);
		textView.setText("Vessel Type: " + vesselType);
		
		textView = (TextView) findViewById(R.id.hull_textView);
		textView.setText("Hull Type: " + hull);
		
		textView = (TextView) findViewById(R.id.beam_textView);
		textView.setText("Beam Length: " + String.valueOf(beam));
		
		textView = (TextView) findViewById(R.id.length_textView);
		textView.setText("Vessel Length: " + String.valueOf(length));
		
		textView = (TextView) findViewById(R.id.cargo_textView);
		textView.setText("Cargo: " + cargo);
		
		// biuld info
		textView = (TextView) findViewById(R.id.builder_textView);
		textView.setText("Builder: " + builder);
		
		textView = (TextView) findViewById(R.id.buildplace_textView);
		textView.setText("Build Place: " + buildPlace);
		
		textView = (TextView) findViewById(R.id.built_textView);
		textView.setText("Year Built: " + String.valueOf(built));
		
		// lost info
		textView = (TextView) findViewById(R.id.lost_textView);
		textView.setText("Year Lost: " + String.valueOf(lost));
		
		textView = (TextView) findViewById(R.id.losstype_textView);
		textView.setText("Lost By: " + lossType);
		
		textView = (TextView) findViewById(R.id.liveslost_textView);
		textView.setText("Lives Lost: " + String.valueOf(livesLost));
		
		textView = (TextView) findViewById(R.id.county_textView);
		textView.setText("County of Wreck: " + county);
		
		textView = (TextView) findViewById(R.id.latitude_textView);
		textView.setText("Latitude: " + String.valueOf(latitude));
		
		textView = (TextView) findViewById(R.id.longitude_textView);
		textView.setText("Longitude: " + String.valueOf(longitude));
		
		textView = (TextView) findViewById(R.id.depth_textView);
		textView.setText("Depth of Wreck: " + String.valueOf(depth));
		
		// notes
		textView = (TextView) findViewById(R.id.notes_textView);
		textView.setText("Notes: " + notes);
		
	}

}
