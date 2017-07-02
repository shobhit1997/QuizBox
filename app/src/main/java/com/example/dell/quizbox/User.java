package com.example.dell.quizbox;

import java.util.ArrayList;

/**
 * Created by DELL on 7/2/2017.
 */

public class User {

    private String userId;
    private String userName;
    private int score;


    public User() {
    }

    public User(String userId,String userName,int score) {
        this.userName = userName;
        this.userId = userId;
        this.score=score;


    }




    public void setUsername(String userName)
    {
        this.userName=userName;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public void setScore(int score){
        this.score=score;
    }
    public String getUsername()
    {
        return userName;
    }
    public String getUserId()
    {
        return userId;
    }
    public int getScore()
    {
        return score;
    }

}
