package com.intelligent.marking.net.model;

import java.util.List;

public class BedStatusModel {

    /**
     * bed_name : 01
     * name : 王某
     * sex : 2
     * age : 25
     * data : [{"treat_id":1,"course_id":1,"subarea_id":1,"duct_id":1,"duct_name":"CVC","duct_attr_id":1,"duct_attr_name":"敷贴","treat_status":0,"keep_day":3,"keep_hour":0,"insert_date":"2018-11-13","insert_time":"14:50","insert_at":0,"outside":3,"inside":2,"nurse_id":2,"nurse_name":"周星星","operate_place":"湘雅二医院-急症科"}]
     */

    private String bed_name;
    private String name;
    private int sex;
    private int age;
    private List<DataBean> data;

    public String getBed_name() {
        return bed_name;
    }

    public void setBed_name(String bed_name) {
        this.bed_name = bed_name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * treat_id : 1
         * course_id : 1
         * subarea_id : 1
         * duct_id : 1
         * duct_name : CVC
         * duct_attr_id : 1
         * duct_attr_name : 敷贴
         * treat_status : 0
         * keep_day : 3
         * keep_hour : 0
         * insert_date : 2018-11-13
         * insert_time : 14:50
         * insert_at : 0
         * outside : 3
         * inside : 2
         * nurse_id : 2
         * nurse_name : 周星星
         * operate_place : 湘雅二医院-急症科
         */

        private int treat_id;
        private int course_id;
        private int subarea_id;
        private int duct_id;
        private String duct_name;
        private int duct_attr_id;
        private String duct_attr_name;
        private int treat_status;
        private int keep_day;
        private int keep_hour;
        private String insert_date;
        private String insert_time;
        private int insert_at;
        private int outside;
        private int inside;
        private int nurse_id;
        private String nurse_name;
        private String operate_place;

        public int getTreat_id() {
            return treat_id;
        }

        public void setTreat_id(int treat_id) {
            this.treat_id = treat_id;
        }

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
        }

        public int getSubarea_id() {
            return subarea_id;
        }

        public void setSubarea_id(int subarea_id) {
            this.subarea_id = subarea_id;
        }

        public int getDuct_id() {
            return duct_id;
        }

        public void setDuct_id(int duct_id) {
            this.duct_id = duct_id;
        }

        public String getDuct_name() {
            return duct_name;
        }

        public void setDuct_name(String duct_name) {
            this.duct_name = duct_name;
        }

        public int getDuct_attr_id() {
            return duct_attr_id;
        }

        public void setDuct_attr_id(int duct_attr_id) {
            this.duct_attr_id = duct_attr_id;
        }

        public String getDuct_attr_name() {
            return duct_attr_name;
        }

        public void setDuct_attr_name(String duct_attr_name) {
            this.duct_attr_name = duct_attr_name;
        }

        public int getTreat_status() {
            return treat_status;
        }

        public void setTreat_status(int treat_status) {
            this.treat_status = treat_status;
        }

        public int getKeep_day() {
            return keep_day;
        }

        public void setKeep_day(int keep_day) {
            this.keep_day = keep_day;
        }

        public int getKeep_hour() {
            return keep_hour;
        }

        public void setKeep_hour(int keep_hour) {
            this.keep_hour = keep_hour;
        }

        public String getInsert_date() {
            return insert_date;
        }

        public void setInsert_date(String insert_date) {
            this.insert_date = insert_date;
        }

        public String getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(String insert_time) {
            this.insert_time = insert_time;
        }

        public int getInsert_at() {
            return insert_at;
        }

        public void setInsert_at(int insert_at) {
            this.insert_at = insert_at;
        }

        public int getOutside() {
            return outside;
        }

        public void setOutside(int outside) {
            this.outside = outside;
        }

        public int getInside() {
            return inside;
        }

        public void setInside(int inside) {
            this.inside = inside;
        }

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

        public String getOperate_place() {
            return operate_place;
        }

        public void setOperate_place(String operate_place) {
            this.operate_place = operate_place;
        }
    }
}
