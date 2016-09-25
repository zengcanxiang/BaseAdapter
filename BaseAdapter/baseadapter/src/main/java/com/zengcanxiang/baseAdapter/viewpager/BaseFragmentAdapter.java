package com.zengcanxiang.baseAdapter.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配viewPagerAdapter，减少赘于代码和加快开发流程
 *
 * @author zengcx
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();

    public BaseFragmentAdapter(@NonNull FragmentManager fm, @NonNull List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public final Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public final int getCount() {
        return mFragments.size();
    }
}
