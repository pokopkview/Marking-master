package com.intelligent.marking.net.model;

import com.contrarywind.interfaces.IPickerViewData;

public class AreaJsonModel implements IPickerViewData {

    /**
     * id : 7869
     * name : 四平市辖区
     */

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }
}
