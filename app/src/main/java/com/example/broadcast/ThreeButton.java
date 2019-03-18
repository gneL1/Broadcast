package com.example.broadcast;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class ThreeButton extends Activity implements View.OnClickListener {
    private  MyReceiver receiver = null;
//    private Button mBtnSR;
//    private Button mBtnRR;
//    private Button mBtnUn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        findViewById(R.id.btn_send_receiver).setOnClickListener(this);
        findViewById(R.id.btn_register_receiver).setOnClickListener(this);
        findViewById(R.id.btn_unregister_receiver).setOnClickListener(this);

//        mBtnSR = (Button)findViewById(R.id.btn_send_receiver);
//        mBtnRR = (Button)findViewById(R.id.btn_register_receiver);
//        mBtnUn = (Button)findViewById(R.id.btn_unregister_receiver);
//        setlisteners();
    }

//    private void setlisteners(){
//        OnClick onclick = new OnClick();
//        mBtnUn.setOnClickListener(onclick);
//        mBtnRR.setOnClickListener(onclick);
//        mBtnRR.setOnClickListener(onclick);
//
//
//    }


         public void onClick(View v) {
//    private class OnClick implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_send_receiver:
                    Intent intent = new Intent(MyReceiver.ACTION);
                    intent.putExtra("data", "来自ThreeButton的消息");
                    sendBroadcast(intent);
                    break;
                case R.id.btn_register_receiver:
                    if (receiver == null) {
                        System.out.println("注册receiver");
                        receiver = new MyReceiver();
                        registerReceiver(receiver, new IntentFilter(MyReceiver.ACTION));
                    }
                    break;
                case R.id.btn_unregister_receiver:
                    if (receiver != null) {
                        System.out.println("注销receiver");
                        unregisterReceiver(receiver);
                        receiver = null;
                    }
                    break;

                default:
                    break;
//            }
        }
    }
}
