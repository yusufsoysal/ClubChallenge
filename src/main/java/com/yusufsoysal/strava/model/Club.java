package com.yusufsoysal.strava.model;

import com.yusufsoysal.strava.model.enums.ClubType;
import com.yusufsoysal.strava.model.enums.SportType;
import com.yusufsoysal.strava.utils.Utilities;
import javastrava.api.v3.model.StravaClub;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Club {

    @Id
    private Integer id;
    private String name;
    private String profileMedium;
    private String profile;
    private String description;

    @Enumerated(EnumType.STRING)
    private ClubType clubType;

    @Enumerated(EnumType.STRING)
    private SportType sportType;
    private String city;
    private String state;
    private String country;
    private Boolean privateClub;
    private Integer memberCount;

    private LocalDateTime lastSynchedTime;

    public Club() {
    }

    public Club(StravaClub stravaClub) {
        setId(stravaClub.getId());
        setCity(stravaClub.getCity());
        setCountry(stravaClub.getCountry());
        setMemberCount(stravaClub.getMemberCount());
        setName(stravaClub.getName());
        setProfile(stravaClub.getProfile());
        setProfileMedium(stravaClub.getProfileMedium());
        setSportType(Utilities.valueOf(SportType.class, stravaClub.getSportType()));
        setState(stravaClub.getState());
        setDescription(stravaClub.getDescription());
        setClubType(Utilities.valueOf(ClubType.class, stravaClub.getClubType()));
        setPrivateClub(stravaClub.getPrivateClub());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileMedium() {
        return profileMedium;
    }

    public void setProfileMedium(String profileMedium) {
        this.profileMedium = profileMedium;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClubType getClubType() {
        return clubType;
    }

    public void setClubType(ClubType clubType) {
        this.clubType = clubType;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getPrivateClub() {
        return privateClub;
    }

    public void setPrivateClub(Boolean privateClub) {
        this.privateClub = privateClub;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public LocalDateTime getLastSynchedTime() {
        return lastSynchedTime;
    }

    public void setLastSynchedTime(LocalDateTime lastSynchedTime) {
        this.lastSynchedTime = lastSynchedTime;
    }
}
