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

    public SimpleMultiStateView(Context context) {
        this(context, null);
    }

    public SimpleMultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleMultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleMultiStateView);
        int resIdEmpty = typedArray.getResourceId(R.styleable.SimpleMultiStateView_msv_emptyView, -1);
        int resIdLoading = typedArray.getResourceId(R.styleable.SimpleMultiStateView_msv_loadingView, -1);
        int resIdFail = typedArray.getResourceId(R.styleable.SimpleMultiStateView_msv_failView, -1);

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

}
