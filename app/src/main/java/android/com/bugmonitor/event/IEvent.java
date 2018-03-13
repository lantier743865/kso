package android.com.bugmonitor.event;

import android.app.Activity;
import android.view.MotionEvent;

/**
 * Created by wuxiaolong on 2018/3/12.
 */

interface IEvent {
    void onDispatchTouchEvent(Activity activity, MotionEvent event);
}
