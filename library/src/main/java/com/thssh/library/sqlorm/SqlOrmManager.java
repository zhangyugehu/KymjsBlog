package com.thssh.library.sqlorm;

import android.content.Context;

import com.thssh.library.sqlorm.exception.SqlOrmExecption;
import com.thssh.library.sqlorm.sqlite.SQLiteDatabaseHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public class SqlOrmManager {
    private static final SqlOrmManager ourInstance = new SqlOrmManager();

    public static SqlOrmManager getInstance() {
        return ourInstance;
    }

    private Context mContext;
    private SQLiteDatabaseHelper mDataHelper;
    private Map<Class, IBaseDao> mDaoMap;

    public SqlOrmManager init(Context context){
        if(this.mContext == null) { this.mContext = context; }
        if(this.mDataHelper == null) { this.mDataHelper = new SQLiteDatabaseHelper(context); }
        return this;
    }
    private SqlOrmManager() {
        this.mDaoMap = new HashMap<>();
    }

    public <E, T extends IBaseDao<E>> T createDao(Class<T> daoClass, Class<E> entityClass) throws SqlOrmExecption {
        try {
            T dao = (T) mDaoMap.get(daoClass);
            if(dao != null){  return dao; }
            dao = daoClass.newInstance();
            dao.init(mDataHelper.getWritableDatabase(), entityClass);
            dao.createTable();
            mDaoMap.put(entityClass, dao);
            return dao;
        } catch (Exception e) {
            throw new SqlOrmExecption(e);
        }
    }

}
