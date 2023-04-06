package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PracticalTest01Var03Thread extends Thread{
    private String firstNumber;
    private String secondNumber;
    private Context context;

    public PracticalTest01Var03Thread() {}
    public PracticalTest01Var03Thread(Context context, String firstNumber, String secondNumber) {
        this.context = context;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public void run() {
        sendMessage(0);
        sleep();
        sendMessage(1);
    }

    private void sendMessage(int code) {
        Intent intent = new Intent();

        if (code == 0) {
            String message = String.valueOf(Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber));
            intent.setAction(Constants.SUM_ACTION);
            intent.putExtra(Constants.MESSAGE, "SUM:" + message);
            Log.d(Constants.TAG, "Service sending sum message.");
        } else {
            String message = String.valueOf(Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber));
            intent.setAction(Constants.DIFF_ACTION);
            intent.putExtra(Constants.MESSAGE, "DIFF:" + message);
            Log.d(Constants.TAG, "Service sending diff message");
        }

        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            Log.d(Constants.TAG, e.getMessage());
        }
    }
}
