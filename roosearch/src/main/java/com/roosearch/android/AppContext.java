package com.roosearch.android;


import com.roosearch.android.provider.DataProvider;
import com.roosearch.android.provider.rest.RestDataProvider;
import com.roosearch.android.provider.stub.StubbedDataProvider;

public class AppContext {

    private static AppContext appContext;
    private DataProvider dataProvider;

    private AppContext() {

    }

    public static AppContext getInstance() {
        if (appContext == null) {
            appContext = new AppContext();

        }
        return appContext;
    }

    public DataProvider getDataProvider() {
        // default it to live
        if (dataProvider == null) {
            dataProvider = new RestDataProvider();
        }
        return dataProvider;
    }

    public void setLiveMode() {
        dataProvider = new RestDataProvider();
    }

    public void setStubbedMode() {
        dataProvider = new StubbedDataProvider();
    }
}
