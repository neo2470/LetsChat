package com.alex.develop.entity;

import java.util.List;

/**
 * Created by alex on 15-5-20.
 */
public class User extends BaseObject {

    public User(String usrId, String usrName, String usrPassword) {
        this.usrId = usrId;
        this.usrName = usrName;
        this.usrPassword = usrPassword;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<User> getRecents() {
        return recents;
    }

    public void setRecents(List<User> recents) {
        this.recents = recents;
    }

    private String usrId;// 用户ID
    private String usrName;// 用户昵称
    private String usrPassword;// 用户密码(加密)
    private List<Group> groups;// 用户分组
    private List<User> recents;// 用户的近期联系人
}
