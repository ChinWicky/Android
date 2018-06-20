package com.example.lenovo.application_1214.letter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;

import com.example.lenovo.application_1214.loginSignin.ProfileActivity;
import com.example.lenovo.application_1214.R;
import com.example.lenovo.application_1214.broadcast.BroadcastActivity;
import com.example.lenovo.application_1214.broadcast.MyTopicActivity;
import com.example.lenovo.application_1214.database.DatabaseHelper;
import com.example.lenovo.application_1214.friend.FriendActivity;
import com.example.lenovo.application_1214.loginSignin.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2017/12/14.
 */
public class LetterActivity extends Activity {
    RadioGroup radioGroup = null;
    RadioButton broadcast = null;
    RadioButton friend = null;
    RadioButton letter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.letter_layout);

        ImageView add = (ImageView) findViewById(R.id.letter_add);
        ImageView refresh = (ImageView) findViewById(R.id.letter_refresh);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup_letter);
        broadcast = (RadioButton)findViewById(R.id.radio_button_letter_broadcast);
        friend = (RadioButton)findViewById(R.id.radio_button_letter_friend);
        letter = (RadioButton)findViewById(R.id.radio_button_letter_letter);
        letter.setChecked(true);

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId){
                if(checkedId==broadcast.getId()){
                    finish();
                    Intent intent = new Intent(LetterActivity.this,BroadcastActivity.class);
                    startActivity(intent);
                }
                if(checkedId==friend.getId()){
                    finish();
                    Intent intent = new Intent(LetterActivity.this,FriendActivity.class);
                    startActivity(intent);
                }
                if (checkedId==letter.getId()){

                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LetterActivity.this, LetterActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LetterActivity.this, AddLetterActivity.class);
                startActivity(intent);

            }
        });

        ListView listView = (ListView) findViewById(R.id.letter_list);

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String Uid = sharedPreferences.getString("UID", null);

        //ArrayList<String> arrayList = new ArrayList<>();
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();

        DatabaseHelper db1 = new DatabaseHelper(LetterActivity.this, "Letter", null, 1);
        SQLiteDatabase db2 = db1.getReadableDatabase();
        Cursor cursor = db2.query("Letter", new String[]{"UID","_id", "PrivateLetter_Name","PrivateLetter_isSend"}, "PrivateLetter_UID=?", new String[]{Uid}, null, null, null);
        while (cursor.moveToNext()) {
            String L_Name = cursor.getString(cursor.getColumnIndex("PrivateLetter_Name"));
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            String isRead = cursor.getString(cursor.getColumnIndex("PrivateLetter_isSend"));
            //System.out.println(Uid+"私信题目"+L_Name);
            //arrayList.add(_id);
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("PrivateLetter_Name",L_Name);
            listItem.put("letter_id", _id);
            listItem.put("PrivateLetter_isSend", isRead);
            listItems.add(listItem);
            //arrayList.add(L_Name);
        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this, R.layout.simple_item, arrayList);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                R.layout.simple_item,
                new String[]{"PrivateLetter_Name","PrivateLetter_isSend"},
                new int[]{R.id.letter_name,R.id.letter_isRead});

        //listView.setAdapter(arrayAdapter);
        listView.setAdapter(simpleAdapter);
        db1.close();
        db2.close();

        /*listView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FriendActivity.this, FindFriendActivity.class);
                startActivity(intent);
            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Map<String,Object> listItem = (Map<String,Object>)parent.getItemAtPosition(position);
                String result = listItem.get("letter_id").toString();

                    DatabaseHelper db1 = new DatabaseHelper(LetterActivity.this, "Letter", null, 1);
                    SQLiteDatabase db2 = db1.getReadableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("PrivateLetter_isSend", "已读");
                    db2.update("Letter", values, "_id=?", new String[]{result});
                    db1.close();
                    db2.close();

                System.out.println("结果："+position);
                Intent intent = new Intent(LetterActivity.this,PrivateLetterActivity.class);
                intent.putExtra("letter_id",result);
                finish();
                startActivity(intent);
            }
        });
    }

    public boolean onKeyDown(int keyCode,KeyEvent event){
        if (keyCode ==KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(LetterActivity.this)
                    .setTitle("注意")
                    .setMessage("确定要退出？")
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create().show();
        }
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.menu_topic:
                startActivity(new Intent(this,MyTopicActivity.class));
                break;
            case R.id.menu_letter:
                startActivity(new Intent(this,MyLetterActivity.class));
                break;
            case  R.id.menu_logout:
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.menu_exit:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }



}

