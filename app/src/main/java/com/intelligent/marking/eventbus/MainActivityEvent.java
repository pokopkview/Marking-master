package com.intelligent.marking.eventbus;

import com.intelligent.marking.net.model.TreatInfoModel;

public class MainActivityEvent {

    private TreatInfoModel message;
    public  MainActivityEvent(TreatInfoModel message){
        this.message=message;
    }

    public TreatInfoModel getMessage() {
        return message;
    }

    public void setMessage(TreatInfoModel message) {
        this.message = message;
    }
}
