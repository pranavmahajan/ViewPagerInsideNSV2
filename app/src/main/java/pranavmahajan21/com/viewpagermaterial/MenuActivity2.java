package pranavmahajan21.com.viewpagermaterial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import pranavmahajan21.com.viewpagermaterial.adapter.MenuRecyclerAdapter;
import pranavmahajan21.com.viewpagermaterial.dao.EventDAO;
import pranavmahajan21.com.viewpagermaterial.model.Event;
import pranavmahajan21.com.viewpagermaterial.model.MenuItem;
import pranavmahajan21.com.viewpagermaterial.model.Profile;
import pranavmahajan21.com.viewpagermaterial.util.Constants;
import pranavmahajan21.com.viewpagermaterial.util.ItemClickSupport;

public class MenuActivity2 extends DaddyActivity {

    String className = "MenuActivity2      ";

    //    NestedScrollView scrollParent_NSV;
//    LinearLayout menuHeader_LL;
    LinearLayout activity_menu_root_LL;
//    ImageView bg_IV;
    Intent previousIntent, nextIntent;
    FirebaseStorage storage;

    int noOfCMToDownload = 0;

    Profile tempProfile;

    EventDAO eventDAO;

    List<MenuItem> menuItems;

    LayoutInflater inflater;

    boolean isNewlyFoundEvent = false;

    static int spanCount = 3; // 3 columns
    static int spacing = 30; // 50px
    boolean includeEdge = false;


