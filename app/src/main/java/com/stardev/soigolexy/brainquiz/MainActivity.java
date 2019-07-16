package com.stardev.soigolexy.brainquiz;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView resultsTextView;
    TextView scoreTextView;
    TextView timerTextView;
    ConstraintLayout goLayout;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21) ;
        int b = rand.nextInt(21) ;

        sumTextView.setText(Integer.toString(a) + "+"  + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for (int i=0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                int wrongAnswer = rand.nextInt(40);
                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(40);

                }
                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultsTextView.setText("Correct!");
            score++;
        }else {
            resultsTextView.setText("Wrong");
        }
        numberOfQuestions ++;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        newQuestion();


    }
    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        resultsTextView.setText("");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+ "S");
            }

            @Override
            public void onFinish() {
                resultsTextView.setText("Done");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();

    }
    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        goLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        goButton = findViewById(R.id.goButton);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        resultsTextView = findViewById(R.id.resultsTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButon);
        goLayout = findViewById(R.id.goLayout);

        goButton.setVisibility(View.VISIBLE);
        goLayout.setVisibility(View.INVISIBLE);

    }
}
