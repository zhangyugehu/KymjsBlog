package com.thssh.kymjsblog.bean;

import com.thssh.library.sqlorm.annotation.DBField;
import com.thssh.library.sqlorm.annotation.DBTable;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

@DBTable("tb_user")
public class User {
    @DBField("f_name")
    private String name;
    @DBField("f_age")
    private String age;
    @DBField("f_sex")
    private String sex;

    public User() {
    }

    public User(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
