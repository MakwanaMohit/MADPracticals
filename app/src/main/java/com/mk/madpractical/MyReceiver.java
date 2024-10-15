package com.mk.madpractical;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action != null) {
            switch (action) {
                case "android.intent.action.ACTION_POWER_CONNECTED":
                    Toast.makeText(context, "Power connected to your device", Toast.LENGTH_SHORT).show();
                    break;
                case "android.intent.action.ACTION_POWER_DISCONNECTED":
                    Toast.makeText(context, "Power disconnected from your device", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context, "Unknown action: " + action, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
