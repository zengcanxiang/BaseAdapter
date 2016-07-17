# BaseAdapter
This project is to encapsulate listview or GridView as well as Adapter. RecyclerView this project is to watch the Zhang Hongyang teacher's video and inspired by the also has a lot of older projects on the github:
<ul>
  <li><a href="https://github.com/tianzhijiexian/CommonAdapter">CommonAdapter</a></li>
  <li><a href="https://github.com/hongyangAndroid/base-adapter-helper">hongyangAndroid:base-adapter-helper</a></li>
  <li><a href="https://github.com/JoanZapata/base-adapter-helper">JoanZapata:base-adapter-helper</a></li>
  <li>.....</li>
</ul>
But in terms of personal habits, it is more complex to feel the project, to absorb the essence, to simplify the use of it.

[中文文档][1]

## compile
```java
compile 'com.zengcanxiang.baseAdapter:baseadapter:1.4.3
```   
###Example
####1.ordinary absListView
```java
private class ExampleListAdapter extends HelperAdapter<String> {

        public ExampleListAdapter(List<String> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
            //super(mList,context,R.layout.xxxx);
        }

        @Override
        public void HelpConvert(HelperHolder viewHolder, int position, String s) {
            viewHolder.setText(R.id.example_item_text_view,"listView"+s);
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
```
```java
    
    //The return value of the checklayou and the position in the HelperBindData is the order of the two ID
    //Because of the internal checklayou method will return the value corresponding to the ID array layout file in the constructor in accordance with the incoming

    MyRecyAdapter adapter = new MyRecyAdapter(mList, this, R.layout.example_different_item_right,             R.layout.example_different_item_left);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(adapter);
    
```

Adapter's absListView and adapter's recyclerView are almost the same.
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
