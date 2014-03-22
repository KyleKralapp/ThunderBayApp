package edu.mtu.citizenscience.thunderbay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class MapFragmentTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mapfragmenttab, container, false);
        return rootView;
    }
 
}
