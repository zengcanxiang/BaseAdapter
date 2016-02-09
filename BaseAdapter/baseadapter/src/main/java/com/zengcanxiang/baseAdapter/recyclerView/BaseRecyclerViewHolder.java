package com.zengcanxiang.baseAdapter.recyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zengcanxiang on 2016/1/6.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews = new SparseArray<View>();
    private View mConvertView;
    private int mLayoutId;
    public BaseRecyclerViewHolder(int layoutId,View itemView) {
        super(itemView);
        this.mLayoutId=layoutId;
        mConvertView = itemView;
        mConvertView.setTag(this);
    }

    @SuppressWarnings("unchecked")
    public <R extends View> R getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (R) view;
    }

    public int getLayoutId(){
        return  mLayoutId;
    }
    public View getItemView() {
        return mConvertView;
    }

    /**
     * 为textView设置文字
     *
     * @param viewId viewId
     * @param value  value
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setText(int viewId, String value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * 设置imageView文件内容，从drawable文件
     *
     * @param viewId     viewId
     * @param imageResId 文件id
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param viewId viewId
     * @param color  不是colorId
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     *
     * @param viewId        viewId
     * @param backgroundRes 背景Id
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId    viewId
     * @param textColor 文字颜色，不是colorId
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * Will set text color of a TextView.
     * @param context
     * @param viewId       viewId
     * @param textColorRes colorId
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setTextColorRes(Context context,int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId   viewId
     * @param drawable
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 通过url加载图片
     *
     * @param viewId   viewId
     * @param imageUrl 图片路径
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setImageUrl(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        //TODO 加载图片
        //TODO 网络路径和本地文件路径
        return this;
    }


    public BaseRecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }


    /**
     * 设置view的显示和隐藏
     *
     * @param viewId  viewId
     * @param visible
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置Tag
     *
     * @param viewId viewId
     * @param tag    The tag;
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 设置Tag
     *
     * @param viewId viewId
     * @param key    The key of tag;
     * @param tag    The tag;
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * 设置选择状态
     *
     * @param viewId  CheckableViewId
     * @param checked 状态
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置Adapter
     *
     * @param viewId  viewId
     * @param adapter The adapter;
     * @return The BaseRecyclerViewHolder for chaining.
     */
    public BaseRecyclerViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

}
