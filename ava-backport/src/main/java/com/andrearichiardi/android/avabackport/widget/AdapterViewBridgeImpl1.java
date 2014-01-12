package com.andrearichiardi.android.avabackport.widget;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.widget.AdapterView;

/**
 * This Bridge tries to access to AdapterView fields, methods...
 * It will throw a RuntimeException if something is wrong.
 * @author Andrea Richiardi
 *
 */
class AdapterViewBridgeImpl1 implements AdapterViewBridge {

    protected final AdapterView<?> mInstance;
    private final Class<AdapterView> mClazz;
    
    protected Field mDataChanged;
    protected Method mCheckFocus;
    protected Field mItemCount; // Setter only because getCount() on the parent is accessible
    protected Field mOldItemCount;
    private Field mSelectedPosition;
    private Field mSelectedRowId;
    private Field mNextSelectedPosition;
    private Field mNextSelectedRowId;
    private Field mNeedSync;
    private Method mRememberSyncState;
    private Method mHandleDataChanged;
    
    AdapterViewBridgeImpl1(AdapterView<?> instance) {
        mClazz = AdapterView.class;
        mInstance = instance;
        
        // Fields
        try {
            mDataChanged = mClazz.getDeclaredField("mDataChanged");
            mDataChanged.setAccessible(true);
            mCheckFocus = mClazz.getDeclaredMethod("checkFocus");
            mCheckFocus.setAccessible(true);
            mItemCount = mClazz.getDeclaredField("mItemCount");
            mItemCount.setAccessible(true);
            mOldItemCount = mClazz.getDeclaredField("mOldItemCount");
            mOldItemCount.setAccessible(true);
            mSelectedPosition = mClazz.getDeclaredField("mSelectedPosition");
            mSelectedPosition.setAccessible(true);
            mSelectedRowId = mClazz.getDeclaredField("mSelectedRowId");
            mSelectedRowId.setAccessible(true);
            mNextSelectedPosition = mClazz.getDeclaredField("mNextSelectedPosition");
            mNextSelectedPosition.setAccessible(true);
            mNextSelectedRowId = mClazz.getDeclaredField("mNextSelectedRowId");
            mNextSelectedRowId.setAccessible(true);
            mNeedSync = mClazz.getDeclaredField("mNeedSync");
            mNeedSync.setAccessible(true);
            mRememberSyncState = mClazz.getDeclaredMethod("rememberSyncState");
            mRememberSyncState.setAccessible(true);
            mHandleDataChanged = mClazz.getDeclaredMethod("handleDataChanged");
            mHandleDataChanged.setAccessible(true);
            
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Fields
    @Override
    public boolean getDataChanged() {
        try {
            return mDataChanged.getBoolean(mInstance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public void setDataChanged(boolean dataChanged) {
        try {
            mDataChanged.set(mInstance, dataChanged);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int getOldItemCount() {
        try {
            return mOldItemCount.getInt(mInstance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public void setOldItemCount(int count) {
        try {
            mOldItemCount.set(mInstance, count);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Setter only because getCount() on the parent is accessible
    @Override
    public void setItemCount(int count) {
        try {
            mItemCount.set(mInstance, count);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int getSelectedPosition() {
        try {
            return mSelectedPosition.getInt(mInstance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public void setSelectedPosition(int position) {
        try {
            mSelectedPosition.set(mInstance, position);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Setter only because AdapterView.getSelectedItemPosition() returns this the mNextSelectedPosition field.
     * @see "https://android.googlesource.com/platform/frameworks/base/+/android-1.6_r1/core/java/android/widget/AdapterView.java" 
     */
    @Override
    public void setNextSelectedPosition(int position) {
        try {
            mNextSelectedPosition.set(mInstance, position);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public long getSelectedRowId() {
        try {
            return mSelectedRowId.getLong(mInstance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public void setSelectedRowId(long id) {
        try {
            mSelectedRowId.set(mInstance, id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Setter only because AdapterView.getSelectedItemId() returns this the mNextSelectedRowId field.
     * @see "https://android.googlesource.com/platform/frameworks/base/+/android-1.6_r1/core/java/android/widget/AdapterView.java" 
     */
    @Override
    public void setNextSelectedRowId(long id) {
        try {
            mNextSelectedRowId.set(mInstance, id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public boolean getNeedSync() {
        try {
            return mNeedSync.getBoolean(mInstance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setNeedSync(boolean needSync) {
        try {
            mNeedSync.set(mInstance, needSync);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Methods
    
    @Override
    public void checkFocus() {
        try {
            mCheckFocus.invoke(mInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void rememberSyncState() {
        try {
            mRememberSyncState.invoke(mInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleDataChanged() {
        try {
            mHandleDataChanged.invoke(mInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
