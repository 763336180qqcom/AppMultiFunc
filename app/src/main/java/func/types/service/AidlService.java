package func.types.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import func.types.IMyAidl;

/**
 * author-ZKC
 * create on 2016-08-24-10-00.
 */
public class AidlService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAidlStub;
    }

    IMyAidl.Stub mAidlStub = new IMyAidl.Stub() {
        @Override
        public String getMsg(String aString) throws RemoteException {
            return aString + "-aidl";
        }
    };
}
