package func.types.browser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import func.types.R;

/**
 * author-ZKC
 * create on 2016-08-23-22-12.
 */
public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceSate) {
        super.onCreate(saveInstanceSate);
        setContentView(R.layout.activity_browser);
        findViewById(R.id.b_start_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri=Uri.parse("http://www.baidu.com");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        findViewById(R.id.b_start_definite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri=Uri.parse("http://www.baidu.com");
                intent.setData(uri);
                intent.setClassName("com.us.browser","com.uc.browser.ActivityUpdate");
                startActivity(intent);
            }
        });
    }
}
