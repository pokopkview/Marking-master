package com.intelligent.marking.net.model;

import java.io.Serializable;
import java.util.List;

public class TreatInfoModel implements Serializable {


    /**
     * treat_id : 1
     * course_id : 1
     * subarea_id : 1
     * duct_id : 1
     * duct_attr_id : 1
     * treat_status : 0
     * keep_day : 3
     * keep_hour : 0
     * insert_date : 2018-11-13
     * insert_time : 14:50
     * insert_at : 0
     * remove_at : 1544021423
     * outside : 3
     * inside : 2
     * nurse_id : 2
     * remove_nurse_id : 0
     * operate_place : 湘雅二医院-急症科
     * remove_operate_place : null
     * next_replace_time : 1544254200
     * next_remind_date : 2018-11-14
     * need_remind_next : 0
     * is_remind_current : 0
     * created_at : 0
     * updated_at : 1544021423
     * remark : null
     * duct_name : CVC
     * show_color : red
     * nurse_name : 周星星
     * nurse_no : no0033
     * duct_cat : [{"duct_attr_id":1,"duct_cat_id":1,"duct_cat_name":"敷贴"},{"duct_attr_id":2,"duct_cat_id":2,"duct_cat_name":"引流袋或引流瓶"}]
     */

    private int treat_id;
    private int course_id;
    private int subarea_id;
    private int duct_id;
    private int duct_attr_id;
    private int treat_status;
    private int keep_day;
    private int keep_hour;
    private String insert_date;
    private String insert_time;
    private int insert_at;
    private int remove_at;
    private int outside;
    private int inside;
    private int nurse_id;
    private int remove_nurse_id;
    private String operate_place;
    private Object remove_operate_place;
    private int next_replace_time;
    private String next_remind_date;
    private int need_remind_next;
    private int is_remind_current;
    private int created_at;
    private int updated_at;
    private Object remark;
    private String duct_name;
    private String show_color;
    private String nurse_name;
    private String nurse_no;
    private List<DuctCatBean> duct_cat;

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

    public int getDuct_attr_id() {
        return duct_attr_id;
    }

    public void setDuct_attr_id(int duct_attr_id) {
        this.duct_attr_id = duct_attr_id;
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

    public int getRemove_at() {
        return remove_at;
    }

    public void setRemove_at(int remove_at) {
        this.remove_at = remove_at;
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

    public int getRemove_nurse_id() {
        return remove_nurse_id;
    }

    public void setRemove_nurse_id(int remove_nurse_id) {
        this.remove_nurse_id = remove_nurse_id;
    }

    public String getOperate_place() {
        return operate_place;
    }

    public void setOperate_place(String operate_place) {
        this.operate_place = operate_place;
    }

    public Object getRemove_operate_place() {
        return remove_operate_place;
    }

    public void setRemove_operate_place(Object remove_operate_place) {
        this.remove_operate_place = remove_operate_place;
    }

    public int getNext_replace_time() {
        return next_replace_time;
    }

    public void setNext_replace_time(int next_replace_time) {
        this.next_replace_time = next_replace_time;
    }

    public String getNext_remind_date() {
        return next_remind_date;
    }

    public void setNext_remind_date(String next_remind_date) {
        this.next_remind_date = next_remind_date;
    }

    public int getNeed_remind_next() {
        return need_remind_next;
    }

    public void setNeed_remind_next(int need_remind_next) {
        this.need_remind_next = need_remind_next;
    }

    public int getIs_remind_current() {
        return is_remind_current;
    }

    public void setIs_remind_current(int is_remind_current) {
        this.is_remind_current = is_remind_current;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getDuct_name() {
        return duct_name;
    }

    public void setDuct_name(String duct_name) {
        this.duct_name = duct_name;
    }

    public String getShow_color() {
        return show_color;
    }

    public void setShow_color(String show_color) {
        this.show_color = show_color;
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

    public List<DuctCatBean> getDuct_cat() {
        return duct_cat;
    }

    public void setDuct_cat(List<DuctCatBean> duct_cat) {
        this.duct_cat = duct_cat;
    }

    public static class DuctCatBean  implements Serializable {
        /**
         * duct_attr_id : 1
         * duct_cat_id : 1
         * duct_cat_name : 敷贴
         */

        private int duct_attr_id;
        private int duct_cat_id;
        private String duct_cat_name;

        public int getDuct_attr_id() {
            return duct_attr_id;
        }

        public void setDuct_attr_id(int duct_attr_id) {
            this.duct_attr_id = duct_attr_id;
        }

        public int getDuct_cat_id() {
            return duct_cat_id;
        }

        public void setDuct_cat_id(int duct_cat_id) {
            this.duct_cat_id = duct_cat_id;
        }

        public String getDuct_cat_name() {
            return duct_cat_name;
        }

        public void setDuct_cat_name(String duct_cat_name) {
            this.duct_cat_name = duct_cat_name;
        }
    }
}
