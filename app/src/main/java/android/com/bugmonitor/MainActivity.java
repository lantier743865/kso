package android.com.bugmonitor;

import android.Manifest;
import android.com.bugmonitor.base.BaseActivity;
import android.content.pm.PackageManager;
import android.os.BaseBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xiaomi.mipush.sdk.MiPushClient;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    public static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                ,Manifest.permission.WRITE_EXTERNAL_STORAGE
                ,Manifest.permission.ACCESS_WIFI_STATE},REQUEST_CODE);
        initView();
        String regId = MiPushClient.getRegId(this);
        Log.e(TAG, "---->>regId: "+regId );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "---->>onRequestPermissionsResult: success" );
            } else {
                Log.e(TAG, "---->>onRequestPermissionsResult: false" );
            }
        }
    }

    private void initView() {
        findViewById(R.id.bt_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = null;
                s.split("sd");
            }
        });
    }


}
