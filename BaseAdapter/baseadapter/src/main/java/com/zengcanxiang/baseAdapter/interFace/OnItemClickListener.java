package com.zengcanxiang.baseAdapter.interFace;

import com.zengcanxiang.baseAdapter.recyclerView.BaseViewHolder;

/**
 * item的点击事件
 */
public interface OnItemClickListener<T> {
    void onItemClick(BaseViewHolder holder, int position, T item);
}
