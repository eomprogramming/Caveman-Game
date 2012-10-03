package com.alyhassan.caveman.gui;
import com.alyhassan.caveman.R;
import com.alyhassan.caveman.options.Options;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * 
 * @author Aly
 *
 */
public class LevelScreen extends Activity implements OnClickListener{

	private Button[][] button;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		TableLayout table = new TableLayout(this);		
		
		button = new Button[5][4];
		TableRow[] row = new TableRow[button.length];
		int count = 1;
		
		Drawable dr = getResources().getDrawable(R.drawable.button_level);
		Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
		
		TableRow.LayoutParams bparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
		bparams.setMargins(5, 5, 5, 5);
		
		
		for(int i=0;i<button.length;i++){
			row[i] = new TableRow(this);
			row[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
			for(int j=0;j<button[0].length;j++){
				button[i][j] = new Button(this);
				button[i][j].setText(count+"");
	//			button[i][j].getBackground().setColorFilter(Color.argb(255,215,242,17), Mode.MULTIPLY);
				button[i][j].setBackgroundDrawable(dr);
				button[i][j].setPadding(20, 20,20, 20);
				button[i][j].setTextSize(32);	
				button[i][j].setId(count-1);
				button[i][j].setOnClickListener(this);
				row[i].addView(button[i][j],bparams);			
				count++;
			}
			table.addView(row[i]);
		}
		
/*		Drawable d = new BitmapDrawable(Bitmap.createScaledBitmap(bitmap, 40, 40, true));
				
		for(int i=0;i<button.length;i++)
			for(int j=0;j<button[0].length;j++)
				button[i][j].setBackgroundDrawable(d);
*/				
		
		table.setPadding(10, 30, 10, 20);
		layout.addView(table);
		layout.setBackgroundResource(R.drawable.back_common);
		
		setContentView(layout);
		
	}
	
	protected void onDestroy() {
		this.finish();
		super.onDestroy();
	}

	protected void onPause() {
		this.finish();
		super.onPause();
	}

	public void onClick(View v) {
		Options.currentMap = v.getId();
		startActivity(new Intent("com.alyhassan.caveman.LAUNCHGAME"));
	}
}
