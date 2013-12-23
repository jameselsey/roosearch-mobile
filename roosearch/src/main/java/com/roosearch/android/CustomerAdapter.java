package com.roosearch.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.roosearch.android.activity.FindRooActivity;
import com.roosearch.android.activity.JoeySelection;
import com.roosearch.android.domain.Customer;
import com.roosearch.android.domain.SurveySummary;

public class CustomerAdapter extends ArrayAdapter<SurveySummary> {

    private final Context context;
    private Customer customer;

    public CustomerAdapter(Context context, Customer customer) {
        super(context, R.layout.joeyselection, customer.getSurveys());
        this.context = context;
        this.customer = customer;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.surveyName);
        textView.setText(customer.getSurveys().get(position).getTitle());

        rowView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((JoeySelection)context).clickedJoey(customer.getSurveys().get(position));
            }
        });
        return rowView;
    }
}
