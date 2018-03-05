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


public class StudentListFragment extends Fragment{

    private RecyclerView student_RV;
    private StudentRVAdapter adapter;


    public StudentListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new StudentRVAdapter(getActivity(), studentList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        student_RV = (RecyclerView) getActivity().findViewById(R.id.student_RV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        student_RV.setLayoutManager(mLayoutManager);
        student_RV.setItemAnimator(new DefaultItemAnimator());
        student_RV.setAdapter(adapter);

    }
}