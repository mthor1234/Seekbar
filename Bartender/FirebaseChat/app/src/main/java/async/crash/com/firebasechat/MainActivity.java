package async.crash.com.firebasechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private EditText editMessage;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMessage = (EditText) findViewById(R.id.editMessageE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");

    }

    public void sendButtonClicked(View view){
        final String messageValue = editMessage.getText().toString().trim();

        if (!TextUtils.isEmpty(messageValue)){

            final DatabaseReference newPost = mDatabase.push();
            newPost.child("content").setValue(messageValue);
        }

    }
}
