package com.example.sqliteinsert;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteinsert.data.UserContract;
import com.example.sqliteinsert.data.UserContract.UserEntity;


public class myDbAdapter {

    myDbHelper helper;

    public myDbAdapter(Context context) {
        helper = new myDbHelper(context);
    }

    public long insertData(String name, String password) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.PASSWORD, password);
        long id = db.insert(UserEntity.TABLE_NAME, null, contentValues);
        return id;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String UID = UserEntity.UID;
        private static final String NAME = UserEntity.USER_NAME;
        private static final String PASSWORD = UserEntity.USER_PWD;

        private static final String CREATE_TABLE = "CREATE TABLE " + UserEntity.TABLE_NAME +
                "( " + UserEntity.UID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + UserEntity.USER_NAME + " TEXT, " + UserEntity.USER_PWD + " TEXT;";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + UserEntity.TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, UserContract.DATABASE_NAME, null, UserContract.DATABASE_VERSION);
            this.context = context;
            Message.message(context, "Started...");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {

                db.execSQL(CREATE_TABLE);
                Message.message(context, "TABLE CREATED");
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}

//   static class myDbHelper extends SQLiteOpenHelper
//   {
//       private static final String DATABASE_NAME = "myDatabase";
//       private static final String TABLE_NAME = "myTable";
//       private static final int DATABASE_Version = 1;
//       private static final String UID="_id";
//       private static final String NAME = "Name";
//       private static final String PASSWORD= "Password";
//       private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME +
//               "( "+UID+" INTEGER PRIMARY KEY AUTOINCREMENT ," +NAME+ " VARCHAR(225) ," + PASSWORD+" VARCHAR(225));";
//      // private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
//       private Context context;
//
//       public myDbHelper(Context context) {
//           super(context, DATABASE_NAME, null, DATABASE_Version);
//           this.context=context;
//           Message.message(context,"Started...");
//       }
//
//       @Override
//       public void onCreate(SQLiteDatabase db) {
//           try {
//
//               db.execSQL(CREATE_TABLE);
//               Message.message(context,"TABLE CREATED");
//           } catch (Exception e) {
//              Message.message(context,""+e);
//           }
//       }
//
//       @Override
//       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//          /* try {
//               Message.message(context,"OnUpgrade");
//               db.execSQL(DROP_TABLE);
//               onCreate(db);
//           }catch (Exception e) {
//               Message.message(context,""+e);
//                          }*/
//       }
//   }
//}
