package pranavmahajan21.com.viewpagermaterial;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    private static String APP_NAME = "rurtyry";

    protected List<Student> studentList;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    protected List<Student> studentList75Above;
    protected List<Student> studentList50Above;
    protected List<Student> studentList25Above;
    protected List<Student> studentList0Above;
    protected List<Student> selectedConditionList;


    public class ViewPagerAdapter extends FragmentPagerAdapter {
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
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        studentList = new ArrayList<Student>();

        studentList.add(new Student(1, "Student1", 95));
        studentList.add(new Student(2, "Student2", 85));
        studentList.add(new Student(3, "Student3", 15));
        studentList.add(new Student(4, "Student4", 65));
        studentList.add(new Student(5, "Student5", 55));
        studentList.add(new Student(6, "Student6", 65));
        studentList.add(new Student(7, "Student7", 85));
        studentList.add(new Student(8, "Student8", 95));
        studentList.add(new Student(9, "Student9", 5));
        studentList.add(new Student(10, "Student10", 15));
        studentList.add(new Student(11, "Student11", 20));
        studentList.add(new Student(12, "Student12", 70));
        studentList.add(new Student(13, "Student13", 80));
        studentList.add(new Student(14, "Student14", 35));
        studentList.add(new Student(15, "Student15", 30));
        studentList.add(new Student(16, "Student16", 45));

        studentList0Above = new ArrayList<>();
        studentList25Above = new ArrayList<>();
        studentList50Above = new ArrayList<>();
        studentList75Above = new ArrayList<>();

        for (int i = 0; i < studentList.size(); i++) {
            Student tempStudent = studentList.get(i);
            if (tempStudent.getMarks() > 75) {
                studentList75Above.add(tempStudent);
            } else if (tempStudent.getMarks() > 50) {
                studentList50Above.add(tempStudent);
            } else if (tempStudent.getMarks() > 25) {
                studentList25Above.add(tempStudent);
            } else {
                studentList0Above.add(tempStudent);
            }
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);


        final ViewPagerAdapter adapterVP = new ViewPagerAdapter(getSupportFragmentManager());

        if (studentList75Above.size() > 0) {
            adapterVP.addFragment(new StudentListFragment(), "75+");
            selectedConditionList = selectedConditionList == null ? studentList75Above : selectedConditionList;
        }
        if (studentList50Above.size() > 0) {
            adapterVP.addFragment(new StudentListFragment(), "50+");
            selectedConditionList = selectedConditionList == null ? studentList50Above : selectedConditionList;
        }
        if (studentList25Above.size() > 0) {
            adapterVP.addFragment(new StudentListFragment(), "25+");
            selectedConditionList = selectedConditionList == null ? studentList25Above : selectedConditionList;
        }
        if (studentList0Above.size() > 0) {
            adapterVP.addFragment(new StudentListFragment(), "0+");
            selectedConditionList = selectedConditionList == null ? studentList0Above : selectedConditionList;
        }


        viewPager.setAdapter(adapterVP);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(APP_NAME, "onPageScrolled()       " + position);


            }

            @Override
            public void onPageSelected(int position) {
                Log.i(APP_NAME, "onPageSelected()       " + position);
                assignListAccordingToPosition(position);
                List<Fragment> allFragments = getSupportFragmentManager().getFragments();
                if (allFragments.get(position) instanceof StudentListFragment) {
                    StudentListFragment studentListFragment = (StudentListFragment) allFragments.get(position);
                    if (studentListFragment.getAdapter() != null) {
                        studentListFragment.getAdapter().notifyDataSetChanged();
                    }
                }

                adapterVP.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void assignListAccordingToPosition(int position) {
        if (position == 0) {
            selectedConditionList = studentList75Above.size() > 0 ? studentList75Above : new ArrayList<Student>();
            System.out.println("Selected List " + selectedConditionList.size());
        } else if (position == 1) {
            selectedConditionList = studentList50Above.size() > 0 ? studentList50Above : new ArrayList<Student>();
            System.out.println("Selected List " + selectedConditionList.size());
        } else if (position == 2) {
            selectedConditionList = studentList25Above.size() > 0 ? studentList25Above : new ArrayList<Student>();
            System.out.println("Selected List " + selectedConditionList.size());
        } else if (position == 3) {
            selectedConditionList = studentList25Above.size() > 0 ? studentList0Above : new ArrayList<Student>();
            System.out.println("Selected List " + selectedConditionList.size());
        }

    }

}
