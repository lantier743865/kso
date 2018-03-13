package android.com.bugmonitor;

import android.app.Application;
import android.com.bugmonitor.collector.BugMonitor;
import android.util.Log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class App extends Application {
    private static final String TAG = "App";
    private static final String APP_ID = "2882303761517739145";
    private static final String APP_KEY = "5931773919145";
    @Override
    public void onCreate() {
        super.onCreate();
        BugMonitor.init(this);
        MiPushClient.registerPush(this, APP_ID, APP_KEY);
        LoggerInterface newLogger = new LoggerInterface() {
            @Override
            public void setTag(String tag) {
                // ignore
            }
            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, "---->>"+content, t);
            }
            @Override
            public void log(String content) {
                Log.d(TAG, "---->>"+content);
            }
        };
        Logger.setLogger(this, newLogger);

    }
}
