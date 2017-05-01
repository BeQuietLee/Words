package work.lilei.words;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import work.lilei.words.base.BaseActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WriteActivity extends BaseActivity {

    // 字体展示
    @BindView(R.id.tv_writing)
    TextView mTvWriting;
    // 用户输入
    @BindView(R.id.et_input)
    EditText mEtInput;
    // 确认按钮
    @BindView(R.id.btn_ok)
    Button btnOK;

    // 字体
    private static final String FONT_CXD = "fonts/CXD_6763_Chinese.ttf";
    private static final String FONT_ELEPHANT = "fonts/Elephant_Handwritten_Chinese.ttf";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFont(mTvWriting, FONT_CXD);
    }

    // 设置字体
    private void setFont(TextView tv, String fontFilePath) {
        Typeface tf = Typeface.createFromAsset(getAssets(), fontFilePath);
        tv.setTypeface(tf);
    }

    // 点击后更新TV
    @OnClick(R.id.btn_ok)
    void displayHandWriting() {
        String inp = mEtInput.getText().toString();
        if (TextUtils.isEmpty(inp)) {
            toastShort("未输入文字");
        } else {
            mTvWriting.setText(inp);
        }
    }
}
