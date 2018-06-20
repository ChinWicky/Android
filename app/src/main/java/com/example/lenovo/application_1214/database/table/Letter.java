package com.example.lenovo.application_1214.database.table;

/**
 * Created by Lenovo on 2017/12/17.
 */
import java.util.ArrayList;
import java.util.HashMap;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.application_1214.database.DatabaseHelper;


public class Letter implements DatabaseHelper.TableCreateInterface {
    public static String tableName = "Letter";

    public static String _id = "_id";
    public static String UID = "UID";
    public static String PrivateLetterUID = "PrivateLetter_UID";

    public static String content = "PrivateLetter_Content";
    public static String time = "PrivateLetter_Time";
    public static String name = "PrivateLetter_Name";
    public static String photo = "PrivateLetter_Photo";
    public static String isSend = "PrivateLetter_isSend";
    public static String isRead = "PrivateLetter_isRead";



    private static Letter privateLetter = new Letter();

    public static Letter getInstance() {
        return Letter.privateLetter;
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {

        String sql = "CREATE TABLE "
                + Letter.tableName
                + " (  "
                + "_id integer primary key autoincrement, "
                + Letter.UID + " LONG, "
                + Letter.PrivateLetterUID + " LONG, "
              //  + Letter.PrivateLetterID + " LONG, "
                + Letter.content + " TEXT, "
                + Letter.time + " INTEGER, "
                + Letter.name + " TEXT, "
                + Letter.photo + " TEXT, "
                + Letter.isSend + " BOOLEAN "
                + Letter.isRead + " BOOLEAN "
                + ")";
        db.execSQL( sql );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {

        if ( oldVersion < newVersion ) {
            String sql = "DROP TABLE IF EXISTS " + Letter.tableName;
            db.execSQL( sql );
            this.onCreate( db );
        }
    }

    // 插入话题
    public static void insertLetter( DatabaseHelper dbHelper, ContentValues userValues ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(  Letter.tableName, null, userValues );
        db.close();
    }

}

