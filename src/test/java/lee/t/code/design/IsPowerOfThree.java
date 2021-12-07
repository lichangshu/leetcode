package lee.t.code.design;

import org.junit.Assert;
import org.junit.Test;

/**
 * 打乱数组
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 *
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 *  
 *
 * 示例：
 *
 * 输入
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * 输出
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 5 * 104 次 reset 和 shuffle
 * 相关标签
 * 数组
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn6gq1/
 */
public class IsPowerOfThree {
    enum Solution {
        good {
            @Override
            public boolean isPowerOfThree(int n) {
                if (n <= 0) {
                    return false;
                }
                return 1162261467 % n == 0;
            }
        },
        loop {
            @Override
            public boolean isPowerOfThree(int n) {
                if (n <= 0) {
                    return false;
                }
                while (n % 3 == 0) {
                    n = n / 3;
                }
                return n == 1;
            }
        },
        recursion {
            @Override
            public boolean isPowerOfThree(int n) {
                if (n <= 0) {
                    return false;
                }
                if (n % 3 != 0) {
                    return n == 1;
                }
                return isPowerOfThree(n / 3);
            }
        },
        ;

        public abstract boolean isPowerOfThree(int n);
    }

    @Test
    public void test() {
        var nums = new int[]{-4, 0, 1, 2, 3, 100, 1594323, 1162261467, 1594323};
        var res = new boolean[]{false, false, true, false, true, false, true, true, true};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < nums.length; i++) {
                System.out.println(value.name() + " ," + nums[i]);
                Assert.assertEquals(res[i], value.isPowerOfThree(nums[i]));
            }
        }
    }
}
