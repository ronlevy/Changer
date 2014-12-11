package com.RoNir.changer;

import android.telephony.SmsManager;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SignInWaitingActivity extends Activity {
	private BroadcastReceiver mIntentReceiver;
	TextView timerTv;
	TextView mobNoVeryfyTv;

	private ProgressBar progressBar;
	static Boolean timeOut = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sign_in_waiting);
		
		mobNoVeryfyTv = (TextView) findViewById(R.id.SW_MobNoVeryfyDesctxt);

		timerTv = (TextView) findViewById(R.id.SW_TimeRemainigTv);
		progressBar = (ProgressBar) findViewById(R.id.SW_progressBar);
		// show 30 second time count down
		new CountDownTimer(30000, 1000) {

			public void onTick(long millisUntilFinished) {
				timerTv.setText("Seconds Remaining : " + millisUntilFinished
						/ 1000);
			}

			public void onFinish() {
				timerTv.setText("Time Over");
			//	SignInWaitingActivity.this.finish();
			}
		}.start();

	}

	@Override
	protected void onResume() {
		super.onResume();
		 String messageToSend = "Ron";
		 String number = new String(MySingleton.getInstance().phoneNumber);
		 Log.d("SignInWaiting", MySingleton.getInstance().phoneNumber);

		 SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
		IntentFilter intentFilter = new IntentFilter("SmsMessage.intent.MAIN");
		mIntentReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String msg = intent.getStringExtra("get_msg");

				// Process the sms format and extract body &amp; phoneNumber
				msg = msg.replace("\n", "");
				String body = msg.substring(msg.lastIndexOf(":") + 1,
						msg.length());
				String pNumber = msg.substring(0, msg.lastIndexOf(":"));
				String result = "false";

				// Add it to the list or do whatever you wish to
				Log.d("onResume", "" + msg + body + pNumber);

				Toast.makeText(getApplicationContext(), body, 1).show();

				// check body content with your validation code mine is
				// success123

				if (body.equalsIgnoreCase("Ron")) {

					Toast.makeText(getApplicationContext(),
							"Authentication Success.", 1).show();
					mobNoVeryfyTv.setText("Authentication Success.");
					MySingleton.getInstance().isAuth = true;
					result = "true";
					

				} else {

					// if message is contains some invalide code
					mobNoVeryfyTv.setText("Authentication Fails.");

				//	SignInWaitingActivity.this.finish();

				}
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result",result);
				SignInWaitingActivity.this.setResult(RESULT_OK,returnIntent);
				finish();

			}
		};
		this.registerReceiver(mIntentReceiver, intentFilter);
	}

	@Override
	protected void onPause() {

		super.onPause();
		this.unregisterReceiver(this.mIntentReceiver);
	}

}
