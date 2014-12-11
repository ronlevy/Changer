package com.RoNir.changer;


import com.RoNir.changer.R.id;
import com.parse.ParseObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClientRegistrationActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_registration);
		Button buttonCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
		buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//MySingleton.getInstance().phoneNumber = new String(((EditText) findViewById(id.editTextPhoneNumber)).getText().toString());
				Intent i = new Intent(ClientRegistrationActivity.this, SignInWaitingActivity.class);
				startActivityForResult(i, 1);
				
				
					
			}
		});
		
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            String result=data.getStringExtra("result");
	            if(result.equals("true")){
					Log.d("ClientRegistration",  "true");
					ParseObject client = new ParseObject("Clients");
					client.put("FirstName", ((EditText) findViewById(id.editTextFirstName)).getText().toString());
					client.put("LastName", ((EditText) findViewById(id.editTextLastName)).getText().toString());
					client.put("PhoneNumber", ((EditText) findViewById(id.editTextPhoneNumber)).getText().toString());
					client.saveInBackground();
				}	
				else{
					Log.d("ClientRegistration",  "false");
					AlertDialog.Builder builder1 = new AlertDialog.Builder(ClientRegistrationActivity.this);
		            builder1.setMessage(getResources().getString(R.string.msg_auth_failed));
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("OK",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });
//		            builder1.setNegativeButton("No",
//		                    new DialogInterface.OnClickListener() {
//		                public void onClick(DialogInterface dialog, int id) {
//		                    dialog.cancel();
//		                }
//		            });

		            AlertDialog alert11 = builder1.create();
		            alert11.show();
				}
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}//onActivityResult
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_registration, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
