package pranavmahajan21.com.viewpagermaterial;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pranavmahajan21.com.viewpagermaterial.model.BasicInfo;
import pranavmahajan21.com.viewpagermaterial.model.Event;
import pranavmahajan21.com.viewpagermaterial.model.MenuItem;
import pranavmahajan21.com.viewpagermaterial.model.Profile;
import pranavmahajan21.com.viewpagermaterial.model.Session;
import pranavmahajan21.com.viewpagermaterial.util.Constants;

/**
 * Created by pranavmahajan21 on 11/17/17.
 */

public class MyApp extends Application {
    FirebaseUser loggedInUser;
    Profile loggedInUserProfile;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Gson gson;

    // TODO: 1/16/18 Rename this to eventsRecentlySearched
//    List<Event> events = new ArrayList<Event>(); // this contains recently searched as well as owned by me.

    Map<String, Event> events = new HashMap<String, Event>();
    List<BasicInfo> eventsCreatedByMeBIList = new ArrayList<BasicInfo>();

    //    List<Profile> profiles = new ArrayList<Profile>();
    Map<String, Profile> profiles = new HashMap<String, Profile>();

    public Map<String, Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Map<String, Profile> profiles) {
        this.profiles = profiles;

        String json = gson.toJson(profiles);
        editor.putString(Constants.PREF_PROFILES, json);
        editor.commit();
    }

    /*
        * EventDAO class manipulates tempEvent & UnusedEventLoginActivity class assigns it to currentEvent
        * */
    Event tempEvent;
    Event currentEvent;
//    boolean isPreReleaseSearch;

    Set<String> sessionsBookmarked = new HashSet<String>();

    //    List<Object> myEvents = new ArrayList<Object>();

    //    public static String ROOT = Environment.getExternalStorageDirectory()
//            .toString() + "/" + Constants.APP_NAME;
    public static String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String ROOT_APP = ROOT + "/" + Constants.APP_NAME;
    public static String ROOT_APP_PROFILE_IMAGES = ROOT + "/" + Constants.APP_NAME + "/" + Constants.PROFILE_FOLDER_ON_DEVICE;

    String eventPathInStorage;
    String eventContentPathInStorage;

    public static ChildEventListener chatRoomListener;
