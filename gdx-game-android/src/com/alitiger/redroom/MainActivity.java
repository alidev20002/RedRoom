package com.alitiger.redroom;

import android.os.*;
import android.view.*;
import com.badlogic.gdx.backends.android.*;
import android.media.*;
import android.content.*;


public class MainActivity extends AndroidApplication {
	
	int ad = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		ad = getIntent().getExtras().getInt("ad");
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        initialize(new MyGdxGame2(ad), cfg);
    }

	@Override
	public void onBackPressed()
	{
		Intent i = new Intent(MainActivity.this, StartActivity.class);
		startActivity(i);
		MainActivity.this.finish();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
	}
}
