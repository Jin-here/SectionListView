package com.vgaw.androidtest;

import android.view.View;
import android.widget.TextView;

import java.util.Random;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class SectionHolder extends EasyHolder {
    private TextView tv_section;
    @Override
    public int getLayout() {
        return R.layout.item_section;
    }

    @Override
    public View createView() {
        tv_section = (TextView) view.findViewById(R.id.tv_section);
        return view;
    }

    @Override
    public void refreshView(Object item) {
        tv_section.setText(String.valueOf(item));
    }
}
