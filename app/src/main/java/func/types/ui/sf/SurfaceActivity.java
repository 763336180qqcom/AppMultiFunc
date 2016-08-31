package func.types.ui.sf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * author-ZKC
 * create on 2016-08-31-20-24.
 */
public class SurfaceActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SFView(this));
    }

    class SFView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder holder;
        private SFThread mThread;

        public SFView(Context context) {
            super(context);
            holder = this.getHolder();
            holder.addCallback(this);
            mThread = new SFThread(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mThread.isRunning = true;
            mThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mThread.isRunning = false;
            mThread.interrupt();
        }
    }

    class SFThread extends Thread {
        private SurfaceHolder holder;
        public boolean isRunning = false;

        public SFThread(SurfaceHolder holder) {
            this.holder = holder;
            isRunning = true;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            int count = 0;
            while (isRunning) {
                try {
                    synchronized (holder) {
                        canvas = holder.lockCanvas();
                        if (canvas != null) {
                            canvas.drawColor(Color.YELLOW);
                            Paint p = new Paint();
                            p.setColor(Color.GRAY);
                            p.setTextSize(100);
                            Rect r = new Rect(400, 400, 400, 400);
                            canvas.drawRect(r, p);
                            canvas.drawText("第" + (count++) + "秒", 300, 310, p);
                            Thread.sleep(1000);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
