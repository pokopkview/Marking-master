package com.intelligent.marking.net.model;

import java.io.Serializable;
import java.util.List;

public class DuctListInfo implements Serializable{


    /**
     * duct_id : 1
     * duct_name : CVC
     * duct_img : https://data.zusux.com/upload/images/82de726c07bfab9dd9d3471ca6a1b3c9.jpg
     * intro :
     * number : 1
     * duct_cat : [{"duct_attr_id":1,"duct_cat_name":"敷贴","outside":0,"inside":0,"keep_day":0,"keep_hour":0},{"duct_attr_id":2,"duct_cat_name":"引流袋或引流瓶","outside":0,"inside":0,"keep_day":0,"keep_hour":0}]
     * operate_place : 湘雅二医院-骨一科
     */

    private int duct_id;
    private String duct_name;
    private String duct_img;
    private String intro;
    private int number;
    private String operate_place;
    private int outside;
    private int inside;
    private int keep_day;
    private int keep_hour;
    private List<DuctCatBean> duct_cat;

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

    public String getDuct_img() {
        return duct_img;
    }

    public void setDuct_img(String duct_img) {
        this.duct_img = duct_img;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOperate_place() {
        return operate_place;
    }

    public void setOperate_place(String operate_place) {
        this.operate_place = operate_place;
    }

    public List<DuctCatBean> getDuct_cat() {
        return duct_cat;
    }

    public void setDuct_cat(List<DuctCatBean> duct_cat) {
        this.duct_cat = duct_cat;
    }

    public static class DuctCatBean implements Serializable {
        /**
         * duct_attr_id : 1
         * duct_cat_name : 敷贴
         * outside : 0
         * inside : 0
         * keep_day : 0
         * keep_hour : 0
         */

        private int duct_attr_id;
        private String duct_cat_name;
        private int outside;
        private int inside;
        private int keep_day;
        private int keep_hour;

        public int getDuct_attr_id() {
            return duct_attr_id;
        }

        public void setDuct_attr_id(int duct_attr_id) {
            this.duct_attr_id = duct_attr_id;
        }

        public String getDuct_cat_name() {
            return duct_cat_name;
        }

        public void setDuct_cat_name(String duct_cat_name) {
            this.duct_cat_name = duct_cat_name;
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
    }
}
