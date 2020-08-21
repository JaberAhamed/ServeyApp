package com.example.serveyappfor.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public  class SaveServey {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    public  String check;
    public  String text;
    public  String drop;
    public String mult;
    public  String num;

    public SaveServey(int id, String check, String text, String drop, String mult, String num) {
        this.id = id;
        this.check = check;
        this.text = text;
        this.drop = drop;
        this.mult = mult;
        this.num = num;
    }

    @Ignore
    public SaveServey(String check, String text, String drop, String mult, String num) {
        this.check = check;
        this.text = text;
        this.drop = drop;
        this.mult = mult;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getMult() {
        return mult;
    }

    public void setMult(String mult) {
        this.mult = mult;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
