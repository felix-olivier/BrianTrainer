package com.felixfelix.briantrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainBtn;
    RelativeLayout gameRelLay;



    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Boolean isGameActive;

    public void playAgain(View view){
        isGameActive = true;
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long msTilFinished) {
                timerTextView.setText(String.valueOf(msTilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainBtn.setVisibility(View.VISIBLE);
                isGameActive = false;
            }
        }.start();
    }

    public void generateQuestion () {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i<4; i++){
            if (i==locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a +b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    public void chooseAnswer (View view) {

        if (isGameActive) {

            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
                score++;
                resultTextView.setText("Correct!");
            } else {
                resultTextView.setText("Wrong!");
            }
            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            generateQuestion();
        }
    }

    public void start (View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelLay.setVisibility(View.VISIBLE);

        isGameActive = true;

        playAgain(findViewById(R.id.playAgainBtn));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startBtn);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        gameRelLay = (RelativeLayout) findViewById(R.id.gameRelLay);

    }
}
