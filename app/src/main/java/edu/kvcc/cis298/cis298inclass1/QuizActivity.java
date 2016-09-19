package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //Variables to hold the widget controls from the layout
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    //The question bank
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    //The index for the question bank that will pull a question
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Finding a reference to the textview in the layout by ID
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();


        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswer(false);
            }
        });
    }

    private void updateQuestion() {
        //Use the current index to get the question in the array at that point,
        //and also call the getTextResID method to get the associated string resource
        int question = mQuestionBank[mCurrentIndex].getTextResID();
        //Set the text for the question using the integer resource ID that was fetched
        //from the array of questions
        mQuestionTextView.setText(question);
    }

    //Method for comparing the user selected button to the answer on file for each question
    private void CheckAnswer(boolean userPressedTrue){
        //bool to represent the answer to the question we are on
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        //Integer to hold the resource ID of the correct/incorrect message
        //to display in the toast message
        int messageResid = 0;
        //if statement that will display the correct or incorrect toast message depending
        //on if the answer selected is true or false
        if (userPressedTrue == answerIsTrue){
            messageResid = R.string.correct_toast;
        } else {
            messageResid = R.string.incorrect_toast;
        }

        Toast.makeText(this,messageResid,Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//    //</editor-fold>
}
