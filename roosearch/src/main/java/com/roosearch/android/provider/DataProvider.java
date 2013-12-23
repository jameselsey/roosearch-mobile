package com.roosearch.android.provider;

import com.roosearch.android.domain.Customer;
import com.roosearch.android.domain.Survey;

public interface DataProvider {

    public Customer getCustomerDetails(int customerId);

    public Survey getSurvey(Integer id);

    public void postResults(Survey survey);
}
