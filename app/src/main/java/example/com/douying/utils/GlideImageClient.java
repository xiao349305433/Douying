package example.com.douying.utils;

import android.content.Context;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import douying.example.com.vpager.ImageLoadClient;

/**
 * Created by admin on 2018/9/12.
 */

public class GlideImageClient extends ImageLoadClient {
    @Override
    public void loadImage(ImageView imageView, Object obj, Context context) {
        Glide.with(context).load(obj).into(imageView);
    }
}
