package geoquiz.android.bignerdranch.com.ahlyquiz;

/**
 * Created by LENOVO on 1/26/2018.
 */

public class Question {

    private int mTextQuesid;
    private int mAnswerTrue;
    private int mChoice1;
    private int mChoice2;
    private int mChoice3;
    private  int mAnswered;
    private int mCheated;

    public int getmAnswered() {
        return mAnswered;
    }

    public void setmAnswered(int answerd) {
        mAnswered = answerd;
    }

    public Question (int mTextQuesid, int mChoice1, int mChoice2, int mChoice3 , int mAnswerTrue ){

      this.mTextQuesid=mTextQuesid;
        this.mAnswerTrue = mAnswerTrue;
        this.mChoice1=mChoice1;
        this.mChoice2=mChoice2;
        this.mChoice3=mChoice3;
        mAnswered=0;
        mCheated=0;
    }

    public int getCheated() {
        return mCheated;
    }

    public void setCheated(int cheated) {
        mCheated = cheated;
    }

    public int getChoice1() {
        return mChoice1;
    }

    public void setChoice1(int choice1) {
        mChoice1 = choice1;
    }

    public int getChoice2() {
        return mChoice2;
    }

    public void setChoice2(int choice2) {
        mChoice2 = choice2;
    }

    public int getChoice3() {
        return mChoice3;
    }

    public void setChoice3(int choice3) {
        mChoice3 = choice3;
    }

    public int getTextQuesid() {
        return mTextQuesid;
    }

    public void setTextQuesid(int textQuesid) {
        mTextQuesid = textQuesid;
    }

    public int getAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(int answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
