package com.roosearch.android.test;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import com.roosearch.android.activity.LookupSurvey;
import com.roosearch.android.activity.SurveyRunner;


public class LookupSurveyActivityTest extends ActivityInstrumentationTestCase2<LookupSurvey> {

    private Solo solo;

    public LookupSurveyActivityTest() {
        super(LookupSurvey.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testActivity() {
        LookupSurvey activity = getActivity();
        assertNotNull(activity);
    }

    public void testInitialActivity() {
        solo.assertCurrentActivity("Current activity was not correct", LookupSurvey.class);
    }

    public void testExpectedElementsOnScreen() throws Exception {
        assertTrue(solo.searchButton("Get Survey"));
    }

    public void testClickGetSurveyTakesToSurveyRunner() {
        solo.clickOnText("Get Survey");
        solo.waitForActivity("SurveyRunner", 10000);
        solo.assertCurrentActivity("Current activity was not correct", SurveyRunner.class);
    }
}

