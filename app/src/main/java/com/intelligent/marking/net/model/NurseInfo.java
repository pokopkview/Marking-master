package com.intelligent.marking.net.model;

public class NurseInfo {


    /**
     * nurse_id : 2
     * nurse_name : 周星星
     * nurse_no : no0033
     * subarea_id : 1
     */

    private int nurse_id;
    private String nurse_name;
    private String nurse_no;
    private int subarea_id;

    public int getNurse_id() {
        return nurse_id;
    }

    public void setNurse_id(int nurse_id) {
        this.nurse_id = nurse_id;
    }

    public String getNurse_name() {
        return nurse_name;
    }

    public void setNurse_name(String nurse_name) {
        this.nurse_name = nurse_name;
    }

    public String getNurse_no() {
        return nurse_no;
    }

    public void setNurse_no(String nurse_no) {
        this.nurse_no = nurse_no;
    }

    public int getSubarea_id() {
        return subarea_id;
    }

    public void setSubarea_id(int subarea_id) {
        this.subarea_id = subarea_id;
    }
}
