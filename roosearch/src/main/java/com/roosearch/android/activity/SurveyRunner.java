package com.roosearch.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.roosearch.android.R;
import com.roosearch.android.domain.Answer;
import com.roosearch.android.domain.Survey;
import de.akquinet.android.androlog.Log;

import static java.lang.String.format;

/**
 * Author:  JElsey
 * Date:    31/08/2012
 */
public class SurveyRunner extends Activity {
    public static String TAG = "SurveyRunner";
    private Survey s;
    private int questionIndex = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surveyrunner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        s = getIntent().getExtras().getParcelable("com.roosearch.Survey");
        Log.d(TAG, "There are a total of " + s.getQuestionCount() + " questions");
        drawQuestionOnScreen(1);
    }

    public void previous(View v) {
        // work out if there is a previous question, and if so move to it
        if (s.getQuestionCount() > 1 && questionIndex > 1) {
            questionIndex--;
            drawQuestionOnScreen(questionIndex);
        } else {
            //if there are no other questions, move back to home screen, finish() this and scrap any progress
            finish();
        }
    }

    public void next(View v) {
        RadioGroup rg = (RadioGroup) findViewById(1);

        int selectedRadioId = rg.getCheckedRadioButtonId();
        if(selectedRadioId == -1){
            Toast.makeText(this, "Please select a response", Toast.LENGTH_SHORT).show();
            return;
        }

        s.getQuestion(questionIndex - 1).getResponses().get(selectedRadioId).setSelected(true);
        // work out if there is another question, then move to it
        if (s.getQuestionCount() > 1 && questionIndex < s.getQuestionCount()) {
            questionIndex++;
            drawQuestionOnScreen(questionIndex);
        } else {
            // if there are no other questions, show dialog saying submit or not
            Toast.makeText(this, "Reached the end of the survey", Toast.LENGTH_SHORT).show();
            // HERE we should process the entire survey, crunch data and post off (maybe async)

            Intent i = new Intent(this, SurveyComplete.class);
            i.putExtra("com.roosearch.domain.Survey", s);
            startActivity(i);
        }
    }

    /**
     * This method will redraw the screen based on a given question id.
     *
     * @param id int Question id, this is used to lookup a question from the survey and display it on screen
     */
    public void drawQuestionOnScreen(int id) {
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(s.getQuestion(id - 1).getText());   // subtract 1 as lists are indexed from 0

        LinearLayout linLay = (LinearLayout) findViewById(R.id.answers);
        linLay.removeAllViews();
        RadioGroup rg = new RadioGroup(this);
        rg.setId(1);
        for (int aIndex = 0; aIndex < s.getQuestion(id - 1).getResponses().size(); aIndex++) {
            Answer a = s.getQuestion(id - 1).getAvailableOption(aIndex);
            RadioButton button = new RadioButton(this);
            button.setText(a.getText());
            button.setTextColor(R.color.dark_text_color);
            button.setId(aIndex);
            rg.addView(button);
        }
        linLay.addView(rg);

        TextView status = (TextView) findViewById(R.id.status);
        status.setText(format("%d of %d", id, s.getQuestionCount()));
    }

}
