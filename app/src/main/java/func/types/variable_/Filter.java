package func.types.variable_;

import java.util.ArrayList;
import java.util.List;

/**
 * author-ZKC
 * create on 2016-08-31-22-14.
 */
public class Filter {

    public static String removeRedundantString(String str) {
        List<ST> ss = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            ST s = new ST();
            char a = str.charAt(i);
            s.setName(String.valueOf(a));
            int count = 0;
            s.setNum(count);
            boolean f = false;
            for (int k = 0; k < ss.size(); k++) {
                if (ss.get(k).getName().equals(s.getName())) {
                    f = true;
                    break;
                }
            }
            if (f) {
                continue;
            }
            for (int j = 0; j < str.length(); j++) {
                if (a == str.charAt(j)) {
                    s.setNum(++count);
                }
            }
            ss.add(s);

        }
        return ss.toString().replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
    }

    static class ST {

        @Override
        public String toString() {
            return name + num;
        }

        private int num;
        private String name;

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
