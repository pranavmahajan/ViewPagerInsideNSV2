package pranavmahajan21.com.viewpagermaterial.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Created by Pranav on 5/9/17 */

@IgnoreExtraProperties
public class Profile {

    String uid;
    String email;

    String name;
    String phoneNo;

    String about;

    String fileCMId;

    boolean isPhoneNumberVisible = false;
    boolean isProfileImageVisible = true;

    //    String profileImageUpdatedOn;
    String profileImageUri;
//    String localReference;

    String organization;
    String designation;


    long updatedAt;
    long updatedAtImage;

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("about", about);
        result.put("fileCMId", fileCMId);
        result.put("phoneNo", phoneNo);
        result.put("getIsPhoneNumberVisible", isPhoneNumberVisible);
        result.put("getIsProfileImageVisible", isProfileImageVisible);
        result.put("organization", organization);
        result.put("designation", designation);
        result.put("profileImageUri", profileImageUri);
        result.put("updatedAt", updatedAt);
        result.put("updatedAtImage", updatedAtImage);

        return result;
    }

    public Profile() {
//        com.google.firebase.database.DatabaseException: Class com.mw.lovelycrap.model.Speaker is missing a constructor with no arguments
    }


    // TODO: 12/11/16 Rearrange this construcror to have email as first argumnet
    public Profile(String name, String email, String phoneNo, long updatedAt) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.updatedAt = updatedAt;
    }


    public Profile(String name, String email, String about, String profileImageUri, long updatedAt, String fileCMId) {
        this.name = name;
        this.email = email;
//        this.about = about;
        this.profileImageUri = profileImageUri;
        this.updatedAt = updatedAt;
        this.fileCMId = fileCMId;
    }

    public void setNameEmailAboutPhone(String name, String email, String about, String phoneNo, long updatedAt) {
        this.name = name;
        this.email = email;
//        this.about = about;
        this.phoneNo = phoneNo;

        this.isPhoneNumberVisible = false;
        this.isProfileImageVisible = false;
        this.updatedAt = updatedAt;
    }

    public void setOrganizationDesignation(String organization, String designation) {
        this.organization = organization;
        this.designation = designation;
    }


    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getUpdatedAtImage() {
        return updatedAtImage;
    }

    public void setUpdatedAtImage(long updatedAtImage) {
        this.updatedAtImage = updatedAtImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfileImageUri() {
        return profileImageUri;
    }

    public void setProfileImageUri(String profileImageUri) {
        this.profileImageUri = profileImageUri;
    }

//    public String getLocalReference() {
//        return localReference;
//    }
//
//    public void setLocalReference(String localReference) {
//        this.localReference = localReference;
//    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean getIsPhoneNumberVisible() {
        return isPhoneNumberVisible;
    }

    public void setIsPhoneNumberVisible(boolean phoneNumberVisible) {
        isPhoneNumberVisible = phoneNumberVisible;
    }

    public boolean getIsProfileImageVisible() {
        return isProfileImageVisible;
    }

    public void setIsProfileImageVisible(boolean profileImageVisible) {
        isProfileImageVisible = profileImageVisible;
    }

    public String getFileCMId() {
        return fileCMId;
    }

    public void setFileCMId(String fileCMId) {
        this.fileCMId = fileCMId;
    }


    @Override
    public String toString() {
        String x = "name : " + this.name + " email : " + this.email;
        return x;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
