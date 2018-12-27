package com.intelligent.marking.net.model;

import java.io.Serializable;

public class BaseModel<T> {
    private int status;
    private int count;
    private T data;
    private String info;
    private String uuid;
    private boolean flag;
    private InserDuctNurseInfo course_info;
    private int course_id;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public InserDuctNurseInfo getCourse_info() {
        return course_info;
    }

    public void setCourse_info(InserDuctNurseInfo course_info) {
        this.course_info = course_info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public class InserDuctNurseInfo implements Serializable {

        /**
         * course_id : 1
         * subarea_id : 1
         * name : 王某
         * sex : 2
         * age : 25
         * bed_name : 01
         */

        private int course_id;
        private int subarea_id;
        private String name;
        private int sex;
        private int age;
        private String bed_name;

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

        public String getBed_name() {
            return bed_name;
        }

        public void setBed_name(String bed_name) {
            this.bed_name = bed_name;
        }
    }

}
