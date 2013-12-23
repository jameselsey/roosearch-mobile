package com.roosearch.android.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {
    private Integer id;
    private String text;
    private List<Answer> responses = new ArrayList<Answer>();

    public Question() {
    }

    public Question(String questionText) {
        this.text = questionText;
    }

    /**
     * Constructor used for parceling
     *
     * @param in Parcel input stream
     */
    public Question(Parcel in) {
        readFromParcel(in);
    }


    /*
        Accessor methods
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getResponses() {
        return responses;
    }

    public void setResponses(List<Answer> responses) {
        this.responses = responses;
    }


    /*
        Utility methods
     */

    public String getSelectedAnswer() {
        for (Answer a : responses) {
            if (a.isSelected()) {
                return a.getText();
            }
        }
        return null;
    }

    public int getSelectedAnswerId(){
        for (Answer a : responses) {
            if (a.isSelected()) {
                return a.getId();
            }
        }
        return 0;
    }

    public Answer getAvailableOption(int aIndex) {
        return responses.get(aIndex);
    }

    public void addAvailableOption(Answer option) {
        responses.add(option);
    }

    public void addAvailableOptions(Answer... options) {
        for (Answer s : options) {
            responses.add(s);
        }
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
        dest.writeTypedList(responses);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        text = in.readString();
        in.readTypedList(responses, Answer.CREATOR);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };


}
