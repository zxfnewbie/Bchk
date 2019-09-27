package com.example.bcbk;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.bcbk.GameMenu.changeState;

public class GameOver extends BaseProperty {

	private changeState change;
	private DrawText text_score;//分数
	private DrawText text_Mkuai;//每秒块数
	private DrawText text_again;//重来
	private DrawText text_over;//结束
	@Override
	public void onTouch(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(text_again.onTouch(event)){
				change.execute(GameState.STATE_PLAY_INIT);
			}else if(text_over.onTouch(event)){
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);
			}
			break;

		default:
			break;
		}


	}
	public GameOver(){
		text_score=new DrawText();
		text_score.setXY(screen_width/2, screen_height/2);
		
		text_Mkuai=new DrawText();
		text_Mkuai.setText(GameScore.SCORE+"分", 60);
		text_Mkuai.setXY(screen_width/4, screen_height/4);
		
		text_again=new DrawText();
		text_again.setText("重来", 40);
		text_again.setXY(screen_width/4, screen_height*3/4);
		
		text_over=new DrawText();
		text_over.setText("结束", 40);
		text_over.setXY(screen_width*3/4, screen_height*3/4);
		
	}
	public void ondraw(Canvas canvas){
		text_score.setText(GameScore.SCORE+"分", 60);
		text_score.ondraw(canvas);
	//	text_Mkuai.ondraw(canvas);
		text_again.ondraw(canvas);
		text_over.ondraw(canvas);	
	}
	public void setchange(changeState change) {
		// TODO Auto-generated method stub
		this.change=change;
	}
	public void logic(){
		
	}
}
