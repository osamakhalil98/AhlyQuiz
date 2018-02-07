package geoquiz.android.bignerdranch.com.ahlyquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_TRUE_ANSWER = "answer_is_true";
    private static final String EXTRA_ANSWER_COUNTER="answer_counter";
    public static Intent newIntent(Context packageContext, int answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_TRUE_ANSWER, answerIsTrue);
        return intent;
    }
    public static int wasAnswerShown(Intent result) {
        return result.getIntExtra(EXTRA_ANSWER_COUNTER, 0);
    }
    private int mTrueAnswer;
    private TextView mAnswerTextView;
    private int mCounterCheat=0;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        castUtils();
        mTrueAnswer=getIntent().getIntExtra(EXTRA_TRUE_ANSWER,0);
        mAnswerTextView.setText(mTrueAnswer);
        mCounterCheat++;
        setAnswerCounter(mCounterCheat);
    }
  private void castUtils(){
        mAnswerTextView=findViewById(R.id.answer_text_view);
  }
  private void setAnswerCounter(int counter){
      Intent data=new Intent();
      data.putExtra(EXTRA_ANSWER_COUNTER,counter);
      setResult(RESULT_OK,data);
  }
}
