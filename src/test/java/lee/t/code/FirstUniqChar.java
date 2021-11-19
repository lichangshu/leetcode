package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// 字符串中的第一个唯一字符
// 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
// 提示：你可以假定该字符串只包含小写字母。
public class FirstUniqChar {

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[][] cs = new int[26][2];
        for (int i = 0; i < chars.length; i++) {
            int[] ccc = cs[chars[i] - 'a'];
            ccc[0]++;
            ccc[1] = i;
        }
        int min = chars.length;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i][0] == 1) {
                if (cs[i][1] < min) {
                    min = cs[i][1];
                }
            }
        }
        if (min >= chars.length) {
            return -1;
        }
        return min;
    }

    @Test
    public void testFirstUniqChar() {
        Assert.assertEquals(0, firstUniqChar("leetcode"));
        Assert.assertEquals(2, firstUniqChar("loveleetcode"));
        Assert.assertEquals(-1, firstUniqChar(""));
        Assert.assertEquals(0, firstUniqChar("a"));
        Assert.assertEquals(61, firstUniqChar("itwqbtcdprfsuprkrjkausiterybzncbmdvkgljxuekizvaivszowqtmrttiihervpncztuoljftlxybpgwnjb"));
    }
}
