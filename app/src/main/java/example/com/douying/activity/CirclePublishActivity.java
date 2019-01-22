package example.com.douying.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.support.text.emoji.widget.EmojiTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;


import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.zolad.videoslimmer.VideoSlimmer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.AppLoading;
import douying.example.com.mylibrary.view.utils.EncodeUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;

import example.com.douying.activitys.AddMaterialActivity;
import example.com.douying.activitys.HomeActivityS;
import example.com.douying.adapter.PhotoAdapter;
import example.com.douying.adapter.RecyclerItemClickListener;
import example.com.douying.event.PublishEvent;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.utils.FileUtil;
import example.com.douying.utils.ToastUtil;
import example.com.douying.view.CustomDialog;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import qiu.niorgai.StatusBarCompat;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * Created by admin on 2018/11/28.
 */

public class CirclePublishActivity extends BaseActivity {
    private static final int REQUEST_FOR_VIDEO_FILE = 1000;
    @BindView(R.id.circlepublish_rv)
    RecyclerView circlepublish_rv;
    @BindView(R.id.publish_edit)
    TextView publish_edit;
    @BindView(R.id.publish_push)
    TextView publish_push;
    @BindView(R.id.publish_video_rl)
    RelativeLayout publish_video_rl;
    @BindView(R.id.publish_video_img)
    ImageView publish_video_img;
    @BindView(R.id.publish_video_time)
    TextView publish_video_time;
    @BindView(R.id.publish_compress)
    ProgressBar publish_compress;
    @BindView(R.id.publish_video_close)
    ImageView publish_video_close;
    @BindView(R.id.publish_video_length)
    TextView publish_video_length;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<File> lubanPath=new ArrayList<>();
    CustomDialog dialog;
    private String inputPath;
    private String destPath;
    private Bitmap firstbitmap;
    private boolean IsAdd = false;
    private HttpParams httpParams = new HttpParams();

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        inview();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_circlepublish;
    }


    @Override
    protected void onStop() {
        super.onStop();
        for (int i = 0; i <lubanPath.size() ; i++) {
            lubanPath.get(i).delete();
        }
    }

    private void inview() {
        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        circlepublish_rv.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        circlepublish_rv.setAdapter(photoAdapter);
        circlepublish_rv.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD && position == 0) {
                            setdialog();
                        } else if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD && position != 0) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(CirclePublishActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(CirclePublishActivity.this);
                        }
                    }
                }));
    }


    @OnClick({R.id.publish_push, R.id.publish_video_rl, R.id.publish_video_close})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.publish_push:
                AppLoading.show(this);
                sendPost();
                break;
            case R.id.publish_video_rl:
                getvideo();
                break;
            case R.id.publish_video_close:
                publish_video_img.setImageResource(R.mipmap.tianjia);
                publish_video_time.setText("");
                IsAdd = false;
                circlepublish_rv.setVisibility(View.VISIBLE);
                publish_video_rl.setVisibility(View.GONE);
                break;
        }
    }


    private void setimgs() {
        if (selectedPhotos.size() > 1) {
            for (int i = 0; i < selectedPhotos.size(); i++) {
                luban(selectedPhotos.get(i), i + 1);
            }
        } else {
            if (selectedPhotos.size() > 0) {
                luban(selectedPhotos.get(0), 0);
            }
        }
    }


    private void luban(String photo, int arg) {
        Luban.with(this)
                .load(photo)
                .ignoreBy(100)
                .setTargetDir(outputDir)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        lubanPath.add(file);
                        if (arg == 0) {
                            httpParams.put("img", file);
                        } else {
                            httpParams.put("img" + arg, file);
                        }
                        Log.e("Tag", "onSuccess");
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }


    private void sendPost() {
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("type", 1);// 1 普通帖子 2悬赏帖子
        if (IsAdd) { //判断是否有视频
            httpParams.put("video", new File(destPath));
        }
        httpParams.put("title", "");
        if (publish_edit.getText() != null) {
       //     String str=EncodeUtils.urlDecode(Utils.getBase64(publish_edit.getText().toString()),"UTF-8");

                String str=EncodeUtils.urlEncode(Utils.getBase64(publish_edit.getText().toString()),"UTF-8");

            httpParams.put("content", EncodeUtils.urlEncode(Utils.getBase64(publish_edit.getText().toString()),"UTF-8"));
        }

        if (selectedPhotos.size() > 0) { //判断是否有图片
            if (selectedPhotos.size() > 1) {
                httpParams.put("multi", 2); //1为单图 2为多图
            } else {
                httpParams.put("multi", 1);
            }
        }

        mainHttp.HttpPost(this, "Articles/sendPost", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 201:
                        AppLoading.close();
                        ToastUtil.show(CirclePublishActivity.this, baseM.getInfo());
                        //发布刷新消息
                        EventBus.getDefault().post(new PublishEvent(true));
                        finish();
                        break;
                    default:
                        ToastUtil.show(CirclePublishActivity.this, baseM.getInfo());
                        AppLoading.close();
                        break;
                }
            }
        });
    }


    private void setdialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.dialog_swtichmeadi)
                        .heightDimenRes(R.dimen.dialog_loginerror_height)
                        .widthDimenRes(R.dimen.dialog_loginerror_width)
                        .style(R.style.Dialog)
                        .addViewOnclick(R.id.close, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .addViewOnclick(R.id.tophoto, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PhotoPicker.builder()
                                        .setPhotoCount(PhotoAdapter.MAX)
                                        .setShowCamera(true)
                                        .setPreviewEnabled(false)
                                        .setSelected(selectedPhotos)
                                        .start(CirclePublishActivity.this);
                                dialog.dismiss();
                            }
                        })
                        .addViewOnclick(R.id.tovideo, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getvideo();
                                dialog.dismiss();
                            }
                        })
                        .build();
        dialog.show();
    }


    private void getvideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_FOR_VIDEO_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            //压缩和添加图片
            setimgs();
            photoAdapter.notifyDataSetChanged();
            //获取视频
        } else if (requestCode == REQUEST_FOR_VIDEO_FILE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                circlepublish_rv.setVisibility(View.GONE);
                publish_video_rl.setVisibility(View.VISIBLE);
                try {
                    inputPath = FileUtil.getFilePath(this, data.getData());
                    destPath = outputDir + File.separator + "VIDEOSIMMER_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4";
                    VideoSlimmer.convertVideo(inputPath, destPath, 1080, 720, 200 * 360 * 30, new VideoSlimmer.ProgressListener() {
                        @Override
                        public void onStart() {
                            publish_compress.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFinish(boolean result) {
                            if (result) {
                                publish_compress.setVisibility(View.INVISIBLE);
                                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                                retriever.setDataSource(destPath);
                                //获取视频第一帧
                                MediaMetadataRetriever media = new MediaMetadataRetriever();
                                media.setDataSource(destPath);// videoPath 本地视频的路径
                                firstbitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                                publish_video_img.setImageBitmap(firstbitmap);//对应的ImageView
                                String time = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                                IsAdd = true;
                                publish_video_time.setText(Utils.ms2HMS(Integer.parseInt(time)));
                                File file = new File(destPath);
                                long fileSize = file.length();
                                publish_video_length.setText(Formatter.formatFileSize(CirclePublishActivity.this, fileSize));
                            } else {
                                publish_compress.setVisibility(View.INVISIBLE);
                                FileUtil.writeFile(CirclePublishActivity.this, "Failed Compress!!!" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                            }
                        }

                        @Override
                        public void onProgress(float percent) {

                        }
                    });

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
