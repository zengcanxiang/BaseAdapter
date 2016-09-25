package com.zengcanxiang.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ExampleFragment extends Fragment {


    private int layoutId;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutId, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView= (TextView) view.findViewById(R.id.example_viewpager_text);
        String temp=textView.getText().toString();
        textView.setText(temp+"_fragment");
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
