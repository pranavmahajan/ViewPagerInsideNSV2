package pranavmahajan21.com.viewpagermaterial.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Created by Pranav on 5/9/17 */

@IgnoreExtraProperties
public class Event {

    String id;
    String aliasId;

    BasicInfo basicInfo;

    // TODO: 1/16/18 Rename this to ownerList
    List<String> owner;
    Map<String, Object> prefs;

    List<Session> sessionList;

    List<Profile> speakersProfiles = new ArrayList<Profile>();

    //    List<Invite> attendeeInvites;
    /* This is a Map of <email, attendingStatus> */
    Map<String, Integer> attendeesMap = new HashMap<String, Integer>();
//    List<Profile> attendeesProfiles = new ArrayList<Profile>();

    List<MenuItem> menuItems = new ArrayList<MenuItem>();

    boolean isPreReleaseTest;

    long userDataUpdatedAt;

    public Event() {
    }

    public Event(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public List<String> getOwner() {
        return owner;
    }

    public void setOwner(List<String> owner) {
        this.owner = owner;
    }


    public Map<String, Object> getPrefs() {
        return prefs;
    }

    public void setPrefs(Map<String, Object> prefs) {
        this.prefs = prefs;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }


    public List<Profile> getSpeakersProfiles() {
        return speakersProfiles;
    }

    public void setSpeakersProfiles(List<Profile> speakersProfiles) {
        this.speakersProfiles = speakersProfiles;
    }

    public Map<String, Integer> getAttendeesMap() {
        return attendeesMap;
    }

    public void setAttendeesMap(Map<String, Integer> attendeesMap) {
        this.attendeesMap = attendeesMap;
    }

//    public List<Profile> getAttendeesProfiles() {
//        return attendeesProfiles;
//    }
//
//    public void setAttendeesProfiles(List<Profile> attendeesProfiles) {
//        this.attendeesProfiles = attendeesProfiles;
//    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


    public boolean isLoggedInUserOwner() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        return owner.contains(firebaseAuth.getCurrentUser().getEmail());
    }

    public boolean isPreReleaseTest() {
        return isPreReleaseTest;
    }

    public void setPreReleaseTest(boolean preReleaseTest) {
        isPreReleaseTest = preReleaseTest;
    }

    public long getUserDataUpdatedAt() {
        return userDataUpdatedAt;
    }

    public void setUserDataUpdatedAt(long userDataUpdatedAt) {
        this.userDataUpdatedAt = userDataUpdatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BasicInfo) {
            return id.equalsIgnoreCase(((BasicInfo) obj).getEventId());
        } else if (obj instanceof Event) {
            return id.equalsIgnoreCase(((Event) obj).getId());
        } else if (obj instanceof String) {
            return id.equalsIgnoreCase((String) obj);
        } else {

            return false;
        }

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("aliasId", aliasId);
        result.put("basicInfo", basicInfo);
        result.put("owner", owner);

//        result.put("sessionList", sessionList);

        result.put("menuItems", menuItems);

        //        result.put("ContactUs", contactUsList);

        return result;
    }
}
