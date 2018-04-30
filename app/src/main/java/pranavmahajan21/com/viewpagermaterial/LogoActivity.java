package pranavmahajan21.com.viewpagermaterial;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pranavmahajan21.com.viewpagermaterial.adapter.LogoAdapter;
import pranavmahajan21.com.viewpagermaterial.adapter.MenuRecyclerAdapter;
import pranavmahajan21.com.viewpagermaterial.model.LogoColor;

public class LogoActivity extends AppCompatActivity {

    @BindView(R.id.logo_RV)
    RecyclerView logo_RV;

    static int spanCount = 3; // 3 columns
    static int spacing = 30; // 50px
    boolean includeEdge = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);


        List<LogoColor> logoColors = new ArrayList<LogoColor>();
        logoColors.add(new LogoColor("#00796b", "#FFD31C"));
        logoColors.add(new LogoColor("#00796b", "#FCDD51"));
        logoColors.add(new LogoColor("#00796b", "#FBE51B"));
        logoColors.add(new LogoColor("#00796b", "#FFD400"));
        logoColors.add(new LogoColor("#00796b", "#fdd300"));

        logoColors.add(new LogoColor("#FFD31C", "#00796b"));
        logoColors.add(new LogoColor("#FCDD51", "#00796b"));
        logoColors.add(new LogoColor("#FBE51B", "#00796b"));
        logoColors.add(new LogoColor("#FFD400", "#00796b"));
        logoColors.add(new LogoColor("#fdd300", "#00796b"));

        logoColors.add(new LogoColor("#0154FF", "#E725FF"));
        logoColors.add(new LogoColor("#0154FF", "#E725FF"));

        LogoAdapter adapter = new LogoAdapter(this, logoColors);

        logo_RV.setLayoutManager(new GridLayoutManager(this, spanCount));
        logo_RV.setItemAnimator(new DefaultItemAnimator());
        logo_RV.setAdapter(adapter);
        logo_RV.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

}
