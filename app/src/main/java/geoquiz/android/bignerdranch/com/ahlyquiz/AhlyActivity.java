package geoquiz.android.bignerdranch.com.ahlyquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AhlyActivity extends AppCompatActivity {
    private TextView mQuestionTextView;
    private  TextView mFirstChoiceTextView;
    private  TextView mSecondChoiceTextView;
    private  TextView mThirdChoiceTextView;
 //   private Button mCheatButton;
    private ArrayList<Integer> mQuestionAsked = new ArrayList<Integer>(10);
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private int mTrueAnswer=0;
    private TextView mQuestionTimerTextView;
    private  int timer = 15000;
    private int mResponse=0;
    private Toast toast;
    private boolean isInForeGround;
    private CountDownTimer mCountDownTimer;
    private Question[] mQuestionsBank= new Question[]{
            new Question(R.string.question_1, R.string.question_1_choice_1, R.string.question_1_choice_2, R.string.question_1_choice_3, R.string.question_1_choice_2),
            new Question(R.string.question_7, R.string.question_7_choice_1, R.string.question_7_choice_2, R.string.question_7_choice_3, R.string.question_7_choice_3),
            new Question(R.string.question_9, R.string.question_9_choice_1, R.string.question_9_choice_2, R.string.question_9_choice_3, R.string.question_9_choice_2),
            new Question(R.string.question_10, R.string.question_10_choice_1, R.string.question_10_choice_2, R.string.question_10_choice_3, R.string.question_10_choice_1),
            new Question(R.string.question_15, R.string.question_15_choice_1, R.string.question_15_choice_2, R.string.question_15_choice_3, R.string.question_15_choice_1),
            new Question(R.string.question_19, R.string.question_19_choice_1, R.string.question_19_choice_2, R.string.question_19_choice_3, R.string.question_19_choice_1),
            new Question(R.string.question_26, R.string.question_26_choice_1, R.string.question_26_choice_2, R.string.question_26_choice_3, R.string.question_26_choice_3),
            new Question(R.string.question_29, R.string.question_29_choice_1, R.string.question_29_choice_2, R.string.question_29_choice_3, R.string.question_29_choice_2),
            new Question(R.string.question_27, R.string.question_27_choice_1, R.string.question_27_choice_2, R.string.question_27_choice_3, R.string.question_27_choice_2),
            new Question(R.string.question_28, R.string.question_28_choice_1, R.string.question_28_choice_2, R.string.question_28_choice_3, R.string.question_28_choice_3),
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
//                if (mQuestionAsked.size() == mQuestionsBank.length){
//                    ShowPercent();
//                    mQuestionAsked.add(mCurrentIndex);
//                }
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

//        mCheatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int trueAnswer = mQuestionsBank[mCurrentIndex].getAnswerTrue();
//                Intent intent=new Intent(AhlyActivity.this,CheatActivity.class);
//                intent.putExtra("zip",trueAnswer);
//                startActivityForResult(intent,0);
//            }
//        });

    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState)
//    {
//        super.onSaveInstanceState(outState);
//        outState.putInt(KEY_INDEX, mCurrentIndex);
//       // mQuestionAsked.add(mCurrentIndex);
//       outState.putIntegerArrayList("myArray", mQuestionAsked);
//    }


    private void castUtils() {
        mQuestionTextView=  findViewById(R.id.question_text_view);
        mFirstChoiceTextView=  findViewById(R.id.first_choice_text_view);
        mSecondChoiceTextView=  findViewById(R.id.second_choice_text_view);
        mThirdChoiceTextView=  findViewById(R.id.third_choice_text_view);
        mNextButton=  findViewById(R.id.next_button);
        mPrevButton=  findViewById(R.id.prev_button);
      //  mCheatButton=findViewById(R.id.cheat_button);
        mQuestionTimerTextView=findViewById(R.id.question_timer_text_view);
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

        if(choice == answerIsTrue) {
            mResponse++;
            mTrueAnswer++;
            toast = new Toast(AhlyActivity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            LayoutInflater inflater = (LayoutInflater) AhlyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.correct_toast, null);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setView(view);
            if (isInForeGround) {
                toast.show();
            }
        }

        else if(choice!=answerIsTrue){
            mResponse++;
            toast = new Toast(AhlyActivity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            LayoutInflater inflater = (LayoutInflater) AhlyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        int resultResId = (mTrueAnswer*100) / 10;
        if (mQuestionAsked.size() == mQuestionsBank.length ) {
            if (isInForeGround) {

                Toast.makeText(this, Integer.toString(resultResId) + "% اجابات صح", Toast.LENGTH_LONG)
                        .show();
            }

        }

        if(resultResId==100){
            Toast.makeText(this, "مستوي المشجع الحقيقي اتفتح!", Toast.LENGTH_SHORT).show();
            Intent intent=ChooseLevelActivity.newIntent(AhlyActivity.this,resultResId);
            startActivity(intent);
            finish();

        }

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
    protected void onDestroy() {isInForeGround=false;
        super.onDestroy();
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
                    if(mResponse<10){
                        mQuestionAsked.add(mCurrentIndex);
                        Toast toast = new Toast(AhlyActivity.this);
                        toast.setDuration(Toast.LENGTH_LONG);
                        LayoutInflater inflater = (LayoutInflater) AhlyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.incorrect_toast, null);
                        toast.setGravity(Gravity.BOTTOM, 0, 0);
                        toast.setView(view);
                        if(isInForeGround) {
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
