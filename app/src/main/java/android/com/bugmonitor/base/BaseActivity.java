package android.com.bugmonitor.base;

import android.com.bugmonitor.collector.BugMonitor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onResume() {
        super.onResume();
        BugMonitor.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BugMonitor.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        BugMonitor.onDispatchTouchEvent(this,ev);
        return super.dispatchTouchEvent(ev);
    }
}
