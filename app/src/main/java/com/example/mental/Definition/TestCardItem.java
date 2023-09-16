package com.example.mental.Definition;

public class TestCardItem {
    private int imageResId;
    private String question;
    private String choiceA;
    private String choiceB;
    private String choiceC;

    public TestCardItem(int imageResId, String question, String choiceA, String choiceB, String choiceC) {
        this.imageResId = imageResId;
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }
}
