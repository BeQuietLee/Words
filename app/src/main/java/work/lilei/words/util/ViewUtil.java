package work.lilei.words.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import work.lilei.words.R;

/**
 * 视图工具方法
 * Created by lei.li on 17-5-2.
 */

public class ViewUtil {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss"); // 用于截图文件名的时间格式
    private static final int QUALITY = 100; // 图片质量，0~100,100为原图


    private ViewUtil() {}
    public static void takeScreenshot(Context context, View view) {

        Date now = new Date();

        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        try {

            String path = Environment.getExternalStorageDirectory().toString() + "/" + DATE_FORMAT.format(now) + ".jpg";
            File image = new File(path);
            FileOutputStream outputStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, outputStream);
            outputStream.flush();
            outputStream.close();

            openImage(context, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void openImage(Context context, File image) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(image);
        intent.setDataAndType(uri, "image/*");
        context.startActivity(intent);
    }

    public static void showAnimation(Context context, View view, long startTime) {
        Animation scaleAnim = AnimationUtils.loadAnimation(context , R.anim.anim_screenshot);
        scaleAnim.setStartTime(startTime);
        view.setAnimation(scaleAnim);
    }

}
