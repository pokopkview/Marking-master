package com.intelligent.marking.net.model;

public class LoginModel {

    /**
     * hospital_id : 20
     * area_id : 1
     * department_id : 1
     * subarea_id : 1
     * passwd : 123456
     */

    private int hospital_id;
    private int area_id;
    private int department_id;
    private int subarea_id;
    private int passwd;

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getSubarea_id() {
        return subarea_id;
    }

    public void setSubarea_id(int subarea_id) {
        this.subarea_id = subarea_id;
    }

    public int getPasswd() {
        return passwd;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }
}
