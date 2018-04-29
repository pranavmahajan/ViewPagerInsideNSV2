package pranavmahajan21.com.viewpagermaterial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import pranavmahajan21.com.viewpagermaterial.MyApp;
import pranavmahajan21.com.viewpagermaterial.R;
import pranavmahajan21.com.viewpagermaterial.model.Session;

/**
 * Created by pranav on 12/9/16.
 */
public class SessionRVAdapter extends RecyclerView.Adapter<SessionRVAdapter.MyViewHolder> {

    MyApp myApp;
    Context context;

    List<Session> sessionList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time_TV, name_TV;
        public ImageView ongoingStatus_IV, arrow_IV;
        public LinearLayout favourite_LL, calendar_LL;


        public MyViewHolder(View view) {
            /* https://stackoverflow.com/a/30285361/2937847 */
            super(view);
            time_TV = (TextView) view.findViewById(R.id.time_TV);
            name_TV = (TextView) view.findViewById(R.id.name_TV);

            favourite_LL = (LinearLayout) view.findViewById(R.id.favourite_LL);
            calendar_LL = (LinearLayout) view.findViewById(R.id.calendar_LL);

            ongoingStatus_IV = (ImageView) view.findViewById(R.id.ongoingStatus_IV);
            arrow_IV = (ImageView) view.findViewById(R.id.arrow_IV);
        }
    }


    public SessionRVAdapter(Context context, List<Session> sessionList) {
        super();
        this.context = context;
        this.sessionList = sessionList;
        myApp = (MyApp) context.getApplicationContext();
    }

    public void swapData(List<Session> sessionList){
        this.sessionList = sessionList;
    }

    @Override
    public SessionRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_session3, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SessionRVAdapter.MyViewHolder holder, int position) {
        Session tempSession = sessionList.get(position);

        holder.time_TV.setText(tempSession.getStartTime());
        holder.name_TV.setText(tempSession.getName());


        if (tempSession.getHasDetailsScreen()) {
            holder.arrow_IV.setVisibility(View.VISIBLE);
        } else {
            holder.arrow_IV.setVisibility(View.GONE);
        }

        holder.favourite_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }
}
