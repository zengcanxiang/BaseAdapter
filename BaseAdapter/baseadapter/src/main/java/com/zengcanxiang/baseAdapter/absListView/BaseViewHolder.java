package com.zengcanxiang.baseAdapter.absListView;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 万能适配Holder,减少赘于代码和加快开发流程
 *
 * @author zcx
 */
public class BaseViewHolder {

    /**
     * 保存小控件的集合
     */
    private SparseArray<View> mViews = new SparseArray<View>();
    /**
     * 保存布局view的集合
     */
    private SparseArray<View> mConvertViews = new SparseArray<View>();
    private int mPosition;
    /**
     * 单个布局view
     */
    private View mConvertView;
    /**
     * layoutId
     */
    private int mLayoutId;
    /**
     * 上下文
     */
    protected Context mContext;
    public BaseViewHolder(Context context, int position, ViewGroup parent,
                          int layoutId) {
        mConvertView= mConvertViews.get(layoutId);
        mPosition = position;
        mContext=context;
        mLayoutId=layoutId;
        if(mConvertView==null){
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            mConvertViews.put(layoutId,mConvertView);
            mConvertView.setTag(this);
        }
    }
    public BaseViewHolder(){
    }
    /**
     * 获取BaseViewHolder实例
     *
     * @param context  上下文
     * @param position 位置
     * @param parent   viewGroup
     * @param layoutId 对应的布局Id
     * @return BaseViewHolder实例
     */
    public BaseViewHolder get(Context context, int position,
                                     View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new BaseViewHolder(context, position, parent, layoutId);
        } else {
            BaseViewHolder bHolder = (BaseViewHolder) convertView.getTag();
            if(bHolder.mLayoutId!=layoutId){
                return new BaseViewHolder(context, position, parent, layoutId);
            }
            bHolder.setPosition(position);
            return bHolder;
        }
    }

    /**
     * 获取viewId对应的控件
     *
     * @param viewId
     * @param <R>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <R extends View> R getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (R) view;
    }


    /**
     * 当没有指定到时候，返回队列中的第一个
     *
     * @return
     */
    public View getConvertView() {
        return mConvertViews.valueAt(0);
    }
    /**
     * 返回队列中指定layoutId对应的view
     *
     * @param layoutId
     * @return
     */
    public View getConvertView(int layoutId) {
        return mConvertViews.get(layoutId);
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public int getLayoutId(){
        return mLayoutId;
    }
}
