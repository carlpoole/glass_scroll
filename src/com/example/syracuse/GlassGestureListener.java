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
	private ScrollView sv = null;
	
	/**
	 * The amount to scroll when Glass swiped.
	 */
	private final int SCROLL_VAL = 200;
	
	/**
	 * Constructs a GlassGestureListener.
	 * 
	 * @param sv The ScrollView to manipulate.
	 */
	public GlassGestureListener(ScrollView sv){
		this.sv = sv;
	}

	/**
	 * Scrolls in the direction swiped on Glass.
	 */
    @Override
    public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX, float velocityY){     
    	int scrollDir = (velocityX>0) ? SCROLL_VAL : -SCROLL_VAL;
    	sv.smoothScrollBy(0, scrollDir);
        //Log.w("Gesture", "Fling Velocity: " + Float.toString(velocityY));
        return true;
    } 
    
    /**
     * Scrolls to the top of the speech.
     */
    public boolean onDoubleTap(MotionEvent e){
    	sv.smoothScrollTo(0, 0);
    	Log.w("Gesture", "DoubleTap");
    	return true;
    }
}
