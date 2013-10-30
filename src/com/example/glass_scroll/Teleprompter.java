package com.example.glass_scroll;

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

public class Teleprompter extends Activity{
private GestureDetector gestureDetector;

TextView mytextview;
private WakeLock mWakeLock;

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

mytextview.setText("Four score and seven years ago our fathers brought forth on this continent," +
		" a new nation, conceived in Liberty, and dedicated to the proposition that all men are " +
		"created equal. \n Now we are engaged in a great civil war, testing whether that nation, " +
		"or any nation so conceived and so dedicated, can long endure. We are met on a great " +
		"battle-field of that war. We have come to dedicate a portion of that field, as a final " +
		"resting place for those who here gave their lives that that nation might live. " +
		"It is altogether fitting and proper that we should do this.\n But, in a larger sense, " +
		"we can not dedicate -- we can not consecrate -- we can not hallow -- this ground. " +
		"The brave men, living and dead, who struggled here, have consecrated it, " +
		"far above our poor power to add or detract. The world will little note, " +
		"nor long remember what we say here, but it can never forget what they did here. " +
		"It is for us the living, rather, to be dedicated here to the unfinished work which " +
		"they who fought here have thus far so nobly advanced. It is rather for us to be here " +
		"dedicated to the great task remaining before us -- that from these honored dead we take " +
		"increased devotion to that cause for which they gave the last full measure of devotion -- " +
		"that we here highly resolve that these dead shall not have died in vain -- that this nation, " +
		"under God, shall have a new birth of freedom -- and that government of the people, by the people, " +
		"for the people, shall not perish from the earth."
);

}
public class MyGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
{
@Override
public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX, float velocityY)
{ 
	
	if (velocityX>0) {
		ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
		sv.smoothScrollBy(0, 100);
	} else {
		ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
		sv.smoothScrollBy(0, -100);
	}
	
	Log.w("y", Float.toString(velocityY));
		return true;
	} 
}

}