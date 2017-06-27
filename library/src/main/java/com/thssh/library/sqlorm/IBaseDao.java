package com.thssh.library.sqlorm;

import android.database.sqlite.SQLiteDatabase;

import com.thssh.library.sqlorm.exception.SqlOrmExecption;

import java.util.List;

/**
 * 接口不关心数据表的创建，交由{@link com.thssh.library.sqlorm.impl.AbsDBDao} 抽象类处理
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public interface IBaseDao<T> {

    /**
     * 初始化
     * @param database
     * @param entityClass
     */
    void init(SQLiteDatabase database, Class entityClass);

    /**
     * 插入数据
     * @param entity
     * @return
     * @throws SqlOrmExecption
     */
    long insert(T entity) throws SqlOrmExecption;

    /**
     * 删除某条数据
     * @param where
     * @return
     * @throws SqlOrmExecption
     */
    int delete(T where) throws SqlOrmExecption;

    /**
     * 根据条件删除数据
     * @param entity
     * @param bys
     * @return
     * @throws SqlOrmExecption
     */
    int delete(T entity, String... bys) throws SqlOrmExecption;

    /**
     * 清空数据
     * @return
     * @throws SqlOrmExecption
     */
    int clear() throws SqlOrmExecption;

    /**
     * 更新某条数据
     * @param entity
     * @param where
     * @return
     * @throws SqlOrmExecption
     */
    int update(T entity, T where) throws SqlOrmExecption;

    /**
     * 更新某些符合条件的数据
     * @param entity
     * @param bys
     * @return
     * @throws SqlOrmExecption
     */
    int update(T entity, String... bys) throws SqlOrmExecption;

    /**
     * 创建表
     * @throws SqlOrmExecption
     */
    void createTable() throws SqlOrmExecption;

    /**
     * 更新表
     * @throws SqlOrmExecption
     */
    void upgradeTable() throws  SqlOrmExecption;

    /**
     * 删除表
     * @throws SqlOrmExecption
     */
    void dropTable() throws SqlOrmExecption;

    /**
     * * 查询数据
     * @param builder
     * @return
     * @throws SqlOrmExecption
     */
    List<T> query(QueryBuilder builder) throws SqlOrmExecption;

    class QueryBuilder<T>{
        public String groupBy;
        public String having;
        public String orderBy;
        public String[] columns;
        public String[] columnArgs;
        public IBaseDao<T> dao;
        public T entity;

        public QueryBuilder groupBy(String groupBy){
            this.groupBy = groupBy;
            return this;
        }
        public QueryBuilder having(String having){
            this.having = having;
            return this;
        }
        public QueryBuilder orderBy(String orderBy){
            this.orderBy = orderBy;
            return this;
        }
        public QueryBuilder columns(String... columns){
            this.columns = columns;
            return this;
        }
        public QueryBuilder columnArgs(String... columnArgs){
            this.columnArgs = columnArgs;
            return this;
        }

        public QueryBuilder entity(T entity){
            this.entity = entity;
            return this;
        }

        public List<T> query() throws SqlOrmExecption {
            return dao.query(this);
        }

        public QueryBuilder dao(IBaseDao<T> dao) {
            this.dao = dao;
            return this;
        }
    }
}
