package com.zengcanxiang.baseAdapter.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zengcanxiang on 2016/1/6.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected List<T> mList;
    protected Context mContext;
    protected LayoutInflater mLInflater;
    private int[] mLayoutId;
    private SparseArray<View> mConvertViews = new SparseArray<View>();
    /**
     * @param data     数据源
     * @param context  上下文
     * @param layoutId 布局Id
     */
    public BaseRecyclerViewAdapter(List<T> data, Context context, int... layoutId) {
        this.mList = data;
        this.mLayoutId = layoutId;
        this.mContext = context;
        this.mLInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {
        return checkLayout(mList.get(position), position);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType<0||viewType>mLayoutId.length){
            throw new ArrayIndexOutOfBoundsException("checkLayout > LayoutId.length");
        }
        if(mLayoutId.length==0){
            throw new ArrayIndexOutOfBoundsException("not layoutId");
        }
        int layoutId=mLayoutId[viewType];
        View view=inflateItemView(layoutId,parent);
        BaseRecyclerViewHolder viewHolder = (BaseRecyclerViewHolder) view.getTag();
        if(viewHolder==null||viewHolder.getLayoutId()!=layoutId){
            viewHolder=new BaseRecyclerViewHolder(layoutId,view);
           return viewHolder;
        }
        return viewHolder;
    }

    /**
     * 解析布局资源
     *
     * @param viewGroup
     * @return
     */
    protected View inflateItemView(int layoutId,ViewGroup viewGroup) {
        View convertView=mConvertViews.get(layoutId);
        if(convertView==null){
            convertView= mLInflater.inflate(layoutId,
                    viewGroup, false);
        }
        return convertView;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        final T item = getItem(position);
        // 绑定数据
        onBindData(holder, position, item);
        //赋值相关事件,例如点击长按等
        setListener(mContext, holder, position, item);
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    /**
     * 追加单个数据
     *
     * @param item
     */
    public void addItem(T item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    /**
     * 追加数据集
     *
     * @param items
     */
    public void addItems(List<T> items) {
        mList.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据到列表头部
     *
     * @param item
     */
    public void addItemToHead(T item) {
        mList.add(0, item);
        notifyDataSetChanged();
    }

    /**
     * 添加数据集到列表头部
     *
     * @param items
     */
    public void addItemsToHead(List<T> items) {
        mList.addAll(0, items);
        notifyDataSetChanged();
    }

    /**
     * 移除第position条数据
     *
     * @param position
     */
    public void remove(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 移除某个数据项
     *
     * @param item
     */
    public void remove(T item) {
        mList.remove(item);
        notifyDataSetChanged();
    }

    /**
     * 移除所有数据
     */
    public void removeAll() {
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取指定位置的数据项
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mList.get(position);
    }

    /**
     * 绑定数据到Item View上
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected abstract void onBindData(BaseRecyclerViewHolder viewHolder, int position, T item);

    /**
     * 绑定相关事件,例如点击长按等,默认空实现
     *
     * @param context    上下文
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected void setListener(final Context context, BaseRecyclerViewHolder viewHolder, int position, T item) {

    }

    public int checkLayout(T item, int position) {
        return 0;
    }
}
