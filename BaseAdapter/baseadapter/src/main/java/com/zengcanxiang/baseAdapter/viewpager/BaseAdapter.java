package com.zengcanxiang.baseAdapter.viewpager;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配viewPagerAdapter，减少赘于代码和加快开发流程
 *
 * @author zengcx
 */
public abstract class BaseAdapter extends PagerAdapter {

    private List<View> mViews;
    private Context mContext;


    public BaseAdapter(Context context, @NonNull List<View> views) {
        this.mContext = context;
        this.mViews = views;
    }

    public BaseAdapter(@NonNull List<Integer> viewIds, Context context) {
        this.mContext = context;
        this.mViews = id2Views(viewIds);
    }

    public BaseAdapter(Context context, @NonNull@LayoutRes int... viewIds) {
        this.mContext = context;
        this.mViews = id2Views(viewIds);
    }

    @Override
    public final int getCount() {
        return mViews == null ? 0 : mViews.size();
    }

    @Override
    public final boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public final Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        convert(mViews.get(position), position);
        return mViews.get(position);
    }

    private List<View> id2Views(@NonNull int... ids) {
        List<View> temp = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int id : ids) {
            View view = inflater.inflate(id, null);
            temp.add(view);
        }
        return temp;
    }

    private List<View> id2Views(@NonNull List<Integer> ids) {
        List<View> temp = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i : ids) {
            View view = inflater.inflate(ids.get(i), null);
            temp.add(view);
        }
        return temp;
    }

    public final Context getContext() {
        return mContext;
    }

    /**
     * 处理一些与view相关的
     * @param view 当前view
     * @param position 当前位置
     */
    public abstract void convert(View view, int position);
}
