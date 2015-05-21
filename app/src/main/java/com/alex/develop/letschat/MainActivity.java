package com.alex.develop.letschat;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.alex.develop.entity.Group;
import com.alex.develop.entity.User;
import com.alex.develop.fragment.ContactFragment;
import com.alex.develop.fragment.LoginFragment;
import com.alex.develop.fragment.RecentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * App入口
 * @author Created by alex 2014/10/23
 */
public class MainActivity extends BaseActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		changeThemeByTime();
        setContentView(R.layout.main_activity);

        initialize();
        go2LoginView();
	}

    //登陆界面
    public void go2LoginView() {
        FragmentTransaction transaction = getTransaction();
        transaction.add(LAYOUT_CONTENT_ID, new LoginFragment());
        transaction.commit();
    }

    //近期联系人界面
    public void go2RecentView() {
        FragmentTransaction transaction = getTransaction();
        transaction.replace(LAYOUT_CONTENT_ID, new RecentFragment());
        transaction.commit();
    }

    //联系人列表
    public void go2ContactView() {
        FragmentTransaction transaction = getTransaction();
        transaction.replace(LAYOUT_CONTENT_ID, new ContactFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }


    //聊天室
    public void go2ChatView() {

    }

    public User getUsr(String usrIdOrName) {

        if(null != usr) {
            return usr;
        }

        String usrId = "";
        String usrName = "AlexTest";
        String usrPassword = "123456";

        usr = new User(usrId, usrName, usrPassword);

        List<Group> groups = new ArrayList();
        for(int i=0; i<5; ++i) {
            String groupId = i + "";
            String groupName = "Group " + i;
            List<User> usrs = new ArrayList();
            for(int j=0; j<3+i; ++j) {
                String uId = i + "-" + j;
                String uName = "User-"+uId;
                usrs.add(new User(uId, uName, ""));
            }
            groups.add(new Group(groupId, groupName, usrs));
        }
        usr.setGroups(groups);

        List<User> recent = new ArrayList();
        for(int i=0; i<50; ++i) {
            String uId = i+"";
            String uName = "User-"+uId;
            recent.add(new User(uId, uName, ""));
        }
        usr.setRecents(recent);

        return usr;
    }

    private void initialize() {


    }

    private final int LAYOUT_CONTENT_ID = R.id.content;
    private User usr;// 当前用户
}