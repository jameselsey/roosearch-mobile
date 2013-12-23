package com.roosearch.android.task;

import android.content.Context;
import android.os.AsyncTask;
import com.roosearch.android.AppContext;
import com.roosearch.android.domain.Survey;

public class SurveyUploadTask extends AsyncTask<Survey, Integer, Void> {

    private Context context;
    private AsyncTaskCompleteListener<Void> listener;

    public SurveyUploadTask(Context ctx, AsyncTaskCompleteListener<Void> listener) {
        this.context = ctx;
        this.listener = listener;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Survey... surveys) {
        Survey survey = surveys[0];

        AppContext.getInstance().getDataProvider().postResults(survey);

        return null;
    }

    @Override
    protected void onPostExecute(Void voidz) {
        super.onPostExecute(voidz);

        listener.onTaskComplete(voidz);
    }
}
