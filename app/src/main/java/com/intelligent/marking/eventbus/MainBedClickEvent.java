package com.intelligent.marking.eventbus;

import com.intelligent.marking.net.model.BedInfoModel;

public class MainBedClickEvent {
    private BedInfoModel message;
    public  MainBedClickEvent(BedInfoModel message){
        this.message=message;
    }

    public BedInfoModel getMessage() {
        return message;
    }

    public void setMessage(BedInfoModel message) {
        this.message = message;
    }
}
