package com.example.tutorial07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import androidx.annotation.Nullable;

import javax.xml.validation.Validator;

public class DatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE = "library";
        public static final String TBL_USERS = "users";
        public static final String COL_ID = "id";
        public static final String COL_USERNAME = "username";
        public static final String COL_PASSWORD = "password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String sqlCreate= "create table if not exists "+ TBL_USERS+ "(" +
            COL_ID + " integer primary key autoincrement, "+
            COL_USERNAME + " text, "+
            COL_PASSWORD + " text)";
        Log.i("sqlCreate",sqlCreate);
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " +TBL_USERS);
        onCreate(sqLiteDatabase);
    }

    public boolean inserdata(String valUsername, String valPassword) {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COL_USERNAME,valUsername);
        values.put(COL_PASSWORD,valPassword);

       // long result =db.insert(TBL_USERS,null,values);
        return (db.insert(TBL_USERS,null,values)!= -1);
    }

    public boolean validateUser(String valusername, String valpassword) {
        SQLiteDatabase db=getWritableDatabase();
        // Select username,ID From Table where username="" and password =""
        Cursor cursor=db.query(TBL_USERS,
                new String[]{COL_USERNAME,COL_ID},
                "username=? and password=?",
                new String[]{valusername,valpassword},
                null,
                null,
                null);
        return  (cursor !=null && cursor.getCount()>0);

    }
}
