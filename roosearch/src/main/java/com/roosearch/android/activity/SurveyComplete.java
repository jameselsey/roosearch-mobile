package com.roosearch.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.roosearch.android.AppContext;
import com.roosearch.android.R;
import com.roosearch.android.domain.Customer;
import com.roosearch.android.domain.Question;
import com.roosearch.android.domain.Survey;
import com.roosearch.android.task.AsyncTaskCompleteListener;
import com.roosearch.android.task.FindRooTask;
import com.roosearch.android.task.SurveyUploadTask;

/**
 * Author:  JElsey
 * Date:    31/08/2012
 */
public class SurveyComplete extends Activity
{
    public static String TAG = "SurveyRunner";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surveycomplete);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        TextView tv = (TextView) findViewById(R.id.completeMessage);
        tv.setText("Thank you for taking the time to complete the survey");
        tv.setTextColor(R.color.dark_text_color);

        Survey s = getIntent().getExtras().getParcelable("com.roosearch.domain.Survey");

        if (s != null)
        {
            StringBuffer sb = new StringBuffer();
            sb.append("\n" + s.getTitle() + "\n");
            for (Question q : s.getQuestions())
            {
                sb.append("\nQ: " + q.getText());
                sb.append("\nA: " + q.getSelectedAnswer() + "\n");
            }
            tv.append("\n\n" + sb.toString());
        }

        new SurveyUploadTask(this, new SurveyUploadTaskCompleteListener()).execute(s);
    }

    public class SurveyUploadTaskCompleteListener implements AsyncTaskCompleteListener<Void> {
        @Override
        public void onTaskComplete(Void voidz) {
            Toast.makeText(SurveyComplete.this, "Survey uploaded", Toast.LENGTH_SHORT).show();
        }
    }
}
