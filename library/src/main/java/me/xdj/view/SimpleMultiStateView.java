package me.xdj.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * Created by xdj on 16/2/3.
 * 多状态视图
 */
public class SimpleMultiStateView extends MultiStateView {

    private static final String TAG = SimpleMultiStateView.class.getSimpleName();

    private static final int MIN_SHOW_TIME = 600; // ms
    private static final int MIN_DELAY = 600; // ms

    private int mTargetState = -1;
    private long mLoadingStartTime = -1;

    private final Runnable mLoadingHide = new Runnable() {
        @Override
        public void run() {
            setViewState(mTargetState);
            mLoadingStartTime = -1;
            mTargetState = -1;
        }
    };

    public SimpleMultiStateView(Context context) {
        this(context, null);
    }

    public SimpleMultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleMultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.msv_SimpleMultiStateView);
        int resIdEmpty = typedArray.getResourceId(R.styleable.msv_SimpleMultiStateView_msv_emptyView, -1);
        int resIdLoading = typedArray.getResourceId(R.styleable.msv_SimpleMultiStateView_msv_loadingView, -1);
        int resIdFail = typedArray.getResourceId(R.styleable.msv_SimpleMultiStateView_msv_failView, -1);

        if (resIdEmpty != -1) {
            addViewForStatus(MultiStateView.STATE_EMPTY, resIdEmpty);
        }
        if (resIdFail != -1) {
            addViewForStatus(MultiStateView.STATE_FAIL, resIdFail);
        }
        if (resIdLoading != -1) {
            addViewForStatus(MultiStateView.STATE_LOADING, resIdLoading);
        }

        typedArray.recycle();
    }

//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        removeCallbacks(mLoadingHide);
//    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mLoadingHide);
    }

    @Override
    public void setViewState(int state) {
        if (getViewState() == STATE_LOADING && state != STATE_LOADING) {
            long diff = System.currentTimeMillis() - mLoadingStartTime;
            if (diff < MIN_SHOW_TIME) {
                mTargetState = state;
                postDelayed(mLoadingHide, MIN_DELAY);
            } else {
                super.setViewState(state);
            }
            return;
        } else if (state == STATE_LOADING) {
            mLoadingStartTime = System.currentTimeMillis();
        }
        super.setViewState(state);
    }

}
