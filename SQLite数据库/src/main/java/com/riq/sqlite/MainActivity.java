package com.riq.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInsert;
    private Button btnDelete;
    private Button btnUpdate;
    private Button btnQuery;
    private Button btnQueryAll;
    private MaleDao maleDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnQuery = (Button) findViewById(R.id.btn_query);
        btnQueryAll = (Button) findViewById(R.id.btn_query_all);

        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnQueryAll.setOnClickListener(this);

        maleDao = new MaleDao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                Male male = new Male("zhang", 2);
                maleDao.add(male);
                Log.e("========male=======", male.toString());
                break;
            case R.id.btn_delete:
                maleDao.delete(1);
                break;
            case R.id.btn_update:
                Male male2 = new Male("wang", 2);
                maleDao.update(male2);
                Log.e("========male2=======", male2.toString());
                break;
            case R.id.btn_query:
                Male male1 = maleDao.findById(1);
                Log.e("========male1=======", male1.toString());
                break;
            case R.id.btn_query_all:
                ArrayList<Male> males = maleDao.findAll();
                for (Male male3 : males) {
                    Log.e("========males3=======", male3.toString());

                }
                break;
        }
    }
}
