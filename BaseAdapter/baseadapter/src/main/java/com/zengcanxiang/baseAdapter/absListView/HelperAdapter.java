package com.zengcanxiang.baseAdapter.absListView;

import android.content.Context;

import com.zengcanxiang.baseAdapter.interFace.DataHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供便捷操作方法的ListView和GridView的baseAdapter
 *
 * @author zengcx
 */
public abstract class HelperAdapter<T> extends BaseAdapter<T> implements DataHelper<T> {

    public HelperAdapter(List<T> mList, Context context, int... layoutIds) {
        super(mList, context, layoutIds);
    }

    @Override
    public <BH extends BaseViewHolder> void bindData(BH viewHolder, int position, T t) {
        HelperViewHolder holder = (HelperViewHolder) viewHolder;
        HelperBindData(holder, position, t);
    }

    /**
     * <p>实现具体控件的获取和赋值等业务</p>
     */
    public abstract void HelperBindData(HelperViewHolder viewHolder, int position, T t);

    @Override
    public boolean isEnabled() {
        if (mList == null) {
            return false;
        } else {
            if (mList.size() <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addItemToHead(T data) {
        add(0, data);
    }


    @Override
    public boolean addItemToLast(T data) {
        if (!isEnabled()) {
            addItemToHead(data);
            return true;
        }
        boolean result = mList.add(data);
        notifyDataSetChanged();
        return result;
    }


    @Override
    public boolean addItemsToHead(List<T> datas) {
        return addAll(0, datas);
    }


    @Override
    public boolean addItemsToLast(List<T> datas) {
        if (!isEnabled()) {
            addItemsToHead(datas);
        }
        return addAll(mList.size() - 1, datas);
    }


    @Override
    public boolean addAll(int startPosition, List<T> datas) {
        initList();
        boolean result = mList.addAll(startPosition < 0 ? 0 : startPosition, datas);
        notifyDataSetChanged();
        return result;
    }


    @Override
    public void add(int startPosition, T data) {
        initList();
        mList.add(startPosition, data);
        notifyDataSetChanged();
    }

    @Override
    public T getData(int position) {
        return getCount() == 0 ? null : mList.get(position);
    }


    @Override
    public void alterObj(T oldData, T newData) {
        alterObj(mList.indexOf(oldData), newData);
    }


    @Override
    public void alterObj(int position, T data) {
        initList();
        mList.set(position, data);
        notifyDataSetChanged();
    }


    @Override
    public boolean remove(T data) {
        if (mList == null) {
            throw new IllegalArgumentException("list is null,cannot execute");
        }
        boolean result = mList.remove(data);
        notifyDataSetChanged();
        return result;
    }

    @Override
    public void removeToIndex(int position) {
        if (mList == null) {
            throw new IllegalArgumentException("list is null,cannot execute");
        }
        mList.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public void replaceAll(List<T> data) {
        initList();
        mList.clear();
        addAll(0, data);
    }

    @Override
    public void clear() {
        initList();
        mList.clear();
        notifyDataSetChanged();
    }


    @Override
    public boolean contains(T data) {
        initList();
        return mList.contains(data);
    }

    private void initList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
    }


}
