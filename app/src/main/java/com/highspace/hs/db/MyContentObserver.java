package com.highspace.hs.db;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/**
 * Created by Administrator on 2016/10/5.
 */

public class MyContentObserver extends ContentObserver {

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    private Cursor mCursor;

    public MyContentObserver(Handler handler, Cursor mCursor) {
        super(handler);
        this.mCursor = mCursor;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if(mCursor.isClosed()) {
            return;
        }

        mCursor.requery();
    }


}
