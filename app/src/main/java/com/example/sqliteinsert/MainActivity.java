package com.example.sqliteinsert;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteinsert.data.UserContract.UserEntity;
import com.example.sqliteinsert.data.UserContract;
import static com.example.sqliteinsert.data.UserContract.UserEntity.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass;
    myDbAdapter helper;
    Button ShowData;
    Context context;
    EditText editOld;
    EditText editNew;
    EditText editDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        ShowData = (Button) findViewById(R.id.button2);
        Button btnUpdate = (Button) findViewById(R.id.button3);
        Button btnDelete = (Button) findViewById(R.id.button4);
        editOld = (EditText) findViewById(R.id.editText3);
        editNew = (EditText) findViewById(R.id.editText5);
        editDel = (EditText) findViewById(R.id.editText6);

        helper = new myDbAdapter(this);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = editDel.getText().toString();
                    helper.deleteUser(name);
                    Toast.makeText(MainActivity.this, "User deleted sucessfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "User has not been deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String oldname = editOld.getText().toString();
                    String NewName = editNew.getText().toString();
                    helper.updateUser(oldname, NewName);
                    Toast.makeText(MainActivity.this, "The User has been Updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Failed to Update the User", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
    });


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
