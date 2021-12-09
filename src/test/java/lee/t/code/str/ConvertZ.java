package lee.t.code.str;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 * 通过次数329,582提交次数647,65
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConvertZ {
    enum Solution {
        loop {
            public String convert(String s, int numRows) {
                if (numRows == 1) return s;
                StringBuilder sb = new StringBuilder();
                int n = (numRows - 1) * 2;
                for (int i = 0; i < numRows; i++) {
                    for (int j = i; j < s.length(); j += n) {
                        char ch = s.charAt(j);
                        sb.append(ch);
                        //print.append(ch);
                        //IntStream.range(0, (numRows - 2) * 2 + 1).forEach(e -> print.append(" "));
                        if (i > 0 && i < numRows - 1) {
                            int ix = j + n - i * 2;
                            if (ix >= s.length()) {
                                break;
                            }
                            char c = s.charAt(ix);
                            sb.append(c);
                            //print.setCharAt(print.length() - i * 2, c); // +i 结尾有个 \n
                        }
                    }
                    //print.append("\n");
                }
                return sb.toString();
            }

            public String convert(String s, int numRows, StringBuilder print) {
                if (numRows == 1) return s;
                StringBuilder sb = new StringBuilder();
                int n = (numRows - 1) * 2;
                for (int i = 0; i < numRows; i++) {
                    for (int j = i; j < s.length(); j += n) {
                        char ch = s.charAt(j);
                        sb.append(ch);
                        print.append(ch);
                        IntStream.range(0, (numRows - 2) * 2 + 1).forEach(e -> print.append(" "));
                        if (i > 0 && i < numRows - 1) {
                            int ix = j + n - i * 2;
                            if (ix >= s.length()) {
                                break;
                            }
                            char c = s.charAt(ix);
                            sb.append(c);
                            print.setCharAt(print.length() - i * 2, c); // +i 结尾有个 \n
                        }
                    }
                    print.append("\n");
                }
                return sb.toString();
            }
        },
        ;

        public String convert(String s, int numRows) {
            return convert(s, numRows, new StringBuilder());
        }

        abstract public String convert(String s, int numRows, StringBuilder stringBuilder);
    }

    @Test
    public void test() {
        var strs = new String[]{"A", "A", "PAYPALISHIRING", "PAYPALISHIRING", "PAYPALISHIRING", "01234567890123456789012345678901234567890"};
        var nums = new int[]{1, 2, 3, 3, 4, 5};
        var ress = new String[]{"A", "A", "PAHNAPLSIIGYIR", "PAHNAPLSIIGYIR", "PINALSIGYAHRPI", "08642017957351392604826048351391795742086"};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < strs.length; i++) {
                Assert.assertEquals(ress[i], value.convert(strs[i], nums[i]));
            }
        }
    }

    @Test
    public void print() {
        var strs = new String[]{"PAYPALISHIRING", "PAYPALISHIRING", "PAYPALISHIRING", "01234567890123456789012345678901234567890"};
        var nums = new int[]{3, 3, 4, 5};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < strs.length; i++) {
                StringBuilder sb = new StringBuilder();
                value.convert(strs[i], nums[i], sb);
                System.out.println(sb);
            }
        }
    }
}
