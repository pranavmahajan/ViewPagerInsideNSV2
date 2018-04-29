package pranavmahajan21.com.viewpagermaterial.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Created by Pranav on 5/9/17 */

@IgnoreExtraProperties
public class Session {
    String id;

    // TODO: 7/21/17 Refactor name to title
    String name;

    String location;

    String startTime;
    String endTime;


    List<String> speakerList = new ArrayList<String>();

    boolean hasHeader = false;
    String headerType;
    List<String> headerContentMandatoryList;
    String youtubeUrl;

    boolean hasPolls = false;
    boolean isQuestionAllowed = true;
    boolean hasDetailsScreen = true;
    boolean hasChatroom = false;

    int position;

    boolean isQCPVisible = false;

    public Session() {
    }

    public Session(String name, String startTime, String endTime, List<String> speakerList, Boolean hasPolls, Boolean isQuestionAllowed, Boolean hasDetailsScreen) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speakerList = speakerList;
        if (hasPolls != null) {
            this.hasPolls = hasPolls;
        }
        if (isQuestionAllowed != null) {
            this.isQuestionAllowed = isQuestionAllowed;
        }
        if (hasDetailsScreen != null) {
            this.hasDetailsScreen = hasDetailsScreen;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<String> getSpeakerList() {
        return speakerList;
    }

    public void setSpeakerList(List<String> speakerList) {
        this.speakerList = speakerList;
    }

    public String getHeaderType() {
        return headerType;
    }

    public void setHeaderType(String headerType) {
        this.headerType = headerType;
    }

    public List<String> getHeaderContentMandatoryList() {
        return headerContentMandatoryList;
    }

    public void setHeaderContentMandatoryList(List<String> headerContentMandatoryList) {
        this.headerContentMandatoryList = headerContentMandatoryList;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setHasHeaderHeaderType(boolean hasHeader, String headerType) {
        this.hasHeader = hasHeader;
        this.headerType = headerType;
    }

    public boolean isQCPVisible() {
        return isQCPVisible;
    }

    public void setQCPVisible(boolean QCPVisible) {
        isQCPVisible = QCPVisible;
    }

    /* booleans with special Gs & Ss */
    public boolean getHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean getHasPolls() {
        return hasPolls;
    }

    public void setHasPolls(boolean hasPolls) {
        this.hasPolls = hasPolls;
    }

    public boolean getIsQuestionAllowed() {
        return isQuestionAllowed;
    }

    public void setIsQuestionAllowed(boolean questionAllowed) {
        isQuestionAllowed = questionAllowed;
    }

    public boolean getHasDetailsScreen() {
        return hasDetailsScreen;
    }

    public void setHasDetailsScreen(boolean hasDetailsScreen) {
        this.hasDetailsScreen = hasDetailsScreen;
    }

    public boolean getHasChatroom() {
        return hasChatroom;
    }

    public void setHasChatroom(boolean hasChatroom) {
        this.hasChatroom = hasChatroom;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("startTime", startTime);
        result.put("location", location);
        result.put("endTime", endTime);
        result.put("speakerList", speakerList);
        result.put("hasPolls", hasPolls);
        result.put("hasDetailsScreen", hasDetailsScreen);
        result.put("getIsQuestionAllowed", isQuestionAllowed);
        result.put("hasChatroom", hasChatroom);
        result.put("hasHeader", hasHeader);
        result.put("headerType", headerType);
        result.put("headerContentMandatoryList", headerContentMandatoryList);
        result.put("youtubeUrl", youtubeUrl);
        result.put("position", position);

        return result;
    }
}
