package com.lide.app.bean.DBBean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

@Table(name = "EPC")
public class EPC extends Model implements Serializable {
    @Column(name = "EPC")
    String EPC;
    @Column(name = "task")
    CheckTask task;
    @Column(name = "distance")
    String distance;

    public String getEPC() {
        return EPC;
    }

    public void setEPC(String EPC) {
        this.EPC = EPC;
    }

    public CheckTask getTask() {
        return task;
    }

    public void setTask(CheckTask task) {
        this.task = task;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "EPC{" +
                "EPC='" + EPC + '\'' +
                ", task=" + task +
                ", distance=" + distance +
                '}';
    }
}
