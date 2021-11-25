package lee.t.code.str;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IsPalindrome {
    enum Solution {
        loop {
            @Override
            public boolean isPalindrome(String s) {
                List<Character> list = new ArrayList<>();
                char[] chars = s.toCharArray();
                for (char c : chars) {
                    if (c >= '0' && c <= '9') {
                        list.add(c);
                    } else if (c >= 'a' && c <= 'z') {
                        list.add(c);
                    } else if (c >= 'A' && c <= 'Z') {
                        int v = c - 'A' + 'a';
                        list.add((char) v);
                    }
                }
                for (int i = 0, j = list.size() - 1; i <= j; i++, j--) {
                    Character c = list.get(i);
                    if (!list.get(i).equals(list.get(j))) {
                        return false;
                    }
                }
                return true;
            }
        },
        loop2 {
            @Override
            public boolean isPalindrome(String s) {
                s = s.toLowerCase();
                int len = s.length();
                for (int i = 0, j = len - 1; i <= j; i++, j--) {
                    for (; i <= j; i++) {
                        if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                            break;
                        } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                            break;
                        }
                    }
                    for (; i <= j; j--) {
                        if (s.charAt(j) >= 'a' && s.charAt(j) <= 'z') {
                            break;
                        } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                            break;
                        }
                    }
                    if (i >= j) {
                        break;
                    }
                    if (i < len && s.charAt(i) != s.charAt(j)) {
                        return false;
                    }
                }
                return true;
            }
        };

        public abstract boolean isPalindrome(String s);
    }

    @Test
    public void test() {
        for (Solution value : Solution.values()) {
            Assert.assertTrue(value.isPalindrome(" "));
            Assert.assertTrue(value.isPalindrome("   "));
            Assert.assertTrue(value.isPalindrome("a a"));
            Assert.assertTrue(value.isPalindrome("A man, a plan, a canal: Panama"));
            Assert.assertFalse(value.isPalindrome("race a car"));
            Assert.assertFalse(value.isPalindrome("OP"));
            Assert.assertTrue(value.isPalindrome("A man, a plan, a canal: Panama"));
        }
    }
}
