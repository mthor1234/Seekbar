package async.crash.com.cookbook.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import async.crash.com.cookbook.R;

/**
 * Created by mitchthornton on 4/9/18.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    // Array to hold images
    public Integer[] mThumbIds = {
            R.drawable.img_6641,
            R.drawable.img_6647,
            R.drawable.img_6659,
            R.drawable.img_6660,
            R.drawable.img_6661,
            R.drawable.img_6662,
            R.drawable.img_6666,
            R.drawable.img_6669,
    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }
}
