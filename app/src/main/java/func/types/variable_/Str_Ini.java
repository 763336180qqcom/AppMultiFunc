package func.types.variable_;

/**
 * 2016-08-18.
 */
public class Str_Ini {

    String str = new String("good");
    char[] chars = {'a', 'b', 'c'};

    private void change(String str_, char[] chars_) {
        str_ = "ok";
        chars_[0] = 'g';
    }

    public static String OutPuts()  {
        Str_Ini sIni = new Str_Ini();
        sIni.change(sIni.str, sIni.chars);
        return sIni.str + "\t" + sIni.chars[0];
    }
}
