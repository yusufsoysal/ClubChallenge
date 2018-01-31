package com.yusufsoysal.strava.service;

import com.yusufsoysal.strava.model.Club;
import com.yusufsoysal.strava.model.Gear;
import com.yusufsoysal.strava.model.StravaUserDetails;
import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.repository.UserRepository;
import com.yusufsoysal.strava.utils.DateUtils;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.model.reference.StravaGearType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private GearService gearService;

    @Autowired
    private ClubService clubService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public List<User> getUsersToSync() {
        LocalDateTime lastSyncTime = LocalDateTime.now().minusDays(1);
        LocalDateTime lastLoginTime = LocalDateTime.now().minusDays(3);
        return repository.findUsersByLastSynchedTimeBeforeAndLastLoginTimeAfter(lastSyncTime, lastLoginTime);
    }

    @Transactional
    public void userSynched(User user) {
        user.setLastSynchedTime(LocalDateTime.now());
        repository.save(user);
    }

    public User getUserByStravaId(Integer stravaId) {
        return repository.findByStravaId(stravaId);
    }

    @Transactional
    public void userLoggedIn(User user, StravaUserDetails userDetails) {
        user.setLastLoginTime(LocalDateTime.now());
        updateUser(user, userDetails);
    }

    @Transactional
    public void userLoggedIn(User user) {
        user.setLastLoginTime(LocalDateTime.now());
        repository.save(user);
    }

    @Transactional
    public void updateUser(User user, StravaUserDetails userDetails) {
        StravaAthlete athlete = userDetails.getAthlete();

        user.updateWithStrava(athlete);
        user.setAccessToken(userDetails.getAccesstoken());
        if (user.getUsername() == null) {
            user.setUsername(userDetails.getUsername());
        }

        if( user.getLastSynchedTime() == null ){
            user.setLastSynchedTime(DateUtils.minimumDate());
        }

        user.setClubs(getClubs(athlete));
        if (user.hasAnyBikes()) {
            removeGearsNotInUseAnymore(user.getBikes(), athlete.getBikes());
        }
        user.setBikes(getGears(athlete.getBikes(), StravaGearType.BIKE));

        if (user.hasAnyShoes()) {
            removeGearsNotInUseAnymore(user.getShoes(), athlete.getShoes());
        }
        user.setShoes(getGears(athlete.getShoes(), StravaGearType.SHOES));

        repository.save(user);

        // save user reference to gears
        gearService.assignGearsToUser(user, user.getBikes());
        gearService.assignGearsToUser(user, user.getShoes());
    }

    private void removeGearsNotInUseAnymore(List<Gear> userGears, List<StravaGear> gears) {
        userGears.stream()
                .filter(gear -> notContainsGear(gears, gear))
                .forEach(gearService::removeGear);
    }

    private boolean notContainsGear(List<StravaGear> gears, Gear gear) {
        return gears.stream()
                .map(StravaGear::getId)
                .noneMatch(id -> StringUtils.equals(gear.getId(), id));
    }

    @Transactional
    public void createUser(StravaUserDetails userDetails) {
        StravaAthlete athlete = userDetails.getAthlete();
        User user = new User(athlete);

        user.setAccessToken(userDetails.getAccesstoken());
        user.setUsername(userDetails.getUsername());

        user.setClubs(getClubs(athlete));
        user.setBikes(getGears(athlete.getBikes(), StravaGearType.BIKE));
        user.setShoes(getGears(athlete.getShoes(), StravaGearType.SHOES));
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastSynchedTime(DateUtils.minimumDate());
        repository.save(user);

        // save user reference to gears
        gearService.assignGearsToUser(user, user.getBikes());
        gearService.assignGearsToUser(user, user.getShoes());
    }

    @Transactional
    public User createNonActiveUser(StravaAthlete athlete) {
        User user = new User(athlete);
        repository.save(user);

        return user;
    }

    private List<Gear> getGears(List<StravaGear> gears, StravaGearType gearType) {
        return gears.stream()
                .map(gear -> {
                    gear.setGearType(gearType);
                    return gear;
                })
                .map(Gear::new)
                .collect(Collectors.toList());
    }

    private List<Club> getClubs(StravaAthlete athlete) {
        return athlete.getClubs()
                .stream()
                .map(clubService::getOrCreate)
                .collect(Collectors.toList());
    }
}
