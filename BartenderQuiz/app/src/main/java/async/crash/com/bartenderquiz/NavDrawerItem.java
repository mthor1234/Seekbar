package async.crash.com.bartenderquiz;

import android.graphics.drawable.Drawable;

/**
 * Created by mitchthornton on 2/19/18.
 */

public class NavDrawerItem {

    private String title;
    private Drawable icon;

    public NavDrawerItem(String title, Drawable icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
