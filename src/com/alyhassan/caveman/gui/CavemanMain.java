package com.alyhassan.caveman.gui;

import com.alyhassan.caveman.R;
import com.alyhassan.caveman.io.OptionsIO;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;

public class CavemanMain extends Activity {
	
	ScrollView scroll;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		OptionsIO.loadOptions();
		
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
                
        Button play = (Button) findViewById(R.id.play);
        play.setTypeface(tf);
	    play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startActivity(new Intent("com.alyhassan.caveman.PLAYGAME"));
            	Log.d("caveman", "Play Game Selected");
           }
        });
	    
	    Button options = (Button) findViewById(R.id.options);
        options.setTypeface(tf);
        options.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startActivity(new Intent("com.alyhassan.caveman.OPTIONS"));
            	Log.d("caveman", "Options Selected");
           }
        });
        
        Button about = (Button) findViewById(R.id.about);
        about.setTypeface(tf);
        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startActivity(new Intent("com.alyhassan.caveman.ABOUT"));
            	Log.d("caveman", "About Selected");
           }
        });
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
}