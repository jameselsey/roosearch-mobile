package com.roosearch.android.domain;

import android.os.Parcel;
import android.os.Parcelable;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Customer implements Parcelable {

    @JsonProperty("company_name")
    private String companyName;
    private String twitter;
    private String facebook;
    private List<SurveySummary> surveys = new ArrayList<SurveySummary>();

    public Customer() {
    }

    /**
     * Constructor used for parceling
     *
     * @param in Parcelable in stream
     */
    public Customer(Parcel in) {
        readFromParcel(in);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public List<SurveySummary> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<SurveySummary> surveys) {
        this.surveys = surveys;
    }

    /*
        Parcelable methods
     */

    //@Override
    public int describeContents() {
        return 0;
    }

    //@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyName);
        dest.writeString(StringUtils.trimToEmpty(twitter));
        dest.writeString(StringUtils.trimToEmpty(facebook));
        dest.writeTypedList(surveys);
    }

    private void readFromParcel(Parcel in) {
        companyName= in.readString();
        twitter= in.readString();
        facebook= in.readString();
        in.readTypedList(surveys, SurveySummary.CREATOR);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Customer createFromParcel(Parcel in) {
                    return new Customer(in);
                }

                public Customer[] newArray(int size) {
                    return new Customer[size];
                }
            };
}
