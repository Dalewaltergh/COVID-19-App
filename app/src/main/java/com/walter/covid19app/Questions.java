package com.walter.covid19app;

public class Questions {

    private String question, answer;
    private boolean expandable;

    public Questions(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.expandable = false;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswers() {
        return answer;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean isExpandable) {
        expandable = isExpandable;
    }
}
