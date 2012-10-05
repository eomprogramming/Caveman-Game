package com.caveman.gui;

import com.caveman.R;
import com.caveman.io.MapIO;
import com.caveman.options.Options;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class LaunchGame extends Activity{

	PlayView game;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		game = new PlayView(this, MapIO.readBoard(getResources().openRawResource(R.raw.maps),Options.currentMap));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(game);		
		
	}
	

	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(1,1,Menu.FIRST,"Refresh");
	    menu.add(1,2,Menu.FIRST,"Restart");
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("caveman", item.getItemId()+" selected");
		if(item.getItemId() == 1)
			game.refresh();
		else if(item.getItemId() == 2)
			game.reset(MapIO.readBoard(getResources().openRawResource(R.raw.maps),Options.currentMap));
		return super.onOptionsItemSelected(item);
	}

}
