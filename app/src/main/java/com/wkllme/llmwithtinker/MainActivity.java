package com.wkllme.llmwithtinker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.app.TinkerServerManager;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.wkllme.llmwithtinker.util.LLMApplicationContext;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button test_crash_bug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button loadPatch = (Button) findViewById(R.id.loadPatch);
//        loadPatch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
//                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
//                    Toast
//                            .makeText(MainActivity.this, "更新补丁成功", Toast.LENGTH_LONG)
//                            .show();
//                } catch (Exception e) {
//                    Toast
//                            .makeText(MainActivity.this, "更新补丁失败: " + e.getMessage(), Toast.LENGTH_LONG)
//                            .show();
//                }
//            }
//        });
        Button restartApp = (Button) findViewById(R.id.restartApp);
        restartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startSelf();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        test_crash_bug = (Button) findViewById(R.id.test_crash_bug);
        test_crash_bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast
                        .makeText(MainActivity.this, "这个错误已经修正了", Toast.LENGTH_LONG)
                .show();
            }
        });

        setMessageListener();
    }

    private void setMessageListener() {
        UmengMessageHandler messageHandler = new UmengMessageHandler(){

            @Override
            public void dealWithNotificationMessage(final Context context, final UMessage msg) {
                TinkerServerManager.checkTinkerUpdate(true);
//                final Handler handler = new Handler(getMainLooper());
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                localNotifi(msg.title,msg.text);
//                                try {
//                                    LogUtil.i("推送消息"+msg.extra.get("result")+"---");
//                                    JSONObject jsonObject= new JSONObject(msg.extra.get("result"));
//                                    String type=jsonObject.getString("pushedType");
//                                    //只在销售
//                                    //根据推送弹出pop点击前往接待呼叫
//                                    if("2".equals(type)){
//                                        requestGlobal();
//                                    }
//                                } catch (Exception e) {
//                                    Log.d(TAG,e.getMessage());
////                                    e.printStackTrace();
//                                }
//
//                            }
//
//                        });
//                    }
//                }.start();
            }
        };
        LLMApplicationContext.mPushAgent.setMessageHandler(messageHandler);
    }


    private void startSelf() {
        AlarmManager larmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("RESTART");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, 0);
        larmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 5, pendingIntent);
    }



}
