package com.alex.develop.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alex.develop.entity.User;
import com.alex.develop.letschat.R;

/**
 * Created by alex on 15-5-20.
 * 用户登录界面处理
 */
public class LoginFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences sharedPreferences = act.getSharedPreferences(act.getPackageName(), Context.MODE_PRIVATE);
        String lastUsrName = sharedPreferences.getString(act.getString(R.string.key_login_user), "");

        ImageView usrAvator = (ImageView) act.findViewById(R.id.usrAvator);
        final EditText usrName = (EditText) act.findViewById(R.id.usrName);
        usrName.setText(lastUsrName);
        usrName.setSelection(lastUsrName.length());

        final EditText usrPassword = (EditText) act.findViewById(R.id.usrPassword);
        Button loginBtn = (Button) act.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usrNm = usrName.getText().toString().trim();
                String usrPwd = usrPassword.getText().toString().trim();

                User usr = act.getUsr(usrNm);
                if(usrNm.equals(usr.getUsrName()) && usrPwd.equals(usr.getUsrPassword())) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(act.getString(R.string.key_login_user), usrNm);
                    editor.commit();

                    act.go2RecentView();
                } else {
                    Toast.makeText(act, act.getString(R.string.login_failure), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
