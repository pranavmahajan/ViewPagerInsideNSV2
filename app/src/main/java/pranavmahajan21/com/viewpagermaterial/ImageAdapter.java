package pranavmahajan21.com.viewpagermaterial;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by pranav on 12/9/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context mContext;
    List<Integer> imageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail, overflow, phoneNumber_IV;

        public MyViewHolder(View view) {
            /* https://stackoverflow.com/a/30285361/2937847 */
            super(view);
            phoneNumber_IV = (ImageView) view.findViewById(R.id.image_IV);

        }
    }


    public ImageAdapter(Context mContext, List<Integer> imageList) {
        this.mContext = mContext;
        this.imageList = imageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        int tempImg = imageList.get(position);
        holder.phoneNumber_IV.setImageDrawable(ContextCompat.getDrawable(mContext, tempImg));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}