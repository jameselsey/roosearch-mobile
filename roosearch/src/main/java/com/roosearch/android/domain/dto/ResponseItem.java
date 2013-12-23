package com.roosearch.android.domain.dto;


import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseItem {
    @JsonProperty("question_id")
    private Integer questionId;
    @JsonProperty("response_id")
    private Integer responseId;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }
}
