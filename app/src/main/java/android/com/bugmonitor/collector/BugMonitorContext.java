package android.com.bugmonitor.collector;

import android.app.Activity;
import android.com.bugmonitor.crash.CrashInfo;
import android.com.bugmonitor.queue.StepQueueHelper;
import android.com.bugmonitor.util.CPUUtil;
import android.com.bugmonitor.util.MemoryUtil;
import android.com.bugmonitor.util.NetUtil;
import android.com.bugmonitor.util.SystemInfoUtil;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.icu.util.IslamicCalendar;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.Log;
import android.view.Display;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class BugMonitorContext implements CrashInterceptor{
    private static final String TAG = "BugMonitorContext";

    public static final SimpleDateFormat TIME_FORMATTER =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US) ;

    public static String dirName ;

    private BugMonitorContext() {}

    public static BugMonitorContext getInstance() {
       return SingletonHodler.instance;
    }

    private static class SingletonHodler {
        private static final BugMonitorContext instance = new BugMonitorContext();
    }

    @Override
    public void onCrash(String crashInfo) {
        Activity activity = ActivityHelper.getActivity();
        long mills = System.currentTimeMillis();
        dirName = TIME_FORMATTER.format(mills);

        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        float density = activity.getResources().getDisplayMetrics().density;

        String networkState = NetUtil.getNetworkState(BugMonitor.mContext);
        PackageInfo info = null;
        try {
            info = BugMonitor.mContext.getPackageManager().getPackageInfo(BugMonitor.mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        long freeMemory = MemoryUtil.getFreeMemory();
        long totalMemory = MemoryUtil.getTotalMemory();
        String cpuName = CPUUtil.getCpuName();
        String curCpuFreq = CPUUtil.getCurCpuFreq();
        String maxCpuFreq = CPUUtil.getMaxCpuFreq();
        String minCpuFreq = CPUUtil.getMinCpuFreq();
        int numCores = CPUUtil.getNumCores();



        boolean rooted = SystemInfoUtil.checkSuFile();


        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        BitmapHelper.saveBitmap(bmp, dirName);
        final String s = CrashInfo.newInstance()
                .setStackStrings(crashInfo)
                .setScreenResolution(point)
                .setDensity(density)
                .setNetwork(networkState)
                .setPackageInfo(info)
                .setMemory(freeMemory,totalMemory)
                .setCpuInfo(numCores,cpuName,curCpuFreq,maxCpuFreq)
                .setIsRoot(rooted)
                .flushString()
                .toString();
        saveLogToSDCard(s,"crashInfo");
        String step = StepQueueHelper.flushString();
        Log.e(TAG, "---->>step: "+step );
        saveLogToSDCard(step,"step");
        Log.e(TAG, s );
    }

    private void saveLogToSDCard(String s, String fileName) {
        String path = Environment.getExternalStorageDirectory() + File.separator + "crash" + File.separator + dirName;
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File file = new File(path + File.separator + fileName + ".log");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                if (fileOutputStream != null) {
                    fileOutputStream.write(s.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Log.e(TAG, "---->>saveLogToSDCard: "+file.getAbsolutePath() );
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
