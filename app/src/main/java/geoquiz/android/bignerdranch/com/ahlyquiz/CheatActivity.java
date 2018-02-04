package geoquiz.android.bignerdranch.com.ahlyquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_TRUE_ANSWER = "true_answer";
    private int mTrueAnswer;
    private TextView mAnswerTextView;
    private Button   mShowAnswerButton;
    public static Intent cheatIntent(Context packageContext, int trueAnswer) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_TRUE_ANSWER, trueAnswer);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mTrueAnswer=getIntent().getIntExtra(EXTRA_TRUE_ANSWER,0);
        castUtils();
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mAnswerTextView.setText(mTrueAnswer);
            }
        });
    }
  private void castUtils(){
        mAnswerTextView=findViewById(R.id.answer_text_view);
        mShowAnswerButton=findViewById(R.id.show_answer_button);
  }
}
