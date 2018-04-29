package pranavmahajan21.com.viewpagermaterial.dao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pranavmahajan21.com.viewpagermaterial.EventLoginTabActivity;
import pranavmahajan21.com.viewpagermaterial.MenuActivity2;
import pranavmahajan21.com.viewpagermaterial.MyApp;
import pranavmahajan21.com.viewpagermaterial.model.BasicInfo;
import pranavmahajan21.com.viewpagermaterial.model.Event;
import pranavmahajan21.com.viewpagermaterial.model.Session;
import pranavmahajan21.com.viewpagermaterial.util.Constants;

public class EventDAO {
    String className = "EventDAO      ";

    DatabaseReference mDatabase;
    Context context;
    MyApp myApp;

    Gson gson;

//    static int noOfMandatoryThingsToFind = Constants.NO_OF_MANDATORY_THINGS_TO_FIND;

    public EventDAO(Context context, DatabaseReference mDatabase) {
        /*
        * OPTIMIZATION(5): We need Context to send Broadcast. We could do this in a service class as well.
        * */
        this.context = context;
        this.mDatabase = mDatabase;

        myApp = (MyApp) this.context.getApplicationContext();
        gson = new Gson();
    }

    private static int BASIC_INFO_COUNTER = 0;
    List<BasicInfo> basicInfoList;

