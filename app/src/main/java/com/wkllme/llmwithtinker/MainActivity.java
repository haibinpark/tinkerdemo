package com.wkllme.llmwithtinker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.wkllme.llmwithtinker.display.TestActivity;

public class MainActivity extends AppCompatActivity {

    Button start_new_activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loadPatch = (Button) findViewById(R.id.loadPatch);
        start_new_activity = (Button) findViewById(R.id.start_new_activity);
        start_new_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        loadPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                    Toast
                            .makeText(MainActivity.this, "更新补丁成功", Toast.LENGTH_LONG)
                            .show();
                } catch (Exception e) {
                    Toast
                            .makeText(MainActivity.this, "更新补丁失败: " + e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        Button restartApp = (Button) findViewById(R.id.restartApp);
        restartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSelf();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }


    private void startSelf() {
        AlarmManager larmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("RESTART");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, 0);
        larmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 5, pendingIntent);
    }
}
