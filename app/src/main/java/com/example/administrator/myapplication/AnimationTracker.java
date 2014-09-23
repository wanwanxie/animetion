package com.example.administrator.myapplication;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaobin Wu on 2014/9/23.
 */
public class AnimationTracker {
	private final String TAG = AnimationTracker.class.getSimpleName();
	List<Entry> mData = new ArrayList<Entry>();
	private boolean isLastAnimationFinished = false;

	public static AnimationTracker newTracker() {
		return new AnimationTracker();
	}

	public AnimationTracker addViewEntry(Entry entry) {
		mData.add(entry);
		return this;
	}

	public void showIn() {
		for (int i = 0; i < mData.size(); i++) {
			isLastAnimationFinished = false;
			Entry item = mData.get(i);
			if (item.mView != null && item.mInAnimation != null) {
				if (i == mData.size() - 1) {
					item.mInAnimation.setAnimationListener(new Animation.AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							isLastAnimationFinished = true;
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
						}
					});
				}
				item.mView.startAnimation(item.mInAnimation);
				Log.d(TAG, "showIn animation start, view=" + item.mView.toString());
			}
		}
	}

	public boolean isAnimationFinished() {
		return isLastAnimationFinished;
	}

	public void flipOut() {
		isLastAnimationFinished = false;
		for (int i = 0; i < mData.size(); i++) {
			Entry item = mData.get(i);
			if (item.mView != null && item.mInAnimation != null) {
				item.mView.startAnimation(item.mOutAnimation);
				Log.d(TAG, "flipOut animation start view=" + item.mView.toString());
			}
		}
	}

	public void cancel() {
		for (Entry item : mData) {
			if (item.mView != null) {
				item.mView.clearAnimation();
			}
		}
	}

	public static class Entry {
		public View mView;
		public Animation mInAnimation;
		public Animation mOutAnimation;

		public Entry(View view, Animation in, Animation out) {
			mView = view;
			mInAnimation = in;
			mOutAnimation = out;
			mInAnimation.setFillAfter(true);
		}
	}

	public static void playAtIndex(int pageIndex, AnimationTracker... trackers) {
		for (int i = 0; i < trackers.length; i++) {
			if (i != pageIndex && trackers[i] != null) {
				if (trackers[i].isAnimationFinished()) {
					trackers[i].flipOut();
				} else {
					trackers[i].cancel();
				}
			}
		}
		if (trackers[pageIndex] != null) {
			trackers[pageIndex].showIn();
		}
	}
}
