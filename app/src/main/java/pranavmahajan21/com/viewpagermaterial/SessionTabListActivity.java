package pranavmahajan21.com.viewpagermaterial;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pranavmahajan21.com.viewpagermaterial.fragment.SessionListFragment;
import pranavmahajan21.com.viewpagermaterial.model.Event;
import pranavmahajan21.com.viewpagermaterial.model.Session;
import pranavmahajan21.com.viewpagermaterial.util.Constants;
import pranavmahajan21.com.viewpagermaterial.util.DateFormatter;

public class SessionTabListActivity extends DaddySessionActivity {

    String className = "SessionTabListActivity      ";

    Event tempEvent;

    List<String> listDataHeader;
    LinkedHashMap<String, List<Session>> listDataChild;

    TextView date_TV;



    private TabLayout tabLayout;
    private ViewPager viewPager;

    public List<Session> listInAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_tab);
        initThings();
//        setToolbar(currentMenuItem.getAliasName(), false, null);
        findThings();
        initView();
        initListeners();


    }
    private void prepareContentsForList() {
        //            https://stackoverflow.com/questions/4011075/how-do-you-format-the-day-of-the-month-to-say-11th-21st-or-23rd-in-java
        listDataChild = new LinkedHashMap<String, List<Session>>();
        listDataHeader = new ArrayList<String>();

        List<Date> tempDateListForSorting = new ArrayList<Date>();

        for (int j = 0; j < tempEvent.getSessionList().size(); j++) {
            Session tempSession = tempEvent.getSessionList().get(j);

            Date beginDate = DateFormatter.formatStringToDateEvent(tempSession.getStartTime());

            if (beginDate != null) {
                String dateString = getStringFromDate(beginDate);
                if (!listDataChild.keySet().contains(dateString)) {
                    tempDateListForSorting.add(beginDate);

                    List<Session> tempSessionList = new ArrayList<Session>();
                    tempSessionList.add(tempSession);
                    listDataChild.put(dateString, tempSessionList);
                } else {
                    List<Session> tempSessionList = listDataChild.get(dateString);
                    tempSessionList.add(tempSession);
                    listDataChild.put(dateString, tempSessionList);
                }

            } else {
                Log.e(Constants.APP_NAME, className + "prepareContentsForList()         THIS SHOULD NEVER BE REACHED        Session startDate is NULL");
            }
        }//for

        /* 1.Sort List<Dates>
         * 2.Convert dates to strings
         * 3.Remove duplicate strings
         * https://stackoverflow.com/a/203992/2937847
          * */
        Collections.sort(tempDateListForSorting);
        for (int i = 0; i < tempDateListForSorting.size(); i++) {
            String dateString = DateFormatter.getStringFromDate(tempDateListForSorting.get(i));
            listDataHeader.add(dateString);
        }
        Set<String> setDataHeader = new LinkedHashSet<>(listDataHeader);
        if (setDataHeader.size() != tempDateListForSorting.size()) {
            Log.e(Constants.APP_NAME, className + "prepareContentsForList()         THIS SHOULD NEVER BE REACHED2");
        }
        listDataHeader.clear();
        listDataHeader.addAll(setDataHeader);
    }

    public void initThings() {
        super.initThings();

        currentMenuItem = myApp.findMenuItemByRealName(Constants.MENU_ITEM_SESSION);

        tempEvent = myApp.getCurrentEvent();

        if (tempEvent.getSessionList() != null && tempEvent.getSessionList().size() > 0) {
            prepareContentsForList();
        }
    }

    private void findThings() {
//        sessions_ELV = (ExpandableListView) findViewById(R.id.sessions_ELV);
        date_TV = (TextView) findViewById(R.id.date_TV);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
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
    }

    public void initView() {
        super.initView();

        Log.i(Constants.APP_NAME, className + "initView()");

        if (listDataHeader != null && listDataHeader.size() > 0) {
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);

            tabLayout.setTabTextColors(ColorUtils.blendARGB(colorPrimary, Color.parseColor("#FFFFFF"), 0.5F), Color.parseColor("#FFFFFF"));

            listInAction = new ArrayList<>();
            assignListAccordingToPosition(0);
//            listInAction.add((Session) new ArrayList<List<Session>>(listDataChild.values()).get(0));

            final ViewPagerAdapter adapterVP = new ViewPagerAdapter(getSupportFragmentManager());

            List<String> keys = new ArrayList<String>(listDataChild.keySet());
            for (int i = 0; i < keys.size(); i++) {
//            adapterVP.addFragment(new SessionListFragment(), keys.get(i));
//            adapterVP.addFragment(new SessionListFragment(), keys.get(i));
                adapterVP.addFragment(new SessionListFragment(), "Day " + (i + 1));
            }


            Log.i(Constants.APP_NAME, className + "initView()   " + adapterVP.getCount());

            viewPager.setAdapter(adapterVP);

            tabLayout.setupWithViewPager(viewPager);
            viewPager.setOffscreenPageLimit(3);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    Log.i(Constants.APP_NAME, className + "onPageScrolled()       " + position);

                }

                @Override
                public void onPageSelected(int position) {
                    Log.i(Constants.APP_NAME, className + "onPageSelected()       " + position);

                    date_TV.setText(listDataHeader.get(position));
                    List<Fragment> allFragments = getSupportFragmentManager().getFragments();
                    if (allFragments.get(position) instanceof SessionListFragment) {
                        SessionListFragment studentListFragment = (SessionListFragment) allFragments.get(position);
                        if (studentListFragment.getSessionRVAdapter() != null) {

//                            studentListFragment.getSessionRVAdapter().swapData(listInAction);
                            assignListAccordingToPosition(position);
                            studentListFragment.getSessionRVAdapter().notifyDataSetChanged();
                        }
                    }

                    adapterVP.notifyDataSetChanged();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


            String x = DateFormatter.getStringFromDate(new Date());
            final int i = listDataHeader.indexOf(x);

            if (i > -1) {
                date_TV.setText(listDataHeader.get(i));

//                https://stackoverflow.com/a/31452777/2937847
                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                tabLayout.getTabAt(i).select();
                            }
                        }, 100);
            } else {
                date_TV.setText(listDataHeader.get(0));
            }

        } else {
            Log.i(Constants.APP_NAME, "huhuhu ");
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
        }
    }


    public void assignListAccordingToPosition(int position) {
//
        listInAction.clear();
        System.out.println("Position "+listDataChild.values().size());
        List mList = new ArrayList<List<Session>>(listDataChild.values()).get(position);
        System.out.println("List Size "+mList.size());
        listInAction.addAll(mList);
//        if(listDataChild.containsKey(position)) {
//            listInAction.clear();
//            listInAction.addAll(listDataChild.get(position));
//        }
//        listInAction = ((List<List<Session>>) listDataChild.values()).get(position);
    }

    private void initListeners() {
    }

    public List<Session> getListInAction() {
        return listInAction;
    }
}
