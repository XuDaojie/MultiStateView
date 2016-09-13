package me.xdj.multistateview;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by xdj on 16/9/13.
 */

public class ActivityUtils {
    public static void addFragmentToActivity(@NonNull FragmentManager fm,
                                             @NonNull Fragment fragment, int contentId) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(contentId, fragment);
        ft.commit();
    }
}
