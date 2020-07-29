package com.abhijit.allinone.model;

public class TodoTask {

    String task_name;
    String assigned_by;
    String accepted_by;
    String charges;
    String start_date;
    String end_date;
    boolean accept_reject;
    String advancepaied;
    String feedback;


    public TodoTask(){

    }

    public TodoTask(String task_name, String assigned_by, String accepted_by, String charges, String start_date, String end_date, boolean accept_reject, String advancepaied, String feedback) {
        this.task_name = task_name;
        this.assigned_by = assigned_by;
        this.accepted_by = accepted_by;
        this.charges = charges;
        this.start_date = start_date;
        this.end_date = end_date;
        this.accept_reject = accept_reject;
        this.advancepaied = advancepaied;
        this.feedback = feedback;
    }



    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(String assigned_by) {
        this.assigned_by = assigned_by;
    }

    public String getAccepted_by() {
        return accepted_by;
    }

    public void setAccepted_by(String accepted_by) {
        this.accepted_by = accepted_by;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public boolean isAccept_reject() {
        return accept_reject;
    }

    public void setAccept_reject(boolean accept_reject) {
        this.accept_reject = accept_reject;
    }

    public String getAdvancepaied() {
        return advancepaied;
    }

    public void setAdvancepaied(String advancepaied) {
        this.advancepaied = advancepaied;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


}
