package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class DaoGen {
    public static void main(String args[]) throws Exception{
        Schema schema = new Schema(1,"com.lubin.bean");
        schema.setDefaultJavaPackageDao("com.lubin.dao");
        Entity userBean = schema.addEntity("User");

        userBean.addIdProperty();
        userBean.implementsSerializable();
        userBean.addStringProperty("userName");
        userBean.addStringProperty("password");
        userBean.addStringProperty("apiKey");

        Entity checkTask = schema.addEntity("CheckTask");
        checkTask.implementsSerializable();
        checkTask.addIdProperty();
        checkTask.addStringProperty("name");
        checkTask.addBooleanProperty("compete");
        checkTask.addIntProperty("number");
        checkTask.addDateProperty("date");
        Property userId = checkTask.addLongProperty("userId").getProperty();

        //这里是重点，我们为这两个表建立1:n的关系，并设置关联字段。
        checkTask.addToOne(userBean, userId);
        ToMany addToMany = userBean.addToMany(checkTask,userId);
        addToMany.setName("tasks");

        Entity EPC= schema.addEntity("EPC");
        EPC.implementsInterface();
        EPC.addIdProperty();
        EPC.addStringProperty("tid");
        EPC.addStringProperty("epc");
        EPC.addBooleanProperty("isUploading");
        Property checkTaskId = EPC.addLongProperty("checkTaskId").getProperty();

        EPC.addToOne(checkTask,checkTaskId);
        ToMany toMany = checkTask.addToMany(EPC, checkTaskId);
        toMany.setName("EPCs");

        new DaoGenerator().generateAll(schema,"app/src/main/java-gen");
    }
}
