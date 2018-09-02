package async.crash.com.mynewasyncdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomBroadcast1 extends BroadcastReceiver {
    public CustomBroadcast1() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "First Recevier Called", Toast.LENGTH_LONG);
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
