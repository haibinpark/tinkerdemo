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

import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loadPatch = (Button) findViewById(R.id.loadPatch);
        loadPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
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
        Intent intent =new Intent("RESTART");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, 0);
        larmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 5, pendingIntent);
    }
}
