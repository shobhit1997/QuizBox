package com.example.dell.quizbox;

import java.util.ArrayList;

/**
 * Created by DELL on 6/21/2017.
 */

public class MyQuiz {



        private String userName;
        private String quizName;
        private ArrayList<Question> questions=new ArrayList<>();

        public MyQuiz() {
        }

        public MyQuiz(String userName,String quizName,ArrayList<Question> questions) {
            this.userName = userName;
            this.quizName = quizName;
            this.questions=questions;


        }

        public void addQuestion(Question question) {
            questions.add(question);
        }

        public Question getQuestion(int index) {
            return questions.get(index);
        }
        public void setUsername(String userName)
        {
            this.userName=userName;
        }
        public void setQuizName(String quizName){
            this.quizName=quizName;
        }
        public void setQuestions(ArrayList<Question> questions){
            this.questions=questions;
        }
        public String getUsername()
        {
            return userName;
        }
        public String getQuizName()
        {
            return quizName;
        }
        public ArrayList<Question> getQuestions()
        {
            return questions;
        }

}
