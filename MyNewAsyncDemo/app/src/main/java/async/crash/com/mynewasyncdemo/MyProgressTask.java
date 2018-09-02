package async.crash.com.mynewasyncdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by mitchthornton on 10/10/17.
 */
public class MyProgressTask extends AsyncTask<Void, Integer, String> {


    Context context;
    ProgressDialog pd;

    public MyProgressTask(Context ct){
        context = ct;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setTitle("Downloading");
        pd.setMessage("Please Wait...");
        pd.setMax(10);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                cancel(true);
            }
        });
    }


    @Override
    protected String doInBackground(Void... voids) {

        try{
            for (int i = 0; i <= 10; i++){
                Thread.sleep(1000);
                Log.i("Thread", "Execute " + i);
                publishProgress();
            }
            return "Success";
        } catch (InterruptedException e) {
            Log.i("Exception", e.getMessage());
            return "Failure";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int myValue = values[0];
        pd.setProgress(myValue);
    }


}
