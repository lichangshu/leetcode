package lee.t.code.dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnq4km/
 */
public class Rob {
    enum Solution {
        loop {
            @Override
            public int rob(int[] nums) {
                int len = nums.length;
                int[][] nms = new int[len][2];
                nms[0][0] = 0;
                nms[0][1] = nums[0];
                for (int i = 1; i < len; i++) {
                    nms[i][0] = Math.max(nms[i - 1][0], nms[i - 1][1]);
                    nms[i][1] = nms[i - 1][0] + nums[i];
                }
                return Math.max(nms[len - 1][0], nms[len - 1][1]);
            }
        },
        loop2simple {
            @Override
            public int rob(int[] nums) {
                int len = nums.length;
                int none = 0;
                int rob = nums[0];
                for (int i = 1; i < len; i++) {
                    int v = none;
                    none = Math.max(none, rob);
                    rob = v + nums[i];
                }
                return Math.max(none, rob);
            }
        },
        loop2one {
            @Override
            public int rob(int[] nums) { // why ???
                int len = nums.length;
                if (len == 1) return nums[0];
                if (len == 2) return Math.max(nums[0], nums[1]);
                for (int i = 2; i < len; i++) {
                    nums[i] = Math.max(nums[i - 1], nums[i] + nums[i - 2]);
                    System.out.println(nums[i]);
                }
                return nums[len - 1];
            }
        },
        ;

        public abstract int rob(int[] nums);
    }

    @Test
    public void testSolution() {
        int[][] its = {
                {1, 2, 3, 1,},
                {2, 7, 9, 3, 1,},
                {1, 2, 3, 4, 5, 6},
        };
        int[] res = {4, 12, 12,};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < its.length; i++) {
                int[] it = its[i];
                int rob = value.rob(it);
                Assert.assertEquals(res[i], rob);
            }
        }
    }
}
