package async.crash.com.wheelcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static MainActivity instance = null;

    // Used to adjust the aspect ratio of the tire preview image
    private int img_Tire_Diameter;
    private int img_Tire_Sidewall;
    private int img_Rim_Diameter;

    private ImageView iv_tirePreview_tire;
    private ImageView iv_tirePreview_rim;
    private ImageView iv_tirePreview_sidewall;
    private ImageView iv_tirePreviewSide;


    // Keeps track of the original dimesions of the tire preview images
    private int iv_tirePreview_tire_original_width;
    private int iv_tirePreview_tire_original_height;
    private int iv_tirePreview_rim_original_width;
    private int iv_tirePreview_rim_original_height;
    private int iv_tirePreview_sidewall_original_width;
    private int iv_tirePreview_sidewall_original_height;
    private int iv_tirePreviewSide_original_width;
    private int iv_tirePreviewSide_original_height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button btnCalculate = (Button) findViewById(R.id.btn_calculate);
        final EditText et_width = (EditText) findViewById(R.id.et_width);
        final EditText et_aspectRatio = (EditText) findViewById(R.id.et_aspect_ratio);
        final EditText et_radius = (EditText) findViewById(R.id.et_inner_radius);

        final TextView tv_width = (TextView) findViewById(R.id.tv_tire_width);
        final TextView tv_height = (TextView) findViewById(R.id.tv_tire_size);
        final TextView tv_radius = (TextView) findViewById(R.id.tv_inner_radius);
        final TextView tv_sideWallHeight = (TextView) findViewById(R.id.tv_sidewall_size);

        iv_tirePreview_tire = (ImageView) findViewById(R.id.iv_front_tire_tread);
        iv_tirePreview_rim = (ImageView) findViewById(R.id.iv_front_tire_rim);
        iv_tirePreview_sidewall = (ImageView) findViewById(R.id.iv_front_tire_sidewall);
        iv_tirePreviewSide = (ImageView) findViewById(R.id.iv_sideview_tire);



        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // TODO: Each time the calculate button is clicked, the tire continues to either shrink or grow
                // Need to reset the tire size each time btn_calculate is clicked


                //Example: 285/75R16 (285 X 75 / 2540 x 2) + 16 = 32.8 inches tall



                double sideWallHeight = Double.parseDouble(String.valueOf(et_width.getText())) *
                        Double.parseDouble(String.valueOf(et_aspectRatio.getText())) / 2540;

                double conversionHeight = (sideWallHeight * 2) +
                        Double.parseDouble(String.valueOf(et_radius.getText()));

                double tire_width = Double.parseDouble(String.valueOf(et_width.getText())) / 25.4;
                double wheel_radius = Double.parseDouble(String.valueOf(et_radius.getText()));


                tv_height.setText(String.format("%.2f", conversionHeight) + "\"");
                tv_width.setText(String.format("%.2f", tire_width) + "\"");
                tv_radius.setText(String.format("%.2f", wheel_radius) + "\"");
                tv_sideWallHeight.setText(String.format("%.2f", sideWallHeight) + "\"");


                // Hides the softkeyboard
                if (instance != null) {
                    hideSoftKeyboard(instance);
                }


                if (handleUserEntries(sideWallHeight, tire_width, wheel_radius)) {
                    adjustTireImageAspectRatio((int) tire_width, (int) wheel_radius, (int) sideWallHeight);
                } else {
                    System.out.println("Something went wrong");
                }

            }
        });
    }

    // Hides three button nenu dropdown
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item= menu.findItem(R.id.action_settings);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        instance = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        instance = null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tire_size) {
            // Handle the camera action
        } else if (id == R.id.nav_speed_calibration) {

        } else if (id == R.id.nav_gear_ratio) {

        } else if (id == R.id.nav_offset) {

        } else if (id == R.id.nav_bolt) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*
    Called when button is clicked to adjust the aspect ratio of all the tire image components
    So they can be scaled properly to provide a seamless preview
     */
    public void adjustTireImageAspectRatio(int tireWidth, int rimSize, int aspectRatio) {

//        java.lang.NullPointerException: Attempt to invoke virtual method 'android.view.ViewGroup$LayoutParams android.widget.ImageView.getLayoutParams()' on a null object reference
//        at async.crash.com.wheelcalculator.MainActivity.adjustTireImageAspectRatio(MainActivity.java:160)



        // Frontview params
        android.view.ViewGroup.LayoutParams frontview_rim_layoutParams = iv_tirePreview_rim.getLayoutParams();
        android.view.ViewGroup.LayoutParams frontview_tread_layoutParams = iv_tirePreview_tire.getLayoutParams();
        android.view.ViewGroup.LayoutParams frontview_sidewall_layoutParams = iv_tirePreview_sidewall.getLayoutParams();

        // Sideview params
        android.view.ViewGroup.LayoutParams sideview_tire_layoutParams = iv_tirePreviewSide.getLayoutParams();

        frontview_rim_layoutParams.width = frontview_rim_layoutParams.width * rimSize / 16;

        System.out.println(tireWidth);  //10
        System.out.println(sideview_tire_layoutParams.width); //800
        System.out.println((int) Math.round(sideview_tire_layoutParams.width * tireWidth / 10.50)); //762

        sideview_tire_layoutParams.width = (int) Math.round(sideview_tire_layoutParams.width * tireWidth / 10.50);

        // Tire height formula
        //Example: 285/75R16 (285 X 75 / 2540 x 2) + 16 = 32.8 inches tall.

        img_Tire_Diameter = (tireWidth * aspectRatio / 2540 * 2) + rimSize;
        img_Tire_Sidewall = tireWidth * aspectRatio / 2540;
        img_Rim_Diameter = rimSize;

        // iv_tirePreview_rim.setLayoutParams(frontview_rim_layoutParams);
        // iv_tirePreviewSide.setLayoutParams(sideview_tire_layoutParams);
    }


    // Used to hide the softkeyboard
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public boolean handleUserEntries(double width, double aspectRatio, double rim_diameter) {

        // Used for checking whether the user's entry was sufficient
        boolean boolValue = false;

        if (aspectRatio >= 20 || aspectRatio <= 100) {
            boolValue = true;
        } else {
            Toast.makeText(instance, "Aspect Ratio must be between 25 - 100",
                    Toast.LENGTH_LONG).show();
            boolValue = false;
        }

        if (width >= 10 || width <= 40) {
            boolValue = true;
        } else {
            Toast.makeText(instance, "Width must between 10 - 40",
                    Toast.LENGTH_LONG).show();
            boolValue = false;
        }

        if (rim_diameter >= 10 ||
                rim_diameter <= 30) {
            boolValue = true;
        } else {
            Toast.makeText(instance, "Rim diameter must be between 10 - 30",
                    Toast.LENGTH_LONG).show();
            boolValue = false;
        }


        return boolValue;
    }


}
