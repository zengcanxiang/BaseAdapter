package com.zengcanxiang.baseAdapter.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;

public class BaseAdapter extends PagerAdapter {

    public BaseAdapter(){

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
