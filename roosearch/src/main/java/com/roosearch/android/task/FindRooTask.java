package com.roosearch.android.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roosearch.android.AppContext;
import com.roosearch.android.domain.Customer;
import de.akquinet.android.androlog.Log;

public class FindRooTask extends AsyncTask<Integer, Integer, Customer> {

    private static final String TAG = "FindRooTask";

    private Context context;
    private AsyncTaskCompleteListener<Customer> listener;
    private ProgressDialog dialog;

    public FindRooTask(Context ctx, AsyncTaskCompleteListener<Customer> listener) {
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
    protected Customer doInBackground(Integer... args) {
        int customerId = args[0];
        Log.d(TAG, "Searching for customer using id " + customerId);
        return AppContext.getInstance().getDataProvider().getCustomerDetails(customerId);
    }

    @Override
    protected void onPostExecute(Customer customer) {
        super.onPostExecute(customer);
        dialog.dismiss();
        Log.d(TAG, "Found customer");
        listener.onTaskComplete(customer);
    }
}