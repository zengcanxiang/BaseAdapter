package com.zengcanxiang.baseAdapter.expandableListView;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.zengcanxiang.baseAdapter.absListView.BaseViewHolder;
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;

import java.util.List;

/**
 * <p>ExpandableListView万能适配Adapter,减少赘于代码和加快开发流程</p>
 *
 * @param <G>父标题实体泛型
 * @param <C>子列表数据泛型
 * @author zengcx
 */
public abstract class BaseAdapter2<G, C> extends BaseExpandableListAdapter {

    protected List<List<C>> mChildData;
    protected List<G> mGroupData;
    protected Context mContext;
    private LayoutInflater mLInflater;
    private int[] groupLayoutIds;
    private int[] childLayoutIds;
    private BaseViewHolder holder = new HelperViewHolder();
    private ViewGroup mGroupParent, mChildParent;

    public BaseAdapter2(@Nullable List<G> groupData, List<List<C>> childData, Context context, @NonNull @LayoutRes int[] groupLayoutIds, @NonNull @LayoutRes int... childLayoutIds) {
        this.mChildData = childData;
        this.mGroupData = groupData;
        this.groupLayoutIds = groupLayoutIds;
        this.childLayoutIds = childLayoutIds;
        this.mContext = context;
        this.mLInflater = LayoutInflater.from(mContext);
    }

    /**
     * 获取分组的个数
     */
    @Override
    public final int getGroupCount() {
        return mGroupData.size();
    }

    /**
     * 获取指定分组中的子选项的个数
     */
    @Override
    public final int getChildrenCount(int groupPosition) {
        return mChildData.get(groupPosition).size();
    }

    /**
     * 获取指定的分组数据
     */
    @Override
    public final Object getGroup(int groupPosition) {
        return mGroupData.get(groupPosition);
    }

    /**
     * 获取指定分组中的指定子选项数据
     */
    @Override
    public final Object getChild(int groupPosition, int childPosition) {
        return mChildData.get(groupPosition).get(childPosition);
    }

    /**
     * <p>获取指定分组的ID, 这个ID必须是唯一的</p>
     * <p>默认为position</p>
     */
    @Override
    public final long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * <p> 获取子选项的ID, 这个ID必须是唯一的</p>
     * <p>默认为position</p>
     */
    @Override
    public final long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 指定位置上的子元素是否可选中
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (groupLayoutIds == null || groupLayoutIds.length == 0) {
            throw new IllegalArgumentException("not groupLayoutId");
        }
        mGroupParent = parent;
        int groupLayoutId = groupLayoutIds[checkGroupLayoutIndex(groupPosition,
                mGroupData.get(groupPosition), mChildData.get(groupPosition))];
        holder = holder.get(mContext, groupPosition, convertView, parent, groupLayoutId);
        convertGroup(holder, groupPosition, mGroupData.get(groupPosition), mChildData.get(groupPosition));
        return holder.getConvertView(groupLayoutId);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (childLayoutIds == null || childLayoutIds.length == 0) {
            throw new IllegalArgumentException("not childLayoutId");
        }
        mChildParent = parent;
        int childLayoutId = childLayoutIds[checkChildLayoutIndex(childPosition,
                mChildData.get(groupPosition).get(childPosition))];
        holder = holder.get(mContext, childPosition, convertView, parent, childLayoutId);
        convertChild(holder, groupPosition, childPosition, mChildData.get(groupPosition).get(childPosition));
        return holder.getConvertView(childLayoutId);
    }

    /**
     * <p>根据业务逻辑确定childLayoutId位置,使用在listView中有几种样式</p>
     *
     * @param childPosition 所在位置
     * @param item          对应子数据
     * @return 默认使用第一个, 返回下标, 从0开始
     */
    public int checkChildLayoutIndex(int childPosition, C item) {
        return 0;
    }

    /**
     * <p>根据业务逻辑确定groupLayoutId位置,使用在listView中有几种样式</p>
     *
     * @param groupLosition 所在位置
     * @param item          对应的group数据
     * @param childs        对应的该组数据
     * @return 默认使用第一个, 返回下标, 从0开始
     */
    public int checkGroupLayoutIndex(int groupLosition, G item, List<C> childs) {
        return 0;
    }

    public abstract <BH extends BaseViewHolder> void convertGroup(BH viewHolder, int groupPosition, G item, List<C> childs);

    public abstract <BH extends BaseViewHolder> void convertChild(BH viewHolder, int groupPosition, int childPosition, C t);

    public ViewGroup getGroupParent() {
        return mGroupParent;
    }

    public ViewGroup getChildParent() {
        return mChildParent;
    }
}
