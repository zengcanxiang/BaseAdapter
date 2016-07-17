package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter2;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewExample extends AppCompatActivity {

    private android.widget.ExpandableListView exampleexpandableListView;
    private List<List<Msg>> mlist1 = new ArrayList<>();
    private List<List<Msg>> mlist2 = new ArrayList<>();
    private List<GroupMsg> groupMsgList = new ArrayList<>();
    private ExampleAdapter adapter1;
    private ExampleAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.exampleexpandableListView = (ExpandableListView) findViewById(R.id.example_expandableListView);

        init1();
        init2();

        adapter2 = new ExampleAdapter2(groupMsgList, mlist2, this,
                new int[]{R.layout.example_expandable_group}, R.layout.example_item_2);

        adapter1 = new ExampleAdapter(mlist1, this,
                new int[]{R.layout.example_expandable_group}, R.layout.example_item_2);


        exampleexpandableListView.setAdapter(adapter1);


    }

    private void init2() {
        GroupMsg groupMsg = new GroupMsg();
        groupMsg.setMsg("西游记·2");
        groupMsgList.add(groupMsg);

        groupMsg = new GroupMsg();
        groupMsg.setMsg("水浒传·2");
        groupMsgList.add(groupMsg);

        groupMsg = new GroupMsg();
        groupMsg.setMsg("三国演义·2");
        groupMsgList.add(groupMsg);

        groupMsg = new GroupMsg();
        groupMsg.setMsg("红楼梦·2");
        groupMsgList.add(groupMsg);

        List<Msg> temp = new ArrayList<>();
        Msg msg = new Msg();
        msg.setMsg("万寿山大仙留故友 五庄观行者窃人参");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg(" 镇元仙赶捉取经僧 孙行者大闹五庄观");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("孙悟空三岛求方 观世音甘泉活树");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("尸魔三戏唐三藏 圣僧恨逐美猴王");
        temp.add(msg);

        mlist2.add(temp);
        temp = new ArrayList<>();
        msg = new Msg();
        msg.setMsg(" 阎婆大闹郓城县 朱仝义释宋公明");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("横海郡柴进留宾 景阳冈武松打虎");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("王婆贪贿说风情 郓哥不忿闹茶肆");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("王婆计啜西门庆 淫妇药鸩武大郎");
        temp.add(msg);
        mlist2.add(temp);

        temp = new ArrayList<>();
        msg = new Msg();
        msg.setMsg(" 国贼行凶杀贵妃 皇叔败走投袁绍");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg(" 屯土山关公约三事 救白马曹操解重围");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("袁本初败兵折将 关云长挂印封金");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("美髯公千里走单骑 汉寿侯五关斩六将");
        temp.add(msg);

        mlist2.add(temp);

        temp = new ArrayList<>();
        msg = new Msg();
        msg.setMsg("贤袭人娇嗔箴宝玉  俏平儿软语救贾琏");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg(" 听曲文宝玉悟禅机  制灯谜贾政悲谶语");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("西厢记妙词通戏语  牡丹亭艳曲警芳心");
        temp.add(msg);
        msg = new Msg();
        msg.setMsg("醉金刚轻财尚义侠  痴女儿遗帕惹相思");
        temp.add(msg);

        mlist2.add(temp);

    }


    private void init1() {

        List<Msg> temp = new ArrayList<>();
        Msg msg = new Msg();
        msg.setGroupMsg("西游记");
        msg.setMsg("灵根育孕源流出 心性修持大道生");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("西游记");
        msg.setMsg("悟彻菩提真妙理 断魔归本合元神");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("西游记");
        msg.setMsg("四海千山皆拱伏 九幽十类尽除名");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("西游记");
        msg.setMsg("官封弼马心何足 名注齐天意未宁");
        temp.add(msg);

        mlist1.add(temp);

        temp = new ArrayList<>();
        msg = new Msg();
        msg.setGroupMsg("水浒传");
        msg.setMsg("王教头私走延安府 九纹龙大闹史家村");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("水浒传");
        msg.setMsg("史大郎夜走华阴县 鲁提辖拳打镇关西");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("水浒传");
        msg.setMsg("赵员外重修文殊院 鲁智深大闹五台山");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("水浒传");
        msg.setMsg("小霸王醉入销金帐 花和尚大闹桃花村");
        temp.add(msg);
        mlist1.add(temp);

        temp = new ArrayList<>();
        msg = new Msg();
        msg.setGroupMsg("三国演义");
        msg.setMsg(" 宴桃园豪杰三结义 斩黄巾英雄首立功");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("三国演义");
        msg.setMsg(" 张翼德怒鞭督邮 何国舅谋诛宦竖");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("三国演义");
        msg.setMsg("议温明董卓叱丁原 馈金珠李肃说吕布");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("三国演义");
        msg.setMsg("废汉帝陈留践位 谋董贼孟德献刀");
        temp.add(msg);

        mlist1.add(temp);

        temp = new ArrayList<>();
        msg = new Msg();
        msg.setGroupMsg("红楼梦");
        msg.setMsg("甄士隐梦幻识通灵  贾雨村风尘怀闺秀");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("红楼梦");
        msg.setMsg(" 贾夫人仙逝扬州城  冷子兴演说荣国府");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("红楼梦");
        msg.setMsg("托内兄如海酬训教  接外孙贾母惜孤女");
        temp.add(msg);
        msg = new Msg();
        msg.setGroupMsg("红楼梦");
        msg.setMsg("薄命女偏逢薄命郎  葫芦僧乱判葫芦案");
        temp.add(msg);

        mlist1.add(temp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("标题数据与子数据在一个实体");
        menu.add("标题数据与子数据不在一个实体");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "标题数据与子数据在一个实体":
                exampleexpandableListView.setAdapter(adapter1);
                return true;
            case "标题数据与子数据不在一个实体":
                exampleexpandableListView.setAdapter(adapter2);
                return true;
        }
        return false;

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
            viewHolder.setText(R.id.example_item_text_view, msg.getMsg());
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
            viewHolder.setText(R.id.example_item_text_view, msg.getMsg());
        }
    }
}

