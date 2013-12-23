package com.roosearch.android.activity;

import android.widget.Button;
import com.roosearch.android.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import static org.fest.assertions.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
public class LookupSurveyTest {

    private LookupSurvey activity;
    private Button survey;
    private Button openBarcodeScannerButton;

    @Before
    public void setUp() throws Exception {
        activity = new LookupSurvey();
        activity.onCreate(null);
        survey = (Button) activity.findViewById(R.id.submitSurveyLookup);
        openBarcodeScannerButton = (Button) activity.findViewById(R.id.openBarcodeScanner);
    }

    @Test
    public void testSomething(){
//         assertEquals(1,2);
    }

    @Test
    public void uiElementsVisible(){
        assertThat(survey.getText().toString()).isEqualTo("Get Survey");
//        assertThat(openBarcodeScannerButton.getText().toString()).isEqualTo("Open QR Scanner");
    }
}
