package func.types.socket_;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import func.types.R;

/**
 * author-ZKC
 * create on 2016-08-29-14-34.
 */
public class SocketClientActivity extends AppCompatActivity {
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;
    private ClientRunnable mRunnable;
    private Handler MyHandler_SendUI = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                mTextView.append("\n" + msg.obj.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        iniViews();
        mRunnable = new ClientRunnable(MyHandler_SendUI);
        new Thread(mRunnable).start();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = mEditText.getText().toString().trim();
                    mRunnable.mHandler_FromUI.sendMessage(msg);
                    mEditText.setText("");
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }

    private void iniViews() {
        mEditText = (EditText) findViewById(R.id.edit_input);
        mTextView = (TextView) findViewById(R.id.tv_show);
        mButton = (Button) findViewById(R.id.b_send);
    }


}
