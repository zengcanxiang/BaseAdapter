# BaseAdapter
这个项目是为了封装listview或者GridView还有RecyclerView的Adapter.
此项目是观看了 <a href="http://www.imooc.com/learn/372">张鸿洋老师的视频</a>而受到的启发.
在github上也有很多前辈们的项目:
<ul>
  <li><a href="https://github.com/tianzhijiexian/CommonAdapter">CommonAdapter</a></li>
  <li><a href="https://github.com/hongyangAndroid/base-adapter-helper">hongyangAndroid:base-adapter-helper</a></li>
  <li><a href="https://github.com/JoanZapata/base-adapter-helper">JoanZapata:base-adapter-helper</a></li>
  <li>.....</li>
</ul>
但是以个人习惯来说,感觉项目使用起来都是比较复杂的,就吸取精华,简化了一下使用.

![示例](https://github.com/zengcanxiang/BaseAdapter/blob/master/Animation.gif)  

[demo.apk](https://github.com/zengcanxiang/BaseAdapter/blob/master/app-debug.apk)  

## compile
```java
compile 'com.zengcanxiang.baseAdapter:baseadapter:1.4.9'
```   
###使用范例
####1.普通absListView
```java
import com.zengcanxiang.baseAdapter.absListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;

private class ExampleListAdapter extends HelperAdapter<String> {

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
```

```java

    /因为这里是将layoutId当作参数传进去，一般的话，会在adapter内部就进行配置
    //如果adapter的代码少，无须处理很多逻辑，更加适合创建内部类。
    //demo就是创建的内部类

    ExampleListAdapter adapter1=new ExampleListAdapter(mList,this,R.layout.example_item);
    ExampleGridAdapter adapter2=new ExampleGridAdapter(mList,this,R.layout.example_item);

    examplelistview.setAdapter(adapter1);
    examplegridview.setAdapter(adapter2);
```

####2.多子布局的recyclerView
```java
import com.zengcanxiang.baseAdapter.recyclerView.HelperAdapter;
import com.zengcanxiang.baseAdapter.recyclerView.HelperViewHolder;

private class MyRecyAdapter extends HelperAdapter<Msg> {
       /**
        * @param data     数据源
        * @param context  上下文
        * @param layoutId 布局Id
        */
       public MyRecyAdapter(List<Msg> data, Context context, int... layoutId) {
           super(data, context, layoutId);
       }


       @Override
       protected void HelperBindData(HelperViewHolder viewHolder, int position, Msg item) {
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
       public int checkLayoutIndex(Msg item, int position) {
           /**
            * 多子布局样式重写checkLayout()方法，返回对应的index
            * 本例子因为msg的Type对应的就是0和1,所以就直接返回msgType
            */
           return item.getType();
       }
   }
```

```java

    //checklayou的返回值和HelperBindData中判断的position就是这两个id的顺序
    //因为内部会按照checklayou方法的返回值去取在构造方法中传入的id数组中对应的布局文件
    MyRecyAdapter adapter = new MyRecyAdapter(mList, this, R.layout.example_different_item_right, R.layout.example_different_item_left);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(adapter);

```

recyclerView的Adapter和absListView的adapter两者是差不多的


#### 3.普通expandableListView
```java
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter;

/**
*标题数据与子数据在一个实体
*/
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

/**
*标题数据与子数据不在一个实体
*/
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
```
```java

    /**
    *标题数据与子数据在一个实体
    */
adapter1 = new ExampleAdapter(mlist1, this,
  new int[]{R.layout.example_expandable_group}, R.layout.example_item_2);
  exampleexpandableListView.setAdapter(adapter1);
    /**
    *标题数据与子数据不在一个实体
    */
adapter2 = new ExampleAdapter2(groupMsgList, mlist2, this,
  new int[]{R.layout.example_expandable_group}, R.layout.example_item_2);
  exampleexpandableListView.setAdapter(adapter2);
```

#### 4.多子布局的expandableListView

```java  
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter2;


    /**
      *标题数据与子数据在一个实体
      */
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

        @Override
        public int checkChildLayoutIndex(int childPosition, Msg item) {
            return item.getType();
        }

        @Override
        public int checkGroupLayoutIndex(int groupLosition, List<Msg> childs) {
            if (groupLosition % 2 == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    /**
    *标题数据与子数据不在一个实体
    */
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

        @Override
        public int checkChildLayoutIndex(int childPosition, Msg item) {
            return item.getType();
        }

        @Override
        public int checkGroupLayoutIndex(int groupLosition, GroupMsg item, List<Msg> childs) {
            return item.getType();
        }
    }
```

```java
      adapter1 = new ExampleAdapter(mlist, this,
              new int[]{R.layout.example_expandable_group, R.layout.example_expandable_group_2},
              R.layout.example_item_1, R.layout.example_item_2);

      exampleexpandableListView.setAdapter(adapter1);

      adapter2 = new ExampleAdapter2(groupMsgList, mlist, this,
               new int[]{R.layout.example_expandable_group, R.layout.example_expandable_group_2},
               R.layout.example_item_1, R.layout.example_item_2);
      exampleexpandableListView.setAdapter(adapter2);

```

#### 5.ViewPager:使用view和使用fragment

```java

import com.zengcanxiang.baseAdapter.viewpager.BaseAdapter;
import com.zengcanxiang.baseAdapter.viewpager.BaseFragmentAdapter;

public class ViewAdapter extends BaseAdapter {

      public ViewAdapter(Context context) {
          super(context, R.layout.example_viewpager_one,R.layout.example_viewpager_two,R.layout.example_viewpager_three);
      }

      @Override
      public void convert(View view, int position) {

      }
  }

  public class FragmentAdapter extends BaseFragmentAdapter {

      public FragmentAdapter(FragmentManager fm, @NonNull List<Fragment> fragments) {
          super(fm, fragments);
      }
  }

```
```java

  viewAdapter = new ViewAdapter(this);

  List<Fragment> fragments=new ArrayList<>();
  ExampleFragment oneFragment=new ExampleFragment();
  ExampleFragment twoFragment=new ExampleFragment();
  ExampleFragment threeFragment=new ExampleFragment();
  oneFragment.setLayoutId(R.layout.example_viewpager_one);
  twoFragment.setLayoutId(R.layout.example_viewpager_two);
  threeFragment.setLayoutId(R.layout.example_viewpager_three);
  fragments.add(oneFragment);
  fragments.add(twoFragment);
  fragments.add(threeFragment);
  ragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

```

#### 6.headFootViewAdapter(RecyclerView)
```java
import com.zengcanxiang.baseAdapter.recyclerView.HeadFootAdapter;

MyRecyerAdapter mAdapter = new MyRecyerAdapter(mList, this, R.layout.example_item);
HeadFootAdapter headFootAdapter = new HeadFootAdapter(mAdapter) {
            @Override
            public void disposeHeadView(HelperViewHolder viewHolder, int layoutId, int position) {
            }

            @Override
            public void disposeFootView(HelperViewHolder viewHolder, View footView, int position) {

            }
        };
//注意：推荐head或者foot不是listView或者之类的列表控件
//如果超出一屏的话，将他们分割开来，不要一次性写入到一个xml中
headFootAdapter.addHeadView(R.layout.list_head_home);
```

<a href="https://github.com/zengcanxiang/BaseAdapter/tree/master/BaseAdapter/app">example项目</a>

### License

This library is licensed under the [Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

See [`License`](License) for full of the license text.

    Copyright (C) 2016 [Hanks](https://github.com/zengcanxiang)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
