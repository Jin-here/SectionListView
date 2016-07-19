package com.vgaw.androidtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ListView;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class SectionListView extends ListView {
    private ViewGroup v_section;
    private SectionAdapter adapter;
    private View convertView;
    // 是否遍历
    private boolean shouldBL = true;
    private int lastSection = -1;

    public SectionListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(ViewGroup v_section, SectionAdapter adapter){
        this.v_section = v_section;
        setOnScrollListener(scrollListener);
        this.adapter = adapter;
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
            if (firstVisibleItem < getHeaderViewsCount()){
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v_section.getLayoutParams();
                params.topMargin = -v_section.getHeight();
                v_section.setLayoutParams(params);
                return;
            }
            // section到达顶端
            if (adapter.isSection(firstVisibleItem) && getChildAt(0).getTop() <= 0){
                shouldBL = true;
                refreshSectionView(firstVisibleItem, false);
                showSectionView();
                return;
            }
            int top;
            int margin = ((ViewGroup.MarginLayoutParams)v_section.getLayoutParams()).topMargin;
            // 没有完全隐藏的情况下
            if (margin > -v_section.getHeight()){
                for (int i = 0;i < visibleItemCount;i++){
                    View child = getChildAt(i);
                    top = child.getTop();
                    if (adapter.isSection(firstVisibleItem + i)){
                        if (top > 0){
                            if (top > v_section.getHeight() + margin){
                                refreshSectionView(firstVisibleItem, true);
                            }
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
    private void refreshSectionView(int position, boolean isLast){
        if (isLast){
            // 遍历找出上一个section
            if (shouldBL){
                shouldBL = false;
                for (int i = position;i > -1;i--){
                    if (adapter.isSection(i)){
                        lastSection = i;
                        break;
                    }
                }
            }
            position = lastSection;
        }
        position -= getHeaderViewsCount();
        convertView = adapter.getView(position, convertView, null);
        if (v_section.getChildCount() == 0){
            v_section.addView(convertView);
        }
    }

    private void showSectionView(){
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v_section.getLayoutParams();
        params.topMargin = 0;
        v_section.setLayoutParams(params);
    }
}
