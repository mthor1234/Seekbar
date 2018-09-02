package async.crash.com.amazonawsmessaging;

import android.app.Activity;
import android.os.Bundle;

import com.amazonaws.mobile.client.AWSMobileClient;


    public class MainActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            AWSMobileClient.getInstance().initialize(this).execute();
        }
    }

