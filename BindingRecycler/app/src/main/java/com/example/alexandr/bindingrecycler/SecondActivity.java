package com.example.alexandr.bindingrecycler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.alexandr.bindingrecycler.databinding.ActivitySecondBinding;
import com.example.alexandr.bindingrecycler.ui.BaseActivity;
import com.example.alexandr.bindingrecycler.model.User;
/**
 * Created by Alexandr on 27.01.2017.
 */

public class SecondActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        User user = getIntent().getExtras().getParcelable(MainActivity.PHOTO_OBJECT);
        ActivitySecondBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        binding.setUser(user);
    }
}
