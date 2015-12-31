package com.fineos.transferintentparameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fineos.transferintentparameter.data.intentDataObject;

public class MainActivity extends Activity {

    final static String TAG = "liubo";
    private final static String DISPLAY_ACTION = "android.intent.action.DISPLAY_DETAIL";

    private Button mStartSecAct;

    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartSecAct = (Button) findViewById(R.id.startSecAct_btn);
        mTvResult = (TextView) findViewById(R.id.textView4);

        mStartSecAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secIntent = new Intent(DISPLAY_ACTION);
                //传递一般数据
                /*secIntent.putExtra("name", "xiaoming");
                secIntent.putExtra("age", 22);*/

                //传递 Bundle
                /*Bundle dataBundle = new Bundle();
                dataBundle.putString("name", "this is data bundle");
//                dataBundle.putInt("age", 22);dataBundle.putBundle("bundle", new Bundle());
                //dataBundle.putParcelable();

                secIntent.putExtras(dataBundle);*/

                //传递对象,Serializable
                /*intentDataObject dataObj = new intentDataObject();
                dataObj.setName("transferObj");
                dataObj.setAge(331);
                secIntent.putExtra("dataObj", dataObj);*/

                //传递对象, Parcelable
                // Parcelable 的速度效率 比 Serializable高,
                /*intentDataObject dataObj = new intentDataObject("transferObj", 44);
                secIntent.putExtra("dataObj", dataObj);*/

                //传递Bundle中的 Parcelable
                intentDataObject dataObj = new intentDataObject("transferObj", 55);
                Bundle dataParcelableBundle = new Bundle();
                dataParcelableBundle.putParcelable("dataParcelBundle", dataObj);
                secIntent.putExtras(dataParcelableBundle);

                startActivityForResult(secIntent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode = " + requestCode + ", resultCode = " + ", Intent = " + data +
                "\nback data = " + data.getStringExtra("backStr"));
        if(0 == requestCode){
            mTvResult.setText(data.getStringExtra("backStr"));
        }
    }
}
