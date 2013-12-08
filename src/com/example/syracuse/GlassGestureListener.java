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
	
	/**
	 * The runnable containing scrolling actions.
	 */
	private Runnable runnable;
	
	/**
	 * A flag to control auto-scrolling.
	 */
	private boolean autoOn = false;

	/**
	 * Set the runnable.
	 * 
	 * @param runnable The runnable to manipulate.
	 */
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	/**
	 * Constructs a GlassGestureListener.
	 * 
	 * @param sv The ScrollView to manipulate.
	 */
	public GlassGestureListener(ScrollView sv){
		this.sv = sv;
	}
	
	/**
	 * Get the status of the autoOn auto-scrolling toggle
	 * 
	 * @return True for on, False for off.
	 */
	public boolean autoOn(){
		return autoOn;
	}
	
	/**
	 * Set value for AutoOn auto-scrolling toggle
	 * 
	 * @param b True for on, False for off.
	 */
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
     * Toggle auto-scrolling
     */
    public boolean onDoubleTap(MotionEvent e){

    	if(!autoOn){
    		autoOn = true;
    		Log.w("Gesture", "Run");
    		runnable.run();
    	} else{
    		Log.w("Gesture", "Stop");
    		autoOn = false;
    	}
    	
    	return true;
    }
}
