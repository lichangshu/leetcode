package lee.t.code;

import lee.t.code.ann.Examination;
import lee.t.code.ann.Solution;
import org.junit.Assert;
import org.junit.Test;

@Examination(
        value = {"给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。"},
        url = "https://leetcode-cn.com/problems/longest-palindromic-substring/"
)
public class LongestPalindrome {

    public String solutionDP(String s) { // 动态规划, 如果是回文,则子串也是回文
        return null;
    }

    @Solution
    public String solutionCenter(String s) { // 回文中心扩散, 时间复杂度 O(n^2)
        if (s.length() <= 1) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            String rs = sub(s, i, i);
            if (rs.length() > res.length()) {
                res = rs;
            }
            if (s.charAt(i) == s.charAt(i - 1)) {
                rs = sub(s, i - 1, i);
                if (rs.length() > res.length()) {
                    res = rs;
                }
            }
        }
        return res;
    }

    private String sub(String s, int left, int right) {
        boolean ch = false;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                ch = true;
            } else {
                break;
            }
        }
        if (!ch) {
            return s.substring(left, right);
        }
        return s.substring(left + 1, right);
    }

    @Solution
    public String solution(String s) { // 暴力遍历 时间复杂度 O(n^3)
        int from = 0, to = 0, max = from - to;
        for (int i = 0; i < s.length() - 1; i++) {
            char f = s.charAt(i);
            for (int j = i + max + 1; j < s.length(); j++) {
                char t = s.charAt(j);
                if (f == t) {
                    boolean ok = check(s, i, j);
                    if (ok && j - i > max) {
                        from = i;
                        to = j;
                        max = to - from;
                    }
                }
            }
        }
        return s.substring(from, to + 1);
    }

    private boolean check(String val, int start, int end) {
        for (int i = 0; ; i++) {
            int from = start + i;
            int to = end - i;
            if (from >= to) {
                break;
            } else {
                if (val.charAt(from) == val.charAt(to)) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals("a", solution("a"));
        Assert.assertEquals("aca", solution("aacabdkacaa"));
        Assert.assertEquals("bb", solution("cbbd"));
        Assert.assertEquals("aaaaaaaa", solution("aaaaaaaa"));

        Assert.assertEquals("a", solutionCenter("a"));
        Assert.assertEquals("aca", solutionCenter("aacabdkacaa"));
        Assert.assertEquals("bb", solutionCenter("cbbd"));
        Assert.assertEquals("aaaaaaaa", solutionCenter("aaaaaaaa"));
    }
}
