package com.zengcanxiang.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Jump(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.JumpAbsView:
                intent = new Intent(MainActivity.this, AbsListViewExample.class);
                startActivity(intent);
                break;
            case R.id.JumpAbsView2:
                intent = new Intent(MainActivity.this, AbsListViewExampleTwo.class);
                startActivity(intent);
                break;
            case R.id.RecyclerView:
                intent = new Intent(MainActivity.this, RecyclerViewExample.class);
                startActivity(intent);
                break;
            case R.id.RecyclerView2:
                intent = new Intent(MainActivity.this, RecyclerViewExampleTwo.class);
                startActivity(intent);
                break;
            case R.id.expandableListView:
                intent = new Intent(MainActivity.this, ExpandableListViewExample.class);
                startActivity(intent);
                break;
            case R.id.expandableListView2:
                intent = new Intent(MainActivity.this, ExpandableListViewExampleTwo.class);
                startActivity(intent);
                break;
            case R.id.viewpager:
                intent = new Intent(MainActivity.this, ViewPagerExample.class);
                startActivity(intent);
                break;
            case R.id.headFootRecyclerView:
                intent = new Intent(MainActivity.this, RecyclerViewOtherView.class);
                startActivity(intent);
                break;
        }
    }
}
