package async.crash.com.bartenderquiz;

import android.os.Bundle;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MainActivity extends DrawerLayoutActivity {

    private NavDrawerAdapter mNavDrawerAdapter;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private String[] navMenuTitles;

    @Override
    public void init() {

    }

    @Override
    public void displayView(int position, Bundle fragmentBundle) {

    }

    @Override
    protected BaseAdapter getAdapter() {
        return null;
    }
}
