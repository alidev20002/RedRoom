package com.alitiger.redroom;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;

public class AboutActivity extends Activity
{
	TextView me, sound, gm;
	Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		me = findViewById(R.id.me);
		sound = findViewById(R.id.sounds);
		gm = findViewById(R.id.gm);
		sound.setText("Sound effects from zapslat.com\n  Music from AShamaluevMusic");
		
		tf = Typeface.createFromAsset(getAssets(), "fonts/omegaforce.otf");
		me.setTypeface(tf);
		sound.setTypeface(tf);
		gm.setTypeface(tf);
	}
	
	@Override
	public void onBackPressed()
	{
		Intent i = new Intent(AboutActivity.this, StartActivity.class);
		startActivity(i);
		AboutActivity.this.finish();
	}
}
