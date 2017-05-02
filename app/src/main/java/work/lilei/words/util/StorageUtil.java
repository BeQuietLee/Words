package work.lilei.words.util;

import android.os.Environment;

import java.io.File;

import work.lilei.words.BuildConfig;

/**
 * 存储
 * Created by lei.li on 17-5-2.
 */

public class StorageUtil {
    private StorageUtil() {}

    /**
     * 外部存储地址， /storage/emulated/0/work.lilei.words
     * 如果不存在，会创建这个目录
     * @return 外部存储地址
     */
    public static String externalStoragePath() {
        String path = Environment.getExternalStorageDirectory().toString()
                + "/"
                + BuildConfig.APPLICATION_ID;
        return checkFolderExist(path);
    }

    /**
     * 截屏文件夹地址， /storage/emulated/0/work.lilei.words/screenshot
     * @return 截屏文件夹地址
     */
    public static String screenshotPath() {
        String path = externalStoragePath()
                + "/screenshot";
        return checkFolderExist(path);
    }

    /**
     * 检验文件夹是否存在，若不存在，则创建
     * @param path 文件路径
     * @return 路径
     */
    private static String checkFolderExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path;
    }
}
