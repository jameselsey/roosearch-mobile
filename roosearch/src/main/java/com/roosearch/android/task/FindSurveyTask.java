package com.roosearch.android.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roosearch.android.AppContext;
import com.roosearch.android.domain.Survey;
import com.roosearch.android.provider.DataProvider;
import de.akquinet.android.androlog.Log;

public class FindSurveyTask extends AsyncTask<Integer, Integer, Survey> {

    private static final String TAG = "FindSurveyTask";

    private Context context;
    private AsyncTaskCompleteListener<Survey> listener;
    private ProgressDialog dialog;

    public FindSurveyTask(Context ctx, AsyncTaskCompleteListener<Survey> listener) {
        this.context = ctx;
        this.listener = listener;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Searching...");
        dialog.show();
    }

    @Override
    protected Survey doInBackground(Integer... args) {
        int surveyId = args[0];
        Log.d(TAG, "Searching for survey using id " + surveyId);
        return AppContext.getInstance().getDataProvider().getSurvey(surveyId);
    }

    @Override
    protected void onPostExecute(Survey survey) {
        super.onPostExecute(survey);
        dialog.dismiss();
        Log.d(TAG, "Found survey");
        listener.onTaskComplete(survey);
    }
}
