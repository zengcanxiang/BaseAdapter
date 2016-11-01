package com.zengcanxiang.baseAdapter.recyclerView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zengcanxiang.baseAdapter.R;

import java.util.List;

/**
 * 支持headView,footerView的RecyclerViewAdapter
 *
 * @author zengcx
 */
public class HeadFootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ID_HEAD = 0x01, ID_FOOTER = 0x02;
    private final int VIEW_TYPE_HEAD = 0x001, VIEW_TYPE_FOOTER = 0x002;
    private View mHeadView, mFootView;
    private RecyclerView.Adapter mDataAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isStaggered;

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
        super.onAttachedToRecyclerView(recyclerView);
        if (mHeadView != null && mHeadView.getParent() != null) {
            ((ViewGroup) mHeadView.getParent()).removeView(mHeadView);
        }
        if (mFootView != null && mFootView.getParent() != null) {
            ((ViewGroup) mFootView.getParent()).removeView(mFootView);
        }
        mLayoutManager = recyclerView.getLayoutManager();
        initLayoutManager();
    }

    /**
     * 初始化布局管理器,处理GridLayoutManager,StaggeredGridLayoutManager没有占满一行
     */
    private void initLayoutManager() {
        if (mLayoutManager instanceof GridLayoutManager) {
            final GridLayoutManager castedLayoutManager = (GridLayoutManager) mLayoutManager;
            final GridLayoutManager.SpanSizeLookup existingLookup = castedLayoutManager.getSpanSizeLookup();

            castedLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isHeader(position) || isFooter(position)) {
                        return castedLayoutManager.getSpanCount();
                    }
                    return existingLookup.getSpanSize(head2DataPosition(position));
                }
            });
        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            isStaggered = true;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEAD || viewType == VIEW_TYPE_FOOTER) {
            return new HeadFooterViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.base_head_footer_adapter_item, parent, false));
        } else {
            return mDataAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadFooterViewHolder) {
            bind((HeadFooterViewHolder) holder, position);
        } else {
            mDataAdapter.onBindViewHolder(holder, head2DataPosition(position));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (holder instanceof HeadFooterViewHolder) {
            bind((HeadFooterViewHolder) holder, position);
        } else {
            mDataAdapter.onBindViewHolder(holder, head2DataPosition(position), payloads);
        }
    }

    @SuppressWarnings("all")
    private void bind(HeadFooterViewHolder holder, int position) {
        View viewToAdd = isHeader(position) ? mHeadView : mFootView;

        ViewGroup itemView = (ViewGroup) holder.itemView;
        if (itemView != null) {
            itemView.removeAllViews();
            itemView.addView(viewToAdd);
        }

        ViewGroup.LayoutParams layoutParams;
        //设置瀑布流头部、尾部布局宽度高度调整
        if (isStaggered) {
            if (viewToAdd.getLayoutParams() == null) {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            } else {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(
                        viewToAdd.getLayoutParams().width,
                        viewToAdd.getLayoutParams().height
                );
            }
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        } else {
            if (viewToAdd.getLayoutParams() == null) {
                layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            } else {
                layoutParams = new ViewGroup.LayoutParams(
                        viewToAdd.getLayoutParams().width,
                        viewToAdd.getLayoutParams().height
                );
            }
        }

        holder.itemView.setLayoutParams(layoutParams);
    }


    @Override
    public int getItemCount() {
        int itemCount = mDataAdapter.getItemCount();
        if (hasHeadView()) {
            itemCount += 1;
        }
        if (hasFootView()) {
            itemCount += 1;
        }
        return itemCount;
    }

    @Override
    public long getItemId(int position) {
        if (isHeader(position)) {
            return ID_HEAD;
        } else if (isFooter(position)) {
            return ID_FOOTER;
        } else {
            return mDataAdapter.getItemId(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return VIEW_TYPE_HEAD;
        } else if (isFooter(position)) {
            return VIEW_TYPE_FOOTER;
        } else {
            return mDataAdapter.getItemViewType(head2DataPosition(position));
        }
    }

    /**
     * dataAdapter position对应在headAdapter上的位置
     */
    public int data2HeadPosition(int position) {
        return position + (hasHeadView() ? 1 : 0);
    }

    /**
     * headAdapter position对应在dataAdapter上的位置
     */
    private int head2DataPosition(int position) {
        return position - (hasHeadView() ? 1 : 0);
    }

    /**
     * 获取footView position
     */
    private int getFootPosition() {
        return mDataAdapter.getItemCount() + (hasHeadView() ? 1 : 0);
    }

    /**
     * 获取尾部在headAdapter的位置
     *
     * @return 是否有FooterView:  <code>false</code> -1 ; <code>true</code> footView的位置
     */
    public int getFootViewPosition() {
        if (hasFootView()) {
            return getFootPosition();
        } else {
            return -1;
        }
    }

    /**
     * 获取头部在headAdapter的位置
     *
     * @return 是否有HeadView:  <code>false</code> -1 <code>true</code> 0
     */
    public int getHeadViewPosition() {
        return hasHeadView() ? 0 : -1;
    }

    private boolean isHeader(int position) {
        return hasHeadView() && position == 0;
    }

    private boolean isFooter(int position) {
        return hasFootView() && position == getFootPosition();
    }

    public boolean hasHeadView() {
        return getHeadView() != null;
    }

    public boolean hasFootView() {
        return getFootView() != null;
    }

    public View getHeadView() {
        return mHeadView;
    }

    public void addHeadView(@Nullable View headView) {
        if (mHeadView == headView) {
            return;
        }
        boolean hadHeader = mHeadView != null;
        if (hadHeader) {
            detachFromParent(this.mHeadView);
        }
        mHeadView = headView;
        //如果传进来的head为null,就移除头部
        if (headView == null) {
            //如果之前存在头部，清除
            if (hadHeader) {
                notifyItemRemoved(0);
            }
        } else {
            //否则添加头部view到adapter
            //如果之前存在头部view,做更新，不存在，做插入
            if (hadHeader) {
                notifyItemChanged(0);
            } else {
                notifyItemInserted(0);
            }
        }
    }

    public void removeHeadView() {
        addHeadView(null);
    }

    public View getFootView() {
        return mFootView;
    }

    public void addFootView(@Nullable View footView) {
        if (mFootView == footView) {
            return;
        }
        boolean hadFooter = mFootView != null;

        if (hadFooter) {
            detachFromParent(this.mFootView);
        }

        mFootView = footView;

        if (footView == null) {
            if (hadFooter) {
                notifyItemRemoved(getFootPosition());
            }
        } else {
            if (hadFooter) {
                notifyItemChanged(getFootPosition());
            } else {
                notifyItemInserted(getFootPosition());
            }
        }
    }

    public void removeFootView() {
        addFootView(null);
    }

    private void detachFromParent(@NonNull View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
    }

    private class HeadFooterViewHolder extends RecyclerView.ViewHolder {
        HeadFooterViewHolder(View itemView) {
            super(itemView);
        }
    }


}
