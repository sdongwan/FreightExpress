package com.highspace.hs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 带滚动监听的scrollview
 */
public class GradationScrollView extends ScrollView {

    public interface ScrollViewListener {

        void onScrollChanged(GradationScrollView scrollView, int x, int y,
                             int oldx, int oldy);

    }

    private ScrollViewListener mScrollViewListener = null;

    public GradationScrollView(Context context) {
        super(context);
    }

    public GradationScrollView(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
    }

    public GradationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setmScrollViewListener(ScrollViewListener mScrollViewListener) {
        this.mScrollViewListener = mScrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

}