/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andrearichiardi.android.avabackport.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Adapter;
import android.widget.RemoteViews.RemoteView;
import android.widget.ViewAnimator;


/**
 * Simple {@link ViewAnimator} that will animate between two or more views
 * that have been added to it.  Only one child is shown at a time.  If
 * requested, can automatically flip between each child at a regular interval.
 * <p>
 * Even if the class is marked with the RemoteView Annotation, it seems not to work inside App widgets.
 * Therefore the BroadcastReceiver feature has been commented out.
 * 
 * @attr ref R.styleable#AdapterViewFlipper_flipInterval
 * @attr ref R.styleable#AdapterViewFlipper_autoStart
 */
@RemoteView
public class AdapterViewFlipper extends AdapterViewAnimator {
    private static final String TAG = "com.andrearichiardi.android.widget.AdapterViewFlipper";
    private static final boolean LOGD = false;

    private static final int DEFAULT_INTERVAL = 10000;

    private int mFlipInterval = DEFAULT_INTERVAL;
    private boolean mAutoStart = false;

    private boolean mRunning = false;
    private boolean mStarted = false;
    private boolean mVisible = false;
    private boolean mUserPresent = true;
    private boolean mAdvancedByHost = false;

    public AdapterViewFlipper(Context context) {
        super(context);
    }

    /**
     * Interesting note for Android developers follows.
     * <p>
     * The only way to correctly read attributes' value was to iterate on the
     * {@link android.util.AttributeSet} and compare the result of <code>getAttributeName()</code>
     * with the actual name of the attribute itself from inside this constructor.
     * The normal <code>obtainStyledAttributes(attrs, R.styleable.AdapterViewFlipper)</code>
     * did not work.
     * <p>
     * While using the following:
     * <code>
     * int count = attrs.getAttributeCount();<br/>
     * for(int i = 0; i < count; i++){<br/>
     * &nbspString attrName = attrs.getAttributeName(i);<br/>
     * &nbspint attrRes = attrs.getAttributeNameResource(i);<br/>
     * &nbspLog.v(TAG, "#" + i + " " + attrName + " " + attrRes);<br/>
     * }<br/>
     * </code>
     * I had:
     * <code>
     * 01-13 03:01:11.625: V/AdapterViewFlipper(1236): #0 gravity 16842927<br/>
     * 01-13 03:01:11.625: V/AdapterViewFlipper(1236): #1 id 16842960<br/>
     * 01-13 03:01:11.625: V/AdapterViewFlipper(1236): #2 layout_width 16842996<br/>
     * 01-13 03:01:11.635: V/AdapterViewFlipper(1236): #3 layout_height 16842997<br/>
     * 01-13 03:01:11.635: V/AdapterViewFlipper(1236): #4 layout_centerHorizontal 16843152<br/>
     * 01-13 03:01:11.635: V/AdapterViewFlipper(1236): #5 flipInterval 0<br/>
     * 01-13 03:01:11.635: V/AdapterViewFlipper(1236): #6 autoStart 0<br/>
     * </code>
     */
    public AdapterViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        int count = attrs.getAttributeCount();
        for(int i = 0; i < count; i++) {
            String attrName = attrs.getAttributeName(i);
            int attrRes = attrs.getAttributeNameResource(i);
            Log.v(TAG, "#" + i + " " + attrName + " " + attrRes);
            if (attrName.equals("flipInterval")) {
                mFlipInterval = attrs.getAttributeIntValue(i, DEFAULT_INTERVAL);
            } else if (attrName.equals("autoStart")) {
                mAutoStart = attrs.getAttributeBooleanValue(i, false);
            }
         }

