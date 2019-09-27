package com.example.bcbk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public abstract class BaseProperty {
	public Paint paint;
	int screen_width=MainActivity.screen_width;
	int screen_height=MainActivity.screen_height;
	public abstract void onTouch(MotionEvent event);
	
}
