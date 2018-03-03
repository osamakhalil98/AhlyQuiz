package geoquiz.android.bignerdranch.com.ahlyquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseLevelActivity extends AppCompatActivity {
    private static final String UNLOCK_TRUE_LEVEL = "unlock_true_level";
    private static final String UNLOCK_CAPO_LEVEL = "unlock_capo_level";
    private static final String FINISHED_CAPO_LEVEL = "finished_capo_level";
    private Button mNormalFanButton;
    private Button mTrueFanButton;
    private Button mCapoFanButton;
    private ImageView mShareScoreImage;
    private int mResultByUser;
    private TextView mFirstPercentageTextView;
    private TextView mSecondPercentageTextView;
    private TextView mThirdPercentageTextView;
    private int mSecondResultByUser;
    private int mThirdResultByUser;
    public static Intent newIntent(Context packageContext, int resultByUser) {
        Intent intent = new Intent(packageContext, ChooseLevelActivity.class);
        intent.putExtra(UNLOCK_TRUE_LEVEL, resultByUser);
        return intent;
    }
    public static Intent secondIntent(Context packageContext, int resultByUser) {
        Intent intent = new Intent(packageContext, ChooseLevelActivity.class);
        intent.putExtra(UNLOCK_CAPO_LEVEL, resultByUser);
        return intent;
    }
    public static Intent thirdIntent(Context packageContext, int resultByUser) {
        Intent intent = new Intent(packageContext, ChooseLevelActivity.class);
        intent.putExtra(FINISHED_CAPO_LEVEL, resultByUser);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        castUtils();
       mResultByUser=getIntent().getIntExtra(UNLOCK_TRUE_LEVEL,0);
       mSecondResultByUser=getIntent().getIntExtra(UNLOCK_CAPO_LEVEL,0);
       mThirdResultByUser=getIntent().getIntExtra(FINISHED_CAPO_LEVEL,0);
       showPercentage();
        mNormalFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooseLevelActivity.this, NormalFan.class);
                startActivity(intent);
            }
        });

        mTrueFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mResultByUser>=50){
                    Intent intent=new Intent(ChooseLevelActivity.this, TrueFanActivity.class);
                    startActivity(intent);
                }
                else if(mSecondResultByUser>=50){
                    Intent intent=new Intent(ChooseLevelActivity.this, TrueFanActivity.class);
                    startActivity(intent);
                }
                else if(mThirdResultByUser>=50){
                    Intent intent=new Intent(ChooseLevelActivity.this, TrueFanActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ChooseLevelActivity.this, "لازم تعدي مستوي المشجع العادي الاول!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCapoFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mResultByUser>=50){
                    Toast.makeText(ChooseLevelActivity.this, "لازم تعدي مستوي المشجع الحقيقي الاول!", Toast.LENGTH_SHORT).show();
                }
               else if(mSecondResultByUser>=50){
                    Intent intent=new Intent(ChooseLevelActivity.this, CapoFanActivity.class);
                    startActivity(intent);
                }
               else  if(mThirdResultByUser>=50){
                    Intent intent=new Intent(ChooseLevelActivity.this, CapoFanActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ChooseLevelActivity.this, "لازم تعدي مستوي المشجع العادي الاول!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mShareScoreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendReport=new Intent(Intent.ACTION_SEND);
                sendReport.setType("text/plain");
                sendReport.putExtra(Intent.EXTRA_TEXT, getQuizReport());
                sendReport.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.quiz_score_share));
                sendReport = Intent.createChooser(sendReport, getString(R.string.send_score));
                startActivity(sendReport);
            }
        });
    }

    private String getQuizReport() {
        int score;
        score=mResultByUser+mSecondResultByUser+mThirdResultByUser;
        String quizScore=getString(R.string.quiz_score_share,score);
        return  quizScore;
    }


    private void castUtils(){
        mNormalFanButton=findViewById(R.id.normal_fan_button);
        mTrueFanButton=findViewById(R.id.true_fan_button);
        mCapoFanButton=findViewById(R.id.capo_fan_button);
        mFirstPercentageTextView=findViewById(R.id.first_percentage_text_view);
        mSecondPercentageTextView=findViewById(R.id.second_percentage_text_view);
        mThirdPercentageTextView=findViewById(R.id.third_percentage_text_view);
        mShareScoreImage=findViewById(R.id.share_score_image);
    }

    private void showPercentage(){
        mFirstPercentageTextView.setText(mResultByUser+"%");
       mSecondPercentageTextView.setText(mSecondResultByUser+"%");
        mThirdPercentageTextView.setText(mThirdResultByUser+"%");
//        mFirstPercentageTextView.setVisibility(View.INVISIBLE);
//        mFirstPercentageTextView.setVisibility(View.INVISIBLE);
//        mSecondPercentageTextView.setVisibility(View.INVISIBLE);
//            if(mResultByUser>=50&&mSecondResultByUser>=50&&mThirdResultByUser>=50){
//                mFirstPercentageTextView.setVisibility(View.VISIBLE);
//                mSecondPercentageTextView.setVisibility(View.VISIBLE);
//                mThirdPercentageTextView.setVisibility(View.VISIBLE);
//            }
//           else  if(mSecondResultByUser>=50){
//                mFirstPercentageTextView.setVisibility(View.VISIBLE);
//                mSecondPercentageTextView.setVisibility(View.VISIBLE);
//                mFirstPercentageTextView.setText(mResultByUser+"%");
//                mSecondPercentageTextView.setText(mSecondResultByUser+"%");
//            }
//            else  if(mThirdResultByUser>=50){
//                mFirstPercentageTextView.setVisibility(View.VISIBLE);
//                mSecondPercentageTextView.setVisibility(View.VISIBLE);
//                mThirdPercentageTextView.setVisibility(View.VISIBLE);
//                mThirdPercentageTextView.setText(mThirdResultByUser+"%");
//                mFirstPercentageTextView.setText(mResultByUser+"%");
//                mSecondPercentageTextView.setText(mSecondResultByUser+"%");
//            }



    }
}
