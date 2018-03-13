package android.com.bugmonitor.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by wuxiaolong on 2018/3/12.
 */

public class SystemInfoUtil {

    public static boolean checkSuFile() {
        Process process = null;
        try {
            //   /system/xbin/which 或者  /system/bin/which
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }

}
