package alarm.crash.com.alarmmanager;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    ToggleButton tg;
    JobScheduler myJob;
    JobInfo myJobInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tg = (ToggleButton) findViewById(R.id.togglebutton);

        tg.setOnCheckedChangeListener(this);

        myJob = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            JobInfo.Builder myJobBuilder = new JobInfo.Builder(1, new ComponentName(getPackageName(), MyNewJob.class.getName()))
                    .setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setRequiresDeviceIdle(false);

            myJobInfo = myJobBuilder.build();
            myJob.schedule(myJobInfo);
        }
        else{
            myJob.cancelAll();

        }
    }

    public void startSomething(View view){

    }

    public void doSomething(View view){

    }

}
