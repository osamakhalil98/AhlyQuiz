package geoquiz.android.bignerdranch.com.ahlyquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CapoFanActivity extends AppCompatActivity {
    private TextView mQuestionTextView;
    private  TextView mFirstChoiceTextView;
    private  TextView mSecondChoiceTextView;
    private  TextView mThirdChoiceTextView;
    private Button mCheatButton;
    private static final int REQUEST_CODE_CHEAT=0;
    private int cheatCounter;
    private static final String KEY_INDEX="index";
    private ArrayList<Integer> mQuestionAsked = new ArrayList<Integer>(9);
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    QuestionLab mScore=new QuestionLab();
    private int mTrueAnswer=0;
    private TextView mQuestionTimerTextView;
    MediaPlayer correctAnswer;
    MediaPlayer wrongAnswer;
    private boolean musicOnOrOff=true;
    private ImageView mMusicOn;
    private ImageView mMusicOff;
    private boolean isInForeGround;
    private  int timer = 10000;
    private int mResponse=0;
    private CountDownTimer mCountDownTimer;
    private Question[] mQuestionsBank= new Question[]{
            new Question(R.string.question_3, R.string.question_3_choice_1, R.string.question_3_choice_2, R.string.question_3_choice_3, R.string.question_3_choice_1),
            new Question(R.string.question_12, R.string.question_12_choice_1, R.string.question_12_choice_2, R.string.question_12_choice_3, R.string.question_12_choice_3),
            new Question(R.string.question_16, R.string.question_16_choice_1, R.string.question_16_choice_2, R.string.question_16_choice_3, R.string.question_16_choice_3),
            new Question(R.string.question_18, R.string.question_18_choice_1, R.string.question_18_choice_2, R.string.question_18_choice_3, R.string.question_18_choice_1),
            new Question(R.string.question_17, R.string.question_17_choice_1, R.string.question_17_choice_2, R.string.question_17_choice_3, R.string.question_17_choice_1),
            new Question(R.string.question_20, R.string.question_20_choice_1, R.string.question_20_choice_2, R.string.question_20_choice_3, R.string.question_20_choice_1),
            new Question(R.string.question_22, R.string.question_22_choice_1, R.string.question_22_choice_2, R.string.question_22_choice_3, R.string.question_22_choice_3),
            new Question(R.string.question_23, R.string.question_23_choice_1, R.string.question_23_choice_2, R.string.question_23_choice_3, R.string.question_23_choice_2),
            new Question(R.string.question_25, R.string.question_25_choice_1, R.string.question_25_choice_2, R.string.question_25_choice_3, R.string.question_25_choice_1),


    };
    private int mCurrentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahly);
        if(savedInstanceState!=null) {
            //  mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            //  mQuestionAsked.add(mCurrentIndex);
            // mQuestionAsked = savedInstanceState.getIntegerArrayList("myarray");
        }
        castUtils();
        updateMethod();
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Next();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Prev();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCountDownTimer.cancel();
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateMethod();
            }
        });
        mFirstChoiceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Choice1=mQuestionsBank[mCurrentIndex].getChoice1();
                mQuestionAsked.add(mCurrentIndex);
                checkAns(Choice1);
              Next();
            }
        });
        mSecondChoiceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Choice2=mQuestionsBank[mCurrentIndex].getChoice2();
                mQuestionAsked.add(mCurrentIndex);
                checkAns(Choice2);
                Next();
            }
        });
        mMusicOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicOnOrOff=false;
                mMusicOn.setVisibility(View.INVISIBLE);
                mMusicOff.setVisibility(View.VISIBLE);
            }
        });
        mMusicOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicOnOrOff=true;
                mMusicOff.setVisibility(View.INVISIBLE);
                mMusicOn.setVisibility(View.VISIBLE);
            }
        });
        mThirdChoiceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Choice3=mQuestionsBank[mCurrentIndex].getChoice3();
                mQuestionAsked.add(mCurrentIndex);
                checkAns(Choice3);
               Next();
            }
        });
        //    mBank[Current].mAns() = 1 ;

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int trueAnswer = mQuestionsBank[mCurrentIndex].getAnswerTrue();
                Intent intent = CheatActivity.newIntent(CapoFanActivity.this, trueAnswer);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
                if(mQuestionsBank[mCurrentIndex].getmAnswered()==0){
                    cheatCounter++;
                }
                if(cheatCounter>=3 || mQuestionsBank[mCurrentIndex].getmAnswered() == 1){
                    mCheatButton.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        // mQuestionAsked.add(mCurrentIndex);
        outState.putIntegerArrayList("myArray", mQuestionAsked);
    }

    private void castUtils() {
        mQuestionTextView=  findViewById(R.id.question_text_view);
        mFirstChoiceTextView=  findViewById(R.id.first_choice_text_view);
        mSecondChoiceTextView=  findViewById(R.id.second_choice_text_view);
        mThirdChoiceTextView=  findViewById(R.id.third_choice_text_view);
        mNextButton=  findViewById(R.id.next_button);
        mPrevButton=  findViewById(R.id.prev_button);
        mCheatButton=findViewById(R.id.cheat_button);
        mQuestionTimerTextView=findViewById(R.id.question_timer_text_view);
        mMusicOn=findViewById(R.id.music_on);
        mMusicOff=findViewById(R.id.music_off);
    }

    private void updateMethod() {
        int Question=mQuestionsBank[mCurrentIndex].getTextQuesid();
        mQuestionTextView.setText(Question);
        int Choice1=mQuestionsBank[mCurrentIndex].getChoice1();
        mFirstChoiceTextView.setText(Choice1);
        int Choice2=mQuestionsBank[mCurrentIndex].getChoice2();
        mSecondChoiceTextView.setText(Choice2);
        int Choice3=mQuestionsBank[mCurrentIndex].getChoice3();
        mThirdChoiceTextView.setText(Choice3);
        ShowPercent();
        setButtons();
        questionTimer();
    }


    private void checkAns ( int choice )
    {

        int answerIsTrue = mQuestionsBank[mCurrentIndex].getAnswerTrue();

        if(choice == answerIsTrue){
            if(musicOnOrOff) {
                correctAnswerSound();
            }
            mResponse++;
            mTrueAnswer++;
            Toast toast = new Toast(CapoFanActivity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            LayoutInflater inflater = (LayoutInflater) CapoFanActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.correct_toast, null);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setView(view);
            if (isInForeGround) {
                toast.show();
            }
        }

        else if(choice!=answerIsTrue){
            if(musicOnOrOff) {
                wrongAnswerSound();
            }
            mResponse++;
            Toast toast = new Toast(CapoFanActivity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            LayoutInflater inflater = (LayoutInflater) CapoFanActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.incorrect_toast, null);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setView(view);
            if (isInForeGround) {
                toast.show();
            }
        }
        else{
            mResponse=0;
        }
        mQuestionsBank[mCurrentIndex].setmAnswered(1);
        setButtons();
    }
    private void correctAnswerSound(){
        correctAnswer=MediaPlayer.create(this,R.raw.correct_answer_sound);
        try {
            if (correctAnswer.isPlaying()) {
                correctAnswer.stop();
                correctAnswer.release();
                correctAnswer = MediaPlayer.create(this, R.raw.correct_answer_sound);
            } correctAnswer.start();
        } catch(Exception e) { e.printStackTrace(); }
    }
    private void wrongAnswerSound(){
        wrongAnswer=MediaPlayer.create(this,R.raw.wrong_answer_sound);
        try {
            if (wrongAnswer.isPlaying()) {
                wrongAnswer.stop();
                wrongAnswer.release();
                wrongAnswer = MediaPlayer.create(this, R.raw.wrong_answer_sound);
            }
            if(isInForeGround) {
                wrongAnswer.start();
            }
        }
        catch(Exception e) { e.printStackTrace(); }
    }
    @Override
    protected void onResume() {
        super.onResume();
        isInForeGround=true;
    }
    @Override
    protected void onStop() {
        isInForeGround=false;
        super.onStop();

    }
    @Override
    protected void onDestroy() {
        isInForeGround=false;
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(CapoFanActivity.this);
        builder.setMessage(R.string.levels_activity);
        builder.setCancelable(true);
        builder.setNegativeButton("ايوه", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(CapoFanActivity.this, ChooseLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setPositiveButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private  void  Prev (){
        if(mCurrentIndex==0){
            return;
        }

        if (mQuestionAsked.size() == mQuestionsBank.length + 1){
            mQuestionAsked.add(mCurrentIndex);
        }
        if (mQuestionsBank[mCurrentIndex].getmAnswered() > 0){
            if(mCountDownTimer!=null){
                mCountDownTimer.cancel();
                mCountDownTimer=null;
                mQuestionTimerTextView.setText("Done");
            }
        }
        else{
            mCountDownTimer.cancel();
        }
        mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
        updateMethod();
    }
    private void Next (){
        mCountDownTimer.cancel();
        if (mQuestionAsked.size() == mQuestionsBank.length  ){
            ShowPercent();
            mQuestionAsked.add(mCurrentIndex);
        }
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
        updateMethod();
    }

    private void setButtons() {

        if (mQuestionsBank[mCurrentIndex].getmAnswered() > 0) {
            // make buttons disabled
            mFirstChoiceTextView.setEnabled(false);
            mSecondChoiceTextView.setEnabled(false);
            mThirdChoiceTextView.setEnabled(false);

        }
        else
        {
            mFirstChoiceTextView.setEnabled(true);
            mSecondChoiceTextView.setEnabled(true);
            mThirdChoiceTextView.setEnabled(true);
        }
    }
    private void ShowPercent (){

        int resultResId = (mTrueAnswer*100) / 9;
        mScore.setScore(resultResId);
        if (mQuestionAsked.size() == mQuestionsBank.length ) {
            if (isInForeGround) {
                Intent intent = ChooseLevelActivity.thirdIntent(CapoFanActivity.this, resultResId);
                startActivity(intent);
                finish();
            }
        }

    }

    private void questionTimer () {
        if (mQuestionsBank[mCurrentIndex].getmAnswered() == 0) {

            mCountDownTimer = new CountDownTimer(timer, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mQuestionTimerTextView.setText("Time: " + millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
                    mQuestionTimerTextView.setText("Done");
                    mQuestionsBank[mCurrentIndex].setmAnswered(1);
                    setButtons();
                    if(mResponse<9){
                        mQuestionAsked.add(mCurrentIndex);
                        if(musicOnOrOff) {
                            wrongAnswerSound();
                        }
                        Toast toast = new Toast(CapoFanActivity.this);
                        toast.setDuration(Toast.LENGTH_LONG);
                        LayoutInflater inflater = (LayoutInflater)CapoFanActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.incorrect_toast, null);
                        toast.setGravity(Gravity.BOTTOM, 0, 0);
                        toast.setView(view);
                        if (isInForeGround) {
                            toast.show();
                        }
                        Next();
                    }
                }
            }.start();
        } else {
            mQuestionTimerTextView.setText("Done");
        }
    }

}
