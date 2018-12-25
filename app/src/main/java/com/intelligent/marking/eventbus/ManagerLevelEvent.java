package com.intelligent.marking.eventbus;

public class ManagerLevelEvent {
    public int departid;

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }

    public ManagerLevelEvent(int departid){
        this.departid = departid;
    }


}
