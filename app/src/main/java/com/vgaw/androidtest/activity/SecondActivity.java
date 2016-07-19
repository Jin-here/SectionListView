package com.vgaw.androidtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.vgaw.androidtest.ContactHolder;
import com.vgaw.androidtest.EasyHolder;
import com.vgaw.androidtest.R;
import com.vgaw.androidtest.SectionAdapter;
import com.vgaw.androidtest.SectionHolder;

import java.util.ArrayList;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class SecondActivity extends Activity {
    private ListView lv;
    private ViewGroup v_section;
    private SectionAdapter adapter;
    private ArrayList<String> dataList = new ArrayList<>();

    private View convertView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = (ListView) findViewById(R.id.lv);
        v_section = (ViewGroup) findViewById(R.id.v_section);

        ImageView temp = new ImageView(this);
        temp.setImageResource(R.mipmap.ic_launcher);
        lv.addHeaderView(temp);
        lv.setOnScrollListener(scrollListener);
        getData();
        adapter = new SectionAdapter(this, dataList) {
            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position) {
                return position % 5 == 0 ? 0 : 1;
            }

            // 需要减去header count
            @Override
            public boolean isSection(int position) {
                return (position - 1) % 5 == 0 ? true : false;
            }

            @Override
            public EasyHolder getHolder(int type) {
                if (type == 0){
                    return new SectionHolder();
                }else {
                    return new ContactHolder();
                }
            }
        };
        lv.setAdapter(adapter);
    }

    private AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // section到达顶端，更换当前view，下来
            // 在section到达顶端之前碰到v_section，把v_section往上推
            if (adapter == null){
                return;
            }
            // 默认情况，第一个section.getTop() > 0，section消失
            if (firstVisibleItem < lv.getHeaderViewsCount()){
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v_section.getLayoutParams();
                params.topMargin = -v_section.getHeight();
                v_section.setLayoutParams(params);
            }
            // section到达顶端
            if (adapter.isSection(firstVisibleItem) && lv.getChildAt(0).getTop() <= 0){
                refreshSectionView(firstVisibleItem);
                showSectionView();
                return;
            }
            int top;
            int margin = ((ViewGroup.MarginLayoutParams)v_section.getLayoutParams()).topMargin;
            // 没有完全隐藏的情况下
            if (margin > -v_section.getHeight()){
                for (int i = 0;i < visibleItemCount;i++){
                    View child = lv.getChildAt(i);
                    top = child.getTop();
                    if (adapter.isSection(firstVisibleItem + i)){
                        if (top > 0){
                            int temp = Math.min(v_section.getHeight(), child.getTop());
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v_section.getLayoutParams();
                            params.topMargin = temp - v_section.getHeight();
                            v_section.setLayoutParams(params);
                        }
                        return;
                    }
                }
            }
        }
    };

    // 需要减去header count
    private void refreshSectionView(int position){
        position -= lv.getHeaderViewsCount();
        View view = adapter.getView(position, convertView, null);
        if (v_section.getChildCount() == 0){
            v_section.addView(view);
        }
    }

    private void showSectionView(){
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v_section.getLayoutParams();
        params.topMargin = 0;
        v_section.setLayoutParams(params);
    }

    private void getData(){
        for (int i = 0;i < 23;i++){
            dataList.add(String.valueOf(i));
        }
    }
}
