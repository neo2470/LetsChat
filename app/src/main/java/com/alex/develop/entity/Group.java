package com.alex.develop.entity;

import java.util.List;

/**
 * Created by alex on 15-5-20.
 */
public class Group extends BaseObject {

    public Group(String groupId, String groupName, List<User> contacts) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.contacts = contacts;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public List<User> getContacts() {
        return contacts;
    }

    private String groupId;// 分组Id
    private String groupName;// 分组名称
    private List<User> contacts;// 该分组下的联系人
}