    /*private BroadcastReceiver latestVersionCheckReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String logPrefix = className + "latestVersionCheckReceiver     ";
            Log.i(Constants.APP_NAME, logPrefix);

            Version latestVersion = gson.fromJson(intent.getStringExtra(Constants.EXTRAS_LATEST_VERSION), Version.class);

            if (latestVersion.getIsDisabledBySA() || latestVersion.getIsDisabled()) {
                Log.i(Constants.APP_NAME, logPrefix + "Event DISABLED");

                String msg = Constants.TEXT4;
                if (latestVersion.getIsDisabledBySA()) {
                    msg = Constants.TEXT5;
                }
                alertDialogBuilder = createDialog.createAlertDialogBuilder("Error", msg, null, false);
                createDialog.setNegativeButton(alertDialogBuilder, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        nextIntent = new Intent(MenuActivity2.this, EventLoginTabActivity.class);
                        nextIntent.putExtra(Constants.EXTRAS_SWITCH_EVENT, true);
                        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(nextIntent);
                    }
                });
                createDialog.showAlertDialog(alertDialogBuilder);
                return;
            }


            int latestEventLMV = latestVersion.getLmv();
            int latestEventCV = latestVersion.getCv();
            int currentEventCV = currentEvent.getVersion().getCv();

            Log.i(Constants.APP_NAME, logPrefix + "currentEventCV  " + currentEventCV + "     latestEventCV  " + latestEventCV + "     latestEventLMV     " + latestEventLMV);

            if (latestEventLMV > currentEventCV) {
                Log.i(Constants.APP_NAME, logPrefix + "-   MANDATORY update is available for this event   " + latestEventLMV + " > " + currentEventCV);

                Map<String, String> notesHistory = latestVersion.getNotesHistory();

                List notesHistoryKeyList = new ArrayList(notesHistory.keySet());
                Collections.sort(notesHistoryKeyList);

                *//* https://stackoverflow.com/a/19198616/2937847 *//*
                NavigableSet<String> foo = new TreeSet<String>(notesHistoryKeyList);
                String lmvLessThanCurrentEventCV = foo.floor("v" + currentEventCV);

                int currentEventCVIndexInNotesHistory = notesHistoryKeyList.indexOf(lmvLessThanCurrentEventCV);
                currentEventCVIndexInNotesHistory = currentEventCVIndexInNotesHistory < 0 ? 0 : currentEventCVIndexInNotesHistory + 1;


                String message = Constants.TEXT1;
                for (int i = currentEventCVIndexInNotesHistory; i < notesHistoryKeyList.size(); i++) {
                    message = message + "\n" + notesHistory.get(notesHistoryKeyList.get(i)) + "\n";
                }

                alertDialogBuilder = createDialog.createAlertDialogBuilder(Constants.TEXT2, message, null, false);
                createDialog.setNegativeButton(alertDialogBuilder, "Update Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        onUpdateEvent(null);
                    }
                });
                createDialog.showAlertDialog(alertDialogBuilder);

            } else if (latestEventCV > currentEventCV) {
                Log.i(Constants.APP_NAME, logPrefix + "-  NON MANDATORY  update is available for this event   " + latestEventCV + " > " + currentEventCV);
                Toast.makeText(MenuActivity2.this, Constants.TEXT3, Toast.LENGTH_LONG).show();
            } else {
                eventDAO.lastUserDataUpdatedTime(currentEvent.getId());
            }
        }
    };

    private BroadcastReceiver eventUserDataUpdatedAtReceiver = new BroadcastReceiver() {
        String text = className + "eventUserDataUpdatedAtRECEIVER       ";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(Constants.APP_NAME, text);
            long l = intent.getLongExtra(Constants.EXTRAS_USER_D_UPDATED_AT, 0);

            Log.i(Constants.APP_NAME, text + "l     " + l + "       currentEvent.getUserDataUpdatedAt      " + currentEvent.getUserDataUpdatedAt());

            if (l > currentEvent.getUserDataUpdatedAt()) {
            *//* Fetch User Data *//*
                Log.i(Constants.APP_NAME, text + "Update user data");
                eventDAO.onFindEventUserData(currentEvent, false, currentEvent.isPreReleaseTest());
            } else {
                Log.i(Constants.APP_NAME, text + "User data up-to-date");
            }

        }
    };

    private BroadcastReceiver eventDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(Constants.APP_NAME, className + "eventDataRECEIVER  : ");

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Event event = gson.fromJson(intent.getStringExtra(Constants.EXTRAS_LATEST_EVENT), Event.class);
            if (event != null) {
                myApp.setCurrentEvent(event, "MenuActivity2");
                currentEvent = event;

                setTheme();
                setCustomContentView();
                findThings();
                initView();
                setToolbar(myApp.getCurrentEvent().getBasicInfo().getName(), false, null);

                downloadContentMandatoryFiles(isNewlyFoundEvent, "eventUserDataReceiver");
            } else {
                Log.i(Constants.APP_NAME, className + "eventDataRECEIVER  : ");
                Toast.makeText(MenuActivity2.this, "Error while updating", Toast.LENGTH_SHORT).show();
            }
//            eventDAO.onFindEventUserData(myApp.getCurrentEvent(), true);
        }
    };*/

//    private BroadcastReceiver eventUserDataReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.i(Constants.APP_NAME, className + "eventUSERDataRECEIVER  : ");
//
//            if (progressDialog != null && progressDialog.isShowing()) {
//                progressDialog.dismiss();
//            }
//            Event event = gson.fromJson(intent.getStringExtra(Constants.EXTRAS_LATEST_EVENT), Event.class);
//            myApp.setCurrentEvent(event, "MenuActivity2");
//
////            Activity.
//            downloadContentMandatoryFiles(isNewlyFoundEvent, "eventUserDataReceiver");
//        }
//    };
//
//    private BroadcastReceiver speakersProfileReceiver = new BroadcastReceiver() {
//        // TODO: 13/12/16 This wont be called if you move to next Activity. Test this properly.
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.i(Constants.APP_NAME, className + "speakersProfileReceiver   : ");
//            if (intent.hasExtra(Constants.EXTRAS_PROFILE)) {
//                Profile profile = gson.fromJson(intent.getStringExtra(Constants.EXTRAS_PROFILE), Profile.class);
//                myApp.addOrReplaceSpeakerProfile(profile);
//            }
//        }
//    };

    /*private BroadcastReceiver contentMandatoryDownloadedFilesReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(Constants.APP_NAME, className + "contentMandatoryDownloadedFilesReceiver      " + noOfCMToDownload);
            noOfCMToDownload--;
            if (noOfCMToDownload == 0) {
                Log.i(Constants.APP_NAME, className + "contentMandatoryDownloadedFilesReceiver    Refresh screen");
                Toast.makeText(MenuActivity2.this, "Content Mandatory downloded", Toast.LENGTH_LONG).show();
                setToolbar(myApp.getCurrentEvent().getBasicInfo().getName(), false, null);
                inflateBG();
                if ((boolean) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_HAS_HEADER)) {
                    String headerType = (String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_HEADER_TYPE);
                    inflateHeader(Constants.SCREEN_MENU, null, menuHeader_LL, headerType);
                } else {
                    Log.i(Constants.APP_NAME, className + "contentMandatoryDownloadedFilesReceiver    Hide the header");
                    menuHeader_LL.setVisibility(View.GONE);
                }
            }
        }
    };*/

