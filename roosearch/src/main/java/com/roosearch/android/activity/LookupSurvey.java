package com.roosearch.android.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.roosearch.android.R;
import com.roosearch.android.domain.Answer;
import com.roosearch.android.domain.Question;
import com.roosearch.android.domain.Survey;

public class LookupSurvey extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void submitSurveyLookup(View v)
    {
        Toast.makeText(this, "Looking up survey...", Toast.LENGTH_SHORT).show();
        Survey survey = lookupSurvey(123);

        Intent i = new Intent(this, SurveyRunner.class);
        i.putExtra("com.roosearch.Survey", survey);
        startActivity(i);
    }


    private Survey lookupSurvey(int id)
    {
        Survey survey = new Survey();
        survey.setTitle("Feedback on COSMOS");
        Question q1 = new Question("How long did you have to wait for a seat?");
        q1.addAvailableOptions(new Answer("Wait? What wait?"), new Answer("5 minutes"), new Answer("Forver, we gave up and left"));
        Question q2 = new Question("What was the food like?");
        q2.addAvailableOptions(new Answer("NOM NOM!"), new Answer("Not bad"), new Answer("Yuk"));
        Question q3 = new Question("Would you visit again?");
        q3.addAvailableOptions(new Answer("We've already reserved for next weekend"), new Answer("Maybe"), new Answer("No way"));

        survey.addQuestion(q1);
        survey.addQuestion(q2);
        survey.addQuestion(q3);

        return survey;
    }

}

