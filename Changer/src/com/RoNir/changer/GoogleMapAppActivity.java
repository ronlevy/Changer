package com.RoNir.changer;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.app.Activity;
public class GoogleMapAppActivity extends Activity {
	   static final LatLng TutorialsPoint = new LatLng(21 , 57);
	   private GoogleMap googleMap;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_google_map_app);
	      try { 
	            if (googleMap == null) {
	               googleMap = ((MapFragment) getFragmentManager().
	               findFragmentById(R.id.map)).getMap();
	            }
	         googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	         googleMap.getUiSettings().setMyLocationButtonEnabled(true);

		      googleMap.setMyLocationEnabled(true);
	         Marker TP = googleMap.addMarker(new MarkerOptions().
	         position(TutorialsPoint).title("TutorialsPoint"));

	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	   }

	}