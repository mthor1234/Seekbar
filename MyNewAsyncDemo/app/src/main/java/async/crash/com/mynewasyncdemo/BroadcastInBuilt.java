package async.crash.com.mynewasyncdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastInBuilt extends BroadcastReceiver {

    public BroadcastInBuilt() {
    }



    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Battery is Low, Please Charge", Toast.LENGTH_SHORT);

        throw new UnsupportedOperationException("Not yet implemented");
    }


}
