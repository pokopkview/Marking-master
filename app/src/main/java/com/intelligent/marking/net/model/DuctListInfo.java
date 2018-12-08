package com.intelligent.marking.net.model;

import java.util.List;

public class DuctListInfo {


    /**
     * duct_id : 1
     * duct_name : 胃管
     * duct_img : https://data.zusux.com/upload/images/c0afa30689f7c8955b2cc6547cb02a5b.jpg
     * intro :
     * number : 1
     * duct_cat : [{"duct_attr_id":1,"duct_cat_id":1,"duct_cat_name":"管"},{"duct_attr_id":2,"duct_cat_id":2,"duct_cat_name":"膜"}]
     * operate_place : 湘雅二医院-骨一科
     */

    private int duct_id;
    private String duct_name;
    private String duct_img;
    private String intro;
    private int number;
    private String operate_place;
    private List<DuctCatBean> duct_cat;

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

    public static class DuctCatBean {
        /**
         * duct_attr_id : 1
         * duct_cat_id : 1
         * duct_cat_name : 管
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
