package com.example.bcbk;

import com.example.bcbk.GameMenu.changeState;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback, Runnable, changeState {
	private SurfaceHolder hodler;
	private int game_state_now = GameState.STATE_MENU;// 代表当前的游戏状态
	private boolean game_is_stop = true;// 代表游戏是否暂停
	private Canvas canvas;
	private GameMenu gameMenu;
	private GamePlay gamePlay;
	private GameScore gameScore;
	private GameOver gameOver;
	
	private boolean thread_is_run=true;

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		gameMenu = new GameMenu();
		gameMenu.setChange(this);
		
		gamePlay=new GamePlay();
		gamePlay.setchange(this);
		
		gameOver=new GameOver();
		gameOver.setchange(this);
		
		gameScore=new GameScore();
		hodler = getHolder();
		hodler.addCallback(this);
	}

	// 屏幕改变的时候调用
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	// 建立的时候
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		new Thread(this).start();
	}

	// 销毁的时候
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		game_is_stop=false;
		thread_is_run=false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (thread_is_run) {
			if (game_is_stop) {
				logic();
				ondraw();
			}
		}
	}

	// 画面渲染
	public void ondraw() {
		try {
			canvas = hodler.lockCanvas();// 锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。

			switch (game_state_now) {
			case GameState.STATE_MENU:
				gameMenu.ondraw(canvas);
				break;
			case GameState.STATE_PLAY_INIT:
				gamePlay.init();
				game_state_now=GameState.STATE_PLAY;
				break;				
			case GameState.STATE_PLAY:
				canvas.drawColor(Color.WHITE);
				gamePlay.ondraw(canvas);
				gameScore.ondraw(canvas);
				break;
			case GameState.STATE_PAUSE:

				break;
			case GameState.STATE_OVER:
				canvas.drawColor(Color.BLACK);
				gameOver.ondraw(canvas);
				break;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				hodler.unlockCanvasAndPost(canvas);// 结束锁定画图，并提交改变。
			}
		}

	}

	// 逻辑判断
	public void logic() {
		switch (game_state_now) {
		case GameState.STATE_MENU:

			break;
		case GameState.STATE_PLAY:
			gamePlay.logic();
			break;
		case GameState.STATE_PAUSE:

			break;
		case GameState.STATE_OVER:
			
			break;

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (game_state_now) {
		case GameState.STATE_MENU:
			gameMenu.onTouch(event);
			break;
		case GameState.STATE_PLAY:
			gamePlay.onTouch(event);
			break;
		case GameState.STATE_PAUSE:

			break;
		case GameState.STATE_OVER:
			gameOver.onTouch(event);
			break;
		}
		return true;
	}

	@Override
	public void execute(int state) {
		// TODO Auto-generated method stub
		game_state_now=state;
	}

}
