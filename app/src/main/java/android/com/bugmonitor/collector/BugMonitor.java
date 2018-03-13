package android.com.bugmonitor.collector;

import android.app.Activity;
import android.com.bugmonitor.crash.CrashHandler;
import android.com.bugmonitor.event.MonitorEvent;
import android.com.bugmonitor.queue.SequenceQueue;
import android.com.bugmonitor.queue.StepQueueHelper;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class BugMonitor {
    private static final String TAG = "BugMonitor";
    public static Context mContext;
    public static final String RESUME = "onResume";
    public static final String PAUSE = "onPause";


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
        StepQueueHelper.enqueueStep(activity,RESUME);

    }
    public static void onPause(Activity activity) {
        StepQueueHelper.enqueueStep(activity,PAUSE);
    }

}
