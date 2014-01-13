package com.andrearichiardi.android.avasample;

import static org.fest.assertions.api.Assertions.assertThat;

import org.fest.assertions.api.android.widget.AdapterViewAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import com.andrearichiardi.android.avabackport.widget.AdapterViewFlipper;

@RunWith(RobolectricTestRunner.class)
public class AdapterViewFlipperTests {

    AdapterViewAssert mAssert;
    AvaSampleActivity mActivity;
    ActivityController<AvaSampleActivity> mController;
    AdapterViewFlipper mAdapterViewFlipper;
    
    @Before
    public void setUp() throws Exception {
        mController = Robolectric.buildActivity(AvaSampleActivity.class);
        mActivity = mController.create().start().resume().visible().get();
        mAdapterViewFlipper=(AdapterViewFlipper)mActivity.findViewById(R.id.flipper);
        mAssert = new AdapterViewAssert(mAdapterViewFlipper);
    }

    @After
    public void tearDown() throws Exception {
        mController = mController.pause().stop().destroy();
        mActivity = null;
    }
    
    @Test
    public void verifyContext() {
        assertThat(mAdapterViewFlipper.getContext()).isSameAs(Robolectric.application);
    }
    
    @Test
    public void shouldBeAdapterViewFlipper() {
        mAssert.isInstanceOf(AdapterViewFlipper.class);
    }
    
    @Test
    public void shouldFlipIntervalFalse() {
        int flipInterval = mActivity.getFlipperFlipInterval();
        assertThat(flipInterval).isEqualTo(5000);

    }
    
    @Test
    public void shouldAutostartBeTrue() {
        boolean b = mActivity.getFlipperAutostart();
        assertThat(b).isEqualTo(true);
    }
    
    @Test
    public void shouldLoopViewsBeTrue() {
        boolean b = mActivity.getFlipperLoopViews();
        assertThat(b).isEqualTo(true);
    }
    
    @Test
    public void shouldAnimateFirstTimeBeTrue() {
        boolean b = mActivity.getFlipperAutostart();
        assertThat(b).isEqualTo(true);
    }
}
