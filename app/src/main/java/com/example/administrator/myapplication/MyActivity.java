package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity {

	private TextView mImg, mImg_0;
	private TextView mImg1, mImg1_1;
	private TextView mImg2;
	private TextView mImg3;

	private Button btn;
	private Button btn1;
	private Button btn2;
	private Button btn3;

	//	TranslateAnimation mDownAnimation;
//	TranslateAnimation mUpAnimation;
	AnimationTracker mTracker0;
	AnimationTracker mTracker1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		mTracker0 = AnimationTracker.newTracker();

		mImg = (TextView) findViewById(R.id.img);
		mImg_0 = (TextView) findViewById(R.id.img_0);
		Animation animation = new TranslateAnimation(0f, 0f, 0f, 700);
		animation.setDuration(500);
		Animation animation1 = new TranslateAnimation(0f, 0f, 700f, -200);
		animation1.setDuration(500);

		Animation animation2 = new TranslateAnimation(0f, 0f, 0f, 900);
		animation2.setDuration(700);
		Animation animation3 = new TranslateAnimation(0f, 0f, 900f, -200);
		animation3.setDuration(700);
		mTracker0.addViewEntry(new AnimationTracker.Entry(mImg, animation, animation1))
				.addViewEntry(new AnimationTracker.Entry(mImg_0, animation2, animation3));

		mImg1 = (TextView) findViewById(R.id.img1);
		mImg1_1 = (TextView) findViewById(R.id.img1_1);
		mTracker1 = AnimationTracker.newTracker();

		Animation animation4 = new TranslateAnimation(0f, 0f, 0f, 700);
		animation.setDuration(500);
		Animation animation5 = new TranslateAnimation(0f, 0f, 700f, -200);
		animation5.setDuration(500);

		Animation animation6 = new TranslateAnimation(0f, 0f, 0f, 900);
		animation6.setDuration(700);
		Animation animation7 = new TranslateAnimation(0f, 0f, 900f, -200);
		animation7.setDuration(700);
		mTracker1.addViewEntry(new AnimationTracker.Entry(mImg1, animation4, animation5));
		mTracker1.addViewEntry(new AnimationTracker.Entry(mImg1_1, animation6, animation7));

		mImg2 = (TextView) findViewById(R.id.img2);
		mImg3 = (TextView) findViewById(R.id.img3);

		btn = (Button) findViewById(R.id.start);
		btn1 = (Button) findViewById(R.id.start1);
		btn2 = (Button) findViewById(R.id.start2);
		btn3 = (Button) findViewById(R.id.start3);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playAnimationAtPage(0);
			}
		});

		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playAnimationAtPage(1);
			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playAnimationAtPage(2);
			}
		});

		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playAnimationAtPage(3);
			}
		});
	}

	int mLastPage = -1;

	private void playAnimationAtPage(int pageIndex) {
		if (mLastPage != pageIndex) {
			AnimationTracker.playAtIndex(pageIndex, mTracker0, mTracker1);
		}
		mLastPage = pageIndex;
	}

}
