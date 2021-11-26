package lee.t.code.str;

import org.junit.Assert;
import org.junit.Test;

/**
 * 实现 strStr()
 * 实现strStr()函数。
 * <p>
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnr003/
 */
public class StrStr {
    enum Solution {
        loop2loop {// Timeout

            @Override
            public int strStr(String haystack, String needle) {
                if (needle.length() == 0) return 0;
                ; //;;;;
                int olen = haystack.length();
                int nlen = needle.length();
                for (int i = 0; i < olen; i++) {
                    int j = 0;
                    for (; j < nlen && i + j < olen; j++) {
                        if (haystack.charAt(i + j) != needle.charAt(j)) {
                            break;
                        }
                    }
                    if (j == nlen) return i;
                }
                return -1;
            }
        },
        loop {// Timeout

            @Override
            public int strStr(String haystack, String needle) {
                if (needle.length() == 0) return 0;
                ; //;;;;
                int olen = haystack.length();
                int nlen = needle.length();
                int i = 0, j = 0;
                for (; i < olen && j < nlen; ) {
                    if (haystack.charAt(i) == needle.charAt(j)) {
                        j++;
                    } else {
                        i = i - j; // back
                        j = 0;
                    }
                    i++;
                }
                if (j == nlen) return i - j;
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
            Assert.assertEquals(4, value.strStr("mississippi", "issip"));
            Assert.assertEquals(-1, value.strStr("baaa", "abbb"));
            Assert.assertEquals(4, value.strStr("aabaaabaaac", "aabaaac"));
        }
    }
}
