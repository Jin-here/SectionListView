package com.vgaw.androidtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vgaw.androidtest.ContactHolder;
import com.vgaw.androidtest.EasyHolder;
import com.vgaw.androidtest.R;
import com.vgaw.androidtest.SectionAdapter;
import com.vgaw.androidtest.SectionHolder;
import com.vgaw.androidtest.SectionListView;

import java.util.ArrayList;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class TestActivity extends Activity {
    private SectionListView slv;
    private SectionAdapter adapter;

    private ArrayList<String> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        slv = (SectionListView) findViewById(R.id.slv);

        ImageView temp = new ImageView(this);
        temp.setImageResource(R.mipmap.ic_launcher);
        slv.addHeaderView(temp);
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
                return (position - slv.getHeaderViewsCount()) % 5 == 0 ? true : false;
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
        slv.init((ViewGroup) findViewById(R.id.v_section), adapter);
        slv.setAdapter(adapter);
    }

    private void getData(){
        for (int i = 0;i < 23;i++){
            dataList.add(String.valueOf(i));
        }
    }
}
