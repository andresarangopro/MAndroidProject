package com.lubin.bean;

import com.lubin.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.lubin.dao.CheckTaskDao;
import com.lubin.dao.EPCDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "EPC".
 */
public class EPC {

    private Long id;
    private String tid;
    private String epc;
    private Boolean isUploading;
    private Long checkTaskId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient EPCDao myDao;

    private CheckTask checkTask;
    private Long checkTask__resolvedKey;


    public EPC() {
    }

    public EPC(Long id) {
        this.id = id;
    }

    public EPC(Long id, String tid, String epc, Boolean isUploading, Long checkTaskId) {
        this.id = id;
        this.tid = tid;
        this.epc = epc;
        this.isUploading = isUploading;
        this.checkTaskId = checkTaskId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEPCDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public Boolean getIsUploading() {
        return isUploading;
    }

    public void setIsUploading(Boolean isUploading) {
        this.isUploading = isUploading;
    }

    public Long getCheckTaskId() {
        return checkTaskId;
    }

    public void setCheckTaskId(Long checkTaskId) {
        this.checkTaskId = checkTaskId;
    }

    /** To-one relationship, resolved on first access. */
    public CheckTask getCheckTask() {
        Long __key = this.checkTaskId;
        if (checkTask__resolvedKey == null || !checkTask__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CheckTaskDao targetDao = daoSession.getCheckTaskDao();
            CheckTask checkTaskNew = targetDao.load(__key);
            synchronized (this) {
                checkTask = checkTaskNew;
            	checkTask__resolvedKey = __key;
            }
        }
        return checkTask;
    }

    public void setCheckTask(CheckTask checkTask) {
        synchronized (this) {
            this.checkTask = checkTask;
            checkTaskId = checkTask == null ? null : checkTask.getId();
            checkTask__resolvedKey = checkTaskId;
        }
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
