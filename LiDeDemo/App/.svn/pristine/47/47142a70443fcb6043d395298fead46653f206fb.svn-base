package com.lide.app.bean.DBBean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

@Table(name = "CheckTask")
public class CheckTask extends Model implements Serializable {
    @Column(name = "name")
    String name;
    @Column(name = "user")
    User user;
    @Column(name = "number")
    int number;
    @Column(name = "complete")
    int complete;

    public CheckTask(String name, User user, int number, int complete) {
        this.name = name;
        this.user = user;
        this.number = number;
        this.complete = complete;
        this.save();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "CheckTask{" +
                "name='" + name + '\'' +
                ", user=" + user +
                ", number=" + number +
                ", complete=" + complete +
                '}';
    }
}
