package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGen {
    public static void main(String args[]) throws Exception{
        Schema schema = new Schema(1,"com.example.jp");
        Entity userBean = schema.addEntity("User");

        userBean.addIdProperty();
        userBean.addStringProperty("userName");
        userBean.addStringProperty("password");
        userBean.addStringProperty("apiKey");

        new DaoGenerator().generateAll(schema,"app/src/main/java-gen");
    }
}
