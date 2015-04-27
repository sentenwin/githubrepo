package com.example.eagleeye;

import java.util.Calendar;

import com.example.eagleeye.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SoundAlert extends ActionBarActivity  {
        /* constants */
        private static final int POLL_INTERVAL = 300;
        
        private int count = 0;
        
        private String PhoneNo;
       
        /** running state **/
        private boolean mRunning = false;
        
        /** config state **/
        private int mThreshold;
        
        private PowerManager.WakeLock mWakeLock;

        private Handler mHandler = new Handler();

        /* References to view elements */
        private TextView mStatusView;
        private SoundLevelView mDisplay;

        /* sound data source */
        private SoundMeter mSensor;
        
       /****************** Define runnable thread again and again detect noise *********/
        
        private Runnable mSleepTask = new Runnable() {
                public void run() {
                	//Log.i("Noise", "runnable mSleepTask");
                        
                	start();
                }
        };
        
        // Create runnable thread to Monitor Voice
        private Runnable mPollTask = new Runnable() {
                public void run() {
                	
                        double amp = mSensor.getAmplitude();
                        //Log.i("Noise", "runnable mPollTask");
                        updateDisplay("Monitoring Sound...", amp);

                        if ((amp > mThreshold)) {
                        	  count++;
                              callForHelp();
                              //Log.i("Noise", "==== onCreate ===");
                              
                        }
                        
                        // Runnable(mPollTask) will again execute after POLL_INTERVAL
                        mHandler.postDelayed(mPollTask, POLL_INTERVAL);
                     
                }
        };
        
        
        
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                
                // Defined SoundLevelView in main.xml file
                setContentView(R.layout.watchman_mode);
                mStatusView = (TextView) findViewById(R.id.status);
               
                // Used to record voice
                mSensor = new SoundMeter();
                mDisplay = (SoundLevelView) findViewById(R.id.volume);
                
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "SoundAlert");
                
        		Button stop_watch = (Button)findViewById(R.id.stop_watch);
        		
        		Bundle extras = getIntent().getExtras();
        		PhoneNo = extras.getString(watchman_main.EXTRA_MESSAGE);

        		stop_watch.setOnClickListener(new View.OnClickListener() {
        			
        			@Override
        			public void onClick(View v) {
        				// TODO Auto-generated method stub
        				stop();
        			}
        		});
        }

        
        @Override
        public void onResume() {
                super.onResume();
                //Log.i("Noise", "==== onResume ===");
                
                initializeApplicationConstants();
                mDisplay.setLevel(0, mThreshold);
                
                if (!mRunning) {
                    mRunning = true;
                    start();
                }
        }

        @Override
        public void onStop() {
                super.onStop();
               // Log.i("Noise", "==== onStop ===");
               
                //Stop noise monitoring
                stop();
               
        }

        private void start() {
        	    //Log.i("Noise", "==== start ===");
        	
                mSensor.start();
                if (!mWakeLock.isHeld()) {
                        mWakeLock.acquire();
                }
                
                //Noise monitoring start
                // Runnable(mPollTask) will execute after POLL_INTERVAL
                mHandler.postDelayed(mPollTask, POLL_INTERVAL);
        }

        private void stop() {
        	Log.i("Noise", "==== Stop Noise Monitoring===");
                if (mWakeLock.isHeld()) {
                        mWakeLock.release();
                }
                mHandler.removeCallbacks(mSleepTask);
                mHandler.removeCallbacks(mPollTask);
                mSensor.stop();
                mDisplay.setLevel(0,0);
                updateDisplay("stopped...", 0.0);
                mRunning = false;
               
        }

       
        private void initializeApplicationConstants() {
                // Set Noise Threshold
        	    mThreshold = 8;
                
        }

        private void updateDisplay(String status, double signalEMA) {
                mStatusView.setText(status);
                // 
                mDisplay.setLevel((int)signalEMA, mThreshold);
        }
        
        
        private void callForHelp() {
              
      	  Toast.makeText(getApplicationContext(), "sound detected ....", 
    			  Toast.LENGTH_SHORT).show();
              
        	 // Show alert when noise thersold crossed
        	if (count % 5 == 0) {
        		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
			    SmsManager sms = SmsManager.getDefault();
			    sms.sendTextMessage(PhoneNo, null, "Sound Detected time :"+ mydate, null, null);
			    Toast.makeText(getApplicationContext(), "Five time continiously Sound Detected time :"+ mydate + "send alert to "+ PhoneNo, Toast.LENGTH_SHORT).show();	
        	  count = 0;
        	}
        }

};
