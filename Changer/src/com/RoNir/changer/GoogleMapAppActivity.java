package com.RoNir.changer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.app.Activity;

public class GoogleMapAppActivity extends Activity {
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(31.75121, 35.21272);
	private GoogleMap map;
	private static final LatLng GOLDEN_GATE_BRIDGE = 
	        new LatLng(37.828891,-122.485884);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_map_app);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMyLocationEnabled(true);
//		Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
//				.title("Hamburg"));
		Marker kiel = map.addMarker(new MarkerOptions()
				.position(KIEL)
				.title("Money Change YL")
				.snippet("best prices"));

//		 Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(KIEL, 30));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.google_map_app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.menu_sethybrid:
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;

		case R.id.menu_showtraffic:
			map.setTrafficEnabled(true);
			break;

		case R.id.menu_zoomin:
			map.animateCamera(CameraUpdateFactory.zoomIn());
			break;

		case R.id.menu_zoomout:
			map.animateCamera(CameraUpdateFactory.zoomOut());
			break;

		case R.id.menu_gotolocation:
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(GOLDEN_GATE_BRIDGE) // Sets the center of the map to
												// Golden Gate Bridge
					.zoom(17) // Sets the zoom
					.bearing(90) // Sets the orientation of the camera to east
					.tilt(30) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			map.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
			break;

		case R.id.menu_addmarker:
			// ---using the default marker---
			/*
			 * map.addMarker(new MarkerOptions() .position(GOLDEN_GATE_BRIDGE)
			 * .title("Golden Gate Bridge") .snippet("San Francisco")
			 * .icon(BitmapDescriptorFactory
			 * .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			 */

			map.addMarker(new MarkerOptions()
					.position(GOLDEN_GATE_BRIDGE)
					.title("Golden Gate Bridge")
					.snippet("San Francisco")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)));
			break;

		case R.id.menu_getcurrentlocation:
			// ---get your current location and display a blue dot---
			map.setMyLocationEnabled(true);

			break;

		case R.id.menu_showcurrentlocation:
			Location myLocation = map.getMyLocation();
			LatLng myLatLng = new LatLng(myLocation.getLatitude(),
					myLocation.getLongitude());

			CameraPosition myPosition = new CameraPosition.Builder()
					.target(myLatLng).zoom(17).bearing(90).tilt(30).build();
			map.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
			break;
		}
		return true;
	}
}
