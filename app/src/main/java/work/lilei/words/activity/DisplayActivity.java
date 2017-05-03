package work.lilei.words.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import work.lilei.words.R;
import work.lilei.words.base.BaseActivity;
import work.lilei.words.util.ViewUtil;

/**
 * 展示
 */
public class DisplayActivity extends BaseActivity {

    // request code
    private static final int PERMISSIONS_REQUEST_READ_WRITE_STORAGE = 0;

    // 全屏
    private View mLayoutPage;

    // 字体展示
//    @BindView(R.id.tv_writing)
    private TextView mTvWriting;

    // anim_screenshot handler
    private Handler handler = new Handler();


    // 截图按钮
    private FloatingActionButton mBtnTakeScreenshot;

    // 字体
    private static final String FONT_CXD = "fonts/CXD_6763_Chinese.ttf";
    private static final String FONT_ELEPHANT = "fonts/Elephant_Handwritten_Chinese.ttf";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_display);
        initViews();
        setFont(mTvWriting, FONT_ELEPHANT);
        requestPermissions();
    }

    private void initViews() {
        mLayoutPage = findViewById(R.id.layout_wholepage);
        mLayoutPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBtnVisibility();
            }
        });

        mTvWriting = (TextView) findViewById(R.id.tv_writing);
        setGoldenRatio(mTvWriting);
        mTvWriting.setText(getIntent().getStringExtra("text"));
        mBtnTakeScreenshot = (FloatingActionButton) findViewById(R.id.btn_take_screenshot);
        mBtnTakeScreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
            }
        });
    }

    // 设置黄金分割 高:宽 = 1:1.618，抽取宽（match_parent），设置高
    private void setGoldenRatio(View view) {
        // 屏幕宽
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        // 黄金比例
        int height = (int)(width / 1.618d);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
    }

    // 设置字体
    private void setFont(TextView tv, String fontFilePath) {
        Typeface tf = Typeface.createFromAsset(getAssets(), fontFilePath);
        tv.setTypeface(tf);
    }

    // 截屏
    private void takeScreenshot() {
        mBtnTakeScreenshot.hide();
        ViewUtil.showAnimation(this, mTvWriting, AnimationUtils.currentAnimationTimeMillis() + 100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewUtil.takeScreenshot(DisplayActivity.this, mTvWriting);
                mBtnTakeScreenshot.show();
            }
        }, 800);
    }

    // 按钮可见性
    private void toggleBtnVisibility() {
        if (mBtnTakeScreenshot.getVisibility() == View.VISIBLE) {
            mBtnTakeScreenshot.hide();
        } else {
            mBtnTakeScreenshot.show();
        }
    }

    /**
     * 请求授权读写存储
     */
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                toastLong("未获取读写外部存储权限，请在系统设置里进行授权");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_READ_WRITE_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_WRITE_STORAGE:
                if (isGranted(grantResults)) {
                    // 已获得授权
                } else {
                    toastLong("截图保存功能需要读写外部存储权限，请在系统设置里进行授权");
                    finish(); // 退出当前Activity
                }
            default:
                // do nothing
        }
    }

    /**
     * 是否获得全部授权
     * @param grantResults 授权结果
     * @return 是否获得全部授权
     */
    private boolean isGranted(int[] grantResults) {
        if (grantResults == null || grantResults.length == 0) {
            return false;
        }
        for (int i : grantResults) {
            if (i == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
}
