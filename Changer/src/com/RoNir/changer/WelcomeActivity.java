package com.RoNir.changer;

import com.parse.Parse;
import com.parse.ParseObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Parse.initialize(this, "gLL0wdZDN1V69a0NQbRYge3wIMxptHq2OobyuUpG", "JOFv82TcY2VyBGcHFR6cHNUMjFc4ttbYYX2SkTQx");
        setContentView(R.layout.activity_welcome);
        setupClientButton();
        setupBusinessButton();
//		ParseObject testObject = new ParseObject("TestObject");
//      testObject.put("foo", "bar");
//      testObject.saveInBackground();
    }


    private void setupClientButton() {
		Button buttonClient = (Button) findViewById(R.id.buttonClient);
		buttonClient.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(WelcomeActivity.this, ClientRegistrationActivity.class));
			}
		});
	}


	private void setupBusinessButton() {
    	Button buttonBusinessOwner = (Button) findViewById(R.id.buttonBusinessOwner);
    	buttonBusinessOwner.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(WelcomeActivity.this, GoogleMapAppActivity.class));
				
			}
		});
    	
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) { 
		case R.id.action_exit: 
			super.finish();
			System.exit(0);
			return true; 
		case R.id.action_settings: 
			return true; default: return super.onOptionsItemSelected(item); } 
	
        }
    }
