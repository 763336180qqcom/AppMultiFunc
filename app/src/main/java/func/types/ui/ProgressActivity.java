package func.types.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import func.types.R;

/**
 * on 2016-08-19.
 */
public class ProgressActivity extends AppCompatActivity {

    private Handler mPgsHandler = new Handler();
    private int mCurrentProgress = 0;
    private int mMaxProgress = 100;
    private ProgressBar mPgsBar;
    private Button mbtnExe;

    protected void onCreate(Bundle saveInstanceSate) {
        super.onCreate(saveInstanceSate);
        setContentView(R.layout.activity_progressbar_);
        iniViews();
    }

    private void iniViews() {
        mPgsBar = (ProgressBar) findViewById(R.id.pgs);
        mbtnExe = (Button) findViewById(R.id.btnTest);
        mbtnExe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mCurrentProgress < mMaxProgress) {
                            try {
                                mCurrentProgress += mMaxProgress / 10;
                                mPgsHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mPgsBar.setProgress(mCurrentProgress);
                                    }
                                });
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mPgsHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProgressActivity.this, R.string.end_, Toast.LENGTH_SHORT).show();
                                mPgsBar.setProgress(0);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
