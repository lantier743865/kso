package android.com.bugmonitor.collector;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

interface CrashInterceptor {
    void onCrash(String crashInfo);
}
