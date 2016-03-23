package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zengcanxiang.baseAdapter.recyclerView.HelperRecyclerViewAdapter;
import com.zengcanxiang.baseAdapter.recyclerView.HelperRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExampleTwo extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView recyclerView;
    private List<Msg> mList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example_two);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        setData();
        MyRecyAdapter adapter = new MyRecyAdapter(mList, this, R.layout.example_different_item_right, R.layout.example_different_item_left);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setData() {
        Msg msg = new Msg(0, "你好!");
        mList.add(msg);
        msg = new Msg(1, "你也好啊!");
        mList.add(msg);
        msg = new Msg(0, "这是我的开源之路的第一个项目!");
        mList.add(msg);
        msg = new Msg(1, "我来看看!");
        mList.add(msg);
        msg = new Msg(0, "谢谢!");
        mList.add(msg);
        msg = new Msg(1, "不用谢!");
        mList.add(msg);

    }

    private class MyRecyAdapter extends HelperRecyclerViewAdapter<Msg> {
        /**
         * @param data     数据源
         * @param context  上下文
         * @param layoutId 布局Id
         */
        public MyRecyAdapter(List<Msg> data, Context context, int... layoutId) {
            super(data, context, layoutId);
        }


        @Override
        protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, Msg item) {
            switch (item.getType()) {
                case 0:
                    viewHolder.setText(R.id.chat_send_content, item.getMsg());
                    break;
                case 1:
                    viewHolder.setText(R.id.chat_from_content, item.getMsg());
                    break;
            }
        }


        @Override
        public int checkLayout(Msg item, int position) {
            /**
             * 多子布局样式重写checkLayout()方法，返回对应的index
             * 本例子因为msg的Type对应的就是0和1,所以就直接返回msgType
             */
            return item.getType();
        }
    }
}
