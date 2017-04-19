package com.example.database;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.example.database.DBHelper.TBL_NAME;
import static com.example.database.MainActivity.helper;

/**
 * Created by 鲨鱼辣椒 on 2017/4/16.
 */

public class DisplayActivity extends ListActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得Cursor
        db = openOrCreateDatabase(DBHelper.DB_NAME, Context.MODE_PRIVATE, null);
        Cursor cursor = db.query(DBHelper.TBL_NAME, null, null, null, null, null, null);

        //列表项数组
        String[] from = {"_id", "name", "like"};
        //列表项id
        int[] to = {R.id.id, R.id.name, R.id.like};
        //适配器
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.display, cursor, from, to);
        ListView listView = getListView();
        listView.setAdapter(adapter);


        //对话框
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //为ListView添加监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final long temp = id;
                final int temp1 = (int)temp;
                builder.setMessage("是否删除记录？").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db1;
                        db1 = openOrCreateDatabase(DBHelper.DB_NAME, Context.MODE_PRIVATE, null);
                        //删除数据
                        db1.delete(DBHelper.TBL_NAME, "_id=?", new String[]{String.valueOf(temp1)});
                        //数据变化，重新查询并加载进去
                        Cursor cursor = db1.query(DBHelper.TBL_NAME, null, null, null, null, null, null);
                        String[] from = {"_id", "name", "like"};
                        int[] to = {R.id.id, R.id.name, R.id.like};
                        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.display, cursor, from, to);
                        ListView listView = getListView();
                        listView.setAdapter(adapter);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //创建提示对话框
                AlertDialog ad = builder.create();
                //显示对话框
                ad.show();
            }
        });
        //关闭数据库
        db.close();

    }
}
