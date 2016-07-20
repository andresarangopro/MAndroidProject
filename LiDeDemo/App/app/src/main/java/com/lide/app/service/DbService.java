package com.lide.app.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lide.app.MApplication;
import com.lubin.bean.CheckTask;
import com.lubin.bean.EPC;
import com.lubin.bean.User;
import com.lubin.dao.CheckTaskDao;
import com.lubin.dao.DaoSession;
import com.lubin.dao.EPCDao;
import com.lubin.dao.UserDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 用户操作类
 * Created by cg on 2015/12/29.
 */
public class DbService {
    private static final String TAG = DbService.class.getSimpleName();
    private static DbService instance;
    private static Context appContext;
    private DaoSession mDaoSession;
    private UserDao userDao;
    private CheckTaskDao checkTaskDao;
    private EPCDao epcDao;

    private DbService() {
    }

    /**
     * 采用单例模式
     *
     * @param context 上下文
     * @return dbservice
     */
    public static DbService getInstance(Context context) {
        if (instance == null) {
            instance = new DbService();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = MApplication.getDaoSession(context);
            instance.userDao = instance.mDaoSession.getUserDao();
            instance.checkTaskDao = instance.mDaoSession.getCheckTaskDao();
            instance.epcDao = instance.mDaoSession.getEPCDao();
        }
        return instance;
    }

    /**
     * 根据用户id,取出用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    public User loadNote(long id) {
        if (!TextUtils.isEmpty(id + "")) {
            return userDao.load(id);
        }
        return null;
    }

    /**
     * 取出所有数据
     *
     * @return 所有数据信息
     */
    public List<User> loadAllNote() {
        return userDao.loadAll();
    }

    /**
     * 生成按id倒排序的列表
     *
     * @return 倒排数据
     */
    public List<User> loadAllNoteByOrder() {
        return userDao.queryBuilder().orderDesc(UserDao.Properties.Id).list();
    }

    /**
     * 根据查询条件,返回数据列表
     *
     * @param where  条件
     * @param params 参数
     * @return 数据列表
     */
    public List<User> queryNote(String where, String... params) {
        return userDao.queryRaw(where, params);
    }


    /**
     * 根据用户信息,插件或修改信息
     *
     * @param user 用户信息
     * @return 插件或修改的用户id
     */
    public long saveNote(User user) {
        return userDao.insertOrReplace(user);
    }


    /**
     * 批量插入或修改用户信息
     *
     * @param list 用户信息列表
     */
    public void saveNoteLists(final List<User> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        userDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    User user = list.get(i);
                    userDao.insertOrReplace(user);
                }
            }
        });

    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote() {
        userDao.deleteAll();
    }

    /**
     * 根据id,删除数据
     *
     * @param id 用户id
     */
    public void deleteNote(long id) {
        userDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }

    /**
     * 根据用户类,删除信息
     *
     * @param user 用户信息类
     */
    public void deleteNote(User user) {
        userDao.delete(user);
    }

    /**********************任务类别*********************************/
    /**
     * 根据任务类别id,取出其类别下所有的信息
     *
     * @param checkTaskId 类别id
     * @return 信息列表
     */
    public List<CheckTask> getCheckTaskBycheckTaskId(long checkTaskId) {
        return userDao.load(checkTaskId).getTasks();
    }

    /**
     * 添加或修改
     *
     * @param checkTask 信息
     * @return 添加或修改的id
     */
    public long saveCheckTask(CheckTask checkTask) {
        return checkTaskDao.insertOrReplace(checkTask);
    }

    /**
     * @return 返回所有盘点任务
     */
    public List<CheckTask> getAllCheckTask() {
        return checkTaskDao.loadAll();
    }

    /**
     * 根据查询条件,返回数据列表
     * @param param 参数
     * @return 数据列表
     */
    public List<CheckTask> queryCheckTask (String param) {
        QueryBuilder<CheckTask> qb = checkTaskDao.queryBuilder();
        qb.where(CheckTaskDao.Properties.Name.eq(param));
        return qb.list();
    }

    /**
     * 根据id,删除数据
     *
     * @param id 用户id
     */
    public void deleteCheckTask(long id) {
        checkTaskDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }

    /**********************EPC类别*********************************/
    /**
     * 根据EPC类别id,取出其类别下所有的信息
     *
     * @param checkTaskId 类别id
     * @return 信息列表
     */
    public List<EPC> getEPCsBycheckTaskId(long checkTaskId) {
        return checkTaskDao.load(checkTaskId).getEPCs();
    }

    /**
     * 添加或修改
     *
     * @param epc 标签
     * @return 添加或修改的epc
     */
    public long saveEPC(EPC epc) {
        return epcDao.insertOrReplace(epc);
    }

    /**
     * @return 返回所有EPC
     */
    public List<EPC> getAllEPC() {
        return epcDao.loadAll();
    }

    /**
     * 根据查询条件,返回数据列表
     * @param param 参数
     * @return 数据列表
     */
    public List<EPC> queryEPC(String param) {
        QueryBuilder<EPC> qb = epcDao.queryBuilder();
        qb.where(EPCDao.Properties.Epc.eq(param));
        return qb.list();
    }

    /**
     * 根据id,删除数据
     *
     * @param id 用户id
     */
    public void deleteEPC(long id) {
        epcDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }
}