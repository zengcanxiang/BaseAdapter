package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

import com.zengcanxiang.baseAdapter.absListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AbsListViewExample extends AppCompatActivity {

    private android.widget.ListView examplelistview;
    private android.widget.GridView examplegridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_list_view_example);
        this.examplegridview = (GridView) findViewById(R.id.example_grid_view);
        this.examplelistview = (ListView) findViewById(R.id.example_list_view);


        List<String> mList=new ArrayList<>();

        for(int i=0;i<30;i++){
            mList.add(i+"");
        }

        ExampleListAdapter adapter1=new ExampleListAdapter(mList,this,R.layout.example_item);
        ExampleGridAdapter adapter2=new ExampleGridAdapter(mList,this,R.layout.example_item);

        examplelistview.setAdapter(adapter1);
        examplegridview.setAdapter(adapter2);


    }


    private class ExampleListAdapter extends HelperAdapter<String> {

        public ExampleListAdapter(List<String> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
        }

        @Override
        public void HelpConvert(HelperViewHolder viewHolder, int position, String s) {
            viewHolder.setText(R.id.example_item_text_view,"listView"+s);
        }
    }



    private class ExampleGridAdapter extends HelperAdapter<String>{

        public ExampleGridAdapter(List<String> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
        }

        @Override
        public void HelpConvert(HelperViewHolder viewHolder, int position, String s) {
            viewHolder.setText(R.id.example_item_text_view,"gridView"+s);
        }
    }

}
