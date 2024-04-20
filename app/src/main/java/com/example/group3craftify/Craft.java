package com.example.group3craftify;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Craft {
    String createdBy;
    String craftTitle;
    String craftDesc;
    String craftID;
    Craft(){}
    Craft(String createdBy, String craftTitle, String craftDesc){
        UUID id = UUID.randomUUID();
        this.craftID    = id.toString();
        this.createdBy  = createdBy;
        this.craftTitle = craftTitle;
        this.craftDesc  = craftDesc;
    }
    Craft(String craftID,String createdBy, String craftTitle, String craftDesc){
        this.craftID    = craftID;
        this.createdBy  = createdBy;
        this.craftTitle = craftTitle;
        this.craftDesc  = craftDesc;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCraftTitle() {
        return craftTitle;
    }

    public void setCraftTitle(String craftTitle) {
        this.craftTitle = craftTitle;
    }

    public String getCraftDesc() {
        return craftDesc;
    }

    public void setCraftDesc(String craftDesc) {
        this.craftDesc = craftDesc;
    }

    public String getCraftID() {
        return craftID;
    }

    public void setCraftID(String craftID) {
        this.craftID = craftID;
    }
}
