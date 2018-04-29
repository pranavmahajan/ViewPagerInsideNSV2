package pranavmahajan21.com.viewpagermaterial;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import pranavmahajan21.com.viewpagermaterial.dao.EventDAO;
import pranavmahajan21.com.viewpagermaterial.model.BasicInfo;
import pranavmahajan21.com.viewpagermaterial.model.Event;
import pranavmahajan21.com.viewpagermaterial.util.Constants;

public class EventLoginTabActivity extends DaddyActivity {

    String className = "EventLoginTabActivity      ";
    EditText eventId_ET;
    EventDAO eventDAO;

    Intent previousIntent, nextIntent;

    Event tempEvent;

    List<String> listDataHeader;
    LinkedHashMap<String, List<BasicInfo>> listDataChild;

    public List<BasicInfo> listInAction;
    public String headerType;

//    private TabLayout tabLayout;
//    private ViewPager viewPager;

    private BroadcastReceiver eventReceiver = new BroadcastReceiver() {
        String receiverName = "eventReceiver        ";

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getBooleanExtra(Constants.EXTRAS_IS_EVENT_FOUND, false)) {
                Log.i(Constants.APP_NAME, className + receiverName + "Event FOUND");
                /* The event has been found. Now find all the remaining data for the event" +
                        " IF 1. You're the owner of the event OR 2. The event is public OR
                        3. You're a speaker for the event OR 4. You've been invited for the event */
                tempEvent = gson.fromJson(intent.getStringExtra(Constants.EXTRAS_LATEST_EVENT), Event.class);
//                boolean isPreReleaseSearch = intent.getBooleanExtra(Constants.EXTRAS_IS_PRE_RELEASE_SEARCH, false);
                startNextActivity(true);

                /*if (tempEvent.isLoggedInUserOwner()) {
                    Log.i(Constants.APP_NAME, className + receiverName + "Event Login SUCCESSFUL as OWNER");
                    startNextActivity(true);
                    return;
                    // TODO: 16/9/16 Test return; properly
                }

                if (tempEvent.getBasicInfo().isEventPublic()) {
                    Log.i(Constants.APP_NAME, className + receiverName + "Event Login SUCCESSFUL as PUBLIC event");
                    startNextActivity(true);
                } else {

                    if (tempEvent.getAttendeesMap().containsKey(myApp.convertEmailToPath(myApp.getLoggedInUser().getEmail()))) {
                        Log.i(Constants.APP_NAME, className + receiverName + "Event Login SUCCESSFUL as ATTENDEE");
                        startNextActivity(true);
                    } else {
                        for (Speaker tempSpeaker : tempEvent.getSpeakerList()) {
                            if (tempSpeaker.getEmail().equalsIgnoreCase(myApp.getLoggedInUserProfile().getEmail())) {
                                Log.i(Constants.APP_NAME, className + receiverName + "Event Login SUCCESSFUL as SPEAKER");
                                startNextActivity(true);
                                return;
                            }
                        }
                        Log.i(Constants.APP_NAME, className + receiverName + "Event Login FAILED");
                        progressDialog.dismiss();
                        Toast.makeText(EventLoginTabActivity.this, "You are not invited for this Event", Toast.LENGTH_SHORT).show();

                    }
                }*/

            } else {
                progressDialog.dismiss();
                Log.i(Constants.APP_NAME, className + receiverName + "Event NOT Found");
                Toast.makeText(EventLoginTabActivity.this, "Event Not Found", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private BroadcastReceiver basicInfoListReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(Constants.APP_NAME, className + "basicInfoListRECEIVER");

            String basicInfoListString = intent.getStringExtra(Constants.EXTRAS_BASIC_INFO_LIST);

            Type listType = (Type) new TypeToken<ArrayList<BasicInfo>>() {
            }.getType();
            List<BasicInfo> basicInfoList = gson.fromJson(basicInfoListString, listType);

            myApp.getEventsCreatedByMeBIList().addAll(basicInfoList);
            myApp.setEventsCreatedByMeBIList(myApp.getEventsCreatedByMeBIList());

            prepareContentsForList("basicInfoListReceiver");
            initView();
        }
    };

