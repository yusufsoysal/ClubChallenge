package com.yusufsoysal.strava.model;

import javastrava.api.v3.model.StravaMap;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Map {

    private String mapId;

    @Column(length = 1000)
    private String polyline;

    @Column(length = 1000)
    private String summaryPolyline;

    public Map(){ }

    public Map(StravaMap map) {
        setMapId(map.getId());
        setPolyline(map.getPolyline());
        setSummaryPolyline(map.getSummaryPolyline());
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getSummaryPolyline() {
        return summaryPolyline;
    }

    public void setSummaryPolyline(String summaryPolyline) {
        this.summaryPolyline = summaryPolyline;
    }
}
