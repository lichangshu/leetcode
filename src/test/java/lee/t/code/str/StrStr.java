package lee.t.code.str;

import org.junit.Assert;
import org.junit.Test;

public class StrStr {
    enum Solution {
        loop {// Timeout

            @Override
            public int strStr(String haystack, String needle) {
                if (needle.length() == 0) return 0;
                ; //;;;;
                int val = haystack.length();
                int len = needle.length();
                for (int i = 0; i < val; i++) {
                    for (int j = 0; j < len && i + j < val; j++) {
                        char ch = haystack.charAt(i + j);
                        char c2 = needle.charAt(j);
                        if (c2 != ch) {
                            break;
                        }
                        if (j + 1 == len) return i;
                    }
                }
                return -1;
            }
        },
        ;

        public abstract int strStr(String haystack, String needle);
    }

    @Test
    public void test() {
        for (Solution value : Solution.values()) {
            Assert.assertEquals(0, value.strStr("aaaaaaa", ""));
            Assert.assertEquals(2, value.strStr("aaTop", "Top"));
            Assert.assertEquals(-1, value.strStr("aaa", "aaaaa"));
        }
    }
}
