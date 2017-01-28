package com.example.alexandr.bindingrecycler.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.alexandr.bindingrecycler.R;
import com.example.alexandr.bindingrecycler.databinding.DialogBinding;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String CURRENT_TIME_ACTION = "current_time_action";
    public static final String CURERENT_TIME = "current_time";
    public static final String IMAGE_URL = "image_url";
    TimeReceiver timeReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intentFilter = new IntentFilter(CURRENT_TIME_ACTION);
        timeReceiver = new TimeReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(timeReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onPause();
        unregisterReceiver(timeReceiver);
    }

    public class TimeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CURRENT_TIME_ACTION)) {
                String time = intent.getStringExtra(CURERENT_TIME);
                String image_url = intent.getStringExtra(IMAGE_URL);
                showCustomDialog(time,image_url);
            }
        }
    }

    public void showCustomDialog(String time ,String image_url) {

        DialogBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog, null, false);
        binding.setImage(image_url);
        final Dialog dialog = new Dialog(this);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        dialog.addContentView(binding.getRoot(),params);
        dialog.setTitle("Current time " + time);
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
