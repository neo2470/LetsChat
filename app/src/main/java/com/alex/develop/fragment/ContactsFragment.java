package com.alex.develop.fragment;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.alex.develop.entity.User;
import com.alex.develop.letschat.R;

/**
 * Created by alex on 15-5-20.
 */
public class ContactsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final User usr = act.getUsr("");

        ExpandableListAdapter expandableAdapter = new ExpandableListAdapter() {

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getGroupCount() {
                return usr.getGroups().size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return usr.getGroups().get(groupPosition).getContacts().size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return null;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return 0;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                if(null == convertView) {
                    LayoutInflater inflater = LayoutInflater.from(act);
                    convertView = inflater.inflate(R.layout.contact_group, null);
                }

                TextView groupName = (TextView) convertView.findViewById(R.id.groupName);
                String gName = usr.getGroups().get(groupPosition).getGroupName();
                groupName.setText(gName);
                return convertView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                if(null == convertView) {
                    LayoutInflater inflater = LayoutInflater.from(act);
                    convertView = inflater.inflate(R.layout.contact_detail, null);
                }

                TextView usrName = (TextView) convertView.findViewById(R.id.usrName);
                String uName = usr.getGroups().get(groupPosition).getContacts().get(childPosition).getUsrName();
                usrName.setText(uName);
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int groupPosition) {

            }

            @Override
            public void onGroupCollapsed(int groupPosition) {

            }

            @Override
            public long getCombinedChildId(long groupId, long childId) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long groupId) {
                return 0;
            }
        };

        ExpandableListView expandableView = (ExpandableListView) act.findViewById(R.id.contactsList);
        expandableView.setAdapter(expandableAdapter);
    }
}
