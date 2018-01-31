package com.yusufsoysal.strava.model;

import javastrava.api.v3.model.StravaMapPoint;

import javax.persistence.Embeddable;

@Embeddable
public class MapPoint {

    private Float startLat;
    private Float startLon;
    private Float endLat;
    private Float endLon;

    public MapPoint(){ }

    public MapPoint(StravaMapPoint startMapPoint, StravaMapPoint endMapPoint){
        if( startMapPoint != null ){
            setStartLat(startMapPoint.getLatitude());
            setStartLon(startMapPoint.getLongitude());
        }

        if( endMapPoint != null ){
            setEndLat(endMapPoint.getLatitude());
            setEndLon(endMapPoint.getLongitude());
        }
    }

    public Float getStartLatitude() {
        return startLat;
    }

    public void setStartLat(Float startLatitude) {
        this.startLat = startLatitude;
    }

    public Float getStartLongitude() {
        return startLon;
    }

    public void setStartLon(Float startLongitude) {
        this.startLon = startLongitude;
    }

    public Float getEndLatitude() {
        return endLat;
    }

    public void setEndLat(Float endLatitude) {
        this.endLat = endLatitude;
    }

    public Float getEndLongitude() {
        return endLon;
    }

    public void setEndLon(Float endLongitude) {
        this.endLon = endLongitude;
    }
}
