package com.zengcanxiang.baseAdapter.expandableListView;

import android.content.Context;

import com.zengcanxiang.baseAdapter.absListView.BaseViewHolder;
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>提供便捷操作方法的expandableListView的baseAdapter</p>
 * <p>适用与父标题数据与子列表数据是在同一个实体</p>
 *
 * @author zengcx
 */
public abstract class HelperAdapter<T> extends BaseAdapter<T> {

    /**
     * @param data           数据源
     * @param context        上下文
     * @param groupLayoutIds group布局Id数组
     * @param childLayoutIds child布局Id数组
     */
    public HelperAdapter(List<List<T>> data, Context context, int[] groupLayoutIds, int... childLayoutIds) {
        super(data, context, groupLayoutIds, childLayoutIds);
    }

    @Override
    public <BH extends BaseViewHolder> void convertGroup(BH viewHolder, int groupPosition, List<T> childs) {
        HelperViewHolder holder = (HelperViewHolder) viewHolder;
        HelpConvertGroup(holder, groupPosition, childs);
    }

    @Override
    public <BH extends BaseViewHolder> void convertChild(BH viewHolder, int groupPosition, int childPosition, T t) {
        HelperViewHolder holder = (HelperViewHolder) viewHolder;
        HelpConvertChild(holder, groupPosition, childPosition, t);
    }

    /**
     * <p>实现具体控件的获取和赋值等业务</p>
     */
    public abstract void HelpConvertGroup(HelperViewHolder viewHolder, int groupPosition, List<T> childs);

    /**
     * <p>实现具体控件的获取和赋值等业务</p>
     */
    public abstract void HelpConvertChild(HelperViewHolder viewHolder, int groupPosition, int childPosition, T t);

    /**
     * 判断有无数据
     *
     * @return 判断结果
     */
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

    /**
     * 整体添加多个数据
     *
     * @param startPosition 要添加的位置
     * @param datas         数据集
     * @return 操作结果
     */
    public boolean addAll(int startPosition, List<List<T>> datas) {
        initList();
        boolean result = mList.addAll(startPosition < 0 ? 0 : startPosition, datas);
        notifyDataSetChanged();
        return result;
    }

    /**
     * 添加多个数据到头部
     *
     * @param addData 数据集
     */
    public void addAGroupToHead(List<T> addData) {
        addAGroup(0, addData);
    }

    /**
     * 添加多个数据到尾部
     *
     * @param addData 数据集
     */
    public void addAGroupToLast(List<T> addData) {
        initList();
        addAGroup(mList.size() - 1, addData);
    }

    /**
     * 添加一组数据
     *
     * @param startPosition 数据位置
     * @param addData       数据集
     * @return 操作结果
     */
    public boolean addAGroup(int startPosition, List<T> addData) {
        List<List<T>> temp = new ArrayList<>();
        temp.add(addData);
        return addAll(startPosition, temp);
    }

    /**
     * remove指定位置上的数据
     *
     * @param removePosition 指定位置
     */
    public void removeAGroup(int removePosition) {
        initList();
        mList.remove(removePosition);
        notifyDataSetChanged();
    }

    /**
     * remove指定数据
     *
     * @param removeData 指定数据
     * @return 操作结果
     */
    public boolean removeAGroup(List<T> removeData) {
        initList();
        boolean result = mList.remove(removeData);
        notifyDataSetChanged();
        return result;
    }

    /**
     * 替换一组数据
     *
     * @param startPosition 要替换的位置
     * @param aGroup        替换的数据
     */
    public void alterAGroup(int startPosition, List<T> aGroup) {
        initList();
        mList.set(startPosition, aGroup);
        notifyDataSetChanged();
    }

    /**
     * 添加一个子列表数据到对应的子列表
     *
     * @param startPosition 要添加的位置
     * @param groupIndex    groupIndex
     * @param child         子列表数据
     */
    public void addAChild(int startPosition, int groupIndex, T child) {
        throwException();
        mList.get(groupIndex).add(startPosition < 0 ? 0 : startPosition, child);
        notifyDataSetChanged();
    }

    /**
     * 添加一个子列表数据到对应的子列表头部
     *
     * @param groupIndex 数据位置
     * @param child      子列表数据
     */
    public void addAChildToHead(int groupIndex, T child) {
        throwException();
        addAChild(0, groupIndex, child);
    }

    /**
     * 添加一个子列表数据到对应的子列表尾部
     *
     * @param groupIndex 数据位置
     * @param child      子列表数据
     */
    public void addAChildToLast(int groupIndex, T child) {
        throwException();
        addAChild(mList.size() - 1, groupIndex, child);
    }

    /**
     * remove一个子列表里面的指定位置的子列表数据
     *
     * @param groupIndex     数据位置
     * @param removePosition 要remove的子列表数据位置
     */
    public void removeAChild(int groupIndex, int removePosition) {
        throwException();
        mList.get(groupIndex).remove(removePosition);
        notifyDataSetChanged();
    }

    /**
     * remove一个指定内容的子列表数据
     *
     * @param groupIndex 数据位置
     * @param removeData remove数据
     * @return 操作结果
     */
    public boolean removeAChild(int groupIndex, T removeData) {
        throwException();
        boolean result = mList.get(groupIndex).remove(removeData);
        notifyDataSetChanged();
        return result;
    }

    /**
     * 替换一个子列表数据
     *
     * @param startPosition 要替换的位置
     * @param groupIndex    数据位置
     * @param aChild        替换的子列表数据
     */
    public void alterAChild(int startPosition, int groupIndex, T aChild) {
        throwException();
        mList.get(groupIndex).set(startPosition, aChild);
        notifyDataSetChanged();
    }

    /**
     * 比较查看该组数据是否存在数据列表中
     *
     * @param data 数据集
     * @return 操作结果
     */
    public boolean containsAGroup(List<T> data) {
        initList();
        return mList.contains(data);
    }

    /**
     * 比较查看是子列表数据否存在数据列表中
     *
     * @param groupIndex 数据位置
     * @param data       比较数据
     * @return 操作结果
     */
    public boolean containsAChild(int groupIndex, T data) {
        throwException();
        return mList.get(groupIndex).contains(data);
    }

    /**
     * 替换所有数据
     *
     * @param datas 数据集
     */
    public void replaceAll(List<List<T>> datas) {
        initList();
        mList.clear();
        addAll(0, datas);
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        initList();
        mList.clear();
        notifyDataSetChanged();
    }

    private void initList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
    }

    private void throwException() {
        if (mList == null) {
            throw new IllegalArgumentException("list is null,cannot execute");
        }
    }
}
