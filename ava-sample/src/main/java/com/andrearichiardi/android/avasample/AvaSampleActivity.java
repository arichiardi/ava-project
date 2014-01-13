package com.andrearichiardi.android.avasample;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.andrearichiardi.android.avabackport.widget.AdapterViewFlipper;

public class AvaSampleActivity extends Activity {

    static String[] items= { "lorem", "ipsum", "dolor", "sit", "amet",
        "consectetuer", "adipiscing", "elit", "morbi", "vel", "ligula",
        "vitae", "arcu", "aliquet", "mollis", "etiam", "vel", "erat",
        "placerat", "ante", "porttitor", "sodales", "pellentesque",
        "augue", "purus" };

    AdapterViewFlipper mFlipper;
    Button mButton;
    CheckBox mCheckBox;
    TextView mTextView;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //Debug.waitForDebugger();
        setContentView(R.layout.sample_layout);

        mFlipper=(AdapterViewFlipper)findViewById(R.id.flipper);
        mFlipper.setAdapter(new ArrayAdapter<String>(this, R.layout.sample_list_item, items));

        mTextView = (TextView)findViewById(R.id.flipIntervalText);
        mTextView.setText("Flip Interval: " + String.valueOf(getFlipperFlipInterval()));

        mButton=(Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlipper.advance();
            }
        });

        mCheckBox=(CheckBox)findViewById(R.id.checkbox);
        mCheckBox.setChecked(getFlipperAutostart());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) mFlipper.startFlipping(); else mFlipper.stopFlipping();
            }
        });


    }

    public boolean getFlipperAutostart() {
        boolean ret = false;
        try {
            Field autoStart = AdapterViewFlipper.class.getDeclaredField("mAutoStart");
            autoStart.setAccessible(true);
            ret  = autoStart.getBoolean(mFlipper);
        } catch (SecurityException e) {
            new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            new RuntimeException(e);
        } catch (IllegalAccessException e) {
            new RuntimeException(e);
        }
        return ret;
    }

    public int getFlipperFlipInterval() {
        int ret = 0;
        try {
            Field flipInterval = AdapterViewFlipper.class.getDeclaredField("mFlipInterval");
            flipInterval.setAccessible(true);
            ret = flipInterval.getInt(mFlipper);
        } catch (SecurityException e) {
            new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            new RuntimeException(e);
        } catch (IllegalAccessException e) {
            new RuntimeException(e);
        }
        return ret;
    }
    
    public boolean getFlipperAnimateFirstTime() {
        boolean ret = false;
        try {
            Field animateFirstTime = AdapterViewFlipper.class.getDeclaredField("mAnimateFirstTime");
            animateFirstTime.setAccessible(true);
            ret  = animateFirstTime.getBoolean(mFlipper);
        } catch (SecurityException e) {
            new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            new RuntimeException(e);
        } catch (IllegalAccessException e) {
            new RuntimeException(e);
        }
        return ret;
    }
    
    public boolean getFlipperLoopViews() {
        boolean ret = false;
        try {
            Field autoStart = AdapterViewFlipper.class.getDeclaredField("mLoopViews");
            autoStart.setAccessible(true);
            ret  = autoStart.getBoolean(mFlipper);
        } catch (SecurityException e) {
            new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            new RuntimeException(e);
        } catch (IllegalAccessException e) {
            new RuntimeException(e);
        }
        return ret;
    }
}