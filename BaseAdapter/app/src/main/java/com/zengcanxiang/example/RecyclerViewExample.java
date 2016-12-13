package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zengcanxiang.baseAdapter.interFace.OnItemClickListener;
import com.zengcanxiang.baseAdapter.recyclerView.BaseViewHolder;
import com.zengcanxiang.baseAdapter.recyclerView.HelperAdapter;
import com.zengcanxiang.baseAdapter.recyclerView.HelperViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewExample extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView recyclerView;
    private List<String> mList;
    private LinearLayoutManager mLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGrid;
    private GridLayoutManager mGridLayoutManager;
    private int i = 0;
    private MyRecyerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = Arrays.asList(PhotoUrl.photoUrls);
        mAdapter = new MyRecyerAdapter(mList, this, R.layout.example_item);
        recyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mGridLayoutManager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

        mAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(BaseViewHolder holder, int position, String item) {
                Toast.makeText(RecyclerViewExample.this, "你点击了第"+position+"项，数据:"+item, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("listView");
        menu.add("gridView");
        menu.add("StaggeredGrid");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "listView":
                i = 0;
                mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                return true;
            case "gridView":
                i = 0;
                mGridLayoutManager = new GridLayoutManager(this, 3);
                recyclerView.setLayoutManager(mGridLayoutManager);
                recyclerView.setAdapter(mAdapter);
                return true;
            case "StaggeredGrid":
                i = 1;
                mStaggeredGrid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mStaggeredGrid);
                recyclerView.setAdapter(mAdapter);
                return true;
        }
        return false;
    }


    private  class MyRecyerAdapter extends HelperAdapter<String> {
        private List<Integer> mHeights = new ArrayList<>();

        /**
         * @param data     数据源
         * @param context  上下文
         * @param layoutId 布局Id
         */
        public MyRecyerAdapter(List<String> data, Context context, int... layoutId) {
            super(data, context, layoutId);
            for (int i = 0; i < data.size(); i++) {
                mHeights.add((int) (200 + Math.random() * 300));
            }
        }

        @Override
        protected void HelperBindData(HelperViewHolder viewHolder, int position, String s) {
            ImageView view = viewHolder.getView(R.id.example_item_img_view);
            if (i == 1) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = mHeights.get(position);
                view.setLayoutParams(layoutParams);
            }
            Glide.with(RecyclerViewExample.this)
                    .load(s)
                    .crossFade()
                    .centerCrop()
                    .into(view);
        }

    }


}
