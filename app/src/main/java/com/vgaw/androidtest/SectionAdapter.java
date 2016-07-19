package com.vgaw.androidtest;

import android.content.Context;

import java.util.List;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public abstract class SectionAdapter extends EasyAdapter {

    public SectionAdapter(Context context, List dataList) {
        super(context, dataList);
    }

    public abstract boolean isSection(int position);

}
