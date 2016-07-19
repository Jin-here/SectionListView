package com.vgaw.androidtest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class NavigateActivity extends Activity {
    private String[] array = new String[]{"悬浮选项", "SectionListView", "TestActivity"};
    private Class[] classArray = new Class[]{MainActivity.class, SecondActivity.class, TestActivity.class};

    @Override
    protected void onResume() {
        super.onResume();
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("请选择")
                .setItems(array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(NavigateActivity.this, classArray[which]));
                    }
                }).create().show();
    }
}
