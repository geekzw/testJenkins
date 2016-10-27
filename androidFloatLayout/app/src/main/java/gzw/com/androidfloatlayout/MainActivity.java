package gzw.com.androidfloatlayout;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button start = (Button) findViewById(R.id.start_id);
        Button remove = (Button) findViewById(R.id.remove_id);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CustomToast.makeText(MainActivity.this, "开启悬浮窗", CustomToast.LENGTH_LONG).show();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CustomToast.makeText(MainActivity.this, "关闭悬浮窗", CustomToast.LENGTH_LONG).show();
            }
        });

    }


}
