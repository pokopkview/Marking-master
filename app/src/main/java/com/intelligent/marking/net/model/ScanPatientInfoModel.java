package com.intelligent.marking.net.model;

import java.io.Serializable;

public class ScanPatientInfoModel implements Serializable {

    /**
     * treat_id : 1
     * name : 王某
     * sex : 2
     * birthday : 1992-12-12
     * age : 26
     * bed_name : 01
     * course_id : 1
     * path : 20_1
     */

    private String treat_id;
    private String name;
    private int sex;
    private String birthday;
    private int age;
    private String bed_name;
    private int course_id;
    private String path;

    public String getTreat_id() {
        return treat_id;
    }

    public void setTreat_id(String treat_id) {
        this.treat_id = treat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBed_name() {
        return bed_name;
    }

    public void setBed_name(String bed_name) {
        this.bed_name = bed_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
