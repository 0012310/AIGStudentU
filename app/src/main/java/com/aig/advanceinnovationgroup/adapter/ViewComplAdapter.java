package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.ComplaintDetsils;

import java.util.List;

/**
 * Created by admin on 4/6/2018.
 */

public class ViewComplAdapter extends BaseExpandableListAdapter {

    Context context;
    private List<ComplaintDetsils> headList;
    private List<ComplaintDetsils> childList;

    public ViewComplAdapter(Context context, List<ComplaintDetsils> headList, List<ComplaintDetsils> childList){
        this.context = context;
        this.headList = headList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return headList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.childList.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return (long) groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (long) childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ComplaintDetsils headerTitle = (ComplaintDetsils) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.complaint_title_single_row, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.tv_title);
        TextView dateTV = (TextView) convertView.findViewById(R.id.tv_date);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getComplaint_type());
        dateTV.setText(headerTitle.getComplaint_date());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ComplaintDetsils childText = (ComplaintDetsils) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.complaint_child_single_row, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.tv_complaint_no);
        TextView complaintTypeTV = (TextView) convertView
                .findViewById(R.id.tv_complaint_type);
        TextView complaintTV = (TextView) convertView
                .findViewById(R.id.tv_complaint);
        TextView tv_process = (TextView) convertView.findViewById(R.id.tv_process);

        txtListChild.setText(childText.getComplaint_id());
        complaintTypeTV.setText(childText.getComplaint_type());
        complaintTV.setText(childText.getMessage());

        tv_process.setText(childText.getStatus_msg());


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