    /*private BroadcastReceiver myEventActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(Constants.APP_NAME, className + "myEventActionReceiver ");

            final String eventId = intent.getStringExtra(Constants.EXTRAS_EVENT_ID);
            String action = intent.getStringExtra(Constants.MY_EVENT_ACTION);

            if (action == null) {
                return;
            }

            if (action.equalsIgnoreCase(Constants.MY_EVENT_ACTION_REVIEW)) {
                progressDialog = createDialog.createProgressDialog("Finding Event", "Finding Event", false, null);
                progressDialog.show();

                eventDAO.onFindEventById(eventId, false, true);
            } else {

                View view = inflater.inflate(R.layout.alert_publish,
                        null, false);
                final CheckBox mandatory_CB = (CheckBox) view.findViewById(R.id.mandatory_CB);
                final LinearLayout reason_LL = (LinearLayout) view.findViewById(R.id.reason_LL);
                final EditText reason_ET = (EditText) view.findViewById(R.id.reason_ET);
                TextView note_TV = (TextView) view.findViewById(R.id.note_TV);
                note_TV.setText(Html.fromHtml("<b>NOTE:</b> This action can't be undone"));

                mandatory_CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            reason_LL.setVisibility(View.VISIBLE);
                        } else {
                            reason_LL.setVisibility(View.GONE);
                        }
                    }
                });

//                alertDialogBuilder = createDialog.createAlertDialogBuilder("Are you sure you want to publish your changes?", "This action can't be undone", view, false);
                alertDialogBuilder = createDialog.createAlertDialogBuilder(null, null, view, false);
                createDialog.setNegativeButton(alertDialogBuilder, "Cancel", null);
                alertDialogBuilder.setPositiveButton("Publish", null);

                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(final DialogInterface dialog) {
                        *//* To disable dismiss functionality of AlertDialog
                            https://stackoverflow.com/a/26087947/2937847
                         *  *//*
                        Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        b.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if (mandatory_CB.isChecked() && reason_ET.getText().toString().trim().length() < 1) {
                                    reason_ET.setError("Enter a description");
                                    return;
                                } else {
                                    progressDialog = createDialog.createProgressDialog("Publishing Event", "Publishing Event", false, null);
                                    progressDialog.show();

                                    if (reason_ET.getText().toString().trim().length() > 1) {
                                        eventDAO.publishEvent(eventId, reason_ET.getText().toString().trim());
                                    } else {
                                        eventDAO.publishEvent(eventId, null);
                                    }
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                });


                alertDialog.show();
            }
        }
    };*/

    private BroadcastReceiver publishReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(Constants.APP_NAME, className + "publishReceiver");

