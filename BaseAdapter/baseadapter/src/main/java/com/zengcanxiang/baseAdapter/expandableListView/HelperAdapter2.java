package com.zengcanxiang.baseAdapter.expandableListView;

import android.content.Context;

import com.zengcanxiang.baseAdapter.absListView.BaseViewHolder;
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>提供便捷操作方法的expandableListView的baseAdapter</p>
 * <p>适用与父标题数据与子列表数据是 不在 同一个实体</p>
 *
 * @param <G>父标题实体泛型
 * @param <C>子列表数据泛型
 * @author zengcx
 */
public abstract class HelperAdapter2<G, C> extends BaseAdapter2<G, C> {

    public HelperAdapter2(List<G> groupData, List<List<C>> childData, Context context, int[] groupLayoutIds, int... childLayoutIds) {
        super(groupData, childData, context, groupLayoutIds, childLayoutIds);
    }

    @Override
    public <BH extends BaseViewHolder> void convertGroup(BH viewHolder, int groupPosition, G item, List<C> childs) {
        HelperViewHolder holder = (HelperViewHolder) viewHolder;
        HelpConvertGroup(holder, groupPosition, item, childs);
    }

    @Override
    public <BH extends BaseViewHolder> void convertChild(BH viewHolder, int groupPosition, int childPosition, C t) {
        HelperViewHolder holder = (HelperViewHolder) viewHolder;
        HelpConvertChild(holder, groupPosition, childPosition, t);
    }

    /**
     * <p>实现具体控件的获取和赋值等业务</p>
     */
    public abstract void HelpConvertGroup(HelperViewHolder viewHolder, int groupPosition, G item, List<C> childs);

    /**
     * <p>实现具体控件的获取和赋值等业务</p>
     */
    public abstract void HelpConvertChild(HelperViewHolder viewHolder, int groupPosition, int childPosition, C t);


