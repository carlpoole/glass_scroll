package com.example.syracuse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import junit.framework.Test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayWeb extends Activity{
	private GestureDetector gestureDetector;
	
	TextView mytextview;
	private WakeLock mWakeLock;
	public String SpeechString;

	@Override
	public boolean onGenericMotionEvent(MotionEvent event)
	{
	    gestureDetector.onTouchEvent(event);
	    return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gestureDetector = new GestureDetector(this, new MyGestureListener());
		
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mytextview = (TextView) findViewById(R.id.textView1);
		
		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httppost = new HttpGet("http://carlpoole.com/glass_scroll.html");

		InputStream inputStream = null;
		
		try {
			HttpResponse response = httpclient.execute(httppost);           
			HttpEntity entity = response.getEntity();
	
			SpeechString = EntityUtils.toString(entity);
	
			Log.w("HTTPRESPONSE", SpeechString);

		} catch (Exception e) { 
		} finally {
			try{
				if(inputStream != null)
					inputStream.close();
				}
			catch(Exception squish){}
		}

		mytextview.setText(SpeechString);
		
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			
			ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
			  @Override
			  public void run() {
				  sv.smoothScrollBy(0, 1);
			  }
		}, 10,100);
		
	}
	
	public class MyGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
	{
	    @Override
	    public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX, float velocityY)
	    {     
	    	if (velocityX>0) {
	    		ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
	    		sv.smoothScrollBy(0, 150);
	    	} else {
	    		ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
	    		sv.smoothScrollBy(0, -150);
	    	}
	        
	        Log.w("y", Float.toString(velocityY));
	        return true;
	    } 
	}
}

