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

public class GuideActivity3 extends BaseActivity {
    @BindView(R.id.guide3_img1)
    ImageView guide3_img1;
    @BindView(R.id.guide3_img2)
    ImageView guide3_img2;
    @BindView(R.id.guide3_img3)
    ImageView guide3_img3;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_guide3;
    }

    @OnClick({R.id.guide3_img1, R.id.guide3_img2, R.id.guide3_img3, R.id.guide3_next})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.guide3_img1:
                guide3_img1.setBackground(getResources().getDrawable(R.drawable.item_guide2_img_shape));
                guide3_img2.setBackground(null);
                guide3_img3.setBackground(null);
                break;
            case R.id.guide3_img2:
                guide3_img1.setBackground(null);
                guide3_img2.setBackground(getResources().getDrawable(R.drawable.item_guide2_img_shape));
                guide3_img3.setBackground(null);
                break;
            case R.id.guide3_img3:
                guide3_img1.setBackground(null);
                guide3_img2.setBackground(null);
                guide3_img3.setBackground(getResources().getDrawable(R.drawable.item_guide2_img_shape));
                break;
            case R.id.guide3_next:
                startActivity(new Intent(this, GuideActivity4.class));
                break;
        }

    }
}
