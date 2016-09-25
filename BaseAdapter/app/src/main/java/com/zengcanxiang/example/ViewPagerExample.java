package com.zengcanxiang.example;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zengcanxiang.baseAdapter.viewpager.BaseAdapter;
import com.zengcanxiang.baseAdapter.viewpager.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerExample extends AppCompatActivity {

    private android.support.v4.view.ViewPager exampleviewpager;
    ViewAdapter viewAdapter;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.exampleviewpager = (ViewPager) findViewById(R.id.example_viewpager);
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
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        exampleviewpager.setAdapter(viewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("view ViewPager");
        menu.add("fragment ViewPager");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "view ViewPager":
                exampleviewpager.setAdapter(viewAdapter);
                return true;
            case "fragment ViewPager":
                exampleviewpager.setAdapter(fragmentAdapter);
                return true;
        }
        return false;
    }

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
}
