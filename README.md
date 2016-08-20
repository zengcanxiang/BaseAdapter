# BaseAdapter
This project is to encapsulate listview or GridView as well as Adapter. RecyclerView this project is to watch the Zhang Hongyang teacher's video and inspired by the also has a lot of older projects on the github:
<ul>
  <li><a href="https://github.com/tianzhijiexian/CommonAdapter">CommonAdapter</a></li>
  <li><a href="https://github.com/hongyangAndroid/base-adapter-helper">hongyangAndroid:base-adapter-helper</a></li>
  <li><a href="https://github.com/JoanZapata/base-adapter-helper">JoanZapata:base-adapter-helper</a></li>
  <li>.....</li>
</ul>
But in terms of personal habits, it is more complex to feel the project, to absorb the essence, to simplify the use of it.

![示例](https://github.com/zengcanxiang/BaseAdapter/blob/master/Animation.gif)

[中文文档][1]

## compile
```java
compile 'com.zengcanxiang.baseAdapter:baseadapter:1.4.5
```   
###Example
####1.ordinary absListView
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

    //Because here is the layoutId as a parameter to pass in, in general, will be in the adapter
    //internal configuration
    //if adapter less code, without many logic, more suitable for creating internal classes.
    //demo is the creation of the internal class

    ExampleListAdapter adapter1=new ExampleListAdapter(mList,this,R.layout.example_item);
    ExampleGridAdapter adapter2=new ExampleGridAdapter(mList,this,R.layout.example_item);

    examplelistview.setAdapter(adapter1);
    examplegridview.setAdapter(adapter2);
```
####2.Multiple sub layout recyclerView
```java
import com.zengcanxiang.baseAdapter.recyclerView.HelperAdapter;
import com.zengcanxiang.baseAdapter.recyclerView.HelperViewHolder;

private class MyRecyAdapter extends HelperAdapter<Msg> {
       /**
        * @param data     data
        * @param context  context
        * @param layoutId Id
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
            * Multi layout style override checkLayout () method returns the corresponding index
            * This example because the Type MSG corresponds to 0 and 1, so directly to return msgType
            */
           return item.getType();
       }
   }
```
```java

    //The return value of the checklayou and the position in the HelperBindData is the order of the two ID
    //Because of the internal checklayou method will return the value corresponding to the ID array layout file in the constructor in accordance with the incoming

    MyRecyAdapter adapter = new MyRecyAdapter(mList, this, R.layout.example_different_item_right,             R.layout.example_different_item_left);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(adapter);

```

Adapter's absListView and adapter's recyclerView are almost the same.



#### 3.ordinary expandableListView
```java
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter;

/**
*Header data and sub data in an entity
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
*Header data and sub data are not in an entity
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
    *Header data and sub data in an entity
    */
adapter1 = new ExampleAdapter(mlist1, this,
  new int[]{R.layout.example_expandable_group}, R.layout.example_item_2);
  exampleexpandableListView.setAdapter(adapter1);
    /**
    *Header data and sub data are not in an entity
    */
adapter2 = new ExampleAdapter2(groupMsgList, mlist2, this,
  new int[]{R.layout.example_expandable_group}, R.layout.example_item_2);
  exampleexpandableListView.setAdapter(adapter2);
```

#### 4.Multiple sub layout expandableListView

```java  
import com.zengcanxiang.baseAdapter.absListView.HelperViewHolder;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.expandableListView.HelperAdapter2;


    /**
      *Header data and sub data in an entity
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
    *Header data and sub data are not in an entity
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

<a href="https://github.com/zengcanxiang/BaseAdapter/tree/master/BaseAdapter/app">example</a>

### License

This library is licensed under the [Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

See [`LICENSE`](LICENSE) for full of the license text.

    Copyright (C) 2015 [Hanks](https://github.com/ZengcxAperson)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


  [1]: https://github.com/zengcanxiang/BaseAdapter/blob/master/README-zh.md
