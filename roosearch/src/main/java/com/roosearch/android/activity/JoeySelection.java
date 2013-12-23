package com.roosearch.android.activity;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.roosearch.android.CustomerAdapter;
import com.roosearch.android.R;
import com.roosearch.android.domain.Customer;
import com.roosearch.android.domain.Survey;
import com.roosearch.android.domain.SurveySummary;
import com.roosearch.android.task.AsyncTaskCompleteListener;
import com.roosearch.android.task.FindSurveyTask;

public class JoeySelection extends Activity {

    public static String TAG = "JoeySelection";
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joeyselection);
    }

    @Override
    protected void onResume() {
        super.onResume();
        customer = getIntent().getExtras().getParcelable("com.roosearch.Customer");
        drawScreenWithCustomer(customer);
    }

    private void drawScreenWithCustomer(Customer c) {
        TextView rooName = (TextView) findViewById(R.id.rooName);
        rooName.setText(c.getCompanyName());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomerAdapter(this, c));
    }

    public void clickedJoey(SurveySummary surveySummary) {
        new FindSurveyTask(this, new FindSurveyTaskCompleteListener()).execute(surveySummary.getId());
    }

    public void facebookClicked(View v){
        Intent i;
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            i = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + customer.getFacebook()));
        } catch (Exception e) {                              // http://www.facebook.com/pages/Jamies-Italian/155660184467188?fref=ts
            i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.facebook.com/profile.php?id=" + customer.getFacebook()));
        }
        startActivity(i);
    }

    public void twitterClicked(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/" + customer.getTwitter()));
        startActivity(i);
    }

    public class FindSurveyTaskCompleteListener implements AsyncTaskCompleteListener<Survey> {
        @Override
        public void onTaskComplete(Survey survey) {
            // do something with the result
            Intent i = new Intent(JoeySelection.this, SurveyRunner.class);
            i.putExtra("com.roosearch.Survey", survey);
            startActivity(i);
        }
    }


}
