package com.example.dell.quizbox;

import java.util.ArrayList;

/**
 * Created by DELL on 7/2/2017.
 */

public class User {

    private String userid;
    private String username;
    private int score;


    public User() {
    }

    public User(String userid,String username,int score) {
        this.username = username;
        this.userid = userid;
        this.score=score;


    }




    public void setUsername(String username)
    {
        this.username=username;
    }
    public void setUserid(String userid){
        this.userid=userid;
    }
    public void setScore(int score){
        this.score=score;
    }
    public String getUsername()
    {
        return username;
    }
    public String getUserid()
    {
        return userid;
    }
    public int getScore()
    {
        return score;
    }

}