        // A view flipper should cycle through the views
        mLoopViews = true;
    }

    /* AR - feature commented out
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                mUserPresent = false;
                updateRunning();
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                mUserPresent = true;
                updateRunning(false);
            }
        }
    };*/

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // Listen for broadcasts related to user-presence
        // AR - feature commented out.
        //final IntentFilter filter = new IntentFilter();
        //filter.addAction(Intent.ACTION_SCREEN_OFF);
        //filter.addAction(Intent.ACTION_USER_PRESENT);
        //getContext().registerReceiver(mReceiver, filter);

        if (mAutoStart) {
            // Automatically start when requested
            startFlipping();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVisible = false;
        // AR - feature commented out.
        //getContext().unregisterReceiver(mReceiver);
        updateRunning();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mVisible = (visibility == VISIBLE);
        updateRunning(false);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        updateRunning();
    }

    /**
     * How long to wait before flipping to the next view
     *
     * @param milliseconds
     *            time in milliseconds
     */
    public void setFlipInterval(int milliseconds) {
        mFlipInterval = milliseconds;
    }

    /**
     * Start a timer to cycle through child views
     */
    public void startFlipping() {
        mStarted = true;
        updateRunning();
    }

    /**
     * No more flips
     */
    public void stopFlipping() {
        mStarted = false;
        updateRunning();
    }

    /**
    * {@inheritDoc}
    */
   @Override
   public void showNext() {
       // if the flipper is currently flipping automatically, and showNext() is called
       // we should we should make sure to reset the timer
       if (mRunning) {
           mHandler.removeMessages(FLIP_MSG);
           Message msg = mHandler.obtainMessage(FLIP_MSG);
           mHandler.sendMessageDelayed(msg, mFlipInterval);
       }
       super.showNext();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void showPrevious() {
       // if the flipper is currently flipping automatically, and showPrevious() is called
       // we should we should make sure to reset the timer
       if (mRunning) {
           mHandler.removeMessages(FLIP_MSG);
           Message msg = mHandler.obtainMessage(FLIP_MSG);
           mHandler.sendMessageDelayed(msg, mFlipInterval);
       }
       super.showPrevious();
   }

    /**
     * Internal method to start or stop dispatching flip {@link Message} based
     * on {@link #mRunning} and {@link #mVisible} state.
     */
    private void updateRunning() {
        // by default when we update running, we want the
        // current view to animate in
        updateRunning(true);
    }

    /**
     * Internal method to start or stop dispatching flip {@link Message} based
     * on {@link #mRunning} and {@link #mVisible} state.
     *
     * @param flipNow Determines whether or not to execute the animation now, in
     *            addition to queuing future flips. If omitted, defaults to
     *            true.
     */
    private void updateRunning(boolean flipNow) {
        boolean running = !mAdvancedByHost && mVisible && mStarted && mUserPresent
                && mAdapter != null;
        if (running != mRunning) {
            if (running) {
                showOnly(mWhichChild, flipNow);
                Message msg = mHandler.obtainMessage(FLIP_MSG);
                mHandler.sendMessageDelayed(msg, mFlipInterval);
            } else {
                mHandler.removeMessages(FLIP_MSG);
            }
            mRunning = running;
        }
        if (LOGD) {
            Log.d(TAG, "updateRunning() mVisible=" + mVisible + ", mStarted=" + mStarted
                    + ", mUserPresent=" + mUserPresent + ", mRunning=" + mRunning);
        }
    }

    /**
     * Returns true if the child views are flipping.
     * @return True or false.
     */
    public boolean isFlipping() {
        return mStarted;
    }

    /**
     * Set if this view automatically calls {@link #startFlipping()} when it
     * becomes attached to a window.
     * @param autoStart True or false.
     */
    public void setAutoStart(boolean autoStart) {
        mAutoStart = autoStart;
    }

    /**
     * Returns true if this view automatically calls {@link #startFlipping()}
     * when it becomes attached to a window.
     * @return True or false.
     */
    public boolean isAutoStart() {
        return mAutoStart;
    }

    private final int FLIP_MSG = 1;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == FLIP_MSG) {
                if (mRunning) {
                    showNext();
                }
            }
        }
    };

    /**
     * Called by an {@link android.appwidget.AppWidgetHost} to indicate that it will be
     * automatically advancing the views of this {@link AdapterViewFlipper} by calling
     * {@link AdapterViewFlipper#advance()} at some point in the future. This allows
     * {@link AdapterViewFlipper} to prepare by no longer Advancing its children.
     */
    @Override
    public void fyiWillBeAdvancedByHostKThx() {
        mAdvancedByHost = true;
        updateRunning(false);
    }
}