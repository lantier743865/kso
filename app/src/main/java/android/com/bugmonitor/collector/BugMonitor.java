package android.com.bugmonitor.collector;

import android.app.Activity;
import android.com.bugmonitor.crash.CrashHandler;
import android.com.bugmonitor.event.MonitorEvent;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class BugMonitor {
    private static final String TAG = "BugMonitor";
    public static Context mContext;

    public static void init(Context context) {
        mContext = context;
        initCrashHandler(context);
    }

    private static void initCrashHandler(Context context) {
        Thread.UncaughtExceptionHandler exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        CrashHandler crashHandler = new CrashHandler(context, exceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    public static void onDispatchTouchEvent(Activity activity, MotionEvent event) {
        MonitorEvent.getInstance().onDispatchTouchEvent(activity,event);
    }
    public static void onResume(Activity activity) {
        Log.e(TAG, "---->>onResume: "+activity.getClass().getName() );

    }
    public static void onPause(Activity activity) {
        Log.e(TAG, "---->>onPause: "+activity.getClass().getName());
    }
}
