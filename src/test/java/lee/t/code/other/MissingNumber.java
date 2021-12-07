package lee.t.code.other;

import org.junit.Assert;
import org.junit.Test;

/**
 * 缺失数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 * <p>
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 * <p>
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 *  
 * <p>
 * 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 * <p>
 * 相关标签
 * 位运算
 * 数组
 * 哈希表
 * 数学
 * 排序
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnj4mt/
 */
public class MissingNumber {
    enum Solution {
        sum {
            @Override
            public int missingNumber(int[] nums) {
                int sum = 0, min = nums[0];
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] < min) {
                        min = nums[i];
                    }
                    sum += nums[i];
                }
                int s2 = (min + min + nums.length) * (nums.length + 1) / 2;
                return s2 - sum;
            }
        },
        xor { // a ^ b ^ a == a ^ a ^ b

            @Override
            public int missingNumber(int[] nums) {
                int v = 0, min = nums[0];
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] < min) {
                        min = nums[i];
                    }
                }
                for (int i = 0; i < nums.length; i++) {
                    v = v ^ (nums[i] - min) ^ i;
                }
                return (v ^ nums.length) + min;
            }
        },
        ;

        abstract public int missingNumber(int[] nums);
    }

    @Test
    public void test() {
        var nmbs = new int[][]{{3, 0, 1}, {9, 6, 4, 2, 3, 5, 7, 0, 1}, {0, 1}, {1, 3}, {4, 6, 8, 7}, {9, 1, 2, 7, 8, 3, 4, 6},};
        var res = new int[]{2, 8, 2, 2, 5, 5};

        for (Solution value : Solution.values()) {
            for (int i = 0; i < nmbs.length; i++) {
                System.out.println(value + ", " + i);
                Assert.assertEquals(res[i], value.missingNumber(nmbs[i]));
            }
        }
    }
}