    public void onFindEventBasicInfoById(String eventID) {
        final String functionName = "       onFindEventBasicInfoById()      " + eventID;
        Log.i(Constants.APP_NAME, className + functionName);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(Constants.APP_NAME, className + functionName + "onDataChange()");

                BasicInfo basicInfo = dataSnapshot.getValue(BasicInfo.class);
                afterBasicInfoFound(basicInfo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(Constants.APP_NAME, className + functionName + "onCancelled()");
                afterBasicInfoFound(null);
            }
        };
        DatabaseReference eventReference = mDatabase.child(Constants.TABLE_EVENT_WEB).child(eventID).child(Constants.TABLE_BASIC_INFO);
        eventReference.addListenerForSingleValueEvent(eventListener);
    }

    private void afterBasicInfoFound(BasicInfo basicInfo) {
        BASIC_INFO_COUNTER--;

        if (basicInfo != null) {
            basicInfoList.add(basicInfo);
        }
        if (BASIC_INFO_COUNTER == 0) {
            Log.i(Constants.APP_NAME, className + "         ALL BasicInfo FOUND");
            String basicInfoListString = gson.toJson(basicInfoList);

            Intent nextIntent = new Intent(Constants.RECEIVER_BASIC_INFO_LIST);
            nextIntent.putExtra(Constants.EXTRAS_BASIC_INFO_LIST, basicInfoListString);

            LocalBroadcastManager.getInstance(context).sendBroadcast(nextIntent);
        }

    }

    public void findEventWithAliasName(String aliasName) {
        Log.i(Constants.APP_NAME, className + "       findEventWithAliasName()");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String realEventId = (String) dataSnapshot.getValue();
                if (realEventId != null) {

                    onFindEventById(realEventId, false, false);
                } else {
                    afterEventFound(null, false, false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(Constants.APP_NAME, className + "       findEventWithAliasName()      databaseError");
                afterEventFound(null, false, false);
            }
        };
        DatabaseReference eventReference = mDatabase.child(Constants.TABLE_EVENT_ALIAS).child(aliasName);
        eventReference.addListenerForSingleValueEvent(eventListener);
    }

    public void onFindEventById(final String id, final boolean isEventBeingUpdated, final boolean isPreReleaseSearch) {
        Log.i(Constants.APP_NAME, className + "onFindEventById()   " + id);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(Constants.APP_NAME, className + "onFindEventById()         onDataChange()");

                Event event = dataSnapshot.getValue(Event.class);
                if (event != null) {
                    event.setId(dataSnapshot.getKey());


                   /* DataSnapshot contactUsDataSnapshot = dataSnapshot.child(Constants.TABLE_CONTACT_US);
                    List<ContactUs> contactUsList = new ArrayList<ContactUs>();

                    for (DataSnapshot childSnapshot : contactUsDataSnapshot.getChildren()) {
                        ContactUs contactUs = childSnapshot.getValue(ContactUs.class);
                        if (contactUs != null) {
                            contactUs.setId(childSnapshot.getKey());
                            contactUsList.add(contactUs);
                        } else {
                            Log.e(Constants.APP_NAME, className + "TABLE_CONTACT_US        THIS SHOULD NEVER BE REACHED");
                        }
                    }

                    DataSnapshot speakersDataSnapshot = dataSnapshot.child(Constants.TABLE_SPEAKER);
                    List<Speaker> speakerList = new ArrayList<Speaker>();

                    for (DataSnapshot childSnapshot : speakersDataSnapshot.getChildren()) {
                        Speaker speaker = childSnapshot.getValue(Speaker.class);
                        if (speaker != null) {
                            speaker.setId(childSnapshot.getKey());
                            speakerList.add(speaker);
                        } else {
                            Log.e(Constants.APP_NAME, className + "TABLE_SPEAKER        THIS SHOULD NEVER BE REACHED");
                        }
                    }*/

                    DataSnapshot sessionDataSnapshot = dataSnapshot.child(Constants.TABLE_SESSION);
                    List<Session> sessionList = new ArrayList<Session>();

                    for (DataSnapshot childSnapshot : sessionDataSnapshot.getChildren()) {
                        Session session = childSnapshot.getValue(Session.class);
                        if (session != null) {
                            session.setId(childSnapshot.getKey());
                            sessionList.add(session);
                        } else {
                            Log.e(Constants.APP_NAME, className + "TABLE_SESSION        THIS SHOULD NEVER BE REACHED");
                        }
                    }


                    /*DataSnapshot contentDataSnapshot = dataSnapshot.child(Constants.TABLE_CONTENTS);
                    List<Content> contentList = new ArrayList<Content>();

                    for (DataSnapshot childSnapshot : contentDataSnapshot.getChildren()) {
                        Content about = childSnapshot.getValue(Content.class);
                        if (about != null) {
                            about.setId(childSnapshot.getKey());
                            contentList.add(about);
                        } else {
                            Log.e(Constants.APP_NAME, className + "TABLE_CONTENTS        THIS SHOULD NEVER BE REACHED");
                        }
                    }

                    DataSnapshot contentMandatoryDataSnapshot = dataSnapshot.child(Constants.TABLE_CONTENTS_MANDATORY);
                    List<ContentMandatory> contentMandatoryList = new ArrayList<ContentMandatory>();

                    for (DataSnapshot childSnapshot : contentMandatoryDataSnapshot.getChildren()) {
                        ContentMandatory contentMandatory = childSnapshot.getValue(ContentMandatory.class);
                        if (contentMandatory != null) {
                            contentMandatory.setId(childSnapshot.getKey());
                            contentMandatoryList.add(contentMandatory);
                        } else {
                            Log.e(Constants.APP_NAME, className + "TABLE_CONTENTS_MANDATORY        THIS SHOULD NEVER BE REACHED");
                        }
                    }

                    DataSnapshot pollDataSnapshot = dataSnapshot.child(Constants.TABLE_POLL);
                    List<Poll> pollList = new ArrayList<Poll>();

                    for (DataSnapshot childSnapshot : pollDataSnapshot.getChildren()) {
                        Poll poll = childSnapshot.getValue(Poll.class);
                        if (poll != null) {
                            poll.setId(childSnapshot.getKey());
                            pollList.add(poll);
                        } else {
                            Log.e(Constants.APP_NAME, className + "TABLE_POLL         THIS SHOULD NEVER BE REACHED");
                        }
                    }

                    DataSnapshot sponsorDataSnapshot = dataSnapshot.child(Constants.TABLE_SPONSOR);
                    List<Sponsor> sponsorList = new ArrayList<Sponsor>();

                    for (DataSnapshot childSnapshot : sponsorDataSnapshot.getChildren()) {
                        Sponsor sponsor = childSnapshot.getValue(Sponsor.class);
                        if (sponsor != null) {
                            sponsor.setId(childSnapshot.getKey());
                            sponsorList.add(sponsor);
                        } else {
                            Log.e(Constants.APP_NAME, className + "SPONSORS           THIS SHOULD NEVER BE REACHED");
                        }
                    }

                    DataSnapshot sponsorTypeDataSnapshot = dataSnapshot.child(Constants.TABLE_SPONSOR_TYPE);
                    List<SponsorType> sponsorTypeList = new ArrayList<SponsorType>();

                    for (DataSnapshot childSnapshot : sponsorTypeDataSnapshot.getChildren()) {
                        SponsorType sponsorType = childSnapshot.getValue(SponsorType.class);
                        if (sponsorType != null) {
                            sponsorType.setId(childSnapshot.getKey());
                            sponsorTypeList.add(sponsorType);
                        } else {
                            Log.e(Constants.APP_NAME, className + "SPONSORS TYPE           THIS SHOULD NEVER BE REACHED");
                        }
                    }*/

                    DataSnapshot prefDataSnapshot = dataSnapshot.child(Constants.TABLE_PREFERENCES);
                    Map<String, Object> prefs = new LinkedHashMap<String, Object>();
                    for (DataSnapshot childSnapshot : prefDataSnapshot.getChildren()) {
//                    Log.i(Constants.APP_NAME, className + "     pref : " + childSnapshot.getKey() + "    " + childSnapshot.getValue());
                        prefs.put(childSnapshot.getKey(), childSnapshot.getValue());
                    }

//                    List<SponsorType> sponsorTypeList = new ArrayList<>();
                    /*if (((Map<String, Object>)prefs.get(Constants.EVENT_PREF_MAP_SPONSORS)).containsKey(Constants.EVENT_PREF_SPONSORS_PRIORITY_ORDER)) {
                        Map<String, Object> sponsors_priority_order = (Map<String, Object>) ((Map<String, Object>)prefs.get(Constants.EVENT_PREF_MAP_SPONSORS)).get(Constants.EVENT_PREF_SPONSORS_PRIORITY_ORDER);
                        List<String> sponsorTypeKeys = new ArrayList<>(sponsors_priority_order.keySet());

                        for (int i = 0; i < sponsorTypeKeys.size(); i++) {
                            Map<String, Object> sponsorTypeObjectMap = (Map<String, Object>) sponsors_priority_order.get(sponsorTypeKeys.get(i));
                            SponsorType sponsorType = new SponsorType((String) sponsorTypeObjectMap.get("text"), (long)sponsorTypeObjectMap.get("position"));
                            sponsorType.setId(sponsorTypeKeys.get(i));
                            sponsorTypeList.add(sponsorType);
                        }
                    }*/


                    /*Collections.sort(contentList, new Comparator<Content>() {
                        public int compare(Content element1, Content element2) {
                            return element1.getPosition() - element2.getPosition();
                        }
                    });

                    Collections.sort(contactUsList, new Comparator<ContactUs>() {
                        public int compare(ContactUs element1, ContactUs element2) {
                            return element1.getPosition() - element2.getPosition();
                        }
                    });

                    Collections.sort(pollList, new Comparator<Poll>() {
                        public int compare(Poll element1, Poll element2) {
                            return element1.getPosition() - element2.getPosition();
                        }
                    });

                    Collections.sort(sponsorList, new Comparator<Sponsor>() {
                        public int compare(Sponsor element1, Sponsor element2) {
                            return element1.getPosition() - element2.getPosition();
                        }
                    });

                    Collections.sort(sponsorTypeList, new Comparator<SponsorType>() {
                        public int compare(SponsorType element1, SponsorType element2) {
                            return (int) (element1.getPosition() - element2.getPosition());
                        }
                    });*/

                    Collections.sort(sessionList, new Comparator<Session>() {
                        public int compare(Session element1, Session element2) {
                            return element1.getPosition() - element2.getPosition();
                        }
                    });

                    event.setPrefs(prefs);
                    /*event.setContentList(contentList);
                    event.setContentMandatoryList(contentMandatoryList);
                    event.setContactUsList(contactUsList);
                    event.setPollList(pollList);
                    event.setSponsorList(sponsorList);
                    event.setSponsorTypeList(sponsorTypeList);
                    event.setSpeakerList(speakerList);*/
                    event.setSessionList(sessionList);

                    event.setPreReleaseTest(isPreReleaseSearch);
                    afterEventFound(event, isEventBeingUpdated, isPreReleaseSearch);
                } else {
                    Log.w(Constants.APP_NAME, className + "onFindEventById()        WARNING. SOMETHING IS REALLY WRONG");
                    afterEventFound(null, false, isPreReleaseSearch);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                afterEventFound(null, false, isPreReleaseSearch);
            }
        };
        DatabaseReference eventReference;

        if (isPreReleaseSearch) {
            Log.i(Constants.APP_NAME, className + "onFindEventById()        THIS is a PRE-RELEASE search");
            eventReference = mDatabase.child(Constants.TABLE_EVENT_WEB).child(id);
        } else {
            eventReference = mDatabase.child(Constants.TABLE_EVENT_APP).child(id);
        }
        eventReference.addListenerForSingleValueEvent(eventListener);

    }

    private void afterEventFound(Event event, boolean isEventBeingUpdated, boolean isPreReleaseSearch) {
        if (event != null) {
            onFindEventUserData(event, isEventBeingUpdated, isPreReleaseSearch);
        } else {

            Intent nextIntent = null;

            Activity activity = (Activity) context;

            if (activity instanceof EventLoginTabActivity) {
                nextIntent = new Intent(Constants.RECEIVER_EVENT);
            } else if (activity instanceof MenuActivity2) {
                nextIntent = new Intent(Constants.RECEIVER_EVENT_DATA);
            }

//            if (event != null) {
//                nextIntent.putExtra(Constants.EXTRAS_IS_EVENT_FOUND, true);
//                nextIntent.putExtra(Constants.EXTRAS_LATEST_EVENT, gson.toJson(event));
//                nextIntent.putExtra(Constants.EXTRAS_IS_EVENT_BEING_UPDATED, isEventBeingUpdated);
//                nextIntent.putExtra(Constants.EXTRAS_IS_PRE_RELEASE_SEARCH, isPreReleaseSearch);
//            } else {
//                nextIntent.putExtra(Constants.EXTRAS_IS_EVENT_FOUND, false);
//            }

            LocalBroadcastManager.getInstance(context).sendBroadcast(nextIntent);
        }
    }


    public void onFindEventUserData(final Event event, final boolean isEventBeingUpdated, final boolean isPreReleaseSearch) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(Constants.APP_NAME, className + "       onFindEventUserData()         onDataChange()");

               /* DataSnapshot questionsDataSnapshot = dataSnapshot.child(Constants.TABLE_QUESTIONS);
                List<Question> questionList = new ArrayList<Question>();
                for (DataSnapshot childSnapshot : questionsDataSnapshot.getChildren()) {
                    Question question = childSnapshot.getValue(Question.class);
                    if (question != null) {
                        question.setId(childSnapshot.getKey());


                        questionList.add(question);
                    } else {
                        Log.i(Constants.APP_NAME, className + "TABLE_QUESTIONS        THIS SHOULD NEVER BE REACHED");
                    }
                }

                DataSnapshot questionAnswerParentDS = dataSnapshot.child(Constants.TABLE_QUESTION_ANSWERS);
                for (DataSnapshot questionDS : questionAnswerParentDS.getChildren()) {
                    List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();
                    for (DataSnapshot questionAnswerDS : questionDS.getChildren()) {
                        QuestionAnswer questionAnswer = questionAnswerDS.getValue(QuestionAnswer.class);
                        if (questionAnswer != null) {
                            questionAnswer.setId(questionDS.getKey());
                            questionAnswerList.add(questionAnswer);
                        } else {
                            Log.i(Constants.APP_NAME, className + "TABLE_QUESTION_ANSWERS        THIS SHOULD NEVER BE REACHED");
                        }
                    }

                    String questionId = questionDS.getKey();
                    for (Question question : questionList) {
                        if (question.getId().equalsIgnoreCase(questionId)) {
                            question.setQuestionAnswers(questionAnswerList);
                            continue;
                        }
                    }
                }


                DataSnapshot pollAnswerDataSnapshot = dataSnapshot.child(Constants.TABLE_POLL_ANSWERS);
                List<PollAnswer> pollAnswerList = new ArrayList<PollAnswer>();

                for (DataSnapshot childSnapshot : pollAnswerDataSnapshot.getChildren()) {
                    PollAnswer pollAnswer = childSnapshot.getValue(PollAnswer.class);
                    if (pollAnswer != null) {
                        pollAnswer.setId(childSnapshot.getKey());
                        pollAnswerList.add(pollAnswer);
                    } else {
                        Log.i(Constants.APP_NAME, className + "TABLE_POLL_ANSWERS        THIS SHOULD NEVER BE REACHED");
                    }
                }

                DataSnapshot invitesDataSnapshot = dataSnapshot.child(Constants.TABLE_ATTENDEES);
                Map<String, Integer> attendeesMap = new HashMap<String, Integer>();

                for (DataSnapshot childSnapshot : invitesDataSnapshot.getChildren()) {
                    Invite invite = childSnapshot.getValue(Invite.class);
                    invite.setEmail(childSnapshot.getKey());
                    attendeesMap.put(childSnapshot.getKey(), invite.getIsAttending());
                }

                Feedback feedback = null;
                DataSnapshot feedbackListDS = dataSnapshot.child(Constants.TABLE_FEEDBACK);
                if (feedbackListDS.hasChild(myApp.getLoggedInUser().getUid())) {
                    DataSnapshot myFeedbackDS = feedbackListDS.child(myApp.getLoggedInUser().getUid());
                    feedback = myFeedbackDS.getValue(Feedback.class);
                }*/

                String key = Constants.TABLE_EVENT_USER_D_UPDATED_AT;
                Object o = dataSnapshot.child(key).getValue();
                long userDataUpdatedAt = 0;
                if (o != null) {
                    userDataUpdatedAt = (long) dataSnapshot.child(key).getValue();
                }

                event.setUserDataUpdatedAt(userDataUpdatedAt);
               /* event.setAttendeesMap(attendeesMap);
                event.setQuestionList(questionList);
                event.setPollAnswerList(pollAnswerList);
                event.setLoggedInUserFeedback(feedback);*/

                if (event != null) {
                    afterEventUserDataFound(event, isEventBeingUpdated, isPreReleaseSearch);
                } else {
                    // TODO: 8/11/17 This is wrong. It should be afterEventFound(null);
                    Log.w(Constants.APP_NAME, className + "         onFindEventById()        WARNING. SOMETHING IS REALLY WRONG");
                    afterEventUserDataFound(null, isEventBeingUpdated, isPreReleaseSearch);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                afterEventUserDataFound(null, isEventBeingUpdated, isPreReleaseSearch);
            }
        };
        DatabaseReference eventReference = mDatabase.child(Constants.TABLE_EVENT_USER_DATA).child(event.getId());
        eventReference.addListenerForSingleValueEvent(eventListener);
    }

    private void afterEventUserDataFound(Event event, boolean isEventBeingUpdated, final boolean isPreReleaseSearch) {
        Log.i(Constants.APP_NAME, className + "afterEventUserDataFound()");
        Intent nextIntent = null;

        Activity activity = (Activity) context;
        if ( activity instanceof EventLoginTabActivity) {
            nextIntent = new Intent(Constants.RECEIVER_EVENT);
        } else if (activity instanceof MenuActivity2) {
//            nextIntent = new Intent(Constants.RECEIVER_EVENT_USER_DATA);
            nextIntent = new Intent(Constants.RECEIVER_EVENT_DATA);
        }


        if (event != null) {
            nextIntent.putExtra(Constants.EXTRAS_IS_EVENT_FOUND, true);
            nextIntent.putExtra(Constants.EXTRAS_LATEST_EVENT, gson.toJson(event));
            nextIntent.putExtra(Constants.EXTRAS_IS_EVENT_BEING_UPDATED, isEventBeingUpdated);
//            nextIntent.putExtra(Constants.EXTRAS_IS_PRE_RELEASE_SEARCH, isPreReleaseSearch);
        } else {
            nextIntent.putExtra(Constants.EXTRAS_IS_EVENT_FOUND, false);
        }

        LocalBroadcastManager.getInstance(context).sendBroadcast(nextIntent);
    }


    public void findAllMyEvents() {
        Log.i(Constants.APP_NAME, className + "       findAllMyEvents()");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /* Make an array of all My Event Ids */
                List<String> myEventIds = new ArrayList<String>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    System.out.println(postSnapshot);
                    myEventIds.add(postSnapshot.getValue(String.class));
                }
                System.out.println(myEventIds.size() + "");

                if (myEventIds.size() > 0) {
                    Log.i(Constants.APP_NAME, className + "       findAllMyEvents()  :  " + myEventIds.size());

                    /* Remove all IDs that are already there in my local list */
                    List<BasicInfo> myEventBIList = myApp.getEventsCreatedByMeBIList();
                    for (BasicInfo myEvent : myEventBIList) {
                        myEventIds.remove(myEvent.getEventId());
                    }
                    Log.i(Constants.APP_NAME, className + "       findAllMyEvents()  filtered  :  " + myEventIds.size());
                }

                BASIC_INFO_COUNTER = myEventIds.size();
                basicInfoList = new ArrayList<BasicInfo>();
                for (String myEventId : myEventIds) {
                    onFindEventBasicInfoById(myEventId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(Constants.APP_NAME, className + "       findAllMyEvents()      databaseError");
            }
        };
        DatabaseReference eventReference = mDatabase.child(Constants.TABLE_EVENT_LIST).child(myApp.getLoggedInUser().getUid());
        eventReference.addListenerForSingleValueEvent(eventListener);
    }


    public void downloadProfileImagesOfInvites() {
        Log.i(Constants.APP_NAME, className + "      downloadProfileImagesOfInvites()  : ");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(Constants.BUCKET_NAME);

//        Set<String> set = myApp.getCurrentEvent().getAttendeesMap().keySet();
        final List<String> keysList = new ArrayList<String>(myApp.getCurrentEvent().getAttendeesMap().keySet());

        for (int i = 0; i < keysList.size(); i++) {
            final String emailString = myApp.convertPathToEmail(keysList.get(i));
            StorageReference profileImageRef = storageRef.child(myApp.convertPathToEmail(keysList.get(i)) + ".jpg");
            System.out.println("getPath  : " + profileImageRef.getPath());
            System.out.println("getDownloadUrl  : " + profileImageRef.getDownloadUrl());
            System.out.println("getName  : " + profileImageRef.getName());
            final long ONE_MEGABYTE = 1024 * 1024;
            final File myDir = new File(MyApp.ROOT_APP);
//            File file = new File(myDir, emailString + ".jpg");
//            if (!file.exists()) {
//                Log.i(Constants.APP_NAME, className + "      downloadProfileImagesOfInvites()  Profile Img already exists : " + emailString + ".jpg");
//            }else{
            profileImageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("got bytes");

                    File file = new File(myDir, emailString + ".jpg");
                    if (!myDir.exists()) {
                        myDir.mkdir();
                    }
//                    File file = new File(myDir, "asdf.jpg");
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(file);
                        out.write(bytes);
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//            File file = new File(myDir, filename);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    System.out.println("Error error error in getting bytes: " + exception.getMessage());
                }
            });
//            }

        }

    }

    private void minCounter() {
        Intent nextIntent = new Intent(Constants.RECEIVER_EVENT_DATA);
        LocalBroadcastManager.getInstance(context).sendBroadcast(nextIntent);
    }



    public void lastUserDataUpdatedTime(String eventId) {
        final Intent nextIntent = new Intent(Constants.RECEIVER_USER_D_UPDATED_AT);
        Long l = null;
        nextIntent.putExtra(Constants.EXTRAS_USER_D_UPDATED_AT, l);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object o = dataSnapshot.getValue();
                if (o != null) {
                    nextIntent.putExtra(Constants.EXTRAS_USER_D_UPDATED_AT, (long)o);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(nextIntent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(Constants.APP_NAME, className + "FAILURE reading hgjhgj    ", databaseError.toException());
                LocalBroadcastManager.getInstance(context).sendBroadcast(nextIntent);
            }
        };
        DatabaseReference eventReference = mDatabase.child(Constants.TABLE_EVENT_USER_DATA).child(eventId).child(Constants.TABLE_EVENT_USER_D_UPDATED_AT);
        eventReference.addListenerForSingleValueEvent(eventListener);
    }
}
