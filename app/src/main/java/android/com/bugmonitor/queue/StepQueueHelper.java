package android.com.bugmonitor.queue;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by wuxiaolong on 2018/3/13.
 */

public class StepQueueHelper {
    private static final String TAG = "StepQueueHelper";
    private static SequenceQueue<String> stepQueue = new SequenceQueue<>();
    private static StringBuilder stepBuilder = new StringBuilder();
    private static final SimpleDateFormat TIME_FORMATTER =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US) ;
    private static final String  SPACE = "  ";
    public static void enqueueStep (Activity activity, String type) {
        String format = TIME_FORMATTER.format(System.currentTimeMillis());
        stepBuilder.append(format)
                    .append(SPACE)
                    .append(activity.getClass().getName())
                    .append(SPACE)
                    .append(type);
        String s = stepBuilder.toString();
        stepQueue.add(s);
        stepBuilder.delete(0,stepBuilder.length());
    }
    public static void enqueueStep(Activity activity, View view) {
        Log.e(TAG, "---->>view: "+view.getClass().getSimpleName() );
        Log.e(TAG, "---->>getName: "+view.getClass().getName() );
        String format = TIME_FORMATTER.format(System.currentTimeMillis());
        stepBuilder.append(format)
                .append(SPACE)
                .append(activity.getClass().getName())
                .append(SPACE)
                .append(view);
        String s = stepBuilder.toString();
        stepQueue.add(s);
        stepBuilder.delete(0,stepBuilder.length());
    }

    public static String flushString () {
       return stepQueue.flushString();
    }


}
