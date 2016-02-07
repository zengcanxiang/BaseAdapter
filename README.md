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
###使用范例
####1.普通absListView
```java
private class ExampleListAdapter extends HelperAdapter<String> {

        public ExampleListAdapter(List<String> mList, Context context, int... layoutIds) {
            super(mList, context, layoutIds);
        }

        @Override
        public void convert(HelperHolder viewHolder, int position, String s) {
            viewHolder.setText(R.id.example_item_text_view,"listView"+s);
        }
    }
```
####2.多子布局的recyclerView
```java
 private class MyRecyAdapter extends BaseRecyclerViewAdapter<Msg> {
        /**
         * @param data     数据源
         * @param context  上下文
         * @param layoutId 布局Id
         */
        public MyRecyAdapter(List<Msg> data, Context context, int... layoutId) {
            super(data, context, layoutId);
        }

        @Override
        protected void onBindData(BaseRecyclerViewHolder viewHolder, int position, Msg item) {
            switch (item.getType()){
                case 0:
                    viewHolder.setText(R.id.chat_send_content,item.getMsg());
                    break;
                case 1:
                    viewHolder.setText(R.id.chat_from_content,item.getMsg());
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
recyclerView的Adapter使用和以上示例差不多
<a href="https://github.com/zengcanxiang/BaseAdapter/tree/master/BaseAdapter/app">example项目</a>

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

