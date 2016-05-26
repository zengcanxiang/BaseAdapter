package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.zengcanxiang.baseAdapter.absListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AbsListViewExampleTwo extends AppCompatActivity {

    private android.widget.ListView msglistview;
    List<Msg> msgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_list_view_example_two);
        this.msglistview = (ListView) findViewById(R.id.msg_list_view);
        msgList= new ArrayList<>();
        setData();
        MyAdapter adapter = new MyAdapter(msgList, this, R.layout.example_different_item_right, R.layout.example_different_item_left);
        msglistview.setAdapter(adapter);

    }

    private void setData() {
        Msg msg=new Msg(0,"你好!");
        msgList.add(msg);
        msg=new Msg(1,"你也好啊!");
        msgList.add(msg);
        msg=new Msg(0,"这是我的开源之路的第一个项目!");
        msgList.add(msg);
        msg=new Msg(1,"我来看看!");
        msgList.add(msg);
        msg=new Msg(0,"谢谢!");
        msgList.add(msg);
        msg=new Msg(1,"不用谢!");
        msgList.add(msg);

    }


    private class MyAdapter extends HelperAdapter<Msg> {

        public MyAdapter(List<Msg> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
        }

        @Override
        public void HelpConvert(HelperViewHolder viewHolder, int position, Msg msg) {
            switch (msg.getType()){
                case  0:
                    viewHolder.setText(R.id.chat_send_content,msg.getMsg());
                    break;
                case 1:
                    viewHolder.setText(R.id.chat_from_content,msg.getMsg());
                    break;
            }
        }

        @Override
        public int checkLayoutIndex(int position, Msg item) {
            return item.getType();
        }
    }

}
