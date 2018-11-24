package com.intelligent.marking.net.model;

public class RegisterModel {

    /**
     * hospital_id : 20
     * area_name : 外科楼
     * department_name : 创伤科
     * subarea_name : 一区
     * bed_number : 20
     * passwd : 123456
     */

    private int hospital_id;
    private String area_name;
    private String department_name;
    private String subarea_name;
    private int bed_number;
    private int passwd;

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getSubarea_name() {
        return subarea_name;
    }

    public void setSubarea_name(String subarea_name) {
        this.subarea_name = subarea_name;
    }

    public int getBed_number() {
        return bed_number;
    }

    public void setBed_number(int bed_number) {
        this.bed_number = bed_number;
    }

    public int getPasswd() {
        return passwd;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }
}
