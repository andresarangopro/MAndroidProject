package com.lide.app.bean.DBBean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

@Table(name = "User")
public class User extends Model implements Serializable {
    @Column(name = "warehouseCode")
    String warehouseCode;
    @Column(name = "userName")
    String userName;
    @Column(name = "password")
    String password;
    @Column(name = "apiKey")
    String apiKey;
    @Column(name = "saveState")
    int saveState;

    public int getSaveState() {
        return saveState;
    }

    public void setSaveState(int saveState) {
        this.saveState = saveState;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "warehouseCode='" + warehouseCode + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", saveState=" + saveState +
                '}';
    }
}
