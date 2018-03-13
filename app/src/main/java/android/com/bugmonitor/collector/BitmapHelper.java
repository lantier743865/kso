package android.com.bugmonitor.collector;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class BitmapHelper {
    private static final String TAG = "BitmapHelper";


    public static void saveBitmap(final Bitmap bitmap, String dirName) {
                String path = Environment.getExternalStorageDirectory() + File.separator + "crash" + File.separator + BugMonitorContext.dirName;
                File filePath = new File(path);
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                File file = new File(filePath + File.separator + dirName +".png");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        FileOutputStream fos = null;
                        fos = new FileOutputStream(file);
                        if (null != fos) {
                            bitmap.compress(Bitmap.CompressFormat.PNG,90,fos);
                            fos.flush();
                            fos.close();
                            Log.e(TAG, "---->>saveBitmap: "+file.getAbsolutePath() );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "---->>run: "+e.toString() );
                    }
                }

    }
}
