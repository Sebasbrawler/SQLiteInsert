package com.example.sqliteinsert;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqliteinsert.data.UserContract;
import com.example.sqliteinsert.data.UserContract.UserEntity;

import java.util.ArrayList;

/**
 * Created by sebas on 3-11-2017.
 */

public class ResultActivity extends AppCompatActivity {


    private static final String TAG = "ResultActivity";

    myDbAdapter myDbHelper;

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mListView = (ListView) findViewById(R.id.listView);
        myDbHelper = new myDbAdapter(this);

        populateListView();
    }

    private void populateListView() {
        Cursor data = myDbHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
