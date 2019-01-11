package example.com.douying.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;


import example.com.douying.R;
import example.com.douying.adapter.SwtichActorAdapter;
import example.com.douying.model.PartBean;

public class SwtichActorActivity extends BaseActivity {
    @BindView(R.id.swtichactor_rv)
    RecyclerView swtichactor_rv;
    SwtichActorAdapter swtichActorAdapter;
    List<Object> list = new ArrayList();

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_swtichactor;
    }

    @OnClick({R.id.swtichactor_tocamera})
    public void test(View view){
        switch (view.getId()){
            case R.id.swtichactor_tocamera:
             //   takePhoto();
                startActivity(new Intent(this, CameraActivity.class));
                break;
        }
    }

    private void setdata() {
        PartBean partBean = getIntent().getParcelableExtra("PartBean");
        swtichActorAdapter = new SwtichActorAdapter(partBean.getRole());
        swtichactor_rv.setAdapter(swtichActorAdapter);
        swtichactor_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void takePhoto() {
        if (ActivityCompat.checkSelfPermission(SwtichActorActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SwtichActorActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0x12);
            return;
        }

    }
}
