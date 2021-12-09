package lee.t.code.design;

import org.junit.Assert;
import org.junit.Test;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/problems/container-with-most-water/
 */
public class WaterMax {
    enum Solution {
        twoPoint {
            @Override
            public int maxArea(int[] height) {
                int p1 = 0, p2 = height.length - 1, max = 0;
                for (; p1 < p2; ) {
                    int v = Math.min(height[p1], height[p2]) * (p2 - p1);
                    if (v > max) {
                        max = v;
                    }
                    if (height[p1] > height[p2]) {
                        p2--;
                    } else {
                        p1++;
                    }
                }
                return max;
            }
        },
        ;

        abstract public int maxArea(int[] height);
    }

    @Test
    public void test() {
        var strs = new int[][]{{1, 2, 1}, {4, 3, 2, 1, 4}, {1, 1}, {1, 8, 6, 2, 5, 4, 8, 3, 7}};
        var vals = new int[]{2, 16, 1, 49};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < strs.length; i++) {
                Assert.assertEquals(vals[i], value.maxArea(strs[i]));
            }
        }
    }
}
