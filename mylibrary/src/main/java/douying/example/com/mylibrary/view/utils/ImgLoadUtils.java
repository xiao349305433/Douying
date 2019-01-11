package douying.example.com.mylibrary.view.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;


import java.io.File;
import java.security.MessageDigest;

import douying.example.com.mylibrary.R;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;


/**
 * 图片加载工具类(目前使用 Glide)
 * <p>
 *
 * @author fangs
 * @date 2017/5/5
 */
public class ImgLoadUtils {

    private ImgLoadUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 加载指定URL的图片
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.icon_load_error)
                .error(R.drawable.icon_load_error)
                .placeholder(R.drawable.icon_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context)
                .load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())

                .into(imageView);
    }

    public static void loadImage(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.icon_load_error)
                .error(R.drawable.icon_load_error)
                .placeholder(R.drawable.icon_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context)
                .load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }



    /**
     * 加载圆形 图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCirthumbnail(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.icon_load_error)
                .error(R.drawable.icon_load_error)
                .placeholder(R.drawable.icon_placeholder)
                .circleCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .thumbnail(0.1f)
                .into(imageView);
    }

    public static void loadthumbnail(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.icon_load_error)
                .error(R.drawable.icon_load_error)
                .placeholder(R.drawable.icon_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context)
                .load(url)
                .apply(options)
                .thumbnail(0.1f)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

    /**
     * 加载指定URL的图片 不要缓存
     *
     * @param url
     * @param imageView
     */
    public static void loadImages(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.icon_load_error)
                .error(R.drawable.icon_load_error)
                .placeholder(R.drawable.icon_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆形 图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircularBead(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.icon_load_error)
                .error(R.drawable.icon_load_error)
                .placeholder(R.drawable.icon_placeholder)
                .circleCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadCircularBead(Context context, File url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.icon_load_error)
                .error(R.drawable.icon_load_error)
                .placeholder(R.drawable.icon_placeholder)
                .circleCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }


    public static void loadVideoScreenshot(final Context context, String uri, ImageView imageView, long frameTimeMicros) {
        RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros);
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
        requestOptions.transform(new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                return toTransform;
            }

            @Override
            public void updateDiskCacheKey(MessageDigest messageDigest) {
                try {
                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Glide.with(context).load(uri).apply(requestOptions).into(imageView);
    }


    /**
     * 预加载 （把指定URL地址的图片 的原始尺寸保存到缓存中）
     *
     * @param url
     */
    public static void preloadImg(Context context, String url) {
        Glide.with(context)
                .load(url)
                .preload();
    }
    //  设置背景图
    public static void setBg(Context context,String ImageUrl,final View view){
        Glide.with(context).load(ImageUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                view.setBackground(resource);
            }
        });
    }

    //  设置背景图
    public static void setBg(Context context,int ImageUrl,final View view){
        Glide.with(context).load(ImageUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                view.setBackground(resource);
            }
        });
    }

    //  设置背景图
//    public static void setBg2(Context context,String ImageUrl,final View view){
//        Glide.with(context).load(ImageUrl).into(new SimpleTarget<GlideDrawable>() {
//            @Override
//            public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                view.setBackground(glideDrawable);
//            }
//        });
//    }


    /**
     * 异步获取 glide 缓存在磁盘的图片
     *
     * @param context
     * @param url
     * @param consumer
     */
//    @SuppressLint("CheckResult")
//    public static void getImgCachePath(Context context, String url, Consumer<File> consumer) {
//        Observable.just(url)
//                .map(url1 -> {
//                    FutureTarget<File> target = Glide.with(context)
//                            .asFile()
//                            .load(url1)
//                            .submit();
//
//                    return target.get();
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(consumer);
//    }
}
