package lee.t.code.str;

import org.junit.Assert;
import org.junit.Test;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若  s 和 t  中每个字符出现的次数都相同，则称  s 和 t  互为字母异位词。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn96us/
 */
public class IsAnagram {
    public enum Solution {
        bucket {
            @Override
            public boolean isAnagram(String s, String t) {
                int[] az = new int[26];
                for (char c : s.toCharArray()) {
                    az[c - 'a']++;
                }
                for (char c : t.toCharArray()) {
                    az[c - 'a']--;
                }
                for (int i : az) {
                    if (i != 0) return false;
                }
                return true;
            }
        };

        public abstract boolean isAnagram(String s, String t);

    }

    @Test
    public void testSolution() {
        for (Solution value : Solution.values()) {
            Assert.assertTrue(value.isAnagram("anagram", "nagaram"));
            Assert.assertFalse(value.isAnagram("rat", "car"));
        }
    }
}
