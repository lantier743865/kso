package android.com.bugmonitor;

import android.app.Application;
import android.com.bugmonitor.collector.BugMonitor;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BugMonitor.init(this);
    }
}