//    public static DatabaseReference chatMessageReference;

    public static ChildEventListener chatMessagesListener;

    Map<String, String> sponsorsPriorityMap = new HashMap<String, String>();
    Map<String, String> speakersPriorityMap = new HashMap<String, String>();

    private void initThings() {
        Log.i(Constants.APP_NAME, "MyApp    initThings()");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        gson = new Gson();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        loggedInUser = firebaseAuth.getCurrentUser();

        sponsorsPriorityMap.put("1", "Sponsors");
        sponsorsPriorityMap.put("2", "Co-Sponsors");
        sponsorsPriorityMap.put("3", "Shit Sponsors");
        sponsorsPriorityMap.put("4", "Useless Sponsors");
        sponsorsPriorityMap.put("5", "Others");

        speakersPriorityMap.put("1", "Hosts");
        speakersPriorityMap.put("2", "Lead Speakers");
        speakersPriorityMap.put("3", "Co Speakers");
        speakersPriorityMap.put("4", "Support Staff");
        speakersPriorityMap.put("5", "Others");
    }

    private void fetchPreferences() {
        Log.i(Constants.APP_NAME, "MyApp    fetchPreferences()");

        if (sharedPreferences.contains(Constants.PREF_USER_PROFILE)) {
            Profile loggedInUserProfile = gson.fromJson(sharedPreferences.getString(
                    Constants.PREF_USER_PROFILE, null), Profile.class);
            setLoggedInUserProfile(loggedInUserProfile);
        }

        if (sharedPreferences.contains(Constants.PREF_EVENTS)) {
            Type listType = (Type) new TypeToken<Map<String, Event>>() {
            }.getType();
            Map<String, Event> events = gson.fromJson(sharedPreferences.getString(
                    Constants.PREF_EVENTS, null), listType);
            this.events = events;
        }

        if (sharedPreferences.contains(Constants.PREF_MY_EVENTS)) {
            Type listType = (Type) new TypeToken<ArrayList<BasicInfo>>() {
            }.getType();
            List<BasicInfo> myEvents = gson.fromJson(sharedPreferences.getString(
                    Constants.PREF_MY_EVENTS, null), listType);
            setEventsCreatedByMeBIList(myEvents);
        }

        if (sharedPreferences.contains(Constants.PREF_CURRENT_EVENT)) {
            Event event = gson.fromJson(sharedPreferences.getString(
                    Constants.PREF_CURRENT_EVENT, null), Event.class);
            setCurrentEvent(event, "MyApp");
        }

        if (sharedPreferences.contains(Constants.PREF_SESSION_BOOKMARKED)) {
            /* https://stackoverflow.com/a/14034804/2937847 */
            sessionsBookmarked = new HashSet<String>(sharedPreferences.getStringSet(Constants.PREF_SESSION_BOOKMARKED, new HashSet<String>()));
        }

        if (sharedPreferences.contains(Constants.PREF_PROFILES)) {
            Type listType = (Type) new TypeToken<Map<String, Profile>>() {
            }.getType();
            Map<String, Profile> profiles = gson.fromJson(sharedPreferences.getString(
                    Constants.PREF_PROFILES, null), listType);
            this.profiles = profiles;
//            setProfiles(profiles);
//            setEventsCreatedByMeBIList(myEvents);
        }


    }

    @Override
    public void onCreate() {
        super.onCreate();


        initThings();
        fetchPreferences();

        // TODO: 8/23/17 Also fetch all the Profiles from SQLite & assign the correct ones for the event

        /* https://stackoverflow.com/a/40674771/2937847
         * To resolve error while opening files from Content. Only in Nougat.
          * */
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }


    /*
    *   GETTERS & SETTERS
    * */
    /*public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;

        String json = gson.toJson(events);
        editor.putString(Constants.PREF_EVENTS, json);
        editor.commit();
    }*/

    public Map<String, Event> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Event> events) {
        this.events = events;

        String json = gson.toJson(events);
        editor.putString(Constants.PREF_EVENTS, json);
        editor.commit();
    }

    public void addEventToList(Event event) {
        if (event != null) {
            events.put(event.getAliasId(), event);
            setEvents(events);
        }
    }

    public void removeEventFromList(String eventId) {
        events.remove(eventId);
        setEvents(events);
    }

    /*public int isEventAlrealdyInList(String eventId) {
        *//* Check in recently searched events *//*
        if(events.containsKey(eventId))
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getAliasId().equalsIgnoreCase(eventId)) {
                    return i;
                }
            }

        return Constants.EVENT_NOT_IN_LIST;
    }*/

    public List<BasicInfo> getEventsCreatedByMeBIList() {
        return eventsCreatedByMeBIList;
    }

    public void setEventsCreatedByMeBIList(List<BasicInfo> eventsCreatedByMeBIList) {
        this.eventsCreatedByMeBIList = eventsCreatedByMeBIList;

        String json = gson.toJson(eventsCreatedByMeBIList);
        editor.putString(Constants.PREF_MY_EVENTS, json);
        editor.commit();
    }

    public Event getTempEvent() {
        return tempEvent;
    }

    public void setTempEvent(Event tempEvent) {
        this.tempEvent = tempEvent;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent, String fromWhere) {
        this.currentEvent = currentEvent;
        addEventToList(currentEvent);
//        System.out.println(fromWhere);

        String json = gson.toJson(currentEvent);
        editor.putString(Constants.PREF_CURRENT_EVENT, json);
        editor.commit();
    }

