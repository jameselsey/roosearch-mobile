package com.roosearch.android.domain;


import android.os.Parcel;
import android.os.Parcelable;

public class SurveySummary implements Parcelable {
    private Integer id;
    private String title;

    public SurveySummary() {
    }

    /**
     * Constructor used for parceling
     *
     * @param in Parcel input stream
     */
    public SurveySummary(Parcel in) {
        readFromParcel(in);
    }

    public SurveySummary(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        dest.writeInt(id);
        dest.writeString(title);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SurveySummary createFromParcel(Parcel in) {
            return new SurveySummary(in);
        }

        public SurveySummary[] newArray(int size) {
            return new SurveySummary[size];
        }
    };
}
