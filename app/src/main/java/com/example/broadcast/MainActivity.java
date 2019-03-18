package com.example.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnButton;//声明控件
    private Button mBtnButton1;
    private Button mBtnButton2;
    private Button mBtnButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnButton = (Button)findViewById(R.id.Btn_1);//创建布局时找到控件
        mBtnButton1 = (Button)findViewById(R.id.Btn_2);
        mBtnButton2 = (Button)findViewById(R.id.Btn_3);
        mBtnButton4 = (Button)findViewById(R.id.Btn_4);
        setListeners();//同时设置监听事件
        Log.d("MyService", "MyService 1111111111111thread id is " + Thread.currentThread().getId());

    }

    private void setListeners(){
        OnClick onClick = new OnClick();
        mBtnButton.setOnClickListener(onClick);
        mBtnButton1.setOnClickListener(onClick);
        mBtnButton2.setOnClickListener(onClick);
        mBtnButton4.setOnClickListener(onClick);//    监听事件类型：当点击时
    }



    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.Btn_1:
                    intent = new Intent(MainActivity.this,MyBroadReceiver.class);
                    break;
                case R.id.Btn_2:
                    intent = new Intent(MainActivity.this,BroadcastTestActivity.class);
                    break;
                case R.id.Btn_3:
                    intent = new Intent(MainActivity.this,ThreeButton.class);
                    break;
                case R.id.Btn_4:
                    intent = new Intent(MainActivity.this,ServiceMain.class);
                    break;

            }

            startActivity(intent);
        }
    }
}
