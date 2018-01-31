package com.yusufsoysal.strava.model;

import com.yusufsoysal.strava.model.enums.GearType;
import com.yusufsoysal.strava.utils.Utilities;
import javastrava.api.v3.model.StravaGear;

import javax.persistence.*;

@Entity(name = "gear")
public class Gear {
    @Id
    protected String id;
    protected Boolean primaryGear;
    protected String name;
    protected Float distance;
    protected String brandName;
    protected String modelName;
    protected String description;

    @Enumerated(EnumType.STRING)
    protected GearType gearType;

    @ManyToOne
    private User user;

    public Gear(){ }

    public Gear(StravaGear stravaGear){
        setId(stravaGear.getId());
        setBrandName(stravaGear.getBrandName());
        setDescription(stravaGear.getDescription());
        setDistance(stravaGear.getDistance());
        setModelName(stravaGear.getModelName());
        setName(stravaGear.getName());
        setPrimaryGear(stravaGear.getPrimary());
        setGearType(Utilities.valueOf(GearType.class, stravaGear.getGearType()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getPrimaryGear() {
        return primaryGear;
    }

    public void setPrimaryGear(Boolean primaryGear) {
        this.primaryGear = primaryGear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public GearType getGearType() {
        return gearType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
