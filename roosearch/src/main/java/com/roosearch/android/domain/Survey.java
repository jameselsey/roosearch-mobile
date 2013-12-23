package com.roosearch.android.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Survey implements Parcelable {
    private Integer id;
    private String title;
    private List<Question> questions = new ArrayList<Question>();

    public Survey() {
    }

    /**
     * Constructor used for parceling
     *
     * @param in Parcelable in stream
     */
    public Survey(Parcel in) {
        readFromParcel(in);
    }


    /*
        Accessors
     */

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    /*
        Utility methods
     */
    public int getQuestionCount() {
        return questions.size();
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public void addQuestion(Question question) {
        questions.add(question);
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
        dest.writeTypedList(questions);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        in.readTypedList(questions, Question.CREATOR);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Survey createFromParcel(Parcel in) {
                    return new Survey(in);
                }

                public Survey[] newArray(int size) {
                    return new Survey[size];
                }
            };


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n" + title);
        for (Question q : questions) {
            sb.append("\n" + q.getText());
            for (Answer a : q.getResponses()) {
                if (a.isSelected()) {
                    sb.append("\n" + a.getText());
                }
            }
        }
        return sb.toString();
    }
}

