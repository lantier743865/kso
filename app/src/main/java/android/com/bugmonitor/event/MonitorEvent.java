package android.com.bugmonitor.event;

import android.app.Activity;
import android.com.bugmonitor.queue.StepQueueHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wuxiaolong on 2018/3/12.
 */

public class MonitorEvent implements IEvent {
    private static final String TAG = "MonitorEvent";
    private static MonitorEvent instance;
    private float lastX;
    private float lastY;

    private MonitorEvent() {
    }

    public static MonitorEvent getInstance() {
        if (instance == null) {
            synchronized (MotionEvent.class) {
                if (instance == null) {
                    instance = new MonitorEvent();
                }
            }
        }
        return instance;
    }

    @Override
    public void onDispatchTouchEvent(Activity activity, MotionEvent event) {
        if(event.getAction() == 0) {
            this.lastX = event.getRawX();
            this.lastY = event.getRawY();
        } else if(event.getAction() == 1) {
            float currentX = event.getRawX();
            float currentY = event.getRawY();
            if(this.lastX == currentX && this.lastY == currentY) {
                View var5 = this.getView(activity.getWindow().getDecorView(), currentX, currentY);
                Log.e(TAG, "---->>var5: "+var5 );
                StepQueueHelper.enqueueStep(activity,var5);
//                if(this.jf != null) {
//                    this.jf.b(var5);
//                }
            }
        }
    }

    private View getView(View decorView, float currentX, float currentY) {
        View var4 = null;
        int[] var5 = new int[2];
        decorView.getLocationInWindow(var5);
        if(this.a(currentX, currentY, var5[0], var5[1], decorView.getWidth(), decorView.getHeight())) {
            if(decorView instanceof ViewGroup) {
                for(int var6 = 0; var6 < ((ViewGroup)decorView).getChildCount(); ++var6) {
                    View var7 = ((ViewGroup)decorView).getChildAt(var6);
                    var4 = this.getView(var7, currentX, currentY);
                    if(var4 != null) {
                        break;
                    }
                }
            } else {
                var4 = decorView;
            }
        }

        return var4;
    }

    private boolean a(float var1, float var2, int var3, int var4, int var5, int var6) {
        return var1 >= (float)var3 && var1 <= (float)(var3 + var5) && var2 >= (float)var4 && var2 <= (float)(var4 + var6);
    }
}
