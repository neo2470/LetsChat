package com.alex.develop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alex.develop.entity.User;
import com.alex.develop.letschat.R;

import java.util.List;

/**
 * Created by alex on 15-5-21.
 * 近期联系人界面
 */
public class RecentFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recent_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView recentList = (ListView) act.findViewById(R.id.recentList);

        RecentAdapter recentAdapter = new RecentAdapter();
        recentList.setAdapter(recentAdapter);
    }

    private class RecentAdapter extends BaseAdapter {

        public RecentAdapter() {
            recents = act.getUsr("").getRecents();
        }

        @Override
        public int getCount() {
            return recents.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(null == convertView) {
                LayoutInflater inflater = LayoutInflater.from(act);
                convertView = inflater.inflate(R.layout.recent_detail, null);
            }

            User usr = recents.get(position);
            TextView usrName = (TextView) convertView.findViewById(R.id.usrName);
            usrName.setText(usr.getUsrName());

            return convertView;
        }

        private List<User> recents;
    }
}
