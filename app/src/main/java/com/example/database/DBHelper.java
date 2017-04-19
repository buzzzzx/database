package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by 鲨鱼辣椒 on 2017/4/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    //数据库名，表名
    public static final String DB_NAME = "Stu.db";
    public static final String TBL_NAME = "stuTbl";

    //创建表的语句
    private static final String CREATE_TBL = "create table stuTbl ("
            +"_id integer primary key autoincrement, "
            + "name text, "
            + "like text)";

    private Context mContext;

    //SQLiteDatabase实例
    private SQLiteDatabase db;

    //构造方法
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        //System.out.printf("创建数据库");
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
        //System.out.printf("创建表");
    }

    /*
    //插入方法
    public void insert(ContentValues values) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入
        db.insert(TBL_NAME, null, values);
        db.close();
        System.out.printf("数据库插入操作");
    }
*/
    /*
    //查询方法
    public Cursor query() {
        System.out.printf("数据库查询方法");
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        //查询获得Cursor
        Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
        return cursor;
    }


    //删除方法
    public void del(int id) {
        System.out.printf("数据库删除方法");
        if (db == null) {
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            //删除
            db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
        }
    }

    //关闭数据库
    public void close() {
        System.out.printf("数据库关闭方法");
        if (db != null) {
            db.close();
        }
    }
*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
