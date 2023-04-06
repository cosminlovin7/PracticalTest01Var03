package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    private EditText secondaryResultTextView;
    private Button buttonCorrect;
    private Button buttonIncorrect;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Integer buttonId = view.getId();

            switch(buttonId) {
                case R.id.button_correct:
                    setResult(Constants.CORRECT_CODE);
                    finish();
                    break;
                case R.id.button_incorrect:
                    setResult(Constants.INCORRECT_CODE);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        EditText secondaryResultTextView = findViewById(R.id.secondary_result_textview);
        Button buttonCorrect = findViewById(R.id.button_correct);
        Button buttonIncorrect = findViewById(R.id.button_incorrect);
        buttonCorrect.setOnClickListener(buttonClickListener);
        buttonIncorrect.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null) {
            String resultValue = intent.getStringExtra(Constants.RESULT_TEXTVIEW);

            secondaryResultTextView.setText(resultValue);
        }
    }
}