            progressDialog.dismiss();
            Toast.makeText(EventLoginTabActivity.this, "Published successfully", Toast.LENGTH_LONG).show();
        }
    };

    private BroadcastReceiver removeEventFromListReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String eventId = intent.getStringExtra(Constants.EXTRAS_EVENT_ID);

            Log.i(Constants.APP_NAME, className + "removeEventFromListRECEIVER  " + eventId);

            if (currentEvent != null && currentEvent.getId().equalsIgnoreCase(eventId)) {
                /* If the user is removing currentEvent */
                Toast.makeText(EventLoginTabActivity.this, "You can't remove your current event", Toast.LENGTH_SHORT).show();
            } else {

//                List<Event> eventList = myApp.getEvents();
//                Event tempEvent = myApp.findEventById(eventId);
//                boolean x = eventList.remove(tempEvent);
//                myApp.setEvents(eventList);
                myApp.removeEventFromList(eventId);

                prepareContentsForList("removeEventFromListReceiver");
                initView();
            }
        }
    };

    private BroadcastReceiver findEventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String aliasId = intent.getStringExtra(Constants.EXTRAS_EVENT_ALIAS_ID);
            Log.i(Constants.APP_NAME, className + "findEventReceiver  " + aliasId);

            eventId_ET.setText(aliasId);
            onFindEvent(null);
        }
    };


    private void prepareContentsForList(String fromWhere) {
        //            https://stackoverflow.com/questions/4011075/how-do-you-format-the-day-of-the-month-to-say-11th-21st-or-23rd-in-java
        listDataChild = new LinkedHashMap<String, List<BasicInfo>>();
        listDataHeader = new ArrayList<String>();

        List<BasicInfo> createdByMeEventList = myApp.getEventsCreatedByMeBIList();
        /* Eventscreated By Me */
        List<BasicInfo> recentlySearchedEventList = new ArrayList<BasicInfo>();
        if (createdByMeEventList.size() > 0) {
            listDataHeader.add(Constants.EVENTS_CREATED_BY_ME);
            listDataChild.put(Constants.EVENTS_CREATED_BY_ME, createdByMeEventList);
        }


        /* From List<Event> for which I'm not the owner are my recently searched Events */
        for (Event event : myApp.getEvents().values()) {
            if (!event.isLoggedInUserOwner()) {
                recentlySearchedEventList.add(event.getBasicInfo());
            }
        }

        if (recentlySearchedEventList.size() > 0) {
            listDataHeader.add(Constants.EVENTS_RECENTLY_SEARCHED);
            listDataChild.put(Constants.EVENTS_RECENTLY_SEARCHED, recentlySearchedEventList);
        }

        Log.i(Constants.APP_NAME, className + "prepareContentsForList()     listDataHeader : " + listDataHeader.size());

    }

    private void findThings() {
        eventId_ET = (EditText) findViewById(R.id.eventId_ET);

//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        tabLayout = (TabLayout) findViewById(R.id.tabs);

        /* To make all the character uppercase - https://stackoverflow.com/a/25571410/2937847 */
        InputFilter[] editFilters = eventId_ET.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter.AllCaps();
        eventId_ET.setFilters(newFilters);

    }

    public void initThings() {
        super.initThings();
        previousIntent = getIntent();
        eventDAO = new EventDAO(this, mDatabase);

        prepareContentsForList("initThings");
    }

    /*public class ViewPagerAdapter extends FragmentPagerAdapter {
        public List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/


    public void initView() {
        /* If we call super.initView() the indefinite SNACKBAR starts showing
         * super.initView(); */

        /*if (listDataHeader != null && listDataHeader.size() > 0) {
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);

            listInAction = new ArrayList<List<BasicInfo>>(listDataChild.values()).get(0);
            headerType = new ArrayList<String>(listDataChild.keySet()).get(0);


            final ViewPagerAdapter adapterVP = new ViewPagerAdapter(getSupportFragmentManager());

            List<String> keys = new ArrayList<String>(listDataChild.keySet());
            for (int i = 0; i < keys.size(); i++) {
                adapterVP.addFragment(new EventListFragment(), keys.get(i));
            }

            viewPager.setAdapter(adapterVP);

            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabTextColors(ColorUtils.blendARGB(colorPrimary, Color.parseColor("#FFFFFF"), 0.5F), Color.parseColor("#FFFFFF"));


            viewPager.setOffscreenPageLimit(3);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    Log.i(Constants.APP_NAME, "onPageScrolled()       " + position);


                }

                @Override
                public void onPageSelected(int position) {
                    Log.i(Constants.APP_NAME, "onPageSelected()       " + position);
                    assignListAccordingToPosition(position);
                    if (getSupportFragmentManager().getFragments().get(position) instanceof EventListFragment) {
                        EventListFragment studentListFragment = (EventListFragment) getSupportFragmentManager().getFragments().get(position);
                        if (studentListFragment.getEventRVAdapter() != null) {
                            studentListFragment.getEventRVAdapter().notifyDataSetChanged();
                        }
                    }

                    adapterVP.notifyDataSetChanged();

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            Log.i(Constants.APP_NAME, "huhuhu ");
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
        }*/
    }

    public void assignListAccordingToPosition(int position) {
//        listInAction = ((List<List<BasicInfo>>) listDataChild.values()).get(position);
        listInAction = new ArrayList<List<BasicInfo>>(listDataChild.values()).get(position);
        headerType = new ArrayList<String>(listDataChild.keySet()).get(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_login);

        initThings();


        if (currentEvent != null && !(previousIntent.hasExtra(Constants.EXTRAS_SWITCH_EVENT) && previousIntent.getBooleanExtra(Constants.EXTRAS_SWITCH_EVENT, false))) {
        /* If we have reached this activity to switch the event, then we should not redirect to the MenuActivity(despite Application Class having a currentEvent).
         * But if the user has just started the app then it must redirect the user to the MenuActivity
          * */
            startNextActivity(false);
        } else {

            findThings();
            initView();

            eventId_ET.setText("NGOBOX_CSR_2017");
//            eventId_ET.setText("MOLO");


            eventDAO.findAllMyEvents();
        }
    }

    public void onFindEvent(View view) {
        String text = className + "onFindEvent()        ";

        Log.i(Constants.APP_NAME, text);
        if (!checkAndRequestPermissions()) {
            /* User denied permission */
            return;
        }

        boolean shouldFindEvent;
        String eventId = eventId_ET.getText().toString();
        Log.i(Constants.APP_NAME, text + eventId);

        boolean doesListHaveThisEvent = myApp.getEvents().containsKey(eventId);
        if (doesListHaveThisEvent) {
            /* The event already exists in Map<allEvents> */
            if (myApp.getEvents().get(eventId).isPreReleaseTest()) {
                Log.i(Constants.APP_NAME, text + "Event exists in List<E>. BUT, its - isPreReleaseTest");
                shouldFindEvent = true;
            } else {
                Log.i(Constants.APP_NAME, text + "Event already exists in your list. Redirect to MenuActivity ");

                myApp.setCurrentEvent(myApp.getEvents().get(eventId), "UnusedEventLoginActivity");
                startNextActivity(false);
                return;
            }
        } else {
            Log.i(Constants.APP_NAME, text + "Event NOT found in List<E>");
            shouldFindEvent = true;
        }

        if (shouldFindEvent) {
            Log.i(Constants.APP_NAME, text + "NEW event. Finding now.");
            progressDialog = createDialog.createProgressDialog("Finding Event", "Finding Event", false, null);
            progressDialog.show();

            if (eventId.startsWith("-")) {
                eventDAO.onFindEventById(eventId, false, false);
            } else {
                eventDAO.findEventWithAliasName(eventId);
            }
        }

    }

    private void startNextActivity(boolean isItANewlyFoundEvent) {
        Log.i(Constants.APP_NAME, className + "startNextActivity()     isItANewlyFoundEvent   " + isItANewlyFoundEvent);
        if (isItANewlyFoundEvent) {
            progressDialog.dismiss();
            currentEvent = tempEvent;
            myApp.setCurrentEvent(tempEvent, "");

            /*if (!myApp.getEvents().contains(tempEvent)) {
                myApp.getEvents().add(myApp.getCurrentEvent());
                myApp.setEvents(myApp.getEvents());
            }*/

            editor.putBoolean(Constants.PREF_SHOULD_FIND_SPEAKERS_PROFILE, true);
            editor.putBoolean(Constants.PREF_SHOULD_FIND_ATTENDEES_PROFILE, true);
            editor.putBoolean(Constants.PREF_SHOULD_FIND_CHATROOM_MESSAGES, true);
            editor.commit();
        }

        nextIntent = new Intent(EventLoginTabActivity.this, MenuActivity2.class);

        nextIntent.putExtra(Constants.EXTRAS_IS_NEWLY_FOUND_EVENT, isItANewlyFoundEvent);
//        nextIntent.putExtra(Constants.EXTRAS_IS_PRE_RELEASE_SEARCH, isPreReleaseSearch);
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(nextIntent);
    }

    public void onLogout(View view) {
        Log.i(Constants.APP_NAME, className + "onLogout()");
        myApp.logoutFromApp();
    }




    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(
                eventReceiver, new IntentFilter(Constants.RECEIVER_EVENT));
        LocalBroadcastManager.getInstance(this).registerReceiver(
                basicInfoListReceiver, new IntentFilter(Constants.RECEIVER_BASIC_INFO_LIST));
//        LocalBroadcastManager.getInstance(this).registerReceiver(
//                myEventActionReceiver, new IntentFilter(Constants.RECEIVER_MY_EVENT_ACTION));
        LocalBroadcastManager.getInstance(this).registerReceiver(
                removeEventFromListReceiver, new IntentFilter(Constants.RECEIVER_REMOVE_EVENT_FROM_LIST));
        LocalBroadcastManager.getInstance(this).registerReceiver(
                findEventReceiver, new IntentFilter(Constants.RECEIVER_FIND_EVENT));
        LocalBroadcastManager.getInstance(this).registerReceiver(
                publishReceiver, new IntentFilter(Constants.RECEIVER_PUBLISH));

    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                eventReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                basicInfoListReceiver);
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(
//                myEventActionReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                removeEventFromListReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                findEventReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                publishReceiver);

    }


}
