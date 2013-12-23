package com.roosearch.android.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    private int id;
    private String text;
    private boolean isSelected;

    public Answer() {
    }

    public Answer(String text) {
        this.text = text;
    }

    /**
     * Constructor used for parceling
     *
     * @param in Parcel input stream
     */
    public Answer(Parcel in) {
        readFromParcel(in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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
        dest.writeString(text);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        text = in.readString();
        isSelected = in.readByte() == 1;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

}
