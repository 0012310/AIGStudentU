package com.aig.advanceinnovationgroup.model;

/**
 * Created by admin on 4/7/2018.
 */

public class ComplaintDetsils {

    String complaint_type;
    String complaint_date;
    String complaint_id;
    String reply_id;
    String message;
    String status1;
    String allocated_to;
    String assign_to;
    String assign_date;
    String reply_date;
    String problem_statement;
    String rca;
    String recommended_action;
    String status_msg;

    public ComplaintDetsils(String complaint_type, String complaint_date) {
        this.complaint_type = complaint_type;
        this.complaint_date = complaint_date;
    }

    public ComplaintDetsils(String complaint_type, String complaint_id, String reply_id, String message, String status1, String allocated_to, String assign_to, String assign_date, String reply_date, String problem_statement, String rca, String recommended_action, String status_msg) {
        this.complaint_type = complaint_type;
        this.complaint_id = complaint_id;
        this.reply_id = reply_id;
        this.message = message;
        this.status1 = status1;
        this.allocated_to = allocated_to;
        this.assign_to = assign_to;
        this.assign_date = assign_date;
        this.reply_date = reply_date;
        this.problem_statement = problem_statement;
        this.rca = rca;
        this.recommended_action = recommended_action;
        this.status_msg = status_msg;

    }

    public String getComplaint_type() {
        return complaint_type;
    }

    public void setComplaint_type(String complaint_type) {
        this.complaint_type = complaint_type;
    }

    public String getComplaint_date() {
        return complaint_date;
    }

    public void setComplaint_date(String complaint_date) {
        this.complaint_date = complaint_date;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getAllocated_to() {
        return allocated_to;
    }

    public void setAllocated_to(String allocated_to) {
        this.allocated_to = allocated_to;
    }

    public String getAssign_to() {
        return assign_to;
    }

    public void setAssign_to(String assign_to) {
        this.assign_to = assign_to;
    }

    public String getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public String getReply_date() {
        return reply_date;
    }

    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }

    public String getProblem_statement() {
        return problem_statement;
    }

    public void setProblem_statement(String problem_statement) {
        this.problem_statement = problem_statement;
    }

    public String getRca() {
        return rca;
    }

    public void setRca(String rca) {
        this.rca = rca;
    }

    public String getRecommended_action() {
        return recommended_action;
    }

    public void setRecommended_action(String recommended_action) {
        this.recommended_action = recommended_action;
    }

    public String getStatus_msg() {
        return status_msg;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }
}
