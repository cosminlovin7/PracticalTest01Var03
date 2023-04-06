package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private EditText firstNumberEditText;
    private EditText secondNumberEditText;
    private EditText resultEditText;
    private Button buttonAdd;
    private Button buttonMinus;
    private Button buttonNavigateSecondaryApp;
    private PracticalTest01Var03BroadcastReceiver practicalTest01Var03BroadcastReceiver;
    private IntentFilter intentFilter;

    private boolean isCorrectString(final String number) {
        if (number.length() == 0)
            return false;

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) >= '0' && number.charAt(i) <= '9')
                continue;

            return false;
        }

        return true;
    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Integer buttonId = view.getId();
            String firstNumber = null;
            String secondNumber = null;

            switch(buttonId) {
                case R.id.button_add:
                    firstNumber = firstNumberEditText.getText().toString();
                    secondNumber = secondNumberEditText.getText().toString();

                    if (firstNumber.length() != 0 && secondNumber.length() != 0) {
                        Double firstIntNumber = Double.parseDouble(firstNumber);
                        Double secondIntNumber = Double.parseDouble(secondNumber);
                        double result = firstIntNumber + secondIntNumber;

                        Intent intent = new Intent(PracticalTest01Var03MainActivity.this, PracticalTest01Var03Service.class);
                        intent.putExtra(Constants.FIRST_NUMBER_TEXTVIEW, firstNumber);
                        intent.putExtra(Constants.SECOND_NUMBER_TEXTVIEW, secondNumber);

                        startService(intent);

                        resultEditText.setText(firstNumber + " + " + secondNumber + " = " + result);
                    } else {
                        Toast.makeText(PracticalTest01Var03MainActivity.this, Constants.ONLY_NUMBERS, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button_minus:
                    firstNumber = firstNumberEditText.getText().toString();
                    secondNumber = secondNumberEditText.getText().toString();

                    if (firstNumber.length() != 0 && secondNumber.length() != 0) {
                        Double firstIntNumber = Double.parseDouble(firstNumber);
                        Double secondIntNumber = Double.parseDouble(secondNumber);
                        double result = firstIntNumber - secondIntNumber;

                        Intent intent = new Intent(PracticalTest01Var03MainActivity.this, PracticalTest01Var03Service.class);
                        intent.putExtra(Constants.FIRST_NUMBER_TEXTVIEW, firstNumber);
                        intent.putExtra(Constants.SECOND_NUMBER_TEXTVIEW, secondNumber);

                        startService(intent);

                        resultEditText.setText(firstNumber + " - " + secondNumber + " = " + result);
                    } else {
                        Toast.makeText(PracticalTest01Var03MainActivity.this, Constants.ONLY_NUMBERS, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.navigate_second_app_button:
                    if (!resultEditText.getText().toString().equalsIgnoreCase("")) {
                        Intent activityIntent = new Intent(PracticalTest01Var03MainActivity.this, PracticalTest01Var03SecondaryActivity.class);
                        activityIntent.putExtra(Constants.RESULT_TEXTVIEW, resultEditText.getText().toString());
                        startActivityForResult(activityIntent, Constants.REQUEST_CODE);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        firstNumberEditText = findViewById(R.id.first_number_textview);
        secondNumberEditText = findViewById(R.id.second_number_textview);
        resultEditText = findViewById(R.id.result_textview);
        buttonAdd = findViewById(R.id.button_add);
        buttonMinus = findViewById(R.id.button_minus);
        buttonNavigateSecondaryApp = findViewById(R.id.navigate_second_app_button);

        buttonAdd.setOnClickListener(buttonClickListener);
        buttonMinus.setOnClickListener(buttonClickListener);
        buttonNavigateSecondaryApp.setOnClickListener(buttonClickListener);

        practicalTest01Var03BroadcastReceiver = new PracticalTest01Var03BroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.DIFF_ACTION);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.FIRST_NUMBER_TEXTVIEW, firstNumberEditText.getText().toString());
        outState.putString(Constants.SECOND_NUMBER_TEXTVIEW, secondNumberEditText.getText().toString());
        outState.putString(Constants.RESULT_TEXTVIEW, resultEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String message = "";
        
        if (savedInstanceState.containsKey(Constants.FIRST_NUMBER_TEXTVIEW)) {
            firstNumberEditText.setText(savedInstanceState.get(Constants.FIRST_NUMBER_TEXTVIEW).toString());

            if (!firstNumberEditText.getText().toString().equalsIgnoreCase(""))
                message += "First Number:" + firstNumberEditText.getText().toString();
        }
        if (savedInstanceState.containsKey(Constants.SECOND_NUMBER_TEXTVIEW)) {
            secondNumberEditText.setText(savedInstanceState.get(Constants.SECOND_NUMBER_TEXTVIEW).toString());

            if (!secondNumberEditText.getText().toString().equalsIgnoreCase(""))
                message += " Second Number:" + secondNumberEditText.getText().toString();
        }
        if (savedInstanceState.containsKey(Constants.RESULT_TEXTVIEW)) {
            resultEditText.setText(savedInstanceState.get(Constants.RESULT_TEXTVIEW).toString());

            if (!resultEditText.getText().toString().equalsIgnoreCase(""))
                message += " Result:" + resultEditText.getText().toString();
        }
        
        if (message != "") {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == Constants.REQUEST_CODE) {
                Toast.makeText(this, Constants.CORRECT_OP, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, Constants.INCORRECT_OP, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(PracticalTest01Var03MainActivity.this, PracticalTest01Var03Service.class);
        stopService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(practicalTest01Var03BroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(practicalTest01Var03BroadcastReceiver, intentFilter);
    }
}