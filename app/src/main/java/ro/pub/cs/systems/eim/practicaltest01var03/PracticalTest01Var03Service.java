package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var03Service extends Service {

    PracticalTest01Var03Thread practicalTest01Var03Thread;

    public PracticalTest01Var03Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        String firstNumber = intent.getStringExtra(Constants.FIRST_NUMBER_TEXTVIEW);
        String secondNumber = intent.getStringExtra(Constants.SECOND_NUMBER_TEXTVIEW);

        practicalTest01Var03Thread = new PracticalTest01Var03Thread(this, firstNumber, secondNumber);

        practicalTest01Var03Thread.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(Constants.TAG, "Stopping service");
    }
}