package com.roosearch.android.domain.dto;

import com.roosearch.android.domain.Question;
import com.roosearch.android.domain.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyComplete {
    private Integer id;
    private List<ResponseItem> responses = new ArrayList<ResponseItem>();

    private SurveyComplete(){
    }

    public Integer getId() {
        return id;
    }

    public List<ResponseItem> getResponses() {
        return responses;
    }

    public static SurveyComplete from(Survey completedSurvey){
        SurveyComplete surveyComplete = new SurveyComplete();
        surveyComplete.id = completedSurvey.getId();

        for (Question q : completedSurvey.getQuestions()){
            ResponseItem ri = new ResponseItem();
            ri.setQuestionId(q.getId());
            ri.setResponseId(q.getSelectedAnswerId());
            surveyComplete.responses.add(ri);
        }
        return surveyComplete;
    }
}
