package com.example.bcbk;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

//游戏菜单页面
public class GameMenu  extends BaseProperty{
	Rect targetRect;
	String str="Play";
	float x_left;
	float x_right;
	float y_top;
	float y_botton;
	changeState change;
	public GameMenu(){
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setTextSize(70);
		targetRect = new Rect();
		//返回包围整个字符串的最小的一个Rect区域
		paint.getTextBounds(str, 0, str.length(), targetRect); 

	}
	public void setChange(changeState change){
		this.change=change;
		
	}
	public void ondraw(Canvas canvas){
		canvas.drawRGB(0, 0, 0);
		int textheight=targetRect.height();
		int textwidth=targetRect.width();
		x_left=(screen_width-textwidth)/2;
		x_right=(screen_width+textwidth)/2;
		y_top=(screen_height-textheight)/2-textheight;
		y_botton=(screen_height-textheight)/2;
		canvas.drawText(str,x_left ,(screen_height-textheight)/2 , paint);
		
	}
	@Override
	public void onTouch(MotionEvent event){
		float x=event.getX();
		float y=event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			if(isStart(x, y)){
				if(change!=null){
					change.execute(GameState.STATE_PLAY_INIT);
				}
			}
			break;
		default:
			break;
		}
	}
	public boolean isStart(float x,float y){

		if(x>x_left&&x<x_right){
			if(y>y_top&&y<y_botton){
				return true;
			}
		}
		return false;
	}
	public interface changeState{
		public void execute(int state);
	}
	
}
