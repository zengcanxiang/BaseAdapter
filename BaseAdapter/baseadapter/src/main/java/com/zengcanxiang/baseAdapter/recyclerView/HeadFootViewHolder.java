package com.zengcanxiang.baseAdapter.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


class HeadFootViewHolder extends HelperViewHolder {

    private HeadFootViewHolder(Context context, int layoutId, View itemView) {
        super(context, layoutId, itemView);
    }

    public static HeadFootViewHolder get(Context context, View convertView,
                                         ViewGroup parent, int layoutId) {
        HeadFootViewHolder holder;
        if (convertView == null) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            holder = new HeadFootViewHolder(context, layoutId, itemView);
        } else {
            holder = (HeadFootViewHolder) convertView.getTag();
        }

        if (holder == null) {
            holder = new HeadFootViewHolder(context, layoutId, convertView);
        }

        return holder;
    }
}
