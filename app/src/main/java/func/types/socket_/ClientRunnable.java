package func.types.socket_;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * author-ZKC
 * create on 2016-08-29-15-09.
 */
public class ClientRunnable implements Runnable {

    private Socket mSocket;
    Handler mHandler_SendToUI;
    Handler mHandler_FromUI;
    BufferedReader mBReader = null;
    OutputStream mOStream = null;

    public ClientRunnable(Handler handler) {
        mHandler_SendToUI = handler;
    }

    @Override
    public void run() {
        mSocket = new Socket();
        try {
            mSocket.connect(new InetSocketAddress("4s.dkys.org", 18266), 10000);
            mBReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mOStream = mSocket.getOutputStream();
            new Thread() {
                @Override
                public void run() {
                    String content;
                    try {
                        while ((content = mBReader.readLine()) != null) {
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = content;
                            mHandler_SendToUI.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            Looper.prepare();
            mHandler_FromUI = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x345) {
                        try {
                            mOStream.write((msg.obj.toString() + "\r\n").getBytes());
                            mOStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (SocketTimeoutException e) {
            Message msg = new Message();
            msg.what = 0x123;
            msg.obj = "网络连接超时";
            mHandler_SendToUI.sendMessage(msg);
        } catch (IOException e) {
            Message msg = new Message();
            msg.what = 0x123;
            msg.obj = e.getMessage();
            mHandler_SendToUI.sendMessage(msg);
            e.printStackTrace();
        }
    }


}
