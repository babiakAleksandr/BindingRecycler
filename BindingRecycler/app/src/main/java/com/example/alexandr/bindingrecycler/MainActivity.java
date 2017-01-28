package com.example.alexandr.bindingrecycler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.alexandr.bindingrecycler.model.User;
import com.example.alexandr.bindingrecycler.net.ApiService;
import com.example.alexandr.bindingrecycler.net.RetrofitBuilder;
import com.example.alexandr.bindingrecycler.service.TimeService;
import com.example.alexandr.bindingrecycler.ui.BaseActivity;
import com.example.alexandr.bindingrecycler.ui.PhotoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AlarmManager am;
    public static final String PHOTO_OBJECT = "photoObject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadPhotos();


    }

    @Override
    protected void onResume() {
        super.onResume();
        createAlarmManager();
    }

    public void loadPhotos() {
        ApiService apiService = RetrofitBuilder.getClient().create(ApiService.class);
        apiService.getPhotos().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();

                mAdapter = new PhotoAdapter(userList, new PhotoAdapter.PhotoClickListener() {
                    @Override
                    public void onClickPhoto(User user) {
                        Intent secondActivityIntent = new Intent(MainActivity.this, SecondActivity.class);
                        secondActivityIntent.putExtra(PHOTO_OBJECT, user);
                        startActivity(secondActivityIntent);

                    }
                });
                mRecyclerView.setAdapter(mAdapter);

                ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Remove swiped item from list and notify the RecyclerView
                        mAdapter.removeItem(viewHolder.getAdapterPosition());
                    }
                };

                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
                itemTouchHelper.attachToRecyclerView(mRecyclerView);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "error " + t);
            }
        });

    }

    private void createAlarmManager() {
        Intent intent = new Intent(this, TimeService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 10_000, 120_000, pendingIntent);
    }

}
