package lee.t.code.str;

import org.junit.Assert;
import org.junit.Test;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * 相关标签
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnmav1/
 */
public class LongestCommonPrefix {
    public enum Solution {
        loop {
            @Override
            public String longestCommonPrefix(String[] strs) {
                String start = strs[0];
                int p = start.length();
                for (int i = 1; i < strs.length; i++) {
                    String str = strs[i];
                    int j = 0;
                    for (; j < start.length() && j < str.length(); j++) { // indexOf ??
                        if (str.charAt(j) != start.charAt(j)) {
                            break;
                        }
                    }
                    if (j < p) {
                        p = j;
                    }
                }
                return start.substring(0, p);
            }
        },
        loopWithMin {
            @Override
            public String longestCommonPrefix(String[] strs) {
                String start = strs[0]; // 最短的字符串
                for (String str : strs) {
                    if (start.length() > str.length()) {
                        start = str;
                    }
                }
                int p = start.length();
                for (int i = 0; i < strs.length; i++) {
                    String str = strs[i];
                    int j = 0;
                    for (; j < start.length() && j < str.length(); j++) { // indexOf ??
                        if (str.charAt(j) != start.charAt(j)) {
                            break;
                        }
                    }
                    if (j < p) {
                        p = j;
                    }
                }
                return start.substring(0, p);
            }
        };

        public abstract String longestCommonPrefix(String[] strs);
    }

    @Test
    public void test() {
        String[] strs = new String[]{"asaaaaa", "adbcd", "asdf", "asssadf", "a"};
        for (Solution value : Solution.values()) {
            Assert.assertEquals("a", value.longestCommonPrefix(strs));
        }
    }
}
