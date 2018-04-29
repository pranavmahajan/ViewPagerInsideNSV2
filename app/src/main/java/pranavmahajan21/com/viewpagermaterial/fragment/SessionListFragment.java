package pranavmahajan21.com.viewpagermaterial.fragment;

/**
 * Created by pranavmahajan21 on 2/17/18.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pranavmahajan21.com.viewpagermaterial.R;
import pranavmahajan21.com.viewpagermaterial.SessionTabListActivity;
import pranavmahajan21.com.viewpagermaterial.adapter.SessionRVAdapter;
import pranavmahajan21.com.viewpagermaterial.model.Session;
import pranavmahajan21.com.viewpagermaterial.util.Constants;
import pranavmahajan21.com.viewpagermaterial.util.ItemClickSupport;


public class SessionListFragment extends Fragment {
    String className = "SessionFrag      ";

    //    https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android

    private RecyclerView sessions_RV;
    SessionTabListActivity activity;
    private SessionRVAdapter sessionRVAdapter;

    Intent nextIntent;
    private int mColumnCount = 1;

    public SessionListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (SessionTabListActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        sessions_RV = (RecyclerView) view.findViewById(R.id.sessions_RV);

//        if (view instanceof RecyclerView) {
        Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
        if (mColumnCount <= 1) {
            sessions_RV.setLayoutManager(new LinearLayoutManager(context));
        } else {
            sessions_RV.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        sessionRVAdapter = new SessionRVAdapter(getActivity(), activity.listInAction);
        sessions_RV.setAdapter(sessionRVAdapter);

        ItemClickSupport.addTo(sessions_RV).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.i(Constants.APP_NAME, className + "   position : " + position);
                Session tempSession = activity.listInAction.get(position);

                Toast.makeText(getActivity(), tempSession.getName(), Toast.LENGTH_SHORT).show();

                if (tempSession.getHasDetailsScreen()) {

                }
            }
        });
//        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionRVAdapter.notifyDataSetChanged();
    }

    public SessionRVAdapter getSessionRVAdapter() {
        return sessionRVAdapter;
    }

    public void setSessionRVAdapter(SessionRVAdapter sessionRVAdapter) {
        this.sessionRVAdapter = sessionRVAdapter;
    }
}