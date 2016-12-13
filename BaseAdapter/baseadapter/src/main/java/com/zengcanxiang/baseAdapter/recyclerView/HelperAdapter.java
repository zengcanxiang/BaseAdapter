package com.zengcanxiang.baseAdapter.recyclerView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zengcanxiang.baseAdapter.interFace.DataHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供便捷操作的baseAdapter
 *
 * @author zengcx
 */
public abstract class HelperAdapter<T> extends BaseAdapter<T>
        implements DataHelper<T> {
    /**
     * @param data     数据源
     * @param context  上下文
     * @param layoutId 布局Id
     */
    public HelperAdapter(List<T> data, Context context, int... layoutId) {
        super(data, context, layoutId);
    }

    @Override
    @SuppressWarnings("all")
    public final HelperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < 0 || viewType > mLayoutId.length) {
            throw new ArrayIndexOutOfBoundsException("checkLayoutIndex > LayoutId.length ：" + viewType + ">" + mLayoutId.length);
        }
        if (mLayoutId.length == 0) {
            throw new IllegalArgumentException("not layoutId");
        }
        mParent = parent;
        int layoutId = mLayoutId[viewType];
        View view = inflaterView(layoutId);
        HelperViewHolder viewHolder = (HelperViewHolder) view.getTag();
        if (viewHolder == null || viewHolder.getLayoutId() != layoutId) {
            viewHolder = new HelperViewHolder(mContext, layoutId, view);
        }
        if (mOnItemClickListener != null) {
            final BaseViewHolder finalViewHolder = viewHolder;
            viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = finalViewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(finalViewHolder, position, mList.get(position));
                }
            });
        }
        return viewHolder;
    }


    @Override
    protected final void bindData(BaseViewHolder viewHolder, int position, T item) {
        HelperViewHolder helperViewHolder = (HelperViewHolder) viewHolder;

        HelperBindData(helperViewHolder, position, item);
    }

    @Override
    protected final void bindData(BaseViewHolder viewHolder, int position, T item, List<Object> payloads) {
        HelperViewHolder helperViewHolder = (HelperViewHolder) viewHolder;

        HelperBindData(helperViewHolder, position, item, payloads);
    }

    protected void HelperBindData(HelperViewHolder viewHolder, int position, T item, List<Object> payloads) {

    }

    protected abstract void HelperBindData(HelperViewHolder viewHolder, int position, T item);

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
        notifyItemInserted(mList.size() - 1);
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
        notifyItemRangeInserted(startPosition, datas.size());
        return result;
    }

    @Override
    public void add(int startPosition, T data) {
        initList();
        mList.add(startPosition, data);
        notifyItemInserted(startPosition);
    }

    @Override
    public T getData(int position) {
        return getItemCount() == 0 ? null : mList.get(position);
    }

    @Override
    public void alterObj(T oldData, T newData) {
        alterObj(mList.indexOf(oldData), newData);
    }

    @Override
    public void alterObj(int position, T data) {
        initList();
        mList.set(position, data);
        notifyItemRangeChanged(position, 1);
    }

    @Override
    public boolean remove(T data) {
        if (mList == null) {
            throw new IllegalArgumentException("list is null,cannot execute");
        }
        int position = mList.indexOf(data);
        boolean result = mList.remove(data);
        notifyItemRemoved(position);
        return result;
    }

    @Override
    public void removeToIndex(int position) {
        if (mList == null) {
            throw new IllegalArgumentException("list is null,cannot execute");
        }
        mList.remove(position);
        notifyItemRemoved(position);
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

    @Override
    public final int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
