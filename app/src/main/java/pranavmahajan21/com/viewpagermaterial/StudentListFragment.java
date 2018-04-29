package pranavmahajan21.com.viewpagermaterial;

/**
 * Created by pranavmahajan21 on 2/17/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StudentListFragment extends Fragment {

    private RecyclerView student_RV;
    private StudentRVAdapter adapter;
    TabActivity mTabActivity;
//    private List

    public StudentRVAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(StudentRVAdapter adapter) {
        this.adapter = adapter;
    }

    public StudentListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_student, container, false);
        mTabActivity = (TabActivity) getActivity();
        student_RV = (RecyclerView) mView.findViewById(R.id.student_RV);
        adapter = new StudentRVAdapter(getActivity(),mTabActivity.selectedConditionList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        student_RV.setLayoutManager(mLayoutManager);
        student_RV.setItemAnimator(new DefaultItemAnimator());
        student_RV.setAdapter(adapter);
        return mView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}