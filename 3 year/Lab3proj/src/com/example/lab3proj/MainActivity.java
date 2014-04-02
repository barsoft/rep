package com.example.lab3proj;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.R.bool;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	public enum State {
		Menu, Game, History
	}

	private MySurfaceView glView;
	public Renderer renderer;
	FrameLayout flayout;
	View menu;
	State st;

	Timer myTimer;
	protected Integer timeValue;
	private String currentTime;

	View historyWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		menu = inflater.inflate(R.layout.menu, null);

		st = State.Menu;
		setContentView(menu);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (st==State.Game)
			renderer.onTouchEvent(event);
		return true;
	}

	public void onHomeClick(View v) {
		if (st == State.Game)
			endGame();
		if (st == State.History)
			hideHistory();
	}

	private void hideHistory() {
		st = State.Menu;
		setContentView(menu);
	}

	public void onStartClick(View v) {
		startGame();
	}

	public void onExitClick(View v) {
		moveTaskToBack(true);
	}

	public void onHistoryClick(View v) {
		showHistory();
	}

	public void onBackPressed() {
		if (st == State.Menu)
			moveTaskToBack(true);
		if (st == State.History)
			hideHistory();
		if (st == State.Game)
			endGame();
	}

	public void startGame() {
		renderer = new Renderer(this);
		glView = new MySurfaceView(this);
		glView.setRenderer(renderer);
		flayout = new FrameLayout(this);
		flayout.addView(glView);

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.gui, null);

		flayout.addView(view);
		st = State.Game;

		setContentView(flayout);

		myTimer = new Timer();

		timeValue = new Integer(0);

		final Handler uiHandler = new Handler();

		myTimer.scheduleAtFixedRate(new TimerTask() { // Определяем задачу
					@Override
					public void run() {
						if (st == State.Game) {
							final String result = timeValue.toString();
							uiHandler.post(new Runnable() {

								@Override
								public void run() {
									TextView tv = (TextView) findViewById(R.id.TextView01);
									timeValue++;
									String seconds;
									if (timeValue.toString().length() == 1) {
										seconds = "0" + timeValue % 60;
									} else
										seconds = timeValue % 60 + "";
									String minutes;
									if (timeValue.toString().length() == 1) {
										minutes = "0" + timeValue / 60;
									} else
										minutes = timeValue / 60 + "";
									currentTime = minutes + ":" + seconds;
									tv.setText(currentTime);
								}
							});
						}
					};
				}, 0, 1000);
	}

	public void endGame() {
		st = State.Menu;
		myTimer.cancel();
		setContentView(menu);
	}

	public void saveHistory() {
		SharedPreferences settings = getSharedPreferences("History", 0);
		int number = settings.getInt("numberOfExperiments", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("numberOfExperiments", number + 1);
		editor.putInt("number" + (number + 1), number + 1);
		editor.putString("time" + (number + 1), currentTime);
		editor.commit();
	}

	public void loadHistory() {
		GridView gv = (GridView) findViewById(R.id.gridView1);
		
		ArrayList<History> hst = new ArrayList<MainActivity.History>();
		ArrayList<String> data = new ArrayList<String>();
		SharedPreferences settings = getSharedPreferences("History", 0);
		int number = settings.getInt("numberOfExperiments", 0);

		for (int i = 0; i < number+1; i++) {
			Integer num = settings.getInt("number" + i, 0);
			String tim = settings.getString("time" + i, null);
			hst.add(new History(num, tim));

			if (num != null && tim != null) {
				data.add(num.toString());
				data.add(tim.toString());
			}
		}

		if (data.isEmpty())
			data.add("null");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item, R.id.itemTxt, data);
		gv.setAdapter(adapter);
	}

	public class History {
		int number;
		String time;

		public History(int number, String time) {
			this.number = number;
			this.time = time;
		}
	}

	public void finishGame() {
		st = State.Menu;
		myTimer.cancel();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		historyWindow = inflater.inflate(R.layout.history, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Well done!")
				.setMessage("Would you like to watch history?")
				.setCancelable(false)
				.setIcon(R.drawable.ic_launcher)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								saveHistory();
								showHistory();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						endGame();
					}
				});

		builder.create().show();
	}

	public void showHistory() {
		st = State.History;
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		historyWindow = inflater.inflate(R.layout.history, null);
		setContentView(historyWindow);
		loadHistory();
	}
}