    /**
     * 判断父标题有无数据
     */
    public boolean isGroupEnabled() {
        if (mGroupData == null) {
            return false;
        } else {
            if (mGroupData.size() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断所有子列表是否有数据
     */
    public boolean isChildEnabled() {
        if (mChildData == null) {
            return false;
        } else {
            if (mChildData.size() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断某个父标题对应的子列表是否有数据
     *
     * @param groupIndex 数据位置
     */
    public boolean isChildEnabledToGroupIndex(int groupIndex) {
        if (isChildEnabled()) {
            if (mChildData.get(groupIndex) == null) {
                return false;
            } else {
                if (mChildData.get(groupIndex).size() <= 0) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 整体添加多个数据
     *
     * @param startPosition 要添加的位置
     * @param groups        添加的父标题数据
     * @param childs        添加的子列表数据
     */
    public boolean addAllGroup(int startPosition, List<G> groups, List<List<C>> childs) {
        initListGroup();
        initListChild();
        boolean groupResult = mGroupData.addAll(startPosition < 0 ? 0 : startPosition, groups);
        boolean childResult = mChildData.addAll(startPosition < 0 ? 0 : startPosition, childs);
        if (groupResult && childResult) {
            notifyDataSetChanged();
        } else {
            mGroupData.removeAll(groups);
            mChildData.removeAll(childs);
        }
        return groupResult && childResult;
    }

    /**
     * 给一个子列表添加多个数据
     *
     * @param startPosition 要添加的位置
     * @param groupIndex    数据位置
     * @param childs        添加的子列表数据
     */
    public boolean addAllChild(int startPosition, int groupIndex, List<C> childs) {
        initListChild();
        initListGroup();
        boolean result = mChildData.get(groupIndex).addAll(startPosition < 0 ? 0 : startPosition, childs);
        notifyDataSetChanged();
        return result;
    }

    /**
     * 添加一组数据到头部
     *
     * @param group  父标题数据
     * @param childs 对应的子列表数据
     */
    public boolean addAGroupToHead(G group, List<C> childs) {
        return addAGroup(0, group, childs);
    }

    /**
     * 添加一个子列表数据到对应的子列表头部
     *
     * @param groupIndex 数据位置
     * @param child      子列表数据
     */
    public boolean addAChildToHead(int groupIndex, C child) {
        return addChilds(0, groupIndex, child);
    }

    /**
     * 添加一组数据到尾部
     *
     * @param group  父标题数据
     * @param childs 对应的子列表数据
     */
    public boolean addAGroupToLast(G group, List<C> childs) {
        return addAGroup(mGroupData.size() - 1, group, childs);
    }

    /**
     * 添加一个子列表数据到对应的子列表尾部
     *
     * @param groupIndex 数据位置
     * @param child      子列表数据
     */
    public boolean addChildToLast(int groupIndex, C child) {
        return addChilds(mChildData.get(groupIndex).size() - 1, groupIndex, child);
    }

    /**
     * 添加一组数据到列表中
     *
     * @param startPosition 要添加的位置
     * @param group         父标题数据
     * @param childs        对应的子列表数据
     */
    public boolean addAGroup(int startPosition, G group, List<C> childs) {
        List<List<C>> tempChild = new ArrayList<>();
        tempChild.add(childs);
        List<G> tempGroup = new ArrayList<>();
        tempGroup.add(group);
        return addAllGroup(startPosition, tempGroup, tempChild);
    }

    /**
     * 添加一个子列表数据到对应的子列表
     *
     * @param startPosition 要添加的位置
     * @param groupIndex    数据位置
     * @param child         子列表数据
     */
    public boolean addChilds(int startPosition, int groupIndex, C child) {
        List<C> tempChild = new ArrayList<>();
        tempChild.add(child);
        return addAllChild(startPosition, groupIndex, tempChild);
    }

    /**
     * remove数据
     *
     * @param groups 要删除的父标题数据集
     * @param childs 要删除的子列表数据集
     */
    public void remove(List<G> groups, List<List<C>> childs) {
        initListGroup();
        initListChild();
        mGroupData.removeAll(groups);
        mChildData.removeAll(childs);
        notifyDataSetChanged();
    }

    /**
     * remove指定位置的一组数据
     *
     * @param removePosition 数据位置
     */
    public void removeAGroupToIndex(int removePosition) {
        initListGroup();
        initListChild();
        mGroupData.remove(removePosition);
        mChildData.remove(removePosition);
        notifyDataSetChanged();
    }

    /**
     * remove一组数据
     *
     * @param removeGroup remove的父标题数据
     * @param removeChild remove的子列表数据
     */
    public void removeAGroup(G removeGroup, List<C> removeChild) {
        initListGroup();
        initListChild();
        mGroupData.remove(removeGroup);
        mChildData.remove(removeChild);
        notifyDataSetChanged();
    }

    /**
     * remove一个子列表里面的指定位置的子列表数据
     *
     * @param groupIndex     数据位置
     * @param removePosition 要remove的子列表数据位置
     */
    public void removeAChild(int groupIndex, int removePosition) {
        initListGroup();
        initListChild();
        mChildData.get(groupIndex).remove(removePosition);
        notifyDataSetChanged();
    }

    /**
     * remove一个指定内容的子列表数据
     *
     * @param groupIndex 数据位置
     * @param removeData remove数据
     */
    public boolean removeAChild(int groupIndex, C removeData) {
        throwException();
        boolean result = mChildData.get(groupIndex).remove(removeData);
        notifyDataSetChanged();
        return result;
    }

    /**
     * 替换一组数据
     *
     * @param startPosition 要替换的位置
     * @param aGroup        替换的父标题数据
     * @param childs        替换的子列表数据
     */
    public void alterAGroup(int startPosition, G aGroup, List<C> childs) {
        throwException();
        mGroupData.set(startPosition, aGroup);
        mChildData.set(startPosition, childs);
        notifyDataSetChanged();
    }

    /**
     * 替换一个子列表数据
     *
     * @param startPosition 要替换的位置
     * @param groupIndex    数据位置
     * @param child         替换的子列表数据
     */
    public void alterAChild(int startPosition, int groupIndex, C child) {
        throwException();
        mChildData.get(groupIndex).set(startPosition, child);
        notifyDataSetChanged();
    }

    /**
     * 比较查看父标题数据和子列表数据是否存在都数据列表中
     *
     * @param group  父标题数据
     * @param childs 子列表数据
     * @return 两者结果的并
     */
    public boolean containsAGroup(G group, List<C> childs) {
        initListChild();
        initListGroup();
        return mGroupData.contains(group) && mChildData.contains(childs);
    }

    /**
     * 比较查看是父标题数据否存在数据列表中
     *
     * @param group 父标题数据
     */
    public boolean containsAGroup(G group) {
        initListGroup();
        return mGroupData.contains(group);
    }

    /**
     * 比较查看是子列表数据否存在数据列表中
     *
     * @param groupIndex 数据位置
     * @param child      比较数据
     */
    public boolean containsAChilds(int groupIndex, C child) {
        throwException();
        return mChildData.get(groupIndex).contains(child);
    }

    /**
     * 替换所有数据
     *
     * @param groups 父标题数据集
     * @param childs 子列表数据集
     */
    public void replaceAll(List<G> groups, List<List<C>> childs) {
        initListGroup();
        initListChild();
        mGroupData.clear();
        mChildData.clear();
        addAllGroup(0, groups, childs);
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        initListGroup();
        initListChild();
        mGroupData.clear();
        mChildData.clear();
        notifyDataSetChanged();
    }

    private void initListGroup() {
        if (mGroupData == null) {
            mGroupData = new ArrayList<>();
        }
    }

    private void initListChild() {
        if (mChildData == null) {
            mChildData = new ArrayList<>();
        }
    }

    private void throwException() {
        if (mGroupData == null) {
            throw new IllegalArgumentException("mGroupData is null,cannot execute");
        }
        if (mChildData == null) {
            throw new IllegalArgumentException("mChildData is null,cannot execute");
        }
    }
}
