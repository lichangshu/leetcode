package lee.t.code.other;

import org.junit.Assert;
import org.junit.Test;

/**
 * 汉明距离
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * 示例 2：
 *
 * 输入：x = 3, y = 1
 * 输出：1
 *  
 *
 * 提示：
 *
 * 0 <= x, y <= 231 - 1
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnyode/
 */
public class HammingDistance {
    enum Solution {
        loop {
            @Override
            public int hammingDistance(int x, int y) {
                int count = 0;
                for (int i = 0; i < 32; i++) {
                    if ((x & 1) != (y & 1)) {
                        count++;
                    }
                    x = x >>> 1;
                    y = y >>> 1;
                    if (x == y) break;
                }
                return count;
            }
        },
        ;

        abstract public int hammingDistance(int x, int y);
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 4, 3, 1};
        int[] res = new int[]{2, 1};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < nums.length; i += 2) {
                Assert.assertEquals(res[i / 2], value.hammingDistance(nums[i], nums[i + 1]));
            }
        }
    }
}
