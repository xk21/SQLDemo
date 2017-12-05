package com.jjyh.it.sqldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjyh.it.sqldemo.order.TestDBHelper;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TestDBHelper testDBHelper;
    private Button select_btn;
    private Button insert_btn;
    private Button update_btn;
    private Button delete_bt;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testDBHelper = TestDBHelper.getInstance(getApplicationContext());
        select_btn = findViewById(R.id.select_btn);
        insert_btn = findViewById(R.id.insert_btn);
        update_btn = findViewById(R.id.update_btn);
        delete_bt = findViewById(R.id.delete_bt);
        textView = findViewById(R.id.textView);

        select_btn.setOnClickListener(this);
        insert_btn.setOnClickListener(this);
        update_btn.setOnClickListener(this);
        delete_bt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_btn:
                List<Map> list = testDBHelper.queryListMap("select * from user",null);
                textView.setText(String.valueOf(list));
                break;
            case R.id.insert_btn:
                testDBHelper.insert("user",new String[]{"name","gender","age"},new Object[]{"qiangyu","male",23});
                break;
            case R.id.update_btn:
                testDBHelper.update("user",new String[]{"name","gender","age"},new Object[]{"yangqiangyu","male",24},
                        new String[]{"name"},new String[]{"qiangyu"});
                break;
            case R.id.delete_bt:
                testDBHelper.delete("user",
                        new String[]{"name"},new String[]{"qiangyu"});
                break;
        }
    }

}
