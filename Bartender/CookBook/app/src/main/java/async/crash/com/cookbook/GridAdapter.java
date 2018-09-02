package async.crash.com.cookbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by mitchthornton on 4/8/18.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private Integer[] imageIds = {R.drawable.img_6771, R.drawable.img_6806, R.drawable.img_6914, R.drawable.linkedinphoto};




    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return imageIds;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


  //      convertView = mInflater.inflate(R.layout.customgridView)


        ImageView iview;


//        final Post post = post[position];


        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.customgridview_adapter, null);

//            iview = new ImageView(context);
//            iview.setLayoutParams(new GridView.LayoutParams(250,300));
//            iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            iview.setPadding(5, 5, 5, 5);
        }else {
            iview = (ImageView) view;
        }
        final ImageView imageView = (ImageView)view.findViewById(R.id.gv_img);

        imageView.setImageResource(imageIds[position]);
        return imageView;
    }
}
