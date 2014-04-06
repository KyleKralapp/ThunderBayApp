package edu.mtu.citizenscience.thunderbay;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class SearchFragmentTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.searchfragmenttab, container, false);
        
		Button button = (Button)rootView.findViewById(R.id.searchbutton);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ImageTextListViewActivity.class);
				startActivity(intent);
			}
		});
        
        return rootView;
    }
 
}
