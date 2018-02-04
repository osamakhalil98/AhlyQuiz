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
    private int mResultByUser;
    private TextView mFirstPercentageTextView;
    private TextView mSecondPercentageTextView;
    private TextView mThirdPercentageTextView;
    private int mSecondResultByUser;
    private int mThirdResultByUser;
    private ImageView mMedalImageView;
    private TextView mCongratsTextView;
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
       showMedal();
        mNormalFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooseLevelActivity.this, AhlyActivity.class);
                startActivity(intent);
            }
        });

        mTrueFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mResultByUser==100){
                    Intent intent=new Intent(ChooseLevelActivity.this, TrueFanActivity.class);
                    startActivity(intent);
                }
                else if(mSecondResultByUser==100){
                    Intent intent=new Intent(ChooseLevelActivity.this, TrueFanActivity.class);
                    startActivity(intent);
                }
                else if(mThirdResultByUser==100){
                    Intent intent=new Intent(ChooseLevelActivity.this, TrueFanActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ChooseLevelActivity.this, "لازم تقفل مستوي المشجع العادي الاول!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCapoFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mResultByUser==100){
                    Toast.makeText(ChooseLevelActivity.this, "لازم تقفل مستوي المشجع الحقيقي الاول!", Toast.LENGTH_SHORT).show();

                }
               else if(mSecondResultByUser==100){
                    Intent intent=new Intent(ChooseLevelActivity.this, CapoFanActivity.class);
                    startActivity(intent);
                }
                else if(mThirdResultByUser==100){
                    Intent intent=new Intent(ChooseLevelActivity.this, CapoFanActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ChooseLevelActivity.this, "لازم تقفل مستوي المشجع العادي الاول!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void castUtils(){
        mNormalFanButton=findViewById(R.id.normal_fan_button);
        mTrueFanButton=findViewById(R.id.true_fan_button);
        mCapoFanButton=findViewById(R.id.capo_fan_button);
        mFirstPercentageTextView=findViewById(R.id.first_percentage_text_view);
        mSecondPercentageTextView=findViewById(R.id.second_percentage_text_view);
        mThirdPercentageTextView=findViewById(R.id.third_percentage_text_view);
        mCongratsTextView=findViewById(R.id.congrats_text_view);
        mMedalImageView=findViewById(R.id.medal_image_view);
    }

private void showMedal(){
        mCongratsTextView.setVisibility(View.INVISIBLE);
        mMedalImageView.setVisibility(View.INVISIBLE);
        if(mThirdResultByUser==100){
            mCongratsTextView.setVisibility(View.VISIBLE);
            mMedalImageView.setVisibility(View.VISIBLE);
        }
}
    private void showPercentage(){
        mFirstPercentageTextView.setVisibility(View.INVISIBLE);
        mSecondPercentageTextView.setVisibility(View.INVISIBLE);
        mThirdPercentageTextView.setVisibility(View.INVISIBLE);
        if(mResultByUser==100){
            mFirstPercentageTextView.setVisibility(View.VISIBLE);
        }
        if(mSecondResultByUser==100){
            mFirstPercentageTextView.setVisibility(View.VISIBLE);
            mSecondPercentageTextView.setVisibility(View.VISIBLE);
        }
if(mThirdResultByUser==100){
    mFirstPercentageTextView.setVisibility(View.VISIBLE);
    mSecondPercentageTextView.setVisibility(View.VISIBLE);
    mThirdPercentageTextView.setVisibility(View.VISIBLE);
}

    }
}
