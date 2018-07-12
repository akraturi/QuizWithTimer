package com.example.amit.braintrainer.feature;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int mFirstnum;
    private int mSeconddnum;
    private TextView mQuesTextView;
    private List mAnsList;
    private int mCorrectAnsPos;
    private Button optionAButton;
    private Button optionBButton;
    private Button optionCButton;
    private Button optionDButton;
    private TextView mResultTextView;
    private int mScore=0;
    private int mTotalAttempted=0;
    private Button mScoreButton;
    private Button mTimerButton;
    private CountDownTimer mCountDownTimer;
    private Button mPlayAgainButton;
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuesTextView=findViewById(R.id.questextView);
        optionAButton=findViewById(R.id.buttonA);
        optionBButton=findViewById(R.id.buttonB);
        optionCButton=findViewById(R.id.buttonC);
        optionDButton=findViewById(R.id.buttonD);
        mResultTextView=findViewById(R.id.resulttextView);
        mScoreButton=findViewById(R.id.scorebutton);
        mTimerButton=findViewById(R.id.timerbutton);
        mPlayAgainButton=findViewById(R.id.playAgainButton);

        mCountDownTimer=new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long l) {
                mTimerButton.setText(Long.toString(l/1000));
            }

            @Override
            public void onFinish() {
                mResultTextView.setText("DONE!");
                mPlayAgainButton.setVisibility(View.VISIBLE);
                mMediaPlayer.stop();
                mMediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.over);
                mMediaPlayer.start();
            }
        }.start();
        prepareQuestion();
        mMediaPlayer=MediaPlayer.create(this,R.raw.playback);
        mMediaPlayer.start();





    }
    public void prepareQuestion()
    {   Random random=new Random();
        mFirstnum=random.nextInt(21);
        mSeconddnum=random.nextInt(21);
        mCorrectAnsPos=random.nextInt(4);
        mQuesTextView.setText(mFirstnum+"+"+mSeconddnum+"=?");
        mAnsList=new ArrayList<Integer>();
        for(int i=0;i<4;i++)
        {   if(i==mCorrectAnsPos)
          {
            mAnsList.add(mFirstnum+mSeconddnum);
          }
          else
          {
            int wrongAns=random.nextInt(41);
            while(wrongAns==mFirstnum+mSeconddnum)
            {
                wrongAns=random.nextInt(41);
            }
            mAnsList.add(wrongAns);
          }

        }
        optionAButton.setText(mAnsList.get(0).toString());
        optionBButton.setText(mAnsList.get(1).toString());
        optionCButton.setText(mAnsList.get(2).toString());
        optionDButton.setText(mAnsList.get(3).toString());
    }
    public void chooseAns(View view)
    {
        String resultString;

        if(view.getTag().toString().equals(Integer.toString(mCorrectAnsPos)))
        {
            resultString="Correct!";
            mScore++;


        }
        else
        {
            resultString="Incorrect!";
        }
        //Toast.makeText(this,resultString,Toast.LENGTH_SHORT).show();
        mResultTextView.setText(resultString);
        mTotalAttempted++;
        mScoreButton.setText(mScore+"/"+mTotalAttempted);
        prepareQuestion();
    }
    public void playAgain(View view)
    {
        recreate();
    }
}

