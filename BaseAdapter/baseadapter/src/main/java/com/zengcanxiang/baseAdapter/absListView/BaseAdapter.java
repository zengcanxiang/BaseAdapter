package com.zengcanxiang.baseAdapter.absListView;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <p>ListView和GridView万能适配Adapter,减少赘于代码和加快开发流程</p>
 *
 * @author zengcx
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected List<T> mList;
    protected Context mContext;
    protected LayoutInflater mLInflater;
    protected int[] layoutIds;
    private BaseViewHolder holder = new HelperViewHolder();
    private ViewGroup mParent;

    /**
     * @param data      数据源
     * @param context   上下文
     * @param layoutIds 布局Id
     */
    public BaseAdapter(@NonNull List<T> data, Context context, @NonNull @LayoutRes int... layoutIds) {
        this.mList = data;
        this.layoutIds = layoutIds;
        this.mContext = context;
        this.mLInflater = LayoutInflater.from(mContext);
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        mParent = parent;
        int layoutId = getViewCheckLayoutId(position);
        holder = holder.get(mContext, position, convertView, parent, layoutId);
        bindData(holder, position, mList.get(position));
        return holder.getConvertView(layoutId);
    }

    private int getViewCheckLayoutId(int position) {
        int layoutId;
        if (layoutIds == null || layoutIds.length == 0) {
            throw new IllegalArgumentException("not layoutId");
        } else {
            layoutId = getLayoutId(position);
        }
        return layoutId;
    }

    /**
     * <p>实现具体控件的获取和赋值等业务</p>
     *
     * @param viewHolder viewHolder
     * @param position   position
     * @param t          数据源中,当前对应的bean
     */
    public abstract <BH extends BaseViewHolder> void bindData(BH viewHolder, int position, T t);

    /**
     * <p>根据业务逻辑确定layoutId位置,使用在listView中有几种样式</p>
     *
     * @param position 所在位置
     * @param item     对应数据
     * @return 默认使用第一个, 返回下标, 从0开始
     */
    public int checkLayoutIndex(int position, T item) {
        return 0;
    }

    /**
     * 获取position对应的layoutId
     *
     * @param position 所在位置
     * @return layoutId
     */
    public int getLayoutId(int position) {
        return layoutIds[checkLayoutIndex(position, mList.get(position))];
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public final Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    /**
     * 实例化view,用于要添加到RecyclerView的View,不会导致设置的宽高不铺满
     */
    public View inflaterView(@LayoutRes int layoutId) {
        return mLInflater.inflate(layoutId, mParent, false);
    }

    public ViewGroup getParent() {
        return mParent;
    }
}
