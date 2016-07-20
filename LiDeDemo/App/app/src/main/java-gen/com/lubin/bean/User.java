package com.lubin.bean;

import java.util.List;
import com.lubin.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.lubin.dao.CheckTaskDao;
import com.lubin.dao.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USER".
 */
public class User implements java.io.Serializable {

    private Long id;
    private String userName;
    private String password;
    private String apiKey;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient UserDao myDao;

    private List<CheckTask> tasks;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String userName, String password, String apiKey) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.apiKey = apiKey;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<CheckTask> getTasks() {
        if (tasks == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CheckTaskDao targetDao = daoSession.getCheckTaskDao();
            List<CheckTask> tasksNew = targetDao._queryUser_Tasks(id);
            synchronized (this) {
                if(tasks == null) {
                    tasks = tasksNew;
                }
            }
        }
        return tasks;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetTasks() {
        tasks = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
