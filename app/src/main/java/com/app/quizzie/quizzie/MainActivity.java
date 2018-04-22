package com.app.quizzie.quizzie;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {
    private boolean isSubmitted = false;
    private int correct_ans_count = 0;
    private final String[] ans6to8 = {"8", "Objects", "objects", "final"};
    private EditText ans_six_input;
    private EditText ans_seven_input;
    private EditText ans_eight_input;
    @BindView(R.id.question_1_option_3_rb)
    RadioButton question_1_option_3;
    private Button submit;
    @BindView(R.id.question_2_option_1_rb)
    RadioButton question_2_option_1;
    @BindView(R.id.question_3_option_2_rb)
    RadioButton question_3_option_2;
    @BindView(R.id.question_4_option_1_cb)
    CheckBox question_4_option_1;
    @BindView(R.id.question_4_option_2_cb)
    CheckBox question_4_option_2;
    @BindView(R.id.question_4_option_3_cb)
    CheckBox question_4_option_3;
    @BindView(R.id.question_4_option_4_cb)
    CheckBox question_4_option_4;
    @BindView(R.id.question_5_option_1_cb)
    CheckBox question_5_option_1;
    @BindView(R.id.question_5_option_2_cb)
    CheckBox question_5_option_2;
    @BindView(R.id.question_5_option_3_cb)
    CheckBox question_5_option_3;
    @BindView(R.id.question_5_option_4_cb)
    CheckBox question_5_option_4;
    private int Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        ans_six_input = findViewById(R.id.input_ans_six_et);
        ans_seven_input = findViewById(R.id.input_ans_seven_et);
        ans_eight_input = findViewById(R.id.input_ans_eight_et);
        submit = findViewById(R.id.submit_quiz_btn);
        ButterKnife.bind(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
            }
        });
    }

    private void checkAnswers() {
        if (!isSubmitted) {

//            Checks Answer 1
            if (question_1_option_3.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 2
            if (question_2_option_1.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 3
            if (question_3_option_2.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 4
            if (question_4_option_1.isChecked() && question_4_option_2.isChecked() && !question_4_option_3.isChecked() && !question_4_option_4.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 5
            if (question_5_option_1.isChecked() && question_5_option_2.isChecked() && !question_5_option_3.isChecked() && question_5_option_4.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answers 6
            String ans_six_input_string = ans_six_input.getText().toString();
            if (!ans_six_input_string.equals("")) {
                if (ans_six_input_string.equals(ans6to8[0])) {
                    Score += 4;
                    correct_ans_count += 1;
                }
            }

//            Checks Answer 7
            String answer_seven_input_string = ans_seven_input.getText().toString();
            if (answer_seven_input_string.equals(ans6to8[1]) || answer_seven_input_string.equals(ans6to8[2])) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 8
            String answer_eight_input_string = ans_eight_input.getText().toString();
            if (answer_eight_input_string.equals(ans6to8[3])) {
                Score += 4;
                correct_ans_count += 1;
            }

            Toast.makeText(this, "Your Score is: " + Score + ". Correct Answers: " + correct_ans_count + "/8", Toast.LENGTH_SHORT).show();

            isSubmitted = true;
        } else {
            Toast.makeText(this, "You have already submitted the answer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
//          This code is run when back button is pressed
        if (!isSubmitted) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Confirm Exit...");

            alertDialog.setMessage("Your progress will be lost. Do you wish to Continue?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = alertDialog.create();
            alert.show();

        } else {
            Intent i = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(i);
            finish();
        }
    }
}
