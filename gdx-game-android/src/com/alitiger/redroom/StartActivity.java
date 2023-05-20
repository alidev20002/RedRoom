package com.alitiger.redroom;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ir.tapsell.sdk.*;

public class StartActivity extends Activity
{
	TextView title, intro, skip_tv;
	Button play, rate, about, skip;
	Typeface tf;
	String appKey = "lclrkicrssmbhgmpjhgdlimccbhlncrbcergmrifeapggkoqniekgaqtmtmlenaoerploa";
	String adKey = "6058bcc3703f730001a18fa6";
	int ad = 0;
	TapsellAd tap = null;
	long time;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.start);
		
		setTime();
		requestAd();
		
		title = findViewById(R.id.title);
		intro = findViewById(R.id.intro);
		play = findViewById(R.id.play);
		rate = findViewById(R.id.rate);
		about = findViewById(R.id.about);
		skip = findViewById(R.id.skip);
		skip.setEnabled(false);
		skip_tv = findViewById(R.id.skip_tv);
		
		tf = Typeface.createFromAsset(getAssets(), "fonts/omegaforce.otf");
		
		skip_tv.setText("Watch an Ad and skip current level");
		intro.setText("  Keep your calm\nDont let the ball hit the obstacles");
		
		title.setTypeface(tf);
		intro.setTypeface(tf);
		skip_tv.setTypeface(tf);
		play.setTypeface(tf);
		rate.setTypeface(tf);
		about.setTypeface(tf);
		skip.setTypeface(tf);
		
		play.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					Intent i = new Intent(StartActivity.this, MainActivity.class);
					i.putExtra("ad", ad);
					startActivity(i);
					StartActivity.this.finish();
				}
			
		});
		
		skip.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					if (System.currentTimeMillis() - time > (3600 * 1000)) {
						showAd();
					}else{
						Toast.makeText(StartActivity.this, "You can only see one ad per hour", Toast.LENGTH_LONG).show();
					}
				}

				private void showAd()
				{
					TapsellShowOptions options = new TapsellShowOptions();
					options.setRotationMode(TapsellShowOptions.ROTATION_LOCKED_PORTRAIT);
					tap.show(StartActivity.this, options, new TapsellAdShowListener() {

							@Override
							public void onOpened(TapsellAd p1)
							{
								Tapsell.setRewardListener(new TapsellRewardListener() {

										@Override
										public void onAdShowFinished(TapsellAd p1, boolean p2)
										{
											if (p2 && p1.isRewardedAd()) {
												SharedPreferences shared = getSharedPreferences("redroom", MODE_PRIVATE);
												SharedPreferences.Editor editor = shared.edit();
												editor.putLong("time", System.currentTimeMillis());
												editor.apply();
												skip.setEnabled(false);
												ad = 1;
												time = System.currentTimeMillis();
												Intent i = new Intent(StartActivity.this, MainActivity.class);
												i.putExtra("ad", ad);
												startActivity(i);
												StartActivity.this.finish();
											}
										}
									});
							}

							@Override
							public void onClosed(TapsellAd p1)
							{
								// TODO: Implement this method
							}

						});
				}
		});
		
		rate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					String url= "myket://comment?id=com.alitiger.redroom";
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(url));
					startActivity(intent);
				}

		});
		
		about.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					Intent i = new Intent(StartActivity.this, AboutActivity.class);
					startActivity(i);
					StartActivity.this.finish();
				}
				
		});
		
	}

	private void setTime()
	{
		SharedPreferences shared = getSharedPreferences("redroom", MODE_PRIVATE);
		if (shared.contains("time")) {
			time = shared.getLong("time", 0);
		}else{
			time = 0;
		}
	}

	private void requestAd()
	{
		Tapsell.initialize(this, appKey);
		TapsellAdRequestOptions option = new TapsellAdRequestOptions();
		option.setCacheType(TapsellAdRequestOptions.CACHE_TYPE_STREAMED);
		Tapsell.requestAd(this, adKey, option, new TapsellAdRequestListener() {

				@Override
				public void onNoAdAvailable()
				{
					// TODO: Implement this method
				}

				@Override
				public void onNoNetwork()
				{
					// TODO: Implement this method
				}

				@Override
				public void onExpiring(TapsellAd p1)
				{
					// TODO: Implement this method
				}
				

				@Override
				public void onError(String p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onAdAvailable(TapsellAd p1)
				{
					tap = p1;
					skip.setEnabled(true);
				}
	     });
	}
}