//    public boolean isPreReleaseSearch() {
//        return isPreReleaseSearch;
//    }
//
//    public void setPreReleaseSearch(boolean preReleaseSearch) {
//        isPreReleaseSearch = preReleaseSearch;
//        editor.putBoolean(Constants.PREF_PRE_RELEASE_SEARCH, preReleaseSearch);
//        editor.commit();
//    }


    public Set<String> getSessionsBookmarked() {
        return sessionsBookmarked;
    }

    public void setSessionsBookmarked(Set<String> sessionsBookmarked) {
        this.sessionsBookmarked = sessionsBookmarked;
        editor.putStringSet(Constants.PREF_SESSION_BOOKMARKED, sessionsBookmarked);
        editor.commit();
    }

    public FirebaseUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(FirebaseUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Profile getLoggedInUserProfile() {
        return loggedInUserProfile;
    }

    public Map<String, String> getSponsorsPriorityMap() {
        return sponsorsPriorityMap;
    }

    public void setSponsorsPriorityMap(Map<String, String> sponsorsPriorityMap) {
        this.sponsorsPriorityMap = sponsorsPriorityMap;
    }


    public Map<String, String> getSpeakersPriorityMap() {
        return speakersPriorityMap;
    }

    public void setSpeakersPriorityMap(Map<String, String> speakersPriorityMap) {
        this.speakersPriorityMap = speakersPriorityMap;
    }

    public void setLoggedInUserProfile(Profile loggedInUserProfile) {
        this.loggedInUserProfile = loggedInUserProfile;
        String json = gson.toJson(loggedInUserProfile);
        editor.putString(Constants.PREF_USER_PROFILE, json);
        editor.commit();
    }


    /* GLOBAL FUNCTIONS */
    public boolean amITheOwner() {
        if (currentEvent.getOwner().contains(loggedInUser.getEmail())) {
            return true;
        }
        return false;
    }

   /* public boolean amISpeaker() {
        List<Speaker> speakerList = currentEvent.getSpeakerList();
        for (Speaker tempSpeaker : speakerList) {
            if (tempSpeaker.getEmail().equalsIgnoreCase(getLoggedInUser().getEmail()))
                return true;
        }

        return false;
    }

    public boolean amISpeakerOfSesssion(Session session, String sessionId) {
        if (!amISpeaker()) {
            return false;
        }

        if (session == null) {
            session = findSessionById(sessionId);
        }
//        List<Speaker> speakerList = new ArrayList<Speaker>();
        List<String> sessionSpeakerIdList = session.getSpeakerList();
        for (String sessionSpeakerId : sessionSpeakerIdList) {

            if (getSpeakerById(sessionSpeakerId).getEmail().equalsIgnoreCase(getLoggedInUser().getEmail())) {
                return true;
            }
        }

        return false;
    }*/

    public boolean amIOwnerOrSpeaker() {
        return (amITheOwner());
    }

    public boolean amIAtendee() {
        return !amIOwnerOrSpeaker();
    }

    public Constants.Roles getMyRole() {
        if (amITheOwner()) {
            return Constants.Roles.OWNER;
        } else if (amITheOwner()) {
            return Constants.Roles.OWNER;
        } else if (amITheOwner()) {
            return Constants.Roles.OWNER;
        }

        return Constants.Roles.ATTENDEE;
    }

   /* public Speaker getSpeakerByEmail(String email) {
        List<Speaker> speakerList = currentEvent.getSpeakerList();
        for (Speaker tempSpeaker : speakerList) {
            if (tempSpeaker.getEmail().equalsIgnoreCase(email))
                return tempSpeaker;
        }
        return null;
    }

    public Speaker getSpeakerById(String id) {
        List<Speaker> speakerList = currentEvent.getSpeakerList();
        for (int i = 0; i < speakerList.size(); i++) {
            if (speakerList.get(i).getId().equalsIgnoreCase(id)) {
                return speakerList.get(i);
            }
        }
        return null;
    }

    public int getSpeakerIndexById(String id) {
        List<Speaker> speakerList = currentEvent.getSpeakerList();
        for (int i = 0; i < speakerList.size(); i++) {
            if (speakerList.get(i).getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    public int getQuestionIndexById(String id) {
        // TODO: 5/4/17 App may become more efficient if we maintain a Map instead of a List
        List<Question> questionList = currentEvent.getQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    public Question getQuestionById(String id) {
        // TODO: 5/4/17 App may become more efficient if we maintain a Map instead of a List
        List<Question> questionList = currentEvent.getQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getId().equalsIgnoreCase(id))
                return questionList.get(i);
        }
        return null;
    }

    public List<Question> getQuestionsOfSession(String sessionId) {
        List<Question> questionSessionList = new ArrayList<Question>();
        List<Question> questionList = currentEvent.getQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            if (sessionId.equalsIgnoreCase(questionList.get(i).getSessionId()))
                questionSessionList.add(questionList.get(i));
        }
        return questionSessionList;
    }

    public List<Content> getContentOfSession(String sessionId) {
        List<Content> contentSessionList = new ArrayList<Content>();
        List<Content> contentList = currentEvent.getContentList();
        for (int i = 0; i < contentList.size(); i++) {
            if (sessionId.equalsIgnoreCase(contentList.get(i).getSessionId()))
                contentSessionList.add(contentList.get(i));
        }
        return contentSessionList;
    }

    public void updateQuestionById(String questionId, Question updatedQuestion) {
        List<Question> questionList = currentEvent.getQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getId().equalsIgnoreCase(updatedQuestion.getId())) {
                questionList.set(i, updatedQuestion);
                currentEvent.setQuestionList(questionList);
                setCurrentEvent(currentEvent, null);
                return;
            }
        }
    }

    public List<Question> updateQuestionByIdInList(String questionId, Question updatedQuestion, List<Question> sessionQuestionList) {
        updateQuestionById(updatedQuestion.getId(), updatedQuestion);
        for (int i = 0; i < sessionQuestionList.size(); i++) {
            if (sessionQuestionList.get(i).getId().equalsIgnoreCase(updatedQuestion.getId())) {
                sessionQuestionList.set(i, updatedQuestion);
                continue;
            }
        }
        return sessionQuestionList;
    }

    public void updatePollAnswerById(String id, PollAnswer updatedPollAnswer) {
        List<PollAnswer> tempPollAnswerList = currentEvent.getPollAnswerList();
        for (int i = 0; i < tempPollAnswerList.size(); i++) {
            if (tempPollAnswerList.get(i).getId().equalsIgnoreCase(id)) {
                tempPollAnswerList.set(i, updatedPollAnswer);
                currentEvent.setPollAnswerList(tempPollAnswerList);
                setCurrentEvent(currentEvent, null);
                return;
            }
        }
    }

    public PollAnswer findPollAnswerById(String id) {
        List<PollAnswer> tempPollAnswerList = currentEvent.getPollAnswerList();
        for (int i = 0; i < tempPollAnswerList.size(); i++) {
            if (tempPollAnswerList.get(i).getId().equalsIgnoreCase(id)) {
                return tempPollAnswerList.get(i);
            }
        }
        return null;
    }

    public void updatePollById(String id, Poll updatedPoll) {
        List<Poll> tempPollList = currentEvent.getPollList();
        for (int i = 0; i < tempPollList.size(); i++) {
            if (tempPollList.get(i).getId().equalsIgnoreCase(id)) {
                tempPollList.set(i, updatedPoll);
                currentEvent.setPollList(tempPollList);
                setCurrentEvent(currentEvent, null);
                return;
            }
        }
    }

    public Poll findPollById(String id) {
        List<Poll> tempPollList = currentEvent.getPollList();
        for (int i = 0; i < tempPollList.size(); i++) {
            if (tempPollList.get(i).getId().equalsIgnoreCase(id)) {
                return tempPollList.get(i);
            }
        }
        return null;
    }*/

    public Profile findSpeakerProfileByEmail(String email) {
        List<Profile> profileList = currentEvent.getSpeakersProfiles();
        for (Profile tempProfile : profileList) {
            if (tempProfile.getEmail().equalsIgnoreCase(email))
                return tempProfile;
        }
        return null;
    }

    public Profile findAttendeeProfileByEmail(String email) {
        if (profiles.containsKey(email)) {
            return profiles.get(email);
        }
//        List<Profile> profileList = currentEvent.getAttendeesProfiles();
//        for (Profile tempProfile : profileList) {
//            if (tempProfile.getEmail().equalsIgnoreCase(email))
//                return tempProfile;
//        }
        return null;

    }

    public Profile findProfileByEmail(String email) {
        /* Search both Speakers & Attendees for a profile */
        // TODO: 12/4/16 Search in OwnerList as well
        Profile tempProfile = findAttendeeProfileByEmail(email);
        if (tempProfile != null) {
            return tempProfile;
        } else {
            tempProfile = findSpeakerProfileByEmail(email);
            if (tempProfile != null) {
                return tempProfile;
            }
        }
        return null;

    }

    public int findSessionIndexById(String id) {
        List<Session> sessionList = currentEvent.getSessionList();
        for (int i = 0; i < sessionList.size(); i++) {
            if (sessionList.get(i).getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    public Session findSessionById(String id) {
        int i = findSessionIndexById(id);
        return currentEvent.getSessionList().get(i);
    }

    public String convertEmailToPath(String email) {
//        https://stackoverflow.com/questions/31904123/good-way-to-replace-invalid-characters-in-firebase-keys
//        String emailPath = email.replace(".", "_");
        String emailPath = email.replaceAll("\\.", ",");

        return emailPath;
    }

    public String convertPathToEmail(String email) {
//        String emailPath = email.replace("_", ".");
        String emailPath = email.replaceAll(",", ".");
        return emailPath;
    }


    public Event findEventById(String eventId) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).equals(eventId)) {
                return events.get(i);
            }
        }

        return null;
    }

    public void logoutFromApp() {
        setCurrentEvent(null, null);
        getEvents().clear();
        getEventsCreatedByMeBIList().clear();
//        isPreReleaseSearch = false;
        setLoggedInUser(null);
        setLoggedInUserProfile(null);

        editor.clear();
        editor.commit();

        FirebaseAuth.getInstance().signOut();

//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(@NonNull Status status) {
//                        updateUI(null);
//                    }
//                });


        Intent nextIntent = new Intent(this, LoginActivity.class);
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //        nextIntent.putExtra(Constants.EXTRAS_SHOULD_LOGOUT, true);
        startActivity(nextIntent);
    }

    public String getSessionNameFromId(String sessionId) {
        for (int i = 0; i < currentEvent.getSessionList().size(); i++) {
            if (currentEvent.getSessionList().get(i).getId().equalsIgnoreCase(sessionId)) {
                return currentEvent.getSessionList().get(i).getName();
            }
        }
        return null;
    }

