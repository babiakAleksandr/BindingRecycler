package com.example.alexandr.bindingrecycler.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexandr.bindingrecycler.databinding.MovieItemBinding;
import com.example.alexandr.bindingrecycler.model.User;

import java.util.List;

/**
 * Created by Alexandr on 26.01.2017.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private PhotoClickListener photoClickListener;
    private List<User> userList;

    public interface PhotoClickListener {
        void onClickPhoto(User user);
    }

    public PhotoAdapter(List<User> userList, PhotoClickListener photoClickListener) {
        this.photoClickListener = photoClickListener;
        this.userList = userList;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding binding = MovieItemBinding.inflate(inflater, parent, false);
        return new PhotoHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        User user = userList.get(position);
        holder.binding.setUser(user);
        if (photoClickListener != null) {
            holder.binding.setOnClickListener(photoClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public void removeItem(int position) {
        userList.remove(position);
        notifyDataSetChanged();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {

        MovieItemBinding binding;

        public PhotoHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}