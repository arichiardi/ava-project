package com.andrearichiardi.android.avabackport.widget;

interface AdapterViewBridge {
    
    // Fields
    
    boolean getDataChanged();

    void setDataChanged(boolean dataChanged);
    
    int getOldItemCount();

    void setOldItemCount(int count);
    
    // Setter only because getCount() on the parent is accessible
    void setItemCount(int count);
    
    int getSelectedPosition();

    void setSelectedPosition(int position);
    
    /**
     * Setter only because AdapterView.getSelectedItemPosition() returns this the mNextSelectedPosition field.
     * @see "https://android.googlesource.com/platform/frameworks/base/+/android-1.6_r1/core/java/android/widget/AdapterView.java" 
     */
    void setNextSelectedPosition(int position);
    
    long getSelectedRowId();

    void setSelectedRowId(long id);
    
    /**
     * Setter only because AdapterView.getSelectedItemId() returns this the mNextSelectedRowId field.
     * @see "https://android.googlesource.com/platform/frameworks/base/+/android-1.6_r1/core/java/android/widget/AdapterView.java" 
     */
    void setNextSelectedRowId(long id);
    
    boolean getNeedSync();

    void setNeedSync(boolean needSync);
    
    // Methods
    
    void checkFocus();
    
    void rememberSyncState();

    void handleDataChanged();
}
