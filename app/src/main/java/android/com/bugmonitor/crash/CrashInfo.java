package android.com.bugmonitor.crash;

import android.content.pm.PackageInfo;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by wuxiaolong on 2018/3/9.
 */

public class CrashInfo {
    private static final String TAG = "CrashInfo";

    public static final SimpleDateFormat TIME_FORMATTER =
            new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US) ;

    public static final String SEPATATOR = "\r\n";

    public static final String KV = " = ";
    public static final String KEY_API = "api-level";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_STACKSTRINGS = "stack_strings";
    public static final String KEY_SCREEN_RESOLUTION = "screen_resolution";
    public static final String KEY_DENSITY = "density";
    public static final String KEY_PACKAGEINFO = "packageInfo";
    public static final String KEY_NETWORK = "network";
    public static final String KEY_MEMORY = "memory";
    public static final String KEY_ISROOT = "isRoot";
    public static final String KEY_CPUINFO = "cpuinfo";



    public static String sModel;//手机型号
    public static String sApi;//sdk版本
    public static String sBrand;//手机厂商


    public String model;
    public String api;
    public String brand;
    private String stackStrings;
    private String screenResolution;
    private float  density;
    private String network;
    private String packageInfo;
    private String memory;
    private boolean isRoot;
    private String cpuInfo;


    private StringBuilder basicSb = new StringBuilder();


    private CrashInfo() {
    }

    public static CrashInfo newInstance() {
        CrashInfo crashInfo = new CrashInfo();
        crashInfo.model = sModel;
        crashInfo.api = sApi;
        crashInfo.brand = Build.BRAND;
        crashInfo.brand = Build.BRAND;
        crashInfo.screenResolution = "";
        crashInfo.density = 0;
        return crashInfo;
    }
    static {
        sModel = Build.MODEL;
        sApi =  "android " + Build.VERSION.RELEASE + "("  + Build.VERSION.SDK_INT + ")";
        sBrand = Build.BOARD;
    }

    public CrashInfo setStackStrings(String stackStrings) {
        this.stackStrings = stackStrings;
        return this;
    }

    public CrashInfo setScreenResolution(Point point) {
        this.screenResolution = point.x + "x" + point.y;
        return this;
    }

    public CrashInfo setDensity(float density) {
        this.density = density;
        return this;
    }

    public CrashInfo setNetwork(String network) {
        this.network = network;
        return this;
    }

    public CrashInfo setPackageInfo(PackageInfo info) {
        if (info != null) {
          this.packageInfo = info.versionName + "(build " + info.versionCode + ")";
        }
        return this;
    }

    public CrashInfo setMemory(long freeMemory, long totalMemory) {
        this.memory = "freeMemory:" + freeMemory + " totalMemory:" + totalMemory;
        return this;
    }

    public CrashInfo setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
        return this;
    }

    public CrashInfo setCpuInfo(int numCores,String cpuName,String curCpuFreq,String maxCpuFreq) {
        this.cpuInfo = "numCores:" + numCores + " cpuName:" + cpuName + " curCpuFreq:" + curCpuFreq + " maxCpuFreq:" + maxCpuFreq;
        return this;
    }
    public CrashInfo flushString() {
        String separator = SEPATATOR;
        basicSb.append(KEY_BRAND).append(KV).append(brand).append(":").append(model).append(separator);
        basicSb.append(KEY_API).append(KV).append(api).append(separator);
        basicSb.append(KEY_SCREEN_RESOLUTION).append(KV).append(screenResolution).append(separator);
        basicSb.append(KEY_DENSITY).append(KV).append(density).append(separator);
        basicSb.append(KEY_PACKAGEINFO).append(KV).append(packageInfo).append(separator);
        basicSb.append(KEY_NETWORK).append(KV).append(network).append(separator);
        basicSb.append(KEY_MEMORY).append(KV).append(memory).append(separator);
        basicSb.append(KEY_CPUINFO).append(KV).append(cpuInfo).append(separator);
        basicSb.append(KEY_ISROOT).append(KV).append(isRoot).append(separator);
        basicSb.append(KEY_STACKSTRINGS).append(KV).append(stackStrings).append(separator);
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(basicSb);
    }


}
