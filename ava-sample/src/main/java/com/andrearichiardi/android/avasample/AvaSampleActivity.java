package com.andrearichiardi.android.avasample;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.sample_layout);

        mFlipper=(AdapterViewFlipper)findViewById(R.id.flipper);
        mFlipper.setAdapter(new ArrayAdapter<String>(this, R.layout.sample_list_item, items));

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
        TypedArray arr = mFlipper.getResources().obtainTypedArray(R.attr.autoStart);
        boolean b = arr.getBoolean(R.styleable.AdapterViewFlipper_autoStart,false);
        arr.recycle();
        return b;
    }
    
    public int getFlipperFlipInterval() {
        TypedArray arr = mFlipper.getResources().obtainTypedArray(R.attr.flipInterval);
        int i = arr.getInteger(R.styleable.AdapterViewFlipper_flipInterval, 0);
        arr.recycle();
        return i;
    }
}