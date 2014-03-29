package edu.mtu.citizenscience.thunderbay;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
 
public class SearchFragmentTab extends Fragment implements OnItemSelectedListener {
	
	String vesselType;
	String hullType;
	String built;
	String lost;
	String builder;
	String buildPlace;
	String county;
	String depth;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.searchfragmenttab, container, false);
        
		Button button = (Button) rootView.findViewById(R.id.searchbutton);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ImageTextListViewActivity.class);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.vesselType", vesselType);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.hullType", hullType);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.built", built);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.lost", lost);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.builder", builder);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.buildPlace", buildPlace);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.county", county);
				intent.putExtra("edu.mtu.citizenscience.thunderbay.depth", depth);
				startActivity(intent);
			}
		});
		
		Spinner vesselTypeSpinner = (Spinner) rootView.findViewById(R.id.vesseltype_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.vesseltype_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		vesselTypeSpinner.setAdapter(adapter);
		vesselTypeSpinner.setOnItemSelectedListener(this);
        
		Spinner hullSpinner = (Spinner) rootView.findViewById(R.id.hull_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.hull_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		hullSpinner.setAdapter(adapter);
		hullSpinner.setOnItemSelectedListener(this);
		
		Spinner builtSpinner = (Spinner) rootView.findViewById(R.id.built_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.built_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		builtSpinner.setAdapter(adapter);
		builtSpinner.setOnItemSelectedListener(this);
		
		Spinner lostSpinner = (Spinner) rootView.findViewById(R.id.lost_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.lost_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		lostSpinner.setAdapter(adapter);
		lostSpinner.setOnItemSelectedListener(this);
		
		Spinner builderSpinner = (Spinner) rootView.findViewById(R.id.builder_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.builder_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		builderSpinner.setAdapter(adapter);
		builderSpinner.setOnItemSelectedListener(this);
		
		Spinner buildPlaceSpinner = (Spinner) rootView.findViewById(R.id.buildPlace_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.buildPlace_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		buildPlaceSpinner.setAdapter(adapter);
		buildPlaceSpinner.setOnItemSelectedListener(this);
		
		Spinner countySpinner = (Spinner) rootView.findViewById(R.id.county_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.county_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		countySpinner.setAdapter(adapter);
		countySpinner.setOnItemSelectedListener(this);
		
		Spinner depthSpinner = (Spinner) rootView.findViewById(R.id.depth_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.depth_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		depthSpinner.setAdapter(adapter);
		depthSpinner.setOnItemSelectedListener(this);
		
        return rootView;
    }

    @Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		// An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    	
    	System.out.println("spinner " + parent + " = " + parent.getId());
    	
    	if (parent.getId() == 0x7f070006) {
    		vesselType = (String)parent.getItemAtPosition(pos);
    	} else if (parent.getId() == 0x7f070008) {
    		hullType = (String)parent.getItemAtPosition(pos);
    	} else if (parent.getId() == 0x7f07000a) {
    		built = (String)parent.getItemAtPosition(pos);
    	} else if (parent.getId() == 0x7f07000c) {
    		lost = (String)parent.getItemAtPosition(pos);
    	} else if (parent.getId() == 0x7f07000e) {
    		builder = (String)parent.getItemAtPosition(pos);
    	} else if (parent.getId() == 0x7f070010) {
    		buildPlace = (String)parent.getItemAtPosition(pos);
    	} else if (parent.getId() == 0x7f070012) {
    		county = (String)parent.getItemAtPosition(pos);
    	} else if (parent.getId() == 0x7f070014) {
    		depth = (String)parent.getItemAtPosition(pos);
    	}
    	
    	
	}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
 
}
