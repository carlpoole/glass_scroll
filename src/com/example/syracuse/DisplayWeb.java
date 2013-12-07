package com.example.syracuse;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * The main view for the Glass teleprompter
 * 
 * @author iSchoolGlass Teleprompter Team
 */
public class DisplayWeb extends Activity{
	
	/**
	 * Detects gestures from the device
	 */
	private GestureDetector gestureDetector;
	
	/**
	 * The TextView that contains the speech
	 */
	private TextView mytextview;
	
	/**
	 * The speech text
	 */
	private String SpeechString;
	
	/**
	 * JSON array to contain speeches
	 */
	private JSONArray speeches = null;
	
	/**
	 * JSON Url for speech strings
	 */
	private static final String speechURL = "http://aqueous-garden-8259.herokuapp.com/iglasstexts.json";
	
	/**
	 * JSON content tag
	 */
	private static final String TAG_CONTENT = "content";
	
	/**
	 * JSON id tag
	 */
	private static final String TAG_ID = "id";

	@Override
	public boolean onGenericMotionEvent(MotionEvent event)
	{
	    gestureDetector.onTouchEvent(event);
	    return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// ------- App setup ----------------------------------------
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
		gestureDetector = new GestureDetector(this, new GlassGestureListener(sv));
		mytextview = (TextView) findViewById(R.id.textView1);

		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		
		// ------- Get speech content from the web ------------------
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httppost = new HttpGet(speechURL);
		InputStream inputStream = null;
		
		try {
			HttpResponse response = httpclient.execute(httppost);           
			HttpEntity entity = response.getEntity();
			
			speeches = new JSONArray(EntityUtils.toString(entity));
			
		    for(int i = 0; i < speeches.length(); i++){
		        JSONObject c = speeches.getJSONObject(i);
		         
		        String id = (c.getString(TAG_ID) == null ? "Empty": c.getString(TAG_ID));
		        String content = (c.getString(TAG_CONTENT) == null ? "Empty": c.getString(TAG_CONTENT));
		        
		        Log.w("Json",id + ": " + content);
		        
		        SpeechString = content;    
		    }

		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			try{
				if(inputStream != null)
					inputStream.close();
				}
			catch(Exception squish){}
		}
		
		mytextview.setText(SpeechString);
		
		
		/*
		
		ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);
		scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {

	      public void run() {
	    	  ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
	    	  sv.smoothScrollBy(0, 1);	  
	      }}
		, 0, 1, TimeUnit.MILLISECONDS);
		
		*/
			
	}
}

