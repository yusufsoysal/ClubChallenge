package com.yusufsoysal.strava.model;

import com.yusufsoysal.strava.model.enums.AthleteType;
import com.yusufsoysal.strava.model.enums.Gender;
import com.yusufsoysal.strava.model.enums.MeasurementMethod;
import com.yusufsoysal.strava.utils.Utilities;
import javastrava.api.v3.model.StravaAthlete;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private Integer stravaId;
    private String firstname;
    private String lastname;
    private String email;
    private String profileMedium;
    private String profile;
    private String city;
    private String state;
    private String country;

    @Enumerated(EnumType.STRING)
    private Gender sex;
    private ZonedDateTime stravaCreatedAt;
    private ZonedDateTime stravaUpdatedAt;
    private Integer followerCount;
    private Integer friendCount;
    private String datePreference;

    @Enumerated(EnumType.STRING)
    private MeasurementMethod measurementPreference;
    private Float weight;

    @Enumerated(EnumType.STRING)
    private AthleteType athleteType;

    @OneToMany(cascade=CascadeType.MERGE)
    private List<Club> clubs;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
    @Where(clause = "gear_type = 'BIKE'")
    private List<Gear> bikes;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
    @Where(clause = "gear_type = 'SHOES'")
    private List<Gear> shoes;

    private String accessToken;

    private LocalDateTime lastLoginTime;

    private LocalDateTime lastSynchedTime;

    public User(){ }

    public User(StravaAthlete athlete){
        updateWithStrava(athlete);
    }

    public void updateWithStrava(StravaAthlete athlete) {
        setStravaId(athlete.getId());
        setFirstname(athlete.getFirstname());
        setLastname(athlete.getLastname());
        setEmail(athlete.getEmail());
        setProfileMedium(athlete.getProfileMedium());
        setProfile(athlete.getProfile());
        setCity(athlete.getCity());
        setState(athlete.getState());
        setCountry(athlete.getCountry());
        setSex(Utilities.valueOf(Gender.class, athlete.getSex()));
        setStravaCreatedAt(athlete.getCreatedAt());
        setStravaUpdatedAt(athlete.getUpdatedAt());
        setFollowerCount(athlete.getFollowerCount());
        setFriendCount(athlete.getFriendCount());
        setDatePreference(athlete.getDatePreference());
        setMeasurementPreference(Utilities.valueOf(MeasurementMethod.class, athlete.getMeasurementPreference()));
        setWeight(athlete.getWeight());
        setAthleteType(Utilities.valueOf(AthleteType.class, athlete.getAthleteType()));
    }

    public boolean hasAnyBikes(){
        return CollectionUtils.isNotEmpty(bikes);
    }

    public boolean hasAnyShoes(){
        return CollectionUtils.isNotEmpty(shoes);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStravaId() {
        return stravaId;
    }

    public void setStravaId(Integer stravaId) {
        this.stravaId = stravaId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public ZonedDateTime getStravaCreatedAt() {
        return stravaCreatedAt;
    }

    public void setStravaCreatedAt(ZonedDateTime stravaCreatedAt) {
        this.stravaCreatedAt = stravaCreatedAt;
    }

    public ZonedDateTime getStravaUpdatedAt() {
        return stravaUpdatedAt;
    }

    public void setStravaUpdatedAt(ZonedDateTime stravaUpdatedAt) {
        this.stravaUpdatedAt = stravaUpdatedAt;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }

    public String getDatePreference() {
        return datePreference;
    }

    public void setDatePreference(String datePreference) {
        this.datePreference = datePreference;
    }

    public MeasurementMethod getMeasurementPreference() {
        return measurementPreference;
    }

    public void setMeasurementPreference(MeasurementMethod measurementPreference) {
        this.measurementPreference = measurementPreference;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public List<Gear> getBikes() {
        return bikes;
    }

    public void setBikes(List<Gear> bikes) {
        this.bikes = bikes;
    }

    public List<Gear> getShoes() {
        return shoes;
    }

    public void setShoes(List<Gear> shoes) {
        this.shoes = shoes;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public AthleteType getAthleteType() {
        return athleteType;
    }

    public void setAthleteType(AthleteType athleteType) {
        this.athleteType = athleteType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getLastSynchedTime() {
        return lastSynchedTime;
    }

    public void setLastSynchedTime(LocalDateTime lastSynchedTime) {
        this.lastSynchedTime = lastSynchedTime;
    }
}