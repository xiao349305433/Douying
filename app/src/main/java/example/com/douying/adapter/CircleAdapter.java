package example.com.douying.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.ItemImageLongClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.MyJZVideoPlayerStandard;
import douying.example.com.mylibrary.view.utils.EncodeUtils;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.R;
import example.com.douying.activity.DragPhotoActivity;

import example.com.douying.http.MainHttp;
import example.com.douying.model.GetArticlesListM;
import example.com.douying.view.ExpandableTextView;


/**
 * Created by admin on 2018/11/19.
 */
public class CircleAdapter extends BaseQuickAdapter<GetArticlesListM.DataBean, BaseViewHolder> {
    public CircleAdapter() {
        super(R.layout.item_circle);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void convert(BaseViewHolder helper, GetArticlesListM.DataBean item) {

        helper.addOnClickListener(R.id.item_circle_like);
        helper.addOnClickListener(R.id.item_circle_liketv);
        helper.addOnClickListener(R.id.item_circle_comment);
        helper.setText(R.id.item_circle_time,item.getAdd_time());
        helper.setText(R.id.item_circle_name,item.getTruename());

        //文本
        if(item.getContent()!=null){
            ((ExpandableTextView)helper.getView(R.id.item_circle_expandabletv)).setText(EncodeUtils.urlDecode(Utils.Base64toStr(item.getContent())));
        }else {
            helper.getView(R.id.item_circle_expandabletv).setVisibility(View.GONE);
        }
        Log.e("Tag","item.getImgs()==="+item.getImgs());
        //图片九宫格
        if(item.getImgs()!=null){
            List<String> listimg=new ArrayList<>();
            for (int i = 0; i <item.getImgs().split("@").length ; i++) {
                listimg.add(MainHttp.RES_STRING+item.getImgs().split("@")[i]);
            }

            NineGridImageView<String> nineGridImageView=helper.itemView.findViewById(R.id.item_circle_images);
            nineGridImageView.setVisibility(View.VISIBLE);
            nineGridImageView.setAdapter(mAdapter);
            nineGridImageView.setImagesData(listimg);

//            nineGridImageView.setItemImageClickListener(new ItemImageClickListener<String>() {
//                @Override
//                public void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
//                    Log.d("onItemImageClick", list.get(index));
//                }
//            });
//            nineGridImageView.setItemImageLongClickListener(new ItemImageLongClickListener<String>() {
//                @Override
//                public boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
//                    Log.d("onItemImageLongClick", list.get(index));
//                    return true;
//                }
//            });
        }else {
            helper.getView(R.id.item_circle_images).setVisibility(View.GONE);
        }

        //视频
        if(item.getVideo()!=null){
            MyJZVideoPlayerStandard actor_mjvpst=helper.itemView.findViewById(R.id.item_circle_video);
            ImgLoadUtils.loadVideoScreenshot(mContext,MainHttp.RES_STRING+item.getVideo(),actor_mjvpst.thumbImageView,1);
            actor_mjvpst.setUp(MainHttp.RES_STRING+item.getVideo(), actor_mjvpst.SCREEN_WINDOW_NORMAL,"");
        }else {
            helper.getView(R.id.item_circle_video).setVisibility(View.GONE);
        }

        //评论 点赞 分享
        helper.setText(R.id.item_circle_comment,item.getReply_count()+"");

        if(item.getIs_praise().equals("on")){
            ((ImageView)(helper.getView(R.id.item_circle_like))).setColorFilter( Color.parseColor("#FF5C5C"));
          //  helper.setTextColor(R.id.item_circle_liketv,mContext.getResources().getColor(R.color.pointtask_Orange));
        }else {
            ((ImageView)(helper.getView(R.id.item_circle_like))).setColorFilter( Color.parseColor("#FFFFFF"));
       //     helper.setTextColor(R.id.item_circle_liketv,mContext.getResources().getColor(R.color.white));
        }

        helper.setText(R.id.item_circle_liketv,item.getPraise()+"");

       ImgLoadUtils.loadCirthumbnail(mContext,item.getPic(),(ImageView) helper.getView(R.id.item_circle_avatar));
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            ImgLoadUtils.loadthumbnail(mContext,s,imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
            Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();
            startPhotoActivity(mContext,imageView,list,index);
        }

        @Override
        protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
            Toast.makeText(context, "image long click position is " + index, Toast.LENGTH_SHORT).show();
            return true;
        }
    };


    public  void startPhotoActivity(Context context, ImageView imageView,List<String> mlist,int index) {
        Intent intent = new Intent(context, DragPhotoActivity.class);
        int location[] = new int[2];

        imageView.getLocationOnScreen(location);
        intent.putExtra("left", location[0]);
        intent.putExtra("top", location[1]);
        intent.putExtra("height", imageView.getHeight());
        intent.putExtra("width", imageView.getWidth());
        intent.putExtra("mList",new Gson().toJson(mlist));
        intent.putExtra("index",index);
        context.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(0,0);
    }

}
