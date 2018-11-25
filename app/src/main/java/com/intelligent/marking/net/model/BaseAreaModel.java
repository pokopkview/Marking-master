package com.intelligent.marking.net.model;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class BaseAreaModel implements IPickerViewData {

    private int id;
    private String name;
    private List<BaseAreaModel> areaJsonModelList;

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

    public List<BaseAreaModel> getAreaJsonModelList() {
        return areaJsonModelList;
    }

    public void setAreaJsonModelList(List<BaseAreaModel> areaJsonModelList) {
        this.areaJsonModelList = areaJsonModelList;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }
}
