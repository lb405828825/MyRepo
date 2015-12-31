package com.fineos.transferintentparameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fineos.transferintentparameter.data.intentDataObject;


public class RecDataActivity extends Activity {

    private final static String TAG = "liubo";
    private TextView mTV;
    private EditText mET;
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_data);

        mTV = (TextView) findViewById(R.id.textView2);
        mET = (EditText) findViewById(R.id.editText);
        mBt = (Button) findViewById(R.id.button);

        Intent recIntent = getIntent();
        if (recIntent != null){
            //接收一般数据
            /*String name = recIntent.getStringExtra("name");
            int age = recIntent.getIntExtra("age", 0);
            Log.d(TAG, "name = " + name + ", age = " + age);*/

            //接收Bundle 数据
            /*Bundle dataBundle = recIntent.getExtras();
            String name = dataBundle.getString("name");
            int age = recIntent.getIntExtra("age", 0);
            Log.d(TAG, "name = " + name + ", age = " + age);*/

            //接收Object对象数据 getSerializableExtra
            /*intentDataObject dataObj = (intentDataObject) recIntent.getSerializableExtra("dataObj");
            String name = dataObj.getName();
            int age = dataObj.getAge();
            Log.d(TAG, "name = " + name + ", age = " + age);*/

            //接收Object对象数据 getParcelableExtra
            /*intentDataObject dataObj = recIntent.getParcelableExtra("dataObj");
            String name = dataObj.getName();
            int age = dataObj.getAge();
            Log.d(TAG, "name = " + name + ", age = " + age);*/

            //接收Bundle 数据 携带Parcelable
            Bundle dataBundle = recIntent.getExtras();
            intentDataObject dataObj = dataBundle.getParcelable("dataParcelBundle");
            String name = dataObj.getName();
            int age = dataObj.getAge();
            Log.d(TAG, "name = " + name + ", age = " + age);

            mTV.setText(String.format("get intent info:\nname = %s\nage = %d", name, age));
        }

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent();
                backIntent.putExtra("backStr", mET.getText().toString());
                Log.d(TAG, "result = " + mET.getText().toString());
                setResult(1, backIntent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
