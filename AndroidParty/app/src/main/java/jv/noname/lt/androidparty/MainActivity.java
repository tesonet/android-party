package jv.noname.lt.androidparty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TOKEN_URL = "http://playground.tesonet.lt/v1/tokens";
    private static final String SERVERS_URL = "http://playground.tesonet.lt/v1/servers";

    private Button postButton;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        postButton = (Button) findViewById(R.id.postButton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        postButton.setOnClickListener(new PostButtonOnClickListetener());
    }

    private class PostButtonOnClickListetener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                Toast.makeText(v.getContext(), getResources().getString(R.string.usernameOrPassWarn), Toast.LENGTH_LONG).show();
                return;
            }
            new RetrieveFeedTask(v.getContext(), 0).execute(username.getText().toString(), password.getText().toString(), TOKEN_URL, SERVERS_URL);
        }
    }
}
