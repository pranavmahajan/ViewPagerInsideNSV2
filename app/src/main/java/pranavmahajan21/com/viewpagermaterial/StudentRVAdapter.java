package pranavmahajan21.com.viewpagermaterial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranav on 12/9/16.
 */
public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.MyViewHolder> {

    Context context;
    List<Student> studentList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id_TV, name_TV, marks_TV;

        public MyViewHolder(View view) {
            /* https://stackoverflow.com/a/30285361/2937847 */
            super(view);
            id_TV = (TextView) view.findViewById(R.id.id_TV);
            name_TV = (TextView) view.findViewById(R.id.name_TV);
            marks_TV = (TextView) view.findViewById(R.id.marks_TV);
        }
    }


    public StudentRVAdapter(Context context, List<Student> studentList) {
        super();
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public StudentRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_student, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StudentRVAdapter.MyViewHolder holder, int position) {
        Student tempStudent = studentList.get(position);

        holder.id_TV.setText(tempStudent.getId()+"");
        holder.name_TV.setText(tempStudent.getName()+"");
        holder.marks_TV.setText(tempStudent.getMarks()+"");



    }

    @Override
    public int getItemCount() {
        return (studentList.size()>0?studentList.size():0);
    }
}
