package com.example.dell.quizbox;

/**
 * Created by DELL on 6/21/2017.
 */

public class Question {


    private String question;
    private String answer;
    private String wrong_ans1;
    private String wrong_ans2;
    private String wrong_ans3;

    public Question() {
    }

    public Question(String question, String answer, String wrong_ans1 , String wrong_ans2 , String wrong_ans3) {
        this.question = question;
        this.answer = answer;
        this.wrong_ans1 = wrong_ans1;
        this.wrong_ans2 = wrong_ans2;
        this.wrong_ans3 = wrong_ans3;
    }

    public String getQuestion() {
        return question;
    }

    public void setText(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getWrong_ans1() {
        return wrong_ans1;
    }

    public void setWrong_ans1(String wrong_ans1) {
        this.wrong_ans1 = wrong_ans1;
    }

    public String getWrong_ans2() {
        return wrong_ans2;
    }

    public void setWrong_ans2(String wrong_ans2) {
        this.wrong_ans2 = wrong_ans2;
    }

    public String getWrong_ans3() {
        return wrong_ans3;
    }

    public void setWrong_ans3(String wrong_ans3) {
        this.wrong_ans3 = wrong_ans3;
    }
}
