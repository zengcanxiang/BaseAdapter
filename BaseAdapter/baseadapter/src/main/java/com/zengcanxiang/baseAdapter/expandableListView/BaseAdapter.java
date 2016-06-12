package com.zengcanxiang.baseAdapter.expandableListView;

import android.content.Context;
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
 * @author zengcx
 */
public abstract class BaseAdapter<T> extends BaseExpandableListAdapter {

    protected List<List<T>> mList;
    protected Context mContext;
    protected LayoutInflater mLInflater;
    protected int[] groupLayoutIds;
    protected int[] childLayoutIds;
    private BaseViewHolder holder = new HelperViewHolder();


    /**
     * @param data           数据源
     * @param context        上下文
     * @param groupLayoutIds group布局Id数组
     * @param childLayoutIds child布局Id数组
     */
    public BaseAdapter(List<List<T>> data, Context context, int[] groupLayoutIds, int... childLayoutIds) {
        this.mList = data;
        this.groupLayoutIds = groupLayoutIds;
        this.childLayoutIds = childLayoutIds;
        this.mContext = context;
        this.mLInflater = LayoutInflater.from(mContext);
    }

    /**
     * 获取分组的个数
     */
    @Override
    public int getGroupCount() {
        return mList.size();
    }

    /**
     * 获取指定分组中的子选项的个数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).size();
    }

    /**
     * 获取指定的分组数据
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    /**
     * 获取指定分组中的指定子选项数据
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).get(childPosition);
    }

    /**
     * <p>获取指定分组的ID, 这个ID必须是唯一的</p>
     * <p>默认为position</p>
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * <p> 获取子选项的ID, 这个ID必须是唯一的</p>
     * <p>默认为position</p>
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
     *
     * @return
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
        if (groupLayoutIds == null || groupLayoutIds.length <= 0) {
            throw new ArrayIndexOutOfBoundsException("not groupLayoutId");
        }
        int groupLayoutId = groupLayoutIds[checkGroupLayoutIndex(groupPosition, mList.get(groupPosition))];
        holder = holder.get(mContext, groupPosition, convertView, parent, groupLayoutId);
        convertGroup(holder, groupPosition, mList.get(groupPosition));
        return holder.getConvertView(groupLayoutId);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (childLayoutIds == null || childLayoutIds.length <= 0) {
            throw new ArrayIndexOutOfBoundsException("not childLayoutId");
        }
        int childLayoutId = childLayoutIds[checkChildLayoutIndex(childPosition, mList.get(groupPosition).get(childPosition))];
        holder = holder.get(mContext, childPosition, convertView, parent, childLayoutId);
        convertChild(holder, groupPosition, childPosition, mList.get(groupPosition).get(childPosition));
        return holder.getConvertView(childLayoutId);
    }

    /**
     * <p>根据业务逻辑确定childLayoutId位置,使用在listView中有几种样式</p>
     *
     * @param childPosition 所在位置
     * @param item          对应子数据
     * @return 默认使用第一个, 返回下标, 从0开始
     */
    public int checkChildLayoutIndex(int childPosition, T item) {
        return 0;
    }

    /**
     * <p>根据业务逻辑确定groupLayoutId位置,使用在listView中有几种样式</p>
     *
     * @param groupLosition 所在位置
     * @param childs        对应的该组数据
     * @return 默认使用第一个, 返回下标, 从0开始
     */
    public int checkGroupLayoutIndex(int groupLosition, List<T> childs) {
        return 0;
    }

    /**
     * group布局相关具体代码
     *
     * @param viewHolder    viewHolder
     * @param groupPosition 当前group的index
     * @param childs        当前group的所有child数据
     */
    public abstract <BH extends BaseViewHolder> void convertGroup(BH viewHolder, int groupPosition, List<T> childs);

    /**
     * child布局相关具体代码
     *
     * @param viewHolder    viewHolder
     * @param groupPosition 当前child所属的group的index
     * @param childPosition 当前child的index
     * @param t             当前child所对应的数据
     */
    public abstract <BH extends BaseViewHolder> void convertChild(BH viewHolder, int groupPosition, int childPosition, T t);

}
