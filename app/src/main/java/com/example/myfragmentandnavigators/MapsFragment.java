package com.example.myfragmentandnavigators;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// Map Fragment works from only JsonActivity!
public class MapsFragment extends Fragment {
    GoogleMap mMap;

    double lat,lon;
    String name, city;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng latLon = new LatLng(lat, lon);

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLon)
                    .title(""+name)
                    .snippet(""+city);
            googleMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 13));

            googleMap.addMarker(new MarkerOptions().position(latLon).title("My Marker"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 13));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       
        //get the information from JsonActivity and put it in the fragment by Bundle variable
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        lat  = this.getArguments().getDouble("latKey");
        lon  = this.getArguments().getDouble("lonKey");
        name  = this.getArguments().getString("nameKey");
        city  = this.getArguments().getString("cityKey");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
