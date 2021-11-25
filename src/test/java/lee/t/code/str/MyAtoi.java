package lee.t.code.str;

import org.junit.Assert;
import org.junit.Test;

/**
 * 字符串转换整数 (atoi)
 * 请你来实现一个   myAtoi(string s)   函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数   myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,    231   − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231   − 1 的整数应该被固定为 231   − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnoilh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MyAtoi {
    enum Solution {
        toint {
            @Override
            public int myAtoi(String s) {
                int max = Integer.MAX_VALUE / 10;
                int status = 0; // 0: start, 1: +-, 2: 0-9
                int v = 0;
                int flag = 1;
                for (char c : s.toCharArray()) {
                    if (status == 0 && c == ' ') {
                        continue;
                    } else if (status == 0 && c == '+') {
                        status = 1;
                    } else if (status == 0 && c == '-') {
                        flag = -1;
                        status = 1;
                    } else if (status <= 1 && c >= '0' && c <= '9') {
                        status = 2;
                        v = c - '0';
                        continue;
                    } else if (status == 2 && c >= '0' && c <= '9') {
                        if (max < v) {
                            return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                        }
                        if (max == v) {
                            if (flag > 0) { // +2147483647
                                if (c > '7') {
                                    return Integer.MAX_VALUE;
                                }
                            } else { // -2147483648
                                if (c > '7') {// +2147483648 will overflow
                                    return Integer.MIN_VALUE;
                                }
                            }
                        }
                        v = c - '0' + v * 10;
                    } else {
                        break;
                    }
                }
                if (status == 2) {
                    return flag * v;
                }
                return 0;
            }
        },
        to2int {
            @Override
            public int myAtoi(String s) {
                int max = Integer.MAX_VALUE / 10;
                int flag = 1;

                char[] chars = s.toCharArray();
                int i = 0;
                for (; i < chars.length; i++) {
                    if (chars[i] == ' ') {
                        continue;
                    } else break;
                }

                if (i >= chars.length) {
                    return 0;
                }
                if (chars[i] == '+') {
                    i++;
                } else if (chars[i] == '-') {
                    flag = -1;
                    i++;
                }
                if (i >= chars.length) {
                    return 0;
                }
                if (chars[i] < '0' || chars[i] > '9') {
                    return 0; // not number !
                }

                int v = 0;
                for (; i < chars.length; i++) {
                    char c = chars[i];
                    if (c >= '0' && c <= '9') {
                        if (max < v) {
                            return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                        }
                        if (max == v) {
                            if (flag > 0) { // +2147483647
                                if (c > '7') {
                                    return Integer.MAX_VALUE;
                                }
                            } else { // -2147483648
                                if (c > '7') {// +2147483648 will overflow
                                    return Integer.MIN_VALUE;
                                }
                            }
                        }
                        v = c - '0' + v * 10;
                    } else break;
                }
                return flag * v;
            }
        };

        public abstract int myAtoi(String s);
    }

    @Test
    public void test() {
        for (Solution value : Solution.values()) {
            Assert.assertEquals(0, value.myAtoi(""));
            Assert.assertEquals(125, value.myAtoi("125"));
            Assert.assertEquals(-1, value.myAtoi("-1"));
            Assert.assertEquals(0, value.myAtoi(" "));
            Assert.assertEquals(12, value.myAtoi(" +12asdfaf"));
            Assert.assertEquals(2147483647, value.myAtoi(" +2147483647"));
            Assert.assertEquals(2147483647, value.myAtoi(" +2147483648"));
            Assert.assertEquals(2147483647, value.myAtoi(" 2147483648"));
            Assert.assertEquals(-2147483647, value.myAtoi(" -2147483647"));
            Assert.assertEquals(-2147483648, value.myAtoi(" -2147483648"));
            Assert.assertEquals(-2147483648, value.myAtoi(" -2147483649"));
            Assert.assertEquals(-12, value.myAtoi(" -12asdfaf"));
            Assert.assertEquals(0, value.myAtoi(" -asdasdf"));
            Assert.assertEquals(3, value.myAtoi("3.1415"));
            Assert.assertEquals(0, value.myAtoi("a12sdasdf"));
            Assert.assertEquals(0, value.myAtoi("a12sdasdf1356"));
        }
    }
}
