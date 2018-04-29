package pranavmahajan21.com.viewpagermaterial.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/* Created by Pranav on 5/9/17 */

@IgnoreExtraProperties
public class BasicInfo {

    String eventId;
    String aliasId;

    String name;

    boolean isEventPublic;

    String startTime;
    String endTime;

    double latitude;
    double longitude;
    String locationAddress;

    String locationShort;

    public BasicInfo() {
    }

    public BasicInfo(String eventId, String aliasId, String name, boolean isEventPublic, String startTime, String endTime, double latitude, double longitude, String locationShort, String locationAddress) {
        this.eventId = eventId;
        this.aliasId = aliasId;
        this.name = name;
        this.isEventPublic = isEventPublic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationShort = locationShort;
        this.locationAddress = locationAddress;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public boolean isEventPublic() {
        return isEventPublic;
    }

    public void setEventPublic(boolean eventPublic) {
        isEventPublic = eventPublic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationShort() {
        return locationShort;
    }

    public void setLocationShort(String locationShort) {
        this.locationShort = locationShort;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setLatLang(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BasicInfo) {
            return eventId.equalsIgnoreCase(((BasicInfo) obj).getEventId());
        }
        return false;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("eventId", eventId);
        result.put("aliasId", aliasId);
        result.put("name", name);

        result.put("isEventPublic", isEventPublic);

        result.put("startTime", startTime);
        result.put("endTime", endTime);

        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("locationShort", locationShort);
        result.put("locationAddress", locationAddress);

        return result;
    }
}