    public void initThings() {
        super.initThings();
        storage = FirebaseStorage.getInstance();

        tempProfile = myApp.getLoggedInUserProfile();
        previousIntent = getIntent();
        isNewlyFoundEvent = previousIntent.getBooleanExtra(Constants.EXTRAS_IS_NEWLY_FOUND_EVENT, false);

        eventDAO = new EventDAO(this, mDatabase);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void findThings() {
        activity_menu_root_LL = (LinearLayout) findViewById(R.id.activity_menu_root_LL);
//        menuHeader_LL = (LinearLayout) findViewById(R.id.menuHeader_LL);

//        scrollParent_NSV = (NestedScrollView) findViewById(R.id.scrollParent_NSV);
//        bg_IV = (ImageView) findViewById(R.id.bg_IV);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }


    /*private void inflateBG() {
        if (myApp.prefContainsAndValue(Constants.EVENT_PREF_MENU_HAS_CUSTOM_BG, myApp.getPrefMenu())) {
            switch ((String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_BG_TYPE)) {
                case "BG001":
                    Log.i(Constants.APP_NAME, className + "inflateBG()      BG color");
                    ((FrameLayout) findViewById(R.id.menu_FL)).setBackgroundColor(Color.parseColor((String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_BG_COLOR)));
                    break;
                case "BG002":
                    *//* BG Image *//*
                    Log.i(Constants.APP_NAME, className + "inflateBG()      BG image");
                    CMUtil cmUtil = new CMUtil(myApp);
                    boolean fileRenderStatus = cmUtil.renderImageFromCMId((String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_BG_IMAGE_ID), bg_IV);

                    if (fileRenderStatus) {
                        *//* File rendered. Now, 1. Apply blur 2. Apply proper Scaling *//*

                        Bitmap myBitmap = ((BitmapDrawable) bg_IV.getDrawable()).getBitmap();
                        if (myApp.getPrefMenu().containsKey(Constants.EVENT_PREF_MENU_BG_IMAGE_HAS_BLUR) && (boolean) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_BG_IMAGE_HAS_BLUR)) {
                            float blurIndex = (float) (double) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_BG_IMAGE_BLUR_INDEX) * 2.5f;
                            if (blurIndex > 0.0f) {
                                myBitmap = BlurBuilder.blur(this, myBitmap, blurIndex);
                            }
                        }
                        bg_IV.setImageBitmap(myBitmap);

                        switch ((String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_BG_IMAGE_TYPE)) {
                            case Constants.EVENT_PREF_MENU_BG_IMAGE_TYPE_FULLSCREEN:
                                bg_IV.setScaleType(ImageView.ScaleType.FIT_XY);
                                break;
                            case Constants.EVENT_PREF_MENU_BG_IMAGE_TYPE_PARTIAL:
                                bg_IV.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                break;
                            default:
                                bg_IV.setScaleType(ImageView.ScaleType.FIT_XY);
                                break;
                        }
                    } else {
                        Log.e(Constants.APP_NAME, className + "fileRenderStatus  " + fileRenderStatus);
                    }
                    break;
                default:
                    ((FrameLayout) findViewById(R.id.menu_FL)).setBackgroundColor(Color.parseColor((String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_BG_COLOR)));
                    break;
            }


        } else {
            Log.i(Constants.APP_NAME, className + "inflateBG()      DOESN'T  have custom BG");
        }
    }*/

    private MenuRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        /* https://stackoverflow.com/a/30701422/2937847 */
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    public void initView() {
        super.initView();
        Log.i(Constants.APP_NAME, className + "initView()");

        /*inflateBG();

        if (myApp.getPrefMenu().containsKey(Constants.EVENT_PREF_MENU_HAS_HEADER) && (boolean) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_HAS_HEADER)) {
        *//* Header *//*
            *//* TODO -  We can remove the first IF check *//*
            String headerType = (String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_HEADER_TYPE);
            inflateHeader(Constants.SCREEN_MENU, null, menuHeader_LL, headerType);
        } else {
            Log.i(Constants.APP_NAME, className + "initView()    Hide the header");
            menuHeader_LL.setVisibility(View.GONE);
        }*/

        if (!currentEvent.getBasicInfo().isEventPublic() && myApp.amIAtendee()) {
            /* Show Attending/Not Attending status snackbar only if you're an attendee. Not in case of admin/speaker */

            int attendingStatus = myApp.getCurrentEvent().getAttendeesMap().get(myApp.convertEmailToPath(myApp.getLoggedInUser().getEmail()));
            if (attendingStatus != Constants.NOT_ANSWERED_AS_YET) {
                Snackbar snackbar = Snackbar.make(activity_menu_root_LL, "", Snackbar.LENGTH_LONG)
                        .setAction("CHANGE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(nextIntent);
                            }
                        });

                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                if (attendingStatus == Constants.ATTENDING) {
                    snackbar.setText("You are already attending this event");
                    textView.setTextColor(Color.GREEN);
                } else if (attendingStatus == Constants.ATTENDING_NOT) {
                    snackbar.setText("You are NOT attending this event");
                    textView.setTextColor(Color.RED);
                }
                snackbar.setActionTextColor(Color.BLUE);
                snackbar.show();
            } else {
                // TODO: 9/1/17 Show a snackbar to ask attending/not attending status
            }
        }

        /* Menu Buttons */
        menuItems = myApp.getCurrentEvent().getMenuItems();

        if (menuItems.size() > 0) {

            Collections.sort(menuItems, new Comparator<MenuItem>() {
                public int compare(MenuItem menuItem1, MenuItem menuItem2) {
                    return menuItem1.getPosition() - menuItem2.getPosition();
                }
            });

            adapter = new MenuRecyclerAdapter(this, menuItems);

            recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

            recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    // do it
                    Log.i(Constants.APP_NAME, className + "   position : " + position);
                    switch (menuItems.get(position).getRealName()) {
                        case Constants.MENU_ITEM_SESSION:
                            Log.i(Constants.APP_NAME, className + "onClick     Session");
                            nextIntent = new Intent(MenuActivity2.this, SessionTabListActivity.class);
                            startActivity(nextIntent);
                            break;
                       case Constants.MENU_ITEM_CUSTOM:
                            Log.i(Constants.APP_NAME, className + "onClick     Custom");
//                        nextIntent = new Intent(MenuActivity2.this, CustomMenuItemActivity.class);
//                        startActivity(nextIntent);
                            break;
                    }
                }
            });
        }
    }

    private void setCustomContentView() {
        String menu_screen = (String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_TYPE);

        if (menu_screen.equalsIgnoreCase("MENU0001")) {
            setContentView(R.layout.activity_menu2);
        } else if (menu_screen.equalsIgnoreCase("MENU0002")) {
            setContentView(R.layout.activity_menu2);
        }
    }

    /*private void downloadContentMandatoryFiles(boolean isNewlyFoundEvent, String x) {
        // TODO: 1/4/18 Test this function after removing Storage PERMISSIONS
        Log.i(Constants.APP_NAME, className + "downloadContentMandatoryFiles()      " + isNewlyFoundEvent + "     " + x);
        int filesExistCounter = 0;
        int filesDoesntExistCounter = 0;
        // TODO: 9/1/17 Don't check for files on the basis of their names, but on the basis of their IDs. Coz if you upload another file with same name, the ID is always different
        ContentMandatoryDAO contentMandatoryDAO = new ContentMandatoryDAO(this, mDatabase);
        List<ContentMandatory> contentMandatoryList = myApp.getCurrentEvent().getContentMandatoryList();
        for (ContentMandatory tempContentMandatory : contentMandatoryList) {
            File appDir = new File(MyApp.ROOT_APP);
            File file = new File(appDir, myApp.getCurrentEvent().getId() + "/" + Constants.STORAGE_FOLDER_CONTENT_MANDATORY + tempContentMandatory.getCategory() + "/" + tempContentMandatory.getFilename());

            if (file.exists()) {
                // TODO: 9/13/17 This part fails if we upload a file eg hello.jpg. And at a later stage we overwrite it by same filename hello.jpg
                filesExistCounter++;
            } else {
                Log.i(Constants.APP_NAME, className + "downloadContentMandatoryFiles()      File doesnt exists. Now downloading :   " + tempContentMandatory.getFilename());
                contentMandatoryDAO.downloadContentMandatory(tempContentMandatory);
                noOfCMToDownload++;
                filesDoesntExistCounter++;
            }
        }
        Log.i(Constants.APP_NAME, className + "downloadContentMandatoryFiles()      filesExistCounter   :   " + filesExistCounter + "   filesDoesntExistCounter    :   " + filesDoesntExistCounter);

        if (isNewlyFoundEvent) {
            Toast.makeText(MenuActivity2.this, "Downloading Mandatory content for the event. " + noOfCMToDownload + " files.", Toast.LENGTH_LONG).show();
        }

    }*/

    private void checkContentMandatoryUpdates() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initThings();

        setCustomContentView();

        findThings();
        initView();
