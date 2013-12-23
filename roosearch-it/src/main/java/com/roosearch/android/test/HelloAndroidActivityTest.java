package com.roosearch.android.test;

import android.test.ActivityInstrumentationTestCase2;
import com.roosearch.android.activity.LookupSurvey;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<LookupSurvey> {

    public HelloAndroidActivityTest() {
        super(LookupSurvey.class);
    }

    public void testActivity() {
        LookupSurvey activity = getActivity();
        assertNotNull(activity);
    }
}

