package example.com.douying.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activity.CameraActivity;
import example.com.douying.activity.CirclePublishActivity;
import example.com.douying.activity.ModelActivity;
import example.com.douying.adapter.PhotoAdapter;
import example.com.douying.adapter.RecyclerItemClickListener;
import example.com.douying.http.JsonCallback;
import example.com.douying.http.MainHttp;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetModelM;
import example.com.douying.model.SectionM;
import example.com.douying.model.TakeSingleM;
import example.com.douying.utils.ToastUtil;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import qiu.niorgai.StatusBarCompat;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by admin on 2019/1/4.
 */

public class AddModelActivity extends BaseActivity {
    @BindView(R.id.addmodel_sure)
    TextView model_edit;
    @BindView(R.id.addmodel_edit)
    EditText addmodel_edit;
    @BindView(R.id.addmodel_rv)
    RecyclerView addmodel_rv;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<File> lubanPath = new ArrayList<>();
    GetModelM.DataBean.ListBeanX listBeanX;
    HttpParams httpParamsUp = new HttpParams();

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        inview();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_addmodel;
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (int i = 0; i < lubanPath.size(); i++) {
            lubanPath.get(i).delete();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
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
            }
        }
    }

    @OnClick({R.id.addmodel_sure})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.addmodel_sure:
                updata();
                break;
        }
    }

    private void inview() {
        listBeanX = getIntent().getParcelableExtra("ListBeanX");
        if(listBeanX!=null){
            addmodel_edit.setText(listBeanX.getTitle());
            for (int i = 0; i < listBeanX.getList().size(); i++) {
                selectedPhotos.add(listBeanX.getList().get(i).getUrl());
            }
        }
        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        addmodel_rv.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        addmodel_rv.setAdapter(photoAdapter);
        addmodel_rv.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD && position == 0) {
                            PhotoPicker.builder()
                                    .setPhotoCount(6)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(AddModelActivity.this);
                        } else if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD && position != 0) {
                            PhotoPicker.builder()
                                    .setPhotoCount(6)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(AddModelActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(AddModelActivity.this);
                        }
                    }
                }));
    }

    private void setimgs() {
        String img="";
        if (selectedPhotos.size() > 1) {
            for (int i = 0; i < selectedPhotos.size(); i++) {
                if (selectedPhotos.get(i).contains("/storage/emulated/")) {
                    luban(selectedPhotos.get(i), i + 1);
                } else {
                    img=img+selectedPhotos.get(i)+",";
                }
            }
            if(!img.equals("")){
                httpParamsUp.put("extra", img.substring(0,img.length()-1));
            }
        } else {
            if (selectedPhotos.size() > 0) {
                if (selectedPhotos.get(0).contains("/storage/emulated/")) {
                    luban(selectedPhotos.get(0), 0);
                } else {
                    httpParamsUp.put("extra",img+selectedPhotos.get(0) );
                }
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
                            httpParamsUp.put("img", file);
                        } else {
                            httpParamsUp.put("img" + arg, file);
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


    private void updata() {
        httpParamsUp.put("uid", BaseData.Uid);
        if(listBeanX!=null){
            httpParamsUp.put("pid", listBeanX.getPid());
        }
        if (addmodel_edit.getText() != null) {
            httpParamsUp.put("title", addmodel_edit.getText().toString());
        }
        httpParamsUp.put("is_default", "1");
        mainHttp.HttpPost(this, "Index/takeSingle", httpParamsUp).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 202:
                        ToastUtil.show(AddModelActivity.this, baseM.getInfo());
                        finish();
                        break;
                    case 201:
                        ToastUtil.show(AddModelActivity.this, baseM.getInfo());
                        finish();
                        break;
                    default:
                        ToastUtil.show(AddModelActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }

}
