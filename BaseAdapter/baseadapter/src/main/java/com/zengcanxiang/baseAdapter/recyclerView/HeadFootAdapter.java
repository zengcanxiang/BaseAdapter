package com.zengcanxiang.baseAdapter.recyclerView;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 支持headView,footerView的RecyclerViewAdapter
 *
 * @author zengcx
 */
public abstract class HeadFootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int BASE_ID = -1;
    private final int BASE_ITEM_TYPE_FOOTER = 2000000;
    private int poolCacheSize = 5;
    private RecyclerView.Adapter mDataAdapter;
    private LayoutInflater mLInflater;
    private ViewGroup mParent;
    private ArrayList<Integer> mHeadLayouts = new ArrayList<>();
    private SparseArray<View> mFootViews = new SparseArray<>();

    private RecyclerView.LayoutManager mLayoutManager;

    public HeadFootAdapter(RecyclerView.Adapter dataAdapter) {
        this.mDataAdapter = dataAdapter;
        /**
         * dataAdapter刷新变化监听
         */
        RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                notifyItemRangeChanged(data2HeadPosition(positionStart), itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                notifyItemRangeChanged(data2HeadPosition(positionStart), itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                notifyItemRangeInserted(data2HeadPosition(positionStart), itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                notifyItemRangeRemoved(data2HeadPosition(positionStart), itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                notifyItemRangeChanged(data2HeadPosition(fromPosition),
                        data2HeadPosition(toPosition) + itemCount);
            }
        };
        this.mDataAdapter.registerAdapterDataObserver(mDataObserver);
        setHasStableIds(mDataAdapter.hasStableIds());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (!mHeadLayouts.isEmpty()) {
            for (int i = 0; i < mHeadLayouts.size(); i++) {
                recyclerView.getRecycledViewPool().setMaxRecycledViews(mHeadLayouts.get(i), poolCacheSize);
            }
        }
        mLayoutManager = recyclerView.getLayoutManager();
        initLayoutManager();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        if (isHead(position) || isFoot(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
        mDataAdapter.onViewAttachedToWindow(holder);
    }

    /**
     * 初始化布局管理器,处理GridLayoutManager,StaggeredGridLayoutManager没有占满一行
     */
    private void initLayoutManager() {
        if (mLayoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isHead(position) || isFoot(position)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return spanSizeLookup.getSpanSize(head2DataPosition(position));
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mParent = parent;
        mLInflater = LayoutInflater.from(parent.getContext());

        if (mFootViews.get(viewType) != null) {
            View footView = mFootViews.get(viewType);
            return HeadFootViewHolder.get(parent.getContext(), footView, parent, viewType);
        } else if (!mHeadLayouts.isEmpty()) {
            for (int i = 0; i < mHeadLayouts.size(); i++) {
                //以layoutId为viewType
                if (mHeadLayouts.get(i) == viewType) {
                    return  HeadFootViewHolder.get(parent.getContext(),
                            null, parent, viewType);
                }
            }
        }
        return mDataAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadFootViewHolder) {
            if (isHead(position)) {
                disposeHeadView((HelperViewHolder) holder, mHeadLayouts.get(position), position);
            } else if (isFoot(position)) {
                disposeFootView((HelperViewHolder) holder, mFootViews.get(position), position);
            }
        } else {
            mDataAdapter.onBindViewHolder(holder, head2DataPosition(position));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (holder instanceof HeadFootViewHolder) {
            if (isHead(position)) {
                disposeHeadView((HelperViewHolder) holder, mHeadLayouts.get(position), position);
            } else if (isFoot(position)) {
                disposeFootView((HelperViewHolder) holder, mFootViews.get(position), position);
            }
        } else {
            mDataAdapter.onBindViewHolder(holder, head2DataPosition(position), payloads);
        }
    }


    @Override
    public int getItemCount() {
        int itemCount = mDataAdapter.getItemCount();
        if (hasHeadView()) {
            itemCount += getHeadSize();
        }
        if (hasFootView()) {
            itemCount += getFootSize();
        }
        return itemCount;
    }

    @Override
    public long getItemId(int position) {
        if (isHead(position)) {
            return BASE_ID + 1 + position;
        } else if (isFoot(position)) {
            return BASE_ID - 1 - position;
        } else {
            return mDataAdapter.getItemId(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHead(position)) {
            return mHeadLayouts.get(position);
        } else if (isFoot(position)) {
            return mFootViews.keyAt(position - getHeadSize() - mDataAdapter.getItemCount());
        } else {
            return mDataAdapter.getItemViewType(head2DataPosition(position));
        }
    }

    /**
     * 判断是否为headView
     */
    private boolean isHead(int position) {
        return hasHeadView() && getHeadSize() > position;
    }

    /**
     * 判断是否为footView
     */
    private boolean isFoot(int position) {
        return hasFootView() && position >= (getHeadSize() + mDataAdapter.getItemCount());
    }

    /**
     * 判断是否有headView
     */
    public boolean hasHeadView() {
        return getHeadSize() != 0;
    }

    /**
     * 判断是否有footView
     */
    public boolean hasFootView() {
        return mFootViews.size() != 0;
    }


    /**
     * 获取headView数量
     */
    public int getHeadSize() {
        return mHeadLayouts.size();
    }

    /**
     * 获取footView数量
     */
    public int getFootSize() {
        return mFootViews.size();
    }

    /**
     * 添加headView
     */
    public void addHeadView(@LayoutRes int layoutId) {
        mHeadLayouts.add(layoutId);
        notifyItemInserted(mHeadLayouts.size() - 1);
    }

    /**
     * 批量添加headView
     */
    public void addHeadView(ArrayList<Integer> layoutIds) {
        int oldSize = mHeadLayouts.size();
        mHeadLayouts.addAll(layoutIds);
        notifyItemRangeInserted(oldSize - 1, layoutIds.size());
    }

    /**
     * 批量添加headView
     */
    public void addHeadView(@LayoutRes Integer[] layoutIds) {
        int oldSize = mHeadLayouts.size();
        mHeadLayouts.addAll(Arrays.asList(layoutIds));
        notifyItemRangeInserted(oldSize - 1, layoutIds.length);
    }

    /**
     * 添加headView,可指定位置
     */
    public void addHeadView(int index, @LayoutRes int layoutId) {
        if (index < 0) {
            index = 0;
        }
        index = index > mHeadLayouts.size() ? mHeadLayouts.size() : index;
        mHeadLayouts.add(index, layoutId);
        notifyItemInserted(index);
    }

    /**
     * 批量添加headView,可指定位置
     */
    public void addHeadView(int index, ArrayList<Integer> layoutIds) {
        if (index < 0) {
            index = 0;
        }
        index = index > mHeadLayouts.size() ? mHeadLayouts.size() : index;
        mHeadLayouts.addAll(index, layoutIds);
        notifyItemRangeInserted(index, layoutIds.size());
    }

    /**
     * 批量添加headView,可指定位置
     */
    public void addHeadView(int index, @LayoutRes Integer[] layoutIds) {
        if (index < 0) {
            index = 0;
        }
        index = index > mHeadLayouts.size() ? mHeadLayouts.size() : index;
        mHeadLayouts.addAll(index, Arrays.asList(layoutIds));
        notifyItemRangeInserted(index, layoutIds.length);
    }

    /**
     * 添加footView(不能通过layoutId来删除,因为内部使用了一个数字来代替未知的layoutId)
     */
    public void addFootView(View footView) {
        addFootView(mFootViews.size(), footView);
    }

    /**
     * 添加footView(可通过key或者footView来删除)
     *
     * @throws IllegalArgumentException 该方法不能重复添加相同key的footView
     */
    public void addFootView(int key, View footView) {
        int keyTemp = key + BASE_ITEM_TYPE_FOOTER;
        if (mFootViews.indexOfKey(keyTemp) >= 0) {
            throw new IllegalArgumentException("have the same key.\n重复添加相同的layoutId为key的footView.");
        }
        mFootViews.put(keyTemp, footView);
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * 添加footView(可通过layoutId或者footView来删除)
     *
     * @throws IllegalArgumentException 该方法不能重复添加相同key的footView
     */
    public void addFootView(@LayoutRes int layoutId) {
        addFootView(layoutId, inflaterView(layoutId));
    }

    /**
     * 根据位置删除headView
     */
    public void removeHeadViewByIndex(int index) {
        if (index < 0) {
            index = 0;
        } else if (index > mHeadLayouts.size()) {
            index = mHeadLayouts.size() - 1;
        }
        mHeadLayouts.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 根据位置删除footView
     */
    public void removeFootViewByIndex(int index) {
        if (index < 0) {
            index = 0;
        } else if (index > mFootViews.size()) {
            index = mFootViews.size() - 1;
        }
        mFootViews.removeAt(index);
        notifyItemRemoved(mHeadLayouts.size() + mDataAdapter.getItemCount() + index - 1);
    }

    /**
     * 删除layoutId对应的headView
     */
    public void removeHeadView(@LayoutRes int layoutId) {
        int index = mHeadLayouts.indexOf(layoutId);
        removeHeadViewByIndex(index);

    }

    /**
     * 删除layoutId对应的footView
     */
    public void removeFootView(@LayoutRes int layoutId) {
        int index = mFootViews.indexOfKey(BASE_ITEM_TYPE_FOOTER + layoutId);
        if (index < 0) {
            throw new IllegalArgumentException("addFootView is the use of layoutId ? ");
        }
        removeFootViewByIndex(index);
    }

    public void removeFootView(View footView) {
        int i = mFootViews.indexOfValue(footView);
        removeFootViewByIndex(i);
    }

    /**
     * 清除所有的headView
     */
    public void clearHeadView() {
        mHeadLayouts.clear();
        notifyDataSetChanged();
    }

    /**
     * 清除所有的footView
     */
    public void clearFootView() {
        mFootViews.clear();
        notifyDataSetChanged();
    }

    /**
     * 实例化view,用于要添加到RecyclerView的View,不会导致设置的宽高不铺满
     */
    public View inflaterView(@LayoutRes int layoutId) {
        return mLInflater.inflate(layoutId, mParent, false);
    }

    /**
     * 转换headAdapter position对应在dataAdapter上的位置
     */
    private int head2DataPosition(int position) {
        return position - (hasHeadView() ? getHeadSize() : 0);
    }

    /**
     * 转换dataAdapter position在headAdapter的位置
     */
    private int data2HeadPosition(int position) {
        return position + (hasHeadView() ? getHeadSize() : 0);
    }

    /**
     * 处理headView
     */
    public abstract void disposeHeadView(HelperViewHolder viewHolder, int headLayoutId, int position);

    /**
     * 处理footView
     */
    public abstract void disposeFootView(HelperViewHolder viewHolder, View footView, int position);


}
