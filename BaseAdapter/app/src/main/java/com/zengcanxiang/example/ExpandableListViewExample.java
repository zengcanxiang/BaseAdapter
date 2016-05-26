package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter2;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewExample extends AppCompatActivity {

    private android.widget.ExpandableListView exampleexpandableListView;
    private android.widget.ExpandableListView exampleexpandableListView2;
    private List<List<Msg>> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_example);
        this.exampleexpandableListView = (ExpandableListView) findViewById(R.id.example_expandableListView);
        this.exampleexpandableListView2 = (ExpandableListView) findViewById(R.id.example_expandableListView2);

        for (int i = 0; i < 10; i++) {
            List<Msg> temp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Msg msg = new Msg();
                msg.setGroupMsg("标题数据与子数据在一个实体" + i);
                msg.setMsg(j + "");
                temp.add(msg);
            }
            mlist.add(temp);
        }

        ExampleAdapter adapter = new ExampleAdapter(mlist, this,
                new int[]{R.layout.example_expandable_group}, R.layout.example_item);
        exampleexpandableListView.setAdapter(adapter);

        List<GroupMsg> groupMsgList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GroupMsg groupMsg = new GroupMsg();
            groupMsg.setMsg("标题数据与子数据不在一个实体" + i);
            groupMsgList.add(groupMsg);
        }
        ExampleAdapter2 adapter2 = new ExampleAdapter2(groupMsgList, mlist, this,
                new int[]{R.layout.example_expandable_group}, R.layout.example_item);
        exampleexpandableListView2.setAdapter(adapter2);

    }

    private class ExampleAdapter extends HelperAdapter<Msg> {

        public ExampleAdapter(List<List<Msg>> data, Context context, int[] groupLayoutIds, int... childLayoutIds) {
            super(data, context, groupLayoutIds, childLayoutIds);
        }

        @Override
        public void HelpConvertGroup(HelperViewHolder viewHolder, int groupPosition, List<Msg> childs) {
            viewHolder.setText(R.id.expandable_group, childs.get(0).getGroupMsg());
        }

        @Override
        public void HelpConvertChild(HelperViewHolder viewHolder, int groupPosition, int childPosition, Msg msg) {
            viewHolder.setText(R.id.example_item_text_view, "expandable" + msg.getMsg());
        }
    }

    private class ExampleAdapter2 extends HelperAdapter2<GroupMsg, Msg> {

        public ExampleAdapter2(List<GroupMsg> groupData, List<List<Msg>> childData, Context context, int[] groupLayoutIds, int... childLayoutIds) {
            super(groupData, childData, context, groupLayoutIds, childLayoutIds);
        }

        @Override
        public void HelpConvertGroup(HelperViewHolder viewHolder, int groupPosition, GroupMsg item, List<Msg> childs) {
            viewHolder.setText(R.id.expandable_group, item.getMsg());
        }

        @Override
        public void HelpConvertChild(HelperViewHolder viewHolder, int groupPosition, int childPosition, Msg msg) {
            viewHolder.setText(R.id.example_item_text_view, "expandable" + msg.getMsg());
        }
    }
}

