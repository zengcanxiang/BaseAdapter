package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.bumptech.glide.Glide;
import com.zengcanxiang.baseAdapter.recyclerView.HeadFootAdapter;
import com.zengcanxiang.baseAdapter.recyclerView.HelperAdapter;
import com.zengcanxiang.baseAdapter.recyclerView.HelperViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RecyclerViewOtherView extends AppCompatActivity {

    private int i = 0;
    private RecyclerView recyclerViewother;
    private List<String> mList;
    private LinearLayoutManager mLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGrid;
    private GridLayoutManager mGridLayoutManager;
    private MyRecyerAdapter mAdapter;
    private HeadFootAdapter headFootAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_other_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRecy();
        initHeadFoot();

        recyclerViewother.setAdapter(headFootAdapter);

    }
    RecyclerView gridView;
    private void initHeadFoot() {
        headFootAdapter = new HeadFootAdapter(mAdapter) {
            @Override
            public void disposeHeadView(HelperViewHolder viewHolder, int position) {
                gridView = viewHolder.getView(R.id.headGrid);
                if(gridView.getAdapter()!=null){
                    return;
                }
                ExampleGridAdapter adapter2 = new ExampleGridAdapter(mList, RecyclerViewOtherView.this, R.layout.example_viewpager_one);
                gridView.setLayoutManager(new GridLayoutManager(RecyclerViewOtherView.this,2));
                gridView.setNestedScrollingEnabled(true);
                gridView.setAdapter(adapter2);
            }

            @Override
            public void disposeFootView(HelperViewHolder viewHolder, int position) {

            }
        };
        headFootAdapter.addHeadView(R.layout.example_head_footer);
    }

    private void initRecy() {
        this.recyclerViewother = (RecyclerView) findViewById(R.id.recyclerView_other);
        mList = Arrays.asList(PhotoUrl.photoUrls);
        mAdapter = new MyRecyerAdapter(mList, this, R.layout.example_item);
        recyclerViewother.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewother.setLayoutManager(mGridLayoutManager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerViewother.addItemDecoration(decoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("listView+head+foot");
        menu.add("gridView+head+foot");
        menu.add("StaggeredGrid+head+foot");
        menu.add("addHead");
        menu.add("addFoot");
        menu.add("removeHead");
        menu.add("removeFoot");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "listView+head+foot":
                i = 0;
                mLayoutManager = new LinearLayoutManager(this);
                recyclerViewother.setLayoutManager(mLayoutManager);
                recyclerViewother.setAdapter(headFootAdapter);
                return true;
            case "gridView+head+foot":
                i = 0;
                mGridLayoutManager = new GridLayoutManager(this, 3);
                recyclerViewother.setLayoutManager(mGridLayoutManager);
                recyclerViewother.setAdapter(headFootAdapter);
                return true;
            case "StaggeredGrid+head+foot":
                i = 1;
                mStaggeredGrid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerViewother.setLayoutManager(mStaggeredGrid);
                recyclerViewother.setAdapter(headFootAdapter);
                return true;
            case "removeHead":
                headFootAdapter.removeHeadView(R.layout.example_head_footer);
                break;
            case "removeFoot":
                headFootAdapter.removeFootView(R.layout.example_head_footer);
                break;
            case "addHead":
                headFootAdapter.addHeadView(R.layout.example_head_footer);
                break;
            case "addFoot":
                headFootAdapter.addFootView(R.layout.example_head_footer);
                break;
        }
        return false;

    }

    public class ExampleGridAdapter extends HelperAdapter<String> {

        public ExampleGridAdapter(List<String> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
        }

        @Override
        protected void HelperBindData(HelperViewHolder viewHolder, int position, String item) {
            viewHolder.setText(R.id.example_viewpager_text, position + "");
        }

    }

    private class MyRecyerAdapter extends HelperAdapter<String> {
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
            Glide.with(RecyclerViewOtherView.this)
                    .load(s)
                    .crossFade()
                    .centerCrop()
                    .into(view);
        }

    }
}
