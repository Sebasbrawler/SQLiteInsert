package com.example.sqliteinsert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteinsert.data.UserContract.UserEntity;
import com.example.sqliteinsert.data.UserContract;
import static com.example.sqliteinsert.data.UserContract.UserEntity.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass;
    myDbAdapter helper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        helper = new myDbAdapter(this);
    }

    public void addUser(View view)
    {
        Toast.makeText(this,"Running", Toast.LENGTH_LONG).show();
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        long identity = helper.insertData(t1,t2);
        if(identity<0)
        {
            Message.message(this,"Unsuccessful");
        } else
        {
            Message.message(this,"Successful");
        }
    }
}
