package com.zengcanxiang.baseAdapter.absListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by zengcanxiang on 2016/1/28.
 */
public class HelperHolder extends BaseViewHolder {

    public HelperHolder(Context context, int position, ViewGroup parent,
                        int layoutId){
        super(context,position,parent,layoutId);
    }

    public HelperHolder(){}

    @Override
    public HelperHolder get(Context context, int position, View convertView, ViewGroup parent, int layoutId) {

        if (convertView == null) {
            return new HelperHolder(context, position, parent, layoutId);
        } else {
            HelperHolder bHolder = (HelperHolder) convertView.getTag();
            if(bHolder.getLayoutId()!=layoutId){
                return new HelperHolder(context, position, parent, layoutId);
            }
            bHolder.setPosition(position);
            return bHolder;
        }
    }

    /**
     * 为textView设置文字
     *
     * @param viewId viewId
     * @param value  value
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setText(int viewId, String value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * 设置imageView文件内容，从drawable文件
     *
     * @param viewId     viewId
     * @param imageResId 文件id
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param viewId viewId
     * @param color  不是colorId
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     *
     * @param viewId        viewId
     * @param backgroundRes 背景Id
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId    viewId
     * @param textColor 文字颜色，不是colorId
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId       viewId
     * @param textColorRes colorId
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId   viewId
     * @param drawable
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 通过url加载图片
     *
     * @param viewId   viewId
     * @param imageUrl 图片路径
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setImageUrl(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        //TODO 加载图片
        //TODO 网络路径和本地文件路径
        return this;
    }


    public HelperHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }


    /**
     * 设置view的显示和隐藏
     *
     * @param viewId  viewId
     * @param visible
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置Tag
     *
     * @param viewId viewId
     * @param tag    The tag;
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setTag(int viewId, Object tag) {
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
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * 设置选择状态
     *
     * @param viewId  CheckableViewId
     * @param checked 状态
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置Adapter
     *
     * @param viewId  viewId
     * @param adapter The adapter;
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }


    //---------------------------------下面的具体作用没有去了解---------------------------------------

    /**
     * 设置动画
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    @SuppressLint("NewApi")
    public HelperHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * Add links into a TextView.
     *
     * @param viewId viewId
     * @return The HelperHolder for chaining.
     */
    public HelperHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * Apply the typeface to the given viewId, and enable subpixel rendering.
     */
    public HelperHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * Apply the typeface to all the given viewIds, and enable subpixel
     * rendering.
     */
    public HelperHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   viewId
     * @param progress The progress.
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   viewId
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     *
     * @param viewId viewId
     * @param max    The max value of a ProgressBar.
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId viewId
     * @param rating The rating.
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId viewId
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The HelperHolder for chaining.
     */
    public HelperHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }


}
