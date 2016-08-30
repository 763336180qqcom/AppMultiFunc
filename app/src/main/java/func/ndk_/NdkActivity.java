package func.ndk_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import func.types.R;

/**
 * author-ZKC
 * create on 2016-08-25-20-13.
 */
public class NdkActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        mTextView= (TextView) findViewById(R.id.tv_ndk);
        NdkUtil ndkUtil=new NdkUtil();
        mTextView.setText(ndkUtil.getStringFromNative());
    }
}
