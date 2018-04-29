package pranavmahajan21.com.viewpagermaterial;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pranavmahajan21.com.viewpagermaterial.model.Event;
import pranavmahajan21.com.viewpagermaterial.model.MenuItem;
import pranavmahajan21.com.viewpagermaterial.util.Constants;
import pranavmahajan21.com.viewpagermaterial.util.CreateDialog;


public class DaddyActivity extends AppCompatActivity {

    String className = "DaddyActivity      ";

    MyApp myApp;

    Intent nextIntent;

    MenuItem currentMenuItem;

    Spinner selectSession_S;

    CreateDialog createDialog;
    ProgressDialog progressDialog;
    AlertDialog.Builder alertDialogBuilder;
    Dialog dialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DatabaseReference mDatabase;

    Gson gson;
    LayoutInflater inflater;

    Toolbar toolbar;

    Event currentEvent;

    int colorPrimary;
    int colorPrimaryDark;
    int colorAccent;

    public void initThings() {
        myApp = (MyApp) getApplicationContext();
        createDialog = new CreateDialog(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        gson = new Gson();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        currentEvent = myApp.getCurrentEvent();
    }

    public void initView() {
        if (currentEvent != null && currentEvent.isPreReleaseTest()) {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, Constants.TEXT_PRE_RELEASE_TEST_MESSAGE, Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    public boolean isExternalStorageWritable() {
        /* https://developer.android.com/guide/topics/data/data-storage.html#filesExternal */
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /*public void setToolbar(String actionBarTitle, boolean showActionBarRightIcon, Drawable actionBarRightIcon) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        *//* https://stackoverflow.com/a/36688110/2937847 *//*
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3A1212")));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        *//* https://stackoverflow.com/questions/39111248/canvas-trying-to-draw-too-large-bitmap-when-android-n-display-size-set-larger
        https://developer.android.com/topic/performance/graphics/load-bitmap.html#read-bitmap *//*
//        ((ImageView) toolbar.findViewById(R.id.eventIcon_IV)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.logo_rectangular2));

        if (currentEvent != null && myApp.prefContainsAndValue(Constants.EVENT_PREF_HAS_CUSTOM_LOGO, myApp.getPrefCustomLogo())) {
            String logoFilepath = MyApp.ROOT_APP + "/" + currentEvent.getId() + "/" + Constants.STORAGE_FOLDER_CONTENT_MANDATORY + Constants.CONTENT_M_TYPE_LOGO + "/";

            ContentMandatory customIconCM = myApp.findContentMandatoryById((String) myApp.getPrefCustomLogo().get(Constants.EVENT_PREF_CUSTOM_LOGO_ID));
            if (customIconCM != null) {
                File customLogoFile = new File(logoFilepath + customIconCM.getFilename());
                if (customLogoFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(customLogoFile.getAbsolutePath());
                    ((ImageView) toolbar.findViewById(R.id.eventIcon_IV)).setImageBitmap(myBitmap);

//                    if (((String) myApp.getPrefCustomLogo().get(Constants.EVENT_PREF_CUSTOM_LOGO_TYPE)).equalsIgnoreCase("horizontal")) {
//                *//* https://stackoverflow.com/a/10046252/2937847
//                 * https://stackoverflow.com/a/23575369/2937847 *//*
//                        final float scale = getResources().getDisplayMetrics().density;
//                        int dpWidthInPx = (int) (80 * scale);
//                        int dpHeightInPx = (int) (40 * scale);
//
//                        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(dpWidthInPx, dpHeightInPx);
//                        ((ImageView) toolbar.findViewById(R.id.eventIcon_IV)).setLayoutParams(param);
//                    }
                }
            }
        } else {
            // TODO: 9/1/17 Show either App logo or move the title to left
            Log.w(Constants.APP_NAME, className + "No Custom Logo");
        }

        if (actionBarTitle != null) {
//            ((TextView) toolbar.findViewById(R.id.activitytName_TV)).setText(actionBarTitle);
            ((AutofitTextView) toolbar.findViewById(R.id.activitytName_TV2)).setText(actionBarTitle);
        }
        if (!showActionBarRightIcon) {
            ((ImageButton) toolbar.findViewById(R.id.actionBarRightIcon_IB)).setVisibility(View.GONE);
        }
        if (actionBarRightIcon != null) {
            ((ImageButton) toolbar.findViewById(R.id.actionBarRightIcon_IB)).setImageDrawable(actionBarRightIcon);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myApp = (MyApp) getApplicationContext();

        setTheme();

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setTheme() {
        /* http://stackoverflow.com/questions/25815769/how-to-really-programmatically-change-primary-and-accent-color-in-android-loll
        * https://stackoverflow.com/a/26515159/2937847
        * */
//        https://android.jlelse.eu/android-changing-app-theme-at-runtime-ab17d3eb93cc

        /* HOW TO MAKE CHOSE PRIMARY & ASCENT COLORS
         * https://www.materialpalette.com/
         * https://material.io/guidelines/style/color.html#
         * https://material.io/color/#!/?view.left=0&view.right=0
          * */

        if (myApp.getCurrentEvent() != null && myApp.getCurrentEvent().getPrefs().containsKey(Constants.EVENT_PREF_THEME)) {
            String theme = (String) myApp.getCurrentEvent().getPrefs().get(Constants.EVENT_PREF_THEME);
//            theme = "THEME0001";
            Log.i(Constants.APP_NAME, className + "THEME   :  " + theme);

            switch (theme) {
//          CORRECT SOLUTION        -       https://stackoverflow.com/a/37905131/2937847
                case "THEME0001":
                    setTheme(R.style.AppTheme);
//                    getTheme().applyStyle(R.style.Test1, true);
//                    getTheme().applyStyle(R.style.Test2, true);
                    break;
                case "THEME0002":
                    setTheme(R.style.AppTheme);
//                    getTheme().applyStyle(R.style.AppTheme2, true);
                    break;
                case "THEME0003":
                    setTheme(R.style.AppTheme);
                    break;
                default:
                    setTheme(R.style.AppTheme);
                    break;
            }

            /* https://stackoverflow.com/a/27611244/2937847 */
            TypedValue typedValue = new TypedValue();

            TypedArray a = obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimary});
            colorPrimary = a.getColor(0, 0);

            a = obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimaryDark});
            colorPrimaryDark = a.getColor(0, 0);

            a = obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
            colorAccent = a.getColor(0, 0);

            Log.i(Constants.APP_NAME, className + "colorPrimary : " + colorPrimary + "      colorPrimaryDark : " + colorPrimaryDark + "     colorAccent : " + colorAccent);

            a.recycle();
        }
    }

    public boolean checkAndRequestPermissions() {
        /* https://developer.android.com/training/permissions/requesting.html#java */

        int writeCalendarPhoneState = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR);

        int readCalendarPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR);

        int permissionWriteDisk = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int permissionReadDisk = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (writeCalendarPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CALENDAR);
        }
        if (readCalendarPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALENDAR);
        }
        if (permissionWriteDisk != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionReadDisk != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            System.out.println("List Size " + listPermissionsNeeded.size());
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 200);
            return false;
        }

        return true;
    }

   /* private void setPagerDot(LinearLayout pager_indicator, int dotsCount, ImageView[] dots, int colorPrimary, int colorAccent) {



        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_dot));
            dots[i].setColorFilter(colorAccent);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));
        dots[0].setColorFilter(colorPrimary);

    }

    public ViewGroup inflateImageSlider(List<String> contentMandatoryList, String contentMandatoryTypeFolder, LinearLayout parentView) {
        List<String> sampleImagesString = new ArrayList<String>();
        for (int k = 0; k < contentMandatoryList.size(); k++) {
            String contentMandatoryId2 = contentMandatoryList.get(k);
            ContentMandatory tempContentMandatory2 = myApp.findContentMandatoryById(contentMandatoryId2);
            if (tempContentMandatory2 != null) {
                String filename2 = tempContentMandatory2.getFilename();

                File imageFile2 = new File(MyApp.ROOT_APP + "/" + currentEvent.getId() + "/" + Constants.STORAGE_FOLDER_CONTENT_MANDATORY + contentMandatoryTypeFolder + "/" + filename2);
                if (imageFile2.exists()) {
                    sampleImagesString.add(imageFile2.getAbsolutePath());
                }
            }
        }
        RelativeLayout imageCarousel_LL = null;
        if (sampleImagesString.size() > 0) {
            imageCarousel_LL = (RelativeLayout) inflater.inflate(R.layout.view_about_image_carousel2,
                    null, false);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            final ViewPager carousel_ViewP = (ViewPager) imageCarousel_LL.findViewById(R.id.carousel_ViewP);
            final NestedScrollView scrollParent_NSV = (NestedScrollView) findViewById(R.id.scrollParent_NSV);


                        *//* Set height of ViewPager *//*
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.height = (displayMetrics.heightPixels / 100) * 40;
            carousel_ViewP.setLayoutParams(layoutParams);

                    *//* Init ViewPager *//*
            ViewPageAdapter mAdapter = new ViewPageAdapter((getApplicationContext()), sampleImagesString, displayMetrics.heightPixels, displayMetrics.widthPixels);
            carousel_ViewP.setAdapter(mAdapter);
            carousel_ViewP.setCurrentItem(0);

                        *//* Init pager indicator view *//*
            LinearLayout pager_indicator = (LinearLayout) imageCarousel_LL.findViewById(R.id.viewPagerCountDots);
            final int dotsCount = mAdapter.getCount();
            final ImageView[] dots = new ImageView[dotsCount];

//                        *//* https://stackoverflow.com/a/28489543/2937847 *//*
            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            final int colorPrimary = typedValue.data;
            getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
            final int colorAccent = typedValue.data;
            setPagerDot(pager_indicator, dotsCount, dots, colorPrimary, colorAccent);

            carousel_ViewP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < dotsCount; i++) {
                        dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_dot));
                        dots[i].setColorFilter(colorAccent);
                    }
                    dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));
                    dots[position].setColorFilter(colorPrimary);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            carousel_ViewP.setOnTouchListener(new View.OnTouchListener() {
                int dragthreshold = 30;
                int downX;
                int downY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            downX = (int) event.getRawX();
                            downY = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            int distanceX = Math.abs((int) event.getRawX() - downX);
                            int distanceY = Math.abs((int) event.getRawY() - downY);
                            if (distanceY > distanceX && distanceY > dragthreshold) {
                                carousel_ViewP.getParent().requestDisallowInterceptTouchEvent(false);

                                if (scrollParent_NSV != null) {
                                    scrollParent_NSV.getParent().requestDisallowInterceptTouchEvent(true);
                                }
                            } else if (distanceX > distanceY && distanceX > dragthreshold) {
                                carousel_ViewP.getParent().requestDisallowInterceptTouchEvent(true);
                                if (scrollParent_NSV != null) {
                                    scrollParent_NSV.getParent().requestDisallowInterceptTouchEvent(false);
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            if (scrollParent_NSV != null) {
                                scrollParent_NSV.getParent().requestDisallowInterceptTouchEvent(false);
                            }
                            carousel_ViewP.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                    return false;
                }
            });
        }
        return imageCarousel_LL;
//        return null;
    }*/

    /*public void inflateHeader(String screenType, String sessionId, LinearLayout parentView, String headerType) {
        Log.i(Constants.APP_NAME, className + "     inflateHeader       screenType - " + screenType + "     headerType - " + headerType);

        List<String> contentMandatoryList = null;
        String contentMandatoryTypeFolder = null;
        parentView.removeAllViews();

        switch (screenType) {
            case Constants.SCREEN_MENU:
                contentMandatoryList = (List<String>) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_CONTENT_MANDATORY_LIST);
                contentMandatoryTypeFolder = Constants.CONTENT_M_TYPE_MENU;
                break;
            case Constants.SCREEN_SESSION_DETAIL:
                contentMandatoryList = myApp.findSessionById(sessionId).getHeaderContentMandatoryList();
                contentMandatoryTypeFolder = Constants.CONTENT_M_TYPE_SESSION;
                break;
        }


        switch (headerType) {
            case Constants.ABOUT_TYPE_IMAGE:
                Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_IMAGE);
                if (contentMandatoryList == null || contentMandatoryList.get(0) == null) {
                    break;
                }

                ImageView imageView = (ImageView) new AboutUtil(this).inflateImage(contentMandatoryList.get(0), contentMandatoryTypeFolder);
                if (imageView != null) {
                    parentView.addView(imageView);
                }

                break;
            case Constants.ABOUT_TYPE_IMAGE_SLIDER:
                Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_IMAGE_SLIDER);

                ViewGroup imageSliderView = inflateImageSlider(contentMandatoryList, contentMandatoryTypeFolder, null);
                if (imageSliderView != null) {
                    parentView.addView(imageSliderView);
                }
                break;
            case Constants.ABOUT_TYPE_YOUTUBE:
                Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_YOUTUBE);

                String youtubeUrl = null;
                switch (screenType) {
                    case Constants.SCREEN_MENU:
                        youtubeUrl = (String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_YOUTUBE_URL);
                        break;
                    case Constants.SCREEN_SESSION_DETAIL:
                        youtubeUrl = myApp.findSessionById(sessionId).getYoutubeUrl();
                        break;
                }

                if (youtubeUrl != null) {
                    ViewGroup youtubeView = new AboutUtil(this).inflateYoutube(youtubeUrl, parentView);
                    if (youtubeView != null) {
                        parentView.addView(youtubeView);
                    }
                }

                break;
            case Constants.ABOUT_TYPE_VIDEO:
                Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_VIDEO);
                if (contentMandatoryList == null || contentMandatoryList.get(0) == null) {
                    break;
                }
                String contentMandatoryId2 = contentMandatoryList.get(0);

                ViewGroup videoView = new AboutUtil(this).inflateVideo(contentMandatoryId2, parentView);
                if (videoView != null) {
                    parentView.addView(videoView);
                }

                break;
            case Constants.ABOUT_TYPE11_COUNTDOWN:
                ViewGroup ctdView = new AboutUtil(this).inflateCountdownTimer();
                if (ctdView != null) {
                    parentView.addView(ctdView);
                }
                break;
            default:
                break;
        }
    }

    public void inflateDescription(String screenType, String id, LinearLayout parentView, List<About> aboutList) {
        if (aboutList != null && aboutList.size() > 0) {
            Log.i(Constants.APP_NAME, className + "   aboutList.size()   " + aboutList.size());
            *//* Sort entire list by position *//*
//            Collections.sort(aboutList, new Comparator<About>() {
//                public int compare(About about1, About about2) {
//                    return about1.getPosition() - about2.getPosition();
//                }
//            });

            for (int i = 0; i < aboutList.size(); i++) {
                final About tempAbout = aboutList.get(i);
                if (tempAbout != null) {
                    Log.i(Constants.APP_NAME, className + "   " + tempAbout.getType());
                }

            }

            *//* Iterate list & inflate view *//*
            for (int i = 0; i < aboutList.size(); i++) {
                final About tempAbout = aboutList.get(i);
                if (tempAbout != null) {

                    switch (tempAbout.getType()) {
                        case Constants.ABOUT_TYPE_TEXT:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_TEXT);
                            String text = tempAbout.getText();

                        *//* https://coderanch.com/t/586917/java/Replacing-Span-Tag-String-empty *//*
                            text = text.replaceAll("\\<bold.*?\\>", "<b>");
                            text = text.replaceAll("\\<\\/bold\\>", "</b>");

                        *//* https://stackoverflow.com/a/3056864/2937847 *//*
                            text = text.replaceAll("(\r\n|\n)", "<br />");

                            TextView textView = (TextView) inflater.inflate(R.layout.view_about_text,
                                    null, false);
                            setParams(textView, MARGIN_LEFT, 12, MARGIN_RIGHT, 12);
                            textView.setText(Html.fromHtml(text));
                            parentView.addView(textView);
                            break;
                        case Constants.ABOUT_TYPE_LIST:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_LIST);

                            View textListView = new AboutUtil(this).inflateListText(tempAbout.getListWithinListList());
                            if (textListView != null) {
                                setParams(textListView, MARGIN_LEFT, 12, MARGIN_RIGHT, 12);
                                parentView.addView(textListView);
                            }
                            break;
                        case Constants.ABOUT_TYPE_URL:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_URL);

                            View urlView = new AboutUtil(this).inflateURL(tempAbout.getUrl(), tempAbout.getUrlAlias());
//                            View urlView = inflateURL(tempAbout.getUrl(), tempAbout.getUrlAlias());
                            if (urlView != null) {
                                setParams(urlView, MARGIN_LEFT, 12, MARGIN_RIGHT, 12);
                                parentView.addView(urlView);
                            }
                            break;
                        case Constants.ABOUT_TYPE_IMAGE:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_IMAGE);
                            ImageView imageView = (ImageView) inflater.inflate(R.layout.view_about_image,
                                    null, false);
                            if (tempAbout.getPosition() != 1) {
                                setParams(imageView, 0, 12, 0, 0);
                            }
                            String contentMandatoryId = tempAbout.getContentMandatoryList().get(0);
                            ContentMandatory tempContentMandatory = myApp.findContentMandatoryById(contentMandatoryId);

                            if (tempContentMandatory == null) {
                                break;
                            }

                            String filename = tempContentMandatory.getFilename();

                            File imageFile = new File(MyApp.ROOT_APP + "/" + currentEvent.getId() + "/" + Constants.STORAGE_FOLDER_CONTENT_MANDATORY + Constants.CONTENT_M_TYPE_ABOUT + "/" + filename);
                            if (imageFile.exists()) {
                                Uri uri = Uri.fromFile(imageFile);
                                imageView.setImageURI(uri);
                            }
                            parentView.addView(imageView);
                            break;
                        case Constants.ABOUT_TYPE_IMAGE_SLIDER:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_IMAGE_SLIDER);
                            ViewGroup imageSliderView = inflateImageSlider(tempAbout.getContentMandatoryList(), Constants.CONTENT_M_TYPE_ABOUT, null);
                            if (imageSliderView != null) {
                                setParams(imageSliderView, 0, 12, 0, 12);
                                parentView.addView(imageSliderView);
                            }
                            break;
                        case Constants.ABOUT_TYPE_YOUTUBE:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_YOUTUBE);

                            ViewGroup youtubeView = new AboutUtil(this).inflateYoutube(tempAbout.getUrl(), parentView);
                            if (youtubeView != null) {
                                setParams(youtubeView, 0, 12, 0, 12);
                                parentView.addView(youtubeView);
                            }
                            break;
                        case Constants.ABOUT_TYPE_VIDEO:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_VIDEO);

                            ViewGroup videoView = new AboutUtil(this).inflateVideo(tempAbout.getContentMandatoryList().get(0), null);
                            if (videoView != null) {
                                setParams(videoView, 0, 12, 0, 12);
                                parentView.addView(videoView);
                            }

                            break;
                        case Constants.ABOUT_TYPE_SPEAKERS:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_SPEAKERS);
                        *//* https://stackoverflow.com/a/12859616/2937847 *//*

                            if (currentEvent.getSpeakerList().size() > 0) {
                                LinearLayout speakers_LL = (LinearLayout) inflater.inflate(R.layout.view_about_speakers,
                                        null, false);
                                setParams(speakers_LL, MARGIN_LEFT, 12, MARGIN_RIGHT, 12);
                                parentView.addView(speakers_LL);

                                ExpandableHeightGridView expandableHeightGridView = (ExpandableHeightGridView) speakers_LL.findViewById(R.id.speakers_EHGV);

                                SpeakerAdapter2 speakerAdapter = new SpeakerAdapter2(this, currentEvent.getSpeakerList());
                                expandableHeightGridView.setAdapter(speakerAdapter);
                                expandableHeightGridView.setExpanded(true);
                            }
                            break;
                        case Constants.ABOUT_TYPE_SPONSORS:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_SPONSORS);

                            break;
                        case Constants.ABOUT_TYPE_TIME_V:
                        *//* https://stackoverflow.com/a/29698129/2937847 *//*
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_TIME_V);

                            LinearLayout timeAndLocation_LL = (LinearLayout) inflater.inflate(R.layout.view_about_time_location,
                                    null, false);
//                        setParams(timeAndLocation_LL, MARGIN_LEFT, 12, MARGIN_RIGHT, 12);
                            if (tempAbout.getPosition() != 1) {
                                setParams(timeAndLocation_LL, 0, 12, 0, 0);
                            }
                            TextView date_TV = (TextView) timeAndLocation_LL.findViewById(R.id.date_TV);
                            TextView dateDescription_TV = (TextView) timeAndLocation_LL.findViewById(R.id.dateDescription_TV);
                            TextView locationName_TV = (TextView) timeAndLocation_LL.findViewById(R.id.locationName_TV);
                            TextView locationAddress_TV = (TextView) timeAndLocation_LL.findViewById(R.id.locationAddress_TV);
                            ImageView navigate_IV = (ImageView) timeAndLocation_LL.findViewById(R.id.navigate_IV);

                            BasicInfo basicInfo = currentEvent.getBasicInfo();
                            String beginDateString = DateFormatter.formatDateToString5(DateFormatter.formatStringToDateEvent(basicInfo.getStartTime()), basicInfo.getEndTime());
                            date_TV.setText(beginDateString);

                            String asd = DateFormatter.getDateStatusString(DateFormatter.formatStringToDateEvent(basicInfo.getStartTime()));
                            if (asd != null) {
                                dateDescription_TV.setText(asd);
                            } else {
                                int noOfDays = DateFormatter.getDaysDifferenceFromDate(DateFormatter.formatStringToDateEvent(basicInfo.getStartTime()));
                                dateDescription_TV.setText(noOfDays + " days to go");
                            }
                            locationName_TV.setText(basicInfo.getLocationShort());

                            if (basicInfo.getLocationAddress() != null) {
                                locationAddress_TV.setText(basicInfo.getLocationAddress());
                            } else {
                                locationAddress_TV.setVisibility(View.GONE);
                            }

                            navigate_IV.setPivotX(navigate_IV.getWidth() / 2);
                            navigate_IV.setPivotY(navigate_IV.getHeight() / 2);
                            navigate_IV.setRotation(45);

                            parentView.addView(timeAndLocation_LL);

                            FragmentManager fm = getSupportFragmentManager();
                            SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
                            fm.beginTransaction().replace(R.id.mapContainer, supportMapFragment).commit();

//                        supportMapFragment.getMapAsync(this);
                            break;
                        case Constants.ABOUT_TYPE11_COUNTDOWN:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE11_COUNTDOWN);

                            ViewGroup ctdView = new AboutUtil(this).inflateCountdownTimer();
                            if (ctdView != null) {
                                setParams(ctdView, 0, 12, 0, 0);
                                parentView.addView(ctdView);
                            }
                            break;
                        case Constants.ABOUT_TYPE_CARDS:
                            Log.i(Constants.APP_NAME, className + Constants.ABOUT_TYPE_CARDS);

                            ViewGroup cardView = new AboutUtil(this).inflateCards(tempAbout.getListItems());
                            if (cardView != null) {
                                setParams(cardView, 0, 12, 0, 0);
                                parentView.addView(cardView);
                            }
                            break;
                    }
                }
            }

        }
    }
*/
    private static int MARGIN_LEFT = 10;
    private static int MARGIN_RIGHT = 10;

    private int getPxFromDp(int dp) {
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
        return px;
    }

    private void setParams(View view, int left, int top, int right, int bottom) {
        /* https://stackoverflow.com/a/11971553/2937847 */
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(getPxFromDp(left), getPxFromDp(top), getPxFromDp(right), getPxFromDp(bottom));
        view.setLayoutParams(params);
    }


}
