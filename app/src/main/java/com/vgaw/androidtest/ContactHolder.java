package com.vgaw.androidtest;

import android.view.View;
import android.widget.TextView;

import java.util.Random;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class ContactHolder extends EasyHolder {
    private TextView tv_name;
    @Override
    public int getLayout() {
        return R.layout.item_contact;
    }

    @Override
    public View createView() {
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        return view;
    }

    @Override
    public void refreshView(Object item) {
        tv_name.setText(String.valueOf(new Random().nextInt()));
    }
}
