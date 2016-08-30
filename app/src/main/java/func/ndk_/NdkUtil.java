package func.ndk_;

/**
 * author-ZKC
 * create on 2016-08-25-20-00.
 */
public class NdkUtil {
    public native String getStringFromNative();
    static {
        System.loadLibrary("myLib_c");
    }
}
