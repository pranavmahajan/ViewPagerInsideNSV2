package pranavmahajan21.com.viewpagermaterial;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParentActivity extends AppCompatActivity {

    LayoutInflater inflater;

    public ViewGroup inflateHeader() {
        RelativeLayout imageCarousel_LL = null;
        int[] sampleImages = {R.drawable.education_summit, R.drawable.exhibition2016};


        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageCarousel_LL = (RelativeLayout) inflater.inflate(R.layout.view_about_image_carousel2,
                null, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        final ViewPager carousel_ViewP = (ViewPager) imageCarousel_LL.findViewById(R.id.carousel_ViewP);
        final NestedScrollView scrollParent_SV = (NestedScrollView) findViewById(R.id.scrollParent_SV);


                        /* Set height of ViewPager */
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.height = (displayMetrics.heightPixels / 100) * 40;
        carousel_ViewP.setLayoutParams(layoutParams);

                    /* Init ViewPager */
        ViewPageAdapter mAdapter = new ViewPageAdapter((getApplicationContext()), sampleImages, displayMetrics.heightPixels, displayMetrics.widthPixels);
        carousel_ViewP.setAdapter(mAdapter);
        carousel_ViewP.setCurrentItem(0);

                        /* Init pager indicator view */
        LinearLayout pager_indicator = (LinearLayout) imageCarousel_LL.findViewById(R.id.viewPagerCountDots);
        final int dotsCount = mAdapter.getCount();
        final ImageView[] dots = new ImageView[dotsCount];

//                        /* https://stackoverflow.com/a/28489543/2937847 */
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
                            scrollParent_SV.getParent().requestDisallowInterceptTouchEvent(true);
                        } else if (distanceX > distanceY && distanceX > dragthreshold) {
                            carousel_ViewP.getParent().requestDisallowInterceptTouchEvent(true);
                            scrollParent_SV.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        scrollParent_SV.getParent().requestDisallowInterceptTouchEvent(false);
                        carousel_ViewP.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
        return imageCarousel_LL;
//        }
    }

    private void setPagerDot(LinearLayout pager_indicator, int dotsCount, ImageView[] dots, int colorPrimary, int colorAccent) {

        /* https://stackoverflow.com/a/28489543/2937847 */
//        TypedValue typedValue = new TypedValue();
//        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
//        final int colorPrimary = typedValue.data;
//        getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
//        final int colorAccent = typedValue.data;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
    }
}
