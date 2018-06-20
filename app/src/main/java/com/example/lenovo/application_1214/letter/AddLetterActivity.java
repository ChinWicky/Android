package com.example.lenovo.application_1214.letter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.application_1214.R;
import com.example.lenovo.application_1214.broadcast.BroadcastActivity;
import com.example.lenovo.application_1214.database.DatabaseHelper;
import com.example.lenovo.application_1214.database.table.Letter;
import com.example.lenovo.application_1214.database.table.Topic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lenovo on 2017/12/19.
 */
public class AddLetterActivity extends Activity{

    private EditText letterName,UIDText,content;
    private boolean a = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.addletter_layout);

        Button add = (Button)findViewById(R.id.button_addletter_add);
        Button close = (Button)findViewById(R.id.button_addletter_close);
        UIDText = (EditText)findViewById(R.id.edit_addletter_UID);
        letterName = (EditText)findViewById(R.id.edit_addletter_letter);
        content = (EditText)findViewById(R.id.edit_addletter_content);
        Intent intent = getIntent();
        final String u_id = intent.getStringExtra("UID");
        UIDText.setText(u_id);

        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(AddLetterActivity.this,LetterActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
//                IsSelf();
//                checkID();
                if (TextUtils.isEmpty(letterName.getText().toString())) {
                    letterName.setError("题目不能为空");

                }
                if (TextUtils.isEmpty(content.getText().toString())) {
                    content.setError("内容不能为空");

                }
                if (TextUtils.isEmpty(UIDText.getText().toString())) {
                    UIDText.setError("收件人不能为空");

                }
                if(IsSelf()||checkID()){
                    UIDText.setError("请重新输入收件人");
                }
                else {
                    AddLetter();
                }
            }
        });
    }

    private void AddLetter(){
        //IsExist();
        if (true){
            SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
            Date curDate =  new Date(System.currentTimeMillis());
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID",null);
            DatabaseHelper db = new DatabaseHelper(AddLetterActivity.this, "Letter", null, 1);

            ContentValues values = new ContentValues();
            values.put("UID",UID);
            values.put("PrivateLetter_UID",UIDText.getText().toString());
            values.put("PrivateLetter_Name",letterName.getText().toString());
            values.put("PrivateLetter_Content",content.getText().toString());
            values.put("PrivateLetter_Time",formatter.format(curDate));
            values.put("PrivateLetter_isSend","未读");
            Letter.insertLetter(db, values);
            db.close();
            finish();
            Intent intent = new Intent(AddLetterActivity.this,LetterActivity.class);
            startActivity(intent);
        }
    }

    private boolean IsSelf() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String Uid = sharedPreferences.getString("UID", null);
        if (Uid.equals(UIDText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "私信对象不能为自己",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public boolean checkID() {
        boolean non = true;
        String check =UIDText.getText().toString();
        //System.out.println(UIDText.getText().toString()+"check");
        DatabaseHelper db1 = new DatabaseHelper(AddLetterActivity.this, "Account", null, 1);
        SQLiteDatabase db2 = db1.getReadableDatabase();
        Cursor cursor = db2.query("Account", new String[]{"UID"}, "UID=?", new String[]{check}, null, null, null);
        while (cursor.moveToNext()) {
                non = false;
        }
        if (non){
            db1.close();
            db2.close();
            Toast.makeText(getApplicationContext(), "该用户不存在",
                    Toast.LENGTH_SHORT).show();
        }
        return non;
    }
}
