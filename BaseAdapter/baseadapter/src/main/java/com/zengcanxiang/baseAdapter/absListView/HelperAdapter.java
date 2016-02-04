package com.zengcanxiang.baseAdapter.absListView;

import android.content.Context;

import java.util.List;

/**
 * Created by zengcanxiang on 2016/1/28.
 */
public abstract class HelperAdapter<T> extends BaseAdapter<T> {

    public HelperAdapter(List<T> mList, Context context, int... layoutIds) {
        super(mList, context, layoutIds);
    }
    @Deprecated
    public HelperAdapter(List<T> mList, Context context) {
        super(mList, context);
    }

    public boolean isEnabled(int position) {
        return position < mList.size();
    }

    /**
     * 添加数据到指定位置
     * @param startPosition 数据添加的位置
     * @param data  数据
     */
    public void add(int startPosition, T data) {
        mList.add(startPosition, data);
        notifyDataSetChanged();
    }
    /**
     * 添加数据到最后一个位置
     * @param data  数据
     */
    public void addItemToLast(T data) {
        mList.add(data);
        notifyDataSetChanged();
    }
    /**
     * 添加单个数据到列表头部
     *
     * @param item
     */
    public void addItemToHead(T item) {
        add(0, item);
    }

    /**
     * 添加数据集到列表头部
     *
     * @param items
     */
    public void addItemsToHead(List<T> items) {
        addAll(0, items);
    }
    /**
     * 添加数据集合到指定位置
     * @param startPosition 数据添加的位置
     * @param data  数据集合
     */
    public void addAll(int startPosition, List<T> data) {
        mList.addAll(startPosition, data);
        notifyDataSetChanged();
    }

    /**
     * 将某一个数据修改
     * @param oldData 旧的数据
     * @param newData 新的数据
     */
    public void set(T oldData, T newData) {
        set(mList.indexOf(oldData), newData);
    }

    /**
     * 修改对应的位置的数据
     * @param index 修改的位置
     * @param data 要代替的的数据
     */
    public void set(int index, T data) {
        mList.set(index, data);
        notifyDataSetChanged();
    }

    /**
     * 删除对应的数据
     * @param data
     */
    public void remove(T data) {
        mList.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 删除对应位置的数据
     * @param index
     */
    public void removeToIndex(int index) {
        mList.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 替换所有数据
     * @param data
     */
    public void replaceAll(List<T> data) {
        mList.clear();
        addAll(0, data);
    }

    public boolean contains(T data) {
        return mList.contains(data);
    }

    /**
     * 清除所有
     */
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
