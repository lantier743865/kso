package android.com.bugmonitor.crash;

import android.com.bugmonitor.collector.BugMonitorContext;
import android.content.Context;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private Context mContext;
    private Thread.UncaughtExceptionHandler mHandler;

    public CrashHandler() {
    }

    public CrashHandler(Context mContext, Thread.UncaughtExceptionHandler mHandler) {
        this.mContext = mContext;
        this.mHandler = mHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        formatCrashInfo(t,e);
        if (mHandler != null) {
            mHandler.uncaughtException(t,e);
        }
    }

    private void formatCrashInfo(Thread t, Throwable e) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        e.printStackTrace(printWriter);
        String s = info.toString();
        BugMonitorContext.getInstance().onCrash(s);
    }
}
