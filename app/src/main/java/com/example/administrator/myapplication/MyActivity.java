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

	private TextView mImg;
	private TextView mImg1;
	private TextView mImg2;
	private TextView mImg3;

	private Button btn;
	private Button btn1;
	private Button btn2;
	private Button btn3;

	TranslateAnimation mDownAnimation;
	TranslateAnimation mUpAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
	    mImg = (TextView) findViewById(R.id.img);
	    mImg1 = (TextView) findViewById(R.id.img1);
	    mImg2 = (TextView) findViewById(R.id.img2);
	    mImg3 = (TextView) findViewById(R.id.img3);

	    btn = (Button ) findViewById(R.id.start);
	    btn1 = (Button ) findViewById(R.id.start1);
	    btn2 = (Button ) findViewById(R.id.start2);
	    btn3 = (Button ) findViewById(R.id.start3);

	    mDownAnimation = new TranslateAnimation(0f, 0f, 0f, 700);
	    mDownAnimation.setDuration(500);
	    mDownAnimation.setFillAfter(true);

	    mUpAnimation = new TranslateAnimation( 0f, 0f, 700f, -200);
	    mUpAnimation.setDuration(500);

	    mDownAnimation.setAnimationListener(mDownAnimationListener);
	    mUpAnimation.setAnimationListener(mUpAnimationListener);

	    btn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    test(mImg);
//			    int[] location;
//
//			    location = new int[2];
//			    mImg.getLocationInWindow(location);
//			    if (isInScreen(location[0], location[1])){
//				    Log.d("mImg1", "down");
//			        mImg1.startAnimation(mUpAnimation);
//			    }  else {
//				    mImg1.clearAnimation();
//			    }
//
//			    location = new int[2];
//			    mImg2.getLocationInWindow(location);
//			    if (isInScreen(location[0], location[1])){
//				    mImg2.startAnimation(mUpAnimation);
//				    Log.d("mImg2", "down");
//			    }  else {
//				    mImg2.clearAnimation();
//			    }
//
//			    location = new int[2];
//			    mImg3.getLocationInWindow(location);
//			    if (isInScreen(location[0], location[1])){
//				    mImg3.startAnimation(mUpAnimation);
//				    Log.d("mImg3", "down");
//		        } else {
//				    mImg3.clearAnimation();
//			    }
//
//			    mImg.startAnimation(mDownAnimation);
		    }
	    });

	    btn1.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    test(mImg1);
//			    int[] location;
//
//			    location = new int[2];
//			    mImg.getLocationInWindow(location);
//			    if (isInScreen(location[0], location[1])){
//				    mImg.startAnimation(mUpAnimation);
//				    Log.d("mImg", "down");
//			    } else {
//				    mImg.clearAnimation();
//			    }
//
//			    location = new int[2];
//			    mImg2.getLocationInWindow(location);
//			    if (isInScreen(location[0], location[1])){
//				    mImg2.startAnimation(mUpAnimation);
//				    Log.d("mImg1", "down");
//			    } else {
//				    mImg2.clearAnimation();
//			    }
//
//			    location = new int[2];
//			    mImg3.getLocationInWindow(location);
//			    if (isInScreen(location[0], location[1])){
//				    mImg3.startAnimation(mUpAnimation);
//				    Log.d("mImg3", "down");
//			    } else {
//				    mImg3.clearAnimation();
//			    }
//
//			    location = new int[2];
//			    mImg1.getLocationOnScreen(location);
//			    int x = location[0];
//			    int y = location[1];
//			    mImg1.startAnimation(mDownAnimation);
		    }
	    });

	    btn2.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    test(mImg2);
//			    mImg.startAnimation(mUpAnimation);
//			    mImg1.startAnimation(mUpAnimation);
//			    mImg2.startAnimation(mDownAnimation);
//			    mImg3.startAnimation(mUpAnimation);
		    }
	    });

	    btn3.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    test(mImg3);
//			    mImg.startAnimation(mUpAnimation);
//			    mImg1.startAnimation(mUpAnimation);
//			    mImg2.startAnimation(mUpAnimation);
//			    mImg3.startAnimation(mDownAnimation);
		    }
	    });
    }

	View mCurrentView = null;
	View mOldView = null;

	private void test(View view) {
		if (view != mCurrentView) {
			Log.i("test", "down start[" + mDownAnimation.hasStarted() + "] end[" + mDownAnimation.hasEnded() + "] up hasStarted[" + mUpAnimation.hasStarted() + "] end[" + mUpAnimation.hasEnded() + "]");
			if (mDownAnimation.hasStarted() && !mDownAnimation.hasEnded()) {
			} else if (mUpAnimation.hasStarted() && !mUpAnimation.hasEnded()) {
			} else {
				mCurrentView = view;
				if (null != mOldView) {
					mOldView.startAnimation(mUpAnimation);
				} else {
					mOldView = mCurrentView;
					mCurrentView.startAnimation(mDownAnimation);
				}
			}
			mCurrentView = view;
		}
	}
	Animation.AnimationListener mUpAnimationListener = new Animation.AnimationListener() {
		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			mOldView.clearAnimation();
			mCurrentView.startAnimation(mDownAnimation);
			mOldView = mCurrentView;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}
	};

	Animation.AnimationListener mDownAnimationListener = new Animation.AnimationListener() {
		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			Log.i("test", "down onAnimationEnd down start[" + mDownAnimation.hasStarted() + "] end[" + mDownAnimation.hasEnded() + "] up hasStarted[" + mUpAnimation.hasStarted() + "] end[" + mUpAnimation.hasEnded() + "]");
			if (mOldView != mCurrentView) {
				mOldView.startAnimation(mUpAnimation);
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
