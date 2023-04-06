package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

                        resultEditText.setText(firstNumber +" + " + secondNumber + " = " + result);
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

                        resultEditText.setText(firstNumber +" - " + secondNumber + " = " + result);
                    } else {
                        Toast.makeText(PracticalTest01Var03MainActivity.this, Constants.ONLY_NUMBERS, Toast.LENGTH_SHORT).show();
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

        buttonAdd.setOnClickListener(buttonClickListener);
        buttonMinus.setOnClickListener(buttonClickListener);
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

        if (savedInstanceState.containsKey(Constants.FIRST_NUMBER_TEXTVIEW)) {
            firstNumberEditText.setText(savedInstanceState.get(Constants.FIRST_NUMBER_TEXTVIEW).toString());
        }
        if (savedInstanceState.containsKey(Constants.SECOND_NUMBER_TEXTVIEW)) {
            secondNumberEditText.setText(savedInstanceState.get(Constants.SECOND_NUMBER_TEXTVIEW).toString());
        }
        if (savedInstanceState.containsKey(Constants.RESULT_TEXTVIEW)) {
            resultEditText.setText(savedInstanceState.get(Constants.RESULT_TEXTVIEW).toString());
        }
    }
}