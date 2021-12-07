package lee.t.code.other;

import org.junit.Assert;
import org.junit.Test;

/**
 * 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *
 *  
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn1m0i/
 */
public class HammingWeight {
    public enum Solution {
        loop {
            @Override
            public int hammingWeight(int n) {
                int v = 0;
                for (int i = 0; i < 32; i++) {
                    if ((n & 1) == 1) {
                        v++;
                    }
                    n = n >>> 1;
                }
                return v;
            }
        };

        // you need to treat n as an unsigned value
        abstract public int hammingWeight(int n);
    }

    @Test
    public void test() {
        int[] nums = new int[]{0B00000000000000000000000000001011, 0B00000000000000000000000010000000, 0B11111111111111111111111111111101};
        int[] res = new int[]{3, 1, 31};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < nums.length; i++) {
                Assert.assertEquals(res[i], value.hammingWeight(nums[i]));
            }
        }
    }
}
