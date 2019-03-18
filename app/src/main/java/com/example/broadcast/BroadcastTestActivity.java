package com.example.broadcast;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BroadcastTestActivity extends AppCompatActivity {
    public static String INTENT_INFO;
    private Button mBtnButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);
        mBtnButton = (Button)findViewById(R.id.Btn_11);
        setListeners();
    }

    private void setListeners(){
         OnClick onClick = new OnClick();
         mBtnButton.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyBroadReceiver.ACTION);
            intent.putExtra(INTENT_INFO,"这是一个有序的广播");
            sendBroadcast(intent);
        }
    }
}
