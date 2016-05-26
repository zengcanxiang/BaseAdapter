package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zengcanxiang.baseAdapter.recyclerView.HelperAdapter;
import com.zengcanxiang.baseAdapter.recyclerView.HelperViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExample extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView recyclerView;
    private List<String> mList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(i + "");
        }
        MyRecyAdapter adapter = new MyRecyAdapter(mList, this, R.layout.example_item);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private class MyRecyAdapter extends HelperAdapter<String> {
        /**
         * @param data     数据源
         * @param context  上下文
         * @param layoutId 布局Id
         */
        public MyRecyAdapter(List<String> data, Context context, int... layoutId) {
            super(data, context, layoutId);
        }

        @Override
        protected void HelperBindData(HelperViewHolder viewHolder, int position, String item) {
            TextView view = viewHolder.getView(R.id.example_item_text_view);
            view.setText(item);
        }
    }


}
