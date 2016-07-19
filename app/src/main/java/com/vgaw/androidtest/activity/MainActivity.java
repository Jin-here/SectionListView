package com.vgaw.androidtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vgaw.androidtest.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private View headerView;
    private View v_choice;

    private ArrayList<String> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        proChoice();

        proHeader();
        lv.addHeaderView(headerView);
        lv.setOnScrollListener(scrollListener);

        getData();
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.test_list_item, android.R.id.text1, dataList));

    }

    private AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            int choiceHeight = v_choice.getHeight();
            int bottom = Math.max(headerView.getBottom(), choiceHeight);
            // TODO: 2016/7/13 为什么此处用layout首次显示出错
            //v_choice.layout(0, bottom - choiceHeight, v_choice.getWidth(), bottom);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)v_choice.getLayoutParams();
            params.topMargin = bottom - choiceHeight;
            v_choice.setLayoutParams(params);
        }
    };

    private void proChoice(){
        v_choice = findViewById(R.id.v_choice);
        TextView tv_one = (TextView) findViewById(R.id.tv_one);
        TextView tv_two = (TextView) findViewById(R.id.tv_two);
        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "one", Toast.LENGTH_SHORT).show();
            }
        });
        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "two", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void proHeader(){
        headerView = LayoutInflater.from(this).inflate(R.layout.header, null);
        ImageView iv = (ImageView) headerView.findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "iv", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(){
        for (int i = 0;i < 23;i++){
            dataList.add(String.valueOf(i));
        }
    }
}
