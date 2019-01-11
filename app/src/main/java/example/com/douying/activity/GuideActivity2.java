package example.com.douying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import qiu.niorgai.StatusBarCompat;

public class GuideActivity2 extends BaseActivity {
    @BindView(R.id.guide2_img1)
    ImageView guide2_img1;
    @BindView(R.id.guide2_img2)
    ImageView guide2_img2;
    @BindView(R.id.guide2_img3)
    ImageView guide2_img3;
    @BindView(R.id.guide2_img4)
    ImageView guide2_img4;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_guide2;
    }

    @OnClick({R.id.guide2_img1, R.id.guide2_img2, R.id.guide2_img3, R.id.guide2_img4,R.id.guide2_next})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.guide2_img1:
                guide2_img1.setBackground(getResources().getDrawable(R.drawable.item_guide2_img_shape));
                guide2_img2.setBackground(null);
                guide2_img3.setBackground(null);
                guide2_img4.setBackground(null);
                break;
            case R.id.guide2_img2:
                guide2_img1.setBackground(null);
                guide2_img2.setBackground(getResources().getDrawable(R.drawable.item_guide2_img_shape));
                guide2_img3.setBackground(null);
                guide2_img4.setBackground(null);
                break;
            case R.id.guide2_img3:
                guide2_img1.setBackground(null);
                guide2_img2.setBackground(null);
                guide2_img3.setBackground(getResources().getDrawable(R.drawable.item_guide2_img_shape));
                guide2_img4.setBackground(null);
                break;
            case R.id.guide2_img4:
                guide2_img1.setBackground(null);
                guide2_img2.setBackground(null);
                guide2_img3.setBackground(null);
                guide2_img4.setBackground(getResources().getDrawable(R.drawable.item_guide2_img_shape));
                break;
            case R.id.guide2_next:
                startActivity(new Intent(this,GuideActivity3.class));
                break;
        }

    }
}
