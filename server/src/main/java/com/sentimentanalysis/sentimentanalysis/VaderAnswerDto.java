package com.sentimentanalysis.sentimentanalysis;

import java.util.List;

public class VaderAnswerDto {
    List answer;

    public VaderAnswerDto(){

    }
    public VaderAnswerDto(List answer) {
        this.answer = answer;
    }

    public List getAnswer() {
        return answer;
    }

    public void setAnswer(List answer) {
        this.answer = answer;
    }
}
