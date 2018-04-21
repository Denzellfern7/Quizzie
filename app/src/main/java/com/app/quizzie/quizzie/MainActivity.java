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

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {
    private boolean isSubmitted = false;
    private int correct_ans_count = 0;
    private double Score = 0;
    private RadioButton radioButton;
    private CheckBox chk1;
    private CheckBox chk2;
    private CheckBox chk4;
    private EditText ans_six_input;
    private EditText ans_seven_input;
    private EditText ans_eight_input;
    private final int[] ans1to6 = {R.id.question1_option3, R.id.question2_option1, R.id.question3_option2, R.id.question4_option1, R.id.question4_option2, R.id.question5_option1, R.id.question5_option2, R.id.question5_option4, 8};
    private final String[] ans7to8 = {"Objects", "objects", "final"};
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        ans_six_input = findViewById(R.id.input_ans_six);
        ans_seven_input = findViewById(R.id.input_ans_seven);
        ans_eight_input = findViewById(R.id.input_ans_eight);
        submit = findViewById(R.id.submit_quiz);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers(ans1to6[0], ans1to6[1], ans1to6[2], ans1to6[3], ans1to6[4], ans1to6[5], ans1to6[6], ans1to6[7]);
            }
        });
    }

    private void checkAnswers(int ans1, int ans2, int ans3, int ans4_1, int ans4_2, int ans5_1, int ans5_2, int ans5_3) {
        if (!isSubmitted) {

//            Checks Answer 1
            radioButton = findViewById(ans1);
            if (radioButton.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
                radioButton.setChecked(false);
            }

//            Checks Answer 2
            radioButton = findViewById(ans2);
            if (radioButton.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 3
            radioButton = findViewById(ans3);
            if (radioButton.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 4
            chk1 = findViewById(ans4_1);
            chk2 = findViewById(ans4_2);
            if (chk1.isChecked() && chk2.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            } else if (chk1.isChecked() || chk2.isChecked()) {
                Score += 2;
                correct_ans_count += 1;
            }

//            Checks Answer 5
            chk1 = findViewById(ans5_1);
            chk2 = findViewById(ans5_2);
            chk4 = findViewById(ans5_3);
            if (chk1.isChecked() && chk2.isChecked() && chk4.isChecked()) {
                Score += 4;
                correct_ans_count += 1;
            } else if (chk1.isChecked() && chk2.isChecked() || chk1.isChecked() && chk4.isChecked() || chk2.isChecked() && chk4.isChecked()) {
                Score += 2.6;
                correct_ans_count += 1;
            } else if (chk1.isChecked() || chk2.isChecked() || chk4.isChecked()) {
                Score += 1.3;
                correct_ans_count += 1;
            }

//            Checks Answers 6
            String ans_six_input_string = ans_six_input.getText().toString();
            if (!ans_six_input_string.equals("")) {
                if (Integer.parseInt(ans_six_input_string) == ans1to6[8]) {
                    Score += 4;
                    correct_ans_count += 1;
                }
            }

//            Checks Answer 7
            String answer_seven_input_string = ans_seven_input.getText().toString();
            if (answer_seven_input_string.equals(ans7to8[0]) || answer_seven_input_string.equals(ans7to8[1])) {
                Score += 4;
                correct_ans_count += 1;
            }

//            Checks Answer 8
            String answer_eight_input_string = ans_eight_input.getText().toString();
            if (answer_eight_input_string.equals(ans7to8[2])) {
                Score += 4;
                correct_ans_count += 1;
            }

            if (Score == Math.round(Score)) {
                Toast.makeText(this, "Your Score is: " + (int) Score + ". " + correct_ans_count + "/8", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Your Score is: " + Score + ". " + correct_ans_count + "/8", Toast.LENGTH_SHORT).show();
            }
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
            // Maria
        }
    }
}
