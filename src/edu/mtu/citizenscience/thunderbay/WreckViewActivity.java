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
		String vesselName = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.vesselName");
		String vesselType = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.vesselType");
		String builder = intent.getStringExtra("edu.mtu.citizenscience.thunderbay.builder");
		int built = intent.getIntExtra("edu.mtu.citizenscience.thunderbay.built", 0);
		
		//TODO: find image of the ship by name
		ImageView imageView = (ImageView) findViewById(R.id.vesselimage_imageView);
		imageView.setImageResource(R.drawable.thuder_bay_logo);
		
		
		TextView textView = (TextView) findViewById(R.id.vesselname_textView);
		textView.setText(vesselName);
		
		textView = (TextView) findViewById(R.id.vesseltype_textView);
		textView.setText(vesselType);
		
		textView = (TextView) findViewById(R.id.builder_textView);
		textView.setText(builder);
		
		textView = (TextView) findViewById(R.id.built_textView);
		textView.setText(String.valueOf(built));
		
		//TODO: display the rest of the data
	}

}
