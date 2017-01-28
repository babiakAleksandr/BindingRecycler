package com.example.alexandr.bindingrecycler.ui;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Alexandr on 27.01.2017.
 */

public class Converter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }
}
