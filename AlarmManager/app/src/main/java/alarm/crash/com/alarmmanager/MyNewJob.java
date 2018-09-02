package alarm.crash.com.alarmmanager;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

public class MyNewJob extends JobService {

    public MyNewJob() {
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i("Job", "Started");
        Toast.makeText(this, "Job Started", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i("Job", "Stopped");

        Toast.makeText(this, "Job Stopped", Toast.LENGTH_LONG).show();
        return false;
    }
}
