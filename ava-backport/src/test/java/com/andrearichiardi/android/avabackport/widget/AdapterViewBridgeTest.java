package com.andrearichiardi.android.avabackport.widget;

import static org.fest.assertions.api.Assertions.assertThat;

import org.fest.assertions.api.android.widget.ArrayAdapterAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.andrearichiardi.android.avabackport.widget.AdapterViewBridgeImpl1;

import android.R;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@RunWith(RobolectricTestRunner.class)
public class AdapterViewBridgeTest {

    ArrayAdapter<String> mTestAdapter;
    ArrayAdapterAssert mAssert;
    AdapterViewBridgeImpl1 mArrayAdapterBridge;
    ListView mListView;
    
    @Before
    public void setUp() throws Exception {
        mTestAdapter = new ArrayAdapter<String>(Robolectric.application,
                R.id.title, new String[] { new String("test"), new String("test2") });
        
        mListView = new ListView(Robolectric.application);
        mListView.setAdapter(mTestAdapter);
        
        mArrayAdapterBridge = new AdapterViewBridgeImpl1(mListView);
        
        mAssert = new ArrayAdapterAssert(mTestAdapter);
    }

    @Test
    public void verifyContext() {
        assertThat(mTestAdapter.getContext()).isSameAs(Robolectric.application);
    }
    
    @Test
    public void shouldListViewCountBeCorrect() {
        mAssert.hasCount(mListView.getCount());
    }

    @Test
    public void shouldGetHasDataChanged() {
        mArrayAdapterBridge.getDataChanged();
    }
    
    @Test
    public void shouldSetDataChanged() {
        mArrayAdapterBridge.setDataChanged(true);
        assertThat(mArrayAdapterBridge.getDataChanged()).isTrue();
    }
    
    @Test
    public void shouldSetItemCount() {
        mArrayAdapterBridge.setItemCount(3);
        assertThat(mListView.getCount()).isEqualTo(3);
    }
    
    @Test
    public void shouldGetOldItemCount() {
        mArrayAdapterBridge.getOldItemCount();
    }
    
    @Test
    public void shouldSetOldItemCount() {
        mArrayAdapterBridge.setOldItemCount(3);
        assertThat(mArrayAdapterBridge.getOldItemCount()).isEqualTo(3);
    }
    
    @Test
    public void shouldGetSelectedPosition() {
        mArrayAdapterBridge.getSelectedPosition();
    }
    
    @Test
    public void shouldSetSelectedPosition() {
        mArrayAdapterBridge.setSelectedPosition(3);
        assertThat(mArrayAdapterBridge.getSelectedPosition()).isEqualTo(3);
    }
    
    @Test
    public void shouldGetSelectedRowId() {
        mArrayAdapterBridge.getSelectedRowId();
    }
    
    @Test
    public void shouldSetSelectedRowId() {
        mArrayAdapterBridge.setSelectedRowId(3);
        assertThat(mArrayAdapterBridge.getSelectedRowId()).isEqualTo(3);
    }
    
    
    @Test
    public void shouldSetNextSelectedPosition() {
        mArrayAdapterBridge.setNextSelectedPosition(3);
        assertThat(mListView.getSelectedItemPosition()).isEqualTo(3);
    }
    
    @Test
    public void shouldCallCheckFocus() {
        mArrayAdapterBridge.checkFocus();
    }
    
    @Test
    public void shouldGetNeedSync() {
        mArrayAdapterBridge.getNeedSync();
    }
    
    @Test
    public void shouldSetNeedSync() {
        mArrayAdapterBridge.setNeedSync(true);
        assertThat(mArrayAdapterBridge.getNeedSync()).isTrue();
    }
    
    @Test
    public void shouldCallRememberSyncState() {
        mArrayAdapterBridge.rememberSyncState();
    }
    
    @Test
    public void shouldCallHandleDataChanged() {
        mArrayAdapterBridge.handleDataChanged();
    }
}
