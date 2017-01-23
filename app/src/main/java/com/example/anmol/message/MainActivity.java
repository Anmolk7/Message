package com.example.anmol.message;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    private BroadcastReceiver intentReceiver= new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            TextView inTxt=(TextView)findViewById(R.id.textView);
            inTxt.setText(intent.getExtras().getString("message"));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
    }

    @Override
    protected void onResume()
    {
        registerReceiver(intentReceiver,intentFilter);
    }
    @Override
    protected void onPause()
    {
        unregisterReceiver(intentReceiver);
        super.onPause();
    }
}
