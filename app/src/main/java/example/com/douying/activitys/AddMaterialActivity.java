package example.com.douying.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.zolad.videoslimmer.VideoSlimmer;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.utils.FileUtil;
import example.com.douying.utils.ToastUtil;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/9/28.
 */

public class AddMaterialActivity extends BaseActivity {
    private static final int REQUEST_FOR_VIDEO_FILE = 1000;
    private String outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath();
    @BindView(R.id.addmaterlial_img)
    ImageView addmaterlial_img;
    @BindView(R.id.addmaterlial_tv1)
    TextView addmaterlial_tv1;
    @BindView(R.id.addmaterlial_num)
    TextView addmaterlial_num;
    @BindView(R.id.addmaterlial_edit)
    EditText addmaterlial_edit;
    private String inputPath;
    @BindView(R.id.pb_compress)
    ProgressBar pb_compress;
    @BindView(R.id.addmaterlial_swtich)
    Switch addmaterlial_swtich;
    @BindView(R.id.addmaterlial_time)
    TextView addmaterlial_time;
    @BindView(R.id.addmaterlial_length)
    TextView addmaterlial_length;
    private boolean IsAdd = false;
    String destPath;
    Bitmap firstbitmap;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_addmaterlial;
    }

    @Override
    protected void onStop() {
        super.onStop();
         if(destPath!=null){
             File file = new File(destPath.toString());
             file.delete();
         }
    }

    @OnClick({R.id.addmaterlial_img, R.id.addmaterlial_add})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.addmaterlial_img:
                getvideo();
                break;
            case R.id.addmaterlial_add:
                if (IsAdd) {
                    video_add();
                } else {
                    ToastUtil.show(this, "请先添加视频");
                }
                break;
        }
    }


    private void getvideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_FOR_VIDEO_FILE);
    }


    private void video_add() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("film", new File(destPath));
        //httpParams.put("poster",);
        httpParams.put("introduce", addmaterlial_edit.getText().toString());
        //  httpParams.put("address",);
        httpParams.put("show", addmaterlial_swtich.isChecked() ? 1 : 2);
        httpParams.put("type", 1);
        mainHttp.HttpPost(AddMaterialActivity.this, "Articles/video_add", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 201:
                        ToastUtil.show(AddMaterialActivity.this, baseM.getInfo());
                        finish();
                        break;
                    default:
                        ToastUtil.show(AddMaterialActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOR_VIDEO_FILE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                try {
                    inputPath = FileUtil.getFilePath(this, data.getData());
                    destPath = outputDir + File.separator + "VIDEOSIMMER_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4";
                    VideoSlimmer.convertVideo(inputPath, destPath, 1080, 720, 200 * 360 * 30, new VideoSlimmer.ProgressListener() {
                        @Override
                        public void onStart() {
                            pb_compress.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFinish(boolean result) {
                            if (result) {
                                pb_compress.setVisibility(View.INVISIBLE);
                                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                                retriever.setDataSource(destPath);
                                //获取视频第一帧
                                MediaMetadataRetriever media = new MediaMetadataRetriever();
                                media.setDataSource(destPath);// videoPath 本地视频的路径
                                firstbitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                                addmaterlial_img.setImageBitmap(firstbitmap);//对应的ImageView
                                String time = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                                IsAdd = true;
                                addmaterlial_time.setText(Utils.ms2HMS(Integer.parseInt(time)));
                                File file=new File(destPath);

                                   long fileSize = file.length();
//                                String after = "outputPath:" + destPath + "\n" + "width:" + width + "\n" + "height:" + height + "\n" + "bitrate:" + bitrate + "\n"
//                                        + "fileSize:" + Formatter.formatFileSize(AddMaterialActivity.this, fileSize);
                            } else {
                                pb_compress.setVisibility(View.INVISIBLE);
                                FileUtil.writeFile(AddMaterialActivity.this, "Failed Compress!!!" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
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
