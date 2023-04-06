package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PracticalTest01Var03BroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        String message = intent.getStringExtra(Constants.MESSAGE);

        Log.d(Constants.TAG, "BroadcastReceiver:" + "[" + action + "]" + message);
    }
}
