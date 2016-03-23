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
compile 'com.zengcanxiang.baseAdapter:baseadapter:1.3.0'
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
        public void convert(HelperHolder viewHolder, int position, String s) {
            viewHolder.setText(R.id.example_item_text_view,"listView"+s);
        }
    }
```
####2.Multiple sub layout recyclerView
```java
 private class MyRecyAdapter extends BaseRecyclerViewAdapter<Msg> {
        /**
         * @param data     data source
         * @param context  context
         * @param layoutId layoutId
         */
        public MyRecyAdapter(List<Msg> data, Context context, int... layoutId) {
            super(data, context, layoutId);
            //super(mList,context,R.layout.xxx1,R.layout.xxx2);
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
             * Multi layout style override checkLayout () method
             * returns the corresponding index
             * this example because the Type MSG corresponds to 0 and 
             * 1, so directly to return msgType
             */
            return item.getType();
        }
    }
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
