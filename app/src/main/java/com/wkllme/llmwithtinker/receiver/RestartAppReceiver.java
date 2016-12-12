package com.wkllme.llmwithtinker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wkllme.llmwithtinker.MainActivity;

public class RestartAppReceiver extends BroadcastReceiver {
    public RestartAppReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainActivityIntent);
    }
}
