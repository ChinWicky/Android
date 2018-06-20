package com.example.lenovo.application_1214.letter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lenovo.application_1214.R;
import com.example.lenovo.application_1214.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2017/12/14.
 */
public class MyLetterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.letter_layout);

        ListView listView = (ListView) findViewById(R.id.letter_list);

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String Uid = sharedPreferences.getString("UID", null);

        //ArrayList<String> arrayList = new ArrayList<>();
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();

        DatabaseHelper db1 = new DatabaseHelper(MyLetterActivity.this, "Letter", null, 1);
        SQLiteDatabase db2 = db1.getReadableDatabase();
        Cursor cursor = db2.query("Letter", new String[]{"UID","_id", "PrivateLetter_Name",}, "UID=?", new String[]{Uid}, null, null, null);
        while (cursor.moveToNext()) {
            String L_Name = cursor.getString(cursor.getColumnIndex("PrivateLetter_Name"));
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            //System.out.println(Uid+"私信题目"+L_Name);
            //arrayList.add(_id);
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("PrivateLetter_Name",L_Name);
            listItem.put("letter_id", _id);
            listItems.add(listItem);
            //arrayList.add(L_Name);
        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this, R.layout.simple_item, arrayList);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                R.layout.simple_item,
                new String[]{"PrivateLetter_Name"},
                new int[]{R.id.letter_name});

        //listView.setAdapter(arrayAdapter);
        listView.setAdapter(simpleAdapter);
        db1.close();
        db2.close();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Map<String,Object> listItem = (Map<String,Object>)parent.getItemAtPosition(position);
//                //String result = parent.getItemAtPosition(position).toString();
//                String result = listItem.get("letter_id").toString();
//                System.out.println("结果："+position);
//               // System.out.println("结果："+listItem.get("_id").toString());
//                Intent intent = new Intent(MyLetterActivity.this,PrivateLetterActivity.class);
//                intent.putExtra("letter_id",result);
//                finish();
//                startActivity(intent);
//            }
//        });
    }




}

