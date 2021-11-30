package lee.t.code.dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * <p>
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *  
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn3cg3/
 */
public class MaxSubArray {
    enum Solution {
        loop {
            @Override
            public int maxSubArray(int[] nums) {
                int max = nums[0];
                int pre = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    if (pre < 0) {
                        pre = nums[i];
                    } else {
                        pre += nums[i];
                    }
                    if (pre > max) {
                        max = pre;
                    }
                }
                return max;
            }
        },
        loop2 {
            @Override
            public int maxSubArray(int[] nums) {
                int max = nums[0];
                int pre = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    pre = Math.max(pre, 0) + nums[i];
                    max = Math.max(pre, max);
                }
                return max;
            }
        };

        public abstract int maxSubArray(int[] nums);

    }

    @Test
    public void test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        for (Solution value : Solution.values()) {
            Assert.assertEquals(6, value.maxSubArray(nums));
        }
    }
}
