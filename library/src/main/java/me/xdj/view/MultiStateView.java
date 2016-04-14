package me.xdj.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by xdj on 16/2/3.
 * 多状态视图
 */
public class MultiStateView extends FrameLayout {

    public static final int VIEW_STATE_CONTENT = 10001;
    public static final int VIEW_STATE_LOADING = 10002;
    public static final int VIEW_STATE_EMPTY = 10003;
    public static final int VIEW_STATE_FAIL = 10004;

    private View mEmptyView;
    private View mContentView;
    private View mFailView;
    private View mLoadingView;

    private int mViewState = VIEW_STATE_CONTENT;
    /**
     * 预览时状态
     */
    private int mPreviewState = VIEW_STATE_CONTENT;

    public MultiStateView(Context context) {
        this(context, null);
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        if (isInEditMode()) {
//            return; // TODO 暂时用于解决Android Studio 1.5中预览时报错
//        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiStateView);
        int initState = typedArray.getInt(R.styleable.MultiStateView_msv_viewState, VIEW_STATE_LOADING);
        int rIdEmpty = typedArray.getResourceId(R.styleable.MultiStateView_msv_emptyView, -1);
        int rIdLoading = typedArray.getResourceId(R.styleable.MultiStateView_msv_loadingView, -1);
        int rIdFail = typedArray.getResourceId(R.styleable.MultiStateView_msv_failView, -1);

        mPreviewState = typedArray.getInt(R.styleable.MultiStateView_msv_previewState, VIEW_STATE_CONTENT);

        LayoutInflater inflater = LayoutInflater.from(context);

        if (rIdEmpty != -1) {
            mEmptyView = inflater.inflate(rIdEmpty, this, false);
        } else {
            mEmptyView = inflater.inflate(R.layout.msv_view_state_empty, this, false);
        }
        if (rIdFail != -1) {
            mFailView = inflater.inflate(rIdFail, this, false);
        } else {
            mFailView = inflater.inflate(R.layout.msv_view_state_fail, this, false);
        }
        if (rIdLoading != -1) {
            mLoadingView = inflater.inflate(rIdLoading, this, false);
        } else {
            mLoadingView = inflater.inflate(R.layout.msv_view_state_loading, this, false);
        }
        addView(mEmptyView, mEmptyView.getLayoutParams());
        addView(mFailView, mFailView.getLayoutParams());
        addView(mLoadingView, mLoadingView.getLayoutParams());

        typedArray.recycle();
        mViewState = initState;
//        if (!isInEditMode()) {
//            setViewState(initView);
//        } else {
//            setViewState(mPreviewState);
//        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 可以解决所有状态都显示出来的问题
        if (isInEditMode()) {
            // 预览模式下
            setViewState(mPreviewState);
        } else {
            setViewState(mViewState);
        }
    }

    @Override
    public void addView(View child) {
        if (isValidContentView(child)) {
            mContentView = child;
            if (mViewState != VIEW_STATE_CONTENT) {
                mContentView.setVisibility(GONE);
            }
        }
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (isValidContentView(child)) {
            mContentView = child;
            if (mViewState != VIEW_STATE_CONTENT) {
                mContentView.setVisibility(GONE);
            }
        }
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        if (isValidContentView(child)) {
            mContentView = child;
            if (mViewState != VIEW_STATE_CONTENT) {
                mContentView.setVisibility(GONE);
            }
        }
        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (isValidContentView(child)) {
            mContentView = child;
            if (mViewState != VIEW_STATE_CONTENT) {
                mContentView.setVisibility(GONE);
            }
        }
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (isValidContentView(child)) mContentView = child;
        super.addView(child, index, params);
    }

    /**
     * 设视图状态
     * @param state 状态类型
     */
    public void setViewState(int state) {
//        if (isInEditMode()) { // TODO 暂时用于解决Android Studio 中预览时报错
//            return;
//        }
        switch (state) {
            case VIEW_STATE_CONTENT:
                setViewStateDelay(state, 0);
                break;
            case VIEW_STATE_LOADING:
                setViewStateDelay(state, 0);
                break;
            case VIEW_STATE_EMPTY:
                setViewStateDelay(state, 0);
                break;
            case VIEW_STATE_FAIL:
                setViewStateDelay(state, 0);
                break;
            default:
                setViewStateDelay(state, 0);
                break;
        }
    }

    /**
     * 获取当前状态
     * @return 状态
     */
    public int getViewState() {
        return mViewState;
    }

    /**
     * 获取指定状态的View
     * @param state 状态类型
     * @return 指定状态的View
     */
    public View getView(int state) {
        switch (state) {
            case VIEW_STATE_CONTENT:
                return mContentView;
            case VIEW_STATE_LOADING:
                return mLoadingView;
            case VIEW_STATE_EMPTY:
                return mEmptyView;
            case VIEW_STATE_FAIL:
                return mFailView;
        }
        return null;
    }

    /**
     * 获取当前状态的View
     * @return 当前状态的View
     */
    public View getCurrentView() {
        return getView(mViewState);
    }

    /**
     *
     */
    private boolean isValidContentView(View view) {
        if (mContentView != null && mContentView != view) {
            return false;
        }
        return view != mLoadingView && view != mFailView && view != mEmptyView;
    }

    /**
     * 延迟改变状态
     * @param state 状态
     * @param milli 延迟数
     */
    public void setViewStateDelay(final int state, long milli) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (state) {
                    case VIEW_STATE_CONTENT:
                        // 在预览时mContentView可能为null
                        if(mContentView != null) mContentView.setVisibility(VISIBLE);
                        mLoadingView.setVisibility(GONE);
                        mFailView.setVisibility(GONE);
                        mEmptyView.setVisibility(GONE);
                        mViewState = VIEW_STATE_CONTENT;
                        break;
                    case VIEW_STATE_LOADING:
                        if(mContentView != null) mContentView.setVisibility(GONE);
                        mLoadingView.setVisibility(VISIBLE);
                        mFailView.setVisibility(GONE);
                        mEmptyView.setVisibility(GONE);
                        mViewState = VIEW_STATE_LOADING;
                        break;
                    case VIEW_STATE_EMPTY:
                        if(mContentView != null) mContentView.setVisibility(GONE);
                        mLoadingView.setVisibility(GONE);
                        mFailView.setVisibility(GONE);
                        mEmptyView.setVisibility(VISIBLE);
                        mViewState = VIEW_STATE_EMPTY;
                        break;
                    case VIEW_STATE_FAIL:
                        if(mContentView != null) mContentView.setVisibility(GONE);
                        mLoadingView.setVisibility(GONE);
                        mFailView.setVisibility(VISIBLE);
                        mEmptyView.setVisibility(GONE);
                        mViewState = VIEW_STATE_FAIL;
                        break;
                    default:
                        setViewState(VIEW_STATE_CONTENT);
                        break;
                }
            }
        }, milli);

    }
}
