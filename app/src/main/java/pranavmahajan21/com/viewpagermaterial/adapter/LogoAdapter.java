package pranavmahajan21.com.viewpagermaterial.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import pranavmahajan21.com.viewpagermaterial.MyApp;
import pranavmahajan21.com.viewpagermaterial.R;
import pranavmahajan21.com.viewpagermaterial.model.LogoColor;


/**
 * Created by pranav on 12/9/16.
 */
public class LogoAdapter extends RecyclerView.Adapter<LogoAdapter.MyViewHolder> {
    //    https://www.google.co.in/search?q=android+recyclerview+onitemclicklistener&oq=android+recyclerview+onitemclicklistener&aqs=chrome..69i57j69i59j0j69i64.6971j0j1&sourceid=chrome&ie=UTF-8
    String className = "MenuRecyclerAdapter      ";

    private Context mContext;
    private List<LogoColor> logoColorList;
    boolean hasCard = true;
    MyApp myApp;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView logo_IV;

        public MyViewHolder(View view) {
            /* https://stackoverflow.com/a/30285361/2937847 */
            super(view);
            logo_IV = (ImageView) view
                    .findViewById(R.id.logo_IV);


        }
    }

    public LogoAdapter(Context mContext, List<LogoColor> logoColorList) {
        this.mContext = mContext;
        this.logoColorList = logoColorList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.element_logo, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final LogoColor tempMenuItem = logoColorList.get(position);


        holder.logo_IV.setBackgroundColor(Color.parseColor(tempMenuItem.getBgColor()));
        holder.logo_IV.setColorFilter(Color.parseColor(tempMenuItem.getIconColor()), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public int getItemCount() {
        return logoColorList.size();
    }
}