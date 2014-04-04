package edu.mtu.citizenscience.thunderbay;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
 
public class MapFragmentTab extends Fragment {
	
	private GoogleMap googleMap;
	double latitude = 45.114238;
	double longitude = -83.254395;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	final View rootview = inflater.inflate(R.layout.mapfragmenttab, container, false);

        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            Log.e("ERROR", "ERROR IN CODE: " + e.toString());
            e.printStackTrace();
        }

        return rootview;
    }
    
	@Override
	public void onDestroyView() {
		super.onDestroyView();

		if (!getActivity().isDestroyed()) {
			Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));  
			FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
			ft.remove(fragment);
			ft.commit();
		}

	}
    
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
			CameraPosition cameraPosition = new CameraPosition.Builder().target(
			        new LatLng(latitude, longitude)).zoom(10).build();
			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getActivity(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
}