//        setToolbar(myApp.getCurrentEvent().getBasicInfo().getName(), false, null);

        Map<String, String> timestamp = ServerValue.TIMESTAMP;

//        boolean
        if (isNewlyFoundEvent) {
            Log.i(Constants.APP_NAME, className + "     onCreate()      newly found event " + isNewlyFoundEvent);
//            eventDAO.onFindEventUserData(myApp.getCurrentEvent(), false);
            eventDAO.downloadProfileImagesOfInvites();
//            sponsorDAO.downloadSponsorImages();
//            profileDAO.findProfilesOfSpeakers(myApp.getCurrentEvent().getSpeakerList());
            isNewlyFoundEvent = true;
        } else {
            checkForLatestVersion();
        }
//        downloadContentMandatoryFiles(isNewlyFoundEvent, "onCreate");
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(Constants.APP_NAME, className + "onCreateOptionsMenu");


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_homepage_context_menu, menu);

        if (!myApp.amITheOwner()) {
            *//* https://stackoverflow.com/a/13099201/2937847 *//*
            menu.findItem(R.id.shareEvent).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.i(Constants.APP_NAME, className + "onOptionsItemSelected");

        switch (item.getItemId()) {
            case R.id.update:
                onUpdateEvent(null);
                return true;
            case R.id.switchEvent:
                Log.i(Constants.APP_NAME, className + "FUNCTION     onSwitchEvent() ");
                nextIntent = new Intent(this, EventLoginTabActivity.class);
                nextIntent.putExtra(Constants.EXTRAS_SWITCH_EVENT, true);
                startActivity(nextIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void checkForLatestVersion() {
        // TODO: 9/9/16  check for internet. If update available then dont goto next page.
//        eventDAO.findEventVersions(myApp.getCurrentEvent().getId());
    }

    public void onUpdateEvent(View view) {
        Log.i(Constants.APP_NAME, className + "FUNCTION     onUpdateEvent() ");

        isNewlyFoundEvent = false;

        progressDialog = createDialog.createProgressDialog("Updating", "Updating", true, null);
        progressDialog.show();

        eventDAO.onFindEventById(currentEvent.getId(), true, currentEvent.isPreReleaseTest());
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.initView();
    }

    public void onGetContentMetadataFromEventBucket(View view) {
        Log.i(Constants.APP_NAME, className + " onGetContentMetadataFromEventBucket()  : ");
        StorageReference storageRef = storage.getReferenceFromUrl(Constants.BUCKET_NAME);
        StorageReference eventContentRef = storageRef.child("/Content/" + myApp.getCurrentEvent().getId() + "/");
//        StorageReference eventContentRef = storageRef.child("/Content/" + myApp.getCurrentEvent().getId() + "/demo_image.jpg");
// Get reference to the file
//        StorageReference forestRef = storageRef.child("images/forest.jpg");

        eventContentRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                // Metadata now contains the metadata for 'images/forest.jpg'
                Log.i(Constants.APP_NAME, className + " onGetContentMetadataFromEventBucket()  : ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
                Log.i(Constants.APP_NAME, className + " onGetContentMetadataFromEventBucket()  : ");
            }
        });
    }

}
