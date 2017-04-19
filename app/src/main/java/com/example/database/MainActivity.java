package com.example.database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static DBHelper helper;

    private EditText et1, et2;
    private Button addData;
    private Button createDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.name);
        et2 = (EditText) findViewById(R.id.like);

        helper = new DBHelper(this, DBHelper.DB_NAME, null, 1);

        createDatabase = (Button) findViewById(R.id.create);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.getWritableDatabase();
            }
        });

        addData = (Button) findViewById(R.id.add);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();

                String name = et1.getText().toString();
                String like = et2.getText().toString();
                ContentValues values = new ContentValues();
                //在values中添加内容
                values.put("name", name);
                values.put("like", like);

                db.insert(DBHelper.TBL_NAME, null, values);
                values.clear();

                //实例化Intent
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                //intent.putExtra("helper_data", helper);
                startActivity(intent);
            }
        });
    }
}
