package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.zengcanxiang.baseAdapter.absListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;

import java.util.Arrays;
import java.util.List;

public class AbsListViewExample extends AppCompatActivity {

    private android.widget.ListView examplelistview;
    private android.widget.GridView examplegridview;
    private LinearLayout example_list, example_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_list_view_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.examplegridview = (GridView) findViewById(R.id.example_grid_view);
        this.examplelistview = (ListView) findViewById(R.id.example_list_view);
        this.example_list = (LinearLayout) findViewById(R.id.example_list);
        this.example_grid = (LinearLayout) findViewById(R.id.example_grid);

        List<String> mList = Arrays.asList(PhotoUrl.photoUrls);
        ExampleListAdapter adapter1 = new ExampleListAdapter(mList, this, R.layout.example_item);
        ExampleGridAdapter adapter2 = new ExampleGridAdapter(mList, this, R.layout.example_item);

        examplelistview.setAdapter(adapter1);
        examplegridview.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("listView");
        menu.add("gridView");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "listView":
                example_list.setVisibility(View.VISIBLE);
                example_grid.setVisibility(View.GONE);
                return true;
            case "gridView":
                example_list.setVisibility(View.GONE);
                example_grid.setVisibility(View.VISIBLE);
                return true;
        }
        return false;

    }


    public class ExampleListAdapter extends HelperAdapter<String> {

        public ExampleListAdapter(List<String> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
        }

        @Override
        public void HelpConvert(HelperViewHolder viewHolder, int position, String s) {
            ImageView view = viewHolder.getView(R.id.example_item_img_view);
            Glide.with(AbsListViewExample.this)
                    .load(s)
                    .centerCrop()
                    .into(view);
        }
    }


    public class ExampleGridAdapter extends HelperAdapter<String> {

        public ExampleGridAdapter(List<String> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
        }

        @Override
        public void HelpConvert(HelperViewHolder viewHolder, int position, String s) {
            ImageView view = viewHolder.getView(R.id.example_item_img_view);
            Glide.with(AbsListViewExample.this)
                    .load(s)
                    .centerCrop()
                    .into(view);
        }
    }

}
