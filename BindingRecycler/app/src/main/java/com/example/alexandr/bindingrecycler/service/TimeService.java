package com.example.alexandr.bindingrecycler.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.alexandr.bindingrecycler.MainActivity;
import com.example.alexandr.bindingrecycler.ui.BaseActivity;

import java.util.Date;

/**
 * Created by Alexandr on 27.01.2017.
 */

public class TimeService extends IntentService {

    private static final String TAG = TimeService.class.getSimpleName();

    public TimeService() {
        super("myName");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Date d = new Date();
        CharSequence s = DateFormat.format("hh:mm", d.getTime());
        final Intent timeIntent = new Intent(MainActivity.CURRENT_TIME_ACTION);
        timeIntent.putExtra(BaseActivity.CURERENT_TIME, s.toString());
        timeIntent.putExtra(BaseActivity.IMAGE_URL,"https://avatars.githubusercontent.com/u/3?v=3");
        sendBroadcast(timeIntent);

    }

}
