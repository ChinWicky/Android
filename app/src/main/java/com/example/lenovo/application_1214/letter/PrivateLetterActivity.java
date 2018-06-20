package com.example.lenovo.application_1214.letter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.application_1214.R;
import com.example.lenovo.application_1214.broadcast.BroadcastActivity;
import com.example.lenovo.application_1214.broadcast.DiscussActivity;
import com.example.lenovo.application_1214.database.DatabaseHelper;

/**
 * Created by Lenovo on 2017/12/19.
 */
public class PrivateLetterActivity extends Activity{
    TextView time;
    TextView uid;
    TextView name;
    TextView content;
    Button close;
    Button reletter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.privateletter_layout);

        time = (TextView)findViewById(R.id.text_letter_time);
        uid = (TextView)findViewById(R.id.text_letter_UID);
        name = (TextView)findViewById(R.id.text_letter_name);
        content = (TextView)findViewById(R.id.text_letter_content);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
        close = (Button)findViewById(R.id.button_letter_close);
        reletter = (Button)findViewById(R.id.button_letter_return);

        Intent intent = getIntent();
        final String letter_id = intent.getStringExtra("letter_id");

        DatabaseHelper db1 = new DatabaseHelper(PrivateLetterActivity.this, "Letter", null, 1);
        SQLiteDatabase db2 = db1.getReadableDatabase();
        Cursor cursor = db2.query("Letter", new String[]{"UID", "PrivateLetter_Name", "PrivateLetter_Time", "PrivateLetter_Content"}, "_id=?", new String[]{letter_id}, null, null, null);
        while (cursor.moveToNext()) {
            String UID = cursor.getString(cursor.getColumnIndex("UID"));
            String L_Time = cursor.getString(cursor.getColumnIndex("PrivateLetter_Time"));
            String L_Name = cursor.getString(cursor.getColumnIndex("PrivateLetter_Name"));
            String L_Content = cursor.getString(cursor.getColumnIndex("PrivateLetter_Content"));
            time.setText(L_Time);
            uid.setText(UID);
            name.setText(L_Name);
            content.setText(L_Content);
            db1.close();
            db2.close();
        }

        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(PrivateLetterActivity.this,LetterActivity.class);
                startActivity(intent);
            }
        });

        reletter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(PrivateLetterActivity.this,AddLetterActivity.class);
                intent.putExtra("UID",uid.getText());
                startActivity(intent);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(PrivateLetterActivity.this, BroadcastActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
