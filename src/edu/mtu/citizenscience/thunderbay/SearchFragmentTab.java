package edu.mtu.citizenscience.thunderbay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
 
public class SearchFragmentTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.searchfragmenttab, container, false);
        Button button = (Button) rootView.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            selfDestruct(v);
          }
        });
        return rootView;
    }
    
    public void selfDestruct(View view) {
        System.out.println("selfDestruct.");
    }
 
}
