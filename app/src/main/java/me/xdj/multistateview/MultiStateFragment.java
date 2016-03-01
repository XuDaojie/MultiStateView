package me.xdj.multistateview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.xdj.view.MultiStateView;

/**
 * Created by xdj on 16/2/5.
 */
public class MultiStateFragment extends Fragment {
    public static final int MODE_DEFAULT = 0;
    public static final int MODE_CUSTOM = 1;

    public static final String KEY_MODE = "mode";

    private int mMode;
    private Handler mHandler;
    private MultiStateView mMultiStateView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMode = getArguments().getInt(KEY_MODE);
        View view;
        if (mMode == MultiStateFragment.MODE_DEFAULT) {
            view = inflater.inflate(R.layout.fragment_content, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_content_custom, container, false);
        }
        mMultiStateView = (MultiStateView) view.findViewById(R.id.multi_state_view);
        mMultiStateView.getView(MultiStateView.VIEW_STATE_FAIL).findViewById(R.id.retry)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_FAIL);
                            }
                        }, 2000);
                    }
                });
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_FAIL);
            }
        }, 2000);
        Log.d("xdj", "onCreateView:" + this);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("xdj", "setUserVisibleHint:" + isVisibleToUser + this);
        if (isVisibleToUser) {

        }
    }

    public static MultiStateFragment newInstance(int mode) {
        MultiStateFragment fragment = new MultiStateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MODE, mode);
        fragment.setArguments(bundle);

        return fragment;
    }
}
