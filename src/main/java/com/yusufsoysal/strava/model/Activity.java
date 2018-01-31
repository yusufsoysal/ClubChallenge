package com.yusufsoysal.strava.model;

import com.yusufsoysal.strava.utils.Utilities;
import com.yusufsoysal.strava.model.enums.ActivityType;
import com.yusufsoysal.strava.model.enums.WorkoutType;
import javastrava.api.v3.model.StravaActivity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "activity")
public class Activity {

    @Id
    private Integer id;

    @ManyToOne
    private User user;

    private String name;
    private String description;
    private Float distance;
    private Integer movingTime;
    private Integer elapsedTime;
    private Float totalElevationGain;

    @Enumerated(EnumType.STRING)
    private ActivityType type;
    private ZonedDateTime startDate;
    private String timezone;
    private MapPoint mapPoint;
    private Integer achievementCount;
    private Integer totalPhotoCount;
    private Map map;
    private Boolean trainer;
    private Boolean commute;

    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;

    @ManyToOne
    private Gear gear;
    private Float averageSpeed;
    private Float maxSpeed;
    private Float averageCadence;
    private Float averageWatts;
    private Float weightedAverageWatts;
    private Float averageHeartrate;
    private Integer maxHeartrate;
    private Float calories;
    private Integer uploadId;
    private Float startLatitude;
    private Float startLongitude;
    private String embedToken;

    public Activity(){ }

    public Activity(StravaActivity stravaActivity, User user, Gear gear){
        setId(stravaActivity.getId());
        setName(stravaActivity.getName());
        setDescription(stravaActivity.getDescription());
        setDistance(stravaActivity.getDistance());
        setMovingTime(stravaActivity.getMovingTime());
        setElapsedTime(stravaActivity.getElapsedTime());
        setTotalElevationGain(stravaActivity.getTotalElevationGain());
        setType(Utilities.valueOf(ActivityType.class, stravaActivity.getType()));
        setStartDate(stravaActivity.getStartDate());
        setTimezone(stravaActivity.getTimezone());
        setMapPoint(new MapPoint(stravaActivity.getStartLatlng(), stravaActivity.getEndLatlng()));
        setAchievementCount(stravaActivity.getAchievementCount());
        setTotalPhotoCount(stravaActivity.getTotalPhotoCount());
        setMap(new Map(stravaActivity.getMap()));
        setTrainer(stravaActivity.getTrainer());
        setCommute(stravaActivity.getCommute());
        setWorkoutType(Utilities.valueOf(WorkoutType.class, stravaActivity.getWorkoutType()));
        setAverageSpeed(stravaActivity.getAverageSpeed());
        setMaxSpeed(stravaActivity.getMaxSpeed());
        setAverageCadence(stravaActivity.getAverageCadence());
        setAverageWatts(stravaActivity.getAverageWatts());
        setWeightedAverageWatts(stravaActivity.getWeightedAverageWatts());
        setAverageHeartrate(stravaActivity.getAverageHeartrate());
        setMaxHeartrate(stravaActivity.getMaxHeartrate());
        setCalories(stravaActivity.getCalories());
        setUploadId(stravaActivity.getUploadId());
        setStartLatitude(stravaActivity.getStartLatitude());
        setStartLongitude(stravaActivity.getStartLongitude());
        setEmbedToken(stravaActivity.getEmbedToken());

        setUser(user);
        setGear(gear);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getMovingTime() {
        return movingTime;
    }

    public void setMovingTime(Integer movingTime) {
        this.movingTime = movingTime;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Float getTotalElevationGain() {
        return totalElevationGain;
    }

    public void setTotalElevationGain(Float totalElevationGain) {
        this.totalElevationGain = totalElevationGain;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public MapPoint getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
    }

    public Integer getAchievementCount() {
        return achievementCount;
    }

    public void setAchievementCount(Integer achievementCount) {
        this.achievementCount = achievementCount;
    }

    public Integer getTotalPhotoCount() {
        return totalPhotoCount;
    }

    public void setTotalPhotoCount(Integer totalPhotoCount) {
        this.totalPhotoCount = totalPhotoCount;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Boolean getTrainer() {
        return trainer;
    }

    public void setTrainer(Boolean trainer) {
        this.trainer = trainer;
    }

    public Boolean getCommute() {
        return commute;
    }

    public void setCommute(Boolean commute) {
        this.commute = commute;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public Float getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Float getAverageCadence() {
        return averageCadence;
    }

    public void setAverageCadence(Float averageCadence) {
        this.averageCadence = averageCadence;
    }

    public Float getAverageWatts() {
        return averageWatts;
    }

    public void setAverageWatts(Float averageWatts) {
        this.averageWatts = averageWatts;
    }

    public Float getWeightedAverageWatts() {
        return weightedAverageWatts;
    }

    public void setWeightedAverageWatts(Float weightedAverageWatts) {
        this.weightedAverageWatts = weightedAverageWatts;
    }

    public Float getAverageHeartrate() {
        return averageHeartrate;
    }

    public void setAverageHeartrate(Float averageHeartrate) {
        this.averageHeartrate = averageHeartrate;
    }

    public Integer getMaxHeartrate() {
        return maxHeartrate;
    }

    public void setMaxHeartrate(Integer maxHeartrate) {
        this.maxHeartrate = maxHeartrate;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Integer getUploadId() {
        return uploadId;
    }

    public void setUploadId(Integer uploadId) {
        this.uploadId = uploadId;
    }

    public Float getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Float startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Float getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Float startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getEmbedToken() {
        return embedToken;
    }

    public void setEmbedToken(String embedToken) {
        this.embedToken = embedToken;
    }
}
