package pranavmahajan21.com.viewpagermaterial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.File;
import java.util.List;

/**
 * Created by MW_MANNU on 9/24/2017.
 */

public class ViewPageAdapter extends PagerAdapter {
    ImageView imageView;
    private Context mContext;
    LinearLayout parent_LL, child_LL;
    View itemView;
    int height;
    int width;
    int percentHeight;
    ScrollView scrollParent_SV;
    //    private int[] mResources;
    int[] sampleImages;

    public ViewPageAdapter(Context mContext, int[] sampleImages, int height, int width) {
        this.mContext = mContext;
        this.sampleImages = sampleImages;
        this.height = height;
        this.width = width;
    }

    @Override
    public int getCount() {
        return sampleImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        itemView = LayoutInflater.from(mContext).inflate(R.layout.carosel_item, container, false);
        parent_LL = (LinearLayout) itemView.findViewById(R.id.parent_LL);
        imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        percentHeight = (height / 100) * 50;
        imageView.setImageDrawable(ContextCompat.getDrawable(mContext, sampleImages[position]));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public Bitmap getResizedBitmap(int targetW, int targetH, String imagePath) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        return (bitmap);
    }
//    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
//        int width = bm.getWidth();
//        int height = bm.getHeight();
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // CREATE A MATRIX FOR THE MANIPULATION
//        Matrix matrix = new Matrix();
//        // RESIZE THE BIT MAP
//        matrix.postScale(scaleWidth, scaleHeight);
//        // "RECREATE" THE NEW BITMAP
//        Bitmap resizedBitmap = Bitmap.createBitmap(
//                bm, 0, 0, width, height, matrix, false);
//        bm.recycle();
//        return resizedBitmap;
//    }


}
