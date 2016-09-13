package me.xdj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by xdj on 16/2/3.
 * 多状态视图
 */
public class MultiStateView extends FrameLayout {

    private static final String TAG = MultiStateView.class.getSimpleName();

    public static final int STATE_CONTENT = 10001;
    public static final int STATE_LOADING = 10002;
    public static final int STATE_EMPTY = 10003;
    public static final int STATE_FAIL = 10004;

    private SparseArray<View> mStateViewArray = new SparseArray<>();
    private View mContentView;
    private int mCurrentState = STATE_CONTENT;

    public MultiStateView(Context context) {
        this(context, null);
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        validContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        validContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        validContentView(child);
        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, index, params);
    }

    /**
     * 改变视图状态
     *
     * @param state 状态类型
     */
    public void setViewState(int state) {
        if (state != mCurrentState) {
            View view = getView(state);
            if (view != null) {
                getCurrentView().setVisibility(GONE);
                view.setVisibility(VISIBLE);
                mCurrentState = state;
            } else {
                // TODO: 16/9/12 throw Exception
                Log.e(TAG, "state not found");
            }
        }
    }

    /**
     * 获取当前状态
     *
     * @return 状态
     */
    public int getViewState() {
        return mCurrentState;
    }

    /**
     * 获取指定状态的View
     *
     * @param state 状态类型
     * @return 指定状态的View
     */
    public View getView(int state) {
        return mStateViewArray.get(state);
    }

    /**
     * 获取当前状态的View
     *
     * @return 当前状态的View
     */
    public View getCurrentView() {
        return getView(mCurrentState);
    }

    public void addViewForStatus(int status, int resLayoutID) {
        View view = LayoutInflater.from(getContext()).inflate(resLayoutID, this, false);
        mStateViewArray.put(status, view);
        addView(view);
        view.setVisibility(GONE);
    }

    private boolean isValidContentView(View view) {
        if (mContentView == null) {
            for (int i = 0; i < mStateViewArray.size(); i++) {
                if (mStateViewArray.indexOfValue(view) != -1) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 检查当前view是否为content
     *
     * @param view
     */
    private void validContentView(View view) {
        if (isValidContentView(view)) {
            mContentView = view;
            mStateViewArray.put(STATE_CONTENT, view);
        } else if (mCurrentState != STATE_CONTENT) {
            mContentView.setVisibility(GONE);
        }
    }
}
