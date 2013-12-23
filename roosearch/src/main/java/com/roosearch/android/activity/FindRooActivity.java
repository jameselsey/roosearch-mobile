package com.roosearch.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.roosearch.android.AppContext;
import com.roosearch.android.CustomerAdapter;
import com.roosearch.android.R;
import com.roosearch.android.domain.Customer;
import com.roosearch.android.domain.Survey;
import com.roosearch.android.domain.SurveySummary;
import com.roosearch.android.provider.DataProvider;
import com.roosearch.android.provider.rest.RestDataProvider;
import com.roosearch.android.provider.stub.StubbedDataProvider;
import com.roosearch.android.task.AsyncTaskCompleteListener;
import com.roosearch.android.task.FindRooTask;
import com.roosearch.android.task.FindSurveyTask;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class FindRooActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findroo);
    }

    public void findRoo(View v) {
        EditText et = (EditText) findViewById(R.id.findRooById);
        performRooLookup(et.getText().toString());
    }

    public void facebookClicked(View v) {
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
    }

    public void twitterClicked(View v) {
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
    }


    public void scanBarCode(View v) {
        final boolean scanAvailable = isIntentAvailable(this,
                "com.google.zxing.client.android.SCAN");
        if (!scanAvailable){
            Toast.makeText(this, "You need to install the ZXing barcode app to use this feature", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                performRooLookup(contents);
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }

    private void performRooLookup(String rooId) {
        if (StringUtils.isBlank(rooId)) {
            Toast.makeText(this, "Please enter a valid customer id", Toast.LENGTH_SHORT).show();
            return;
        }

        Integer customerId;
        try {
            customerId = Integer.parseInt(rooId);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Customer id needs to be numeric", Toast.LENGTH_SHORT).show();
            return;
        }
        new FindRooTask(this, new FindRooTaskCompleteListener()).execute(customerId);
    }

    private static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.stubmode:
                AppContext.getInstance().setStubbedMode();
                Toast.makeText(this, "Switched to Stubbed mode", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.livemode:
                AppContext.getInstance().setLiveMode();
                Toast.makeText(this, "Switched to Live mode", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class FindRooTaskCompleteListener implements AsyncTaskCompleteListener<Customer> {
        @Override
        public void onTaskComplete(Customer customer) {
            Intent i = new Intent(FindRooActivity.this, JoeySelection.class);
            i.putExtra("com.roosearch.Customer", customer);
            startActivity(i);
        }
    }


}