//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null)
//            return;
//
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
//        int totalHeight = 0;
//        View view = null;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            view = listAdapter.getView(i, view, listView);
//            if (i == 0)
//                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//            totalHeight += view.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//    }

    /*public Speaker findSpeakerById(String speakerId) {
        for (int i = 0; i < currentEvent.getSpeakerList().size(); i++) {
            if (currentEvent.getSpeakerList().get(i).getId().equalsIgnoreCase(speakerId)) {
                return currentEvent.getSpeakerList().get(i);
            }
        }
        return null;
    }*/

    public MenuItem findMenuItemByRealName(String realName) {
        for (int i = 0; i < currentEvent.getMenuItems().size(); i++) {
            if (currentEvent.getMenuItems().get(i).getRealName().equalsIgnoreCase(realName)) {
                return currentEvent.getMenuItems().get(i);
            }
        }
        return null;
    }

    /*public ContentMandatory findContentMandatoryById(String contentMandatoryId) {
        for (int i = 0; i < currentEvent.getContentMandatoryList().size(); i++) {
            if (currentEvent.getContentMandatoryList().get(i).getId().equalsIgnoreCase(contentMandatoryId)) {
                return currentEvent.getContentMandatoryList().get(i);
            }
        }
        return null;
    }

    public String findLoggedInUserRoleForThisEvent() {
        if (amITheOwner()) {
            return Constants.ROLE_OWNER;
        } else if (amISpeaker()) {
            return Constants.ROLE_SPEAKER;
        }
        return Constants.ROLE_NEITHER_OWNER_NOR_SPEAKER;
    }*/

    public boolean prefContainsAndValue(String preferenceKey, Map<String, Object> mapToBeSearchedIn) {
        if (mapToBeSearchedIn == null) {
            mapToBeSearchedIn = currentEvent.getPrefs();
        }
        if (mapToBeSearchedIn.containsKey(preferenceKey) && (boolean) mapToBeSearchedIn.get(preferenceKey)) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Object> getPrefSplash() {
        return (Map<String, Object>) currentEvent.getPrefs().get(Constants.EVENT_PREF_MAP_SPLASH);
    }

    public Map<String, Object> getPrefSponsor() {
        return (Map<String, Object>) currentEvent.getPrefs().get(Constants.EVENT_PREF_MAP_SPONSORS);
    }

    public Map<String, Object> getPrefMenu() {
        return (Map<String, Object>) currentEvent.getPrefs().get(Constants.EVENT_PREF_MAP_MENU);
    }

    public Map<String, Object> getPrefCustomLogo() {
        return (Map<String, Object>) currentEvent.getPrefs().get(Constants.EVENT_PREF_MAP_CUSTOM_LOGO);
    }

    public Map<String, Object> getPrefVenue() {
        return (Map<String, Object>) currentEvent.getPrefs().get(Constants.EVENT_PREF_MAP_VENUE);
    }

    /*public int getDrawableForFileType(String filename) {
        if (filename.contains(".doc") || filename.contains(".docx")) {
            // Word document
            return R.drawable.content_doc;
        } else if (filename.contains(".pdf")) {
            // PDF file
            return R.drawable.content_pdf;
        } else if (filename.contains(".ppt")
                || filename.contains(".pptx")) {
            // Powerpoint file
            return R.drawable.content_ppt;
        } else if (filename.contains(".xls")
                || filename.contains(".xlsx")) {
            // Excel file
            return R.drawable.content_excel;
        } else if (filename.contains(".zip")
                || filename.contains(".rar")) {
            // ZIP files
            return R.drawable.content_rar;
        } else if (filename.contains(".rtf")) {
            // RTF file
            return R.drawable.content_rtf;
        } else if (filename.contains(".wav")
                || filename.contains(".mp3")) {
            // WAV audio file
            return R.drawable.content_music;
        } else if (filename.contains(".gif")) {
            // GIF file
            return R.drawable.content_gif;
        } else if (filename.contains(".jpg")
                || filename.contains(".jpeg")
                || filename.contains(".png")) {
            // JPG file
            return R.drawable.content_png;
        } else if (filename.contains(".txt")) {
            // Text file
            return R.drawable.content_text;
        } else if (filename.contains(".3gp")
                || filename.contains(".mpg")
                || filename.contains(".mpeg")
                || filename.contains(".mpe")
                || filename.contains(".mp4")
                || filename.contains(".avi")) {
            // Video files
            return R.drawable.content_video;
        } else {
            return R.drawable.content_unknown_file;
        }
    }*/


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
//        MultiDex.install(this);
    }

    public boolean isETEmpty(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            return true;
        }
        return false;
    }
}