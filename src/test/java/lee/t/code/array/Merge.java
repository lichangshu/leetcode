package lee.t.code.array;

import lee.t.code.Sorter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnumcr/
 */
public class Merge {
    public enum Solution {
        loop {// 从前向后

            @Override
            public void merge(int[] nums1, int m, int[] nums2, int n) {
                if (nums1.length != m + n) throw new IllegalArgumentException("nums1.length != m + n");
                for (int i = m - 1, k = nums1.length - 1; i >= 0; i--, k--) {
                    nums1[k] = nums1[i];
                }
                int k = 0;
                for (int i = n, index = 0; i < nums1.length; index++) {
                    if (k < n && nums1[i] > nums2[k]) {
                        nums1[index] = nums2[k];
                        k++;
                    } else {
                        nums1[index] = nums1[i];
                        i++;
                    }
                }
                int less = n - k;
                if (less > 0) {
                    System.arraycopy(nums2, k, nums1, nums1.length - less, less);
                }
            }
        },
        back { // 从后向前, 减少一次 for 循环

            @Override
            public void merge(int[] nums1, int m, int[] nums2, int n) {
                if (nums1.length != m + n) throw new IllegalArgumentException("nums1.length != m + n");
                for (int index = nums1.length - 1, i = n - 1, k = m - 1; i >= 0; index--) {
                    if (k < 0 || nums2[i] > nums1[k]) {
                        nums1[index] = nums2[i];
                        i--;
                    } else {
                        nums1[index] = nums1[k];
                        k--;
                    }
                }
            }
        },
        api {
            @Override
            public void merge(int[] nums1, int m, int[] nums2, int n) {
                System.arraycopy(nums2, 0, nums1, m, nums2.length);
                Arrays.sort(nums1);
            }
        };

        //nums1.length == m + n
        //nums2.length == n
        public abstract void merge(int[] nums1, int m, int[] nums2, int n);
    }

    @Test
    public void testSolution() {
        for (Merge.Solution solution : Merge.Solution.values()) {
            var ct = new int[]{0};
            solution.merge(ct, 0, new int[]{1}, 1);
            Sorter.printArray(ct);
            Assert.assertArrayEquals(new int[]{1}, ct);

            ct = new int[]{9};
            solution.merge(ct, 1, new int[]{}, 0);
            Sorter.printArray(ct);
            Assert.assertArrayEquals(new int[]{9}, ct);

            ct = new int[]{1, 2, 3, 4, 5, 6, 0, 0, 0, 0};
            solution.merge(ct, 6, new int[]{1, 2, 3, 4}, 4);
            Sorter.printArray(ct);
            Assert.assertArrayEquals(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 6}, ct);

            ct = new int[]{1, 2, 3, 0, 0, 0, 0, 0};
            solution.merge(ct, 3, new int[]{1, 2, 3, 4, 5}, 5);
            Sorter.printArray(ct);
            Assert.assertArrayEquals(new int[]{1, 1, 2, 2, 3, 3, 4, 5}, ct);

            ct = new int[]{1, 1, 2, 0, 0, 0, 0, 0};
            solution.merge(ct, 3, new int[]{2, 2, 2, 4, 5}, 5);
            Sorter.printArray(ct);
            Assert.assertArrayEquals(new int[]{1, 1, 2, 2, 2, 2, 4, 5}, ct);
        }
    }
}
