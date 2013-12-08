package com.example.syracuse;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Handles gestures for teleprompter app.
 * 
 * @author iSchoolGlass Teleprompter Team
 */
public class GlassGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
{
	/**
	 * ScrollView to manipulate.
	 */
	private ScrollView sv;
	
	/**
	 * The amount to scroll when Glass swiped.
	 */
	private final int SCROLL_VAL = 200;
	
	private Runnable runnable;
	private DisplayWeb dw;
	
	private boolean autoOn = false;

	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	/**
	 * Constructs a GlassGestureListener.
	 * 
	 * @param sv The ScrollView to manipulate.
	 */
	public GlassGestureListener(ScrollView sv, DisplayWeb dw){
		this.sv = sv;
		this.dw = dw;
	}
	
	public boolean autoOn(){
		return autoOn;
	}
	
	public void setAutoOn(boolean b){
		autoOn = b;
	}

	/**
	 * Scrolls in the direction swiped on Glass.
	 */
    @Override
    public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX, float velocityY){     
    	int scrollDir = (velocityX>0) ? SCROLL_VAL : -SCROLL_VAL;
    	sv.smoothScrollBy(0, scrollDir);
    	Log.w("ScrollPos",""+sv.getScrollY());
        //Log.w("Gesture", "Fling Velocity: " + Float.toString(velocityY));
        return true;
    } 
    
    /**
     * Toggle autoscrolling
     */
    public boolean onDoubleTap(MotionEvent e){
    	//sv.smoothScrollTo(0, 0);
    	
    	if(!autoOn)
    	{
    		autoOn = true;
    		Log.w("Gesture", "Run");
    		runnable.run();
    	}
    	else
    	{
    		Log.w("Gesture", "Stop");
    		autoOn = false;
    	}
    	
    	return true;
    }
}
