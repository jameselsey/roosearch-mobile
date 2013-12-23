package com.roosearch.android.provider.stub;

import com.roosearch.android.domain.*;
import com.roosearch.android.provider.DataProvider;

import java.util.ArrayList;
import java.util.List;


public class StubbedDataProvider implements DataProvider {

    public Customer getCustomerDetails(int customerId) {
        Customer customer = new Customer();
        customer.setCompanyName("COSMOs Chinese Buffet");
        customer.setFacebook("Facebook_12345");
        customer.setTwitter("COSMOS_2013");

        List<SurveySummary> surveySummaryList = new ArrayList<SurveySummary>(){{
                add(new SurveySummary(1, "COSMOs feedback"));
                add(new SurveySummary(2, "Jamies feedback"));
                add(new SurveySummary(3, "Hotel questionaire"));
        }};
        customer.setSurveys(surveySummaryList);

        return customer;
    }

    public Survey getSurvey(Integer id) {

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

    public void postResults(Survey survey) {
        // do nothing
    }
}
