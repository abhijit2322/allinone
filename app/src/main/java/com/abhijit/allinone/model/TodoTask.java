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
    String task_status;
    String cit_lang;
    String cit_lati;
    String ser_lang;
    String ser_lati;


    public String getCit_lang() {
        return cit_lang;
    }

    public void setCit_lang(String cit_lang) {
        this.cit_lang = cit_lang;
    }

    public String getCit_lati() {
        return cit_lati;
    }

    public void setCit_lati(String cit_lati) {
        this.cit_lati = cit_lati;
    }

    public String getSer_lang() {
        return ser_lang;
    }

    public void setSer_lang(String ser_lang) {
        this.ser_lang = ser_lang;
    }

    public String getSer_lati() {
        return ser_lati;
    }

    public void setSer_lati(String ser_lati) {
        this.ser_lati = ser_lati;
    }




    public TodoTask(String task_name, String assigned_by, String accepted_by, String charges, String start_date, String end_date, boolean accept_reject, String advancepaied, String feedback, String task_status, String cit_lang, String cit_lati, String ser_lang, String ser_lati) {
        this.task_name = task_name;
        this.assigned_by = assigned_by;
        this.accepted_by = accepted_by;
        this.charges = charges;
        this.start_date = start_date;
        this.end_date = end_date;
        this.accept_reject = accept_reject;
        this.advancepaied = advancepaied;
        this.feedback = feedback;
        this.task_status = task_status;
        this.cit_lang = cit_lang;
        this.cit_lati = cit_lati;
        this.ser_lang = ser_lang;
        this.ser_lati = ser_lati;
    }




    public TodoTask(){

    }



    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
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
