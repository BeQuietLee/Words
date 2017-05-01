package work.lilei.words.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 基础Activity，提供通用方法
 * 所有的Activity都应该继承它
 * Created by lei on 4/29/17.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // =====================公共方法=====================
    // 短toast
    protected void toastShort(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    // 长toast
    protected void toastLong(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    // 隐藏状态栏
    protected void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
